package egovframework.let.cop.bbs.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService
import egovframework.let.utl.fcc.service.EgovStringUtil
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException
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
 * 게시판 속성관리를 위한 컨트롤러  클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovBBSAttributeManageController {
    @Resource(name = "EgovBBSAttributeManageService")
    private val bbsAttrbService: EgovBBSAttributeManageService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
     *
     * @param boardMaster
     * @throws EgovBizException
     */
    @Throws(Exception::class)
    protected fun checkAuthority(boardMaster: BoardMaster?) {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        if (user == null) {
            throw EgovBizException("인증된 사용자 정보가 존재하지 않습니다.")
        }
    }

    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/addBBSMaster.do")
    @Throws(Exception::class)
    fun addBBSMaster(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?, model: ModelMap): String {
        val boardMaster = BoardMaster()

        val vo = ComDefaultCodeVO()

        vo.codeId = "COM004"

        var codeResult: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(vo)

        model.addAttribute("typeList", codeResult)

        vo.codeId = "COM009"

        codeResult = cmmUseService.selectCmmCodeDetail(vo)

        model.addAttribute("attrbList", codeResult)
        model.addAttribute("boardMaster", boardMaster)

        val flag = propertyService!!.getString("Globals.addedOptions")
        if (flag != null && flag.trim { it <= ' ' }.equals("true", ignoreCase = true)) {
            model.addAttribute("addedOptions", "true")
        }

        return "cop/bbs/EgovBoardMstrRegist"
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
    @RequestMapping("/cop/bbs/insertBBSMasterInf.do")
    @Throws(Exception::class)
    fun insertBBSMasterInf(
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

            return "cop/bbs/EgovBoardMstrRegist"
        }

        if (isAuthenticated) {
            boardMaster.frstRegisterId = user.uniqId
            boardMaster.useAt = "Y"
            boardMaster.trgetId = "SYSTEMDEFAULT_REGIST"
            boardMaster.posblAtchFileSize = propertyService!!.getString("posblAtchFileSize")

            bbsAttrbService!!.insertBBSMastetInf(boardMaster)
        }

        return "forward:/cop/bbs/SelectBBSMasterInfs.do"
    }

    /**
     * 게시판 마스터 목록을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/SelectBBSMasterInfs.do")
    @Throws(Exception::class)
    fun selectBBSMasterInfs(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO, model: ModelMap): String {
        boardMasterVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardMasterVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardMasterVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardMasterVO.pageUnit)
        paginationInfo.setPageSize(boardMasterVO.pageSize)

        boardMasterVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardMasterVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardMasterVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsAttrbService!!.selectBBSMasterInfs(boardMasterVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/bbs/EgovBoardMstrList"
    }

    /**
     * 게시판 마스터 상세내용을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/SelectBBSMasterInf.do")
    @Throws(Exception::class)
    fun selectBBSMasterInf(@ModelAttribute("searchVO") searchVO: BoardMasterVO?, model: ModelMap): String {
        val vo = bbsAttrbService!!.selectBBSMasterInf(searchVO)

        model.addAttribute("result", vo)

        val flag = propertyService!!.getString("Globals.addedOptions")
        if (flag != null && flag.trim { it <= ' ' }.equals("true", ignoreCase = true)) {
            model.addAttribute("addedOptions", "true")
        }

        /**/-------------------------------* /
                return "cop/bbs/EgovBoardMstrUpdt"
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
    @RequestMapping("/cop/bbs/UpdateBBSMasterInf.do")
    @Throws(Exception::class)
    fun updateBBSMasterInf(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(boardMaster, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = bbsAttrbService!!.selectBBSMasterInf(boardMasterVO)

            model.addAttribute("result", vo)

            return "cop/bbs/EgovBoardMstrUpdt"
        }

        if (isAuthenticated) {
            boardMaster.lastUpdusrId = user.uniqId
            boardMaster.posblAtchFileSize = propertyService!!.getString("posblAtchFileSize")
            bbsAttrbService!!.updateBBSMasterInf(boardMaster)
        }

        return "forward:/cop/bbs/SelectBBSMasterInfs.do"
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
    @RequestMapping("/cop/bbs/DeleteBBSMasterInf.do")
    @Throws(Exception::class)
    fun deleteBBSMasterInf(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        status: SessionStatus?
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            boardMaster.lastUpdusrId = user.uniqId
            bbsAttrbService!!.deleteBBSMasterInf(boardMaster)
        }
        return "forward:/cop/bbs/SelectBBSMasterInfs.do"
    }

    /**
     * 게시판 마스터 선택 팝업을 위한 목록을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/SelectBBSMasterInfsPop.do")
    @Throws(Exception::class)
    fun selectBBSMasterInfsPop(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO, model: ModelMap): String {
        boardMasterVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardMasterVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardMasterVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardMasterVO.pageUnit)
        paginationInfo.setPageSize(boardMasterVO.pageSize)

        boardMasterVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardMasterVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardMasterVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        boardMasterVO.useAt = "Y"

        val map = bbsAttrbService!!.selectNotUsedBdMstrList(boardMasterVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/bbs/EgovBoardMstrListPop"
    }

    /**
     * 게시판 사용을 위한 신규 게시판 속성정보를 생성한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param bindingResult
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBdMstrByTrget.do")
    @Throws(Exception::class)
    fun insertBdMstrByTrget(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        bindingResult: BindingResult,
        status: SessionStatus?,
        model: ModelMap
    ): String {
        checkAuthority(boardMasterVO) // server-side 권한 확인

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

            return "cop/bbs/EgovBdMstrRegistByTrget"
        }

        boardMaster.frstRegisterId = user.uniqId
        boardMaster.useAt = "Y"
        boardMaster.bbsUseFlag = "Y"

        var registSeCode = "REGC06"

        if ("CLB" == EgovStringUtil.cutString(boardMaster.trgetId, 3)) {
            registSeCode = "REGC05"
        }
        boardMaster.registSeCode = registSeCode

        if (isAuthenticated) {
            bbsAttrbService!!.insertBBSMastetInf(boardMaster)
            model.addAttribute("S_FLAG", "S")
        }

        return "forward:/cop/bbs/selectBdMstrListByTrget.do"
    }

    /**
     * 사용중인 게시판 속성 정보의 목록을 조회 한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBdMstrListByTrget.do")
    @Throws(Exception::class)
    fun selectBdMstrListByTrget(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO, model: ModelMap): String {
        checkAuthority(boardMasterVO) // server-side 권한 확인

        boardMasterVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardMasterVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardMasterVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardMasterVO.pageUnit)
        paginationInfo.setPageSize(boardMasterVO.pageSize)

        boardMasterVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardMasterVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardMasterVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsAttrbService!!.selectBdMstrListByTrget(boardMasterVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()
        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("paginationInfo", paginationInfo)
        model.addAttribute("trgetId", boardMasterVO.trgetId)

        return "cop/bbs/EgovBBSListByTrget"
    }

    /**
     * 게시판 사용을 위한 게시판 속성정보 한 건을 상세조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/SelectBBSMasterInfByTrget.do")
    @Throws(Exception::class)
    fun selectBBSMasterInfByTrget(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO,
        @RequestParam commandMap: MutableMap<String?, Any?>?,
        model: ModelMap
    ): String {
        checkAuthority(boardMasterVO) // server-side 권한 확인

        val vo = bbsAttrbService!!.selectBBSMasterInf(boardMasterVO)

        vo!!.trgetId = boardMasterVO.trgetId

        model.addAttribute("result", vo)

        val flag = propertyService!!.getString("Globals.addedOptions")
        if (flag != null && flag.trim { it <= ' ' }.equals("true", ignoreCase = true)) {
            model.addAttribute("addedOptions", "true")
        }

        return "cop/bbs/EgovBdMstrUpdtByTrget"
    }

    /**
     * 게시판 사용을 위한 게시판 속성정보를 수정한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/UpdateBBSMasterInfByTrget.do")
    @Throws(Exception::class)
    fun updateBBSMasterInfByTrget(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        bindingResult: BindingResult,
        model: ModelMap
    ): String {
        checkAuthority(boardMasterVO) // server-side 권한 확인

        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(boardMaster, bindingResult)
        if (bindingResult.hasErrors()) {
            var vo: BoardMasterVO? = BoardMasterVO()

            vo = bbsAttrbService!!.selectBBSMasterInf(boardMasterVO)

            model.addAttribute("result", vo)

            return "cop/bbs/EgovBoardMstrUpdt"
        }

        boardMaster.lastUpdusrId = user.uniqId
        boardMaster.useAt = "Y"

        if (isAuthenticated) {
            bbsAttrbService!!.updateBBSMasterInf(boardMaster)
        }

        return "forward:/cop/bbs/selectBdMstrListByTrget.do"
    }

    /**
     * 커뮤니티, 동호회에서 사용을 위한 게시판 마스터 등록 화면으로 이동한다.
     *
     * @param boardMasterVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/addBBSMasterByTrget.do")
    @Throws(Exception::class)
    fun addBBSMasterByTrget(@ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?, model: ModelMap): String {
        checkAuthority(boardMasterVO) // server-side 권한 확인

        val vo = ComDefaultCodeVO()

        vo.codeId = "COM004"

        var codeResult: MutableList<*>? = cmmUseService!!.selectCmmCodeDetail(vo)

        model.addAttribute("typeList", codeResult)

        vo.codeId = "COM009"

        codeResult = cmmUseService.selectCmmCodeDetail(vo)

        model.addAttribute("attrbList", codeResult)

        val boardMaster = BoardMaster()

        model.addAttribute("boardMaster", boardMaster)

        val flag = propertyService!!.getString("Globals.addedOptions")
        if (flag != null && flag.trim { it <= ' ' }.equals("true", ignoreCase = true)) {
            model.addAttribute("addedOptions", "true")
        }

        return "cop/bbs/EgovBdMstrRegistByTrget"
    }

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param sessionVO
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/DeleteBBSMasterInfByTrget.do")
    @Throws(Exception::class)
    fun deleteBBSMasterInfByTrget(
        @ModelAttribute("searchVO") boardMasterVO: BoardMasterVO?,
        @ModelAttribute("boardMaster") boardMaster: BoardMaster,
        status: SessionStatus?
    ): String {
        checkAuthority(boardMasterVO) // server-side 권한 확인

        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        boardMaster.lastUpdusrId = user.uniqId

        if (isAuthenticated) {
            bbsAttrbService!!.deleteBBSMasterInf(boardMaster)
        }

        return "forward:/cop/bbs/selectBdMstrListByTrget.do"
    }

    /**
     * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록 조회한다.
     *
     * @param commandMap
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectAllBdMstrByTrget.do")
    @Throws(Exception::class)
    fun selectAllBdMstrByTrget(@RequestParam commandMap: MutableMap<String?, Any?>, model: ModelMap): String {
        val trgetId = commandMap.get("param_trgetId") as String?
        val vo = BoardMasterVO()

        vo.trgetId = trgetId

        val result = bbsAttrbService!!.selectAllBdMstrByTrget(vo)

        model.addAttribute("resultList", result)

        return "cop/bbs/EgovBdListPortlet"
    }
}
