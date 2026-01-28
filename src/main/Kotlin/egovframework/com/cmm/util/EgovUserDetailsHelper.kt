package egovframework.com.cmm.util

import egovframework.com.cmm.service.EgovUserDetailsService

/**
 * EgovUserDetails Helper 클래스
 *
 * @since 2009.06.01
 * @version 1.0
 * @author sjyoon
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovUserDetailsHelper {
    var egovUserDetailsService: EgovUserDetailsService
        get() = Companion.egovUserDetailsService!!
        set(egovUserDetailsService) {
            Companion.egovUserDetailsService = egovUserDetailsService
        }

    companion object {
        var egovUserDetailsService: EgovUserDetailsService? = null

        @JvmStatic
        val authenticatedUser: Any?
            /**
             * 인증된 사용자객체를 VO형식으로 가져온다.
             * @return Object - 사용자 ValueObject
             */
            get() = egovUserDetailsService!!.authenticatedUser

        val authorities: MutableList<String?>?
            /**
             * 인증된 사용자의 권한 정보를 가져온다.
             *
             * @return List - 사용자 권한정보 목록
             */
            get() = egovUserDetailsService!!.authorities

        val isAuthenticated: Boolean?
            /**
             * 인증된 사용자 여부를 체크한다.
             * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
             */
            get() = egovUserDetailsService!!.isAuthenticated
    }
}