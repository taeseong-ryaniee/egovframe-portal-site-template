package egovframework.let.uss.olh.faq.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.EgovFileMngUtil
import egovframework.com.cmm.service.FileVO
import egovframework.let.uss.olh.faq.service.EgovFaqManageService
import egovframework.let.uss.olh.faq.service.FaqManageDefaultVO
import egovframework.let.uss.olh.faq.service.FaqManageVO
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
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

/**
 *
 * FAQ내용을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovFaqAdminManageController {
    @Resource(name = "FaqManageService")
    private val faqManageService: EgovFaqManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    // 첨부파일 관련
    @Autowired
    private val fileMngService: EgovFileMngService? = null

    @Resource(name = "EgovFileMngUtil")
    private val fileUtil: EgovFileMngUtil? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    // Validation 관련
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null


    /**
     * FAQ 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return    "/uss/olh/faq/admin/EgovFaqListInqireAdmin"
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/olh/faq/admin/FaqListInqire.do"])
    @Throws(Exception::class)
    fun selectFaqList(
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO,
        model: ModelMap,
        request: HttpServletRequest
    ): String {
        // 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가
        request.getSession().setAttribute("menuNo", "5000000")

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

        model.addAttribute("resultList", faqManageService!!.selectFaqList(searchVO))

        val totCnt = faqManageService.selectFaqListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/uss/olh/faq/admin/EgovFaqListInqire"
    }

    /**
     * FAQ 목록에 대한 상세정보를 조회한다.
     * @param faqManageVO
     * @param searchVO
     * @param model
     * @return    "/uss/olh/faq/admin/EgovFaqDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqListDetailInqire.do")
    @Throws(Exception::class)
    fun selectFaqListDetail(
        faqManageVO: FaqManageVO?,
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?,
        model: ModelMap
    ): String {
        val vo = faqManageService!!.selectFaqListDetail(faqManageVO)

        model.addAttribute("result", vo)

        return "/uss/olh/faq/admin/EgovFaqDetailInqire"
    }

    /**
     * FAQ 조회수를  수정처리
     * @param faqManageVO
     * @param searchVO
     * @return    "forward:/uss/olh/faq/admin/FaqListDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqInqireCoUpdt.do")
    @Throws(Exception::class)
    fun updateFaqInqireCo(
        faqManageVO: FaqManageVO,
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?
    ): String {
        val loginVO: LoginVO
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            loginVO = LoginVO()
            loginVO.uniqId = "_aunonymous"
        } else {
            loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
        }


        // 로그인VO에서  사용자 정보 가져오기
        val lastUpdusrId = loginVO.uniqId

        faqManageVO.lastUpdusrId = lastUpdusrId // 최종수정자ID

        faqManageService!!.updateFaqInqireCo(faqManageVO)

        return "forward:/uss/olh/faq/admin/FaqListDetailInqire.do"
    }

    /**
     * FAQ를 등록하기 위한 전 처리
     * @param searchVO
     * @param model
     * @return    "/uss/olh/faq/admin/EgovFaqCnRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqCnRegistView.do")
    @Throws(Exception::class)
    fun insertFaqCnView(
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?, model: Model
    ): String {
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }
        model.addAttribute("faqManageVO", FaqManageVO())

        return "/uss/olh/faq/admin/EgovFaqCnRegist"
    }

    /**
     * FAQ를 등록한다.
     * @param multiRequest
     * @param searchVO
     * @param faqManageVO
     * @param bindingResult
     * @return    "forward:/uss/olh/faq/admin/FaqListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqCnRegist.do")
    @Throws(Exception::class)
    fun insertFaqCn(
        multiRequest: MultipartHttpServletRequest,  // 첨부파일을 위한...
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?,
        @ModelAttribute("faqManageVO") faqManageVO: FaqManageVO,
        bindingResult: BindingResult
    ): String {
        beanValidator!!.validate(faqManageVO, bindingResult)

        if (bindingResult.hasErrors()) {
            return "/uss/olh/wor/EgovFaqCnRegist"
        }

        // 첨부파일 관련 첨부파일ID 생성
        var _result: MutableList<FileVO?>? = null
        var _atchFileId: String? = ""

        val files = multiRequest.getFileMap()

        if (!files.isEmpty()) {
            _result = fileUtil!!.parseFileInf(files, "FAQ_", 0, "", "")
            _atchFileId = fileMngService!!.insertFileInfs(_result) //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
        }

        // 리턴받은 첨부파일ID를 셋팅한다..
        faqManageVO.atchFileId = _atchFileId // 첨부파일 ID

        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val frstRegisterId = loginVO.uniqId

        faqManageVO.frstRegisterId = frstRegisterId // 최초등록자ID
        faqManageVO.lastUpdusrId = frstRegisterId // 최종수정자ID

        faqManageService!!.insertFaqCn(faqManageVO)


        return "forward:/uss/olh/faq/admin/FaqListInqire.do"
    }

    /**
     * FAQ를 수정하기 위한 전 처리
     * @param faqId
     * @param searchVO
     * @param model
     * @return    "/uss/olh/faq/admin/EgovFaqCnUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqCnUpdtView.do")
    @Throws(Exception::class)
    fun updateFaqCnView(
        @RequestParam("faqId") faqId: String?,
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?, model: ModelMap
    ): String {
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        val faqManageVO = FaqManageVO()

        // Primary Key 값 세팅
        faqManageVO.faqId = faqId

        // 변수명은 CoC 에 따라
        model.addAttribute(selectFaqListDetail(faqManageVO, searchVO, model))

        // 변수명은 CoC 에 따라 JSTL사용을 위해
        model.addAttribute("faqManageVO", faqManageService!!.selectFaqListDetail(faqManageVO))

        return "/uss/olh/faq/admin/EgovFaqCnUpdt"
    }

    /**
     * FAQ를 수정처리한다.
     * @param atchFileAt
     * @param multiRequest
     * @param searchVO
     * @param faqManageVO
     * @param bindingResult
     * @param model
     * @return    "forward:/uss/olh/faq/admin/FaqListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqCnUpdt.do")
    @Throws(Exception::class)
    fun updateFaqCn(
        @RequestParam("atchFileAt") atchFileAt: String?,
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?,
        @ModelAttribute("faqManageVO") faqManageVO: FaqManageVO,
        bindingResult: BindingResult,
        model: ModelMap?
    ): String {
        // Validation

        beanValidator!!.validate(faqManageVO, bindingResult)

        if (bindingResult.hasErrors()) {
            return "/uss/olh/wor/EgovFaqCnUpdt"
        }

        // 첨부파일 관련 ID 생성 start....
        var _atchFileId = faqManageVO.atchFileId

        val files = multiRequest.getFileMap()

        if (!files.isEmpty()) {
            if ("N" == atchFileAt) {
                val _result = fileUtil!!.parseFileInf(files, "FAQ_", 0, _atchFileId, "")
                _atchFileId = fileMngService!!.insertFileInfs(_result)

                // 첨부파일 ID 셋팅
                faqManageVO.atchFileId = _atchFileId // 첨부파일 ID
            } else {
                val fvo = FileVO()
                fvo.atchFileId = _atchFileId
                val _cnt = fileMngService!!.getMaxFileSN(fvo)
                val _result = fileUtil!!.parseFileInf(files, "FAQ_", _cnt, _atchFileId, "")
                fileMngService.updateFileInfs(_result)
            }
        }


        // 첨부파일 관련 ID 생성 end...


        // 로그인VO에서  사용자 정보 가져오기
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        val lastUpdusrId = loginVO.uniqId

        faqManageVO.lastUpdusrId = lastUpdusrId // 최종수정자ID

        faqManageService!!.updateFaqCn(faqManageVO)

        return "forward:/uss/olh/faq/admin/FaqListInqire.do"
    }

    /**
     * FAQ를 삭제처리한다.
     * @param faqManageVO
     * @param searchVO
     * @return    "forward:/uss/olh/faq/admin/FaqListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olh/faq/admin/FaqCnDelete.do")
    @Throws(Exception::class)
    fun deleteFaqCn(
        faqManageVO: FaqManageVO,
        @ModelAttribute("searchVO") searchVO: FaqManageDefaultVO?
    ): String {
        // 첨부파일 삭제를 위한 ID 생성 start....


        val _atchFileId = faqManageVO.atchFileId

        faqManageService!!.deleteFaqCn(faqManageVO)

        // 첨부파일을 삭제하기 위한  Vo
        val fvo = FileVO()
        fvo.atchFileId = _atchFileId

        fileMngService!!.deleteAllFileInf(fvo)

        // 첨부파일 삭제 End.............
        return "forward:/uss/olh/faq/admin/FaqListInqire.do"
    }
}
