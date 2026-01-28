package egovframework.let.sec.ram.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 권한관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class AuthorManage : ComDefaultVO() {
    /**
     * authorManage attribute 를 리턴한다.
     * @return AuthorManage
     */
    /**
     * authorManage attribute 값을 설정한다.
     * @param authorManage AuthorManage
     */
    /**
     * 권한관리
     */
    var authorManage: AuthorManage? = null
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
     * authorCreatDe attribute 를 리턴한다.
     * @return String
     */
    /**
     * authorCreatDe attribute 값을 설정한다.
     * @param authorCreatDe String
     */
    /**
     * 권한등록일자
     */
    var authorCreatDe: String? = null
    /**
     * authorDc attribute 를 리턴한다.
     * @return String
     */
    /**
     * authorDc attribute 값을 설정한다.
     * @param authorDc String
     */
    /**
     * 권한코드설명
     */
    var authorDc: String? = null
    /**
     * authorNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * authorNm attribute 값을 설정한다.
     * @param authorNm String
     */
    /**
     * 권한 명
     */
    var authorNm: String? = null


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
