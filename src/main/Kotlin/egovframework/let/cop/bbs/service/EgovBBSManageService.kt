package egovframework.let.cop.bbs.service

/**
 * 게시물 관리를 위한 서비스 인터페이스  클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.19
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovBBSManageService {
    /**
     * 게시물 한 건을 삭제 한다.
     *
     * @param Board
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun deleteBoardArticle(Board: Board?)

    /**
     * 방명록 내용을 삭제 한다.
     *
     * @param boardVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun deleteGuestList(boardVO: BoardVO?)

    /**
     * 방명록에 대한 패스워드를 조회 한다.
     * @return
     *
     * @param Board
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun getPasswordInf(Board: Board?): String?

    /**
     * 게시판에 게시물 또는 답변 게시물을 등록 한다.
     *
     * @param Board
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun insertBoardArticle(Board: Board?)

    /**
     * 게시물 대하여 상세 내용을 조회 한다.
     * @return
     *
     * @param boardVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectBoardArticle(boardVO: BoardVO?): BoardVO?

    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     * @return
     *
     * @param boardVO
     * @param attrbFlag
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectBoardArticles(boardVO: BoardVO?, attrbFlag: String?): MutableMap<String?, Any?>?

    /**
     * 방명록에 대한 목록을 조회 한다.
     * @return
     *
     * @param boardVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectGuestList(boardVO: BoardVO?): MutableMap<String?, Any?>?

    /**
     * 게시물 한 건의 내용을 수정 한다.
     *
     * @param Board
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun updateBoardArticle(Board: Board?)
}