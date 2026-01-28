package egovframework.let.main.web

import egovframework.com.cmm.ComDefaultVO
import egovframework.com.cmm.LoginVO
import egovframework.let.cop.bbs.service.BoardVO
import egovframework.let.cop.bbs.service.EgovBBSManageService
import egovframework.let.sym.mnu.mpm.service.EgovMenuManageService
import egovframework.let.sym.mnu.mpm.service.MenuManageVO
import egovframework.let.uss.olh.faq.service.EgovFaqManageService
import egovframework.let.uss.olh.faq.service.FaqManageDefaultVO
import egovframework.let.uss.olp.qri.service.EgovQustnrRespondInfoService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
@SessionAttributes(types = [ComDefaultVO::class])
class EgovMainController {
    /**
     * EgovBBSManageService
     */
    @Resource(name = "EgovBBSManageService")
    private val bbsMngService: EgovBBSManageService? = null

    /** EgovMenuManageService  */
    @Resource(name = "meunManageService")
    private val menuManageService: EgovMenuManageService? = null

    /** FaqManageService  */
    @Resource(name = "FaqManageService")
    private val faqManageService: EgovFaqManageService? = null

    /** egovQustnrRespondInfoService  */
    @Resource(name = "egovQustnrRespondInfoService")
    private val egovQustnrRespondInfoService: EgovQustnrRespondInfoService? = null

    /**
     * 메인 페이지에서 각 업무 화면으로 연계하는 기능을 제공한다.
     *
     * @param request
     * @param commandMap
     * @exception Exception Exception
     */
    @RequestMapping(value = ["/cmm/forwardPage.do"])
    @Throws(Exception::class)
    fun forwardPageWithMenuNo(
        request: HttpServletRequest?,
        @RequestParam commandMap: MutableMap<String?, Any?>?
    ): String {
        return ""
    }

    /**
     * 템플릿 메인 페이지 조회
     * @return 메인페이지 정보 Map [key : 항목명]
     *
     * @param request
     * @param model
     * @exception Exception Exception
     */
    @RequestMapping(value = ["/cmm/main/mainPage.do"])
    @Throws(Exception::class)
    fun getMgtMainPage(request: HttpServletRequest?, model: ModelMap): String {
        // 공지사항 메인 컨텐츠 조회 시작 ---------------------------------

        val boardVO = BoardVO()
        boardVO.useAt = "Y"
        boardVO.pageUnit = 5
        boardVO.pageSize = 10
        boardVO.bbsId = "BBSMSTR_AAAAAAAAAAAA"

        val paginationInfo = PaginationInfo()

        paginationInfo.setCurrentPageNo(boardVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardVO.pageUnit)
        paginationInfo.setPageSize(boardVO.pageSize)

        boardVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        val map = bbsMngService!!.selectBoardArticles(boardVO, "BBSA02")
        model.addAttribute("notiList", map!!.get("resultList"))


        // 공지사항 메인컨텐츠 조회 끝 -----------------------------------

        // 자유게시판 메인 컨텐츠 조회 시작 ---------------------------------
        boardVO.pageUnit = 9
        boardVO.pageSize = 10
        boardVO.bbsId = "BBSMSTR_BBBBBBBBBBBB"

        paginationInfo.setCurrentPageNo(boardVO.pageIndex)
        paginationInfo.setRecordCountPerPage(boardVO.pageUnit)
        paginationInfo.setPageSize(boardVO.pageSize)

        boardVO.firstIndex = paginationInfo.getFirstRecordIndex()
        boardVO.lastIndex = paginationInfo.getLastRecordIndex()
        boardVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        model.addAttribute("bbsList", bbsMngService.selectBoardArticles(boardVO, "BBSA02")!!.get("resultList"))

        // 자유게시판 메인컨텐츠 조회 끝 -----------------------------------

        // FAQ 메인 컨텐츠 조회 시작 ---------------------------------
        /** EgovPropertyService.SiteList  */
        val searchVO = FaqManageDefaultVO()
        searchVO.pageUnit = 3
        searchVO.pageSize = 10

        /** pageing  */
        paginationInfo.setCurrentPageNo(searchVO.pageIndex)
        paginationInfo.setRecordCountPerPage(searchVO.pageUnit)
        paginationInfo.setPageSize(searchVO.pageSize)

        searchVO.firstIndex = paginationInfo.getFirstRecordIndex()
        searchVO.lastIndex = paginationInfo.getLastRecordIndex()
        searchVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        model.addAttribute("faqList", faqManageService!!.selectFaqList(searchVO))

        // FAQ 메인 컨텐츠 조회 끝 -----------------------------------

        // 설문참여 메인 컨텐츠 조회 시작 -----------------------------------
        val qVO = ComDefaultVO()
        qVO.pageUnit = 1
        qVO.pageSize = 10

        /** pageing  */
        paginationInfo.setCurrentPageNo(qVO.pageIndex)
        paginationInfo.setRecordCountPerPage(qVO.pageUnit)
        paginationInfo.setPageSize(qVO.pageSize)

        qVO.firstIndex = paginationInfo.getFirstRecordIndex()
        qVO.lastIndex = paginationInfo.getLastRecordIndex()
        qVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        model.addAttribute("qriList", egovQustnrRespondInfoService!!.selectQustnrRespondInfoManageList(qVO))

        // 설문참여 메인 컨텐츠 조회 끝 -----------------------------------
        return "main/EgovMainView"
    }

    /**
     * Header Page를 조회한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "EgovIncHeader"
     * @exception Exception
     */
    @RequestMapping(value = ["/sym/mms/EgovHeader.do"])
    @Throws(Exception::class)
    fun selectHeader(
        @ModelAttribute("menuManageVO") menuManageVO: MenuManageVO,
        @RequestParam(value = "flag", required = false) flag: String?,
        model: ModelMap
    ): String {
        val user =
            if (EgovUserDetailsHelper.isAuthenticated()) EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO? else null
        if (EgovUserDetailsHelper.isAuthenticated() && user != null) {
            menuManageVO.tmp_Id = user.id
            menuManageVO.tmp_Password = user.password
            menuManageVO.tmp_UserSe = user.userSe
            menuManageVO.tmp_Name = user.name
            menuManageVO.tmp_Email = user.email
            menuManageVO.tmp_OrgnztId = user.orgnztId
            menuManageVO.tmp_UniqId = user.uniqId
            model.addAttribute("list_headmenu", menuManageService!!.selectMainMenuHead(menuManageVO))
            model.addAttribute("list_menulist", menuManageService.selectMainMenuLeft(menuManageVO))
        } else {
            menuManageVO.authorCode = "ROLE_ANONYMOUS"
            model.addAttribute("list_headmenu", menuManageService!!.selectMainMenuHeadByAuthor(menuManageVO))
            model.addAttribute("list_menulist", menuManageService.selectMainMenuLeftByAuthor(menuManageVO))
        }

        return "main/inc/EgovIncHeader" // 업무화면의 상단메뉴 화면
    }

    /**
     * Footer Page를 조회한다.
     * @param
     * @return 출력페이지정보 "EgovIncFooter"
     * @exception Exception
     */
    @RequestMapping(value = ["/sym/mms/EgovFooter.do"])
    @Throws(Exception::class)
    fun selectFooter(model: ModelMap?): String {
        return "main/inc/EgovIncFooter"
    }

    /**
     * 좌측메뉴를 조회한다.
     * @param
     * @return 출력페이지정보 "EgovIncLeftmenu"
     * @exception Exception
     */
    @RequestMapping(value = ["/sym/mms/EgovMenuLeft.do"])
    @Throws(Exception::class)
    fun selectMenuLeft(model: ModelMap): String {
        //LoginVO user = EgovUserDetailsHelper.isAuthenticated()? (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser():null;

        //LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        if (EgovUserDetailsHelper.isAuthenticated()) {
            //인증된 경우 처리할 사항 추가 ...
            model.addAttribute("lastLogoutDateTime", "로그아웃 타임: 2021-08-12 11:30")
            //최근 로그아웃 시간 등에 대한 확보 후 메인 컨텐츠로 활용
        }

        return "main/inc/EgovIncLeftmenu"
    }

    /**
     * Head메뉴를 조회한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "main_headG", "main_head"
     * @exception Exception
     */
    @RequestMapping(value = ["/sym/mms/EgovMainMenuHead.do"])
    @Throws(Exception::class)
    fun selectMainMenuHead(
        @ModelAttribute("menuManageVO") menuManageVO: MenuManageVO,
        @RequestParam(value = "flag", required = false) flag: String?,
        model: ModelMap
    ): String {
        val user =
            if (EgovUserDetailsHelper.isAuthenticated()) EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO? else null
        if (EgovUserDetailsHelper.isAuthenticated() && user != null) {
            menuManageVO.tmp_Id = user.id
            menuManageVO.tmp_Password = user.password
            menuManageVO.tmp_UserSe = user.userSe
            menuManageVO.tmp_Name = user.name
            menuManageVO.tmp_Email = user.email
            menuManageVO.tmp_OrgnztId = user.orgnztId
            menuManageVO.tmp_UniqId = user.uniqId
            model.addAttribute("list_headmenu", menuManageService!!.selectMainMenuHead(menuManageVO))
            model.addAttribute("list_menulist", menuManageService.selectMainMenuLeft(menuManageVO))
        } else {
            menuManageVO.authorCode = "ROLE_ANONYMOUS"
            model.addAttribute("list_headmenu", menuManageService!!.selectMainMenuHeadByAuthor(menuManageVO))
            model.addAttribute("list_menulist", menuManageService.selectMainMenuLeftByAuthor(menuManageVO))
        }

        if (flag == null) {
            return "main/inc/EgovIncSubHeaderOld" // 업무화면의 상단메뉴 화면
        } else if (flag == "MAIN") {
            return "main/inc/EgovIncHeaderOld" // 메인화면의 상단메뉴 화면
        } else {
            return "main/inc/EgovIncSubHeaderOld" // 업무화면의 상단메뉴 화면
        }
    }


    /**
     * 좌측메뉴를 조회한다.
     * @param menuManageVO MenuManageVO
     * @param vStartP      String
     * @return 출력페이지정보 "main_left"
     * @exception Exception
     */
    @RequestMapping(value = ["/sym/mms/EgovMainMenuLeft.do"])
    @Throws(Exception::class)
    fun selectMainMenuLeft(
        model: ModelMap
    ): String {
        //LoginVO user = EgovUserDetailsHelper.isAuthenticated()? (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser():null;

        //LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        if (EgovUserDetailsHelper.isAuthenticated()) {
            //인증된 경우 처리할 사항 추가 ...
            model.addAttribute("lastLogoutDateTime", "로그아웃 타임: 2011-11-10 11:30")
            //최근 로그아웃 시간 등에 대한 확보 후 메인 컨텐츠로 활용
        }

        return "main/inc/EgovIncLeftmenuOld"
    }
}