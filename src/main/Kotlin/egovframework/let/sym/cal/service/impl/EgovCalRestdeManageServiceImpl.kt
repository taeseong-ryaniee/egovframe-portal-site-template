package egovframework.let.sym.cal.service.impl

import egovframework.let.sym.cal.service.EgovCalRestdeManageService
import egovframework.let.sym.cal.service.Restde
import egovframework.let.sym.cal.service.RestdeVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 *
 * 휴일에 대한 서비스 구현클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("RestdeManageService")
class EgovCalRestdeManageServiceImpl : EgovAbstractServiceImpl(), EgovCalRestdeManageService {
    @Resource(name = "RestdeManageDAO")
    private val restdeManageDAO: RestdeManageDAO? = null

    /**
     * 일반달력 팝업 정보를 조회한다.
     */
    @Throws(Exception::class)
    override fun selectNormalRestdePopup(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectNormalRestdePopup(restde)
    }

    /**
     * 행정달력 팝업 정보를 조회한다.
     */
    @Throws(Exception::class)
    override fun selectAdministRestdePopup(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectAdministRestdePopup(restde)
    }

    /**
     * 일반달력 일간 정보를 조회한다.
     */
    @Throws(Exception::class)
    override fun selectNormalDayCal(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectNormalDayCal(restde)
    }

    /**
     * 일반달력 일간 휴일을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectNormalDayRestde(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectNormalDayRestde(restde)
    }

    /**
     * 일반달력 월간 휴일을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectNormalMonthRestde(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectNormalMonthRestde(restde)
    }

    /**
     * 행정달력 일간 정보를 조회한다.
     */
    @Throws(Exception::class)
    override fun selectAdministDayCal(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectAdministDayCal(restde)
    }

    /**
     * 행정달력 일간 휴일을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectAdministDayRestde(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectAdministDayRestde(restde)
    }

    /**
     * 행정달력 월간 휴일을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectAdministMonthRestde(restde: Restde?): MutableList<*>? {
        return restdeManageDAO!!.selectAdministMonthRestde(restde)
    }

    /**
     * 휴일을 삭제한다.
     */
    @Throws(Exception::class)
    override fun deleteRestde(restde: Restde?) {
        restdeManageDAO!!.deleteRestde(restde)
    }

    /**
     * 휴일을 등록한다.
     */
    @Throws(Exception::class)
    override fun insertRestde(restde: Restde?) {
        restdeManageDAO!!.insertRestde(restde)
    }

    /**
     * 휴일 상세항목을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectRestdeDetail(restde: Restde?): Restde? {
        val ret = restdeManageDAO!!.selectRestdeDetail(restde)
        return ret
    }

    /**
     * 휴일 목록을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectRestdeList(searchVO: RestdeVO?): MutableList<*>? {
        return restdeManageDAO!!.selectRestdeList(searchVO)
    }

    /**
     * 휴일 총 갯수를 조회한다.
     */
    @Throws(Exception::class)
    override fun selectRestdeListTotCnt(searchVO: RestdeVO?): Int {
        return restdeManageDAO!!.selectRestdeListTotCnt(searchVO)
    }

    /**
     * 휴일을 수정한다.
     */
    @Throws(Exception::class)
    override fun updateRestde(restde: Restde?) {
        restdeManageDAO!!.updateRestde(restde)
    }
}
