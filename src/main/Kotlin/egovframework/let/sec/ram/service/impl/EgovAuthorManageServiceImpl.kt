package egovframework.let.sec.ram.service.impl

import egovframework.let.sec.ram.service.AuthorManage
import egovframework.let.sec.ram.service.AuthorManageVO
import egovframework.let.sec.ram.service.EgovAuthorManageService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovAuthorManageService")
class EgovAuthorManageServiceImpl : EgovAbstractServiceImpl(), EgovAuthorManageService {
    @Resource(name = "authorManageDAO")
    private val authorManageDAO: AuthorManageDAO? = null

    /**
     * 권한 목록을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
    </AuthorManageVO> */
    @Throws(Exception::class)
    override fun selectAuthorList(authorManageVO: AuthorManageVO?): MutableList<AuthorManageVO?>? {
        return authorManageDAO!!.selectAuthorList(authorManageVO)
    }

    /**
     * 권한을 등록한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun insertAuthor(authorManage: AuthorManage?) {
        authorManageDAO!!.insertAuthor(authorManage)
    }

    /**
     * 권한을 수정한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateAuthor(authorManage: AuthorManage?) {
        authorManageDAO!!.updateAuthor(authorManage)
    }

    /**
     * 권한을 삭제한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun deleteAuthor(authorManage: AuthorManage?) {
        authorManageDAO!!.deleteAuthor(authorManage)
    }

    /**
     * 권한을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return AuthorManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectAuthor(authorManageVO: AuthorManageVO?): AuthorManageVO {
        val resultVO = authorManageDAO!!.selectAuthor(authorManageVO)
        if (resultVO == null) throw processException("info.nodata.msg")
        return resultVO
    }

    /**
     * 권한 목록 카운트를 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectAuthorListTotCnt(authorManageVO: AuthorManageVO?): Int {
        return authorManageDAO!!.selectAuthorListTotCnt(authorManageVO)
    }

    /**
     * 모든 권한목록을 조회한다.
     * @param authorManageVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
    </AuthorManageVO> */
    @Throws(Exception::class)
    override fun selectAuthorAllList(authorManageVO: AuthorManageVO?): MutableList<AuthorManageVO?>? {
        return authorManageDAO!!.selectAuthorAllList(authorManageVO)
    }
}
