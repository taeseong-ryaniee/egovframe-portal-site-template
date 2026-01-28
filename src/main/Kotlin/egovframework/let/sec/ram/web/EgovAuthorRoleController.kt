package egovframework.let.sec.ram.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.let.sec.ram.service.AuthorRoleManage
import egovframework.let.sec.ram.service.AuthorRoleManageVO
import egovframework.let.sec.ram.service.EgovAuthorRoleManageService
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.support.SessionStatus
import javax.annotation.Resource

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovAuthorRoleController {
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovAuthorRoleManageService")
    private val egovAuthorRoleManageService: EgovAuthorRoleManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /**
     * 권한 롤 관계 화면 이동
     * @return "/sec/ram/EgovDeptAuthorList"
     * @exception Exception
     */
    @RequestMapping("/sec/ram/EgovAuthorRoleListView.do")
    @Throws(Exception::class)
    fun selectAuthorRoleListView(): String {
        return "/sec/ram/EgovAuthorRoleManage"
    }

    /**
     * 권한별 할당된 롤 목록 조회
     *
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorRoleList.do"])
    @Throws(Exception::class)
    fun selectAuthorRoleList(
        @ModelAttribute("authorRoleManageVO") authorRoleManageVO: AuthorRoleManageVO,
        model: ModelMap
    ): String {
        /** paging  */

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(authorRoleManageVO.pageIndex)
        paginationInfo.setRecordCountPerPage(authorRoleManageVO.pageUnit)
        paginationInfo.setPageSize(authorRoleManageVO.pageSize)

        authorRoleManageVO.firstIndex = paginationInfo.getFirstRecordIndex()
        authorRoleManageVO.lastIndex = paginationInfo.getLastRecordIndex()
        authorRoleManageVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        authorRoleManageVO.setAuthorRoleList(egovAuthorRoleManageService!!.selectAuthorRoleList(authorRoleManageVO))
        model.addAttribute("authorRoleList", authorRoleManageVO.authorRoleList)

        val totCnt = egovAuthorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/sec/ram/EgovAuthorRoleManage"
    }

    /**
     * 권한정보에 롤을 할당하여 데이터베이스에 등록
     * @param authorCode String
     * @param roleCodes String
     * @param regYns String
     * @param authorRoleManage AuthorRoleManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/ram/EgovAuthorRoleInsert.do"])
    @Throws(Exception::class)
    fun insertAuthorRole(
        @RequestParam("authorCode") authorCode: String?,
        @RequestParam("roleCodes") roleCodes: String,
        @RequestParam("regYns") regYns: String,
        @ModelAttribute("authorRoleManage") authorRoleManage: AuthorRoleManage,
        status: SessionStatus,
        model: ModelMap
    ): String {
        val strRoleCodes: Array<String?> = roleCodes.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val strRegYns: Array<String?> = regYns.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        authorRoleManage.roleCode = authorCode

        for (i in strRoleCodes.indices) {
            authorRoleManage.roleCode = strRoleCodes[i]
            authorRoleManage.regYn = strRegYns[i]
            if (strRegYns[i] == "Y") egovAuthorRoleManageService!!.insertAuthorRole(authorRoleManage)
            else egovAuthorRoleManageService!!.deleteAuthorRole(authorRoleManage)
        }

        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.insert"))
        return "forward:/sec/ram/EgovAuthorRoleList.do"
    }
}