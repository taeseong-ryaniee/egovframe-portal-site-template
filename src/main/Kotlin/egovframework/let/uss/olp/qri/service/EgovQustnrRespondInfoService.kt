package egovframework.let.uss.olp.qri.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 설문조사 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovQustnrRespondInfoService {
    /**
     * 설문템플릿을 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManage(map: MutableMap<*, *>?): MutableList<*>?

    /**
     * 객관식 통계를 조회 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageStatistics1(map: MutableMap<*, *>?): MutableList<*>?

    /**
     * 주관식 통계를 조회 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageStatistics2(map: MutableMap<*, *>?): MutableList<*>?

    /**
     * 회원정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageEmplyrinfo(map: MutableMap<*, *>?): MutableMap<*, *>?

    /**
     * 설문정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageComtnqestnrinfo(map: MutableMap<*, *>?): MutableList<*>?

    /**
     * 문항정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageComtnqustnrqesitm(map: MutableMap<*, *>?): MutableList<*>?

    /**
     * 항목정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageComtnqustnriem(map: MutableMap<*, *>?): MutableList<*>?

    /**
     * 설문조사(설문등록)를(을) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageList(searchVO: ComDefaultVO?): MutableList<*>?

    /**
     * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoManageListCnt(searchVO: ComDefaultVO?): Int

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoList(searchVO: ComDefaultVO?): MutableList<*>?

    /**
     * 응답자결과(설문조사)를(을) 상세조회 한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoDetail(qustnrRespondInfoVO: QustnrRespondInfoVO?): MutableList<*>?

    /**
     * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrRespondInfoListCnt(searchVO: ComDefaultVO?): Int

    /**
     * 응답자결과(설문조사)를(을) 등록한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?)

    /**
     * 응답자결과(설문조사)를(을) 수정한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?)

    /**
     * 응답자결과(설문조사)를(을) 삭제한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrRespondInfo(qustnrRespondInfoVO: QustnrRespondInfoVO?)
}
