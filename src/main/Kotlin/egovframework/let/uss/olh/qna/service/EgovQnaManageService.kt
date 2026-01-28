package egovframework.let.uss.olh.qna.service

/**
 *
 * Q&A를 처리하는 서비스 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovQnaManageService {
    /**
     * Q&A 글을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaListDetail(vo: QnaManageVO?): QnaManageVO?

    /**
     * Q&A 조회수를 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateQnaInqireCo(vo: QnaManageVO?)

    /**
     * Q&A 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaList(searchVO: QnaManageDefaultVO?): MutableList<*>?

    /**
     * Q&A 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     * @exception
     */
    fun selectQnaListTotCnt(searchVO: QnaManageDefaultVO?): Int

    /**
     * Q&A 글을 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertQnaCn(vo: QnaManageVO?)

    /**
     * Q&A  작성비밀번호를 확인한다.
     * @param vo
     * @return 확인결과
     * @exception
     */
    fun selectQnaPasswordConfirmCnt(vo: QnaManageVO?): Int

    /**
     * Q&A 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateQnaCn(vo: QnaManageVO?)

    /**
     * Q&A 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteQnaCn(vo: QnaManageVO?)


    /**
     * Q&A 답변 글을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaAnswerListDetail(vo: QnaManageVO?): QnaManageVO?


    /**
     * Q&A 답변 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaAnswerList(searchVO: QnaManageDefaultVO?): MutableList<*>?

    /**
     * Q&A 답변 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     * @exception
     */
    fun selectQnaAnswerListTotCnt(searchVO: QnaManageDefaultVO?): Int

    /**
     * Q&A 답변 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateQnaCnAnswer(vo: QnaManageVO?)
}

