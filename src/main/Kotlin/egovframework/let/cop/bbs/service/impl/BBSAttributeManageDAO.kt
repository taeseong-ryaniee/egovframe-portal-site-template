package egovframework.let.cop.bbs.service.impl

import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 게시판 속성정보 관리를 위한 데이터 접근 클래스
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("BBSAttributeManageDAO")
class BBSAttributeManageDAO : EgovAbstractMapper() {
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun deleteBBSMasterInf(boardMaster: BoardMaster?) {
        update("BBSAttributeManageDAO.deleteBBSMasterInf", boardMaster)
    }

    /**
     * 신규 게시판 속성정보를 등록한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun insertBBSMasterInf(boardMaster: BoardMaster?): Int {
        return insert("BBSAttributeManageDAO.insertBBSMasterInf", boardMaster)
    }

    /**
     * 게시판 속성정보 한 건을 상세조회 한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectBBSMasterInf(vo: BoardMaster?): BoardMasterVO? {
        return selectOne<Any?>("BBSAttributeManageDAO.selectBBSMasterInf", vo) as BoardMasterVO?
    }

    /**
     * 게시판 속성정보 목록을 조회한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectBBSMasterInfs(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return selectList<BoardMasterVO?>("BBSAttributeManageDAO.selectBBSMasterInfs", vo)
    }

    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSMasterInfsCnt(vo: BoardMasterVO?): Int {
        return (selectOne<kotlin.Any?>("BBSAttributeManageDAO.selectBBSMasterInfsCnt", vo) as Int?)!!
    }

    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun updateBBSMasterInf(boardMaster: BoardMaster?) {
        update("BBSAttributeManageDAO.updateBBSMasterInf", boardMaster)
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun validateTemplate(vo: BoardMasterVO?): Boolean {
        return true
    }

    /**
     * 유효한 게시판 목록을 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAllBBSMasteInf(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        // 커뮤니티, 동호회의 게시판이 나오지 않도록 LETTNBBSUSE 테이블과 Join 필요
        return selectList<BoardMasterVO?>("BBSAttributeManageDAO.selectAllBBSMaster", vo)
    }

    /**
     * 사용중인 게시판 속성정보 목록을 조회한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectBdMstrListByTrget(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return selectList<BoardMasterVO?>("BBSAttributeManageDAO.selectBdMstrListByTrget", vo)
    }

    /**
     * 사용중인 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBdMstrListCntByTrget(vo: BoardMasterVO?): Int {
        return (selectOne<kotlin.Any?>("BBSAttributeManageDAO.selectBdMstrListCntByTrget", vo) as Int?)!!
    }

    /**
     * 커뮤니티, 동호회등 게시판 사용등록이 된 게시판 목록 전체를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAllBdMstrByTrget(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return selectList<BoardMasterVO?>("BBSAttributeManageDAO.selectAllBdMstrByTrget", vo)
    }

    /**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectNotUsedBdMstrList(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return selectList<BoardMasterVO?>("BBSAttributeManageDAO.selectNotUsedBdMstrList", vo)
    }

    /**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNotUsedBdMstrListCnt(vo: BoardMasterVO?): Int {
        return (selectOne<kotlin.Any?>("BBSAttributeManageDAO.selectNotUsedBdMstrListCnt", vo) as Int?)!!
    }
}
