package egovframework.let.cop.com.service.impl

import egovframework.let.cop.com.service.BoardUseInf
import egovframework.let.cop.com.service.BoardUseInfVO
import egovframework.let.cop.com.service.EgovBBSUseInfoManageService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 게시판 이용정보를 관리하기 위한 서비스 구현 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("EgovBBSUseInfoManageService")
class EgovBBSUseInfoManageServiceImpl : EgovAbstractServiceImpl(), EgovBBSUseInfoManageService {
    @Resource(name = "BBSUseInfoManageDAO")
    private val bbsUseDAO: BBSUseInfoManageDAO? = null

    /**
     * 게시판 사용 정보를 삭제한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovBBSUseInfoManageService.deleteBBSUseInf
     */
    @Throws(Exception::class)
    override fun deleteBBSUseInf(bdUseInf: BoardUseInf?) {
        bbsUseDAO!!.deleteBBSUseInf(bdUseInf)
    }

    /**
     * 게시판 사용정보를 등록한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovBBSUseInfoManageService.insertBBSUseInf
     */
    @Throws(Exception::class)
    override fun insertBBSUseInf(bdUseInf: BoardUseInf?) {
        bbsUseDAO!!.insertBBSUseInf(bdUseInf)
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovBBSUseInfoManageService.selectBBSUseInfs
     */
    @Throws(Exception::class)
    override fun selectBBSUseInfs(bdUseVO: BoardUseInfVO?): MutableMap<String?, Any?> {
        val result = bbsUseDAO!!.selectBBSUseInfs(bdUseVO)
        val cnt = bbsUseDAO.selectBBSUseInfsCnt(bdUseVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 게시판 사용정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovBBSUseInfoManageService.updateBBSUseInf
     */
    @Throws(Exception::class)
    override fun updateBBSUseInf(bdUseInf: BoardUseInf?) {
        bbsUseDAO!!.updateBBSUseInf(bdUseInf)
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovBBSUseInfoManageService.selectBBSUseInf
     */
    @Throws(Exception::class)
    override fun selectBBSUseInf(bdUseVO: BoardUseInfVO?): BoardUseInfVO? {
        return bbsUseDAO!!.selectBBSUseInf(bdUseVO)
    }

    /**
     * 동호회에 사용되는 게시판 사용정보를 삭제한다.
     *
     * @see EgovBBSUseInfoManageService.deleteBBSUseInfByClub
     */
    @Throws(Exception::class)
    override fun deleteBBSUseInfByClub(bdUseVO: BoardUseInfVO) {
        val result = bbsUseDAO!!.selectBBSUseInfByClub(bdUseVO)

        var bdUseInf: BoardUseInf? = null
        val iter = result.iterator()
        while (iter.hasNext()) {
            bdUseInf = iter.next()

            bdUseInf!!.lastUpdusrId = bdUseVO.lastUpdusrId
            //bdUseInf.setTrgetId(bdUseVO.getClbId());	// 사용자 ID를 넘겨야 함..
            bdUseInf.trgetId = bdUseVO.trgetId

            bbsUseDAO.deleteBBSUseInf(bdUseInf)
        }
    }

    /**
     * 커뮤니티에 사용되는 게시판 사용정보를 삭제한다.
     *
     * @see EgovBBSUseInfoManageService.deleteBBSUseInfByCmmnty
     */
    @Throws(Exception::class)
    override fun deleteBBSUseInfByCmmnty(bdUseVO: BoardUseInfVO) {
        val result = bbsUseDAO!!.selectBBSUseInfByCmmnty(bdUseVO)

        var bdUseInf: BoardUseInf? = null
        val iter = result.iterator()

        while (iter.hasNext()) {
            bdUseInf = iter.next()

            bdUseInf!!.lastUpdusrId = bdUseVO.lastUpdusrId
            //bdUseInf.setTrgetId(bdUseVO.getCmmntyId());	// 사용자 ID를 넘겨야 함..
            bdUseInf.trgetId = bdUseVO.trgetId

            bbsUseDAO.deleteBBSUseInf(bdUseInf)
        }
    }

    /**
     * 동호회에 사용되는 모든 게시판 사용정보를 삭제한다.
     *
     * @see EgovBBSUseInfoManageService.deleteAllBBSUseInfByClub
     */
    @Throws(Exception::class)
    override fun deleteAllBBSUseInfByClub(bdUseVO: BoardUseInfVO?) {
        bbsUseDAO!!.deleteAllBBSUseInfByClub(bdUseVO)
    }

    /**
     * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
     *
     * @see EgovBBSUseInfoManageService.deleteAllBBSUseInfByCmmnty
     */
    @Throws(Exception::class)
    override fun deleteAllBBSUseInfByCmmnty(bdUseVO: BoardUseInfVO?) {
        bbsUseDAO!!.deleteAllBBSUseInfByCmmnty(bdUseVO)
    }

    /**
     * 게시판에 대한 사용정보를 삭제한다.
     *
     * @see EgovBBSUseInfoManageService.deleteBBSUseInfByBoardId
     */
    @Throws(Exception::class)
    override fun deleteBBSUseInfByBoardId(bdUseInf: BoardUseInf?) {
        bbsUseDAO!!.deleteBBSUseInfByBoardId(bdUseInf)
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     *
     * @see EgovBBSUseInfoManageService.selectBBSUseInfsByTrget
     */
    @Throws(Exception::class)
    override fun selectBBSUseInfsByTrget(bdUseVO: BoardUseInfVO?): MutableMap<String?, Any?> {
        val result = bbsUseDAO!!.selectBBSUseInfsByTrget(bdUseVO)
        val cnt = bbsUseDAO.selectBBSUseInfsCntByTrget(bdUseVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     */
    @Throws(Exception::class)
    override fun updateBBSUseInfByTrget(bdUseInf: BoardUseInf?) {
        bbsUseDAO!!.updateBBSUseInfByTrget(bdUseInf)
    }
}
