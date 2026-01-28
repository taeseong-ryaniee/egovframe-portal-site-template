package egovframework.let.uss.olp.qtm.web

import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.let.uss.olp.qtm.service.EgovQustnrTmplatManageService
import egovframework.let.uss.olp.qtm.service.QustnrTmplatManageVO
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
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 설문템플릿 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQustnrTmplatManageController {
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovQustnrTmplatManageService")
    private val egovQustnrTmplatManageService: EgovQustnrTmplatManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageMain.do"])
    @Throws(Exception::class)
    fun EgovQustnrTmplatManageMain(model: ModelMap?): String {
        return "/uss/olp/qtm/EgovQustnrTmplatManageMain"
    }

    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageLeft.do"])
    @Throws(Exception::class)
    fun EgovQustnrTmplatManageLeft(model: ModelMap?): String {
        return "/uss/olp/qtm/EgovQustnrTmplatManageLeft"
    }

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return    "/uss/sam/cpy/"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/EgovMain.do"])
    @Throws(Exception::class)
    fun EgovMain(model: ModelMap?): String {
        return "/uss/olp/qtm/EgovMain"
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return    "/uss/sam/cpy/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/EgovLeft.do"])
    @Throws(Exception::class)
    fun EgovLeft(model: ModelMap?): String {
        return "/uss/olp/qtm/EgovLeft"
    }

    /**
     * 설문템플릿 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrTmplatManageVO
     * @param model
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageList"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageList.do"])
    @Throws(Exception::class)
    fun EgovQustnrTmplatManageList(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrTmplatManageVO: QustnrTmplatManageVO?,
        model: ModelMap
    ): String {
        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrTmplatManageService!!.deleteQustnrTmplatManage(qustnrTmplatManageVO)
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

        model.addAttribute("resultList", egovQustnrTmplatManageService!!.selectQustnrTmplatManageList(searchVO))

        model.addAttribute(
            "searchKeyword",
            if (commandMap.get("searchKeyword") == null) "" else commandMap.get("searchKeyword") as String?
        )
        model.addAttribute(
            "searchCondition",
            if (commandMap.get("searchCondition") == null) "" else commandMap.get("searchCondition") as String?
        )

        val totCnt = egovQustnrTmplatManageService.selectQustnrTmplatManageListCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olp/qtm/EgovQustnrTmplatManageList"
    }

    /**
     * 설문템플릿 목록을 상세조회 조회한다.
     * @param request
     * @param response
     * @param qustnrTmplatManageVO
     * @param commandMap
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageImg"
     * @throws Exception
     */
    @Suppress("unused")
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageImg.do"])
    @Throws(Exception::class)
    fun EgovQustnrTmplatManageImg(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        qustnrTmplatManageVO: QustnrTmplatManageVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>?
    ) {
        val mapResult = egovQustnrTmplatManageService!!.selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO)
        val img = mapResult!!.get("QUSTNR_TMPLAT_IMAGE_INFOPATHNM") as ByteArray
        val imgtype = "jpeg"
        var type = ""

        if (imgtype != null && "" != imgtype) {
            type = "image/" + imgtype
        }

        response.setHeader("Content-Type", imgtype)
        response.setHeader("Content-Length", "" + img.size)
        response.getOutputStream().write(img)
        response.getOutputStream().flush()
        response.getOutputStream().close()
    }

    /**
     * 설문템플릿 목록을 상세조회 조회한다.
     * @param searchVO
     * @param qustnrTmplatManageVO
     * @param commandMap
     * @param model
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageDetail.do"])
    @Throws(Exception::class)
    fun EgovQustnrTmplatManageDetail(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrTmplatManageVO: QustnrTmplatManageVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        var sLocationUrl = "/uss/olp/qtm/EgovQustnrTmplatManageDetail"

        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!

        if (sCmd == "del") {
            egovQustnrTmplatManageService!!.deleteQustnrTmplatManage(qustnrTmplatManageVO)
            sLocationUrl = "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do"
        } else {
            model.addAttribute(
                "resultList",
                egovQustnrTmplatManageService!!.selectQustnrTmplatManageDetail(qustnrTmplatManageVO)
            )
        }

        return sLocationUrl
    }

    /**
     * 설문템플릿를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param qustnrTmplatManageVO
     * @param model
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageModify"
     * @throws Exception
     */
    @Suppress("unused")
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageModify.do"])
    @Throws(Exception::class)
    fun QustnrTmplatManageModify(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        qustnrTmplatManageVO: QustnrTmplatManageVO?,
        model: ModelMap
    ): String {
        val sLocationUrl = "/uss/olp/qtm/EgovQustnrTmplatManageModify"

        val sCmd = if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as String?

        model.addAttribute(
            "resultList",
            egovQustnrTmplatManageService!!.selectQustnrTmplatManageDetail(qustnrTmplatManageVO)
        )

        return sLocationUrl
    }

    /**
     * 설문템플릿를 수정처리 한다.
     * @param multiRequest
     * @param searchVO
     * @param commandMap
     * @param qustnrTmplatManageVO
     * @param bindingResult
     * @param model
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageModifyActor"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageModifyActor.do"])
    @Throws(Exception::class)
    fun QustnrTmplatManageModifyActor(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>?,
        @ModelAttribute("qustnrTmplatManageVO") qustnrTmplatManageVO: QustnrTmplatManageVO,
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

        //서버  validate 체크
        beanValidator!!.validate(qustnrTmplatManageVO, bindingResult)
        if (bindingResult.hasErrors()) {
            model.addAttribute(
                "resultList",
                egovQustnrTmplatManageService!!.selectQustnrTmplatManageDetail(qustnrTmplatManageVO)
            )
            return "/uss/olp/qtm/EgovQustnrTmplatManageModify"
        }

        //아이디 설정
        qustnrTmplatManageVO.frstRegisterId = loginVO.uniqId
        qustnrTmplatManageVO.lastUpdusrId = loginVO.uniqId

        val files = multiRequest.getFileMap()

        if (!files.isEmpty()) {
            for (file in files.values) {
                println("getName =>" + file.getName())
                println("getOriginalFilename =>" + file.getOriginalFilename())
                if (file.getName() == "qestnrTmplatImage" && file.getOriginalFilename() != "") {
                    qustnrTmplatManageVO.setQestnrTmplatImagepathnm(file.getBytes())
                }
            }
        }
        egovQustnrTmplatManageService!!.updateQustnrTmplatManage(qustnrTmplatManageVO)

        return "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do"
    }

    /**
     * 설문템플릿를 등록한다. / 초기등록페이지
     * @param searchVO
     * @param commandMap
     * @param qustnrTmplatManageVO
     * @param model
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageRegist.do"])
    @Throws(Exception::class)
    fun QustnrTmplatManageRegist(
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        @RequestParam commandMap: MutableMap<String?, Any?>?,
        @ModelAttribute("qustnrTmplatManageVO") qustnrTmplatManageVO: QustnrTmplatManageVO,
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

        val sLocationUrl = "/uss/olp/qtm/EgovQustnrTmplatManageRegist"

        //아이디 설정
        qustnrTmplatManageVO.frstRegisterId = loginVO.uniqId
        qustnrTmplatManageVO.lastUpdusrId = loginVO.uniqId

        return sLocationUrl
    }

    /**
     * 설문템플릿를 등록 처리 한다.  / 등록처리
     * @param multiRequest
     * @param searchVO
     * @param qustnrTmplatManageVO
     * @param model
     * @return "/uss/olp/qtm/EgovQustnrTmplatManageRegistActor"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olp/qtm/EgovQustnrTmplatManageRegistActor.do"])
    @Throws(Exception::class)
    fun QustnrTmplatManageRegistActor(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") searchVO: ComDefaultVO?,
        qustnrTmplatManageVO: QustnrTmplatManageVO,
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

        //아이디 설정
        qustnrTmplatManageVO.frstRegisterId = loginVO.uniqId
        qustnrTmplatManageVO.lastUpdusrId = loginVO.uniqId

        val files = multiRequest.getFileMap()

        if (!files.isEmpty()) {
            for (file in files.values) {
                if (file.getName() == "qestnrTmplatImage") {
                    qustnrTmplatManageVO.setQestnrTmplatImagepathnm(file.getBytes())
                }
            }
        }

        egovQustnrTmplatManageService!!.insertQustnrTmplatManage(qustnrTmplatManageVO)

        return "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do"
    }
}
