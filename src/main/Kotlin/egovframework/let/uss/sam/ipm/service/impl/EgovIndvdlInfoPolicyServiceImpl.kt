package egovframework.let.uss.sam.ipm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.sam.ipm.service.EgovIndvdlInfoPolicyService
import egovframework.let.uss.sam.ipm.service.IndvdlInfoPolicy
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 개인정보보호정책를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력
</pre> */
@Service("egovIndvdlInfoPolicyService")
class EgovIndvdlInfoPolicyServiceImpl : EgovAbstractServiceImpl(), EgovIndvdlInfoPolicyService {
    @Resource(name = "onlineIndvdlInfoPolicyDao")
    private val dao: IndvdlInfoPolicyDao? = null

    @Resource(name = "egovIndvdlInfoPolicyIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 개인정보보호정책를(을) 목록을 조회 한다.
     * @param OnlinePoll 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectIndvdlInfoPolicyList(searchVO: ComDefaultVO?): MutableList<*>? {
        return dao!!.selectIndvdlInfoPolicyList(searchVO)
    }

    /**
     * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectIndvdlInfoPolicyListCnt(searchVO: ComDefaultVO?): Int {
        return dao!!.selectIndvdlInfoPolicyListCnt(searchVO)
    }

    /**
     * 개인정보보호정책를(을) 상세조회 한다.
     * @param searchVO 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectIndvdlInfoPolicyDetail(indvdlInfoPolicy: IndvdlInfoPolicy?): IndvdlInfoPolicy? {
        return dao!!.selectIndvdlInfoPolicyDetail(indvdlInfoPolicy)
    }

    /**
     * 개인정보보호정책를(을) 등록한다.
     * @param indvdlInfoPolicy 개인정보보호정책 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy) {
        val sMakeId = idgenService!!.getNextStringId()
        indvdlInfoPolicy.setIndvdlInfoId(sMakeId)
        dao!!.insertIndvdlInfoPolicy(indvdlInfoPolicy)
    }

    /**
     * 개인정보보호정책를(을) 수정한다.
     * @param indvdlInfoPolicy 개인정보보호정책 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?) {
        dao!!.updateIndvdlInfoPolicy(indvdlInfoPolicy)
    }

    /**
     * 개인정보보호정책를(을) 삭제한다.
     * @param indvdlInfoPolicy 개인정보보호정책 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun deleteIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?) {
        dao!!.deleteIndvdlInfoPolicy(indvdlInfoPolicy)
    }
}
