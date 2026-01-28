package egovframework.let.uss.olp.qri.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.uss.olp.qri.service.EgovQustnrRespondInfoService
import egovframework.let.uss.olp.qri.service.QustnrRespondInfoVO
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
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 설문조사 Controller Class 구현
 *
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQustnrRespondInfoController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovQustnrRespondInfoService")
    private val egovQustnrRespondInfoService: EgovQustnrRespondInfoService? = null

    @Resource(name = "egovQustnrRespondManageService")
    private val egovQustnrRespondManageService: EgovQustnrRespondManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /**
     * 설문템플릿을 적용한다.
     *
     * @param searchVO
     * @param request
     * @param commandMap
     * @param model
     * @return "/uss/olp/mgt/template/template"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qri/template/template.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondInfoManageTemplate(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        request: HttpServletRequest,
        @RequestParam commandMap: MutableMap<String, Any?>,
        model: ModelMap
    ): String? {
        val sTemplateUrl = commandMap.get("templateUrl") as String?

        // log.debug("qestnrId=>" + commandMap.get("qestnrId"));
        // log.debug("qestnrTmplatId=>" + commandMap.get("qestnrTmplatId"));
        // log.debug("templateUrl=>" + commandMap.get("templateUrl"));

        // 설문템플릿정보
        model.addAttribute("QustnrTmplatManage", egovQustnrRespondInfoService!!.selectQustnrTmplatManage(commandMap))

        // 설문정보
        model.addAttribute(
            "Comtnqestnrinfo",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqestnrinfo(commandMap)
        )
        // 문항정보
        model.addAttribute(
            "Comtnqustnrqesitm",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnrqesitm(commandMap)
        )
        // 항목정보
        model.addAttribute(
            "Comtnqustnriem",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnriem(commandMap)
        )
        // 설문템플릿ID 설정
        model.addAttribute(
            "qestnrTmplatId",
            if (commandMap.get("qestnrTmplatId") == null) "" else commandMap.get("qestnrTmplatId") as String?
        )
        // 설문지정보ID 설정
        model.addAttribute(
            "qestnrId",
            if (commandMap.get("qestnrId") == null) "" else commandMap.get("qestnrId") as String?
        )

        // 객관식통계 답안
        model.addAttribute(
            "qestnrStatistic1",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics1(commandMap)
        )

        // 주관식통계 답안
        model.addAttribute(
            "qestnrStatistic2",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics2(commandMap)
        )

        // 이전 주소
        model.addAttribute("returnUrl", request.getHeader("REFERER"))

        return sTemplateUrl
    }

    /**
     * 설문조사 전체 통계를 조회한다.
     *
     * @param searchVO
     * @param request
     * @param commandMap
     * @param model
     * @return "/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondInfoManageStatistics(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        request: HttpServletRequest,
        @RequestParam commandMap: MutableMap<String, Any?>,
        model: ModelMap
    ): String {
        val sLocationUrl = "/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics"

        // 설문정보
        model.addAttribute(
            "Comtnqestnrinfo",
            egovQustnrRespondInfoService!!.selectQustnrRespondInfoManageComtnqestnrinfo(commandMap)
        )
        // 문항정보
        model.addAttribute(
            "Comtnqustnrqesitm",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnrqesitm(commandMap)
        )
        // 항목정보
        model.addAttribute(
            "Comtnqustnriem",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnriem(commandMap)
        )
        // 설문템플릿ID 설정
        model.addAttribute(
            "qestnrTmplatId",
            if (commandMap.get("qestnrTmplatId") == null) "" else commandMap.get("qestnrTmplatId") as String?
        )
        // 설문지정보ID 설정
        model.addAttribute(
            "qestnrId",
            if (commandMap.get("qestnrId") == null) "" else commandMap.get("qestnrId") as String?
        )

        // 객관식통계 답안
        model.addAttribute(
            "qestnrStatistic1",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics1(commandMap)
        )

        // 주관식통계 답안
        model.addAttribute(
            "qestnrStatistic2",
            egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics2(commandMap)
        )

        // 이전 주소
        model.addAttribute("returnUrl", request.getHeader("REFERER"))

        return sLocationUrl
    }

    /**
     * 설문조사(설문등록) 목록을 조회한다.
     *
     * @param searchVO
     * @param request
     * @param response
     * @param commandMap
     * @param model
     * @return "/uss/olp/qnn/EgovQustnrRespondInfoManageList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qnn/EgovQustnrRespondInfoManageList.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondInfoManageList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        request: HttpServletRequest,
        response: HttpServletResponse?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        // 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가
        request.getSession().setAttribute("menuNo", "4000000")
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

        model.addAttribute("resultList", egovQustnrRespondInfoService!!.selectQustnrRespondInfoManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrRespondInfoService.selectQustnrRespondInfoManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qnn/EgovQustnrRespondInfoManageList"
    }

    /**
     * 설문조사(설문등록)를 등록한다.
     *
     * @param searchVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qnn/EgovQustnrRespondInfoManageRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondInfoManageRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String, Any?>,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인 객체 선언
        var loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO?
        if (loginVO == null) {
            loginVO = LoginVO()
        }

        var sLocationUrl = "/uss/olp/qnn/EgovQustnrRespondInfoManageRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        // log.info("cmd =>" + sCmd);

        // 성별코드조회
        val voComCode = ComDefaultCodeVO()
        voComCode.codeId = "COM014"
        // List listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("comCode014", cmmUseService!!.selectCmmCodeDetail(voComCode))

        // 직업코드조회
        voComCode.codeId = "COM034"
        // listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("comCode034", cmmUseService.selectCmmCodeDetail(voComCode))

        if (sCmd == "save") {
            // 설문조사 처리 START

            var sKey = ""
            var sVal: String? = ""
            for (key in commandMap.keys) {
                sKey = key.toString()

                // 설문문항정보 추출
                if (sKey.length > 6 && sKey.substring(0, 6) == "QQESTN") {
                    // 설문조사 등록
                    // 객관식 답안 처리

                    if ((commandMap.get("TY_" + key) as String) == "1") {
                        if (commandMap.get(key) is String) {
                            sVal = commandMap.get(key) as String?

                            val qustnrRespondInfoVO = QustnrRespondInfoVO()

                            qustnrRespondInfoVO.qestnrTmplatId = commandMap.get("qestnrTmplatId") as String?
                            qustnrRespondInfoVO.qestnrId = commandMap.get("qestnrId") as String?
                            qustnrRespondInfoVO.qestnrQesitmId = sKey
                            qustnrRespondInfoVO.qustnrIemId = sVal

                            qustnrRespondInfoVO.respondAnswerCn = ""

                            qustnrRespondInfoVO.respondNm = loginVO.name
                            qustnrRespondInfoVO.etcAnswerCn = commandMap.get("ETC_" + sVal) as String?

                            qustnrRespondInfoVO.frstRegisterId = loginVO.uniqId
                            qustnrRespondInfoVO.lastUpdusrId = loginVO.uniqId

                            egovQustnrRespondInfoService!!.insertQustnrRespondInfo(qustnrRespondInfoVO)
                        } else {
                            val arrVal = commandMap.get(key) as Array<String?>
                            for (g in arrVal.indices) {
                                // ("QQESTN arr :" + arrVal[g]);
                                val qustnrRespondInfoVO = QustnrRespondInfoVO()

                                qustnrRespondInfoVO.qestnrTmplatId = commandMap.get("qestnrTmplatId") as String?
                                qustnrRespondInfoVO.qestnrId = commandMap.get("qestnrId") as String?
                                qustnrRespondInfoVO.qestnrQesitmId = sKey
                                qustnrRespondInfoVO.qustnrIemId = arrVal[g]

                                qustnrRespondInfoVO.respondAnswerCn = ""

                                qustnrRespondInfoVO.respondNm = loginVO.name
                                qustnrRespondInfoVO.etcAnswerCn = commandMap.get("ETC_" + arrVal[g]) as String?

                                qustnrRespondInfoVO.frstRegisterId = loginVO.uniqId
                                qustnrRespondInfoVO.lastUpdusrId = loginVO.uniqId

                                egovQustnrRespondInfoService!!.insertQustnrRespondInfo(qustnrRespondInfoVO)
                            }
                        }

                        // 주관식 답안 처리
                    } else if ((commandMap.get("TY_" + key) as String) == "2") {
                        val qustnrRespondInfoVO = QustnrRespondInfoVO()

                        qustnrRespondInfoVO.qestnrTmplatId = commandMap.get("qestnrTmplatId") as String?
                        qustnrRespondInfoVO.qestnrId = commandMap.get("qestnrId") as String?
                        qustnrRespondInfoVO.qestnrQesitmId = sKey
                        qustnrRespondInfoVO.qustnrIemId = null

                        qustnrRespondInfoVO.respondAnswerCn = commandMap.get(sKey) as String?

                        qustnrRespondInfoVO.respondNm = loginVO.name
                        qustnrRespondInfoVO.etcAnswerCn = null

                        qustnrRespondInfoVO.frstRegisterId = loginVO.uniqId
                        qustnrRespondInfoVO.lastUpdusrId = loginVO.uniqId

                        egovQustnrRespondInfoService!!.insertQustnrRespondInfo(qustnrRespondInfoVO)
                    }
                }
            }

            // 설문응답자 처리
            val qustnrRespondManageVO = QustnrRespondManageVO()

            qustnrRespondManageVO.qestnrId = commandMap.get("qestnrId") as String?
            qustnrRespondManageVO.qestnrTmplatId = commandMap.get("qestnrTmplatId") as String?

            qustnrRespondManageVO.sexdstnCode = commandMap.get("sexdstnCode") as String?
            qustnrRespondManageVO.occpTyCode = commandMap.get("occpTyCode") as String?
            qustnrRespondManageVO.brth = commandMap.get("brth") as String?
            qustnrRespondManageVO.respondNm = commandMap.get("respondNm") as String?

            qustnrRespondManageVO.frstRegisterId = loginVO.uniqId
            qustnrRespondManageVO.lastUpdusrId = loginVO.uniqId
            egovQustnrRespondManageService!!.insertQustnrRespondManage(qustnrRespondManageVO)

            var ReusltScript = ""

            ReusltScript += "<script type='text/javaScript' language='javascript'>"
            ReusltScript += "alert(' 설문참여에 응해주셔서 감사합니다!  ');"
            ReusltScript += "</script>"

            model.addAttribute("reusltScript", ReusltScript)
            sLocationUrl = "forward:/uss/olp/qnn/EgovQustnrRespondInfoManageList.do"
        } else {
            if (loginVO.uniqId != null) {
                commandMap.put("uniqId", loginVO.uniqId as String)
                // 사용자정보
                model.addAttribute(
                    "Emplyrinfo",
                    egovQustnrRespondInfoService!!.selectQustnrRespondInfoManageEmplyrinfo(commandMap)
                )
            }

            // 설문템플릿정보
            model.addAttribute(
                "QustnrTmplatManage",
                egovQustnrRespondInfoService!!.selectQustnrTmplatManage(commandMap)
            )

            // 설문정보
            model.addAttribute(
                "Comtnqestnrinfo",
                egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqestnrinfo(commandMap)
            )
            // 문항정보
            model.addAttribute(
                "Comtnqustnrqesitm",
                egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnrqesitm(commandMap)
            )
            // 항목정보
            model.addAttribute(
                "Comtnqustnriem",
                egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnriem(commandMap)
            )
            // 설문템플릿ID 설정
            model.addAttribute(
                "qestnrTmplatId",
                if (commandMap.get("qestnrTmplatId") == null) "" else commandMap.get("qestnrTmplatId") as String?
            )
            // 설문지정보ID 설정
            model.addAttribute(
                "qestnrId",
                if (commandMap.get("qestnrId") == null) "" else commandMap.get("qestnrId") as String?
            )
        }

        return sLocationUrl
    }

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     *
     * @param searchVO
     * @param request
     * @param commandMap
     * @param qustnrRespondInfoVO
     * @param model
     * @return "/uss/olp/qri/EgovQustnrRespondInfoList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qri/EgovQustnrRespondInfoList.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondInfoList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        request: HttpServletRequest?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrRespondInfoVO: QustnrRespondInfoVO,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인 객체 선언
        var loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO?
        if (loginVO == null) {
            loginVO = LoginVO()
        }

        val sSearchMode: String =
            (if (commandMap.get("searchMode") == null) "" else commandMap.get("searchMode") as kotlin.String?)!!

        // 설문지정보에서 넘어오면 자동검색 설정
        if (sSearchMode == "Y") {
            searchVO.searchCondition = "QESTNR_ID"
            searchVO.searchKeyword = qustnrRespondInfoVO.qestnrId
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

        val sampleList = egovQustnrRespondInfoService!!.selectQustnrRespondInfoList(searchVO)
        model.addAttribute("resultList", sampleList)

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrRespondInfoService.selectQustnrRespondInfoListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qri/EgovQustnrRespondInfoList"
    }

    /**
     * 응답자결과(설문조사) 목록을 상세조회 조회한다.
     *
     * @param searchVO
     * @param qustnrRespondInfoVO
     * @param commandMap
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qri/EgovQustnrRespondInfoDetail.do"])
    @Throws(Exception::class)
    fun EgovQustnrRespondInfoDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrRespondInfoVO: QustnrRespondInfoVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/olp/qri/EgovQustnrRespondInfoDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrRespondInfoService!!.deleteQustnrRespondInfo(qustnrRespondInfoVO)
            sLocationUrl = "redirect:/uss/olp/qri/EgovQustnrRespondInfoList.do"
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrRespondInfoService!!.selectQustnrRespondInfoDetail(qustnrRespondInfoVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 응답자결과(설문조사)를 수정한다.
     *
     * @param searchVO
     * @param commandMap
     * @param request
     * @param qustnrRespondInfoVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qri/EgovQustnrRespondInfoModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qri/EgovQustnrRespondInfoModify.do"])
    @Throws(Exception::class)
    fun QustnrRespondInfoModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        request: HttpServletRequest?,
        @ModelAttribute("qustnrRespondInfoVO") qustnrRespondInfoVO: QustnrRespondInfoVO,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인 객체 선언
        var loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO?
        if (loginVO == null) {
            loginVO = LoginVO()
        }

        var sLocationUrl = "/uss/olp/qri/EgovQustnrRespondInfoModify"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "save") {
            // 서버 validate 체크
            beanValidator!!.validate(qustnrRespondInfoVO, bindingResult)
            if (bindingResult.hasErrors()) {
                return sLocationUrl
            }

            // 아이디 설정
            qustnrRespondInfoVO.frstRegisterId = loginVO.uniqId
            qustnrRespondInfoVO.lastUpdusrId = loginVO.uniqId

            egovQustnrRespondInfoService!!.updateQustnrRespondInfo(qustnrRespondInfoVO)
            sLocationUrl = "redirect:/uss/olp/qri/EgovQustnrRespondInfoList.do"
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrRespondInfoService!!.selectQustnrRespondInfoDetail(qustnrRespondInfoVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 응답자결과(설문조사)를 등록한다.
     *
     * @param searchVO
     * @param commandMap
     * @param request
     * @param qustnrRespondInfoVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qri/EgovQustnrRespondInfoRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qri/EgovQustnrRespondInfoRegist.do"])
    @Throws(Exception::class)
    fun QustnrRespondInfoRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, String?>,
        request: HttpServletRequest?,
        @ModelAttribute("qustnrRespondInfoVO") qustnrRespondInfoVO: QustnrRespondInfoVO,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        // 0. Spring Security 사용자권한 처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인 객체 선언
        var loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO?
        if (loginVO == null) {
            loginVO = LoginVO()
        }

        var sLocationUrl = "/uss/olp/qri/EgovQustnrRespondInfoRegist"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        LOGGER.info("cmd => {}", sCmd)

        if (sCmd == "save") {
            // 서버 validate 체크
            beanValidator!!.validate(qustnrRespondInfoVO, bindingResult)
            if (bindingResult.hasErrors()) {
                return sLocationUrl
            }

            // 아이디 설정
            qustnrRespondInfoVO.frstRegisterId = loginVO.uniqId
            qustnrRespondInfoVO.lastUpdusrId = loginVO.uniqId

            egovQustnrRespondInfoService!!.insertQustnrRespondInfo(qustnrRespondInfoVO)
            sLocationUrl = "redirect:/uss/olp/qri/EgovQustnrRespondInfoList.do"
        }

        return sLocationUrl
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovQustnrRespondInfoController::class.java)
    }
}
