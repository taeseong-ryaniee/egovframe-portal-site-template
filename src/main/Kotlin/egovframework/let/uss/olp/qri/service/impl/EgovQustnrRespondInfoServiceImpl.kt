package egovframework.let.uss.olp.qri.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qri.service.EgovQustnrRespondInfoService
import egovframework.let.uss.olp.qri.service.QustnrRespondInfoVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 설문조사 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovQustnrRespondInfoService")
class EgovQustnrRespondInfoServiceImpl : EgovAbstractServiceImpl(), EgovQustnrRespondInfoService {
    @Resource(name = "qustnrRespondInfoDao")
    private val dao: QustnrRespondInfoDao? = null

    @Resource(name = "qustnrRespondInfoIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 설문템플릿을 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManage(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrTmplatManage(map)
    }

    /**
     * 객관식 통계를 조회 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageStatistics1(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoManageStatistics1(map)
    }

    /**
     * 주관식 통계를 조회 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageStatistics2(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoManageStatistics2(map)
    }

    /**
     * 회원정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageEmplyrinfo(map: MutableMap<*, *>?): MutableMap<*, *>? {
        return dao!!.selectQustnrRespondInfoManageEmplyrinfo(map)
    }

    /**
     * 설문정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageComtnqestnrinfo(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoManageComtnqestnrinfo(map)
    }

    /**
     * 문항정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageComtnqustnrqesitm(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoManageComtnqustnrqesitm(map)
    }

    /**
     * 항목정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageComtnqustnriem(map: MutableMap<*, *>?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoManageComtnqustnriem(map)
    }

    /**
     * 설문조사(설문등록)를(을) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoManageList(searchVO)
    }

    /**
     * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoManageListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrRespondInfoManageListCnt(searchVO)
    }

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoList(searchVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 상세조회 한다.
     * @param QustnrRespondInfo - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoDetail(qustnrRespondInfoVO: QustnrRespondInfoVO?): MutableList<*>? {
        return dao!!.selectQustnrRespondInfoDetail(qustnrRespondInfoVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrRespondInfoListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrRespondInfoListCnt(searchVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO) {
        val sMakeId = idgenService!!.getNextStringId()

        qustnrRespondInfoVO.setQestnrQesrspnsId(sMakeId)

        dao!!.insertQustnrRespondInfo(qustnrRespondInfoVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?) {
        dao!!.updateQustnrRespondInfo(qustnrRespondInfoVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun deleteQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?) {
        dao!!.deleteQustnrRespondInfo(qustnrRespondInfoVO)
    }
}
