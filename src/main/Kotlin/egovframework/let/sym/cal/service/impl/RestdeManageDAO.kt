package egovframework.let.sym.cal.service.impl

import egovframework.let.sym.cal.service.Restde
import egovframework.let.sym.cal.service.RestdeVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 *
 * 휴일에 대한 데이터 접근 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("RestdeManageDAO")
class RestdeManageDAO : EgovAbstractMapper() {
    /**
     * 일반달력 팝업 정보를 조회한다.
     * @param restde
     * @return List(일반달력 팝업 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalRestdePopup(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectNormalRestdePopup", restde)
    }

    /**
     * 행정달력 팝업 정보를 조회한다.
     * @param restde
     * @return List(행정달력 팝업 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministRestdePopup(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectAdministRestdePopup", restde)
    }

    /**
     * 일반달력 일간 정보를 조회한다.
     * @param restde
     * @return List(일반달력 일간 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalDayCal(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectNormalDayCal", restde)
    }

    /**
     * 일반달력 일간 휴일을 조회한다.
     * @param restde
     * @return List(일반달력 일간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalDayRestde(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectNormalDayRestde", restde)
    }

    /**
     * 일반달력 월간 휴일을 조회한다.
     * @param restde
     * @return List(일반달력 월간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalMonthRestde(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectNormalMonthRestde", restde)
    }

    /**
     * 행정달력 일간 정보를 조회한다.
     * @param restde
     * @return List(행정달력 일간 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministDayCal(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectAdministDayCal", restde)
    }

    /**
     * 행정달력 일간 휴일을 조회한다.
     * @param restde
     * @return List(행정달력 일간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministDayRestde(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectAdministDayRestde", restde)
    }

    /**
     * 행정달력 월간 휴일을 조회한다.
     * @param restde
     * @return List(행정달력 월간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministMonthRestde(restde: Restde?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectAdministMonthRestde", restde)
    }

    /**
     * 휴일을 삭제한다.
     * @param restde
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteRestde(restde: Restde?) {
        delete("RestdeManageDAO.deleteRestde", restde)
    }


    /**
     * 휴일을 등록한다.
     * @param restde
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertRestde(restde: Restde?) {
        insert("RestdeManageDAO.insertRestde", restde)
    }

    /**
     * 휴일 상세항목을 조회한다.
     * @param restde
     * @return Restde(휴일)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectRestdeDetail(restde: Restde?): Restde? {
        return selectOne<Any?>("RestdeManageDAO.selectRestdeDetail", restde) as Restde?
    }


    /**
     * 휴일 목록을 조회한다.
     * @param searchVO
     * @return List(휴일 목록)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectRestdeList(searchVO: RestdeVO?): MutableList<*>? {
        return selectList<Any?>("RestdeManageDAO.selectRestdeList", searchVO)
    }

    /**
     * 글 총 갯수를 조회한다.
     * @param searchVO
     * @return int(휴일 총 갯수)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectRestdeListTotCnt(searchVO: RestdeVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("RestdeManageDAO.selectRestdeListTotCnt", searchVO)).toInt()
    }

    /**
     * 휴일을 수정한다.
     * @param restde
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateRestde(restde: Restde?) {
        update("RestdeManageDAO.updateRestde", restde)
    }
}
