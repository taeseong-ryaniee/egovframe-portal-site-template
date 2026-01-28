package egovframework.let.uss.olp.qri.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qri.service.QustnrRespondInfoVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 설문조사 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("qustnrRespondInfoDao")
class QustnrRespondInfoDao : EgovAbstractMapper() {
    /**
     * 설문템플릿을 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManage(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrTmplatManages", map)
    }

    /**
     * 객관식 통계를 조회 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageStatistics1(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoManageStatistics1", map)
    }

    /**
     * 주관식 통계를 조회 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageStatistics2(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoManageStatistics2", map)
    }

    /**
     * 회원정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageEmplyrinfo(map: MutableMap<*, *>?): MutableMap<*, *> {
        return TODO(
            """
            |Cannot convert element
            |With text:
            |(Map<?, ?>)
            """.trimMargin()
        ) as MutableMap<*, *>? < Object > selectOne<Any?>(
            "QustnrRespondInfo.selectQustnrRespondInfoManageEmplyrinfo",
            map
        )
    }

    /**
     * 설문정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageComtnqestnrinfo(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoManageComtnqestnrinfo", map)
    }

    /**
     * 문항정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageComtnqustnrqesitm(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoManageComtnqustnrqesitm", map)
    }

    /**
     * 항목정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageComtnqustnriem(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoManageComtnqustnriem", map)
    }

    /**
     * 설문조사(설문등록)를(을) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoManage", searchVO)
    }

    /**
     * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrRespondInfo.selectQustnrRespondInfoManageCnt", searchVO)).toInt()
    }

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfo", searchVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 상세조회 한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoDetail(qustnrRespondInfoVO: QustnrRespondInfoVO?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondInfo.selectQustnrRespondInfoDetail", qustnrRespondInfoVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrRespondInfo.selectQustnrRespondInfoCnt", searchVO)).toInt()
    }

    /**
     * 응답자결과(설문조사)를(을) 등록한다.
     * @param qqustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?) {
        insert("QustnrRespondInfo.insertQustnrRespondInfo", qustnrRespondInfoVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 수정한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?) {
        insert("QustnrRespondInfo.updateQustnrRespondInfo", qustnrRespondInfoVO)
    }

    /**
     * 응답자결과(설문조사)를(을) 삭제한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?) {
        insert("QustnrRespondInfo.deleteQustnrRespondInfo", qustnrRespondInfoVO)
    }
}
