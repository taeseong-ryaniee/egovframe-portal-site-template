package egovframework.let.uat.uia.service.impl

import egovframework.com.cmm.LoginVO
import egovframework.let.uat.uia.service.EgovLoginService
import egovframework.let.utl.fcc.service.EgovNumberUtil
import egovframework.let.utl.fcc.service.EgovStringUtil
import egovframework.let.utl.sim.service.EgovFileScrty
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 일반 로그인을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("loginService")
class EgovLoginServiceImpl : EgovAbstractServiceImpl(), EgovLoginService {
    @Resource(name = "loginDAO")
    private val loginDAO: LoginDAO? = null

    @Resource
    private val environment: Environment? = null

    /**
     * 일반 로그인을 처리한다
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun actionLogin(vo: LoginVO): LoginVO {
        // 1. 입력한 비밀번호를 암호화한다.

        val enpassword = EgovFileScrty.encryptPassword(vo.password, vo.id)
        vo.password = enpassword

        // 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
        var loginVO = loginDAO!!.actionLogin(vo)

        // dev 프로파일(security-dev)에서는 비밀번호 미일치 시 완화 로그인 시도(아이디+상태만)
        if ((loginVO == null || EgovStringUtil.isEmpty(loginVO.id)) && environment != null && environment.acceptsProfiles(
                "security-dev"
            )
        ) {
            loginVO = loginDAO.actionLoginDevRelaxed(vo)
        }

        // 3. 결과를 리턴한다.
        if (loginVO != null && (loginVO.id != "") && (loginVO.password != "")) {
            return loginVO
        } else {
            loginVO = LoginVO()
        }

        return loginVO
    }

    /**
     * 아이디를 찾는다.
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun searchId(vo: LoginVO?): LoginVO {
        // 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.

        var loginVO = loginDAO!!.searchId(vo)

        // 2. 결과를 리턴한다.
        if (loginVO != null && loginVO.id != "") {
            return loginVO
        } else {
            loginVO = LoginVO()
        }

        return loginVO
    }

    /**
     * 비밀번호를 찾는다.
     * @param vo LoginVO
     * @return boolean
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun searchPassword(vo: LoginVO): Boolean {
        val result = true

        // 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
        val loginVO = loginDAO!!.searchPassword(vo)
        if (loginVO == null || loginVO.password == null || loginVO.password == "") {
            return false
        }

        // 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫=6자리)
        var newpassword = ""
        for (i in 1..6) {
            // 영자
            if (i % 3 != 0) {
                newpassword += EgovStringUtil.getRandomStr('a', 'z')
                // 숫자
            } else {
                newpassword += EgovNumberUtil.getRandomNum(0, 9)
            }
        }

        // 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
        val pwVO = LoginVO()
        val enpassword = EgovFileScrty.encryptPassword(newpassword, vo.id)
        pwVO.id = vo.id
        pwVO.password = enpassword
        pwVO.userSe = vo.userSe
        loginDAO.updatePassword(pwVO)

        return result
    }
}
