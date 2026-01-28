package egovframework.let.uss.olp.qtm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qtm.service.QustnrTmplatManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 설문템플릿 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("qustnrTmplatManageDao")
class QustnrTmplatManageDao : EgovAbstractMapper() {
    /**
     * 템플릿파일명을 조회한다.
     * @param qustnrTmplatManageVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    fun selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO: QustnrTmplatManageVO?): MutableMap<*, *> {
        return TODO(
            """
            |Cannot convert element
            |With text:
            |(Map<?, ?>)
            """.trimMargin()
        ) as MutableMap<*, *>? < Object > selectOne<Any?>(
            "QustnrTmplatManage.selectQustnrTmplatManageTmplatImagepathnm",
            qustnrTmplatManageVO
        )
    }


    /**
     * 설문템플릿 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    fun selectQustnrTmplatManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("QustnrTmplatManage.selectQustnrTmplatManage", searchVO)
    }

    /**
     * 설문템플릿를(을) 상세조회 한다.
     * @param QustnrTmplatManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    fun selectQustnrTmplatManageDetail(qustnrTmplatManageVO: QustnrTmplatManageVO?): MutableList<*>? {
        return selectList<Any?>("QustnrTmplatManage.selectQustnrTmplatManageDetail", qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    fun selectQustnrTmplatManageListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("QustnrTmplatManage.selectQustnrTmplatManageCnt", searchVO)).toInt()
    }

    /**
     * 설문템플릿를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    fun insertQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?) {
        insert("QustnrTmplatManage.insertQustnrTmplatManage", qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    fun updateQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?) {
        insert("QustnrTmplatManage.updateQustnrTmplatManage", qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    fun deleteQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?) {
        //설문응답자 삭제
        delete("QustnrTmplatManage.deleteQustnrRespondManage", qustnrTmplatManageVO)
        //설문조사(설문결과) 삭제
        delete("QustnrTmplatManage.deleteQustnrRespondInfo", qustnrTmplatManageVO)
        //설문항목 삭제
        delete("QustnrTmplatManage.deleteQustnrItemManage", qustnrTmplatManageVO)
        //설문문항 삭제
        delete("QustnrTmplatManage.deleteQustnrQestnManage", qustnrTmplatManageVO)
        //설문관리 삭제
        delete("QustnrTmplatManage.deleteQustnrManage", qustnrTmplatManageVO)

        //설문템플릿삭제
        delete("QustnrTmplatManage.deleteQustnrTmplatManage", qustnrTmplatManageVO)
    }
}
