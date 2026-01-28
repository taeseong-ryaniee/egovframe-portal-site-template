package egovframework.let.uss.sam.stp.service.impl

import egovframework.let.uss.sam.stp.service.EgovStplatManageService
import egovframework.let.uss.sam.stp.service.StplatManageDefaultVO
import egovframework.let.uss.sam.stp.service.StplatManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 *
 * 약관내용을 처리하는 서비스 구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("StplatManageService")
class EgovStplatManageServiceImpl : EgovAbstractServiceImpl(), EgovStplatManageService {
    @Resource(name = "StplatManageDAO")
    private val stplatManageDAO: StplatManageDAO? = null

    /** ID Generation  */
    @Resource(name = "egovStplatManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null


    /**
     * 글을 조회한다.
     * @param vo
     * @return 조회한 글
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectStplatDetail(vo: StplatManageVO?): StplatManageVO {
        val resultVO = stplatManageDAO!!.selectStplatDetail(vo)
        if (resultVO == null) throw processException("info.nodata.msg")
        return resultVO
    }

    /**
     * 약관정보 글 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectStplatList(searchVO: StplatManageDefaultVO?): MutableList<*>? {
        return stplatManageDAO!!.selectStplatList(searchVO)
    }

    /**
     * 약관정보 글 총 갯수를 조회한다.
     * @param searchVO
     * @return 글 총 갯수
     */
    override fun selectStplatListTotCnt(searchVO: StplatManageDefaultVO?): Int {
        return stplatManageDAO!!.selectStplatListTotCnt(searchVO)
    }

    /**
     * 약관정보 글을 등록한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun insertStplatCn(vo: StplatManageVO) {
        val useStplatId = idgenService!!.getNextStringId()

        vo.setUseStplatId(useStplatId)

        stplatManageDAO!!.insertStplatCn(vo)
    }

    /**
     * 약관정보 글을 수정한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateStplatCn(vo: StplatManageVO?) {
        stplatManageDAO!!.updateStplatCn(vo)
    }

    /**
     * 약관정보 글을 삭제한다.
     * @param vo
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun deleteStplatCn(vo: StplatManageVO?) {
        stplatManageDAO!!.deleteStplatCn(vo)
    }
}
