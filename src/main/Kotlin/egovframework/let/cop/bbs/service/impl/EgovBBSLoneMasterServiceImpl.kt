package egovframework.let.cop.bbs.service.impl

import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import egovframework.let.cop.bbs.service.EgovBBSLoneMasterService
import egovframework.let.cop.com.service.BoardUseInf
import egovframework.let.cop.com.service.impl.BBSUseInfoManageDAO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.08.25
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("EgovBBSLoneMasterService")
class EgovBBSLoneMasterServiceImpl : EgovAbstractServiceImpl(), EgovBBSLoneMasterService {
    @Resource(name = "BBSLoneMasterDAO")
    private val masterDAO: BBSLoneMasterDAO? = null

    @Resource(name = "BBSUseInfoManageDAO")
    private val bbsUseDAO: BBSUseInfoManageDAO? = null

    @Resource(name = "egovBBSMstrIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     */
    @Throws(Exception::class)
    override fun deleteMaster(boardMaster: BoardMaster) {
        masterDAO!!.deleteMaster(boardMaster)

        val bdUseInf = BoardUseInf()

        bdUseInf.bbsId = boardMaster.bbsId
        bdUseInf.lastUpdusrId = boardMaster.lastUpdusrId

        bbsUseDAO!!.deleteBBSUseInfByBoardId(bdUseInf)
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     */
    @Throws(Exception::class)
    override fun insertMaster(boardMaster: BoardMaster): String? {
        val bbsId = idgenService!!.getNextStringId()

        boardMaster.bbsId = bbsId

        masterDAO!!.insertMaster(boardMaster)

        //----------------------------------------------
        // 게시판 사용 등록 (시스템)
        //----------------------------------------------
        val bdUseInf = BoardUseInf()

        bdUseInf.bbsId = bbsId
        bdUseInf.trgetId = "SYSTEM_DEFAULT_BOARD"
        bdUseInf.registSeCode = "REGC01"
        bdUseInf.frstRegisterId = boardMaster.frstRegisterId
        bdUseInf.useAt = "Y"

        bbsUseDAO!!.insertBBSUseInf(bdUseInf)

        return bbsId
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     */
    @Throws(Exception::class)
    override fun selectMaster(searchVO: BoardMaster?): BoardMasterVO? {
        return masterDAO!!.selectMaster(searchVO)
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     */
    @Throws(Exception::class)
    override fun selectMasterList(searchVO: BoardMasterVO?): MutableMap<String?, Any?> {
        val result = masterDAO!!.selectMasterList(searchVO)
        val cnt = masterDAO.selectMasterListCnt(searchVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 게시판 속성정보를 수정한다.
     */
    @Throws(Exception::class)
    override fun updateMaster(boardMaster: BoardMaster?) {
        masterDAO!!.updateMaster(boardMaster)
    }
}
