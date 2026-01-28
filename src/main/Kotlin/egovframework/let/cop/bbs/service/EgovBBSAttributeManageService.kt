package egovframework.let.cop.bbs.service

/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovBBSAttributeManageService {
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * @param BoardMaster
     *
     * @param boardMaster
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun deleteBBSMasterInf(boardMaster: BoardMaster?)

    /**
     * 신규 게시판 속성정보를 생성한다.
     * @param BoardMaster
     *
     * @param boardMaster
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun insertBBSMastetInf(boardMaster: BoardMaster?): String?

    /**
     * 유효한 게시판 마스터 정보를 호출한다.
     * @param searchVO
     * @return
     *
     * @param vo
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectAllBBSMasteInf(vo: BoardMasterVO?): MutableList<BoardMasterVO?>?

    /**
     * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
     * @return
     *
     * @param vo
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectAllBdMstrByTrget(vo: BoardMasterVO?): MutableList<BoardMasterVO?>?

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * @param BoardMasterVO
     *
     * @param searchVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectBBSMasterInf(searchVO: BoardMaster?): BoardMasterVO?

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * @param BoardMasterVO
     *
     * @param searchVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectBBSMasterInfs(searchVO: BoardMasterVO?): MutableMap<String?, Any?>?

    /**
     * 사용중인 게시판 속성 정보의 목록을 조회 한다.
     * @param BoardMasterVO
     *
     * @param vo
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectBdMstrListByTrget(vo: BoardMasterVO?): MutableMap<String?, Any?>?

    /**
     * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
     * @return
     *
     * @param vo
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectNotUsedBdMstrList(vo: BoardMasterVO?): MutableMap<String?, Any?>?

    /**
     * 게시판 속성정보를 수정한다.
     * @param BoardMaster
     *
     * @param boardMaster
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun updateBBSMasterInf(boardMaster: BoardMaster?)

    /**
     * 템플릿의 유효여부를 점검한다.
     * @param BoardMasterVO
     *
     * @param searchVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun validateTemplate(searchVO: BoardMasterVO?)
}