package egovframework.let.uat.uia.service.impl

import egovframework.com.cmm.LoginVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 일반 로그인을 처리하는 DAO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("loginDAO")
class LoginDAO : EgovAbstractMapper() {
    /**
     * 일반 로그인을 처리한다
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun actionLogin(vo: LoginVO?): LoginVO {
        return (LoginVO)<Object> selectOne < kotlin . Any ? > ("loginDAO.actionLogin", vo)
    }

    @Throws(Exception::class)
    fun actionLoginDevRelaxed(vo: LoginVO?): LoginVO {
        return (LoginVO)<Object> selectOne < kotlin . Any ? > ("loginDAO.actionLoginDevRelaxed", vo)
    }

    /**
     * 아이디를 찾는다.
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun searchId(vo: LoginVO?): LoginVO {
        return (LoginVO)<Object> selectOne < kotlin . Any ? > ("loginDAO.searchId", vo)
    }

    /**
     * 비밀번호를 찾는다.
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun searchPassword(vo: LoginVO?): LoginVO {
        return (LoginVO)<Object> selectOne < kotlin . Any ? > ("loginDAO.searchPassword", vo)
    }

    /**
     * 변경된 비밀번호를 저장한다.
     * @param vo LoginVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updatePassword(vo: LoginVO?) {
        update("loginDAO.updatePassword", vo)
    }
}
