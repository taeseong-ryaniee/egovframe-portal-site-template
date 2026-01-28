package egovframework.let.uss.olp.qim.web

import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.let.uss.olp.qim.service.EgovQustnrItemManageService
import egovframework.let.uss.olp.qim.service.QustnrItemManageVO
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

/**
 * 설문항목관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQustnrItemManageController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovQustnrItemManageService")
    private val egovQustnrItemManageService: EgovQustnrItemManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /**
     * 설문항목 팝업 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrItemManageVO
     * @param model
     * @return "/uss/olp/qim/EgovQustnrItemManageListPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qim/EgovQustnrItemManageListPopup.do"])
    @Throws(Exception::class)
    fun EgovQustnrItemManageListPopup(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrItemManageVO: QustnrItemManageVO?,
        model: ModelMap
    ): String {
        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        if (sCmd == "del") {
            egovQustnrItemManageService!!.deleteQustnrItemManage(qustnrItemManageVO)
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

        model.addAttribute("resultList", egovQustnrItemManageService!!.selectQustnrItemManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrItemManageService.selectQustnrItemManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qim/EgovQustnrItemManageListPopup"
    }

    /**
     * 설문항목 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrItemManageVO
     * @param model
     * @return "/uss/olp/qim/EgovQustnrItemManageList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qim/EgovQustnrItemManageList.do"])
    @Throws(Exception::class)
    fun EgovQustnrItemManageList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrItemManageVO: QustnrItemManageVO,
        model: ModelMap
    ): String {
        val sSearchMode: String =
            (if (commandMap.get("searchMode") == null) "" else commandMap.get("searchMode") as kotlin.String?)!!

        //설문문항에 넘어온 건에 대해 조회
        if (sSearchMode == "Y") {
            searchVO.searchCondition = "QUSTNR_QESITM_ID" //qestnrQesitmId
            searchVO.searchKeyword = qustnrItemManageVO.qestnrQesitmId
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

        model.addAttribute("resultList", egovQustnrItemManageService!!.selectQustnrItemManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrItemManageService.selectQustnrItemManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qim/EgovQustnrItemManageList"
    }

    /**
     * 설문항목 목록을 상세조회 조회한다.
     * @param searchVO
     * @param qustnrItemManageVO
     * @param commandMap
     * @param model
     * @return  "/uss/olp/qim/EgovQustnrItemManageDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qim/EgovQustnrItemManageDetail.do"])
    @Throws(Exception::class)
    fun EgovQustnrItemManageDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrItemManageVO: QustnrItemManageVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/olp/qim/EgovQustnrItemManageDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrItemManageService!!.deleteQustnrItemManage(qustnrItemManageVO)
            sLocationUrl = "redirect:/uss/olp/qim/EgovQustnrItemManageList.do"
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrItemManageService!!.selectQustnrItemManageDetail(qustnrItemManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 설문항목를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrItemManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qim/EgovQustnrItemManageModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qim/EgovQustnrItemManageModify.do"])
    @Throws(Exception::class)
    fun QustnrItemManageModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrItemManageVO") qustnrItemManageVO: QustnrItemManageVO,
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

        var sLocationUrl = "/uss/olp/qim/EgovQustnrItemManageModify"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "save") {
            //서버  validate 체크

            beanValidator!!.validate(qustnrItemManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                //설문항목(을)를  정보 불러오기
                model.addAttribute(
                    "listQustnrTmplat",
                    egovQustnrItemManageService!!.selectQustnrTmplatManageList(qustnrItemManageVO)
                )
                //게시물 불러오기
                model.addAttribute(
                    "resultList",
                    egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO)
                )

                return "/uss/olp/qim/EgovQustnrItemManageModify"
            }

            //아이디 설정
            qustnrItemManageVO.frstRegisterId = loginVO.uniqId
            qustnrItemManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrItemManageService!!.updateQustnrItemManage(qustnrItemManageVO)
            sLocationUrl = "redirect:/uss/olp/qim/EgovQustnrItemManageList.do"
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrItemManageService!!.selectQustnrItemManageDetail(qustnrItemManageVO)
            )

            //설문항목(을)를  정보 불러오기
            model.addAttribute(
                "listQustnrTmplat",
                egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 설문항목를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrItemManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qim/EgovQustnrItemManageRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qim/EgovQustnrItemManageRegist.do"])
    @Throws(Exception::class)
    fun QustnrItemManageRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrItemManageVO") qustnrItemManageVO: QustnrItemManageVO,
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

        var sLocationUrl = "/uss/olp/qim/EgovQustnrItemManageRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        LOGGER.info("cmd => {}", sCmd)

        if (sCmd == "save") {
            //서버  validate 체크

            beanValidator!!.validate(qustnrItemManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                //설문항목(을)를  정보 불러오기
                model.addAttribute(
                    "listQustnrTmplat",
                    egovQustnrItemManageService!!.selectQustnrTmplatManageList(qustnrItemManageVO)
                )
                return "/uss/olp/qim/EgovQustnrItemManageRegist"
            }

            //아이디 설정
            qustnrItemManageVO.frstRegisterId = loginVO.uniqId
            qustnrItemManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrItemManageService!!.insertQustnrItemManage(qustnrItemManageVO)
            sLocationUrl = "redirect:/uss/olp/qim/EgovQustnrItemManageList.do"
        } else {
            //설문항목(을)를  정보 불러오기
            model.addAttribute(
                "listQustnrTmplat",
                egovQustnrItemManageService!!.selectQustnrTmplatManageList(qustnrItemManageVO)
            )
        }

        return sLocationUrl
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovQustnrItemManageController::class.java)
    }
}


