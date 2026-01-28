package egovframework.let.cop.bbs.service

/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.08.25
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovBBSLoneMasterService {
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * @param BoardMaster
     *
     * @param boardMaster
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun deleteMaster(boardMaster: BoardMaster?)

    /**
     * 신규 게시판 속성정보를 생성한다.
     * @param BoardMaster
     *
     * @param boardMaster
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun insertMaster(boardMaster: BoardMaster?): String?

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * @param BoardMasterVO
     *
     * @param searchVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectMaster(searchVO: BoardMaster?): BoardMasterVO?

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * @param BoardMasterVO
     *
     * @param searchVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectMasterList(searchVO: BoardMasterVO?): MutableMap<String?, Any?>?

    /**
     * 게시판 속성정보를 수정한다.
     * @param BoardMaster
     *
     * @param boardMaster
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun updateMaster(boardMaster: BoardMaster?)
}