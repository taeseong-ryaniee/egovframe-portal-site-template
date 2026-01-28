package egovframework.let.uss.olh.faq.service.impl

import egovframework.let.uss.olh.faq.service.EgovFaqManageService
import egovframework.let.uss.olh.faq.service.FaqManageDefaultVO
import egovframework.let.uss.olh.faq.service.FaqManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 *
 * FAQ를 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("FaqManageService")
class EgovFaqManageServiceImpl : EgovAbstractServiceImpl(), EgovFaqManageService {
    @Resource(name = "FaqManageDAO")
    private val faqManageDAO: FaqManageDAO? = null

    /** ID Generation  */
    @Resource(name = "egovFaqManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null


    /**
     * FAQ 글을 조회한다.
     * @param vo - 조회할 정보가 담긴 FaqManageVO
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectFaqListDetail(vo: FaqManageVO?): FaqManageVO {
        val resultVO = faqManageDAO!!.selectFaqListDetail(vo)
        if (resultVO == null) throw processException("info.nodata.msg")
        return resultVO
    }

    /**
     * FAQ 조회수를 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateFaqInqireCo(vo: FaqManageVO?) {
        faqManageDAO!!.updateFaqInqireCo(vo)
    }

    /**
     * FAQ 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectFaqList(searchVO: FaqManageDefaultVO?): MutableList<*>? {
        return faqManageDAO!!.selectFaqList(searchVO)
    }

    /**
     * FAQ 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     * @exception
     */
    override fun selectFaqListTotCnt(searchVO: FaqManageDefaultVO?): Int {
        return faqManageDAO!!.selectFaqListTotCnt(searchVO)
    }

    /**
     * FAQ 글을 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun insertFaqCn(vo: FaqManageVO) {
        val newsId = idgenService!!.getNextStringId()

        vo.setFaqId(newsId)

        faqManageDAO!!.insertFaqCn(vo)
    }

    /**
     * FAQ 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateFaqCn(vo: FaqManageVO?) {
        faqManageDAO!!.updateFaqCn(vo)
    }

    /**
     * FAQ 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun deleteFaqCn(vo: FaqManageVO?) {
        faqManageDAO!!.deleteFaqCn(vo)
    }
}
