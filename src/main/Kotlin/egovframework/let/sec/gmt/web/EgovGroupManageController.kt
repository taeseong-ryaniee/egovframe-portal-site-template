package egovframework.let.sec.gmt.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.let.sec.gmt.service.EgovGroupManageService
import egovframework.let.sec.gmt.service.GroupManage
import egovframework.let.sec.gmt.service.GroupManageVO
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
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
 * 그룹관리에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovGroupManageController {
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovGroupManageService")
    private val egovGroupManageService: EgovGroupManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /** Message ID Generation  */
    @Resource(name = "egovGroupIdGnrService")
    private val egovGroupIdGnrService: EgovIdGnrService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 그룹 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/gmt/EgovGroupListView.do")
    @Throws(Exception::class)
    fun selectGroupListView(): String {
        return "/sec/gmt/EgovGroupManage"
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param groupManageVO GroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupList.do"])
    @Throws(Exception::class)
    fun selectGroupList(
        @ModelAttribute("groupManageVO") groupManageVO: GroupManageVO,
        model: ModelMap
    ): String {
        /** paging  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(groupManageVO.pageIndex)
        paginationInfo.setRecordCountPerPage(groupManageVO.pageUnit)
        paginationInfo.setPageSize(groupManageVO.pageSize)

        groupManageVO.firstIndex = paginationInfo.getFirstRecordIndex()
        groupManageVO.lastIndex = paginationInfo.getLastRecordIndex()
        groupManageVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        groupManageVO.setGroupManageList(egovGroupManageService!!.selectGroupList(groupManageVO))
        model.addAttribute("groupList", groupManageVO.groupManageList)

        val totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/sec/gmt/EgovGroupManage"
    }

    /**
     * 검색조건에 따른 그룹정보를 조회
     * @param groupManageVO GroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroup.do"])
    @Throws(Exception::class)
    fun selectGroup(
        @ModelAttribute("groupManageVO") groupManageVO: GroupManageVO?,
        model: ModelMap
    ): String {
        model.addAttribute("groupManage", egovGroupManageService!!.selectGroup(groupManageVO))
        return "/sec/gmt/EgovGroupUpdate"
    }

    /**
     * 그룹 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupInsertView.do"])
    @Throws(Exception::class)
    fun insertGroupView(): String {
        return "/sec/gmt/EgovGroupInsert"
    }

    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param groupManage GroupManage
     * @param groupManageVO GroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupInsert.do"])
    @Throws(Exception::class)
    fun insertGroup(
        @ModelAttribute("groupManage") groupManage: GroupManage,
        @ModelAttribute("groupManageVO") groupManageVO: GroupManageVO,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: ModelMap
    ): String {
        beanValidator!!.validate(groupManage, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            return "/sec/gmt/EgovGroupInsert"
        } else {
            groupManage.groupId = egovGroupIdGnrService!!.getNextStringId()
            groupManageVO.groupId = groupManage.groupId

            status.setComplete()
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.insert"))
            model.addAttribute("groupManage", egovGroupManageService!!.insertGroup(groupManage, groupManageVO))
            return "/sec/gmt/EgovGroupUpdate"
        }
    }

    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param groupManage GroupManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupUpdate.do"])
    @Throws(Exception::class)
    fun updateGroup(
        @ModelAttribute("groupManage") groupManage: GroupManage,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: Model
    ): String {
        beanValidator!!.validate(groupManage, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            return "/sec/gmt/EgovGroupUpdate"
        } else {
            egovGroupManageService!!.updateGroup(groupManage)
            status.setComplete()
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.update"))
            return "forward:/sec/gmt/EgovGroup.do"
        }
    }

    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param groupManage GroupManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupDelete.do"])
    @Throws(Exception::class)
    fun deleteGroup(
        @ModelAttribute("groupManage") groupManage: GroupManage?,
        status: SessionStatus,
        model: Model
    ): String {
        egovGroupManageService!!.deleteGroup(groupManage)
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/gmt/EgovGroupList.do"
    }

    /**
     * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
     * @param groupIds String
     * @param groupManage GroupManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupListDelete.do"])
    @Throws(Exception::class)
    fun deleteGroupList(
        @RequestParam("groupIds") groupIds: String,
        @ModelAttribute("groupManage") groupManage: GroupManage,
        status: SessionStatus,
        model: Model
    ): String {
        val strGroupIds: Array<String?> = groupIds.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in strGroupIds.indices) {
            groupManage.groupId = strGroupIds[i]
            egovGroupManageService!!.deleteGroup(groupManage)
        }
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/gmt/EgovGroupList.do"
    }

    /**
     * 그룹팝업 화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/gmt/EgovGroupSearchView.do")
    @Throws(Exception::class)
    fun selectGroupSearchView(): String {
        return "/sec/gmt/EgovGroupSearch"
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param groupManageVO GroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/gmt/EgovGroupSearchList.do"])
    @Throws(Exception::class)
    fun selectGroupSearchList(
        @ModelAttribute("groupManageVO") groupManageVO: GroupManageVO,
        model: ModelMap
    ): String {
        /** paging  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(groupManageVO.pageIndex)
        paginationInfo.setRecordCountPerPage(groupManageVO.pageUnit)
        paginationInfo.setPageSize(groupManageVO.pageSize)

        groupManageVO.firstIndex = paginationInfo.getFirstRecordIndex()
        groupManageVO.lastIndex = paginationInfo.getLastRecordIndex()
        groupManageVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        groupManageVO.setGroupManageList(egovGroupManageService!!.selectGroupList(groupManageVO))
        model.addAttribute("groupList", groupManageVO.groupManageList)

        val totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/sec/gmt/EgovGroupSearch"
    }
}