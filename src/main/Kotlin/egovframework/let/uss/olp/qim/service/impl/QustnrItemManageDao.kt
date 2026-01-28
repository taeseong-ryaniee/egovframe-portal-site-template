package egovframework.let.uss.olp.qim.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qim.service.QustnrItemManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 설문항목관리를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("qustnrItemManageDao")
class QustnrItemManageDao : EgovAbstractMapper() {
    /**
     * 설문템플릿(을)를  목록을 조회한다.
     * @param qustnrItemManageVO - 설문항목 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageList(qustnrItemManageVO: QustnrItemManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrItemManage.selectQustnrTmplatManage", qustnrItemManageVO)
    }

    /**
     * 설문항목 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrItemManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrItemManage.selectQustnrItemManage", searchVO)
    }

    /**
     * 설문항목를(을) 상세조회 한다.
     * @param qustnrItemManageVO - 설문항목 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrItemManageDetail(qustnrItemManageVO: QustnrItemManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrItemManage.selectQustnrItemManageDetail", qustnrItemManageVO)
    }

    /**
     * 설문항목를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrItemManageListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrItemManage.selectQustnrItemManageCnt", searchVO)).toInt()
    }

    /**
     * 설문항목를(을) 등록한다.
     * @param qqustnrItemManageVO - 설문항목 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrItemManage(qustnrItemManageVO: QustnrItemManageVO?) {
        insert("QustnrItemManage.insertQustnrItemManage", qustnrItemManageVO)
    }

    /**
     * 설문항목를(을) 수정한다.
     * @param qustnrItemManageVO - 설문항목 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrItemManage(qustnrItemManageVO: QustnrItemManageVO?) {
        insert("QustnrItemManage.updateQustnrItemManage", qustnrItemManageVO)
    }

    /**
     * 설문항목를(을) 삭제한다.
     * @param qustnrItemManageVO - 설문항목 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrItemManage(qustnrItemManageVO: QustnrItemManageVO?) {
        //설문조사(설문결과) 삭제
        delete("QustnrItemManage.deleteQustnrRespondInfo", qustnrItemManageVO)

        //설문항목 삭제
        insert("QustnrItemManage.deleteQustnrItemManage", qustnrItemManageVO)
    }
}
