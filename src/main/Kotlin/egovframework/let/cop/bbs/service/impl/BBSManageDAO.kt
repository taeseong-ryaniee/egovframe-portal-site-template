package egovframework.let.cop.bbs.service.impl

import egovframework.let.cop.bbs.service.Board
import egovframework.let.cop.bbs.service.BoardVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 게시물 관리를 위한 데이터 접근 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.19
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("BBSManageDAO")
class BBSManageDAO : EgovAbstractMapper() {
    /**
     * 게시판에 게시물을 등록 한다.
     *
     * @param board
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertBoardArticle(board: Board) {
        val nttId: Long = ((Long)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectMaxNttId")).toLong()
        board.nttId = nttId

        insert("BBSManageDAO.insertBoardArticle", board)
    }

    /**
     * 게시판에 답변 게시물을 등록 한다.
     *
     * @param board
     * @throws Exception
     */
    @Throws(Exception::class)
    fun replyBoardArticle(board: Board): Long {
        val nttId: Long = ((Long)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectMaxNttId")).toLong()
        board.nttId = nttId

        insert("BBSManageDAO.replyBoardArticle", board)


        //----------------------------------------------------------
        // 현재 글 이후 게시물에 대한 NTT_NO를 증가 (정렬을 추가하기 위해)
        //----------------------------------------------------------
        //String parentId = board.getParnts();
        val nttNo: Long = ((Long)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.getParentNttNo", board)).toLong()

        board.nttNo = nttNo
        update("BBSManageDAO.updateOtherNttNo", board)

        board.nttNo = nttNo + 1
        update("BBSManageDAO.updateNttNo", board)

        return nttId
    }

    /**
     * 게시물 한 건에 대하여 상세 내용을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBoardArticle(boardVO: BoardVO?): BoardVO {
        return (BoardVO)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectBoardArticle", boardVO)
    }

    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBoardArticleList(boardVO: BoardVO?): MutableList<BoardVO?>? {
        return selectList<BoardVO?>("BBSManageDAO.selectBoardArticleList", boardVO)
    }

    /**
     * 조건에 맞는 게시물 목록에 대한 전체 건수를 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBoardArticleListCnt(boardVO: BoardVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectBoardArticleListCnt", boardVO)).toInt()
    }

    /**
     * 게시물 한 건의 내용을 수정 한다.
     *
     * @param board
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateBoardArticle(board: Board?) {
        update("BBSManageDAO.updateBoardArticle", board)
    }

    /**
     * 게시물 한 건을 삭제 한다.
     *
     * @param board
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBoardArticle(board: Board?) {
        update("BBSManageDAO.deleteBoardArticle", board)
    }

    /**
     * 게시물에 대한 조회 건수를 수정 한다.
     *
     * @param board
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateInqireCo(boardVO: BoardVO?) {
        update("BBSManageDAO.updateInqireCo", boardVO)
    }

    /**
     * 게시물에 대한 현재 조회 건수를 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectMaxInqireCo(boardVO: BoardVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectMaxInqireCo", boardVO)).toInt()
    }

    /**
     * 게시판에 대한 목록을 정렬 순서로 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNoticeListForSort(board: Board?): MutableList<BoardVO?>? {
        return selectList<BoardVO?>("BBSManageDAO.selectNoticeListForSort", board)
    }

    /**
     * 게사판에 대한 정렬 순서를 수정 한다.
     *
     * @param sortList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateSortOrder(sortList: MutableList<BoardVO?>) {
        var vo: BoardVO?
        val iter = sortList.iterator()
        while (iter.hasNext()) {
            vo = iter.next()
            update("BBSManageDAO.updateSortOrder", vo)
        }
    }

    /**
     * 게시판에 대한 현재 게시물 번호의 최대값을 구한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNoticeItemForSort(board: Board?): Long {
        return ((Long)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectNoticeItemForSort", board)).toLong()
    }

    /**
     * 방명록에 대한 목록을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectGuestList(boardVO: BoardVO?): MutableList<BoardVO?>? {
        return selectList<BoardVO?>("BBSManageDAO.selectGuestList", boardVO)
    }

    /**
     * 방명록에 대한 목록 건수를 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectGuestListCnt(boardVO: BoardVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.selectGuestListCnt", boardVO)).toInt()
    }

    /**
     * 방명록 내용을 삭제 한다.
     *
     * @param boardVO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteGuestList(boardVO: BoardVO?) {
        update("BBSManageDAO.deleteGuestList", boardVO)
    }

    /**
     * 방명록에 대한 패스워드를 조회 한다.
     *
     * @param board
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getPasswordInf(board: Board?): String {
        return (String)<Object> selectOne < kotlin . Any ? > ("BBSManageDAO.getPasswordInf", board)
    }
}
