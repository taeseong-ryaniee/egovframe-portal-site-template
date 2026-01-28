package egovframework.let.uss.olp.qmc.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.uss.olp.qmc.service.EgovQustnrManageService
import egovframework.let.uss.olp.qmc.service.QustnrManageVO
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

/**
 * 설문관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQustnrManageController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovQustnrManageService")
    private val egovQustnrManageService: EgovQustnrManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /**
     * 설문관리 팝업 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrManageVO
     * @param model
     * @return "/uss/olp/qmc/EgovQustnrManageListPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qmc/EgovQustnrManageListPopup.do"])
    @Throws(Exception::class)
    fun EgovQustnrManageListPopup(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrManageVO: QustnrManageVO?,
        model: ModelMap
    ): String {
        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        if (sCmd == "del") {
            egovQustnrManageService!!.deleteQustnrManage(qustnrManageVO)
        }

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

        model.addAttribute("resultList", egovQustnrManageService!!.selectQustnrManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrManageService.selectQustnrManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qmc/EgovQustnrManageListPopup"
    }

    /**
     * 설문관리 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrManageVO
     * @param model
     * @return  "/uss/olp/qmc/EgovQustnrManageList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qmc/EgovQustnrManageList.do"])
    @Throws(Exception::class)
    fun EgovQustnrManageList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrManageVO: QustnrManageVO?,
        model: ModelMap,
        request: HttpServletRequest
    ): String {
        // 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가

        request.getSession().setAttribute("menuNo", "5000000")

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        if (sCmd == "del") {
            egovQustnrManageService!!.deleteQustnrManage(qustnrManageVO)
        }

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

        model.addAttribute("resultList", egovQustnrManageService!!.selectQustnrManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrManageService.selectQustnrManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qmc/EgovQustnrManageList"
    }

    /**
     * 설문관리 목록을 상세조회 조회한다.
     * @param searchVO
     * @param qustnrManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qmc/EgovQustnrManageDetail";
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qmc/EgovQustnrManageDetail.do"])
    @Throws(Exception::class)
    fun EgovQustnrManageDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrManageVO: QustnrManageVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/olp/qmc/EgovQustnrManageDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrManageService!!.deleteQustnrManage(qustnrManageVO)
            sLocationUrl = "redirect:/uss/olp/qmc/EgovQustnrManageList.do"
        } else {
            //공통코드  직업유형 조회

            val voComCode = ComDefaultCodeVO()
            voComCode.codeId = "COM034"
            model.addAttribute("comCode034", cmmUseService!!.selectCmmCodeDetail(voComCode))

            model.addAttribute("resultList", egovQustnrManageService!!.selectQustnrManageDetail(qustnrManageVO))
        }

        return sLocationUrl
    }

    /**
     * 설문관리를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qmc/EgovQustnrManageModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qmc/EgovQustnrManageModify.do"])
    @Throws(Exception::class)
    fun QustnrManageModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrManageVO: QustnrManageVO,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        //로그인 객체 선언
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        var sLocationUrl = "/uss/olp/qmc/EgovQustnrManageModify"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        //공통코드  직업유형 조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM034"
        model.addAttribute("comCode034", cmmUseService!!.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            beanValidator!!.validate(qustnrManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                model.addAttribute("resultList", egovQustnrManageService!!.selectQustnrManageDetail(qustnrManageVO))

                //설문템플릿 정보 불러오기
                model.addAttribute(
                    "listQustnrTmplat",
                    egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO)
                )

                return sLocationUrl
            }

            //아이디 설정
            qustnrManageVO.frstRegisterId = loginVO.uniqId
            qustnrManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrManageService!!.updateQustnrManage(qustnrManageVO)
            sLocationUrl = "redirect:/uss/olp/qmc/EgovQustnrManageList.do"
        } else {
            model.addAttribute("resultList", egovQustnrManageService!!.selectQustnrManageDetail(qustnrManageVO))

            val newQustnrManageVO = egovQustnrManageService.selectQustnrManageDetailModel(qustnrManageVO)
            model.addAttribute("qustnrManageVO", newQustnrManageVO)

            //설문템플릿 정보 불러오기
            model.addAttribute(
                "listQustnrTmplat",
                egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 설문관리를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qmc/EgovQustnrManageRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qmc/EgovQustnrManageRegist.do"])
    @Throws(Exception::class)
    fun QustnrManageRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrManageVO") qustnrManageVO: QustnrManageVO,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        //로그인 객체 선언
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        var sLocationUrl = "/uss/olp/qmc/EgovQustnrManageRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        LOGGER.info("cmd => {}", sCmd)

        //공통코드  직업유형 조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM034"
        model.addAttribute("comCode034", cmmUseService!!.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            beanValidator!!.validate(qustnrManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                //설문템플릿 정보 불러오기
                model.addAttribute(
                    "listQustnrTmplat",
                    egovQustnrManageService!!.selectQustnrTmplatManageList(qustnrManageVO)
                )
                return sLocationUrl
            }

            //아이디 설정
            qustnrManageVO.frstRegisterId = loginVO.uniqId
            qustnrManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrManageService!!.insertQustnrManage(qustnrManageVO)
            sLocationUrl = "redirect:/uss/olp/qmc/EgovQustnrManageList.do"
        } else {
            //설문템플릿 정보 불러오기
            model.addAttribute(
                "listQustnrTmplat",
                egovQustnrManageService!!.selectQustnrTmplatManageList(qustnrManageVO)
            )
            //System.out.println("???:::" + (egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO)).get(0));
        }

        return sLocationUrl
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovQustnrManageController::class.java)
    }
}
