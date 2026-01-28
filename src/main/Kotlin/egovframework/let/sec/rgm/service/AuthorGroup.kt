package egovframework.let.sec.rgm.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 권한그룹에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class AuthorGroup : ComDefaultVO() {
    /**
     * authorGroup attribute 를 리턴한다.
     * @return AuthorGroup
     */
    /**
     * authorGroup attribute 값을 설정한다.
     * @param authorGroup AuthorGroup
     */
    /**
     * 권한그룹관리
     */
    var authorGroup: AuthorGroup? = null
    /**
     * userId attribute 를 리턴한다.
     * @return String
     */
    /**
     * userId attribute 값을 설정한다.
     * @param userId String
     */
    /**
     * 설정대상 사용자 ID
     */
    var userId: String? = null
    /**
     * userNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * userNm attribute 값을 설정한다.
     * @param userNm String
     */
    /**
     * 설정대상 사용자 명
     */
    var userNm: String? = null
    /**
     * groupId attribute 를 리턴한다.
     * @return String
     */
    /**
     * groupId attribute 값을 설정한다.
     * @param groupId String
     */
    /**
     * 설정대상 그룹 ID
     */
    var groupId: String? = null
    /**
     * mberTyCode attribute 를 리턴한다.
     * @return String
     */
    /**
     * mberTyCode attribute 값을 설정한다.
     * @param mberTyCode String
     */
    /**
     * 설정대상 사용자 유형 코드
     */
    @JvmField
    var mberTyCode: String? = null
    /**
     * mberTyNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * mberTyNm attribute 값을 설정한다.
     * @param mberTyNm String
     */
    /**
     * 설정대상 사용자 유형 명
     */
    var mberTyNm: String? = null
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
    @JvmField
    var authorCode: String? = null
    /**
     * regYn attribute 를 리턴한다.
     * @return String
     */
    /**
     * regYn attribute 값을 설정한다.
     * @param regYn String
     */
    /**
     * 등록 여부
     */
    var regYn: String? = null
    /**
     * uniqId attribute 를 리턴한다.
     * @return String
     */
    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId String
     */
    /**
     * Uniq ID
     */
    @JvmField
    var uniqId: String? = null


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}