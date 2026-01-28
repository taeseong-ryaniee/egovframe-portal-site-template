package egovframework.let.uss.olp.qrm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qrm.service.QustnrRespondManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 설문응답자관리 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("qustnrRespondManageDao")
class QustnrRespondManageDao : EgovAbstractMapper() {
    /**
     * 응답자정보 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondManage.selectQustnrRespondManage", searchVO)
    }

    /**
     * 응답자정보를(을) 상세조회 한다.
     * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondManageDetail(qustnrRespondManageVO: QustnrRespondManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrRespondManage.selectQustnrRespondManageDetail", qustnrRespondManageVO)
    }

    /**
     * 응답자정보를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondManageListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrRespondManage.selectQustnrRespondManageCnt", searchVO)).toInt()
    }

    /**
     * 응답자정보를(을) 등록한다.
     * @param qqustnrRespondManageVO - 응답자정보 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrRespondManage(qustnrRespondManageVO: QustnrRespondManageVO?) {
        insert("QustnrRespondManage.insertQustnrRespondManage", qustnrRespondManageVO)
    }

    /**
     * 응답자정보를(을) 수정한다.
     * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrRespondManage(qustnrRespondManageVO: QustnrRespondManageVO?) {
        insert("QustnrRespondManage.updateQustnrRespondManage", qustnrRespondManageVO)
    }

    /**
     * 응답자정보를(을) 삭제한다.
     * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrRespondManage(qustnrRespondManageVO: QustnrRespondManageVO?) {
        insert("QustnrRespondManage.deleteQustnrRespondManage", qustnrRespondManageVO)
    }
}
