package egovframework.let.uss.olp.qqm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qqm.service.EgovQustnrQestnManageService
import egovframework.let.uss.olp.qqm.service.QustnrQestnManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 설문문항을 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovQustnrQestnManageService")
class EgovQustnrQestnManageServiceImpl : EgovAbstractServiceImpl(), EgovQustnrQestnManageService {
    @Resource(name = "qustnrQestnManageDao")
    private val dao: QustnrQestnManageDao? = null

    @Resource(name = "egovQustnrQestnManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null


    /**
     * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
     * @param Map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageStatistics2(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrManageStatistics2(map)
    }

    /**
     * 설문조사 통계를 조회한다.
     * @param Map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageStatistics(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrManageStatistics(map)
    }

    /**
     * 설문지정보 설문제목을 조회한다.
     * @param Map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrManageQestnrSj(map: MutableMap<*, *>?): MutableMap<*, *>? {
        return dao!!.selectQustnrManageQestnrSj(map)
    }

    /**
     * 설문문항 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrQestnManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrQestnManageList(searchVO)
    }

    /**
     * 설문문항를(을) 상세조회 한다.
     * @param QustnrQestnManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrQestnManageDetail(qustnrQestnManageVO: QustnrQestnManageVO?): MutableList<*>? {
        return dao!!.selectQustnrQestnManageDetail(qustnrQestnManageVO)
    }

    /**
     * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrQestnManageListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrQestnManageListCnt(searchVO)
    }

    /**
     * 설문문항를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertQustnrQestnManage(qustnrQestnManageVO: QustnrQestnManageVO) {
        val sMakeId = idgenService!!.getNextStringId()

        qustnrQestnManageVO.setQestnrQesitmId(sMakeId)

        dao!!.insertQustnrQestnManage(qustnrQestnManageVO)
    }

    /**
     * 설문문항를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateQustnrQestnManage(qustnrQestnManageVO: QustnrQestnManageVO?) {
        dao!!.updateQustnrQestnManage(qustnrQestnManageVO)
    }

    /**
     * 설문문항를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun deleteQustnrQestnManage(qustnrQestnManageVO: QustnrQestnManageVO?) {
        dao!!.deleteQustnrQestnManage(qustnrQestnManageVO)
    }
}
