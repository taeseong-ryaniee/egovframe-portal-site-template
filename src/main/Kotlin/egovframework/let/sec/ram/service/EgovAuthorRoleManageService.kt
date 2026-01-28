package egovframework.let.sec.ram.service

/**
 * 권한별 롤 관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovAuthorRoleManageService {
    /**
     * 권한 롤 관계정보를 조회
     * @param authorRoleManageVO AuthorRoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorRole(authorRoleManageVO: AuthorRoleManageVO?): AuthorRoleManageVO?

    /**
     * 권한 롤 관계정보 목록 조회
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return List<AuthorRoleManageVO>
     * @exception Exception
    </AuthorRoleManageVO> */
    @Throws(Exception::class)
    fun selectAuthorRoleList(authorRoleManageVO: AuthorRoleManageVO?): MutableList<AuthorRoleManageVO?>?

    /**
     * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertAuthorRole(authorRoleManage: AuthorRoleManage?)

    /**
     * 수정된 권한 롤 관계정보를 데이터베이스에 반영
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateAuthorRole(authorRoleManage: AuthorRoleManage?)

    /**
     * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteAuthorRole(authorRoleManage: AuthorRoleManage?)

    /**
     * 목록조회 카운트를 반환한다
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorRoleListTotCnt(authorRoleManageVO: AuthorRoleManageVO?): Int
}
