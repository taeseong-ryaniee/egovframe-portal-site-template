package egovframework.let.sec.ram.service.impl

import egovframework.let.sec.ram.service.AuthorManage
import egovframework.let.sec.ram.service.AuthorManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 권한관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("authorManageDAO")
class AuthorManageDAO : EgovAbstractMapper() {
    /**
     * 권한목록을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
    </AuthorManageVO> */
    @Throws(Exception::class)
    fun selectAuthorList(authorManageVO: AuthorManageVO?): MutableList<AuthorManageVO?>? {
        return selectList<AuthorManageVO?>("authorManageDAO.selectAuthorList", authorManageVO)
    }

    /**
     * 권한을 등록한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertAuthor(authorManage: AuthorManage?) {
        insert("authorManageDAO.insertAuthor", authorManage)
    }

    /**
     * 권한을 수정한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateAuthor(authorManage: AuthorManage?) {
        update("authorManageDAO.updateAuthor", authorManage)
    }

    /**
     * 권한을 삭제한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteAuthor(authorManage: AuthorManage?) {
        delete("authorManageDAO.deleteAuthor", authorManage)
    }

    /**
     * 권한을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return AuthorManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthor(authorManageVO: AuthorManageVO?): AuthorManageVO? {
        return selectOne<Any?>("authorManageDAO.selectAuthor", authorManageVO) as AuthorManageVO?
    }

    /**
     * 권한목록 총 갯수를 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorListTotCnt(authorManageVO: AuthorManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("authorManageDAO.selectAuthorListTotCnt", authorManageVO)).toInt()
    }

    /**
     * 모든 권한목록을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
    </AuthorManageVO> */
    @Throws(Exception::class)
    fun selectAuthorAllList(authorManageVO: AuthorManageVO?): MutableList<AuthorManageVO?>? {
        return selectList<AuthorManageVO?>("authorManageDAO.selectAuthorAllList", authorManageVO)
    }
}
