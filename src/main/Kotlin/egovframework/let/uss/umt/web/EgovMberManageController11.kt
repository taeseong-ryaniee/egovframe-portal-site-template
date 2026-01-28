package egovframework.let.uss.umt.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.uss.umt.service.EgovMberManageService
import egovframework.let.uss.umt.service.MberManageVO
import egovframework.let.uss.umt.service.UserDefaultVO
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
import javax.servlet.http.HttpServletRequest

/**
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovMberManageController {
    /** mberManageService  */
    @Resource(name = "mberManageService")
    private val mberManageService: EgovMberManageService? = null

    /** cmmUseService  */
    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /** DefaultBeanValidator beanValidator  */
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 일반회원목록을 조회한다. (pageing)
     * @param userSearchVO 검색조건정보
     * @param model 화면모델
     * @return cmm/uss/umt/EgovMberManage
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/umt/mber/EgovMberManage.do"])
    @Throws(Exception::class)
    fun selectMberList(
        @ModelAttribute("userSearchVO") userSearchVO: UserDefaultVO,
        model: ModelMap,
        request: HttpServletRequest
    ): String {
        // 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가

        request.getSession().setAttribute("menuNo", "6000000")


        // 미인증 사용자에 대한 보안처리
        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        /** EgovPropertyService  */
        userSearchVO.setPageUnit(propertiesService!!.getInt("pageUnit"))
        userSearchVO.setPageSize(propertiesService!!.getInt("pageSize"))

        /** pageing  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex())
        paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit())
        paginationInfo.setPageSize(userSearchVO.getPageSize())

        userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex())
        userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex())
        userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage())

        model.addAttribute("resultList", mberManageService!!.selectMberList(userSearchVO))

        val totCnt = mberManageService.selectMberListTotCnt(userSearchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        //일반회원 상태코드를 코드정보로부터 조회
        val vo = ComDefaultCodeVO()
        vo.codeId = "COM013"
        model.addAttribute("entrprsMberSttus_result", cmmUseService!!.selectCmmCodeDetail(vo))

        return "cmm/uss/umt/EgovMberManage"
    }

    /**
     * 일반회원등록화면으로 이동한다.
     * @param userSearchVO 검색조건정보
     * @param mberManageVO 일반회원초기화정보
     * @param model 화면모델
     * @return cmm/uss/umt/EgovMberInsert
     * @throws Exception
     */
    @RequestMapping("/uss/umt/mber/EgovMberInsertView.do")
    @Throws(Exception::class)
    fun insertMberView(
        @ModelAttribute("userSearchVO") userSearchVO: UserDefaultVO?,
        @ModelAttribute("mberManageVO") mberManageVO: MberManageVO?,
        model: Model
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        val vo = ComDefaultCodeVO()

        //패스워드힌트목록을 코드정보로부터 조회
        vo.codeId = "COM022"
        model.addAttribute("passwordHint_result", cmmUseService!!.selectCmmCodeDetail(vo))

        //성별구분코드를 코드정보로부터 조회
        vo.codeId = "COM014"
        model.addAttribute("sexdstnCode_result", cmmUseService.selectCmmCodeDetail(vo))

        //사용자상태코드를 코드정보로부터 조회
        vo.codeId = "COM013"
        model.addAttribute("mberSttus_result", cmmUseService.selectCmmCodeDetail(vo))

        //그룹정보를 조회 - GROUP_ID정보
        vo.tableNm = "LETTNORGNZTINFO"
        model.addAttribute("groupId_result", cmmUseService.selectGroupIdDetail(vo))

        return "cmm/uss/umt/EgovMberInsert"
    }

    /**
     * 일반회원등록처리후 목록화면으로 이동한다.
     * @param mberManageVO 일반회원등록정보
     * @param bindingResult 입력값검증용 bindingResult
     * @param model 화면모델
     * @return forward:/uss/umt/mber/EgovMberManage.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/mber/EgovMberInsert.do")
    @Throws(Exception::class)
    fun insertMber(
        @ModelAttribute("mberManageVO") mberManageVO: MberManageVO,
        bindingResult: BindingResult,
        model: Model
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        beanValidator!!.validate(mberManageVO, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = ComDefaultCodeVO()

            //패스워드힌트목록을 코드정보로부터 조회
            vo.codeId = "COM022"
            model.addAttribute("passwordHint_result", cmmUseService!!.selectCmmCodeDetail(vo))

            //성별구분코드를 코드정보로부터 조회
            vo.codeId = "COM014"
            model.addAttribute("sexdstnCode_result", cmmUseService.selectCmmCodeDetail(vo))

            //사용자상태코드를 코드정보로부터 조회
            vo.codeId = "COM013"
            model.addAttribute("mberSttus_result", cmmUseService.selectCmmCodeDetail(vo))

            //그룹정보를 조회 - GROUP_ID정보
            vo.tableNm = "LETTNORGNZTINFO"
            model.addAttribute("groupId_result", cmmUseService.selectGroupIdDetail(vo))
            return "cmm/uss/umt/EgovMberInsert"
        } else {
            mberManageService!!.insertMber(mberManageVO)
            //Exception 없이 진행시 등록 성공메시지
            model.addAttribute("resultMsg", "success.common.insert")
        }
        return "forward:/uss/umt/mber/EgovMberManage.do"
    }

    /**
     * 일반회원정보 수정을 위해 일반회원정보를 상세조회한다.
     * @param mberId 상세조회대상 일반회원아이디
     * @param userSearchVO 검색조건
     * @param model 화면모델
     * @return cmm/uss/umt/EgovMberSelectUpdt
     * @throws Exception
     */
    @RequestMapping("/uss/umt/mber/EgovMberSelectUpdtView.do")
    @Throws(Exception::class)
    fun updateMberView(
        @RequestParam("selectedId") mberId: String?,
        @ModelAttribute("searchVO") userSearchVO: UserDefaultVO?,
        model: Model
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        val vo = ComDefaultCodeVO()

        //패스워드힌트목록을 코드정보로부터 조회
        vo.codeId = "COM022"
        model.addAttribute("passwordHint_result", cmmUseService!!.selectCmmCodeDetail(vo))

        //성별구분코드를 코드정보로부터 조회
        vo.codeId = "COM014"
        model.addAttribute("sexdstnCode_result", cmmUseService.selectCmmCodeDetail(vo))

        //사용자상태코드를 코드정보로부터 조회
        vo.codeId = "COM013"
        model.addAttribute("mberSttus_result", cmmUseService.selectCmmCodeDetail(vo))

        //그룹정보를 조회 - GROUP_ID정보
        vo.tableNm = "LETTNORGNZTINFO"
        model.addAttribute("groupId_result", cmmUseService.selectGroupIdDetail(vo))

        val mberManageVO = mberManageService!!.selectMber(mberId)
        model.addAttribute("mberManageVO", mberManageVO)
        model.addAttribute("userSearchVO", userSearchVO)

        return "cmm/uss/umt/EgovMberSelectUpdt"
    }

    /**
     * 일반회원정보 수정후 목록조회 화면으로 이동한다.
     * @param mberManageVO 일반회원수정정보
     * @param bindingResult 입력값검증용 bindingResult
     * @param model 화면모델
     * @return forward:/uss/umt/mber/EgovMberManage.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/mber/EgovMberSelectUpdt.do")
    @Throws(Exception::class)
    fun updateMber(
        @ModelAttribute("mberManageVO") mberManageVO: MberManageVO,
        bindingResult: BindingResult,
        model: Model
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        beanValidator!!.validate(mberManageVO, bindingResult)
        if (bindingResult.hasErrors()) {
            val vo = ComDefaultCodeVO()

            //패스워드힌트목록을 코드정보로부터 조회
            vo.codeId = "COM022"
            model.addAttribute("passwordHint_result", cmmUseService!!.selectCmmCodeDetail(vo))

            //성별구분코드를 코드정보로부터 조회
            vo.codeId = "COM014"
            model.addAttribute("sexdstnCode_result", cmmUseService.selectCmmCodeDetail(vo))

            //사용자상태코드를 코드정보로부터 조회
            vo.codeId = "COM013"
            model.addAttribute("mberSttus_result", cmmUseService.selectCmmCodeDetail(vo))

            //그룹정보를 조회 - GROUP_ID정보
            vo.tableNm = "LETTNORGNZTINFO"
            model.addAttribute("groupId_result", cmmUseService.selectGroupIdDetail(vo))
            return "cmm/uss/umt/EgovMberSelectUpdt"
        } else {
            mberManageService!!.updateMber(mberManageVO)
            //Exception 없이 진행시 수정성공메시지
            model.addAttribute("resultMsg", "success.common.update")
            return "forward:/uss/umt/mber/EgovMberManage.do"
        }
    }

    /**
     * 일반회원정보삭제후 목록조회 화면으로 이동한다.
     * @param checkedIdForDel 삭제대상 아이디 정보
     * @param userSearchVO 검색조건정보
     * @param model 화면모델
     * @return forward:/uss/umt/mber/EgovMberManage.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/mber/EgovMberDelete.do")
    @Throws(Exception::class)
    fun deleteMber(
        @RequestParam("checkedIdForDel") checkedIdForDel: String?,
        @ModelAttribute("searchVO") userSearchVO: UserDefaultVO?,
        model: Model
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        mberManageService!!.deleteMber(checkedIdForDel)
        //Exception 없이 진행시 삭제성공메시지
        model.addAttribute("resultMsg", "success.common.delete")
        return "forward:/uss/umt/mber/EgovMberManage.do"
    }

    /**
     * 일반회원가입신청 등록화면으로 이동한다.
     * @param userSearchVO 검색조건
     * @param mberManageVO 일반회원가입신청정보
     * @param commandMap 파라메터전달용 commandMap
     * @param model 화면모델
     * @return cmm/uss/umt/EgovMberSbscrb
     * @throws Exception
     */
    @RequestMapping("/uss/umt/cmm/EgovMberSbscrbView.do")
    @Throws(Exception::class)
    fun sbscrbMberView(
        @ModelAttribute("userSearchVO") userSearchVO: UserDefaultVO?,
        @ModelAttribute("mberManageVO") mberManageVO: MberManageVO,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: Model
    ): String {
        // 미인증 사용자에 대한 보안처리
        /*
		 * Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		 * if(!isAuthenticated) { model.addAttribute("message",
		 * egovMessageSource.getMessage("fail.common.login")); return
		 * "uat/uia/EgovLoginUsr"; }
		 */

        val vo = ComDefaultCodeVO()

        //패스워드힌트목록을 코드정보로부터 조회
        vo.codeId = "COM022"
        model.addAttribute("passwordHint_result", cmmUseService!!.selectCmmCodeDetail(vo))

        //성별구분코드를 코드정보로부터 조회
        vo.codeId = "COM014"
        model.addAttribute("sexdstnCode_result", cmmUseService.selectCmmCodeDetail(vo))

        if ("" != commandMap.get("realname")) {
            model.addAttribute("mberNm", commandMap.get("realname")) //실명인증된 이름 - 주민번호 인증
            model.addAttribute("ihidnum", commandMap.get("ihidnum")) //실명인증된 주민등록번호 - 주민번호 인증
        }
        if ("" != commandMap.get("realName")) {
            model.addAttribute("mberNm", commandMap.get("realName")) //실명인증된 이름 - ipin인증
        }

        mberManageVO.setGroupId("DEFAULT")
        mberManageVO.setMberSttus("DEFAULT")

        return "cmm/uss/umt/EgovMberSbscrb"
    }

    /**
     * 일반회원가입신청등록처리후로그인화면으로 이동한다.
     * @param mberManageVO 일반회원가입신청정보
     * @return forward:/uat/uia/egovLoginUsr.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/cmm/EgovMberSbscrb.do")
    @Throws(Exception::class)
    fun sbscrbMber(@ModelAttribute("mberManageVO") mberManageVO: MberManageVO): String {
        //가입상태 초기화

        mberManageVO.setMberSttus("A")
        //그룹정보 초기화
        mberManageVO.setGroupId("GROUP_00000000000000") //기본그룹
        //일반회원가입신청 등록시 일반회원등록기능을 사용하여 등록한다.
        mberManageService!!.insertMber(mberManageVO)
        return "forward:/uat/uia/egovLoginUsr.do"
    }

    /**
     * 일반회원 약관확인
     * @param model 화면모델
     * @return cmm/uss/umt/EgovStplatCnfirm
     * @throws Exception
     */
    @RequestMapping("/uss/umt/cmm/EgovStplatCnfirmMber.do")
    @Throws(Exception::class)
    fun sbscrbEntrprsMber(model: Model): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        //일반회원용 약관 아이디 설정
        val stplatId = "STPLAT_0000000000001"
        //회원가입유형 설정-일반회원
        val sbscrbTy = "USR01"
        //약관정보 조회
        model.addAttribute("stplatList", mberManageService!!.selectStplat(stplatId))
        model.addAttribute("sbscrbTy", sbscrbTy) //회원가입유형 포함

        return "cmm/uss/umt/EgovStplatCnfirm"
    }

    /**
     * @param model 화면모델
     * @param commandMap 파라메터전달용 commandMap
     * @param userSearchVO 검색조건
     * @param mberManageVO 일반회원수정정보(비밀번호)
     * @return cmm/uss/umt/EgovMberPasswordUpdt
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/umt/mber/EgovMberPasswordUpdt.do"])
    @Throws(Exception::class)
    fun updatePassword(
        model: ModelMap,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("searchVO") userSearchVO: UserDefaultVO?,
        @ModelAttribute("mberManageVO") mberManageVO: MberManageVO
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        val oldPassword = commandMap.get("oldPassword") as String?
        val newPassword = commandMap.get("newPassword") as String
        val newPassword2 = commandMap.get("newPassword2") as String?
        val uniqId = commandMap.get("uniqId") as String?

        var isCorrectPassword = false
        var resultVO = MberManageVO()
        mberManageVO.setPassword(newPassword)
        mberManageVO.setOldPassword(oldPassword)
        mberManageVO.setUniqId(uniqId)

        var resultMsg = ""
        resultVO = mberManageService!!.selectPassword(mberManageVO)
        //패스워드 암호화
        val encryptPass = EgovFileScrty.encryptPassword(oldPassword, mberManageVO.getMberId())
        if (encryptPass == resultVO.getPassword()) {
            if (newPassword == newPassword2) {
                isCorrectPassword = true
            } else {
                isCorrectPassword = false
                resultMsg = "fail.user.passwordUpdate2"
            }
        } else {
            isCorrectPassword = false
            resultMsg = "fail.user.passwordUpdate1"
        }

        if (isCorrectPassword) {
            mberManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword, mberManageVO.getMberId()))
            mberManageService.updatePassword(mberManageVO)
            model.addAttribute("mberManageVO", mberManageVO)
            resultMsg = "success.common.update"
        } else {
            model.addAttribute("mberManageVO", mberManageVO)
        }
        model.addAttribute("userSearchVO", userSearchVO)
        model.addAttribute("resultMsg", resultMsg)

        return "cmm/uss/umt/EgovMberPasswordUpdt"
    }

    /**
     * 일반회원 암호 수정 화면 이동
     * @param model 화면모델
     * @param commandMap 파라메터전달용 commandMap
     * @param userSearchVO 검색조건
     * @param mberManageVO 일반회원수정정보(비밀번호)
     * @return cmm/uss/umt/EgovMberPasswordUpdt
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/umt/mber/EgovMberPasswordUpdtView.do"])
    @Throws(Exception::class)
    fun updatePasswordView(
        model: ModelMap,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        @ModelAttribute("searchVO") userSearchVO: UserDefaultVO?,
        @ModelAttribute("mberManageVO") mberManageVO: MberManageVO
    ): String {
        // 미인증 사용자에 대한 보안처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }

        val userTyForPassword = commandMap.get("userTyForPassword") as String?
        mberManageVO.setUserTy(userTyForPassword)

        model.addAttribute("userSearchVO", userSearchVO)
        model.addAttribute("mberManageVO", mberManageVO)
        return "cmm/uss/umt/EgovMberPasswordUpdt"
    }

    /**
     * 입력한 사용자아이디의 중복확인화면 이동
     * @param model 화면모델
     * @return cmm/uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/umt/EgovIdDplctCnfirmView.do"])
    @Throws(Exception::class)
    fun checkIdDplct(model: ModelMap): String {
        /*
		 * // 미인증 사용자에 대한 보안처리 Boolean isAuthenticated =
		 * EgovUserDetailsHelper.isAuthenticated(); if(!isAuthenticated) {
		 * model.addAttribute("message",
		 * egovMessageSource.getMessage("fail.common.login")); return
		 * "uat/uia/EgovLoginUsr"; }
		 */
        model.addAttribute("checkId", "")
        model.addAttribute("usedCnt", "-1")
        return "cmm/uss/umt/EgovIdDplctCnfirm"
    }

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param commandMap 파라메터전달용 commandMap
     * @param model 화면모델
     * @return cmm/uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/umt/cmm/EgovIdDplctCnfirm.do"])
    @Throws(Exception::class)
    fun checkIdDplct(@RequestParam commandMap: MutableMap<String?, Any?>, model: ModelMap): String {
        /*
		 * // 미인증 사용자에 대한 보안처리 Boolean isAuthenticated =
		 * EgovUserDetailsHelper.isAuthenticated(); if(!isAuthenticated) {
		 * model.addAttribute("message",
		 * egovMessageSource.getMessage("fail.common.login")); return
		 * "uat/uia/EgovLoginUsr"; }
		 */
        var checkId = commandMap.get("checkId") as String
        checkId = String(checkId.toByteArray(charset("ISO-8859-1")), charset("UTF-8"))

        if (checkId == null || checkId == "") return "forward:/uss/umt/EgovIdDplctCnfirmView.do"

        val usedCnt = mberManageService!!.checkIdDplct(checkId)
        model.addAttribute("usedCnt", usedCnt)
        model.addAttribute("checkId", checkId)

        return "cmm/uss/umt/EgovIdDplctCnfirm"
    }
}