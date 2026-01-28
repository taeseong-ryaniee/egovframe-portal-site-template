package egovframework.let.cop.bbs.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import egovframework.let.cop.bbs.service.EgovBBSLoneMasterService
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.support.SessionStatus
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource

/**
 * 게시판 속성관리를 위한 컨트롤러  클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.08.25
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovBBSLoneMasterController {
    @Resource(name = "EgovBBSLoneMasterService")
    private val bbsLoneService: EgovBBSLoneMasterService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/addBoardMaster.do")
    @Throws(Exception::class)
    fun addBoardMaster(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?, model: ModelMap): String {
        val boardMaster = BoardMaster()

        val vo = ComDefaultCodeVO()

        vo.codeId = "COM004"

        var codeResult: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(vo)

        model.addAttribute("typeList", codeResult)

        vo.codeId = "COM009"

        codeResult = cmmUseService.selectCmmCodeDetail(vo)

        model.addAttribute("attrbList", codeResult)
        model.addAttribute("boardMaster", boardMaster)

        return "cop/bbs/EgovBBSLoneMstrRegist"
    }

    /**
     * 신규 게시판 마스터 정보를 등록한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBoardMaster.do")
    @Throws(Exception::class)
    fun insertBoardMaster(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        bindingResult: BindingResult,
        status: SessionStatus?,
        model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(boardMaster, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = ComDefaultCodeVO()

            vo.codeId = "COM004"

            var codeResult: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(vo)

            model.addAttribute("typeList", codeResult)

            vo.codeId = "COM009"

            codeResult = cmmUseService.selectCmmCodeDetail(vo)

            model.addAttribute("attrbList", codeResult)

            return "cop/bbs/EgovBBSLoneMstrRegist"
        }

        if (isAuthenticated) {
            boardMaster.frstRegisterId = user.uniqId
            boardMaster.useAt = "Y"
            boardMaster.trgetId = "SYSTEMDEFAULT_REGIST"

            bbsLoneService!!.insertMaster(boardMaster)
        }

        return "forward:/cop/bbs/selectBoardMasterList.do"
    }

    /**
     * 게시판 마스터 목록을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBoardMasterList.do")
    @Throws(Exception::class)
    fun selectBoardMasterList(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO, model: ModelMap): String {
        boardMasterVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardMasterVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardMasterVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardMasterVO.pageUnit)
        paginationInfo.setPageSize(boardMasterVO.pageSize)

        boardMasterVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardMasterVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardMasterVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsLoneService!!.selectMasterList(boardMasterVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/bbs/EgovBBSLoneMstrList"
    }

    /**
     * 게시판 마스터 상세내용을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBoardMaster.do")
    @Throws(Exception::class)
    fun selectBoardMaster(@ModelAttribute("searchVO") searchVO: BoardMasterVO?, model: ModelMap): String {
        val vo = bbsLoneService!!.selectMaster(searchVO)

        model.addAttribute("result", vo)

        model.addAttribute("provdUrl", "/cop/bbs/selectBoardList.do?bbsId=" + vo!!.bbsId)

        return "cop/bbs/EgovBBSLoneMstrUpdt"
    }

    /**
     * 게시판 마스터 정보를 수정한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateBoardMaster.do")
    @Throws(Exception::class)
    fun updateBoardMaster(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(boardMaster, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = bbsLoneService!!.selectMaster(boardMasterVO)

            model.addAttribute("result", vo)

            return "cop/bbs/EgovBBSLoneMstrUpdt"
        }

        if (isAuthenticated) {
            boardMaster.lastUpdusrId = user.uniqId
            bbsLoneService!!.updateMaster(boardMaster)
        }

        return "forward:/cop/bbs/selectBoardMasterList.do"
    }

    /**
     * 게시판 마스터 정보를 삭제한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteBoardMaster.do")
    @Throws(Exception::class)
    fun deleteBoardMaster(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        status: SessionStatus?
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            boardMaster.lastUpdusrId = user.uniqId
            bbsLoneService!!.deleteMaster(boardMaster)
        }
        return "forward:/cop/bbs/selectBoardMasterList.do"
    }
}
