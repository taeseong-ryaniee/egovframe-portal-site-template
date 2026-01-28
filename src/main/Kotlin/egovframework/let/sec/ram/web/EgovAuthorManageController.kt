package egovframework.let.sec.ram.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.let.sec.ram.service.AuthorManage
import egovframework.let.sec.ram.service.AuthorManageVO
import egovframework.let.sec.ram.service.EgovAuthorManageService
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.support.SessionStatus
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource

/**
 * 권한관리에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovAuthorManageController {
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovAuthorManageService")
    private val egovAuthorManageService: EgovAuthorManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 권한 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/ram/EgovAuthorListView.do")
    @Throws(Exception::class)
    fun selectAuthorListView(): String {
        return "/sec/ram/EgovAuthorManage"
    }

    /**
     * 권한 목록을 조회한다
     * @param authorManageVO AuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorList.do"])
    @Throws(Exception::class)
    fun selectAuthorList(@ModelAttribute("authorManageVO") authorManageVO: AuthorManageVO, model: ModelMap): String {
        /** paging  */

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(authorManageVO.pageIndex)
        paginationInfo.setRecordCountPerPage(authorManageVO.pageUnit)
        paginationInfo.setPageSize(authorManageVO.pageSize)

        authorManageVO.firstIndex = paginationInfo.getFirstRecordIndex()
        authorManageVO.lastIndex = paginationInfo.getLastRecordIndex()
        authorManageVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        authorManageVO.setAuthorManageList(egovAuthorManageService!!.selectAuthorList(authorManageVO))
        model.addAttribute("authorList", authorManageVO.authorManageList)

        val totCnt = egovAuthorManageService.selectAuthorListTotCnt(authorManageVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/sec/ram/EgovAuthorManage"
    }

    /**
     * 권한 세부정보를 조회한다.
     * @param authorCode String
     * @param authorManageVO AuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthor.do"])
    @Throws(Exception::class)
    fun selectAuthor(
        @RequestParam("authorCode") authorCode: String?,
        @ModelAttribute("authorManageVO") authorManageVO: AuthorManageVO,
        model: ModelMap
    ): String {
        authorManageVO.authorCode = authorCode

        model.addAttribute("authorManage", egovAuthorManageService!!.selectAuthor(authorManageVO))
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))
        return "/sec/ram/EgovAuthorUpdate"
    }

    /**
     * 권한 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/ram/EgovAuthorInsertView.do")
    @Throws(Exception::class)
    fun insertAuthorView(): String {
        return "/sec/ram/EgovAuthorInsert"
    }

    /**
     * 권한 세부정보를 등록한다.
     * @param authorManage AuthorManage
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorInsert.do"])
    @Throws(Exception::class)
    fun insertAuthor(
        @ModelAttribute("authorManage") authorManage: AuthorManage,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: ModelMap
    ): String {
        beanValidator!!.validate(authorManage, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            return "/sec/ram/EgovAuthorInsert"
        } else {
            egovAuthorManageService!!.insertAuthor(authorManage)
            status.setComplete()
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.insert"))
            return "forward:/sec/ram/EgovAuthor.do"
        }
    }

    /**
     * 권한 세부정보를 수정한다.
     * @param authorManage AuthorManage
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorUpdate.do"])
    @Throws(Exception::class)
    fun updateAuthor(
        @ModelAttribute("authorManage") authorManage: AuthorManage,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: Model
    ): String {
        beanValidator!!.validate(authorManage, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            return "/sec/ram/EgovAuthorUpdate"
        } else {
            egovAuthorManageService!!.updateAuthor(authorManage)
            status.setComplete()
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.update"))
            return "forward:/sec/ram/EgovAuthor.do"
        }
    }

    /**
     * 권한 세부정보를 삭제한다.
     * @param authorManage AuthorManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorDelete.do"])
    @Throws(Exception::class)
    fun deleteAuthor(
        @ModelAttribute("authorManage") authorManage: AuthorManage?,
        status: SessionStatus,
        model: Model
    ): String {
        egovAuthorManageService!!.deleteAuthor(authorManage)
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/ram/EgovAuthorList.do"
    }

    /**
     * 권한목록을 삭제한다.
     * @param authorCodes String
     * @param authorManage AuthorManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorListDelete.do"])
    @Throws(Exception::class)
    fun deleteAuthorList(
        @RequestParam("authorCodes") authorCodes: String,
        @ModelAttribute("authorManage") authorManage: AuthorManage,
        status: SessionStatus,
        model: Model
    ): String {
        val strAuthorCodes: Array<String?> =
            authorCodes.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in strAuthorCodes.indices) {
            authorManage.authorCode = strAuthorCodes[i]
            egovAuthorManageService!!.deleteAuthor(authorManage)
        }
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/ram/EgovAuthorList.do"
    }

    /**
     * 권한제한 화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/ram/accessDenied.do")
    @Throws(Exception::class)
    fun accessDenied(): String {
        return "sec/accessDenied"
    }
}
