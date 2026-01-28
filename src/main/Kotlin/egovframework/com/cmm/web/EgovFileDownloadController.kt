package egovframework.com.cmm.web

import egovframework.com.cmm.EgovWebUtil.filePathBlackList
import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.EgovProperties.getProperty
import egovframework.com.cmm.service.FileVO
import org.apache.commons.lang3.StringUtils
import org.egovframe.rte.fdl.cryptography.EgovCryptoService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.io.*
import java.net.URLEncoder
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovFileDownloadController {
    /** 암호화서비스  */
    @Autowired
    var cryptoService: EgovCryptoService? = null

    @Autowired(required = false)
    private var fileService: EgovFileMngService? = null

    @Autowired
    private val applicationContext: ApplicationContext? = null

    /**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return
     */
    private fun getBrowser(request: HttpServletRequest): String {
        val header = request.getHeader("User-Agent")
        if (header.indexOf("MSIE") > -1) {
            return "MSIE"
        } else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
            return "Trident"
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome"
        } else if (header.indexOf("Opera") > -1) {
            return "Opera"
        }
        return "Firefox"
    }

    /**
     * Disposition 지정하기.
     *
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun setDisposition(filename: String, request: HttpServletRequest, response: HttpServletResponse) {
        val browser = getBrowser(request)

        val dispositionPrefix = "attachment; filename="
        var encodedFilename: String? = null

        if (browser == "MSIE") {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replace("\\+".toRegex(), "%20")
        } else if (browser == "Trident") { // IE11 문자열 깨짐 방지
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replace("\\+".toRegex(), "%20")
        } else if (browser == "Firefox") {
            encodedFilename = "\"" + String(filename.toByteArray(charset("UTF-8")), charset("8859_1")) + "\""
        } else if (browser == "Opera") {
            encodedFilename = "\"" + String(filename.toByteArray(charset("UTF-8")), charset("8859_1")) + "\""
        } else if (browser == "Chrome") {
            val sb = StringBuffer()
            for (i in 0..<filename.length) {
                val c = filename.get(i)
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"))
                } else {
                    sb.append(c)
                }
            }
            encodedFilename = sb.toString()
        } else {
            throw IOException("Not supported browser")
        }

        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename)

        if ("Opera" == browser) {
            response.setContentType("application/octet-stream;charset=UTF-8")
        }
    }

    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
     *
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = ["/cmm/fms/FileDown.do"])
    @Throws(Exception::class)
    fun cvplFileDownload(
        @RequestParam commandMap: MutableMap<String?, Any?>,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        // Lazy-resolve fileService from parent context if necessary

        if (fileService == null) {
            try {
                fileService = applicationContext!!.getBean<EgovFileMngService?>(EgovFileMngService::class.java)
            } catch (ignore: Exception) {
            }
        }

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            // 암호화된 atchFileId 를 복호화. (2022.12.06 추가) - 파일아이디가 유추 불가능하도록 조치

            var param_atchFileId = commandMap.get("atchFileId") as String
            param_atchFileId = param_atchFileId.replace(" ".toRegex(), "+")
            val decodedBytes = Base64.getDecoder().decode(param_atchFileId)
            val decodedString = String(cryptoService!!.decrypt(decodedBytes, ALGORITHM_KEY))
            val decodedFileId = StringUtils.substringAfter(decodedString, "|")
            val fileSn = commandMap.get("fileSn") as String?

            val fileVO = FileVO()
            fileVO.atchFileId = decodedFileId
            fileVO.fileSn = fileSn
            val fvo = fileService!!.selectFileInf(fileVO)

            val fileStreCours = filePathBlackList(fvo!!.fileStreCours)
            val streFileNm = filePathBlackList(fvo.streFileNm)
            val uFile = File(fileStreCours, streFileNm)
            val fSize = uFile.length()

            if (fSize > 0) {
                val mimetype = "application/x-msdownload"

                response.setContentType(mimetype)
                setDisposition(fvo.orignlFileNm!!, request, response)

                //response.setContentLength(fSize);
                var `in`: BufferedInputStream? = null
                var out: BufferedOutputStream? = null

                try {
                    `in` = BufferedInputStream(FileInputStream(uFile))
                    out = BufferedOutputStream(response.getOutputStream())

                    FileCopyUtils.copy(`in`, out)
                    out.flush()
                } catch (ex: IOException) {
                    LOGGER.debug("IGNORED: {}", ex.message)
                } finally {
                    if (`in` != null) {
                        try {
                            `in`.close()
                        } catch (ignore: IOException) {
                            LOGGER.debug("IGNORED: {}", ignore.message)
                        }
                    }
                    if (out != null) {
                        try {
                            out.close()
                        } catch (ignore: IOException) {
                            LOGGER.debug("IGNORED: {}", ignore.message)
                        }
                    }
                }
            } else {
                request.getRequestDispatcher("/cmm/error/egovBizException.jsp").forward(request, response)
            }
        }
    }

    companion object {
        /** 로그설정  */
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovFileDownloadController::class.java)

        // 주의 : 반드시 기본값 "egovframe"을 다른것으로 변경하여 사용하시기 바랍니다.
        val ALGORITHM_KEY: String = getProperty("Globals.File.algorithmKey")
    }
}
