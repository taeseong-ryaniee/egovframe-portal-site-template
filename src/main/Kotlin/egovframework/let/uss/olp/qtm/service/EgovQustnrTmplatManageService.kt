package egovframework.let.uss.olp.qtm.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 설문템플릿 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovQustnrTmplatManageService {
    /**
     * 템플릿파일명을 조회한다.
     * @param qustnrTmplatManageVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO: QustnrTmplatManageVO?): MutableMap<*, *>?

    /**
     * 설문템플릿 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageList(searchVO: ComDefaultVO?): MutableList<*>?

    /**
     * 설문템플릿를(을) 상세조회 한다.
     * @param QustnrTmplatManage - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageDetail(qustnrTmplatManageVO: QustnrTmplatManageVO?): MutableList<*>?

    /**
     * 설문템플릿를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectQustnrTmplatManageListCnt(searchVO: ComDefaultVO?): Int

    /**
     * 설문템플릿를(을) 등록한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?)

    /**
     * 설문템플릿를(을) 수정한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?)

    /**
     * 설문템플릿를(을) 삭제한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteQustnrTmplatManage(qustnrTmplatManageVO: QustnrTmplatManageVO?)
}
