package egovframework.let.utl.sim.service

import org.apache.commons.codec.binary.Base64
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.*
import java.security.MessageDigest

/**
 * Base64인코딩/디코딩 방식을 이용한 데이터를 암호화/복호화하는 Business Interface class
 * @author 공통서비스개발팀 박지욱
 * @since 2009.01.19
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
object EgovFileScrty {
    // 파일구분자
    val FILE_SEPARATOR: Char = File.separatorChar

    // 버퍼사이즈
    const val BUFFER_SIZE: Int = 1024

    private val LOGGER: Logger = LoggerFactory.getLogger(EgovFileScrty::class.java)

    /**
     * 파일을 암호화하는 기능
     *
     * @param String source 암호화할 파일
     * @param String target 암호화된 파일
     * @return boolean result 암호화여부 True/False
     * @exception Exception
     */
    @Throws(Exception::class)
    fun encryptFile(source: String, target: String): Boolean {
        // 암호화 여부

        var result = false

        val sourceFile = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)
        val targetFile = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)
        val srcFile = File(sourceFile)

        var input: BufferedInputStream? = null
        var output: BufferedOutputStream? = null

        val buffer = ByteArray(BUFFER_SIZE)

        try {
            if (srcFile.exists() && srcFile.isFile()) {
                input = BufferedInputStream(FileInputStream(srcFile))
                output = BufferedOutputStream(FileOutputStream(targetFile))

                var length = 0
                while ((input.read(buffer).also { length = it }) >= 0) {
                    val data = ByteArray(length)
                    System.arraycopy(buffer, 0, data, 0, length)
                    output.write(encodeBinary(data).toByteArray())
                    output.write(System.getProperty("line.separator").toByteArray())
                }

                result = true
            }
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (ignore: IOException) {
                    LOGGER.debug("IGNORE: {}" + ignore)
                }
            }
            if (output != null) {
                try {
                    output.close()
                } catch (ignore: IOException) {
                    LOGGER.debug("IGNORE: {}" + ignore)
                }
            }
        }
        return result
    }

    /**
     * 파일을 복호화하는 기능
     *
     * @param String source 복호화할 파일
     * @param String target 복호화된 파일
     * @return boolean result 복호화여부 True/False
     * @exception Exception
     */
    @Throws(Exception::class)
    fun decryptFile(source: String, target: String): Boolean {
        // 복호화 여부

        var result = false

        val sourceFile = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)
        val targetFile = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)
        val srcFile = File(sourceFile)

        var input: BufferedReader? = null
        var output: BufferedOutputStream? = null

        //byte[] buffer = new byte[BUFFER_SIZE];
        var line: String? = null

        try {
            if (srcFile.exists() && srcFile.isFile()) {
                input = BufferedReader(InputStreamReader(FileInputStream(srcFile)))
                output = BufferedOutputStream(FileOutputStream(targetFile))

                while ((input.readLine().also { line = it }) != null) {
                    val data = line!!.toByteArray()
                    output.write(decodeBinary(String(data)))
                }

                result = true
            }
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (ignore: IOException) {
                    LOGGER.debug("IGNORE: {}" + ignore)
                }
            }
            if (output != null) {
                try {
                    output.close()
                } catch (ignore: IOException) {
                    LOGGER.debug("IGNORE: {}" + ignore)
                }
            }
        }
        return result
    }

    /**
     * 데이터를 암호화하는 기능
     *
     * @param byte[] data 암호화할 데이터
     * @return String result 암호화된 데이터
     * @exception Exception
     */
    @Throws(Exception::class)
    fun encodeBinary(data: ByteArray?): String {
        if (data == null) {
            return ""
        }

        return String(Base64.encodeBase64(data))
    }

    /**
     * 데이터를 암호화하는 기능
     *
     * @param String data 암호화할 데이터
     * @return String result 암호화된 데이터
     * @exception Exception
     */
    @Throws(Exception::class)
    fun encode(data: String): String {
        return encodeBinary(data.toByteArray())
    }

    /**
     * 데이터를 복호화하는 기능
     *
     * @param String data 복호화할 데이터
     * @return String result 복호화된 데이터
     * @exception Exception
     */
    @Throws(Exception::class)
    fun decodeBinary(data: String): ByteArray? {
        return Base64.decodeBase64(data.toByteArray())
    }

    /**
     * 데이터를 복호화하는 기능
     *
     * @param String data 복호화할 데이터
     * @return String result 복호화된 데이터
     * @exception Exception
     */
    @Throws(Exception::class)
    fun decode(data: String): String {
        return kotlin.text.String(decodeBinary(data)!!)
    }

    /**
     * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용).
     *
     * deprecated : 보안 강화를 위하여 salt로 ID를 지정하는 encryptPassword(password, id) 사용
     *
     * @param String data 암호화할 비밀번호
     * @return String result 암호화된 비밀번호
     * @exception Exception
     */
    @Deprecated("")
    @Throws(Exception::class)
    fun encryptPassword(data: String?): String {
        if (data == null) {
            return ""
        }

        var plainText: ByteArray? = null // 평문
        var hashValue: ByteArray? = null // 해쉬값
        plainText = data.toByteArray()

        val md = MessageDigest.getInstance("SHA-256")


        // 변경 시 기존 hash 값에 검증 불가.. => deprecated 시키고 유지
        /*	
	    // Random 방식의 salt 추가
	    SecureRandom ng = new SecureRandom();
	    byte[] randomBytes = new byte[16];
	    ng.nextBytes(randomBytes);
	    
	    md.reset();
	    md.update(randomBytes);
	    
		*/
        hashValue = md.digest(plainText)


        /*
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(hashValue);
		*/
        return String(Base64.encodeBase64(hashValue))
    }

    /**
     * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
     *
     * @param password 암호화될 패스워드
     * @param id salt로 사용될 사용자 ID 지정
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encryptPassword(password: String?, id: String): String {
        if (password == null) {
            return ""
        }

        var hashValue: ByteArray? = null // 해쉬값

        val md = MessageDigest.getInstance("SHA-256")

        md.reset()
        md.update(id.toByteArray())

        hashValue = md.digest(password.toByteArray())

        return String(Base64.encodeBase64(hashValue))
    }

    /**
     * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
     * @param data 암호화할 비밀번호
     * @param salt Salt
     * @return 암호화된 비밀번호
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encryptPassword(data: String?, salt: ByteArray?): String {
        if (data == null) {
            return ""
        }

        var hashValue: ByteArray? = null // 해쉬값

        val md = MessageDigest.getInstance("SHA-256")

        md.reset()
        md.update(salt)

        hashValue = md.digest(data.toByteArray())

        return String(Base64.encodeBase64(hashValue))
    }

    /**
     * 비밀번호를 암호화된 패스워드 검증(salt가 사용된 경우만 적용).
     *
     * @param data 원 패스워드
     * @param encoded 해쉬처리된 패스워드(Base64 인코딩)
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun checkPassword(data: String, encoded: String, salt: ByteArray?): Boolean {
        var hashValue: ByteArray? = null // 해쉬값

        val md = MessageDigest.getInstance("SHA-256")

        md.reset()
        md.update(salt)
        hashValue = md.digest(data.toByteArray())

        return MessageDigest.isEqual(hashValue, Base64.decodeBase64(encoded.toByteArray()))
    }
}