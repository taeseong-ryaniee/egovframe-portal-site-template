package egovframework.let.cop.bbs.service.impl

import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.BoardMasterVO
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService
import egovframework.let.cop.com.service.BoardUseInf
import egovframework.let.cop.com.service.EgovUserInfManageService
import egovframework.let.cop.com.service.UserInfVO
import egovframework.let.cop.com.service.impl.BBSUseInfoManageDAO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.24
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("EgovBBSAttributeManageService")
class EgovBBSAttributeManageServiceImpl : EgovAbstractServiceImpl(), EgovBBSAttributeManageService {
    @Resource(name = "BBSAttributeManageDAO")
    private val attrbMngDAO: BBSAttributeManageDAO? = null

    @Resource(name = "BBSUseInfoManageDAO")
    private val bbsUseDAO: BBSUseInfoManageDAO? = null

    @Resource(name = "EgovUserInfManageService")
    private val userService: EgovUserInfManageService? = null

    @Resource(name = "egovBBSMstrIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    // ---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    // ---------------------------------
    @Resource(name = "BBSAddedOptionsDAO")
    private val addedOptionsDAO: BBSAddedOptionsDAO? = null

    /**/ ------------------------------- */
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.deleteBBSMasterInf
     */
    @Throws(Exception::class)
    override fun deleteBBSMasterInf(boardMaster: BoardMaster) {
        attrbMngDAO!!.deleteBBSMasterInf(boardMaster)

        val bdUseInf = BoardUseInf()

        bdUseInf.bbsId = boardMaster.bbsId
        bdUseInf.lastUpdusrId = boardMaster.lastUpdusrId

        bbsUseDAO!!.deleteBBSUseInfByBoardId(bdUseInf)
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.insertBBSMastetInf
     */
    @Throws(Exception::class)
    override fun insertBBSMastetInf(boardMaster: BoardMaster): String? {
        val bbsId = idgenService!!.getNextStringId()

        boardMaster.bbsId = bbsId

        attrbMngDAO!!.insertBBSMasterInf(boardMaster)

        // ---------------------------------
        // 2009.06.26 : 2단계 기능 추가
        // ---------------------------------
        if (boardMaster.option == "comment" || boardMaster.option == "stsfdg") {
            addedOptionsDAO!!.insertAddedOptionsInf(boardMaster)
        }

        /**/ -------------------------------* /
                if ("Y" == boardMaster.bbsUseFlag) {
                    var bdUseInf = BoardUseInf()

                    bdUseInf.bbsId = bbsId
                    bdUseInf.trgetId = boardMaster.trgetId
                    bdUseInf.registSeCode = boardMaster.registSeCode
                    bdUseInf.frstRegisterId = boardMaster.frstRegisterId
                    bdUseInf.useAt = "Y"

                    bbsUseDAO!!.insertBBSUseInf(bdUseInf)

                    val userVO = UserInfVO()
                    userVO.trgetId = boardMaster.trgetId

                    var tmpList: MutableList<UserInfVO?>? = null
                    var iter: MutableIterator<UserInfVO?>? = null

                    if ("REGC05" == boardMaster.registSeCode) {
                        tmpList = userService!!.selectAllClubUser(userVO)
                        iter = tmpList!!.iterator()
                        while (iter.hasNext()) {
                            bdUseInf = BoardUseInf()

                            bdUseInf.bbsId = bbsId
                            bdUseInf.trgetId = (iter.next() as UserInfVO).uniqId
                            bdUseInf.registSeCode = "REGC07"
                            bdUseInf.useAt = "Y"
                            bdUseInf.frstRegisterId = boardMaster.frstRegisterId

                            bbsUseDAO.insertBBSUseInf(bdUseInf)
                        }
                    } else if ("REGC06" == boardMaster.registSeCode) {
                        tmpList = userService!!.selectAllCmmntyUser(userVO)
                        iter = tmpList!!.iterator()
                        while (iter.hasNext()) {
                            bdUseInf = BoardUseInf()

                            bdUseInf.bbsId = bbsId
                            bdUseInf.trgetId = (iter.next() as UserInfVO).uniqId
                            bdUseInf.registSeCode = "REGC07"
                            bdUseInf.useAt = "Y"
                            bdUseInf.frstRegisterId = boardMaster.frstRegisterId

                            bbsUseDAO.insertBBSUseInf(bdUseInf)
                        }
                    }
                }
        return bbsId
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.selectAllBBSMasteInf
     */
    @Throws(Exception::class)
    override fun selectAllBBSMasteInf(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return attrbMngDAO!!.selectAllBBSMasteInf(vo)
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.selectBBSMasterInf
     */
    @Throws(Exception::class)
    override fun selectBBSMasterInf(searchVO: BoardMaster?): BoardMasterVO {
        // ---------------------------------
        // 2009.06.26 : 2단계 기능 추가
        // ---------------------------------
        // return attrbMngDAO.selectBBSMasterInf(searchVO);

        val result = attrbMngDAO!!.selectBBSMasterInf(searchVO)

        val flag = propertyService!!.getString("Globals.addedOptions")
        if (flag != null && flag.trim { it <= ' ' }.equals("true", ignoreCase = true)) {
            val options = addedOptionsDAO!!.selectAddedOptionsInf(searchVO)

            if (options != null) {
                if (options.commentAt == "Y") {
                    result.option = "comment"
                }

                if (options.stsfdgAt == "Y") {
                    result.option = "stsfdg"
                }
            } else {
                result.option = "na" // 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
            }
        }

        return result

        /**/ -------------------------------* /
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.selectBBSMasterInfs
     */
    @Throws(Exception::class)
    override fun selectBBSMasterInfs(searchVO: BoardMasterVO?): MutableMap<String?, Any?> {
        val result = attrbMngDAO!!.selectBBSMasterInfs(searchVO)
        val cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 게시판 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.updateBBSMasterInf
     */
    @Throws(Exception::class)
    override fun updateBBSMasterInf(boardMaster: BoardMaster) {
        attrbMngDAO!!.updateBBSMasterInf(boardMaster)

        // ---------------------------------
        // 2009.06.26 : 2단계 기능 추가
        // ---------------------------------
        val flag = propertyService!!.getString("Globals.addedOptions")
        if (flag != null && flag.trim { it <= ' ' }.equals("true", ignoreCase = true)) {
            if (boardMaster.option == "na") {
                return
            }
            val options = addedOptionsDAO!!.selectAddedOptionsInf(boardMaster)

            if (options == null) {
                boardMaster.frstRegisterId = boardMaster.lastUpdusrId
                addedOptionsDAO.insertAddedOptionsInf(boardMaster)
            } else {
                // 수정 기능 제외 (새롭게 선택사항을 지정한 insert만 처리함)
                // addedOptionsDAO.updateAddedOptionsInf(boardMaster);
                LOGGER.debug("BBS Master update ignored...")
            }
        }
        /**/ -------------------------------* /
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService.validateTemplate
     */
    @Throws(Exception::class)
    override fun validateTemplate(searchVO: BoardMasterVO?) {
        LOGGER.debug("validateTemplate method ignored...")
    }

    /**
     * 사용중인 게시판 속성 정보의 목록을 조회 한다.
     */
    @Throws(Exception::class)
    override fun selectBdMstrListByTrget(vo: BoardMasterVO?): MutableMap<String?, Any?> {
        val result = attrbMngDAO!!.selectBdMstrListByTrget(vo)
        val cnt = attrbMngDAO.selectBdMstrListCntByTrget(vo)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
     */
    @Throws(Exception::class)
    override fun selectAllBdMstrByTrget(vo: BoardMasterVO?): MutableList<BoardMasterVO?>? {
        return attrbMngDAO!!.selectAllBdMstrByTrget(vo)
    }

    /**
     * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
     */
    @Throws(Exception::class)
    override fun selectNotUsedBdMstrList(searchVO: BoardMasterVO?): MutableMap<String?, Any?> {
        val result = attrbMngDAO!!.selectNotUsedBdMstrList(searchVO)
        val cnt = attrbMngDAO.selectNotUsedBdMstrListCnt(searchVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovBBSAttributeManageServiceImpl::class.java)
    }
}
