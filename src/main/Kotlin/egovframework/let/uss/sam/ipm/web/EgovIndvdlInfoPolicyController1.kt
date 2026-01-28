package egovframework.let.uss.sam.ipm.web

import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.let.uss.sam.ipm.service.EgovIndvdlInfoPolicyService
import egovframework.let.uss.sam.ipm.service.IndvdlInfoPolicy
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource

/**
 * 개인정보보호정책를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력
</pre> */
@Controller
class EgovIndvdlInfoPolicyController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    /** egovOnlinePollService  */
    @Resource(name = "egovIndvdlInfoPolicyService")
    private val egovIndvdlInfoPolicyService: EgovIndvdlInfoPolicyService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /**
     * 개인정보보호정책 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param indvdlInfoPolicy
     * @param model
     * @return "/uss/sam/ipm/EgovOnlinePollList"
     * @throws Exception
     */
    @Suppress("unused")
    @RequestMapping(value = ["/uss/sam/ipm/listIndvdlInfoPolicy.do"])
    @Throws(Exception::class)
    fun EgovIndvdlInfoPolicyList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO, @RequestParam commandMap: MutableMap<String?, Any?>,
        indvdlInfoPolicy: IndvdlInfoPolicy?, model: ModelMap
    ): String {
        val sSearchMode = if (commandMap.get("searchMode") == null) "" else commandMap.get("searchMode") as String?

        /** EgovPropertyService.sample  */
        searchVO.pageUnit = propertiesService!!.getInt("pageUnit")
        searchVO.pageSize = propertiesService!!.getInt("pageSize")

        /** pageing  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(searchVO.pageIndex)
        paginationInfo.setRecordCountPerPage(searchVO.pageUnit)
        paginationInfo.setPageSize(searchVO.pageSize)

        searchVO.firstIndex = paginationInfo.getFirstRecordIndex()
        searchVO.lastIndex = paginationInfo.getLastRecordIndex()
        searchVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        model.addAttribute("resultList", egovIndvdlInfoPolicyService!!.selectIndvdlInfoPolicyList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovIndvdlInfoPolicyService.selectIndvdlInfoPolicyListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)
        return "/uss/sam/ipm/EgovIndvdlInfoPolicyList"
    }

    /**
     * 개인정보보호정책 목록을 상세조회 조회한다.
     * @param searchVO
     * @param indvdlInfoPolicy
     * @param commandMap
     * @param model
     * @return
     * "/uss/sam/ipm/EgovOnlinePollDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/sam/ipm/detailIndvdlInfoPolicy.do"])
    @Throws(Exception::class)
    fun EgovIndvdlInfoPolicyDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        indvdlInfoPolicy: IndvdlInfoPolicy?, @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/sam/ipm/EgovIndvdlInfoPolicyDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovIndvdlInfoPolicyService!!.deleteIndvdlInfoPolicy(indvdlInfoPolicy)
            sLocationUrl = "forward:/uss/sam/ipm/listIndvdlInfoPolicy.do"
        } else {
            val indvdlInfoPolicyVO = egovIndvdlInfoPolicyService!!.selectIndvdlInfoPolicyDetail(indvdlInfoPolicy)
            model.addAttribute("indvdlInfoPolicy", indvdlInfoPolicyVO)
        }

        return sLocationUrl
    }

    /**
     * 개인정보보호정책를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param indvdlInfoPolicy
     * @param bindingResult
     * @param model
     * @return
     * "/uss/sam/ipm/EgovOnlinePollUpdt"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/sam/ipm/updtIndvdlInfoPolicy.do"])
    @Throws(Exception::class)
    fun EgovIndvdlInfoPolicyModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("indvdlInfoPolicy") indvdlInfoPolicy: IndvdlInfoPolicy,
        bindingResult: BindingResult, model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인 객체 선언
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        var sLocationUrl = "/uss/sam/ipm/EgovIndvdlInfoPolicyUpdt"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "save") {
            //서버  validate 체크
            beanValidator!!.validate(indvdlInfoPolicy, bindingResult)
            if (bindingResult.hasErrors()) {
                return sLocationUrl
            }
            //아이디 설정
            indvdlInfoPolicy.setFrstRegisterId(loginVO.uniqId)
            indvdlInfoPolicy.setLastUpdusrId(loginVO.uniqId)

            egovIndvdlInfoPolicyService!!.updateIndvdlInfoPolicy(indvdlInfoPolicy)
            sLocationUrl = "forward:/uss/sam/ipm/listIndvdlInfoPolicy.do"
        } else {
            val indvdlInfoPolicyVO = egovIndvdlInfoPolicyService!!.selectIndvdlInfoPolicyDetail(indvdlInfoPolicy)
            model.addAttribute("indvdlInfoPolicy", indvdlInfoPolicyVO)
        }

        return sLocationUrl
    }

    /**
     * 개인정보보호정책를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param indvdlInfoPolicy
     * @param bindingResult
     * @param model
     * @return
     * "/uss/sam/ipm/EgovOnlinePollRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/sam/ipm/registIndvdlInfoPolicy.do"])
    @Throws(Exception::class)
    fun EgovIndvdlInfoPolicyRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("indvdlInfoPolicy") indvdlInfoPolicy: IndvdlInfoPolicy,
        bindingResult: BindingResult, model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인 객체 선언
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        var sLocationUrl = "/uss/sam/ipm/EgovIndvdlInfoPolicyRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "save") {
            //서버  validate 체크
            beanValidator!!.validate(indvdlInfoPolicy, bindingResult)
            if (bindingResult.hasErrors()) {
                return sLocationUrl
            }
            //아이디 설정
            indvdlInfoPolicy.setFrstRegisterId(loginVO.uniqId)
            indvdlInfoPolicy.setLastUpdusrId(loginVO.uniqId)
            //저장
            egovIndvdlInfoPolicyService!!.insertIndvdlInfoPolicy(indvdlInfoPolicy)
            sLocationUrl = "forward:/uss/sam/ipm/listIndvdlInfoPolicy.do"
        }

        return sLocationUrl
    }
}
