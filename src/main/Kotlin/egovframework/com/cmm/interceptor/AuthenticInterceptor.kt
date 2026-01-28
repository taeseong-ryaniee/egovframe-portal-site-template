package egovframework.com.cmm.interceptor

import egovframework.com.cmm.LoginVO
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.ModelAndViewDefiningException
import org.springframework.web.servlet.mvc.WebContentInterceptor
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class AuthenticInterceptor : WebContentInterceptor() {
    /**
     * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
     * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
     */
    @Throws(ServletException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val loginVO = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

        if (loginVO.id != null) {
            return true
        } else {
            val modelAndView = ModelAndView("redirect:/uat/uia/egovLoginUsr.do")
            throw ModelAndViewDefiningException(modelAndView)
        }
    }
}
