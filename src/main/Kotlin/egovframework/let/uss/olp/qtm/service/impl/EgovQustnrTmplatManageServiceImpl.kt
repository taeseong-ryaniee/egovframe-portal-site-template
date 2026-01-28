package egovframework.let.uss.olp.qtm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.olp.qtm.service.EgovQustnrTmplatManageService
import egovframework.let.uss.olp.qtm.service.QustnrTmplatManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 설문템플릿 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovQustnrTmplatManageService")
class EgovQustnrTmplatManageServiceImpl : EgovAbstractServiceImpl(), EgovQustnrTmplatManageService {
    @Resource(name = "qustnrTmplatManageDao")
    private val dao: QustnrTmplatManageDao? = null

    @Resource(name = "egovQustnrTmplatManageIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 템플릿파일명을 조회한다.
     * @param qustnrTmplatManageVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO: QustnrTmplatManageVO?): MutableMap<*, *>? {
        return dao!!.selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManageList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectQustnrTmplatManageList(searchVO)
    }

    /**
     * 설문템플릿를(을) 상세조회 한다.
     * @param QustnrTmplatManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManageDetail(qustnrTmplatManageVO: QustnrTmplatManageVO?): MutableList<*>? {
        return dao!!.selectQustnrTmplatManageDetail(qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectQustnrTmplatManageListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectQustnrTmplatManageListCnt(searchVO)
    }

    /**
     * 설문템플릿를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO) {
        val sMakeId = idgenService!!.getNextStringId()

        qustnrTmplatManageVO.setQestnrTmplatId(sMakeId)

        dao!!.insertQustnrTmplatManage(qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    override fun updateQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?) {
        dao!!.updateQustnrTmplatManage(qustnrTmplatManageVO)
    }

    /**
     * 설문템플릿를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    override fun deleteQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?) {
        dao!!.deleteQustnrTmplatManage(qustnrTmplatManageVO)
    }
}
