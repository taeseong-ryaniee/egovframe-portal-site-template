package egovframework.let.cop.bbs.service.impl

import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 2단계 기능 추가 (댓글관리, 만족도조사) 관리를 위한 데이터 접근 클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.06.26
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("BBSAddedOptionsDAO")
class BBSAddedOptionsDAO : EgovAbstractMapper() {
    /**
     * 신규 게시판 추가기능 정보를 등록한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun insertAddedOptionsInf(boardMaster: BoardMaster?): Int {
        return insert("BBSAddedOptionsDAO.insertAddedOptionsInf", boardMaster)
    }

    /**
     * 게시판 추가기능 정보 한 건을 상세조회 한다.
     *
     * @param BoardMasterVO
     */
    @Throws(Exception::class)
    fun selectAddedOptionsInf(vo: BoardMaster?): BoardMasterVO {
        return (BoardMasterVO)<Object> selectOne < kotlin . Any ? > ("BBSAddedOptionsDAO.selectAddedOptionsInf", vo)
    }

    /**
     * 게시판 추가기능 정보를 수정한다.
     *
     * @param BoardMaster
     */
    @Throws(Exception::class)
    fun updateAddedOptionsInf(boardMaster: BoardMaster?) {
        update("BBSAddedOptionsDAO.updateAddedOptionsInf", boardMaster)
    }
}
