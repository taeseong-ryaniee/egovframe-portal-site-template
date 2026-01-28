package egovframework.let.sec.ram.service.impl

import egovframework.let.sec.ram.service.AuthorRoleManage
import egovframework.let.sec.ram.service.AuthorRoleManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 권한별 롤관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("authorRoleManageDAO")
class AuthorRoleManageDAO : EgovAbstractMapper() {
    /**
     * 권한 롤 관계정보를 조회
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return AuthorRoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorRole(authorRoleManageVO: AuthorRoleManageVO?): AuthorRoleManageVO? {
        return selectOne<Any?>("authorRoleManageDAO.selectAuthorRole", authorRoleManageVO) as AuthorRoleManageVO?
    }

    /**
     * 권한 롤 관계정보 목록 조회
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return List<AuthorRoleManageVO>
     * @exception Exception
    </AuthorRoleManageVO> */
    @Throws(Exception::class)
    fun selectAuthorRoleList(authorRoleManageVO: AuthorRoleManageVO?): MutableList<AuthorRoleManageVO?>? {
        return selectList<AuthorRoleManageVO?>("authorRoleManageDAO.selectAuthorRoleList", authorRoleManageVO)
    }

    /**
     * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertAuthorRole(authorRoleManage: AuthorRoleManage?) {
        insert("authorRoleManageDAO.insertAuthorRole", authorRoleManage)
    }

    /**
     * 수정된 권한 롤 관계정보를 데이터베이스에 반영
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateAuthorRole(authorRoleManage: AuthorRoleManage?) {
        update("authorRoleManageDAO.updateAuthorRole", authorRoleManage)
    }

    /**
     * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteAuthorRole(authorRoleManage: AuthorRoleManage?) {
        delete("authorRoleManageDAO.deleteAuthorRole", authorRoleManage)
    }

    /**
     * 목록조회 카운트를 반환한다
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorRoleListTotCnt(authorRoleManageVO: AuthorRoleManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("authorRoleManageDAO.selectAuthorRoleListTotCnt", authorRoleManageVO)).toInt()
    }
}