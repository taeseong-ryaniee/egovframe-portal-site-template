package egovframework.com.cmm

import java.io.File
import java.util.regex.Pattern

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일         수정자       수정내용
 * ----------   --------   ---------------------------
 * 2011.10.10   한성곤       최초 생성
 * 2017-02-07   이정은       시큐어코딩(ES) - 시큐어코딩 경로 조작 및 자원 삽입[CWE-22, CWE-23, CWE-95, CWE-99]
 * 2018.08.17   신용호       filePathBlackList 수정
 * 2018.10.10   신용호       . => \\.으로 수정
 * 2024.12.04   신용호       filePathBlackList() basePath 파라미터 추가
</pre> *
 */
object EgovWebUtil {
    fun clearXSSMinimum(value: String?): String {
        if (value == null || value.trim { it <= ' ' } == "") {
            return ""
        }

        var returnValue: String? = value

        returnValue = returnValue!!.replace("&".toRegex(), "&amp;")
        returnValue = returnValue.replace("<".toRegex(), "&lt;")
        returnValue = returnValue.replace(">".toRegex(), "&gt;")
        returnValue = returnValue.replace("\"".toRegex(), "&#34;")
        returnValue = returnValue.replace("\'".toRegex(), "&#39;")
        returnValue = returnValue.replace("\\.".toRegex(), "&#46;")
        returnValue = returnValue.replace("%2E".toRegex(), "&#46;")
        returnValue = returnValue.replace("%2F".toRegex(), "&#47;")
        return returnValue
    }

    fun clearXSSMaximum(value: String): String {
        var returnValue = value
        returnValue = clearXSSMinimum(returnValue)

        returnValue = returnValue.replace("%00".toRegex(), null)

        returnValue = returnValue.replace("%".toRegex(), "&#37;")

        // \\. => .
        returnValue = returnValue.replace("\\.\\./".toRegex(), "") // ../
        returnValue = returnValue.replace("\\.\\.\\\\".toRegex(), "") // ..\
        returnValue = returnValue.replace("\\./".toRegex(), "") // ./
        returnValue = returnValue.replace("%2F".toRegex(), "")

        return returnValue
    }

    @JvmStatic
    fun filePathBlackList(value: String?): String {
        var returnValue = value
        if (returnValue == null || returnValue.trim { it <= ' ' } == "") {
            return ""
        }

        returnValue = returnValue.replace("\\.\\.".toRegex(), "")

        return returnValue
    }

    /**
     * 파일경로 보안취약점 조치
     * # 주의사항
     * 1. basePath는 반드시 지정해야 한다.
     * 2. basePath는 ROOT Path "/" 사용 금지 한다.
     * 3. basePath 하위 디렉토리는 업로드한 파일이 존재하도록 구성하며 중요파일이 존재하지 않도록 관리한다.
     *
     * @param value 파일명
     * @param basePath 기본 경로
     * @return
     */
    fun filePathBlackList(value: String?, basePath: String): String {
        if (basePath == null || "" == basePath) throw SecurityException("base path is empty.")
        if (File.separator == basePath || "/" == basePath) throw SecurityException("base path does not allow Root.")
        return filePathBlackList(basePath + value)
    }

    /**
     * 행안부 보안취약점 점검 조치 방안.
     *
     * @param value
     * @return
     */
    fun filePathReplaceAll(value: String?): String {
        var returnValue = value
        if (returnValue == null || returnValue.trim { it <= ' ' } == "") {
            return ""
        }

        returnValue = returnValue.replace("/".toRegex(), "")
        returnValue = returnValue.replace("\\\\".toRegex(), "")
        returnValue = returnValue.replace("\\.\\.".toRegex(), "") // ..
        returnValue = returnValue.replace("&".toRegex(), "")

        return returnValue
    }

    fun fileInjectPathReplaceAll(value: String?): String {
        var returnValue = value
        if (returnValue == null || returnValue.trim { it <= ' ' } == "") {
            return ""
        }


        returnValue = returnValue.replace("/".toRegex(), "")
        returnValue = returnValue.replace("\\..".toRegex(), "") // ..
        returnValue = returnValue.replace("\\\\".toRegex(), "") // \
        returnValue = returnValue.replace("&".toRegex(), "")

        return returnValue
    }

    fun filePathWhiteList(value: String?): String? {
        return value
    }

    fun isIPAddress(str: String): Boolean {
        val ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")

        return ipPattern.matcher(str).matches()
    }

    @JvmStatic
    fun removeCRLF(parameter: String): String {
        return parameter.replace("\r".toRegex(), "").replace("\n".toRegex(), "")
    }

    fun removeSQLInjectionRisk(parameter: String): String {
        return parameter.replace("\\p{Space}".toRegex(), "").replace("\\*".toRegex(), "").replace("%".toRegex(), "")
            .replace(";".toRegex(), "").replace("-".toRegex(), "").replace("\\+".toRegex(), "")
            .replace(",".toRegex(), "")
    }

    fun removeOSCmdRisk(parameter: String): String {
        return parameter.replace("\\p{Space}".toRegex(), "").replace("\\*".toRegex(), "").replace("\\|".toRegex(), "")
            .replace(";".toRegex(), "")
    }
}