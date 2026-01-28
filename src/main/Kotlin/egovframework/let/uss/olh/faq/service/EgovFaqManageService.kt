package egovframework.let.uss.olh.faq.service

/**
 *
 * FAQ를 처리하는 서비스 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovFaqManageService {
    /**
     * FAQ 글을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectFaqListDetail(vo: FaqManageVO?): FaqManageVO?

    /**
     * 조회수를 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateFaqInqireCo(vo: FaqManageVO?)


    /**
     * FAQ 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectFaqList(searchVO: FaqManageDefaultVO?): MutableList<*>?

    /**
     * FAQ 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     */
    fun selectFaqListTotCnt(searchVO: FaqManageDefaultVO?): Int

    /**
     * FAQ글ㅇ르 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertFaqCn(vo: FaqManageVO?)


    /**
     * FAQ 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateFaqCn(vo: FaqManageVO?)

    /**
     * FAQ 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteFaqCn(vo: FaqManageVO?)
}
