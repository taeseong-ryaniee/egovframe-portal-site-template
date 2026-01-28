package egovframework.let.uss.olp.qmc.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qmc.service.EgovQustnrManageService
import egovframework.let.uss.olp.qmc.service.QustnrManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 설문관리를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovQustnrManageService")
class EgovQustnrManageServiceImpl : EgovAbstractServiceImpl(), EgovQustnrManageService {
    @Resource(name = "qustnrManageDao")
    private val dao: QustnrManageDao? = null

    @Resource(name = "egovQustnrManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null


    /**
     * 설문템플릿 목록을 조회한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManageList(qustnrManageVO: QustnrManageVO?): MutableList<*>? {
        return dao!!.selectQustnrTmplatManageList(qustnrManageVO)
    }


    /**
     * 설문관리 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrManageList(searchVO)
    }

    /**
     * 설문관리를 상세조회(Model) 한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageDetailModel(qustnrManageVO: QustnrManageVO?): QustnrManageVO? {
        return dao!!.selectQustnrManageDetailModel(qustnrManageVO)
    }

    /**
     * 설문관리를(을) 상세조회 한다.
     * @param QustnrManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageDetail(qustnrManageVO: QustnrManageVO?): MutableList<*>? {
        return dao!!.selectQustnrManageDetail(qustnrManageVO)
    }

    /**
     * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrManageListCnt(searchVO)
    }

    /**
     * 설문관리를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertQustnrManage(qustnrManageVO: QustnrManageVO) {
        val sMakeId = idgenService!!.getNextStringId()

        qustnrManageVO.setQestnrId(sMakeId)

        dao!!.insertQustnrManage(qustnrManageVO)
    }

    /**
     * 설문관리를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateQustnrManage(qustnrManageVO: QustnrManageVO?) {
        dao!!.updateQustnrManage(qustnrManageVO)
    }

    /**
     * 설문관리를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun deleteQustnrManage(qustnrManageVO: QustnrManageVO?) {
        dao!!.deleteQustnrManage(qustnrManageVO)
    }
}
