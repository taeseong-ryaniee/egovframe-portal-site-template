package egovframework.let.uss.olp.qmc.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 설문관리를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovQustnrManageService {
    /**
     * 설문템플릿 목록을 조회한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageList(qustnrManageVO: QustnrManageVO?): MutableList<*>?

    /**
     * 설문관리 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageList(searchVO: ComDefaultVO?): MutableList<*>?

    /**
     * 설문관리를(을) 상세조회 한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageDetail(qustnrManageVO: QustnrManageVO?): MutableList<*>?

    /**
     * 설문관리를 상세조회(Model) 한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageDetailModel(qustnrManageVO: QustnrManageVO?): QustnrManageVO?

    /**
     * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrManageListCnt(searchVO: ComDefaultVO?): Int

    /**
     * 설문관리를(을) 등록한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrManage(qustnrManageVO: QustnrManageVO?)

    /**
     * 설문관리를(을) 수정한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrManage(qustnrManageVO: QustnrManageVO?)

    /**
     * 설문관리를(을) 삭제한다.
     * @param qustnrManageVO - 설문관리 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrManage(qustnrManageVO: QustnrManageVO?)
}
