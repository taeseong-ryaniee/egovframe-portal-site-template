package egovframework.let.sec.ram.service

/**
 * 권한별 롤 관리에 대한 Vo 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class AuthorRoleManageVO : AuthorRoleManage() {
    /**
     * authorRoleList attribute 를 리턴한다.
     * @return List<AuthorRoleManageVO>
    </AuthorRoleManageVO> */
    /**
     * authorRoleList attribute 값을 설정한다.
     * @param authorRoleList List<AuthorRoleManageVO>
    </AuthorRoleManageVO> */
    @JvmField
    var authorRoleList: MutableList<AuthorRoleManageVO?>? = null


    companion object {
        private const val serialVersionUID = 1L
    }
}