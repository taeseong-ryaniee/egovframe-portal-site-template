package egovframework.let.cop.bbs.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.EgovFileMngUtil
import egovframework.com.cmm.service.FileVO
import egovframework.let.cop.bbs.service.*
import egovframework.let.utl.sim.service.EgovFileScrty
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.servlet.http.HttpServletRequest

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.19
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovBBSManageController {
    @Autowired
    private val bbsMngService: EgovBBSManageService? = null

    @Autowired
    private val bbsAttrbService: EgovBBSAttributeManageService? = null

    @Autowired
    private val fileMngService: EgovFileMngService? = null

    @Autowired
    private val fileUtil: EgovFileMngUtil? = null

    @Autowired
    protected var propertyService: EgovPropertyService? = null

    @Autowired
    var egovMessageSource: EgovMessageSource? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * XSS 방지 처리.
     *
     * @param data
     * @return
     */
    protected fun unscript(data: String?): String {
        if (data == null || data.trim { it <= ' ' } == "") {
            return ""
        }

        var ret: String? = data

        ret = ret!!.replace("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)".toRegex(), "&lt;script")
        ret = ret.replace("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)".toRegex(), "&lt;/script")

        ret = ret.replace("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)".toRegex(), "&lt;object")
        ret = ret.replace("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)".toRegex(), "&lt;/object")

        ret = ret.replace("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)".toRegex(), "&lt;applet")
        ret = ret.replace("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)".toRegex(), "&lt;/applet")

        ret = ret.replace("<(E|e)(M|m)(B|b)(E|e)(D|d)".toRegex(), "&lt;embed")
        ret = ret.replace("</(E|e)(M|m)(B|b)(E|e)(D|d)".toRegex(), "&lt;embed")

        ret = ret.replace("<(F|f)(O|o)(R|r)(M|m)".toRegex(), "&lt;form")
        ret = ret.replace("</(F|f)(O|o)(R|r)(M|m)".toRegex(), "&lt;form")

        return ret
    }

    /**
     * 게시물에 대한 목록을 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cop/bbs/selectBoardList.do")
    @Throws(Exception::class)
    fun selectBoardArticles(
        @ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap,
        request: HttpServletRequest
    ): String {
        println(boardVO.bbsId)
        // 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가
        request.getSession().setAttribute("menuNo", "3000000")

        val user: LoginVO
        if (EgovUserDetailsHelper.isAuthenticated()) {
            user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        } else {
            user = LoginVO()
            user.uniqId = "anonymous"
        }

        boardVO.bbsId = boardVO.bbsId
        boardVO.bbsNm = boardVO.bbsNm

        val vo = BoardMasterVO()

        vo.bbsId = boardVO.bbsId
        vo.uniqId = user.uniqId

        val master = bbsAttrbService!!.selectBBSMasterInf(vo)


        //-------------------------------
        // 방명록이면 방명록 URL로 forward
        //-------------------------------
        if (master!!.bbsTyCode == "BBST04") {
            return "forward:/cop/bbs/selectGuestList.do"
        }

        /**/-----------------------------* /
                boardVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardVO.pageUnit)
        paginationInfo.setPageSize(boardVO.pageSize)

        boardVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsMngService!!.selectBoardArticles(boardVO, vo.bbsAttrbCode)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        //-------------------------------
        // 기본 BBS template 지정
        //-------------------------------
        if (master.tmplatCours == null || master.tmplatCours == "") {
            master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        /**/-----------------------------* /
                model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("boardVO", boardVO)
        model.addAttribute("brdMstrVO", master)
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/bbs/EgovNoticeList"
    }

    /**
     * 게시물에 대한 상세 정보를 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cop/bbs/selectBoardArticle.do")
    @Throws(Exception::class)
    fun selectBoardArticle(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        var user = LoginVO()
        if (EgovUserDetailsHelper.isAuthenticated()) {
            user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        }
        // 조회수 증가 여부 지정
        boardVO.isPlusCount = true

        if (boardVO.subPageIndex != "") {
            boardVO.isPlusCount = false
        }

        boardVO.lastUpdusrId = user.uniqId
        val vo = bbsMngService!!.selectBoardArticle(boardVO)

        model.addAttribute("result", vo)

        model.addAttribute("sessionUniqId", user.uniqId)
        //----------------------------
        // template 처리 (기본 BBS template 지정  포함)
        //----------------------------
        val master = BoardMasterVO()

        master.bbsId = boardVO.bbsId
        master.uniqId = user.uniqId

        val masterVo = bbsAttrbService!!.selectBBSMasterInf(master)

        if (masterVo!!.tmplatCours == null || masterVo.tmplatCours == "") {
            masterVo.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        model.addAttribute("brdMstrVO", masterVo)

        return "cop/bbs/EgovNoticeInqire"
    }

    /**
     * 게시물 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cop/bbs/addBoardArticle.do")
    @Throws(Exception::class)
    fun addBoardArticle(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        var bdMstr: BoardMasterVO? = BoardMasterVO()

        if (isAuthenticated) {
            val vo = BoardMasterVO()
            vo.bbsId = boardVO.bbsId
            vo.uniqId = user.uniqId
            bdMstr = bbsAttrbService!!.selectBBSMasterInf(vo)
            model.addAttribute("bdMstr", bdMstr)
        }

        //----------------------------
        // 기본 BBS template 지정
        //----------------------------
        if (bdMstr!!.tmplatCours == null || bdMstr.tmplatCours == "") {
            bdMstr.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        model.addAttribute("brdMstrVO", bdMstr)

        /**/-----------------------------* /
                return "cop/bbs/EgovNoticeRegist"
    }

    /**
     * 게시물을 등록한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/cop/bbs/insertBoardArticle.do")
    @Throws(Exception::class)
    fun insertBoardArticle(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") boardVO: BoardVO, @ModelAttribute("bdMstr") bdMstr: BoardMaster?,
        @ModelAttribute("board") board: Board, bindingResult: BindingResult, status: SessionStatus?, model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            var master: BoardMasterVO? = BoardMasterVO()
            val vo = BoardMasterVO()

            vo.bbsId = boardVO.bbsId
            vo.uniqId = user.uniqId

            master = bbsAttrbService!!.selectBBSMasterInf(vo)

            model.addAttribute("bdMstr", master)

            //----------------------------
            // 기본 BBS template 지정
            //----------------------------
            if (master!!.tmplatCours == null || master.tmplatCours == "") {
                master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
            }

            model.addAttribute("brdMstrVO", master)

            /**/-----------------------------* /
                    return "cop/bbs/EgovNoticeRegist"
        }

        if (isAuthenticated) {
            var result: MutableList<FileVO?>? = null
            var atchFileId: String? = ""

            val files = multiRequest.getFileMap()
            if (!files.isEmpty()) {
                result = fileUtil!!.parseFileInf(files, "BBS_", 0, "", "")
                atchFileId = fileMngService!!.insertFileInfs(result)
            }
            board.atchFileId = atchFileId
            board.frstRegisterId = user.uniqId
            board.bbsId = board.bbsId

            board.ntcrNm = "" // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
            board.password = "" // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)

            board.nttCn = unscript(board.nttCn) // XSS 방지

            bbsMngService!!.insertBoardArticle(board)
        }

        model.addAttribute("bbsId", boardVO.bbsId)
        model.addAttribute("searchCnd", boardVO.searchCnd)
        model.addAttribute("searchWrd", boardVO.searchWrd)
        model.addAttribute("pageIndex", boardVO.pageIndex)

        return "redirect:/cop/bbs/selectBoardList.do"
    }

    /**
     * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cop/bbs/addReplyBoardArticle.do")
    @Throws(Exception::class)
    fun addReplyBoardArticle(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        var master: BoardMasterVO? = BoardMasterVO()
        val vo = BoardMasterVO()

        vo.bbsId = boardVO.bbsId
        vo.uniqId = user.uniqId

        master = bbsAttrbService!!.selectBBSMasterInf(vo)

        model.addAttribute("bdMstr", master)
        model.addAttribute("result", boardVO)

        //----------------------------
        // 기본 BBS template 지정
        //----------------------------
        if (master!!.tmplatCours == null || master.tmplatCours == "") {
            master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        model.addAttribute("brdMstrVO", master)

        /**/-----------------------------* /
                return "cop/bbs/EgovNoticeReply"
    }

    /**
     * 게시물에 대한 답변을 등록한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/cop/bbs/replyBoardArticle.do")
    @Throws(Exception::class)
    fun replyBoardArticle(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") boardVO: BoardVO, @ModelAttribute("bdMstr") bdMstr: BoardMaster?,
        @ModelAttribute("board") board: Board, bindingResult: BindingResult, model: ModelMap, status: SessionStatus?
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            var master: BoardMasterVO? = BoardMasterVO()
            val vo = BoardMasterVO()

            vo.bbsId = boardVO.bbsId
            vo.uniqId = user.uniqId

            master = bbsAttrbService!!.selectBBSMasterInf(vo)

            model.addAttribute("bdMstr", master)
            model.addAttribute("result", boardVO)

            //----------------------------
            // 기본 BBS template 지정
            //----------------------------
            if (master!!.tmplatCours == null || master.tmplatCours == "") {
                master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
            }

            model.addAttribute("brdMstrVO", master)

            /**/-----------------------------* /
                    return "cop/bbs/EgovNoticeReply"
        }

        if (isAuthenticated) {
            val files = multiRequest.getFileMap()
            var atchFileId: String? = ""

            if (!files.isEmpty()) {
                val result = fileUtil!!.parseFileInf(files, "BBS_", 0, "", "")
                atchFileId = fileMngService!!.insertFileInfs(result)
            }

            board.atchFileId = atchFileId
            board.replyAt = "Y"
            board.frstRegisterId = user.uniqId
            board.bbsId = board.bbsId
            board.parnts = boardVO.nttId.toString()
            board.sortOrdr = boardVO.getSortOrdr()
            board.replyLc = (boardVO.replyLc!!.toInt() + 1).toString()

            board.ntcrNm = "" // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
            board.password = "" // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)

            board.nttCn = unscript(board.nttCn) // XSS 방지

            bbsMngService!!.insertBoardArticle(board)
        }

        model.addAttribute("bbsId", boardVO.bbsId)
        model.addAttribute("searchCnd", boardVO.searchCnd)
        model.addAttribute("searchWrd", boardVO.searchWrd)
        model.addAttribute("pageIndex", boardVO.pageIndex)

        return "redirect:/cop/bbs/selectBoardList.do"
    }

    /**
     * 게시물 수정을 위한 수정페이지로 이동한다.
     *
     * @param boardVO
     * @param vo
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cop/bbs/forUpdateBoardArticle.do")
    @Throws(Exception::class)
    fun selectBoardArticleForUpdt(
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("board") vo: BoardVO, model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        boardVO.frstRegisterId = user.uniqId

        val master = BoardMaster()
        var bmvo: BoardMasterVO? = BoardMasterVO()
        var bdvo: BoardVO? = BoardVO()

        vo.bbsId = boardVO.bbsId

        master.bbsId = boardVO.bbsId
        master.uniqId = user.uniqId

        if (isAuthenticated) {
            bmvo = bbsAttrbService!!.selectBBSMasterInf(master)
            bdvo = bbsMngService!!.selectBoardArticle(boardVO)
        }

        model.addAttribute("result", bdvo)
        model.addAttribute("bdMstr", bmvo)

        //----------------------------
        // 기본 BBS template 지정
        //----------------------------
        if (bmvo!!.tmplatCours == null || bmvo.tmplatCours == "") {
            bmvo.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        model.addAttribute("brdMstrVO", bmvo)

        /**/-----------------------------* /
                return "cop/bbs/EgovNoticeUpdt"
    }

    /**
     * 게시물에 대한 내용을 수정한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/cop/bbs/updateBoardArticle.do")
    @Throws(Exception::class)
    fun updateBoardArticle(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") boardVO: BoardVO, @ModelAttribute("bdMstr") bdMstr: BoardMaster?,
        @ModelAttribute("board") board: Board, bindingResult: BindingResult, model: ModelMap, status: SessionStatus?
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        var atchFileId = boardVO.atchFileId

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            boardVO.frstRegisterId = user.uniqId

            val master = BoardMaster()
            var bmvo: BoardMasterVO? = BoardMasterVO()
            var bdvo: BoardVO? = BoardVO()

            master.bbsId = boardVO.bbsId
            master.uniqId = user.uniqId

            bmvo = bbsAttrbService!!.selectBBSMasterInf(master)
            bdvo = bbsMngService!!.selectBoardArticle(boardVO)

            model.addAttribute("result", bdvo)
            model.addAttribute("bdMstr", bmvo)

            return "cop/bbs/EgovNoticeUpdt"
        }

        if (isAuthenticated) {
            val files = multiRequest.getFileMap()
            if (!files.isEmpty()) {
                if ("" == atchFileId) {
                    val result = fileUtil!!.parseFileInf(files, "BBS_", 0, atchFileId, "")
                    atchFileId = fileMngService!!.insertFileInfs(result)
                    board.atchFileId = atchFileId
                } else {
                    val fvo = FileVO()
                    fvo.atchFileId = atchFileId
                    val cnt = fileMngService!!.getMaxFileSN(fvo)
                    val _result = fileUtil!!.parseFileInf(files, "BBS_", cnt, atchFileId, "")
                    fileMngService.updateFileInfs(_result)
                }
            }

            board.lastUpdusrId = user.uniqId

            board.ntcrNm = "" // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
            board.password = "" // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)

            board.nttCn = unscript(board.nttCn) // XSS 방지

            bbsMngService!!.updateBoardArticle(board)
        }

        model.addAttribute("bbsId", boardVO.bbsId)
        model.addAttribute("searchCnd", boardVO.searchCnd)
        model.addAttribute("searchWrd", boardVO.searchWrd)
        model.addAttribute("pageIndex", boardVO.pageIndex)

        return "redirect:/cop/bbs/selectBoardList.do"
    }

    /**
     * 게시물에 대한 내용을 삭제한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/cop/bbs/deleteBoardArticle.do")
    @Throws(Exception::class)
    fun deleteBoardArticle(
        @ModelAttribute("searchVO") boardVO: BoardVO, @ModelAttribute("board") board: Board,
        @ModelAttribute("bdMstr") bdMstr: BoardMaster?, model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            board.lastUpdusrId = user.uniqId

            bbsMngService!!.deleteBoardArticle(board)
        }

        model.addAttribute("bbsId", boardVO.bbsId)
        model.addAttribute("searchCnd", boardVO.searchCnd)
        model.addAttribute("searchWrd", boardVO.searchWrd)
        model.addAttribute("pageIndex", boardVO.pageIndex)

        return "redirect:/cop/bbs/selectBoardList.do"
    }

    /**
     * 방명록에 대한 목록을 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectGuestList.do")
    @Throws(Exception::class)
    fun selectGuestList(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        @Suppress("unused") val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        // 수정 및 삭제 기능 제어를 위한 처리
        model.addAttribute("sessionUniqId", user.uniqId)

        val vo = BoardVO()

        vo.bbsId = boardVO.bbsId
        vo.bbsNm = boardVO.bbsNm
        vo.ntcrNm = user.name
        vo.ntcrId = user.uniqId

        val masterVo = BoardMasterVO()

        masterVo.bbsId = vo.bbsId
        masterVo.uniqId = user.uniqId

        val mstrVO = bbsAttrbService!!.selectBBSMasterInf(masterVo)

        vo.pageUnit = propertyService!!.getInt("pageUnit")
        vo.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(vo.pageIndex)
        paginationInfo.setRecordCountPerPage(vo.pageUnit)
        paginationInfo.setPageSize(vo.pageSize)

        vo.firstIndex = paginationInfo.getFirstRecordIndex()
        vo.lastIndex = paginationInfo.getLastRecordIndex()
        vo.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsMngService!!.selectGuestList(vo)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("brdMstrVO", mstrVO)
        model.addAttribute("boardVO", vo)
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/bbs/EgovGuestList"
    }

    /**
     * 방명록 수정을 위한 특정 내용을 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectSingleGuestList.do")
    @Throws(Exception::class)
    fun selectSingleGuestList(
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("brdMstrVO") brdMstrVO: BoardMasterVO?, model: ModelMap
    ): String {
        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        @Suppress("unused") val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        val vo = bbsMngService!!.selectBoardArticle(boardVO)

        boardVO.bbsId = boardVO.bbsId
        boardVO.bbsNm = boardVO.bbsNm
        boardVO.ntcrNm = user.name

        boardVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(boardVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardVO.pageUnit)
        paginationInfo.setPageSize(boardVO.pageSize)

        boardVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsMngService.selectGuestList(boardVO)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("boardVO", vo)
        model.addAttribute("brdMstrVO", brdMstrVO)
        model.addAttribute("paginationInfo", paginationInfo)

        return "cop/bbs/EgovGuestList"
    }

    /**
     * 방명록에 대한 내용을 삭제한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteGuestList.do")
    @Throws(Exception::class)
    fun deleteGuestList(
        @ModelAttribute("searchVO") boardVO: BoardVO?, @ModelAttribute("board") board: Board?,
        model: ModelMap?
    ): String {
        @Suppress("unused") val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO?
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (isAuthenticated) {
            bbsMngService!!.deleteGuestList(boardVO)
        }

        return "forward:/cop/bbs/selectGuestList.do"
    }

    /**
     * 방명록 수정의 위한 목록을 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateGuestList.do")
    @Throws(Exception::class)
    fun updateGuestList(
        @ModelAttribute("searchVO") boardVO: BoardVO, @ModelAttribute("board") board: Board,
        bindingResult: BindingResult, model: ModelMap
    ): String {
        //BBST02, BBST04

        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = BoardVO()

            vo.bbsId = boardVO.bbsId
            vo.bbsNm = boardVO.bbsNm
            vo.ntcrNm = user.name
            vo.ntcrId = user.uniqId

            val masterVo = BoardMasterVO()

            masterVo.bbsId = vo.bbsId
            masterVo.uniqId = user.uniqId

            val mstrVO = bbsAttrbService!!.selectBBSMasterInf(masterVo)

            vo.pageUnit = propertyService!!.getInt("pageUnit")
            vo.pageSize = propertyService!!.getInt("pageSize")

            val paginationInfo = PaginationInfo()
            paginationInfo.setCurrentPageNo(vo.pageIndex)
            paginationInfo.setRecordCountPerPage(vo.pageUnit)
            paginationInfo.setPageSize(vo.pageSize)

            vo.firstIndex = paginationInfo.getFirstRecordIndex()
            vo.lastIndex = paginationInfo.getLastRecordIndex()
            vo.recordCountPerPage = paginationInfo.getRecordCountPerPage()

            val map = bbsMngService!!.selectGuestList(vo)
            val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

            paginationInfo.setTotalRecordCount(totCnt)

            model.addAttribute("resultList", map.get("resultList"))
            model.addAttribute("resultCnt", map.get("resultCnt"))
            model.addAttribute("brdMstrVO", mstrVO)
            model.addAttribute("boardVO", vo)
            model.addAttribute("paginationInfo", paginationInfo)

            return "cop/bbs/EgovGuestList"
        }

        if (isAuthenticated) {
            bbsMngService!!.updateBoardArticle(board)
            boardVO.nttCn = ""
            boardVO.password = ""
            boardVO.ntcrId = ""
            boardVO.nttId = 0
        }

        return "forward:/cop/bbs/selectGuestList.do"
    }

    /**
     * 방명록에 대한 내용을 등록한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertGuestList.do")
    @Throws(Exception::class)
    fun insertGuestList(
        @ModelAttribute("searchVO") boardVO: BoardVO, @ModelAttribute("board") board: Board,
        bindingResult: BindingResult, model: ModelMap
    ): String {
        // 익명으로 등록이 가능한 부분임
        // 무인증이 되려면 별도의 컨트롤러를 하나 더 등록해야함

        val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = BoardVO()

            vo.bbsId = boardVO.bbsId
            vo.bbsNm = boardVO.bbsNm
            vo.ntcrNm = user.name
            vo.ntcrId = user.uniqId

            val masterVo = BoardMasterVO()

            masterVo.bbsId = vo.bbsId
            masterVo.uniqId = user.uniqId

            val mstrVO = bbsAttrbService!!.selectBBSMasterInf(masterVo)

            vo.pageUnit = propertyService!!.getInt("pageUnit")
            vo.pageSize = propertyService!!.getInt("pageSize")

            val paginationInfo = PaginationInfo()
            paginationInfo.setCurrentPageNo(vo.pageIndex)
            paginationInfo.setRecordCountPerPage(vo.pageUnit)
            paginationInfo.setPageSize(vo.pageSize)

            vo.firstIndex = paginationInfo.getFirstRecordIndex()
            vo.lastIndex = paginationInfo.getLastRecordIndex()
            vo.recordCountPerPage = paginationInfo.getRecordCountPerPage()

            val map = bbsMngService!!.selectGuestList(vo)
            val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

            paginationInfo.setTotalRecordCount(totCnt)

            model.addAttribute("resultList", map.get("resultList"))
            model.addAttribute("resultCnt", map.get("resultCnt"))
            model.addAttribute("brdMstrVO", mstrVO)
            model.addAttribute("boardVO", vo)
            model.addAttribute("paginationInfo", paginationInfo)

            return "cop/bbs/EgovGuestList"
        }

        if (isAuthenticated) {
            board.frstRegisterId = user.uniqId

            bbsMngService!!.insertBoardArticle(board)

            boardVO.nttCn = ""
            boardVO.password = ""
            boardVO.ntcrId = ""
            boardVO.nttId = 0
        }

        return "forward:/cop/bbs/selectGuestList.do"
    }

    /**
     * 익명게시물에 대한 목록을 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/selectBoardList.do")
    @Throws(Exception::class)
    fun selectAnonymousBoardArticles(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        boardVO.bbsId = boardVO.bbsId
        boardVO.bbsNm = boardVO.bbsNm

        val vo = BoardMasterVO()

        vo.bbsId = boardVO.bbsId
        vo.uniqId = "ANONYMOUS" // 익명

        val master = bbsAttrbService!!.selectBBSMasterInf(vo)

        //-------------------------------
        // 익명게시판이 아니면.. 원래 게시판 URL로 forward
        //-------------------------------
        if (master!!.bbsTyCode != "BBST02") {
            return "forward:/cop/bbs/selectBoardList.do"
        }

        /**/-----------------------------* /
                boardVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardVO.pageUnit)
        paginationInfo.setPageSize(boardVO.pageSize)

        boardVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsMngService!!.selectBoardArticles(boardVO, vo.bbsAttrbCode)
        val totCnt = (map!!.get("resultCnt") as String?)!!.toInt()

        paginationInfo.setTotalRecordCount(totCnt)

        //-------------------------------
        // 기본 BBS template 지정
        //-------------------------------
        if (master.tmplatCours == null || master.tmplatCours == "") {
            master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        /**/-----------------------------* /
                model.addAttribute("resultList", map.get("resultList"))
        model.addAttribute("resultCnt", map.get("resultCnt"))
        model.addAttribute("boardVO", boardVO)
        model.addAttribute("brdMstrVO", master)
        model.addAttribute("paginationInfo", paginationInfo)

        model.addAttribute("anonymous", "true")

        return "cop/bbs/EgovNoticeList"
    }

    /**
     * 익명게시물 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/addBoardArticle.do")
    @Throws(Exception::class)
    fun addAnonymousBoardArticle(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        val isAuthenticated = true

        var bdMstr: BoardMasterVO? = BoardMasterVO()

        if (isAuthenticated) {
            val vo = BoardMasterVO()
            vo.bbsId = boardVO.bbsId
            vo.uniqId = "ANONYMOUS"

            bdMstr = bbsAttrbService!!.selectBBSMasterInf(vo)
            model.addAttribute("bdMstr", bdMstr)
        }

        //-------------------------------
        // 익명게시판이 아니면.. 원래 게시판 URL로 forward
        //-------------------------------
        if (bdMstr!!.bbsTyCode != "BBST02") {
            return "forward:/cop/bbs/addBoardArticle.do"
        }

        /**/-----------------------------* /

                //----------------------------
                // 기본 BBS template 지정
                //----------------------------
                if (bdMstr.tmplatCours == null || bdMstr.tmplatCours == "") {
                    bdMstr.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
                }

        model.addAttribute("brdMstrVO", bdMstr)

        /**/-----------------------------* /
                model.addAttribute("anonymous", "true")

        return "cop/bbs/EgovNoticeRegist"
    }

    /**
     * 익명게시물을 등록한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/insertBoardArticle.do")
    @Throws(Exception::class)
    fun insertAnonymousBoardArticle(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("bdMstr") bdMstr: BoardMaster,
        @ModelAttribute("board") board: Board,
        bindingResult: BindingResult,
        status: SessionStatus?,
        model: ModelMap
    ): String {
        val isAuthenticated = true

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            var master: BoardMasterVO? = BoardMasterVO()
            val vo = BoardMasterVO()

            vo.bbsId = boardVO.bbsId
            vo.uniqId = "ANONYMOUS"

            master = bbsAttrbService!!.selectBBSMasterInf(vo)

            model.addAttribute("bdMstr", master)

            //-------------------------------
            // 익명게시판이 아니면.. 원래 게시판 URL로 forward
            //-------------------------------
            if (bdMstr.bbsTyCode != "BBST02") {
                return "forward:/cop/bbs/insertBoardArticle.do"
            }

            /**/-----------------------------* /

                    //----------------------------
                    // 기본 BBS template 지정
                    //----------------------------
                    if (master!!.tmplatCours == null || master.tmplatCours == "") {
                        master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
                    }

            model.addAttribute("brdMstrVO", master)

            /**/-----------------------------* /
                    model.addAttribute("anonymous", "true")

            return "cop/bbs/EgovNoticeRegist"
        }

        if (isAuthenticated) {
            var result: MutableList<FileVO?>? = null
            var atchFileId: String? = ""

            val files = multiRequest.getFileMap()
            if (!files.isEmpty()) {
                result = fileUtil!!.parseFileInf(files, "BBS_", 0, "", "")
                atchFileId = fileMngService!!.insertFileInfs(result)
            }
            board.atchFileId = atchFileId
            board.frstRegisterId = "ANONYMOUS"
            board.bbsId = board.bbsId

            // 익명게시판 관련
            board.ntcrNm = board.ntcrNm
            board.password = EgovFileScrty.encryptPassword(board.password, board.bbsId)

            board.nttCn = unscript(board.nttCn) // XSS 방지

            bbsMngService!!.insertBoardArticle(board)
        }

        return "forward:/cop/bbs/anonymous/selectBoardList.do"
    }

    /**
     * 익명게시물에 대한 상세 정보를 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/selectBoardArticle.do")
    @Throws(Exception::class)
    fun selectAnonymousBoardArticle(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        // 조회수 증가 여부 지정

        boardVO.isPlusCount = true

        if (boardVO.subPageIndex != "") {
            boardVO.isPlusCount = false
        }

        boardVO.lastUpdusrId = "ANONYMOUS"
        val vo = bbsMngService!!.selectBoardArticle(boardVO)

        model.addAttribute("result", vo)
        model.addAttribute("sessionUniqId", "ANONYMOUS")

        //----------------------------
        // template 처리 (기본 BBS template 지정  포함)
        //----------------------------
        val master = BoardMasterVO()

        master.bbsId = boardVO.bbsId
        master.uniqId = "ANONYMOUS"

        val masterVo = bbsAttrbService!!.selectBBSMasterInf(master)

        //-------------------------------
        // 익명게시판이 아니면.. 원래 게시판 URL로 forward
        //-------------------------------
        if (masterVo!!.bbsTyCode != "BBST02") {
            return "forward:/cop/bbs/selectBoardArticle.do"
        }

        /**/-----------------------------* /
                if (masterVo.tmplatCours == null || masterVo.tmplatCours == "") {
                    masterVo.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
                }

        model.addAttribute("brdMstrVO", masterVo)

        /**/-----------------------------* /
                model.addAttribute("anonymous", "true")

        return "cop/bbs/EgovNoticeInqire"
    }

    /**
     * 익명게시물에 대한 내용을 삭제한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/deleteBoardArticle.do")
    @Throws(Exception::class)
    fun deleteAnonymousBoardArticle(
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("board") board: Board,
        @ModelAttribute("bdMstr") bdMstr: BoardMaster?,
        model: ModelMap
    ): String {
        val isAuthenticated = true

        //--------------------------------------------------
        // 마스터 정보 얻기
        //--------------------------------------------------
        val master = BoardMasterVO()

        master.bbsId = boardVO.bbsId
        master.uniqId = "ANONYMOUS"

        val masterVo = bbsAttrbService!!.selectBBSMasterInf(master)

        //-------------------------------
        // 익명게시판이 아니면.. 원래 게시판 URL로 forward
        //-------------------------------
        if (masterVo!!.bbsTyCode != "BBST02") {
            return "forward:/cop/bbs/deleteBoardArticle.do"
        }

        /**/-----------------------------* /

        //-------------------------------
        // 패스워드 비교
        //-------------------------------
        val dbpassword = bbsMngService!!.getPasswordInf(board)
        val enpassword = EgovFileScrty.encryptPassword(board.password, board.bbsId)

        if (dbpassword != enpassword) {
            model.addAttribute("msg", egovMessageSource!!.getMessage("cop.password.not.same.msg"))

            return "forward:/cop/bbs/anonymous/selectBoardArticle.do"
        }

        /**/-----------------------------* /
                if (isAuthenticated) {
                    board.lastUpdusrId = "ANONYMOUS"

                    bbsMngService.deleteBoardArticle(board)
                }

        return "forward:/cop/bbs/anonymous/selectBoardList.do"
    }

    /**
     * 익명게시물 수정을 위한 수정페이지로 이동한다.
     *
     * @param boardVO
     * @param vo
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/forUpdateBoardArticle.do")
    @Throws(Exception::class)
    fun selectAnonymousBoardArticleForUpdt(
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("board") vo: BoardVO,
        model: ModelMap
    ): String {
        val isAuthenticated = true

        boardVO.frstRegisterId = "ANONYMOUS"

        val master = BoardMaster()
        var bmvo: BoardMasterVO? = BoardMasterVO()
        var bdvo: BoardVO? = BoardVO()

        vo.bbsId = boardVO.bbsId

        master.bbsId = boardVO.bbsId
        master.uniqId = "ANONYMOUS"

        if (isAuthenticated) {
            bmvo = bbsAttrbService!!.selectBBSMasterInf(master)

            //-------------------------------
            // 익명게시판이 아니면.. 원래 게시판 URL로 forward
            //-------------------------------
            if (bmvo!!.bbsTyCode != "BBST02") {
                return "forward:/cop/bbs/forUpdateBoardArticle.do"
            }

            /**/-----------------------------* /

            //-------------------------------
            // 패스워드 비교
            //-------------------------------
            val dbpassword = bbsMngService!!.getPasswordInf(boardVO)
            val enpassword = EgovFileScrty.encryptPassword(boardVO.password, boardVO.bbsId)

            if (dbpassword != enpassword) {
                model.addAttribute("msg", egovMessageSource!!.getMessage("cop.password.not.same.msg"))

                return "forward:/cop/bbs/anonymous/selectBoardArticle.do"
            }

            /**/-----------------------------* /
                    bdvo = bbsMngService.selectBoardArticle(boardVO)
        }

        model.addAttribute("result", bdvo)
        model.addAttribute("bdMstr", bmvo)

        //----------------------------
        // 기본 BBS template 지정
        //----------------------------
        if (bmvo!!.tmplatCours == null || bmvo.tmplatCours == "") {
            bmvo.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        model.addAttribute("brdMstrVO", bmvo)

        /**/-----------------------------* /
                model.addAttribute("anonymous", "true")

        return "cop/bbs/EgovNoticeUpdt"
    }

    /**
     * 익명게시물에 대한 내용을 수정한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/updateBoardArticle.do")
    @Throws(Exception::class)
    fun updateAnonymousBoardArticle(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("bdMstr") bdMstr: BoardMaster,
        @ModelAttribute("board") board: Board,
        bindingResult: BindingResult,
        model: ModelMap,
        status: SessionStatus?
    ): String {
        val isAuthenticated = true

        var atchFileId = boardVO.atchFileId

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            boardVO.frstRegisterId = "ANONYMOUS"

            val master = BoardMaster()
            var bmvo: BoardMasterVO? = BoardMasterVO()
            var bdvo: BoardVO? = BoardVO()

            master.bbsId = boardVO.bbsId
            master.uniqId = "ANONYMOUS"

            bmvo = bbsAttrbService!!.selectBBSMasterInf(master)

            //-------------------------------
            // 익명게시판이 아니면.. 원래 게시판 URL로 forward
            //-------------------------------
            if (bdMstr.bbsTyCode != "BBST02") {
                return "forward:/cop/bbs/updateBoardArticle.do"
            }

            /**/-----------------------------* /
                    bdvo = bbsMngService!!.selectBoardArticle(boardVO)

            model.addAttribute("result", bdvo)
            model.addAttribute("bdMstr", bmvo)

            model.addAttribute("anonymous", "true")

            return "cop/bbs/EgovNoticeUpdt"
        }

        if (isAuthenticated) {
            val files = multiRequest.getFileMap()
            if (!files.isEmpty()) {
                if ("" == atchFileId) {
                    val result = fileUtil!!.parseFileInf(files, "BBS_", 0, atchFileId, "")
                    atchFileId = fileMngService!!.insertFileInfs(result)
                    board.atchFileId = atchFileId
                } else {
                    val fvo = FileVO()
                    fvo.atchFileId = atchFileId
                    val cnt = fileMngService!!.getMaxFileSN(fvo)
                    val _result = fileUtil!!.parseFileInf(files, "BBS_", cnt, atchFileId, "")
                    fileMngService.updateFileInfs(_result)
                }
            }

            board.lastUpdusrId = "ANONYMOUS"

            // 익명게시판 관련
            board.ntcrNm = board.ntcrNm
            board.password = EgovFileScrty.encryptPassword(board.password, board.bbsId)

            board.nttCn = unscript(board.nttCn) // XSS 방지

            bbsMngService!!.updateBoardArticle(board)
        }

        return "forward:/cop/bbs/anonymous/selectBoardList.do"
    }

    /**
     * 익명게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/addReplyBoardArticle.do")
    @Throws(Exception::class)
    fun addAnonymousReplyBoardArticle(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        var master: BoardMasterVO? = BoardMasterVO()
        val vo = BoardMasterVO()

        vo.bbsId = boardVO.bbsId
        vo.uniqId = "ANONYMOUS"

        master = bbsAttrbService!!.selectBBSMasterInf(vo)

        //-------------------------------
        // 익명게시판이 아니면.. 원래 게시판 URL로 forward
        //-------------------------------
        if (master!!.bbsTyCode != "BBST02") {
            return "forward:/cop/bbs/addReplyBoardArticle.do"
        }

        /**/-----------------------------* /
                model.addAttribute("bdMstr", master)
        model.addAttribute("result", boardVO)

        //----------------------------
        // 기본 BBS template 지정
        //----------------------------
        if (master.tmplatCours == null || master.tmplatCours == "") {
            master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
        }

        model.addAttribute("brdMstrVO", master)

        /**/-----------------------------* /
                model.addAttribute("anonymous", "true")

        return "cop/bbs/EgovNoticeReply"
    }

    /**
     * 익명게시물에 대한 답변을 등록한다.
     *
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/replyBoardArticle.do")
    @Throws(Exception::class)
    fun replyAnonymousBoardArticle(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") boardVO: BoardVO,
        @ModelAttribute("bdMstr") bdMstr: BoardMaster?,
        @ModelAttribute("board") board: Board,
        bindingResult: BindingResult,
        model: ModelMap,
        status: SessionStatus?
    ): String {
        val isAuthenticated = true

        beanValidator!!.validate(board, bindingResult)
        if (bindingResult.hasErrors()) {
            var master: BoardMasterVO? = BoardMasterVO()
            val vo = BoardMasterVO()

            vo.bbsId = boardVO.bbsId
            vo.uniqId = "ANONYMOUS"

            master = bbsAttrbService!!.selectBBSMasterInf(vo)

            //-------------------------------
            // 익명게시판이 아니면.. 원래 게시판 URL로 forward
            //-------------------------------
            if (master!!.bbsTyCode != "BBST02") {
                return "forward:/cop/bbs/replyBoardArticle.do"
            }

            /**/-----------------------------* /
                    model.addAttribute("bdMstr", master)
            model.addAttribute("result", boardVO)

            //----------------------------
            // 기본 BBS template 지정
            //----------------------------
            if (master.tmplatCours == null || master.tmplatCours == "") {
                master.tmplatCours = "/css/egovframework/cop/bbs/egovBaseTemplate.css"
            }

            model.addAttribute("brdMstrVO", master)

            /**/-----------------------------* /
                    model.addAttribute("anonymous", "true")

            return "cop/bbs/EgovNoticeReply"
        }

        if (isAuthenticated) {
            val files = multiRequest.getFileMap()
            var atchFileId: String? = ""

            if (!files.isEmpty()) {
                val result = fileUtil!!.parseFileInf(files, "BBS_", 0, "", "")
                atchFileId = fileMngService!!.insertFileInfs(result)
            }

            board.atchFileId = atchFileId
            board.replyAt = "Y"
            board.frstRegisterId = "ANONYMOUS"
            board.bbsId = board.bbsId
            board.parnts = boardVO.nttId.toString()
            board.sortOrdr = boardVO.getSortOrdr()
            board.replyLc = (boardVO.replyLc!!.toInt() + 1).toString()

            // 익명게시판 관련
            board.ntcrNm = board.ntcrNm
            board.password = EgovFileScrty.encryptPassword(board.password, board.bbsId)

            board.nttCn = unscript(board.nttCn) // XSS 방지

            bbsMngService!!.insertBoardArticle(board)
        }

        return "forward:/cop/bbs/anonymous/selectBoardList.do"
    }

    /**
     * 템플릿에 대한 미리보기용 게시물 목록을 조회한다.
     *
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/previewBoardList.do")
    @Throws(Exception::class)
    fun previewBoardArticles(@ModelAttribute("searchVO") boardVO: BoardVO, model: ModelMap): String {
        val template = boardVO.searchWrd // 템플릿 URL

        val master = BoardMasterVO()

        master.bbsNm = "미리보기 게시판"

        boardVO.pageUnit = propertyService!!.getInt("pageUnit")
        boardVO.pageSize = propertyService!!.getInt("pageSize")

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardVO.pageUnit)
        paginationInfo.setPageSize(boardVO.pageSize)

        boardVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        var target: BoardVO? = null
        val list: MutableList<BoardVO?> = ArrayList<BoardVO?>()

        target = BoardVO()
        target.nttSj = "게시판 기능 설명"
        target.frstRegisterId = "ID"
        target.frstRegisterNm = "관리자"
        target.frstRegisterPnttm = "2009-01-01"
        target.inqireCo = 7
        target.parnts = "0"
        target.replyAt = "N"
        target.replyLc = "0"
        target.useAt = "Y"

        list.add(target)

        target = BoardVO()
        target.nttSj = "게시판 부가 기능 설명"
        target.frstRegisterId = "ID"
        target.frstRegisterNm = "관리자"
        target.frstRegisterPnttm = "2009-01-01"
        target.inqireCo = 7
        target.parnts = "0"
        target.replyAt = "N"
        target.replyLc = "0"
        target.useAt = "Y"

        list.add(target)

        boardVO.searchWrd = ""

        val totCnt = list.size

        paginationInfo.setTotalRecordCount(totCnt)

        master.tmplatCours = template

        model.addAttribute("resultList", list)
        model.addAttribute("resultCnt", totCnt.toString())
        model.addAttribute("boardVO", boardVO)
        model.addAttribute("brdMstrVO", master)
        model.addAttribute("paginationInfo", paginationInfo)

        model.addAttribute("preview", "true")

        return "cop/bbs/EgovNoticeList"
    }
}
