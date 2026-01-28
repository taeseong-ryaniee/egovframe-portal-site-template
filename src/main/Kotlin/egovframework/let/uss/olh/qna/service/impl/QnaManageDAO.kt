package egovframework.let.uss.olh.qna.service.impl

import egovframework.let.uss.olh.qna.service.QnaManageDefaultVO
import egovframework.let.uss.olh.qna.service.QnaManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 *
 * Q&A정보를 처리하는 DAO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("QnaManageDAO")
class QnaManageDAO : EgovAbstractMapper() {
    /**
     * Q&A 글 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaListDetail(vo: QnaManageVO?): QnaManageVO? {
        return selectOne<Any?>("QnaManageDAO.selectQnaListDetail", vo) as QnaManageVO?
    }

    /**
     * Q&A 글을 수정한다.(조회수를 수정)
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateQnaInqireCo(vo: QnaManageVO?) {
        update("QnaManageDAO.updateQnaInqireCo", vo)
    }

    /**
     * Q&A 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaList(searchVO: QnaManageDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QnaManageDAO.selectQnaList", searchVO)
    }

    /**
     * Q&A 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     */
    fun selectQnaListTotCnt(searchVO: QnaManageDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QnaManageDAO.selectQnaListTotCnt", searchVO)).toInt()
    }

    /**
     * Q&A 글을 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertQnaCn(vo: QnaManageVO?) {
        insert("QnaManageDAO.insertQnaCn", vo)
    }

    /**
     * 작성비밀번호를 확인한다.
     * @param vo
     * @return 글 총 갯수
     */
    fun selectQnaPasswordConfirmCnt(vo: QnaManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QnaManageDAO.selectQnaPasswordConfirmCnt", vo)).toInt()
    }

    /**
     * Q&A 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateQnaCn(vo: QnaManageVO?) {
        update("QnaManageDAO.updateQnaCn", vo)
    }

    /**
     * Q&A 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteQnaCn(vo: QnaManageVO?) {
        delete("QnaManageDAO.deleteQnaCn", vo)
    }


    /**
     * Q&A 답변 글 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaAnswerListDetail(vo: QnaManageVO?): QnaManageVO? {
        return selectOne<Any?>("QnaManageDAO.selectQnaAnswerListDetail", vo) as QnaManageVO?
    }


    /**
     * Q&A 답변 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectQnaAnswerList(searchVO: QnaManageDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QnaManageDAO.selectQnaAnswerList", searchVO)
    }

    /**
     * Q&A 답변 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     */
    fun selectQnaAnswerListTotCnt(searchVO: QnaManageDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QnaManageDAO.selectQnaAnswerListTotCnt", searchVO)).toInt()
    }

    /**
     * Q&A 답변 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateQnaCnAnswer(vo: QnaManageVO?) {
        update("QnaManageDAO.updateQnaCnAnswer", vo)
    }
}
