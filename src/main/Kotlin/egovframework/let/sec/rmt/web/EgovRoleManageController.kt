package egovframework.let.sec.rmt.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.sec.ram.service.AuthorManageVO
import egovframework.let.sec.ram.service.EgovAuthorManageService
import egovframework.let.sec.rmt.service.EgovRoleManageService
import egovframework.let.sec.rmt.service.RoleManage
import egovframework.let.sec.rmt.service.RoleManageVO
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
 * 롤관리에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovRoleManageController {
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Resource(name = "egovRoleManageService")
    private val egovRoleManageService: EgovRoleManageService? = null

    @Resource(name = "EgovCmmUseService")
    var egovCmmUseService: EgovCmmUseService? = null

    @Resource(name = "egovAuthorManageService")
    private val egovAuthorManageService: EgovAuthorManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /** Message ID Generation  */
    @Resource(name = "egovRoleIdGnrService")
    private val egovRoleIdGnrService: EgovIdGnrService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 롤 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/rmt/EgovRoleListView.do")
    @Throws(Exception::class)
    fun selectRoleListView(): String {
        return "/sec/rmt/EgovRoleManage"
    }

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rmt/EgovRoleList.do"])
    @Throws(Exception::class)
    fun selectRoleList(
        @ModelAttribute("roleManageVO") roleManageVO: RoleManageVO,
        model: ModelMap
    ): String {
        /** paging  */

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(roleManageVO.pageIndex)
        paginationInfo.setRecordCountPerPage(roleManageVO.pageUnit)
        paginationInfo.setPageSize(roleManageVO.pageSize)

        roleManageVO.firstIndex = paginationInfo.getFirstRecordIndex()
        roleManageVO.lastIndex = paginationInfo.getLastRecordIndex()
        roleManageVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        roleManageVO.setRoleManageList(egovRoleManageService!!.selectRoleList(roleManageVO))
        model.addAttribute("roleList", roleManageVO.roleManageList)

        val totCnt = egovRoleManageService.selectRoleListTotCnt(roleManageVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/sec/rmt/EgovRoleManage"
    }

    /**
     * 등록된 롤 정보 조회
     * @param roleCode String
     * @param roleManageVO RoleManageVO
     * @param authorManageVO AuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rmt/EgovRole.do"])
    @Throws(Exception::class)
    fun selectRole(
        @RequestParam("roleCode") roleCode: String?,
        @ModelAttribute("roleManageVO") roleManageVO: RoleManageVO,
        @ModelAttribute("authorManageVO") authorManageVO: AuthorManageVO,
        model: ModelMap
    ): String {
        roleManageVO.roleCode = roleCode

        authorManageVO.setAuthorManageList(egovAuthorManageService!!.selectAuthorAllList(authorManageVO))

        model.addAttribute("roleManage", egovRoleManageService!!.selectRole(roleManageVO))
        model.addAttribute("authorManageList", authorManageVO.authorManageList)
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(ComDefaultCodeVO(), "COM029"))

        return "/sec/rmt/EgovRoleUpdate"
    }

    /**
     * 롤 등록화면 이동
     * @param authorManageVO AuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping("/sec/rmt/EgovRoleInsertView.do")
    @Throws(Exception::class)
    fun insertRoleView(
        @ModelAttribute("authorManageVO") authorManageVO: AuthorManageVO,
        model: ModelMap
    ): String {
        authorManageVO.setAuthorManageList(egovAuthorManageService!!.selectAuthorAllList(authorManageVO))
        model.addAttribute("authorManageList", authorManageVO.authorManageList)
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(ComDefaultCodeVO(), "COM029"))

        return "/sec/rmt/EgovRoleInsert"
    }

    /**
     * 공통코드 호출
     * @param comDefaultCodeVO ComDefaultCodeVO
     * @param codeId String
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun getCmmCodeDetailList(comDefaultCodeVO: ComDefaultCodeVO, codeId: String?): MutableList<*>? {
        comDefaultCodeVO.codeId = codeId
        return egovCmmUseService!!.selectCmmCodeDetail(comDefaultCodeVO)
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage RoleManage
     * @param roleManageVO RoleManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rmt/EgovRoleInsert.do"])
    @Throws(Exception::class)
    fun insertRole(
        @ModelAttribute("roleManage") roleManage: RoleManage,
        @ModelAttribute("roleManageVO") roleManageVO: RoleManageVO,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: ModelMap
    ): String {
        beanValidator!!.validate(roleManage, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            return "/sec/rmt/EgovRoleInsert"
        } else {
            var roleTyp = roleManage.roleTyp
            if (roleTyp == "method") roleTyp = "mtd"
            else if (roleTyp == "pointcut") roleTyp = "pct"
            else roleTyp = "web"

            roleManage.roleCode = roleTyp + "-" + egovRoleIdGnrService!!.getNextStringId()
            roleManageVO.roleCode = roleManage.roleCode

            status.setComplete()
            model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(ComDefaultCodeVO(), "COM029"))
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.insert"))
            model.addAttribute("roleManage", egovRoleManageService!!.insertRole(roleManage, roleManageVO))

            return "/sec/rmt/EgovRoleUpdate"
        }
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage RoleManage
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rmt/EgovRoleUpdate.do"])
    @Throws(Exception::class)
    fun updateRole(
        @ModelAttribute("roleManage") roleManage: RoleManage,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: ModelMap
    ): String {
        beanValidator!!.validate(roleManage, bindingResult) //validation 수행
        if (bindingResult.hasErrors()) {
            return "/sec/rmt/EgovRoleUpdate"
        } else {
            egovRoleManageService!!.updateRole(roleManage)
            status.setComplete()
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.update"))
            return "forward:/sec/rmt/EgovRole.do"
        }
    }

    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage RoleManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rmt/EgovRoleDelete.do"])
    @Throws(Exception::class)
    fun deleteRole(
        @ModelAttribute("roleManage") roleManage: RoleManage?,
        status: SessionStatus,
        model: ModelMap
    ): String {
        egovRoleManageService!!.deleteRole(roleManage)
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/rmt/EgovRoleList.do"
    }

    /**
     * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
     * @param roleCodes String
     * @param roleManage RoleManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/sec/rmt/EgovRoleListDelete.do"])
    @Throws(Exception::class)
    fun deleteRoleList(
        @RequestParam("roleCodes") roleCodes: String,
        @ModelAttribute("roleManage") roleManage: RoleManage,
        status: SessionStatus,
        model: Model
    ): String {
        val strRoleCodes: Array<String?> = roleCodes.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in strRoleCodes.indices) {
            roleManage.roleCode = strRoleCodes[i]
            egovRoleManageService!!.deleteRole(roleManage)
        }
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))
        return "forward:/sec/rmt/EgovRoleList.do"
    }
}