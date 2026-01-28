package egovframework.let.sym.cal.service

/**
 *
 * 휴일에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovCalRestdeManageService {
    /**
     * 일반달력 팝업 정보를 조회한다.
     * @param restde
     * @return List(일반달력 팝업 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalRestdePopup(restde: Restde?): MutableList<*>?

    /**
     * 행정달력 팝업 정보를 조회한다.
     * @param restde
     * @return List(행정달력 팝업 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministRestdePopup(restde: Restde?): MutableList<*>?

    /**
     * 일반달력 일간 정보를 조회한다.
     * @param restde
     * @return List(일반달력 일간 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalDayCal(restde: Restde?): MutableList<*>?

    /**
     * 일반달력 일간 휴일을 조회한다.
     * @param restde
     * @return List(일반달력 일간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalDayRestde(restde: Restde?): MutableList<*>?

    /**
     * 일반달력 월간 휴일을 조회한다.
     * @param restde
     * @return List(일반달력 월간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectNormalMonthRestde(restde: Restde?): MutableList<*>?

    /**
     * 행정달력 일간 정보를 조회한다.
     * @param restde
     * @return List(행정달력 일간 날짜정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministDayCal(restde: Restde?): MutableList<*>?

    /**
     * 행정달력 일간 휴일을 조회한다.
     * @param restde
     * @return List(행정달력 일간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministDayRestde(restde: Restde?): MutableList<*>?

    /**
     * 행정달력 월간 휴일을 조회한다.
     * @param restde
     * @return List(행정달력 월간 휴일정보)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAdministMonthRestde(restde: Restde?): MutableList<*>?

    /**
     * 휴일을 삭제한다.
     * @param restde
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteRestde(restde: Restde?)

    /**
     * 휴일을 등록한다.
     * @param restde
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertRestde(restde: Restde?)

    /**
     * 휴일 상세항목을 조회한다.
     * @param restde
     * @return Restde(휴일)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectRestdeDetail(restde: Restde?): Restde?

    /**
     * 휴일 목록을 조회한다.
     * @param searchVO
     * @return List(휴일 목록)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectRestdeList(searchVO: RestdeVO?): MutableList<*>?

    /**
     * 휴일 총 갯수를 조회한다.
     * @param searchVO
     * @return int(휴일 총 갯수)
     */
    @Throws(Exception::class)
    fun selectRestdeListTotCnt(searchVO: RestdeVO?): Int

    /**
     * 휴일을 수정한다.
     * @param restde
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateRestde(restde: Restde?)
}
