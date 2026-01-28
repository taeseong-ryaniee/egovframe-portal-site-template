package egovframework.com.cmm.web

import egovframework.com.cmm.EgovWebUtil.filePathBlackList
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.annotation.Resource
import javax.servlet.http.HttpSession

/**
 * 공통유틸리티성 작업을 위한 Controller 클래스
 * @author 공통 서비스 개발팀 JJY
 * @since 2009.03.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovComUtlController {
    /** EgovPropertyService  */
    @Autowired
    protected var propertiesService: EgovPropertyService? = null

    @Resource(name = "egovPageLinkWhitelist")
    protected var egovWhitelist: MutableList<String>? = null

    /**
     * JSP 호출작업만 처리하는 공통 함수
     */
    @RequestMapping(value = ["/EgovPageLink.do"])
    fun moveToPage(
        @RequestParam(value = "linkIndex", required = true, defaultValue = "0") linkIndex: Int,
        session: HttpSession, @RequestParam(value = "menuNo", required = false) menuNo: String?
    ): String {
        var link = ""


        // 화이트 리스트가 비었는지 확인
        if (egovWhitelist == null || egovWhitelist!!.isEmpty() || egovWhitelist!!.size <= linkIndex) {
            LOGGER.debug("Page Link WhiteList Error! Please check whitelist!")

            link = "cmm/error/egovError"

            return link
        }

        link = egovWhitelist!!.get(linkIndex)

        link = link.replace(";", "")
        link = link.replace("%", "")
        link = link.replace(".", "")

        if (link.indexOf(",") > -1) {
            link = link.substring(0, link.indexOf(","))
        }


        // 선택된 메뉴정보를 세션으로 등록한다.
        if (menuNo != null && menuNo != "") {
            session.setAttribute("menuNo", menuNo)
        }


        // 안전한 경로 문자열로 조치
        link = filePathBlackList(link)

        return link
    }

    /**
     * validation rule dynamic java script
     */
    @RequestMapping("/validator.do")
    fun validate(): String {
        return "cmm/validator"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovComUtlController::class.java)
    }
}
