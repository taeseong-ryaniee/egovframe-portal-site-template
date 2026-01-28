package egovframework.let.utl.fcc.service

import java.io.IOException
import java.io.ObjectInputStream
import java.io.Serializable
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import kotlin.concurrent.Volatile

/**
 *
 * A class that represents an immutable universally unique identifier (UUID). A
 * UUID represents a 128-bit value.
 *
 *
 *
 * There exist different variants of these global identifiers. The methods of
 * this class are for manipulating the Leach-Salz variant, although the
 * constructors allow the creation of any variant of UUID (described below).
 *
 *
 *
 * The layout of a variant 2 (Leach-Salz) UUID is as follows:
 *
 * The most significant long consists of the following unsigned fields:
 *
 * <pre>
 * 0xFFFFFFFF00000000 time_low
 * 0x00000000FFFF0000 time_mid
 * 0x000000000000F000 version
 * 0x0000000000000FFF time_hi
</pre> *
 *
 * The least significant long consists of the following unsigned fields:
 *
 * <pre>
 * 0xC000000000000000 variant
 * 0x3FFF000000000000 clock_seq
 * 0x0000FFFFFFFFFFFF node
</pre> *
 *
 *
 *
 * The variant field contains a value which identifies the layout of the
 * <tt>UUID</tt>. The bit layout described above is valid only for a
 * <tt>UUID</tt> with a variant value of 2, which indicates the Leach-Salz
 * variant.
 *
 *
 *
 * The version field holds a value that describes the type of this <tt>UUID</tt>.
 * There are four different basic types of UUIDs: time-based, DCE security,
 * name-based, and randomly generated UUIDs. These types have a version value of
 * 1, 2, 3 and 4, respectively.
 *
 *
 *
 * For more information including algorithms used to create <tt>UUID</tt>s,
 * see the Internet-Draft [UUIDs
 * and GUIDs](http://www.ietf.org/internet-drafts/draft-mealling-uuid-urn-03.txt) or the standards body definition at [ISO/IEC 11578:1996](http://www.iso.ch/cate/d2229.html).
 *
 * @version 1.14, 07/12/04
 * @since 1.5
 */
class EgovFormBasedUUID : Serializable {
    /**
     * Returns the most significant 64 bits of this UUID's 128 bit value.
     *
     * @return the most significant 64 bits of this UUID's 128 bit value.
     */
    /*
          * The most significant 64 bits of this UUID.
          *
          * @serial
          */
    val mostSignificantBits: Long

    /**
     * Returns the least significant 64 bits of this UUID's 128 bit value.
     *
     * @return the least significant 64 bits of this UUID's 128 bit value.
     */
    /*
          * The least significant 64 bits of this UUID.
          *
          * @serial
          */
    val leastSignificantBits: Long

    /*
     * The version number associated with this UUID. Computed on demand.
     */
    @Transient
    private var version = -1

    /*
     * The variant number associated with this UUID. Computed on demand.
     */
    @Transient
    private var variant = -1

    /*
     * The timestamp associated with this UUID. Computed on demand.
     */
    @Volatile
    @Transient
    private var timestamp: Long = -1

    /*
     * The clock sequence associated with this UUID. Computed on demand.
     */
    @Transient
    private var sequence = -1

    /*
     * The node number associated with this UUID. Computed on demand.
     */
    @Transient
    private var node: Long = -1

    /*
     * The hashcode of this UUID. Computed on demand.
     */
    @Transient
    private var hashCode = -1

    // Constructors and Factories
    /*
     * Private constructor which uses a byte array to construct the new UUID.
     */
    private constructor(data: ByteArray) {
        var msb: Long = 0
        var lsb: Long = 0
        for (i in 0..7) msb = (msb shl 8) or (data[i].toInt() and 0xff).toLong()
        for (i in 8..15) lsb = (lsb shl 8) or (data[i].toInt() and 0xff).toLong()
        this.mostSignificantBits = msb
        this.leastSignificantBits = lsb
    }

    /**
     * Constructs a new <tt>UUID</tt> using the specified data.
     * <tt>mostSigBits</tt> is used for the most significant 64 bits of the
     * <tt>UUID</tt> and <tt>leastSigBits</tt> becomes the least significant
     * 64 bits of the <tt>UUID</tt>.
     *
     * @param mostSigBits
     * @param leastSigBits
     */
    constructor(mostSigBits: Long, leastSigBits: Long) {
        this.mostSignificantBits = mostSigBits
        this.leastSignificantBits = leastSigBits
    }

    // Field Accessor Methods

    /**
     * The version number associated with this <tt>UUID</tt>. The version
     * number describes how this <tt>UUID</tt> was generated.
     *
     * The version number has the following meaning:
     *
     *
     *
     *  * 1 Time-based UUID
     *  * 2 DCE security UUID
     *  * 3 Name-based UUID
     *  * 4 Randomly generated UUID
     *
     *
     * @return the version number of this <tt>UUID</tt>.
     */
    fun version(): Int {
        if (version < 0) {
            // Version is bits masked by 0x000000000000F000 in MS long
            version = ((this.mostSignificantBits shr 12) and 0x0fL).toInt()
        }
        return version
    }

    /**
     * The variant number associated with this <tt>UUID</tt>. The variant
     * number describes the layout of the <tt>UUID</tt>.
     *
     * The variant number has the following meaning:
     *
     *
     *
     *  * 0 Reserved for NCS backward compatibility
     *  * 2 The Leach-Salz variant (used by this class)
     *  * 6 Reserved, Microsoft Corporation backward compatibility
     *  * 7 Reserved for future definition
     *
     *
     * @return the variant number of this <tt>UUID</tt>.
     */
    fun variant(): Int {
        if (variant < 0) {
            // This field is composed of a varying number of bits
            if ((this.leastSignificantBits ushr 63) == 0L) {
                variant = 0
            } else if ((this.leastSignificantBits ushr 62) == 2L) {
                variant = 2
            } else {
                variant = (this.leastSignificantBits ushr 61).toInt()
            }
        }
        return variant
    }

    /**
     * The timestamp value associated with this UUID.
     *
     *
     *
     * The 60 bit timestamp value is constructed from the time_low, time_mid,
     * and time_hi fields of this <tt>UUID</tt>. The resulting timestamp is
     * measured in 100-nanosecond units since midnight, October 15, 1582 UTC.
     *
     *
     *
     * The timestamp value is only meaningful in a time-based UUID, which has
     * version type 1. If this <tt>UUID</tt> is not a time-based UUID then
     * this method throws UnsupportedOperationException.
     *
     * @throws UnsupportedOperationException
     * if this UUID is not a version 1 UUID.
     */
    fun timestamp(): Long {
        if (version() != 1) {
            throw UnsupportedOperationException("Not a time-based UUID")
        }
        var result = timestamp
        if (result < 0) {
            result = (this.mostSignificantBits and 0x0000000000000FFFL) shl 48
            result = result or (((this.mostSignificantBits shr 16) and 0xFFFFL) shl 32)
            result = result or (this.mostSignificantBits ushr 32)
            timestamp = result
        }
        return result
    }

    /**
     * The clock sequence value associated with this UUID.
     *
     *
     *
     * The 14 bit clock sequence value is constructed from the clock sequence
     * field of this UUID. The clock sequence field is used to guarantee
     * temporal uniqueness in a time-based UUID.
     *
     *
     *
     * The clockSequence value is only meaningful in a time-based UUID, which
     * has version type 1. If this UUID is not a time-based UUID then this
     * method throws UnsupportedOperationException.
     *
     * @return the clock sequence of this <tt>UUID</tt>.
     * @throws UnsupportedOperationException
     * if this UUID is not a version 1 UUID.
     */
    fun clockSequence(): Int {
        if (version() != 1) {
            throw UnsupportedOperationException("Not a time-based UUID")
        }
        if (sequence < 0) {
            sequence = ((this.leastSignificantBits and 0x3FFF000000000000L) ushr 48).toInt()
        }
        return sequence
    }

    /**
     * The node value associated with this UUID.
     *
     *
     *
     * The 48 bit node value is constructed from the node field of this UUID.
     * This field is intended to hold the IEEE 802 address of the machine that
     * generated this UUID to guarantee spatial uniqueness.
     *
     *
     *
     * The node value is only meaningful in a time-based UUID, which has version
     * type 1. If this UUID is not a time-based UUID then this method throws
     * UnsupportedOperationException.
     *
     * @return the node value of this <tt>UUID</tt>.
     * @throws UnsupportedOperationException
     * if this UUID is not a version 1 UUID.
     */
    fun node(): Long {
        if (version() != 1) {
            throw UnsupportedOperationException("Not a time-based UUID")
        }
        if (node < 0) {
            node = this.leastSignificantBits and 0x0000FFFFFFFFFFFFL
        }
        return node
    }

    // Object Inherited Methods
    /**
     * Returns a `String` object representing this
     * `UUID`.
     *
     *
     *
     * The UUID string representation is as described by this BNF :
     *
     * <pre>
     * UUID                   = &lt;time_low&gt; &quot;-&quot; &lt;time_mid&gt; &quot;-&quot;
     * &lt;time_high_and_version&gt; &quot;-&quot;
     * &lt;variant_and_sequence&gt; &quot;-&quot;
     * &lt;node&gt;
     * time_low               = 4*&lt;hexOctet&gt;
     * time_mid               = 2*&lt;hexOctet&gt;
     * time_high_and_version  = 2*&lt;hexOctet&gt;
     * variant_and_sequence   = 2*&lt;hexOctet&gt;
     * node                   = 6*&lt;hexOctet&gt;
     * hexOctet               = &lt;hexDigit&gt;&lt;hexDigit&gt;
     * hexDigit               =
     * &quot;0&quot; | &quot;1&quot; | &quot;2&quot; | &quot;3&quot; | &quot;4&quot; | &quot;5&quot; | &quot;6&quot; | &quot;7&quot; | &quot;8&quot; | &quot;9&quot;
     * | &quot;a&quot; | &quot;b&quot; | &quot;c&quot; | &quot;d&quot; | &quot;e&quot; | &quot;f&quot;
     * | &quot;A&quot; | &quot;B&quot; | &quot;C&quot; | &quot;D&quot; | &quot;E&quot; | &quot;F&quot;
    </pre> *
     *
     * @return a string representation of this <tt>UUID</tt>.
     */
    override fun toString(): String {
        return ((digits(this.mostSignificantBits shr 32, 8) + "-"
                + digits(this.mostSignificantBits shr 16, 4) + "-" + digits(
            this.mostSignificantBits, 4
        )
                + "-" + digits(this.leastSignificantBits shr 48, 4) + "-" + digits(
            this.leastSignificantBits, 12
        )))
    }

    /**
     * Returns a hash code for this `UUID`.
     *
     * @return a hash code value for this <tt>UUID</tt>.
     */
    override fun hashCode(): Int {
        if (hashCode == -1) {
            hashCode = (((this.mostSignificantBits shr 32) xor this.mostSignificantBits
                    xor (this.leastSignificantBits shr 32) xor this.leastSignificantBits)).toInt()
        }
        return hashCode
    }

    /**
     * Compares this object to the specified object. The result is <tt>true</tt>
     * if and only if the argument is not <tt>null</tt>, is a <tt>UUID</tt>
     * object, has the same variant, and contains the same value, bit for bit,
     * as this <tt>UUID</tt>.
     *
     * @param obj
     * the object to compare with.
     * @return `true` if the objects are the same;
     * `false` otherwise.
     */
    override fun equals(obj: Any?): Boolean {
        // 보안 취약점 점검 지적사항 반영 시작
        if (obj == null) return false
        // 보안 취약점 점검 지적사항 반영 시작 끝
        if (obj !is EgovFormBasedUUID) return false
        if (obj.variant() != this.variant()) return false
        val id = obj
        return (this.mostSignificantBits == id.mostSignificantBits && this.leastSignificantBits == id.leastSignificantBits)
    }

    // Comparison Operations
    /**
     * Compares this UUID with the specified UUID.
     *
     *
     *
     * The first of two UUIDs follows the second if the most significant field
     * in which the UUIDs differ is greater for the first UUID.
     *
     * @param val
     * <tt>UUID</tt> to which this <tt>UUID</tt> is to be
     * compared.
     * @return -1, 0 or 1 as this <tt>UUID</tt> is less than, equal to, or
     * greater than <tt>val</tt>.
     */
    fun compareTo(`val`: EgovFormBasedUUID): Int {
        // The ordering is intentionally set up so that the UUIDs
        // can simply be numerically compared as two numbers
        return (if (this.mostSignificantBits < `val`.mostSignificantBits)
            -1
        else
            (if (this.mostSignificantBits > `val`.mostSignificantBits)
                1
            else
                (if (this.leastSignificantBits < `val`.leastSignificantBits)
                    -1
                else
                    (if (this.leastSignificantBits > `val`.leastSignificantBits) 1 else 0))))
    }

    /**
     * Reconstitute the <tt>UUID</tt> instance from a stream (that is,
     * deserialize it). This is necessary to set the transient fields to their
     * correct uninitialized value so they will be recomputed on demand.
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: ObjectInputStream) {
        `in`.defaultReadObject()

        // Set "cached computation" fields to their initial values
        version = -1
        variant = -1
        timestamp = -1
        sequence = -1
        node = -1
        hashCode = -1
    }

    companion object {
        /*
     * The random number generator used by this class to create random based
     * UUIDs.
     */
        @Volatile
        private var numberGenerator: SecureRandom? = null

        /**
         * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
         *
         * The `UUID` is generated using a cryptographically strong
         * pseudo random number generator.
         *
         * @return a randomly generated <tt>UUID</tt>.
         */
        fun randomUUID(): EgovFormBasedUUID {
            var ng: SecureRandom? = numberGenerator
            if (ng == null) {
                ng = SecureRandom()
                numberGenerator = ng
            }

            val randomBytes = ByteArray(16)
            ng.nextBytes(randomBytes)
            randomBytes[6] = randomBytes[6].toInt() and 0x0f /* clear version */
            randomBytes[6] = randomBytes[6].toInt() or 0x40 /* set to version 4 */
            randomBytes[8] = randomBytes[8].toInt() and 0x3f /* clear variant */
            randomBytes[8] = randomBytes[8].toInt() or 0x80 /* set to IETF variant */

            return EgovFormBasedUUID(randomBytes)
        }

        /**
         * Static factory to retrieve a type 3 (name based) <tt>UUID</tt> based on
         * the specified byte array.
         *
         * @param name
         * a byte array to be used to construct a <tt>UUID</tt>.
         * @return a <tt>UUID</tt> generated from the specified array.
         */
        fun nameUUIDFromBytes(name: ByteArray?): EgovFormBasedUUID {
            val md: MessageDigest
            try {
                // 2011.10.10 보안점검 후속조치 암호화 알고리즘 변경(MD5 -> SHA-256)
                //md = MessageDigest.getInstance("MD5");
                md = MessageDigest.getInstance("SHA-256")
            } catch (nsae: NoSuchAlgorithmException) {
                //throw new InternalError("MD5 not supported");
                throw InternalError("SHA-256 not supported")
            }
            // 2011.10.10 보안점검 후속조치
            if (md == null) {
                throw RuntimeException("MessageDigest is null!!")
            }
            // 2014.09.20 보안점검 후속 조치
            // Random 방식의 salt 추가
            val ng = SecureRandom()
            val randomBytes = ByteArray(16)
            ng.nextBytes(randomBytes)

            md.reset()
            md.update(randomBytes)
            val sha = md.digest(name)


            val md5Bytes = ByteArray(8)
            System.arraycopy(sha, 0, md5Bytes, 0, 8)

            //2011.10.10 보안점검 후속조치 끝
            md5Bytes[6] = md5Bytes[6].toInt() and 0x0f /* clear version */
            md5Bytes[6] = md5Bytes[6].toInt() or 0x30 /* set to version 3 */
            md5Bytes[8] = md5Bytes[8].toInt() and 0x3f /* clear variant */
            md5Bytes[8] = md5Bytes[8].toInt() or 0x80 /* set to IETF variant */

            return EgovFormBasedUUID(md5Bytes)
        }

        /**
         * Creates a <tt>UUID</tt> from the string standard representation as
         * described in the [.toString] method.
         *
         * @param name
         * a string that specifies a <tt>UUID</tt>.
         * @return a <tt>UUID</tt> with the specified value.
         * @throws IllegalArgumentException
         * if name does not conform to the string representation as
         * described in [.toString].
         */
        fun fromString(name: String): EgovFormBasedUUID {
            val components: Array<String?> = name.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            require(components.size == 5) { "Invalid UUID string: " + name }
            for (i in 0..4) components[i] = "0x" + components[i]

            var mostSigBits = java.lang.Long.decode(components[0])
            mostSigBits = mostSigBits shl 16
            mostSigBits = mostSigBits or java.lang.Long.decode(components[1])
            mostSigBits = mostSigBits shl 16
            mostSigBits = mostSigBits or java.lang.Long.decode(components[2])

            var leastSigBits = java.lang.Long.decode(components[3])
            leastSigBits = leastSigBits shl 48
            leastSigBits = leastSigBits or java.lang.Long.decode(components[4])

            return EgovFormBasedUUID(mostSigBits, leastSigBits)
        }

        /** Returns val represented by the specified number of hex digits.  */
        private fun digits(`val`: Long, digits: Int): String {
            val hi = 1L shl (digits * 4)
            return java.lang.Long.toHexString(hi or (`val` and (hi - 1))).substring(1)
        }
    }
}