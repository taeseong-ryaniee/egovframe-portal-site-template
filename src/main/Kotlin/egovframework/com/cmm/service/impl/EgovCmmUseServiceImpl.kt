package egovframework.com.cmm.service.impl

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.service.CmmnDetailCode
import egovframework.com.cmm.service.EgovCmmUseService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * @Class Name : EgovCmmUseServiceImpl.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * @Modification Information
 *
 * 수정일       수정자         수정내용
 * -------        -------     -------------------
 * 2009. 3. 11.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 */
@Service("EgovCmmUseService")
class EgovCmmUseServiceImpl : EgovAbstractServiceImpl(), EgovCmmUseService {
    @Resource(name = "cmmUseDAO")
    private val cmmUseDAO: CmmUseDAO? = null

    /**
     * 공통코드를 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectCmmCodeDetail(vo: ComDefaultCodeVO?): MutableList<CmmnDetailCode?>? {
        return cmmUseDAO!!.selectCmmCodeDetail(vo)
    }

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
     *
     * @param voList
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectCmmCodeDetails(voList: MutableList<*>): MutableMap<String?, MutableList<CmmnDetailCode?>?> {
        var vo: ComDefaultCodeVO
        val map: MutableMap<String?, MutableList<CmmnDetailCode?>?> = HashMap<String?, MutableList<CmmnDetailCode?>?>()

        val iter: MutableIterator<*> = voList.iterator()
        while (iter.hasNext()) {
            vo = iter.next() as ComDefaultCodeVO
            map.put(vo.codeId, cmmUseDAO!!.selectCmmCodeDetail(vo))
        }

        return map
    }

    /**
     * 조직정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectOgrnztIdDetail(vo: ComDefaultCodeVO?): MutableList<CmmnDetailCode?>? {
        return cmmUseDAO!!.selectOgrnztIdDetail(vo)
    }

    /**
     * 그룹정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 그룹정보 List
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun selectGroupIdDetail(vo: ComDefaultCodeVO?): MutableList<CmmnDetailCode?>? {
        return cmmUseDAO!!.selectGroupIdDetail(vo)
    }
}
