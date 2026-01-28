package egovframework.let.sec.rgm.service.impl

import egovframework.let.sec.rgm.service.AuthorGroup
import egovframework.let.sec.rgm.service.AuthorGroupVO
import egovframework.let.sec.rgm.service.EgovAuthorGroupService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 권한그룹에 관한 ServiceImpl 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovAuthorGroupService")
class EgovAuthorGroupServiceImpl : EgovAbstractServiceImpl(), EgovAuthorGroupService {
    @Resource(name = "authorGroupDAO")
    private val authorGroupDAO: AuthorGroupDAO? = null

    /**
     * 그룹별 할당된 권한 목록 조회
     * @param authorGroupVO AuthorGroupVO
     * @return List<AuthorGroupVO>
     * @exception Exception
    </AuthorGroupVO> */
    @Throws(Exception::class)
    override fun selectAuthorGroupList(authorGroupVO: AuthorGroupVO?): MutableList<AuthorGroupVO?>? {
        return authorGroupDAO!!.selectAuthorGroupList(authorGroupVO)
    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     * @param authorGroup AuthorGroup
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun insertAuthorGroup(authorGroup: AuthorGroup?) {
        authorGroupDAO!!.insertAuthorGroup(authorGroup)
    }

    /**
     * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param authorGroup AuthorGroup
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateAuthorGroup(authorGroup: AuthorGroup?) {
        authorGroupDAO!!.updateAuthorGroup(authorGroup)
    }

    /**
     * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
     * @param authorGroup AuthorGroup
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun deleteAuthorGroup(authorGroup: AuthorGroup?) {
        authorGroupDAO!!.deleteAuthorGroup(authorGroup)
    }

    /**
     * 목록조회 카운트를 반환한다
     * @param authorGroupVO AuthorGroupVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectAuthorGroupListTotCnt(authorGroupVO: AuthorGroupVO?): Int {
        return authorGroupDAO!!.selectAuthorGroupListTotCnt(authorGroupVO)
    }
}