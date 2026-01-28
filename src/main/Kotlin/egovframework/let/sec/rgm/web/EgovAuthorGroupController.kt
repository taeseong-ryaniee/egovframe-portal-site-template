package egovframework.let.sec.rgm.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.let.sec.ram.service.AuthorManageVO
import egovframework.let.sec.ram.service.EgovAuthorManageService
import egovframework.let.sec.rgm.service.AuthorGroup
import egovframework.let.sec.rgm.service.AuthorGroupVO
import egovframework.let.sec.rgm.service.EgovAuthorGroupService
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
 * 권한그룹에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovAuthorGroupController {
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovAuthorGroupService")
    private val egovAuthorGroupService: EgovAuthorGroupService? = null

    @Resource(name = "egovAuthorManageService")
    private val egovAuthorManageService: EgovAuthorManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /**
     * 권한 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/rgm/EgovAuthorGroupListView.do")
    @Throws(Exception::class)
    fun selectAuthorGroupListView(): String {
        return "/sec/rgm/EgovAuthorGroupManage"
    }

    /**
     * 그룹별 할당된 권한 목록 조회
     * @param authorGroupVO AuthorGroupVO
     * @param authorManageVO AuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rgm/EgovAuthorGroupList.do"])
    @Throws(Exception::class)
    fun selectAuthorGroupList(
        @ModelAttribute("authorGroupVO") authorGroupVO: AuthorGroupVO,
        @ModelAttribute("authorManageVO") authorManageVO: AuthorManageVO,
        model: ModelMap
    ): String {
        /** paging  */

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(authorGroupVO.pageIndex)
        paginationInfo.setRecordCountPerPage(authorGroupVO.pageUnit)
        paginationInfo.setPageSize(authorGroupVO.pageSize)

        authorGroupVO.firstIndex = paginationInfo.getFirstRecordIndex()
        authorGroupVO.lastIndex = paginationInfo.getLastRecordIndex()
        authorGroupVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        authorGroupVO.setAuthorGroupList(egovAuthorGroupService!!.selectAuthorGroupList(authorGroupVO))
        model.addAttribute("authorGroupList", authorGroupVO.authorGroupList)

        val totCnt = egovAuthorGroupService.selectAuthorGroupListTotCnt(authorGroupVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        authorManageVO.setAuthorManageList(egovAuthorManageService!!.selectAuthorAllList(authorManageVO))
        model.addAttribute("authorManageList", authorManageVO.authorManageList)

        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/sec/rgm/EgovAuthorGroupManage"
    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     * @param userIds String
     * @param authorCodes String
     * @param regYns String
     * @param authorGroup AuthorGroup
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rgm/EgovAuthorGroupInsert.do"])
    @Throws(Exception::class)
    fun insertAuthorGroup(
        @RequestParam("userIds") userIds: String,
        @RequestParam("authorCodes") authorCodes: String,
        @RequestParam("regYns") regYns: String,
        @RequestParam("mberTyCodes") mberTyCode: String,
        @ModelAttribute("authorGroup") authorGroup: AuthorGroup,
        status: SessionStatus,
        model: ModelMap
    ): String {
        val strUserIds: Array<String?> = userIds.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val strAuthorCodes: Array<String?> =
            authorCodes.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val strRegYns: Array<String?> = regYns.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val strMberTyCode: Array<String?> =
            mberTyCode.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in strUserIds.indices) {
            authorGroup.uniqId = strUserIds[i]
            authorGroup.authorCode = strAuthorCodes[i]
            authorGroup.mberTyCode = strMberTyCode[i]
            if (strRegYns[i] == "N") egovAuthorGroupService!!.insertAuthorGroup(authorGroup)
            else egovAuthorGroupService!!.updateAuthorGroup(authorGroup)
        }

        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.insert"))
        return "forward:/sec/rgm/EgovAuthorGroupList.do"
    }

    /**
     * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
     * @param userIds String
     * @param authorGroup AuthorGroup
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rgm/EgovAuthorGroupDelete.do"])
    @Throws(Exception::class)
    fun deleteAuthorGroup(
        @RequestParam("userIds") userIds: String,
        @ModelAttribute("authorGroup") authorGroup: AuthorGroup,
        status: SessionStatus,
        model: ModelMap
    ): String {
        val strUserIds: Array<String?> = userIds.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in strUserIds.indices) {
            authorGroup.uniqId = strUserIds[i]
            egovAuthorGroupService!!.deleteAuthorGroup(authorGroup)
        }

        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/rgm/EgovAuthorGroupList.do"
    }
}