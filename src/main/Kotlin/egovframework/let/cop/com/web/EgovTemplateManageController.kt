package egovframework.let.cop.com.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.cop.com.service.EgovTemplateManageService
import egovframework.let.cop.com.service.TemplateInf
import egovframework.let.cop.com.service.TemplateInfVO
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
import org.springframework.web.bind.support.SessionStatus
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource

/**
 * 템플릿 관리를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.03.18
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovTemplateManageController {
    @Resource(name = "EgovTemplateManageService")
    private val tmplatService: EgovTemplateManageService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    //Logger log = Logger.getLogger(this.getClass());
    /**
     * 템플릿 목록을 조회한다.
     *
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectTemplateInfs.do")
    @Throws(Exception::class)
    fun selectTemplateInfs(@ModelAttribute("searchVO") tmplatInfVO: TemplateInfVO, model: ModelMap): String {
        tmplatInfVO.pageUnit = propertyService!!.getInt("pageUnit")
        tmplatInfVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(tmplatInfVO.pageIndex)
        paginationInfo.setRecordCountPerPage(tmplatInfVO.pageUnit)
        paginationInfo.setPageSize(tmplatInfVO.pageSize)

        tmplatInfVO.firstIndex = paginationInfo.getFirstRecordIndex()
        tmplatInfVO.lastIndex = paginationInfo.getLastRecordIndex()
        tmplatInfVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = tmplatService!!.selectTemplateInfs(tmplatInfVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/com/EgovTemplateList"
    }

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     *
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectTemplateInf.do")
    @Throws(Exception::class)
    fun selectTemplateInf(@ModelAttribute("searchVO") tmplatInfVO: TemplateInfVO?, model: ModelMap): String {
        val codeVO = ComDefaultCodeVO()

        codeVO.codeId = "COM005"
        val result: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(codeVO)

        val vo = tmplatService!!.selectTemplateInf(tmplatInfVO)

        model.addAttribute("TemplateInfVO", vo)
        model.addAttribute("resultList", result)

        return "cop/com/EgovTemplateUpdt"
    }

    /**
     * 템플릿 정보를 등록한다.
     *
     * @param searchVO
     * @param tmplatInfo
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/insertTemplateInf.do")
    @Throws(Exception::class)
    fun insertTemplateInf(
        @ModelAttribute("searchVO") searchVO: TemplateInfVO?,
        @ModelAttribute("templateInf") templateInf: TemplateInf,
        bindingResult: BindingResult,
        status: SessionStatus?,
        model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(templateInf, bindingResult)

        if (bindingResult.hasErrors()) {
            val vo = ComDefaultCodeVO()

            vo.codeId = "COM005"

            val result: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(vo)

            model.addAttribute("resultList", result)

            return "cop/com/EgovTemplateRegist"
        }

        templateInf.frstRegisterId = user.uniqId

        if (isAuthenticated) {
            tmplatService!!.insertTemplateInf(templateInf)
        }

        return "forward:/cop/com/selectTemplateInfs.do"
    }

    /**
     * 템플릿 등록을 위한 등록페이지로 이동한다.
     *
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/addTemplateInf.do")
    @Throws(Exception::class)
    fun addTemplateInf(@ModelAttribute("searchVO") searchVO: TemplateInfVO?, model: ModelMap): String {
        val vo = ComDefaultCodeVO()

        vo.codeId = "COM005"

        val result: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(vo)

        model.addAttribute("resultList", result)

        return "cop/com/EgovTemplateRegist"
    }

    /**
     * 템플릿 정보를 수정한다.
     *
     * @param searchVO
     * @param tmplatInfo
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/updateTemplateInf.do")
    @Throws(Exception::class)
    fun updateTemplateInf(
        @ModelAttribute("searchVO") tmplatInfVO: TemplateInfVO?,
        @ModelAttribute("templateInf") templateInf: TemplateInf,
        bindingResult: BindingResult,
        status: SessionStatus?,
        model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(templateInf, bindingResult)

        if (bindingResult.hasErrors()) {
            val codeVO = ComDefaultCodeVO()

            codeVO.codeId = "COM005"

            val result: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(codeVO)

            val vo = tmplatService!!.selectTemplateInf(tmplatInfVO)

            model.addAttribute("TemplateInfVO", vo)
            model.addAttribute("resultList", result)

            return "cop/com/EgovTemplateUpdt"
        }

        templateInf.lastUpdusrId = user.uniqId

        if (isAuthenticated) {
            tmplatService!!.updateTemplateInf(templateInf)
        }

        return "forward:/cop/com/selectTemplateInfs.do"
    }

    /**
     * 템플릿 정보를 삭제한다.
     *
     * @param searchVO
     * @param tmplatInfo
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteTemplateInf.do")
    @Throws(Exception::class)
    fun deleteTemplateInf(
        @ModelAttribute("searchVO") searchVO: TemplateInfVO?,
        @ModelAttribute("tmplatInf") tmplatInf: TemplateInf,
        status: SessionStatus?,
        model: ModelMap?
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        tmplatInf.lastUpdusrId = user.uniqId

        if (isAuthenticated) {
            tmplatService!!.deleteTemplateInf(tmplatInf)
        }

        return "forward:/cop/com/selectTemplateInfs.do"
    }

    /**
     * 팝업을 위한 템플릿 목록을 조회한다.
     *
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectTemplateInfsPop.do")
    @Throws(Exception::class)
    fun selectTemplateInfsPop(
        @ModelAttribute("searchVO") tmplatInfVO: TemplateInfVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        val typeFlag = commandMap.get("typeFlag") as String?

        if ("CLB" == typeFlag) {
            tmplatInfVO.typeFlag = typeFlag
            tmplatInfVO.tmplatSeCode = "TMPT03"
        } else if ("CMY" == typeFlag) {
            tmplatInfVO.typeFlag = typeFlag
            tmplatInfVO.tmplatSeCode = "TMPT02"
        } else {
            tmplatInfVO.typeFlag = typeFlag
            tmplatInfVO.tmplatSeCode = "TMPT01"
        }

        tmplatInfVO.pageUnit = propertyService!!.getInt("pageUnit")
        tmplatInfVO.pageSize = propertyService!!.getInt("pageSize")

        //CMY, CLB
        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(tmplatInfVO.pageIndex)
        paginationInfo.setRecordCountPerPage(tmplatInfVO.pageUnit)
        paginationInfo.setPageSize(tmplatInfVO.pageSize)

        tmplatInfVO.firstIndex = paginationInfo.getFirstRecordIndex()
        tmplatInfVO.lastIndex = paginationInfo.getLastRecordIndex()
        tmplatInfVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = tmplatService!!.selectTemplateInfs(tmplatInfVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/com/EgovTemplateInqirePopup"
    }

    /**
     * 팝업 페이지를 호출한다.
     *
     * @param userVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/openPopup.do")
    @Throws(Exception::class)
    fun openPopupWindow(@RequestParam commandMap: MutableMap<String?, Any?>, model: ModelMap): String {
        val requestUrl = commandMap.get("requestUrl") as String?
        val trgetId = commandMap.get("trgetId") as String?
        val width = commandMap.get("width") as String?
        val height = commandMap.get("height") as String?
        val typeFlag = commandMap.get("typeFlag") as String?

        if (trgetId != null && trgetId !== "") {
            if (typeFlag != null && typeFlag !== "") {
                model.addAttribute("requestUrl", requestUrl + "?trgetId=" + trgetId + "&PopFlag=Y&typeFlag=" + typeFlag)
            } else {
                model.addAttribute("requestUrl", requestUrl + "?trgetId=" + trgetId + "&PopFlag=Y")
            }
        } else {
            if (typeFlag != null && typeFlag !== "") {
                model.addAttribute("requestUrl", requestUrl + "?PopFlag=Y&typeFlag=" + typeFlag)
            } else {
                model.addAttribute("requestUrl", requestUrl + "?PopFlag=Y")
            }
        }

        model.addAttribute("width", width)
        model.addAttribute("height", height)

        return "/cop/com/EgovModalPopupFrame"
    }
}
