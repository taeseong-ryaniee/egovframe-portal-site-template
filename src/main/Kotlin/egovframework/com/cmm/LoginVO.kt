package egovframework.com.cmm

import java.io.Serializable

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.03    박지욱          최초 생성
 *
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.03
 * @version 1.0
 * @see
 */
class LoginVO : Serializable {
    /**
     * id attribute 를 리턴한다.
     * @return String
     */
    /**
     * id attribute 값을 설정한다.
     * @param id String
     */
    /** 아이디  */
    @JvmField
    var id: String? = null
    /**
     * name attribute 를 리턴한다.
     * @return String
     */
    /**
     * name attribute 값을 설정한다.
     * @param name String
     */
    /** 이름  */
    @JvmField
    var name: String? = null
    /**
     * ihidNum attribute 를 리턴한다.
     * @return String
     */
    /**
     * ihidNum attribute 값을 설정한다.
     * @param ihidNum String
     */
    /** 주민등록번호  */
    @JvmField
    var ihidNum: String? = null
    /**
     * email attribute 를 리턴한다.
     * @return String
     */
    /**
     * email attribute 값을 설정한다.
     * @param email String
     */
    /** 이메일주소  */
    @JvmField
    var email: String? = null
    /**
     * password attribute 를 리턴한다.
     * @return String
     */
    /**
     * password attribute 값을 설정한다.
     * @param password String
     */
    /** 비밀번호  */
    @JvmField
    var password: String? = null
    /**
     * passwordHint attribute 를 리턴한다.
     * @return String
     */
    /**
     * passwordHint attribute 값을 설정한다.
     * @param passwordHint String
     */
    /** 비밀번호 힌트  */
    var passwordHint: String? = null
    /**
     * passwordCnsr attribute 를 리턴한다.
     * @return String
     */
    /**
     * passwordCnsr attribute 값을 설정한다.
     * @param passwordCnsr String
     */
    /** 비밀번호 정답  */
    var passwordCnsr: String? = null
    /**
     * userSe attribute 를 리턴한다.
     * @return String
     */
    /**
     * userSe attribute 값을 설정한다.
     * @param userSe String
     */
    /** 사용자구분  */
    @JvmField
    var userSe: String? = null
    /**
     * orgnztId attribute 를 리턴한다.
     * @return String
     */
    /**
     * orgnztId attribute 값을 설정한다.
     * @param orgnztId String
     */
    /** 조직(부서)ID  */
    @JvmField
    var orgnztId: String? = null
    /**
     * @return the orgnztNm
     */
    /**
     * @param orgnztNm the orgnztNm to set
     */
    /** 조직(부서)명  */
    @JvmField
    var orgnztNm: String? = null
    /**
     * uniqId attribute 를 리턴한다.
     * @return String
     */
    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId String
     */
    /** 고유아이디  */
    @JvmField
    var uniqId: String? = null
    /**
     * url attribute 를 리턴한다.
     * @return String
     */
    /**
     * url attribute 값을 설정한다.
     * @param url String
     */
    /** 로그인 후 이동할 페이지  */
    var url: String? = null
    /**
     * ip attribute 를 리턴한다.
     * @return String
     */
    /**
     * ip attribute 값을 설정한다.
     * @param ip String
     */
    /** 사용자 IP정보  */
    var ip: String? = null
    /**
     * dn attribute 를 리턴한다.
     * @return String
     */
    /**
     * dn attribute 값을 설정한다.
     * @param dn String
     */
    /** GPKI인증 DN  */
    var dn: String? = null

    companion object {
        /**
         *
         */
        private val serialVersionUID = -8274004534207618049L
    }
}
