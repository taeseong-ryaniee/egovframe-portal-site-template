package egovframework.let.sec.ram.service

/**
 * 권한관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovAuthorManageService {
    /**
     * 모든 권한목록을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
    </AuthorManageVO> */
    @Throws(Exception::class)
    fun selectAuthorAllList(authorManageVO: AuthorManageVO?): MutableList<AuthorManageVO?>?

    /**
     * 시스템 사용자중 불필요한 시스템권한정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteAuthor(authorManage: AuthorManage?)

    /**
     * 사용자의 시스테접근권한를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertAuthor(authorManage: AuthorManage?)

    /**
     * 개별사용자에게 할당된 권한 조회
     * @param authorManageVO AuthorManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthor(authorManageVO: AuthorManageVO?): AuthorManageVO?

    /**
     * 개별사용자에게 할당된 권한리스트 조회
     * @param authorManageVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
    </AuthorManageVO> */
    @Throws(Exception::class)
    fun selectAuthorList(authorManageVO: AuthorManageVO?): MutableList<AuthorManageVO?>?

    /**
     * 화면에 조회된 사용자권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateAuthor(authorManage: AuthorManage?)

    /**
     * 목록조회 카운트를 반환한다
     * @param authorManageVO AuthorManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorListTotCnt(authorManageVO: AuthorManageVO?): Int
}
