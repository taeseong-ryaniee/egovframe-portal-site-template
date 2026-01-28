package egovframework.let.sec.ram.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 권한별 롤 관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class AuthorRoleManage : ComDefaultVO() {
    /**
     * authorRole attribute 를 리턴한다.
     * @return AuthorRoleManage
     */
    /**
     * authorRole attribute 값을 설정한다.
     * @param authorRole AuthorRoleManage
     */
    /**
     * 권한 롤 관리
     */
    var authorRole: AuthorRoleManage? = null
    /**
     * authorCode attribute 를 리턴한다.
     * @return String
     */
    /**
     * authorCode attribute 값을 설정한다.
     * @param authorCode String
     */
    /**
     * 권한코드
     */
    var authorCode: String? = null
    /**
     * roleCode attribute 를 리턴한다.
     * @return String
     */
    /**
     * roleCode attribute 값을 설정한다.
     * @param roleCode String
     */
    /**
     * 롤코드
     */
    @JvmField
    var roleCode: String? = null
    /**
     * roleNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * roleNm attribute 값을 설정한다.
     * @param roleNm String
     */
    /**
     * 롤명
     */
    var roleNm: String? = null
    /**
     * rolePtn attribute 를 리턴한다.
     * @return String
     */
    /**
     * rolePtn attribute 값을 설정한다.
     * @param rolePtn String
     */
    /**
     * 롤 패턴
     */
    var rolePtn: String? = null
    /**
     * roleDc attribute 를 리턴한다.
     * @return String
     */
    /**
     * roleDc attribute 값을 설정한다.
     * @param roleDc String
     */
    /**
     * 롤 설명
     */
    var roleDc: String? = null
    /**
     * roleTyp attribute 를 리턴한다.
     * @return String
     */
    /**
     * roleTyp attribute 값을 설정한다.
     * @param roleTyp String
     */
    /**
     * 롤 타입
     */
    var roleTyp: String? = null
    /**
     * roleSort attribute 를 리턴한다.
     * @return String
     */
    /**
     * roleSort attribute 값을 설정한다.
     * @param roleSort String
     */
    /**
     * 롤 순서정렬
     */
    var roleSort: String? = null
    /**
     * regYn attribute 를 리턴한다.
     * @return String
     */
    /**
     * regYn attribute 값을 설정한다.
     * @param regYn String
     */
    /**
     * 롤 등록여부
     */
    @JvmField
    var regYn: String? = null
    /**
     * creatDt attribute 를 리턴한다.
     * @return String
     */
    /**
     * creatDt attribute 값을 설정한다.
     * @param creatDt String
     */
    /**
     * 등록일자
     */
    var creatDt: String? = null


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
