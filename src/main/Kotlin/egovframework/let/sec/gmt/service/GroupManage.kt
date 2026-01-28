package egovframework.let.sec.gmt.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 그룹관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class GroupManage : ComDefaultVO() {
    /**
     * groupManage attribute 를 리턴한다.
     * @return GroupManage
     */
    /**
     * groupManage attribute 값을 설정한다.
     * @param groupManage GroupManage
     */
    /**
     * 그룹 관리
     */
    var groupManage: GroupManage? = null
    /**
     * groupId attribute 를 리턴한다.
     * @return String
     */
    /**
     * groupId attribute 값을 설정한다.
     * @param groupId String
     */
    /**
     * 그룹 ID
     */
    @JvmField
    var groupId: String? = null
    /**
     * groupNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * groupNm attribute 값을 설정한다.
     * @param groupNm String
     */
    /**
     * 그룹명
     */
    var groupNm: String? = null
    /**
     * groupCreatDe attribute 를 리턴한다.
     * @return String
     */
    /**
     * groupCreatDe attribute 값을 설정한다.
     * @param groupCreatDe String
     */
    /**
     * 그룹등록일시
     */
    var groupCreatDe: String? = null
    /**
     * groupDc attribute 를 리턴한다.
     * @return String
     */
    /**
     * groupDc attribute 값을 설정한다.
     * @param groupDc String
     */
    /**
     * 그룹설명
     */
    var groupDc: String? = null

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}