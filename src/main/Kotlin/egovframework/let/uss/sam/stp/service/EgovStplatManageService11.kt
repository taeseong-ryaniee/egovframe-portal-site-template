package egovframework.let.uss.sam.stp.service

/**
 *
 * 약관내용을 처리하는 서비스 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovStplatManageService {
    /**
     * 약관정보 글을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectStplatDetail(vo: StplatManageVO?): StplatManageVO?

    /**
     * 약관정보 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectStplatList(searchVO: StplatManageDefaultVO?): MutableList<*>?

    /**
     * 약관정보 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     */
    fun selectStplatListTotCnt(searchVO: StplatManageDefaultVO?): Int

    /**
     * 약관정보 글을 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertStplatCn(vo: StplatManageVO?)


    /**
     * 약관정보 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateStplatCn(vo: StplatManageVO?)

    /**
     * 약관정보 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteStplatCn(vo: StplatManageVO?)
}
