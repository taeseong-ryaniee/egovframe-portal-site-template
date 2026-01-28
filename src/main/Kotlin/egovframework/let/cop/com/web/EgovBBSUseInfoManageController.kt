package egovframework.let.cop.com.web

import egovframework.com.cmm.LoginVO
import egovframework.let.cop.com.service.BoardUseInf
import egovframework.let.cop.com.service.BoardUseInfVO
import egovframework.let.cop.com.service.EgovBBSUseInfoManageService
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
import javax.servlet.http.HttpServletRequest

//SHT-CUSTOMIZING//import egovframework.let.cop.clb.service.ClubUser;
//SHT-CUSTOMIZING//import egovframework.let.cop.clb.service.EgovClubManageService;
//SHT-CUSTOMIZING//import egovframework.let.cop.cmy.service.CommunityUser;
//SHT-CUSTOMIZING//import egovframework.let.cop.cmy.service.EgovCommunityManageService;
//import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
/**
 * 게시판의 이용정보를 관리하기 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovBBSUseInfoManageController {
    @Resource(name = "EgovBBSUseInfoManageService")
    private val bbsUseService: EgovBBSUseInfoManageService? = null

    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 게시판 사용 정보를 삭제한다.
     *
     * @param bdUseVO
     * @param bdUseInf
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/deleteBBSUseInf.do")
    @Throws(Exception::class)
    fun deleteBBSUseInf(
        @ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?,
        @ModelAttribute("bdUseInf") bdUseInf: BoardUseInf?,
        status: SessionStatus?,
        model: ModelMap?
    ): String {
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            bbsUseService!!.deleteBBSUseInf(bdUseInf)
        }

        return "forward:/cop/com/selectBBSUseInfs.do"
    }

    /**
     * 게사판 사용정보 등록을 위한 등록페이지로 이동한다.
     *
     * @param bdUseVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/addBBSUseInf.do")
    @Throws(Exception::class)
    fun addBBSUseInf(@ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?, model: ModelMap?): String {
        return "cop/com/EgovBoardUseInfRegist"
    }

    /**
     * 게시판 사용정보를 등록한다.
     *
     * @param bdUseVO
     * @param bdUseInf
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/insertBBSUseInf.do")
    @Throws(Exception::class)
    fun insertBBSUseInf(
        @ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?,
        @ModelAttribute("boardUseInf") boardUseInf: BoardUseInf,
        bindingResult: BindingResult,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap?
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(boardUseInf, bindingResult)

        if (bindingResult.hasErrors()) {
            return "cop/com/EgovBoardUseInfRegist"
        }

        val trgetType = commandMap.get("param_trgetType") as String?
        var registSeCode = ""

        // CMMNTY 06/CLUB 05/SYSTEM(REGC01)
        if ("CMMNTY" == trgetType) {
            registSeCode = "REGC06"
        } else if ("CLUB" == trgetType) {
            registSeCode = "REGC05"
        } else {
            registSeCode = "REGC01"
        }

        boardUseInf.useAt = "Y"
        boardUseInf.frstRegisterId = user.uniqId
        boardUseInf.registSeCode = registSeCode

        if (isAuthenticated) {
            bbsUseService!!.insertBBSUseInf(boardUseInf)
        }

        return "forward:/cop/com/selectBBSUseInfs.do"
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     *
     * @param bdUseVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectBBSUseInfs.do")
    @Throws(Exception::class)
    fun selectBBSUseInfs(@ModelAttribute("searchVO") bdUseVO: BoardUseInfVO, model: ModelMap): String {
        bdUseVO.pageUnit = propertyService!!.getInt("pageUnit")
        bdUseVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(bdUseVO.pageIndex)
        paginationInfo.setRecordCountPerPage(bdUseVO.pageUnit)
        paginationInfo.setPageSize(bdUseVO.pageSize)

        bdUseVO.firstIndex = paginationInfo.getFirstRecordIndex()
        bdUseVO.lastIndex = paginationInfo.getLastRecordIndex()
        bdUseVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsUseService!!.selectBBSUseInfs(bdUseVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/com/EgovBoardUseInfList"
    }

    /**
     * 게시판 사용정보를 수정한다.
     *
     * @param bdUseVO
     * @param bdUseInf
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/updateBBSUseInf.do")
    @Throws(Exception::class)
    fun updateBBSUseInf(
        @ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?,
        @ModelAttribute("boardUseInf") boardUseInf: BoardUseInf?,
        request: HttpServletRequest?,
        model: ModelMap?
    ): String {
        if (EgovUserDetailsHelper.isAuthenticated()) {
            bbsUseService!!.updateBBSUseInf(boardUseInf)
        }

        return "forward:/cop/com/selectBBSUseInfs.do"
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     *
     * @param bdUseVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectBBSUseInf.do")
    @Throws(Exception::class)
    fun selectBBSUseInf(@ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?, model: ModelMap): String {
        val vo = bbsUseService!!.selectBBSUseInf(bdUseVO)

        // 시스템 사용 게시판의 경우 URL 표시
        if ("SYSTEM_DEFAULT_BOARD" == vo!!.trgetId) {
            if (vo.bbsTyCode == "BBST02") { // 익명게시판
                vo.provdUrl = "/cop/bbs/anonymous/selectBoardList.do?bbsId=" + vo.bbsId
            } else {
                vo.provdUrl = "/cop/bbs/selectBoardList.do?bbsId=" + vo.bbsId
            }
        }

        model.addAttribute("bdUseVO", vo)
        return "cop/com/EgovBoardUseInfInqire"
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     *
     * @param bdUseVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectBBSUseInfsByTrget.do")
    @Throws(Exception::class)
    fun selectBBSUseInfsByTrget(@ModelAttribute("searchVO") bdUseVO: BoardUseInfVO, model: ModelMap): String {
        bdUseVO.pageUnit = propertyService!!.getInt("pageUnit")
        bdUseVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(bdUseVO.pageIndex)
        paginationInfo.setRecordCountPerPage(bdUseVO.pageUnit)
        paginationInfo.setPageSize(bdUseVO.pageSize)

        bdUseVO.firstIndex = paginationInfo.getFirstRecordIndex()
        bdUseVO.lastIndex = paginationInfo.getLastRecordIndex()
        bdUseVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsUseService!!.selectBBSUseInfsByTrget(bdUseVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("trgetId", bdUseVO.trgetId)
        model.addAttribute("trgetType", bdUseVO.trgetType)
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/com/EgovBdUseInfListByTrget"
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     *
     * @param bdUseVO
     * @param boardUseInf
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/updateBBSUseInfByTrget.do")
    @Throws(Exception::class)
    fun updateBBSUseInfByTrget(
        @ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?, @ModelAttribute("boardUseInf") boardUseInf: BoardUseInf,
        @RequestParam commandMap: MutableMap<String?, Any?>, status: SessionStatus?, model: ModelMap?
    ): String {
        val param_trgetId = commandMap.get("param_trgetId") as String?

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            boardUseInf.trgetId = param_trgetId
            bbsUseService!!.updateBBSUseInfByTrget(boardUseInf)
        }

        return "forward:/cop/com/selectBBSUseInfsByTrget.do"
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 등록한다.
     *
     * @param bdUseVO
     * @param boardUseInf
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/insertBBSUseInfByTrget.do")
    @Throws(Exception::class)
    fun insertBBSUseInfByTrget(
        @ModelAttribute("searchVO") bdUseVO: BoardUseInfVO?, @ModelAttribute("boardUseInf") boardUseInf: BoardUseInf,
        @RequestParam commandMap: MutableMap<String?, Any?>, status: SessionStatus?, model: ModelMap?
    ): String {
        val paramTrgetId = commandMap.get("param_trgetId") as String?
        val bbsId = commandMap.get("bbsId") as String?

        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            boardUseInf.useAt = "Y"
            boardUseInf.frstRegisterId = user.uniqId
            boardUseInf.registSeCode = "REGC07"
            boardUseInf.bbsId = bbsId
            boardUseInf.trgetId = paramTrgetId

            bbsUseService!!.insertBBSUseInf(boardUseInf)
        }

        return "forward:/cop/com/selectBBSUseInfsByTrget.do"
    }
}
