package egovframework.let.uss.sam.stp.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.let.uss.sam.stp.service.EgovStplatManageService
import egovframework.let.uss.sam.stp.service.StplatManageDefaultVO
import egovframework.let.uss.sam.stp.service.StplatManageVO
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource

/**
 *
 * 약관내용을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovStplatManageController {
    @Resource(name = "StplatManageService")
    private val stplatManageService: EgovStplatManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    // Validation 관련
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return    "/uss/sam/stp/EgovMain"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/sam/stp/EgovMain.do"])
    @Throws(Exception::class)
    fun EgovMain(model: ModelMap?): String {
        return "/uss/sam/stp/EgovMain"
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return    "/uss/sam/stp/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/sam/stp/EgovLeft.do"])
    @Throws(Exception::class)
    fun EgovLeft(model: ModelMap?): String {
        return "/uss/sam/stp/EgovLeft"
    }

    /**
     * 약관정보 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return    "/uss/sam/stp/EgovStplatListInqire"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/sam/stp/StplatListInqire.do"])
    @Throws(Exception::class)
    fun selectStplatList(@ModelAttribute("searchVO") searchVO: StplatManageDefaultVO, model: ModelMap): String {
        /** EgovPropertyService.SiteList  */

        searchVO.setPageUnit(propertiesService!!.getInt("pageUnit"))
        searchVO.setPageSize(propertiesService!!.getInt("pageSize"))

        /** pageing  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex())
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit())
        paginationInfo.setPageSize(searchVO.getPageSize())

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex())
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex())
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage())

        model.addAttribute("resultList", stplatManageService!!.selectStplatList(searchVO))

        val totCnt = stplatManageService.selectStplatListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/sam/stp/EgovStplatListInqire"
    }

    /**
     * 약관정보상세내용을 조회한다.
     * @param stplatManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/sam/stp/EgovStplatDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatDetailInqire.do")
    @Throws(Exception::class)
    fun selectStplatDetail(
        stplatManageVO: StplatManageVO?,
        @ModelAttribute("searchVO") searchVO: StplatManageDefaultVO?,
        model: ModelMap
    ): String {
        val vo = stplatManageService!!.selectStplatDetail(stplatManageVO)

        model.addAttribute("result", vo)

        return "/uss/sam/stp/EgovStplatDetailInqire"
    }

    /**
     * 약관정보를 등록하기 위한 전 처리
     * @param searchVO
     * @param model
     * @return    "/uss/sam/stp/EgovStplatCnRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnRegistView.do")
    @Throws(Exception::class)
    fun insertStplatCnView(@ModelAttribute("searchVO") searchVO: StplatManageDefaultVO?, model: Model): String {
        model.addAttribute("stplatManageVO", StplatManageVO())

        return "/uss/sam/stp/EgovStplatCnRegist"
    }

    /**
     * 약관정보를 등록한다.
     * @param searchVO
     * @param stplatManageVO
     * @param bindingResult
     * @return    "forward:/uss/sam/stp/StplatListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnRegist.do")
    @Throws(Exception::class)
    fun insertStplatCn(
        @ModelAttribute("searchVO") searchVO: StplatManageDefaultVO?,
        @ModelAttribute("stplatManageVO") stplatManageVO: StplatManageVO,
        bindingResult: BindingResult
    ): String {
        beanValidator!!.validate(stplatManageVO, bindingResult)

        if (bindingResult.hasErrors()) {
            return "/uss/olh/wor/EgovStplatCnRegist"
        }

        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val frstRegisterId = loginVO.uniqId

        stplatManageVO.setFrstRegisterId(frstRegisterId) // 최초등록자ID
        stplatManageVO.setLastUpdusrId(frstRegisterId) // 최종수정자ID

        stplatManageService!!.insertStplatCn(stplatManageVO)

        return "forward:/uss/sam/stp/StplatListInqire.do"
    }

    /**
     * 약관정보를 수정하기 위한 전 처리
     * @param useStplatId
     * @param searchVO
     * @param model
     * @return    "/uss/sam/stp/EgovStplatCnUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnUpdtView.do")
    @Throws(Exception::class)
    fun updateStplatCnView(
        @RequestParam("useStplatId") useStplatId: String?,
        @ModelAttribute("searchVO") searchVO: StplatManageDefaultVO?,
        model: ModelMap
    ): String {
        val stplatManageVO = StplatManageVO()

        // Primary Key 값 세팅
        stplatManageVO.setUseStplatId(useStplatId)

        // 변수명은 CoC 에 따라
        model.addAttribute(selectStplatDetail(stplatManageVO, searchVO, model))

        // 변수명은 CoC 에 따라 JSTL사용을 위해
        model.addAttribute("stplatManageVO", stplatManageService!!.selectStplatDetail(stplatManageVO))

        return "/uss/sam/stp/EgovStplatCnUpdt"
    }

    /**
     * 약관정보를 수정 처리한다.
     * @param searchVO
     * @param stplatManageVO
     * @param bindingResult
     * @return    "forward:/uss/sam/stp/StplatListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnUpdt.do")
    @Throws(Exception::class)
    fun updateStplatCn(
        @ModelAttribute("searchVO") searchVO: StplatManageDefaultVO?,
        @ModelAttribute("stplatManageVO") stplatManageVO: StplatManageVO,
        bindingResult: BindingResult
    ): String {
        // Validation

        beanValidator!!.validate(stplatManageVO, bindingResult)

        if (bindingResult.hasErrors()) {
            return "/uss/olh/wor/EgovStplatCnUpdt"
        }

        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val lastUpdusrId = loginVO.uniqId

        stplatManageVO.setLastUpdusrId(lastUpdusrId) // 최종수정자ID

        stplatManageService!!.updateStplatCn(stplatManageVO)

        return "forward:/uss/sam/stp/StplatListInqire.do"
    }

    /**
     * 약관정보를 삭제 처리한다.
     * @param stplatManageVO
     * @param searchVO
     * @return    "forward:/uss/sam/stp/StplatListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnDelete.do")
    @Throws(Exception::class)
    fun deleteStplatCn(
        stplatManageVO: StplatManageVO?,
        @ModelAttribute("searchVO") searchVO: StplatManageDefaultVO?
    ): String {
        stplatManageService!!.deleteStplatCn(stplatManageVO)

        return "forward:/uss/sam/stp/StplatListInqire.do"
    }
}
