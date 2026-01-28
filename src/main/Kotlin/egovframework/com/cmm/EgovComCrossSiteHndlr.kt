package egovframework.com.cmm

import java.io.IOException
import java.io.Reader
import javax.servlet.jsp.JspException
import javax.servlet.jsp.JspWriter
import javax.servlet.jsp.PageContext
import javax.servlet.jsp.tagext.BodyTagSupport

/**
 * Cross-Site Scripting 체크하여 값을 되돌려 받는 핸들러 JSP TLD, 자바에서 사용가능
 *
 * @author 공통서비스 장동한
 * @since 2010.11.09
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력
</pre> */
class EgovComCrossSiteHndlr : BodyTagSupport() {
    protected var value: Any? = null // tag attribute
    protected var def: String? = null // tag attribute
    protected var escapeXml: Boolean = false // tag attribute
    private var needBody = false // non-space body needed?

    // *********************************************************************
    // Construction and initialization
    private val m_sDiffChar = "()[]{}\"',:;= \t\r\n%!+-"

    //private String m_sDiffChar ="()[]{}\"',:;=%!+-";
    private val m_sArrDiffChar: Array<String>? = arrayOf<String>(
        "&#40;", "&#41;",
        "&#91;", "&#93;",
        "&#123;", "&#125;",
        "&#34;", "&#39;",
        "&#44;", "&#58;",
        "&#59;", "&#61;",
        " ", "\t",  //" ","\t",
        "\r", "\n",  //"\r","\n",
        "&#37;", "&#33;",
        "&#43;", "&#45;"
    )

    /**
     * Constructs a new handler. As with TagSupport, subclasses should not
     * provide other constructors and are expected to call the superclass
     * constructor.
     */
    init {
        init()
    }

    private fun init() {
        def = null
        value = def
        escapeXml = true
        needBody = false
    }

    override fun release() {
        super.release()
        init()
    }

    @Throws(JspException::class)
    override fun doStartTag(): Int {
        needBody = false
        this.bodyContent = null

        val out = pageContext.getOut()
        try {
            if (value != null) {
                val sWriteEscapedXml = this.writeEscapedXml
                out.print(sWriteEscapedXml)
                return SKIP_BODY
            } else {
                if (def == null) {
                    needBody = true
                    return EVAL_BODY_BUFFERED
                }
                if (def != null) {
                    Companion.out(pageContext, escapeXml, def!!)
                }
                return SKIP_BODY
            }
        } catch (ex: IOException) {
            throw JspException(ex.toString(), ex)
        }
    }

    @Throws(JspException::class)
    override fun doEndTag(): Int {
        try {
            if (!needBody) {
                return EVAL_PAGE
            }

            if (bodyContent != null && bodyContent.getString() != null) {
                out(pageContext, escapeXml, bodyContent.getString().trim { it <= ' ' })
            }
            return EVAL_PAGE
        } catch (ex: IOException) {
            throw JspException(ex.toString(), ex)
        }
    }

    @get:Throws(IOException::class)
    @get:Suppress("unused")
    private val writeEscapedXml: String
        /**
         *
         * Optimized to create no extra objects and write directly to the JspWriter
         * using blocks of escaped and unescaped characters
         *
         */
        get() {
            var sRtn = ""

            val obj = this.value

            var start = 0
            val text = obj.toString()

            val length = text.length
            val buffer = text.toCharArray()
            var booleanDiff = false
            val cDiffChar = this.m_sDiffChar.toCharArray()

            for (i in 0..<length) {
                val c = buffer[i]

                booleanDiff = false

                for (k in cDiffChar.indices) {
                    if (c == cDiffChar[k]) {
                        sRtn = sRtn + m_sArrDiffChar!![k]
                        booleanDiff = true
                        continue
                    }
                }

                if (booleanDiff) continue

                if (c.code <= HIGHEST_SPECIAL) {
                    val escaped: CharArray? = specialCharactersRepresentation[c.code]
                    if (escaped != null) {
                        for (j in escaped.indices) {
                            sRtn = sRtn + escaped[j]
                        }
                        start = i + 1
                    } else {
                        sRtn = sRtn + c
                    }
                } else {
                    sRtn = sRtn + c
                }
            }

            return sRtn
        }

    /**
     *
     * Optimized to create no extra objects and write directly to the JspWriter
     * using blocks of escaped and unescaped characters
     *
     */
    @Suppress("unused")
    @Throws(IOException::class)
    private fun getWriteEscapedXml(sWriteString: String): String {
        var sRtn = ""

        val obj: Any = sWriteString

        var start = 0
        val text = obj.toString()

        val length = text.length
        val buffer = text.toCharArray()
        var booleanDiff = false
        val cDiffChar = this.m_sDiffChar.toCharArray()

        for (i in 0..<length) {
            val c = buffer[i]

            booleanDiff = false

            for (k in cDiffChar.indices) {
                if (c == cDiffChar[k]) {
                    sRtn = sRtn + m_sArrDiffChar!![k]
                    booleanDiff = true
                    continue
                }
            }

            if (booleanDiff) continue

            if (c.code <= HIGHEST_SPECIAL) {
                val escaped: CharArray? = specialCharactersRepresentation[c.code]
                if (escaped != null) {
                    for (j in escaped.indices) {
                        sRtn = sRtn + escaped[j]
                    }
                    start = i + 1
                } else {
                    sRtn = sRtn + c
                }
            } else {
                sRtn = sRtn + c
            }
        }

        return sRtn
    }

    fun setValue(value: Any?) {
        this.value = value
    }

    fun setDefault(def: String?) {
        this.def = def
    }

    fun setEscapeXml(escapeXml: Boolean) {
        this.escapeXml = escapeXml
    }

    companion object {
        val HIGHEST_SPECIAL: Int = '>'.code
        var specialCharactersRepresentation: Array<CharArray?> = arrayOfNulls<CharArray>(HIGHEST_SPECIAL + 1)

        init {
            specialCharactersRepresentation['&'.code] = "&amp;".toCharArray()
            specialCharactersRepresentation['<'.code] = "&lt;".toCharArray()
            specialCharactersRepresentation['>'.code] = "&gt;".toCharArray()
            specialCharactersRepresentation['"'.code] = "&#034;".toCharArray()
            specialCharactersRepresentation['\''.code] = "&#039;".toCharArray()
        }

        /*
	 * (One almost wishes XML and JSP could support "anonymous tags," given the
	 * amount of trouble we had naming this one!) :-) - sb
	 */
        // *********************************************************************
        // Internal state
        private val serialVersionUID = -6750233818675360686L

        /**
         * Outputs <tt>text</tt> to <tt>pageContext</tt>'s current JspWriter. If
         * <tt>escapeXml</tt> is true, performs the following substring replacements
         * (to facilitate output to XML/HTML pages):
         *
         * & -> &amp; < -> &lt; > -> &gt; " -> &#034; ' -> &#039;
         *
         * See also Util.escapeXml().
         */
        @Throws(IOException::class)
        fun out(
            pageContext: PageContext, escapeXml: Boolean,
            obj: Any
        ) {
            val w = pageContext.getOut()

            if (!escapeXml) {
                // write chars as is
                if (obj is Reader) {
                    val reader = obj
                    val buf = CharArray(4096)
                    var count: Int
                    while ((reader.read(buf, 0, 4096).also { count = it }) != -1) {
                        w.write(buf, 0, count)
                    }
                } else {
                    w.write(obj.toString())
                }
            } else {
                if (obj is Reader) {
                    val reader = obj
                    val buf = CharArray(4096)
                    var count: Int
                    while ((reader.read(buf, 0, 4096).also { count = it }) != -1) {
                        writeEscapedXml(buf, count, w)
                    }
                } else {
                    val text = obj.toString()
                    writeEscapedXml(text.toCharArray(), text.length, w)
                }
            }
        }

        @Throws(IOException::class)
        fun out2(
            pageContext: PageContext, escapeXml: Boolean,
            obj: Any
        ) {
            val w = pageContext.getOut()

            w.write(obj.toString())
        }

        /**
         *
         * Optimized to create no extra objects and write directly to the JspWriter
         * using blocks of escaped and unescaped characters
         *
         */
        @Throws(IOException::class)
        private fun writeEscapedXml(buffer: CharArray, length: Int, w: JspWriter) {
            var start = 0

            for (i in 0..<length) {
                val c = buffer[i]
                if (c.code <= HIGHEST_SPECIAL) {
                    val escaped: CharArray? = specialCharactersRepresentation[c.code]
                    if (escaped != null) {
                        if (start < i) {
                            w.write(buffer, start, i - start)
                        }
                        w.write(escaped)
                        start = i + 1
                    }
                }
            }
            if (start < length) {
                w.write(buffer, start, length - start)
            }
        }
    }
}


