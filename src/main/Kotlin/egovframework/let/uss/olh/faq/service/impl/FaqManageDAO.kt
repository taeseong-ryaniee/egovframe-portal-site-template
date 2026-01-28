package egovframework.let.uss.olh.faq.service.impl

import egovframework.let.uss.olh.faq.service.FaqManageDefaultVO
import egovframework.let.uss.olh.faq.service.FaqManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 *
 * FAQ를 처리하는 DAO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("FaqManageDAO")
class FaqManageDAO : EgovAbstractMapper() {
    /**
     * FAQ 글 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectFaqListDetail(vo: FaqManageVO?): FaqManageVO? {
        return selectOne<Any?>("FaqManageDAO.selectFaqListDetail", vo) as FaqManageVO?
    }

    /**
     * FAQ 조회수를 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateFaqInqireCo(vo: FaqManageVO?) {
        update("FaqManageDAO.updateFaqInqireCo", vo)
    }

    /**
     * FAQ 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectFaqList(searchVO: FaqManageDefaultVO?): MutableList<*>? {
        return selectList<Any?>("FaqManageDAO.selectFaqList", searchVO)
    }

    /**
     * FAQ 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     */
    fun selectFaqListTotCnt(searchVO: FaqManageDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("FaqManageDAO.selectFaqListTotCnt", searchVO)).toInt()
    }

    /**
     * FAQ 글을 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertFaqCn(vo: FaqManageVO?) {
        insert("FaqManageDAO.insertFaqCn", vo)
    }

    /**
     * FAQ 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateFaqCn(vo: FaqManageVO?) {
        update("FaqManageDAO.updateFaqCn", vo)
    }

    /**
     * FAQ 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteFaqCn(vo: FaqManageVO?) {
        delete("FaqManageDAO.deleteFaqCn", vo)
    }
}
