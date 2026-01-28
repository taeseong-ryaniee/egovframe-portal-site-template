package egovframework.let.cop.com.service.impl

import egovframework.let.cop.com.service.BoardUseInf
import egovframework.let.cop.com.service.BoardUseInfVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 게시판 이용정보를 관리하기 위한 데이터 접근 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("BBSUseInfoManageDAO")
class BBSUseInfoManageDAO : EgovAbstractMapper() {
    /**
     * 게시판 사용 정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBBSUseInf(bdUseInf: BoardUseInf?) {
        update("BBSUseInfoManageDAO.deleteBBSUseInf", bdUseInf)
    }

    /**
     * 커뮤니티에 사용되는 게시판 사용정보 목록을 조회한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfByCmmnty(bdUseVO: BoardUseInfVO?): MutableList<BoardUseInf?>? {
        return selectList<BoardUseInf?>("BBSUseInfoManageDAO.selectBBSUseInfByCmmnty", bdUseVO)
    }

    /**
     * 동호회에 사용되는 게시판 사용정보 목록을 조회한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfByClub(bdUseVO: BoardUseInfVO?): MutableList<BoardUseInf?>? {
        return selectList<BoardUseInf?>("BBSUseInfoManageDAO.selectBBSUseInfByClub", bdUseVO)
    }

    /**
     * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllBBSUseInfByCmmnty(bdUseVO: BoardUseInfVO?) {
        update("BBSUseInfoManageDAO.deleteAllBBSUseInfByCmmnty", bdUseVO)
    }

    /**
     * 동호회에 사용되는 모든 게시판 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllBBSUseInfByClub(bdUseVO: BoardUseInfVO?) {
        update("BBSUseInfoManageDAO.deleteAllBBSUseInfByClub", bdUseVO)
    }

    /**
     * 게시판 사용정보를 등록한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertBBSUseInf(bdUseInf: BoardUseInf?) {
        insert("BBSUseInfoManageDAO.insertBBSUseInf", bdUseInf)
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfs(bdUseVO: BoardUseInfVO?): MutableList<BoardUseInfVO?>? {
        return selectList<BoardUseInfVO?>("BBSUseInfoManageDAO.selectBBSUseInfs", bdUseVO)
    }

    /**
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfsCnt(bdUseVO: BoardUseInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("BBSUseInfoManageDAO.selectBBSUseInfsCnt", bdUseVO)).toInt()
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInf(bdUseVO: BoardUseInfVO?): BoardUseInfVO {
        return (BoardUseInfVO)<Object> selectOne < kotlin . Any ? > ("BBSUseInfoManageDAO.selectBBSUseInf", bdUseVO)
    }

    /**
     * 게시판 사용정보를 수정한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateBBSUseInf(bdUseInf: BoardUseInf?) {
        update("BBSUseInfoManageDAO.updateBBSUseInf", bdUseInf)
    }

    /**
     * 게시판에 대한 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBBSUseInfByBoardId(bdUseInf: BoardUseInf?) {
        update("BBSUseInfoManageDAO.deleteBBSUseInfByBoardId", bdUseInf)
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfsByTrget(bdUseVO: BoardUseInfVO?): MutableList<BoardUseInfVO?>? {
        return selectList<BoardUseInfVO?>("BBSUseInfoManageDAO.selectBBSUseInfsByTrget", bdUseVO)
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 전체 건수를 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfsCntByTrget(bdUseVO: BoardUseInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("BBSUseInfoManageDAO.selectBBSUseInfsCntByTrget", bdUseVO)).toInt()
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateBBSUseInfByTrget(bdUseInf: BoardUseInf?) {
        update("BBSUseInfoManageDAO.updateBBSUseInfByTrget", bdUseInf)
    }
}
