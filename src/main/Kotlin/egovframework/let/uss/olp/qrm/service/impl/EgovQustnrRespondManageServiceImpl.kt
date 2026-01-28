package egovframework.let.uss.olp.qrm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qrm.service.EgovQustnrRespondManageService
import egovframework.let.uss.olp.qrm.service.QustnrRespondManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 설문응답자관리 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovQustnrRespondManageService")
class EgovQustnrRespondManageServiceImpl : EgovAbstractServiceImpl(), EgovQustnrRespondManageService {
    @Resource(name = "qustnrRespondManageDao")
    private val dao: QustnrRespondManageDao? = null


    @Resource(name = "qustnrRespondManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 응답자정보 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrRespondManageList(searchVO)
    }

    /**
     * 응답자정보를(을) 상세조회 한다.
     * @param QustnrRespondManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondManageDetail(qustnrRespondManageVO: QustnrRespondManageVO?): MutableList<*>? {
        return dao!!.selectQustnrRespondManageDetail(qustnrRespondManageVO)
    }

    /**
     * 응답자정보를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondManageListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrRespondManageListCnt(searchVO)
    }

    /**
     * 응답자정보를(을) 등록한다.
     * @param qustnrRespondManageVO -  응답자정보 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertQustnrRespondManage(qustnrRespondManageVO: QustnrRespondManageVO) {
        val sMakeId = idgenService!!.getNextStringId()

        qustnrRespondManageVO.setQestnrRespondId(sMakeId)

        dao!!.insertQustnrRespondManage(qustnrRespondManageVO)
    }

    /**
     * 응답자정보를(을) 수정한다.
     * @param qustnrRespondManageVO - 응답자정보 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateQustnrRespondManage(qustnrRespondManageVO: QustnrRespondManageVO?) {
        dao!!.updateQustnrRespondManage(qustnrRespondManageVO)
    }

    /**
     * 응답자정보를(을) 삭제한다.
     * @param qustnrRespondManageVO - 응답자정보 정보가 담긴 VO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun deleteQustnrRespondManage(qustnrRespondManageVO: QustnrRespondManageVO?) {
        dao!!.deleteQustnrRespondManage(qustnrRespondManageVO)
    }
}
