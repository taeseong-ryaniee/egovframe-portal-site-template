package egovframework.let.uss.olh.qna.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.uss.olh.qna.service.EgovQnaManageService
import egovframework.let.uss.olh.qna.service.QnaManageDefaultVO
import egovframework.let.uss.olh.qna.service.QnaManageVO
import egovframework.let.utl.sim.service.EgovFileScrty
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
 * Q&A를 처리하는 Controller 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovQnaAdminManageController {
    @Resource(name = "QnaManageService")
    private val qnaManageService: EgovQnaManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    // Validation 관련
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return    "/uss/olh/qna/admin/"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olh/qna/admin/EgovMain.do"])
    @Throws(Exception::class)
    fun EgovMain(model: ModelMap?): String {
        return "/uss/olh/qna/admin/EgovMain"
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return    "/uss/olh/qna/admin/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olh/qna/admin/EgovLeft.do"])
    @Throws(Exception::class)
    fun EgovLeft(model: ModelMap?): String {
        return "/uss/olh/qna/admin/EgovLeft"
    }

    /**
     * Q&A정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaListInqire"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olh/qna/admin/QnaListInqire.do"])
    @Throws(Exception::class)
    fun selectQnaList(@ModelAttribute("searchVO") searchVO: QnaManageDefaultVO, model: ModelMap): String {
        /** EgovPropertyService.SiteList  */

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

        model.addAttribute("resultList", qnaManageService!!.selectQnaList(searchVO))


        // 인증여부 체크
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (!isAuthenticated) {
            model.addAttribute("certificationAt", "N")
        } else {
            model.addAttribute("certificationAt", "Y")
        }

        val totCnt = qnaManageService.selectQnaListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olh/qna/admin/EgovQnaListInqire"
    }

    /**
     * Q&A정보 목록에 대한 상세정보를 조회한다.
     * @param passwordConfirmAt
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaDetailInqire.do")
    @Throws(Exception::class)
    fun selectQnaListDetail(
        @RequestParam("passwordConfirmAt") passwordConfirmAt: String?, qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?, model: ModelMap
    ): String {
        val vo = qnaManageService!!.selectQnaListDetail(qnaManageVO)

        vo!!.passwordConfirmAt = passwordConfirmAt // 작성비밀번호 확인여부

        // 작성 비밀번호를 얻는다.
        val writngPassword = vo.writngPassword

        // EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
        vo.writngPassword = EgovFileScrty.decode(writngPassword)

        model.addAttribute("result", vo)

        return "/uss/olh/qna/admin/EgovQnaDetailInqire"
    }

    /**
     * Q&A 조회수를  수정처리한다.
     * @param qnaManageVO
     * @param searchVO
     * @return    "forward:/uss/olh/qna/admin/QnaDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaInqireCoUpdt.do")
    @Throws(Exception::class)
    fun updateQnaInqireCo(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?
    ): String {
        qnaManageService!!.updateQnaInqireCo(qnaManageVO)

        return "forward:/uss/olh/qna/admin/QnaDetailInqire.do"
    }

    /**
     * 로그인/실명확인 처리
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return    /uss/olh/qna/admin/EgovLoginRealnmChoice
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/LoginRealnmChoice.do")
    @Throws(Exception::class)
    fun selectLoginRealnmChoice(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: Model
    ): String {
        model.addAttribute("QnaManageVO", QnaManageVO())

        return "/uss/olh/qna/admin/EgovQnaLoginRealnmChoice"
    }

    /**
     * Q&A정보를 등록하기 위한 전 처리(인증체크)
     * @param searchVO
     * @param qnaManageVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaCnRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaCnRegistView.do")
    @Throws(Exception::class)
    fun insertQnaCnView(
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        qnaManageVO: QnaManageVO,
        model: Model
    ): String {
        // 인증여부 체크

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (!isAuthenticated) {
            model.addAttribute("result", qnaManageVO)
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val wrterNm = loginVO.name // 사용자명
        val emailAdres = loginVO.email // email 주소

        qnaManageVO.wrterNm = wrterNm // 작성자명
        qnaManageVO.emailAdres = emailAdres // email 주소

        model.addAttribute("result", qnaManageVO)
        model.addAttribute("qnaManageVO", qnaManageVO)

        return "/uss/olh/qna/admin/EgovQnaCnRegist"
    }

    /**
     * Q&A정보를 등록한다.
     * @param searchVO
     * @param qnaManageVO
     * @param bindingResult
     * @return    "forward:/uss/olh/qna/admin/QnaListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaCnRegist.do")
    @Throws(Exception::class)
    fun insertQnaCn(
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        @ModelAttribute("qnaManageVO") qnaManageVO: QnaManageVO,
        bindingResult: BindingResult,
        model: ModelMap?
    ): String {
        beanValidator!!.validate(qnaManageVO, bindingResult)

        if (bindingResult.hasErrors()) {
            return "/uss/olh/qna/admin/EgovQnaCnRegist"
        }

        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val frstRegisterId = loginVO.uniqId

        qnaManageVO.frstRegisterId = frstRegisterId // 최초등록자ID
        qnaManageVO.lastUpdusrId = frstRegisterId // 최종수정자ID

        // 작성비밀번호를 암호화 하기 위해서 Get
        val writngPassword = qnaManageVO.writngPassword

        // EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
        qnaManageVO.writngPassword = EgovFileScrty.encode(writngPassword)

        qnaManageService!!.insertQnaCn(qnaManageVO)

        return "forward:/uss/olh/qna/admin/QnaListInqire.do"
    }

    /**
     * 작성 비밀번호를 확인하기 위한 전 처리
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaPasswordConfirm"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaPasswordConfirmView.do")
    @Throws(Exception::class)
    fun selectPasswordConfirmView(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: Model
    ): String {
        model.addAttribute("QnaManageVO", QnaManageVO())

        return "/uss/olh/qna/admin/EgovQnaPasswordConfirm"
    }

    /**
     * 작성 비밀번호를 확인한다.
     * @param qnaManageVO
     * @param searchVO
     * @return    "forward:/uss/olh/qna/admin/QnaDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaPasswordConfirm.do")
    @Throws(Exception::class)
    fun selectPasswordConfirm(
        qnaManageVO: QnaManageVO,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: Model
    ): String {
        // 인증여부 체크


        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (!isAuthenticated) {
            model.addAttribute("result", qnaManageVO)
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        } else {
            // 작성비밀번호를 암호화 하기 위해서 Get
            val writngPassword = qnaManageVO.writngPassword

            // EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
            qnaManageVO.writngPassword = EgovFileScrty.encode(writngPassword)

            val searchCnt = qnaManageService!!.selectQnaPasswordConfirmCnt(qnaManageVO)

            if (searchCnt > 0) { // 작성 비밀번호가 일치하는 경우

                // Q&A를 수정할 수 있는 화면으로 이동.

                return "forward:/uss/olh/qna/admin/QnaCnUpdtView.do"
            } else { // 작성비밀번호가 틀린경우

                val passwordConfirmAt = "N"

                // Q&A 상세조회 화면으로 이동.
                return "forward:/uss/olh/qna/admin/QnaDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt
            }
        }
    }

    /**
     * Q&A정보를 수정하기 위한 전 처리(비밀번호 암호화)
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaCnUpdt
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaCnUpdtView.do")
    @Throws(Exception::class)
    fun updateQnaCnView(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: ModelMap
    ): String {
        val vo = qnaManageService!!.selectQnaListDetail(qnaManageVO)

        // 작성 비밀번호를 얻는다.
        val writngPassword = vo!!.writngPassword

        // EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
        vo.writngPassword = EgovFileScrty.decode(writngPassword)

        // 복호화된 패스워드를 넘긴다..
        model.addAttribute("qnaManageVO", vo)

        // result에도 세팅(jstl 사용을 위해)
        model.addAttribute(selectQnaListDetail("Y", qnaManageVO, searchVO, model))

        return "/uss/olh/qna/admin/EgovQnaCnUpdt"
    }

    /**
     * Q&A정보를 수정처리한다.
     * @param searchVO
     * @param qnaManageVO
     * @param bindingResult
     * @return    "forward:/uss/olh/qna/admin/QnaListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaCnUpdt.do")
    @Throws(Exception::class)
    fun updateQnaCn(
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        @ModelAttribute("qnaManageVO") qnaManageVO: QnaManageVO,
        bindingResult: BindingResult
    ): String {
        // Validation

        beanValidator!!.validate(qnaManageVO, bindingResult)

        if (bindingResult.hasErrors()) {
            return "/uss/olh/qna/admin/EgovQnaCnUpdt"
        }

        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val lastUpdusrId = loginVO.uniqId

        qnaManageVO.lastUpdusrId = lastUpdusrId // 최종수정자ID

        // 작성비밀번호를 암호화 하기 위해서 Get
        val writngPassword = qnaManageVO.writngPassword

        // EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
        qnaManageVO.writngPassword = EgovFileScrty.encode(writngPassword)

        qnaManageService!!.updateQnaCn(qnaManageVO)

        return "forward:/uss/olh/qna/admin/QnaListInqire.do"
    }

    /**
     * Q&A정보를 삭제처리한다.
     * @param qnaManageVO
     * @param searchVO
     * @return    "forward:/uss/olh/qna/admin/QnaListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qna/admin/QnaCnDelete.do")
    @Throws(Exception::class)
    fun deleteQnaCn(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: Model
    ): String {
        // 인증여부 체크

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()

        if (!isAuthenticated) {
            model.addAttribute("result", qnaManageVO)
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }
        qnaManageService!!.deleteQnaCn(qnaManageVO)

        return "forward:/uss/olh/qna/admin/QnaListInqire.do"
    }

    /**
     * Q&A답변정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaAnswerListInqire"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olh/qnm/admin/QnaAnswerListInqire.do"])
    @Throws(Exception::class)
    fun selectQnaAnswerList(@ModelAttribute("searchVO") searchVO: QnaManageDefaultVO, model: ModelMap): String {
        /** EgovPropertyService.SiteList  */

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

        model.addAttribute("resultList", qnaManageService!!.selectQnaAnswerList(searchVO))

        val totCnt = qnaManageService.selectQnaAnswerListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olh/qna/admin/EgovQnaAnswerListInqire"
    }

    /**
     * Q&A답변정보 목록에 대한 상세정보를 조회한다.
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaAnswerDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qnm/admin/QnaAnswerDetailInqire.do")
    @Throws(Exception::class)
    fun selectQnaAnswerListDetail(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: ModelMap
    ): String {
        val vo = qnaManageService!!.selectQnaListDetail(qnaManageVO)

        model.addAttribute("result", vo)

        return "/uss/olh/qna/admin/EgovQnaAnswerDetailInqire"
    }

    /**
     * Q&A답변정보를 수정하기 위한 전 처리(공통코드 처리)
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/olh/qna/admin/EgovQnaCnAnswerUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qnm/admin/QnaCnAnswerUpdtView.do")
    @Throws(Exception::class)
    fun updateQnaCnAnswerView(
        qnaManageVO: QnaManageVO?,
        @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?,
        model: ModelMap
    ): String {
        // 공통코드를 가져오기 위한 Vo

        val vo = ComDefaultCodeVO()
        vo.codeId = "COM028"

        model.addAttribute("resultList", cmmUseService!!.selectCmmCodeDetail(vo))

        // 변수명은 CoC 에 따라
        model.addAttribute(selectQnaAnswerListDetail(qnaManageVO, searchVO, model))

        return "/uss/olh/qna/admin/EgovQnaCnAnswerUpdt"
    }

    /**
     * Q&A답변정보를 수정처리한다.
     * @param qnaManageVO
     * @param searchVO
     * @return    "forward:/uss/olh/qnm/admin/QnaAnswerListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/qnm/admin/QnaCnAnswerUpdt.do")
    @Throws(Exception::class)
    fun updateQnaCnAnswer(qnaManageVO: QnaManageVO, @ModelAttribute("searchVO") searchVO: QnaManageDefaultVO?): String {
        // 로그인VO에서  사용자 정보 가져오기

        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val lastUpdusrId = loginVO.uniqId

        qnaManageVO.lastUpdusrId = lastUpdusrId // 최종수정자ID

        qnaManageService!!.updateQnaCnAnswer(qnaManageVO)

        return "forward:/uss/olh/qnm/admin/QnaAnswerListInqire.do"
    }
}
