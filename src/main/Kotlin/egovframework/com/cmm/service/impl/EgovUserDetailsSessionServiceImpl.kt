package egovframework.com.cmm.service.impl

import egovframework.com.cmm.service.EgovUserDetailsService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

/**
 *
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 6. 25.
 * @version 1.0
 * @see
 * <pre>
 * 개정이력
</pre> */
class EgovUserDetailsSessionServiceImpl : EgovAbstractServiceImpl(), EgovUserDetailsService {
    val authenticatedUser: Any?
        get() = RequestContextHolder.getRequestAttributes().getAttribute("loginVO", RequestAttributes.SCOPE_SESSION)

    val authorities: MutableList<String?>
        get() {
            // 권한 설정을 리턴한다.

            val listAuth: MutableList<String?> = ArrayList<String?>()

            return listAuth
        }

    val isAuthenticated: Boolean?
        get() {
            // 인증된 유저인지 확인한다.
            if (RequestContextHolder.getRequestAttributes() == null) {
                return false
            } else {
                if (RequestContextHolder.getRequestAttributes()
                        .getAttribute("loginVO", RequestAttributes.SCOPE_SESSION) == null
                ) {
                    return false
                } else {
                    return true
                }
            }
        }
}
