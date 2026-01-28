package egovframework.com.cmm.web

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
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovFileMngController {
    @Autowired
    fun setEgovCryptoService(cryptoService: EgovCryptoService) {
        Companion.cryptoService = cryptoService
    }

    @Autowired(required = false)
    private var fileService: EgovFileMngService? = null

    @Autowired
    private val applicationContext: ApplicationContext? = null

    private fun resolveFileService(): EgovFileMngService? {
        if (fileService == null) {
            try {
                fileService = applicationContext!!.getBean<EgovFileMngService?>(EgovFileMngService::class.java)
            } catch (ignore: Exception) {
            }
        }
        return fileService
    }

    /**
     * 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfs.do")
    @Throws(Exception::class)
    fun selectFileInfs(
        @ModelAttribute("searchVO") fileVO: FileVO,
        request: HttpServletRequest,
        @RequestParam commandMap: MutableMap<String?, Any?>, model: ModelMap
    ): String {
        val param_atchFileId = commandMap.get("param_atchFileId") as String?
        val encrypted_atchFileId = Base64.getDecoder().decode(param_atchFileId)
        var decodedAtchFileId = ""
        if (param_atchFileId != null && "" != param_atchFileId) {
            decodedAtchFileId = String(cryptoService!!.decrypt(encrypted_atchFileId, ALGORITHM_KEY))
        }

        fileVO.atchFileId = decodedAtchFileId
        val result: MutableList<FileVO>? = resolveFileService()!!.selectFileInfs(fileVO)

        // FileId를 유추하지 못하도록 세션ID와 함께 암호화하여 표시한다. (2022.12.06 추가) - 파일아이디가 유추 불가능하도록 조치
        for (file in result!!) {
            val sessionId = request.getSession().getId()
            val toEncrypt = sessionId + "|" + file.atchFileId
            file.atchFileId = Base64.getEncoder().encodeToString(
                cryptoService!!.encrypt(toEncrypt.toByteArray(), ALGORITHM_KEY)
            )
        }

        model.addAttribute("fileList", result)
        model.addAttribute("updateFlag", "N")
        model.addAttribute("fileListCnt", result.size)
        model.addAttribute("atchFileId", param_atchFileId)

        return "cmm/fms/EgovFileList"
    }

    /**
     * 첨부파일 변경을 위한 수정페이지로 이동한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfsForUpdate.do")
    @Throws(Exception::class)
    fun selectFileInfsForUpdate(
        @ModelAttribute("searchVO") fileVO: FileVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        request: HttpServletRequest,
        model: ModelMap
    ): String {
        val param_atchFileId = commandMap.get("param_atchFileId") as String?
        val encrypted_atchFileId = Base64.getDecoder().decode(param_atchFileId)
        var decodedAtchFileId = ""
        if (param_atchFileId != null && "" != param_atchFileId) {
            decodedAtchFileId = String(cryptoService!!.decrypt(encrypted_atchFileId, ALGORITHM_KEY))
        }

        fileVO.atchFileId = decodedAtchFileId

        val result: MutableList<FileVO>? = resolveFileService()!!.selectFileInfs(fileVO)

        // FileId를 유추하지 못하도록 세션ID와 함께 암호화하여 표시한다. (2022.12.06 추가) - 파일아이디가 유추 불가능하도록 조치
        for (file in result!!) {
            val sessionId = request.getSession().getId()
            val toEncrypt = sessionId + "|" + file.atchFileId
            file.atchFileId = Base64.getEncoder().encodeToString(
                cryptoService!!.encrypt(toEncrypt.toByteArray(), ALGORITHM_KEY)
            )
        }

        model.addAttribute("fileList", result)
        model.addAttribute("updateFlag", "Y")
        model.addAttribute("fileListCnt", result.size)
        model.addAttribute("atchFileId", param_atchFileId)

        return "cmm/fms/EgovFileList"
    }

    /**
     * 첨부파일에 대한 삭제를 처리한다.
     *
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/deleteFileInfs.do")
    @Throws(Exception::class)
    fun deleteFileInf(
        @ModelAttribute("searchVO") fileVO: FileVO?,
        @RequestParam("returnUrl") returnUrl: String,
        request: HttpServletRequest,
        model: ModelMap?
    ): String {
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            fileService!!.deleteFileInf(fileVO)
        }

        //--------------------------------------------
        // contextRoot가 있는 경우 제외 시켜야 함
        //--------------------------------------------
        /**/return "forward:/cmm/fms/selectFileInfs.do"; */
        //return "forward:" + returnUrl;
        if ("" == request.getContextPath() || "/" == request.getContextPath()) {
            return "forward:" + returnUrl
        }

        if (returnUrl.startsWith(request.getContextPath())) {
            return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1))
        } else {
            return "forward:" + returnUrl
        }
        /**/------------------------------------------* /
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectImageFileInfs.do")
    @Throws(Exception::class)
    fun selectImageFileInfs(
        @ModelAttribute("searchVO") fileVO: FileVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        request: HttpServletRequest,
        model: ModelMap
    ): String {
        val param_atchFileId = commandMap.get("atchFileId") as String?
        var decodedAtchFileId = ""
        if (param_atchFileId != null && "" != param_atchFileId) {
            val encrypted_atchFileId = Base64.getDecoder().decode(param_atchFileId)
            decodedAtchFileId = String(cryptoService!!.decrypt(encrypted_atchFileId, ALGORITHM_KEY))
            decodedAtchFileId = StringUtils.substringAfter(decodedAtchFileId, "|")
        }

        fileVO.atchFileId = decodedAtchFileId
        val result: MutableList<FileVO>? = fileService!!.selectImageFileList(fileVO)

        // FileId를 유추하지 못하도록 세션ID와 함께 암호화하여 표시한다. (2022.12.06 추가) - 파일아이디가 유추 불가능하도록 조치
        for (file in result!!) {
            val sessionId = request.getSession().getId()
            val toEncrypt = sessionId + "|" + file.atchFileId
            file.atchFileId =
                Base64.getEncoder().encodeToString(cryptoService!!.encrypt(toEncrypt.toByteArray(), ALGORITHM_KEY))
        }

        model.addAttribute("fileList", result)

        return "cmm/fms/EgovImgFileList"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovFileMngController::class.java)

        /** 암호화서비스  */
        private var cryptoService: EgovCryptoService? = null

        // 주의 : 반드시 기본값 "egovframe"을 다른것으로 변경하여 사용하시기 바랍니다.
        val ALGORITHM_KEY: String = getProperty("Globals.File.algorithmKey")

        /**
         * 원본 문자열을 암호화 하는 메서드.
         *
         * @param source 원본 문자열
         * @return 암호화 문자열
         */
        fun encrypt(atchFileId: String): String? {
            var returnVal: String? = "-"
            returnVal =
                Base64.getEncoder().encodeToString(cryptoService!!.encrypt(atchFileId.toByteArray(), ALGORITHM_KEY))
            return returnVal
        }

        /**
         * 원본 문자열을 암호화 하는 메서드.
         *
         * @param source 원본 문자열
         * @return 암호화 문자열
         */
        fun encryptSession(atchFileId: String?, sessionId: String?): String? {
            var returnVal: String? = "-"
            val toEncrypt = sessionId + "|" + atchFileId
            returnVal =
                Base64.getEncoder().encodeToString(cryptoService!!.encrypt(toEncrypt.toByteArray(), ALGORITHM_KEY))
            return returnVal
        }

        /**
         * 암호화 문자열을 복호화 하는 메서드.
         * @param source 암호화 문자열
         * @return 원본 문자열
         */
        fun decrypt(base64AtchFileId: String?): String {
            var returnVal = "FILE_ID_DECRIPT_EXCEPTION_02"
            if (base64AtchFileId != null && "" != base64AtchFileId) {
                try {
                    val encrypted_atchFileId = Base64.getDecoder().decode(base64AtchFileId)
                    returnVal = String(cryptoService!!.decrypt(encrypted_atchFileId, ALGORITHM_KEY))
                } catch (e: Exception) {
                    LOGGER.debug(e.message)
                }
            }
            return returnVal
        }
    }
}
