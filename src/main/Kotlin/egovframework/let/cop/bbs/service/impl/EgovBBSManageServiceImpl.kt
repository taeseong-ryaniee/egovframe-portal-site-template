package egovframework.let.cop.bbs.service.impl

import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.FileVO
import egovframework.let.cop.bbs.service.Board
import egovframework.let.cop.bbs.service.BoardVO
import egovframework.let.cop.bbs.service.EgovBBSManageService
import egovframework.let.utl.fcc.service.EgovDateUtil
import egovframework.let.utl.fcc.service.EgovDateUtil.Companion.today
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 게시물 관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.03.19
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("EgovBBSManageService")
class EgovBBSManageServiceImpl : EgovAbstractServiceImpl(), EgovBBSManageService {
    @Resource(name = "BBSManageDAO")
    private val bbsMngDAO: BBSManageDAO? = null

    @Resource(name = "EgovFileMngService")
    private val fileService: EgovFileMngService? = null

    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    /**
     * 게시물 한 건을 삭제 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.deleteBoardArticle
     */
    @Throws(Exception::class)
    override fun deleteBoardArticle(board: Board) {
        val fvo = FileVO()

        fvo.atchFileId = board.atchFileId

        board.nttSj = "이 글은 작성자에 의해서 삭제되었습니다."

        bbsMngDAO!!.deleteBoardArticle(board)

        if ("" != fvo.atchFileId || fvo.atchFileId != null) {
            fileService!!.deleteAllFileInf(fvo)
        }
    }

    /**
     * 게시판에 게시물 또는 답변 게시물을 등록 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.insertBoardArticle
     */
    @Throws(Exception::class)
    override fun insertBoardArticle(board: Board) {
        // SORT_ORDR는 부모글의 소트 오더와 같게, NTT_NO는 순서대로 부여

        if ("Y" == board.replyAt) {
            // 답글인 경우 1. Parnts를 세팅, 2.Parnts의 sortOrdr을 현재글의 sortOrdr로 가져오도록, 3.nttNo는 현재 게시판의 순서대로
            // replyLc는 부모글의 ReplyLc + 1

            @Suppress("unused") var tmpNttId = 0L // 답글 게시물 ID			

            tmpNttId = bbsMngDAO!!.replyBoardArticle(board)
        } else {
            // 답글이 아닌경우 Parnts = 0, replyLc는 = 0, sortOrdr = nttNo(Query에서 처리)
            board.parnts = "0"
            board.replyLc = "0"
            board.replyAt = "N"

            bbsMngDAO!!.insertBoardArticle(board)
        }
    }

    /**
     * 게시물 대하여 상세 내용을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.selectBoardArticle
     */
    @Throws(Exception::class)
    override fun selectBoardArticle(boardVO: BoardVO): BoardVO? {
        if (boardVO.isPlusCount) {
            val iniqireCo = bbsMngDAO!!.selectMaxInqireCo(boardVO)

            boardVO.inqireCo = iniqireCo
            bbsMngDAO.updateInqireCo(boardVO)
        }

        return bbsMngDAO!!.selectBoardArticle(boardVO)
    }

    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.selectBoardArticles
     */
    @Throws(Exception::class)
    override fun selectBoardArticles(boardVO: BoardVO?, attrbFlag: String?): MutableMap<String?, Any?> {
        val list = bbsMngDAO!!.selectBoardArticleList(boardVO)
        var result: MutableList<BoardVO?>? = ArrayList<BoardVO?>()

        if ("BBSA01" == attrbFlag) {
            // 유효게시판 임
            val today = today

            var vo: BoardVO
            val iter = list.iterator()
            while (iter.hasNext()) {
                vo = iter.next() as BoardVO

                if ("" != vo.ntceBgnde || "" != vo.ntceEndde) {
                    if (EgovDateUtil.getDaysDiff(today, vo.ntceBgnde!!) > 0 || EgovDateUtil.getDaysDiff(
                            today,
                            vo.ntceEndde!!
                        ) < 0
                    ) {
                        // 시작일이 오늘날짜보다 크거나, 종료일이 오늘 날짜보다 작은 경우
                        vo.isExpired = "Y"
                    }
                }
                result!!.add(vo)
            }
        } else {
            result = list
        }

        val cnt = bbsMngDAO.selectBoardArticleListCnt(boardVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 게시물 한 건의 내용을 수정 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.updateBoardArticle
     */
    @Throws(Exception::class)
    override fun updateBoardArticle(board: Board?) {
        bbsMngDAO!!.updateBoardArticle(board)
    }

    /**
     * 방명록 내용을 삭제 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.deleteGuestList
     */
    @Throws(Exception::class)
    override fun deleteGuestList(boardVO: BoardVO?) {
        bbsMngDAO!!.deleteGuestList(boardVO)
    }

    /**
     * 방명록에 대한 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSManageService.selectGuestList
     */
    @Throws(Exception::class)
    override fun selectGuestList(boardVO: BoardVO?): MutableMap<String?, Any?> {
        val result = bbsMngDAO!!.selectGuestList(boardVO)
        val cnt = bbsMngDAO.selectGuestListCnt(boardVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 방명록에 대한 패스워드를 조회 한다.
     *
     * @param board
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun getPasswordInf(board: Board?): String? {
        return bbsMngDAO!!.getPasswordInf(board)
    }
}
