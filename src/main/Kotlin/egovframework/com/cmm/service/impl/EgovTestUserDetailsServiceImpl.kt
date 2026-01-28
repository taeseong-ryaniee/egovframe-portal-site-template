package egovframework.com.cmm.service.impl

import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovUserDetailsService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl

/**
 *
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 12.
 * @version 1.0
 * @see
 * <pre>
 * 개정이력
</pre> */
class EgovTestUserDetailsServiceImpl : EgovAbstractServiceImpl(), EgovUserDetailsService {
    val authenticatedUser: Any
        get() {
            val loginVO = LoginVO()
            loginVO.id = "TEST1"
            //		loginVO.setPassword("raHLBnHFcunwNzcDcfad4PhD11hHgXSUr7fc1Jk9uoQ=");
            loginVO.userSe = "USR"
            loginVO.email = "egovframe3@nia.or.kr"
            loginVO.ihidNum = ""
            loginVO.name = "더미사용자"
            loginVO.orgnztId = "ORGNZT_0000000000000"
            loginVO.uniqId = "USRCNFRM_00000000000"
            return loginVO
        }

    val authorities: MutableList<String?>
        get() {
            // 권한 설정을 리턴한다.

            val listAuth: MutableList<String?> = ArrayList<String?>()
            listAuth.add("IS_AUTHENTICATED_ANONYMOUSLY")
            listAuth.add("IS_AUTHENTICATED_FULLY")
            listAuth.add("IS_AUTHENTICATED_REMEMBERED")
            listAuth.add("ROLE_ADMIN")
            listAuth.add("ROLE_ANONYMOUS")
            listAuth.add("ROLE_RESTRICTED")
            listAuth.add("ROLE_USER")

            return listAuth
        }

    val isAuthenticated: Boolean?
        get() = true
}
