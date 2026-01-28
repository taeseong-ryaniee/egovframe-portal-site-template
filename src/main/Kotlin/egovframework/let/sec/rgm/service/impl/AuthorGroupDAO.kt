package egovframework.let.sec.rgm.service.impl

import egovframework.let.sec.rgm.service.AuthorGroup
import egovframework.let.sec.rgm.service.AuthorGroupVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 권한그룹에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("authorGroupDAO")
class AuthorGroupDAO : EgovAbstractMapper() {
    /**
     * 그룹별 할당된 권한 목록 조회
     * @param authorGroupVO AuthorGroupVO
     * @return List<AuthorGroupVO>
     * @exception Exception
    </AuthorGroupVO> */
    @Throws(Exception::class)
    fun selectAuthorGroupList(authorGroupVO: AuthorGroupVO?): MutableList<AuthorGroupVO?>? {
        return selectList<AuthorGroupVO?>("authorGroupDAO.selectAuthorGroupList", authorGroupVO)
    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     * @param authorGroup AuthorGroup
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertAuthorGroup(authorGroup: AuthorGroup?) {
        insert("authorGroupDAO.insertAuthorGroup", authorGroup)
    }

    /**
     * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param authorGroup AuthorGroup
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateAuthorGroup(authorGroup: AuthorGroup?) {
        update("authorGroupDAO.updateAuthorGroup", authorGroup)
    }

    /**
     * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
     * @param authorGroup AuthorGroup
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteAuthorGroup(authorGroup: AuthorGroup?) {
        delete("authorGroupDAO.deleteAuthorGroup", authorGroup)
    }

    /**
     * 그룹권한목록 총 갯수를 조회한다.
     * @param authorGroupVO AuthorGroupVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectAuthorGroupListTotCnt(authorGroupVO: AuthorGroupVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("authorGroupDAO.selectAuthorGroupListTotCnt", authorGroupVO)).toInt()
    }
}