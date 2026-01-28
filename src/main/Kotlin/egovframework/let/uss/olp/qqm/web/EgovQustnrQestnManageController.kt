package egovframework.let.uss.olp.qqm.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.uss.olp.qqm.service.EgovQustnrQestnManageService
import egovframework.let.uss.olp.qqm.service.QustnrQestnManageVO
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
 * 설문문항을 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQustnrQestnManageController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovQustnrQestnManageService")
    private val egovQustnrQestnManageService: EgovQustnrQestnManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /**
     * 설문항목 통계를 조회한다.
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qqm/EgovQustnrQestnManageStatistics"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qqm/EgovQustnrQestnManageStatistics.do"])
    @Throws(Exception::class)
    fun EgovQustnrQestnManageStatistics(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrQestnManageVO: QustnrQestnManageVO,
        @RequestParam commandMap: MutableMap<String?, Any?>?,
        model: ModelMap
    ): String {
        val sLocationUrl = "/uss/olp/qqm/EgovQustnrQestnManageStatistics"

        //List sampleList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
        model.addAttribute(
            "resultList",
            egovQustnrQestnManageService!!.selectQustnrQestnManageDetail(qustnrQestnManageVO)
        )
        // 객관식설문통계
        val mapParam = HashMap<String?, String?>()
        mapParam.put("qestnrQesitmId", qustnrQestnManageVO.qestnrQesitmId)
        //List statisticsList = egovQustnrQestnManageService.selectQustnrManageStatistics(mapParam);
        model.addAttribute("statisticsList", egovQustnrQestnManageService.selectQustnrManageStatistics(mapParam))
        // 주관식설문통계
        //List statisticsList2 = egovQustnrQestnManageService.selectQustnrManageStatistics2(mapParam);
        model.addAttribute("statisticsList2", egovQustnrQestnManageService.selectQustnrManageStatistics2(mapParam))
        return sLocationUrl
    }

    /**
     * 설문문항 팝업 록을 조회한다.
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qqm/EgovQustnrQestnManageListPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qqm/EgovQustnrQestnManageListPopup.do"])
    @Throws(Exception::class)
    fun EgovQustnrQestnManageListPopup(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @ModelAttribute("qustnrQestnManageVO") qustnrQestnManageVO: QustnrQestnManageVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        val sSearchMode: String =
            (if (commandMap.get("searchMode") == null) "" else commandMap.get("searchMode") as kotlin.String?)!!

        //설문지정보에서 넘어오면 자동검색 설정
        if (sSearchMode == "Y") {
            searchVO.searchCondition = "QESTNR_ID"
            searchVO.searchKeyword = qustnrQestnManageVO.qestnrId
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

        model.addAttribute("resultList", egovQustnrQestnManageService!!.selectQustnrQestnManageList(searchVO))

        val totCnt = egovQustnrQestnManageService.selectQustnrQestnManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qqm/EgovQustnrQestnManageListPopup"
    }

    /**
     * 설문문항 목록을 조회한다.
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qqm/EgovQustnrQestnManageList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qqm/EgovQustnrQestnManageList.do"])
    @Throws(Exception::class)
    fun EgovQustnrQestnManageList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @ModelAttribute("qustnrQestnManageVO") qustnrQestnManageVO: QustnrQestnManageVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        val sSearchMode: String =
            (if (commandMap.get("searchMode") == null) "" else commandMap.get("searchMode") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrQestnManageService!!.deleteQustnrQestnManage(qustnrQestnManageVO)
        }

        //설문지정보에서 넘어오면 자동검색 설정
        if (sSearchMode == "Y") {
            searchVO.searchCondition = "QESTNR_ID"
            searchVO.searchKeyword = qustnrQestnManageVO.qestnrId
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

        model.addAttribute("resultList", egovQustnrQestnManageService!!.selectQustnrQestnManageList(searchVO))

        val totCnt = egovQustnrQestnManageService.selectQustnrQestnManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qqm/EgovQustnrQestnManageList"
    }

    /**
     * 설문문항 목록을 상세조회 조회한다.
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qqm/EgovQustnrQestnManageDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qqm/EgovQustnrQestnManageDetail.do"])
    @Throws(Exception::class)
    fun EgovQustnrQestnManageDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @ModelAttribute("qustnrQestnManageVO") qustnrQestnManageVO: QustnrQestnManageVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/olp/qqm/EgovQustnrQestnManageDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrQestnManageService!!.deleteQustnrQestnManage(qustnrQestnManageVO)
            /** 목록으로갈때 검색조건 유지  */
            sLocationUrl = "redirect:/uss/olp/qqm/EgovQustnrQestnManageList.do?"
            sLocationUrl = sLocationUrl + "searchMode=" + qustnrQestnManageVO.searchMode
            sLocationUrl = sLocationUrl + "&qestnrId=" + qustnrQestnManageVO.qestnrId
            sLocationUrl = sLocationUrl + "&qestnrTmplatId=" + qustnrQestnManageVO.qestnrTmplatId
        } else {
            //공통코드 질문유형 조회
            val voComCode = ComDefaultCodeVO()
            voComCode.codeId = "COM018"
            model.addAttribute("cmmCode018", cmmUseService!!.selectCmmCodeDetail(voComCode))

            model.addAttribute(
                "resultList",
                egovQustnrQestnManageService!!.selectQustnrQestnManageDetail(qustnrQestnManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 설문문항를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrQestnManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qqm/EgovQustnrQestnManageModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qqm/EgovQustnrQestnManageModify.do"])
    @Throws(Exception::class)
    fun QustnrQestnManageModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrQestnManageVO") qustnrQestnManageVO: QustnrQestnManageVO,
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

        var sLocationUrl = "/uss/olp/qqm/EgovQustnrQestnManageModify"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        //공통코드 질문유형 조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM018"
        model.addAttribute("cmmCode018", cmmUseService!!.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            //서버  validate 체크
            beanValidator!!.validate(qustnrQestnManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                //설문제목가져오기
                val sQestnrId: String =
                    (if (commandMap.get("qestnrId") == null) "" else commandMap.get("qestnrId") as kotlin.String?)!!
                val sQestnrTmplatId: String =
                    (if (commandMap.get("qestnrTmplatId") == null) "" else commandMap.get("qestnrTmplatId") as kotlin.String?)!!

                LOGGER.info("sQestnrId => {}", sQestnrId)
                LOGGER.info("sQestnrTmplatId => {}", sQestnrTmplatId)
                if (sQestnrId != "" && sQestnrTmplatId != "") {
                    val mapQustnrManage: MutableMap<String?, String?> = HashMap<String?, String?>()
                    mapQustnrManage.put("qestnrId", sQestnrId)
                    mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId)

                    model.addAttribute(
                        "qestnrInfo",
                        egovQustnrQestnManageService!!.selectQustnrManageQestnrSj(mapQustnrManage)
                    )
                }

                model.addAttribute(
                    "resultList",
                    egovQustnrQestnManageService!!.selectQustnrQestnManageDetail(qustnrQestnManageVO)
                )
                return "/uss/olp/qqm/EgovQustnrQestnManageModify"
            }

            //아이디 설정
            qustnrQestnManageVO.frstRegisterId = loginVO.uniqId
            qustnrQestnManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrQestnManageService!!.updateQustnrQestnManage(qustnrQestnManageVO)
            /** 목록으로갈때 검색조건 유지  */
            sLocationUrl = "redirect:/uss/olp/qqm/EgovQustnrQestnManageList.do?"
            sLocationUrl = sLocationUrl + "searchMode=" + qustnrQestnManageVO.searchMode
            sLocationUrl = sLocationUrl + "&qestnrId=" + qustnrQestnManageVO.qestnrId
            sLocationUrl = sLocationUrl + "&qestnrTmplatId=" + qustnrQestnManageVO.qestnrTmplatId
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrQestnManageService!!.selectQustnrQestnManageDetail(qustnrQestnManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 설문문항를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrQestnManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qqm/EgovQustnrQestnManageRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qqm/EgovQustnrQestnManageRegist.do"])
    @Throws(Exception::class)
    fun QustnrQestnManageRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrQestnManageVO") qustnrQestnManageVO: QustnrQestnManageVO,
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

        var sLocationUrl = "/uss/olp/qqm/EgovQustnrQestnManageRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        LOGGER.info("cmd => {}", sCmd)

        //공통코드 질문유형 조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM018"
        model.addAttribute("cmmCode018", cmmUseService!!.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            //서버  validate 체크

            beanValidator!!.validate(qustnrQestnManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                //설문제목가져오기
                val sQestnrId: String =
                    (if (commandMap.get("qestnrId") == null) "" else commandMap.get("qestnrId") as kotlin.String?)!!
                val sQestnrTmplatId: String =
                    (if (commandMap.get("qestnrTmplatId") == null) "" else commandMap.get("qestnrTmplatId") as kotlin.String?)!!

                LOGGER.info("sQestnrId => {}", sQestnrId)
                LOGGER.info("sQestnrTmplatId => {}", sQestnrTmplatId)
                if (sQestnrId != "" && sQestnrTmplatId != "") {
                    val mapQustnrManage: MutableMap<String?, String?> = HashMap<String?, String?>()
                    mapQustnrManage.put("qestnrId", sQestnrId)
                    mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId)

                    model.addAttribute(
                        "qestnrInfo",
                        egovQustnrQestnManageService!!.selectQustnrManageQestnrSj(mapQustnrManage)
                    )
                }

                return "/uss/olp/qqm/EgovQustnrQestnManageRegist"
            }

            //아이디 설정
            qustnrQestnManageVO.frstRegisterId = loginVO.uniqId
            qustnrQestnManageVO.lastUpdusrId = loginVO.uniqId
            /** 목록으로갈때 검색조건 유지  */
            egovQustnrQestnManageService!!.insertQustnrQestnManage(qustnrQestnManageVO)
            sLocationUrl = "redirect:/uss/olp/qqm/EgovQustnrQestnManageList.do?"
            sLocationUrl = sLocationUrl + "searchMode=" + qustnrQestnManageVO.searchMode
            sLocationUrl = sLocationUrl + "&qestnrId=" + qustnrQestnManageVO.qestnrId
            sLocationUrl = sLocationUrl + "&qestnrTmplatId=" + qustnrQestnManageVO.qestnrTmplatId
        } else {
            //설문제목가져오기

            val sQestnrId: String =
                (if (commandMap.get("qestnrId") == null) "" else commandMap.get("qestnrId") as kotlin.String?)!!
            val sQestnrTmplatId: String =
                (if (commandMap.get("qestnrTmplatId") == null) "" else commandMap.get("qestnrTmplatId") as kotlin.String?)!!

            LOGGER.info("sQestnrId => {}", sQestnrId)
            LOGGER.info("sQestnrTmplatId => {}", sQestnrTmplatId)
            if (sQestnrId != "" && sQestnrTmplatId != "") {
                val mapQustnrManage: MutableMap<String?, String?> = HashMap<String?, String?>()
                mapQustnrManage.put("qestnrId", sQestnrId)
                mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId)

                model.addAttribute(
                    "qestnrInfo",
                    egovQustnrQestnManageService!!.selectQustnrManageQestnrSj(mapQustnrManage)
                )
            }
        }

        return sLocationUrl
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovQustnrQestnManageController::class.java)
    }
}


