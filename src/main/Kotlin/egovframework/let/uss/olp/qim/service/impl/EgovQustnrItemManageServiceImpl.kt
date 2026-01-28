package egovframework.let.uss.olp.qim.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qim.service.EgovQustnrItemManageService
import egovframework.let.uss.olp.qim.service.QustnrItemManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 설문항목관리를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovQustnrItemManageService")
class EgovQustnrItemManageServiceImpl : EgovAbstractServiceImpl(), EgovQustnrItemManageService {
    @Resource(name = "qustnrItemManageDao")
    private val dao: QustnrItemManageDao? = null

    @Resource(name = "egovQustnrItemManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 설문템플릿(을)를  목록을 조회한다.
     * @param qustnrItemManageVO - 설문항목 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManageList(qustnrItemManageVO: QustnrItemManageVO?): MutableList<*>? {
        return dao!!.selectQustnrTmplatManageList(qustnrItemManageVO)
    }


    /**
     * 설문항목 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrItemManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrItemManageList(searchVO)
    }

    /**
     * 설문항목를(을) 상세조회 한다.
     * @param QustnrItemManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrItemManageDetail(qustnrItemManageVO: QustnrItemManageVO?): MutableList<*>? {
        return dao!!.selectQustnrItemManageDetail(qustnrItemManageVO)
    }

    /**
     * 설문항목를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrItemManageListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrItemManageListCnt(searchVO)
    }

    /**
     * 설문항목를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertQustnrItemManage(qustnrItemManageVO: QustnrItemManageVO) {
        val sMakeId = idgenService!!.getNextStringId()

        qustnrItemManageVO.setQustnrIemId(sMakeId)

        dao!!.insertQustnrItemManage(qustnrItemManageVO)
    }

    /**
     * 설문항목를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateQustnrItemManage(qustnrItemManageVO: QustnrItemManageVO?) {
        dao!!.updateQustnrItemManage(qustnrItemManageVO)
    }

    /**
     * 설문항목를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun deleteQustnrItemManage(qustnrItemManageVO: QustnrItemManageVO?) {
        dao!!.deleteQustnrItemManage(qustnrItemManageVO)
    }
}
