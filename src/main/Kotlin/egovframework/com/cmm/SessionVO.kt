package egovframework.com.cmm

import java.io.Serializable

/**
 * 세션 VO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class SessionVO : Serializable {
    /**
     * sUserId attribute 를 리턴한다.
     * @return String
     */
    /**
     * sUserId attribute 값을 설정한다.
     * @param sUserId String
     */
    /** 아이디  */
    var sUserId: String? = null
    /**
     * sUserNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * sUserNm attribute 값을 설정한다.
     * @param sUserNm String
     */
    /** 이름  */
    var sUserNm: String? = null
    /**
     * sEmail attribute 를 리턴한다.
     * @return String
     */
    /**
     * sEmail attribute 값을 설정한다.
     * @param sEmail String
     */
    /** 이메일  */
    var sEmail: String? = null
    /**
     * sUserSe attribute 를 리턴한다.
     * @return String
     */
    /**
     * sUserSe attribute 값을 설정한다.
     * @param sUserSe String
     */
    /** 사용자구분  */
    var sUserSe: String? = null
    /**
     * orgnztId attribute 를 리턴한다.
     * @return String
     */
    /**
     * orgnztId attribute 값을 설정한다.
     * @param orgnztId String
     */
    /** 조직(부서)ID  */
    var orgnztId: String? = null
    /**
     * uniqId attribute 를 리턴한다.
     * @return String
     */
    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId String
     */
    /** 고유아이디  */
    var uniqId: String? = null

    companion object {
        private val serialVersionUID = -2848741427493626376L
    }
}
