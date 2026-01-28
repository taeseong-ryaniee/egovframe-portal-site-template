package egovframework.let.uss.olp.qqm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qqm.service.QustnrQestnManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 설문문항을 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("qustnrQestnManageDao")
class QustnrQestnManageDao : EgovAbstractMapper() {
    /**
     * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
     * @param Map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageStatistics2(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrQestnManage.selectQustnrManageStatistics2", map)
    }

    /**
     * 설문조사 통계를 조회한다.
     * @param Map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageStatistics(map: MutableMap<*, *>?): MutableList<*>? {
        return selectList<Any?>("QustnrQestnManage.selectQustnrManageStatistics", map)
    }

    /**
     * 설문지정보 설문제목을 조회한다.
     * @param Map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageQestnrSj(map: MutableMap<*, *>?): MutableMap<*, *> {
        return TODO(
            """
            |Cannot convert element
            |With text:
            |(Map<?, ?>)
            """.trimMargin()
        ) as MutableMap<*, *>? < Object > selectOne<Any?>("QustnrQestnManage.selectQustnrManageQestnrSj", map)
    }


    /**
     * 설문문항 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrQestnManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrQestnManage.selectQustnrQestnManage", searchVO)
    }

    /**
     * 설문문항를(을) 상세조회 한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrQestnManageDetail(qustnrQestnManageVO: QustnrQestnManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrQestnManage.selectQustnrQestnManageDetail", qustnrQestnManageVO)
    }

    /**
     * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrQestnManageListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrQestnManage.selectQustnrQestnManageCnt", searchVO)).toInt()
    }

    /**
     * 설문문항를(을) 등록한다.
     * @param qqustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrQestnManage(qustnrQestnManageVO: QustnrQestnManageVO?) {
        insert("QustnrQestnManage.insertQustnrQestnManage", qustnrQestnManageVO)
    }

    /**
     * 설문문항를(을) 수정한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrQestnManage(qustnrQestnManageVO: QustnrQestnManageVO?) {
        insert("QustnrQestnManage.updateQustnrQestnManage", qustnrQestnManageVO)
    }

    /**
     * 설문문항를(을) 삭제한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrQestnManage(qustnrQestnManageVO: QustnrQestnManageVO?) {
        //설문조사(설문결과) 삭제

        delete("QustnrQestnManage.deleteQustnrRespondInfo", qustnrQestnManageVO)
        //설문항목 삭제
        delete("QustnrQestnManage.deleteQustnrItemManage", qustnrQestnManageVO)

        //설문문항
        delete("QustnrQestnManage.deleteQustnrQestnManage", qustnrQestnManageVO)
    }
}
