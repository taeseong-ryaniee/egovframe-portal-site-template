package egovframework.let.uat.uia.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.let.uat.uia.service.EgovLoginService
import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.support.WebApplicationContextUtils
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse

/**
 * 일반 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovLoginController {
    /** EgovLoginService  */
    @Resource(name = "loginService")
    private val loginService: EgovLoginService? = null

    /** EgovMessageSource  */
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /** TRACE  */
    @Resource(name = "leaveaTrace")
    var leaveaTrace: LeaveaTrace? = null

    @Resource
    private val environment: Environment? = null

    /**
     * 로그인 화면으로 들어간다
     * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
     * @return 로그인 페이지
     * @exception Exception
     */
    @RequestMapping(value = ["/uat/uia/egovLoginUsr.do"])
    @Throws(Exception::class)
    fun loginUsrView(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        model: ModelMap?
    ): String {
        return "uat/uia/EgovLoginUsr"
    }

    /**
     * 일반(스프링 시큐리티) 로그인을 처리한다
     * @param vo - 아이디, 비밀번호가 담긴 LoginVO
     * @param request - 세션처리를 위한 HttpServletRequest
     * @return result - 로그인결과(세션정보)
     * @exception Exception
     */
    @RequestMapping(value = ["/uat/uia/actionSecurityLogin.do"])
    @Throws(Exception::class)
    fun actionSecurityLogin(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        request: HttpServletRequest,
        response: HttpServletResponse?,
        model: ModelMap
    ): String {
        // 1. 일반 로그인 처리

        val resultVO = loginService!!.actionLogin(loginVO)

        val loginPolicyYn = true

        if (resultVO != null && resultVO.id != null && (resultVO.id != "") && loginPolicyYn) {
            // 2. spring security 연동

            request.getSession().setAttribute("LoginVO", resultVO)

            // 개발 프로파일(security-dev)에서는 보안 필터 연동을 생략하고 바로 메인으로
            if (environment != null && environment.acceptsProfiles("security-dev")) {
                return "forward:/cmm/main/mainPage.do"
            }

            var springSecurity: UsernamePasswordAuthenticationFilter? = null

            val act: ApplicationContext =
                WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext())


            val beans =
                act.getBeansOfType<UsernamePasswordAuthenticationFilter?>(UsernamePasswordAuthenticationFilter::class.java)

            if (beans.size > 0) {
                springSecurity = beans.values.toTypedArray()[0]
                springSecurity!!.setUsernameParameter("egov_security_username")
                springSecurity.setPasswordParameter("egov_security_password")
                springSecurity.setRequiresAuthenticationRequestMatcher(
                    AntPathRequestMatcher(
                        request.getServletContext().getContextPath() + "/egov_security_login", "POST"
                    )
                )
            } else {
                throw IllegalStateException("No AuthenticationProcessingFilter")
            }

            springSecurity.doFilter(
                RequestWrapperForSecurity(
                    request,
                    resultVO.userSe + resultVO.id,
                    resultVO.uniqId
                ), response, null
            )

            return "forward:/cmm/main/mainPage.do" // 성공 시 페이지.. (redirect 불가)
        } else {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }
    }

    /**
     * 로그인 후 메인화면으로 들어간다
     * @param
     * @return 로그인 페이지
     * @exception Exception
     */
    @RequestMapping(value = ["/uat/uia/actionMain.do"])
    @Throws(Exception::class)
    fun actionMain(model: ModelMap): String {
        // 1. Spring Security 사용자권한 처리

        val isAuthenticated = EgovUserDetailsHelper.isAuthenticated()
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource!!.getMessage("fail.common.login"))
            return "uat/uia/EgovLoginUsr"
        }


        // 2. 메인 페이지 이동
        return "forward:/cmm/main/mainPage.do"
    }

    /**
     * 로그아웃한다.
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/uat/uia/actionLogout.do"])
    @Throws(Exception::class)
    fun actionLogout(request: HttpServletRequest, model: ModelMap?): String {
        request.getSession().setAttribute("LoginVO", null)

        return "redirect:/egov_security_logout"
    }
}

internal class RequestWrapperForSecurity(request: HttpServletRequest, username: String?, password: String?) :
    HttpServletRequestWrapper(request) {
    private var username: String? = null
    private var password: String? = null

    init {
        this.username = username
        this.password = password
    }

    override fun getServletPath(): String {
        return (super.getRequest() as HttpServletRequest).getContextPath() + "/egov_security_login"
    }

    override fun getRequestURI(): String {
        return (super.getRequest() as HttpServletRequest).getContextPath() + "/egov_security_login"
    }

    override fun getParameter(name: String): String? {
        if (name == "egov_security_username") {
            return username
        }

        if (name == "egov_security_password") {
            return password
        }

        return super.getParameter(name)
    }
}
