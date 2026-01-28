package egovframework.let.cop.com.service

/**
 * 게시판 이용정보를 관리하기 위한 서비스 인터페이스 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovBBSUseInfoManageService {
    /**
     * 게시판 사용 정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBBSUseInf(bdUseInf: BoardUseInf?)

    /**
     * 커뮤니티에 사용되는 게시판 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBBSUseInfByCmmnty(bdUseVO: BoardUseInfVO?)

    /**
     * 동호회에 사용되는 게시판 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBBSUseInfByClub(bdUseVO: BoardUseInfVO?)

    /**
     * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllBBSUseInfByCmmnty(bdUseVO: BoardUseInfVO?)

    /**
     * 동호회에 사용되는 모든 게시판 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllBBSUseInfByClub(bdUseVO: BoardUseInfVO?)

    /**
     * 게시판 사용정보를 등록한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertBBSUseInf(bdUseInf: BoardUseInf?)

    /**
     * 게시판 사용정보 목록을 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfs(bdUseVO: BoardUseInfVO?): MutableMap<String?, Any?>?

    /**
     * 게시판 사용정보를 수정한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateBBSUseInf(bdUseInf: BoardUseInf?)

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInf(bdUseVO: BoardUseInfVO?): BoardUseInfVO?

    /**
     * 게시판에 대한 사용정보를 삭제한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteBBSUseInfByBoardId(bdUseInf: BoardUseInf?)

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     *
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectBBSUseInfsByTrget(bdUseVO: BoardUseInfVO?): MutableMap<String?, Any?>?

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     *
     * @param bdUseInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateBBSUseInfByTrget(bdUseInf: BoardUseInf?)
}
