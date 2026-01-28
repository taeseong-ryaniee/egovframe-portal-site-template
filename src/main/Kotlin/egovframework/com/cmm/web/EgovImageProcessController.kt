package egovframework.com.cmm.web

import egovframework.com.cmm.EgovWebUtil.filePathBlackList
import egovframework.com.cmm.SessionVO
import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.EgovProperties.getProperty
import egovframework.com.cmm.service.FileVO
import org.apache.commons.lang3.StringUtils
import org.egovframe.rte.fdl.cryptography.EgovCryptoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.io.*
import java.util.*
import javax.annotation.Resource
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletResponse

/**
 * @Class Name : EgovImageProcessController.java
 * @Description :
 * @Modification Information
 *
 * 수정일       수정자         수정내용
 * -------        -------     -------------------
 * 2009. 4. 2.     이삼섭
 * 2011.08.31.     JJY        경량환경 템플릿 커스터마이징버전 생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 */
@Controller
class EgovImageProcessController : HttpServlet() {
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

    /** 암호화서비스  */
    @Resource(name = "egovARIACryptoService")
    var cryptoService: EgovCryptoService? = null

    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/getImage.do")
    @Throws(Exception::class)
    fun getImageInf(
        sessionVO: SessionVO?,
        model: ModelMap?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        response: HttpServletResponse
    ) {
        var param_atchFileId = commandMap.get("atchFileId") as String
        param_atchFileId = param_atchFileId.replace(" ".toRegex(), "+")
        val decodedBytes = Base64.getDecoder().decode(param_atchFileId)
        val decodedString = String(cryptoService!!.decrypt(decodedBytes, ALGORITHM_KEY))
        val decodedFileId = StringUtils.substringAfter(decodedString, "|")

        val fileSn = commandMap.get("fileSn") as String?

        val vo = FileVO()

        vo.atchFileId = decodedFileId
        vo.fileSn = fileSn

        val fvo = fileService!!.selectFileInf(vo)

        val fileStreCours = filePathBlackList(fvo!!.fileStreCours)
        val streFileNm = filePathBlackList(fvo.streFileNm)

        val file = File(fileStreCours, streFileNm)
        var fis: FileInputStream? = null

        //		new FileInputStream(file);
        var `in`: BufferedInputStream? = null
        var bStream: ByteArrayOutputStream? = null
        try {
            fis = FileInputStream(file)
            `in` = BufferedInputStream(fis)
            bStream = ByteArrayOutputStream()
            var imgByte: Int
            while ((`in`.read().also { imgByte = it }) != -1) {
                bStream.write(imgByte)
            }

            var type = ""

            if (fvo.fileExtsn != null && "" != fvo.fileExtsn) {
                if ("jpg" == fvo.fileExtsn!!.lowercase(Locale.getDefault())) {
                    type = "image/jpeg"
                } else {
                    type = "image/" + fvo.fileExtsn!!.lowercase(Locale.getDefault())
                }
                type = "image/" + fvo.fileExtsn!!.lowercase(Locale.getDefault())
            } else {
                LOGGER.debug("Image fileType is null.")
            }

            response.setHeader("Content-Type", type)
            response.setContentLength(bStream.size())
            bStream.writeTo(response.getOutputStream())
            response.getOutputStream().flush()
            response.getOutputStream().close()
        } catch (e: IOException) {
            LOGGER.debug("{}", e)
        } finally {
            if (bStream != null) {
                try {
                    bStream.close()
                } catch (est: IOException) {
                    LOGGER.debug("IGNORED: {}", est.message)
                }
            }
            if (`in` != null) {
                try {
                    `in`.close()
                } catch (ei: IOException) {
                    LOGGER.debug("IGNORED: {}", ei.message)
                }
            }
            if (fis != null) {
                try {
                    fis.close()
                } catch (efis: IOException) {
                    LOGGER.debug("IGNORED: {}", efis.message)
                }
            }
        }
    }

    companion object {
        /**
         * serialVersion UID
         */
        private val serialVersionUID = -6339945210971171173L

        private val LOGGER: Logger = LoggerFactory.getLogger(EgovImageProcessController::class.java)

        // 주의 : 반드시 기본값 "egovframe"을 다른것으로 변경하여 사용하시기 바랍니다.
        val ALGORITHM_KEY: String = getProperty("Globals.File.algorithmKey")
    }
}
