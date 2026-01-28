package egovframework.let.uss.sam.ipm.service.impl

import egovframework.com.cmm.ComDefaultVO
import egovframework.let.uss.sam.ipm.service.IndvdlInfoPolicy
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 개인정보보호정책를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력
</pre> */
@Repository("onlineIndvdlInfoPolicyDao")
class IndvdlInfoPolicyDao : EgovAbstractMapper() {
    /**
     * 개인정보보호정책를(을) 목록을 한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectIndvdlInfoPolicyList(searchVO: ComDefaultVO?): MutableList<*>? {
        return selectList<Any?>("IndvdlInfoPolicy.selectIndvdlInfoPolicy", searchVO)
    }

    /**
     * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectIndvdlInfoPolicyListCnt(searchVO: ComDefaultVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("IndvdlInfoPolicy.selectIndvdlInfoPolicyCnt")).toInt()
    }

    /**
     * 개인정보보호정책를(을) 상세조회 한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보가 담김 VO
     * @return IndvdlInfoPolicy
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectIndvdlInfoPolicyDetail(indvdlInfoPolicy: IndvdlInfoPolicy?): IndvdlInfoPolicy {
        return (IndvdlInfoPolicy)<Object> selectOne < kotlin . Any ? > ("IndvdlInfoPolicy.selectIndvdlInfoPolicyDetail", indvdlInfoPolicy)
    }

    /**
     * 개인정보보호정책를(을) 등록한다.
     * @param qindvdlInfoPolicy  개인정보보호정책 정보가 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?) {
        insert("IndvdlInfoPolicy.insertIndvdlInfoPolicy", indvdlInfoPolicy)
    }

    /**
     * 개인정보보호정책를(을) 수정한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보가 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?) {
        update("IndvdlInfoPolicy.updateIndvdlInfoPolicy", indvdlInfoPolicy)
    }

    /**
     * 개인정보보호정책를(을) 삭제한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보가 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?) {
        delete("IndvdlInfoPolicy.deleteIndvdlInfoPolicy", indvdlInfoPolicy)
    }
}
