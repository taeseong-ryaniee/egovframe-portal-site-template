package egovframework.let.sec.gmt.service

/**
 * 그룹관리에 대한 Vo 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class GroupManageVO : GroupManage() {
    /**
     * groupManageList attribute 를 리턴한다.
     * @return List<GroupManageVO>
    </GroupManageVO> */
    /**
     * groupManageList attribute 값을 설정한다.
     * @param groupManageList List<GroupManageVO>
    </GroupManageVO> */
    /**
     * 그룹 목록
     */
    @JvmField
    var groupManageList: MutableList<GroupManageVO?>? = null
    /**
     * delYn attribute 를 리턴한다.
     * @return String[]
     */
    /**
     * delYn attribute 값을 설정한다.
     * @param delYn String[]
     */
    /**
     * 삭제대상 목록
     */
    var delYn: Array<String?>?

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}