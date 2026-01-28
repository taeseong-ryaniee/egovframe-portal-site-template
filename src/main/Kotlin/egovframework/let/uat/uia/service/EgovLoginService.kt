package egovframework.let.uat.uia.service

import egovframework.com.cmm.LoginVO

/**
 * 일반 로그인을 처리하는 비즈니스 인터페이스 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovLoginService {
    /**
     * 일반 로그인을 처리한다
     * @return LoginVO
     *
     * @param vo    LoginVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun actionLogin(vo: LoginVO?): LoginVO?

    /**
     * 아이디를 찾는다.
     * @return LoginVO
     *
     * @param vo    LoginVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun searchId(vo: LoginVO?): LoginVO?

    /**
     * 비밀번호를 찾는다.
     * @return boolean
     *
     * @param vo    LoginVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun searchPassword(vo: LoginVO?): Boolean
}