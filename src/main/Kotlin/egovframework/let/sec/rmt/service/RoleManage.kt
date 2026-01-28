package egovframework.let.sec.rmt.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 롤관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class RoleManage : ComDefaultVO() {
    /**
     * roleManage attribute 를 리턴한다.
     * @return RoleManage
     */
    /**
     * roleManage attribute 값을 설정한다.
     * @param roleManage RoleManage
     */
    /**
     * 롤 관리
     */
    var roleManage: RoleManage? = null
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
     * 롤패턴
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
    @JvmField
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
     * 롤 Sort
     */
    var roleSort: String? = null
    /**
     * roleCreatDe attribute 를 리턴한다.
     * @return String
     */
    /**
     * roleCreatDe attribute 값을 설정한다.
     * @param roleCreatDe String
     */
    /**
     * 롤 등록일시
     */
    var roleCreatDe: String? = null
    /**
     * authorCode attribute 를 리턴한다.
     * @return String
     */
    /**
     * authorCode attribute 값을 설정한다.
     * @param authorCode String
     */
    /**
     * 권한 코드
     */
    var authorCode: String? = null


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}