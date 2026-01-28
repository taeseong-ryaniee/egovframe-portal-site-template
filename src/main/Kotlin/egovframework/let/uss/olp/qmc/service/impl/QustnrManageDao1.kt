package egovframework.let.uss.olp.qmc.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qmc.service.QustnrManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 설문관리를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("qustnrManageDao")
class QustnrManageDao : EgovAbstractMapper() {
    /**
     * 설문템플릿 목록을 조회한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageList(qustnrManageVO: QustnrManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrManage.selectQustnrTmplatManage", qustnrManageVO)
    }

    /**
     * 설문관리 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrManage.selectQustnrManage", searchVO)
    }

    /**
     * 설문관리를 상세조회(Model) 한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageDetailModel(qustnrManageVO: QustnrManageVO?): QustnrManageVO? {
        return selectOne<Any?>("QustnrManage.selectQustnrManageDetailModel", qustnrManageVO) as QustnrManageVO?
    }

    /**
     * 설문관리를(을) 상세조회 한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageDetail(qustnrManageVO: QustnrManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrManage.selectQustnrManageDetail", qustnrManageVO)
    }

    /**
     * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrManage.selectQustnrManageCnt", searchVO)).toInt()
    }

    /**
     * 설문관리를(을) 등록한다.
     * @param qqustnrManageVO - 설문관리 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrManage(qustnrManageVO: QustnrManageVO?) {
        insert("QustnrManage.insertQustnrManage", qustnrManageVO)
    }

    /**
     * 설문관리를(을) 수정한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrManage(qustnrManageVO: QustnrManageVO?) {
        insert("QustnrManage.updateQustnrManage", qustnrManageVO)
    }

    /**
     * 설문관리를(을) 삭제한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrManage(qustnrManageVO: QustnrManageVO?) {
        //설문응답자 삭제
        delete("QustnrManage.deleteQustnrRespondManage", qustnrManageVO)
        //설문조사(설문결과) 삭제
        delete("QustnrManage.deleteQustnrRespondInfo", qustnrManageVO)
        //설문항목 삭제
        delete("QustnrManage.deleteQustnrItemManage", qustnrManageVO)
        //설문문항 삭제
        delete("QustnrManage.deleteQustnrQestnManage", qustnrManageVO)

        //설문관리 삭제
        delete("QustnrManage.deleteQustnrManage", qustnrManageVO)
    }
}
