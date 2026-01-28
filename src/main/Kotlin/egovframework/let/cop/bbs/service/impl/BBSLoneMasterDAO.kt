package egovframework.let.cop.bbs.service.impl

import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 게시판 속성정보 관리를 위한 데이터 접근 클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.08.25
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("BBSLoneMasterDAO")
class BBSLoneMasterDAO : EgovAbstractMapper() {
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun deleteMaster(boardMaster: BoardMaster?) {
        update("BBSLoneMasterDAO.deleteMaster", boardMaster)
    }

    /**
     * 신규 게시판 속성정보를 등록한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun insertMaster(boardMaster: BoardMaster?): Int {
        return insert("BBSLoneMasterDAO.insertMaster", boardMaster)
    }

    /**
     * 게시판 속성정보 한 건을 상세조회 한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectMaster(vo: BoardMaster?): BoardMasterVO {
        return (BoardMasterVO)<Object> selectOne < kotlin . Any ? > ("BBSLoneMasterDAO.selectMaster", vo)
    }

    /**
     * 게시판 속성정보 목록을 조회한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectMasterList(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return selectList<BoardMasterVO?>("BBSLoneMasterDAO.selectMasterList", vo)
    }

    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectMasterListCnt(vo: BoardMasterVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("BBSLoneMasterDAO.selectMasterListCnt", vo)).toInt()
    }

    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun updateMaster(boardMaster: BoardMaster?) {
        update("BBSLoneMasterDAO.updateMaster", boardMaster)
    }
}
