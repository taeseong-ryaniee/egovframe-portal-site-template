package egovframework.let.uss.olp.qrm.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.uss.olp.qrm.service.EgovQustnrRespondManageService
import egovframework.let.uss.olp.qrm.service.QustnrRespondManageVO
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
 * 설문응답자관리 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQustnrRespondManageController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovQustnrRespondManageService")
    private val egovQustnrRespondManageService: EgovQustnrRespondManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /**
     * 응답자정보 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrRespondManageVO
     * @param model
     * @return "/uss/olp/qrm/EgovQustnrRespondManageList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qrm/EgovQustnrRespondManageList.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondManageList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrRespondManageVO: QustnrRespondManageVO,
        model: ModelMap
    ): String {
        val sSearchMode: String =
            (if (commandMap.get("searchMode") == null) "" else commandMap.get("searchMode") as kotlin.String?)!!

        //설문지정보에서 넘어오면 자동검색 설정
        if (sSearchMode == "Y") {
            searchVO.searchCondition = "QESTNR_ID"
            searchVO.searchKeyword = qustnrRespondManageVO.qestnrId
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

        model.addAttribute("resultList", egovQustnrRespondManageService!!.selectQustnrRespondManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrRespondManageService.selectQustnrRespondManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qrm/EgovQustnrRespondManageList"
    }

    /**
     * 응답자정보 목록을 상세조회 조회한다.
     * @param searchVO
     * @param qustnrRespondManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qrm/EgovQustnrRespondManageDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qrm/EgovQustnrRespondManageDetail.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondManageDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrRespondManageVO: QustnrRespondManageVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/olp/qrm/EgovQustnrRespondManageDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrRespondManageService!!.deleteQustnrRespondManage(qustnrRespondManageVO)
            sLocationUrl = "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do"
        } else {
            //성별코드조회
            val voComCode = ComDefaultCodeVO()
            voComCode.codeId = "COM014"
            model.addAttribute("comCode014", cmmUseService!!.selectCmmCodeDetail(voComCode))

            //직업코드조회
            voComCode.codeId = "COM034"
            model.addAttribute("comCode034", cmmUseService.selectCmmCodeDetail(voComCode))

            model.addAttribute(
                "resultList",
                egovQustnrRespondManageService!!.selectQustnrRespondManageDetail(qustnrRespondManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 응답자정보를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrRespondManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qrm/EgovQustnrRespondManageModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qrm/EgovQustnrRespondManageModify.do"])
    @Throws(Exception::class)
    fun QustnrRespondManageModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrRespondManageVO") qustnrRespondManageVO: QustnrRespondManageVO,
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

        var sLocationUrl = "/uss/olp/qrm/EgovQustnrRespondManageModify"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        //성별코드조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM014"
        model.addAttribute("comCode014", cmmUseService!!.selectCmmCodeDetail(voComCode))

        //직업코드조회
        voComCode.codeId = "COM034"
        model.addAttribute("comCode034", cmmUseService.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            //서버  validate 체크
            beanValidator!!.validate(qustnrRespondManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                return sLocationUrl
            }
            //아이디 설정
            qustnrRespondManageVO.frstRegisterId = loginVO.uniqId
            qustnrRespondManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrRespondManageService!!.updateQustnrRespondManage(qustnrRespondManageVO)
            sLocationUrl = "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do"
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrRespondManageService!!.selectQustnrRespondManageDetail(qustnrRespondManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 응답자정보를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrRespondManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qrm/EgovQustnrRespondManageRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qrm/EgovQustnrRespondManageRegist.do"])
    @Throws(Exception::class)
    fun QustnrRespondManageRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("qustnrRespondManageVO") qustnrRespondManageVO: QustnrRespondManageVO,
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

        var sLocationUrl = "/uss/olp/qrm/EgovQustnrRespondManageRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        LOGGER.info("cmd => {}", sCmd)

        //성별코드조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM014"
        model.addAttribute("comCode014", cmmUseService!!.selectCmmCodeDetail(voComCode))

        //직업코드조회
        voComCode.codeId = "COM034"
        model.addAttribute("comCode034", cmmUseService.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            //서버  validate 체크
            beanValidator!!.validate(qustnrRespondManageVO, bindingResult)
            if (bindingResult.hasErrors()) {
                return sLocationUrl
            }
            //아이디 설정
            qustnrRespondManageVO.frstRegisterId = loginVO.uniqId
            qustnrRespondManageVO.lastUpdusrId = loginVO.uniqId

            egovQustnrRespondManageService!!.insertQustnrRespondManage(qustnrRespondManageVO)
            sLocationUrl = "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do"
        }

        return sLocationUrl
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovQustnrRespondManageController::class.java)
    }
}


