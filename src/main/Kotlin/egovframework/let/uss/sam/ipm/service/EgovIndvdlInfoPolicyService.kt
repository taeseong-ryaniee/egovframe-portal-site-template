package egovframework.let.uss.sam.ipm.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 개인정보보호정책를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovIndvdlInfoPolicyService {
    /**
     * 개인정보보호정책 목록을 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectIndvdlInfoPolicyList(searchVO: ComDefaultVO?): MutableList<*>?

    /**
     * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectIndvdlInfoPolicyListCnt(searchVO: ComDefaultVO?): Int

    /**
     * 개인정보보호정책를(을) 상세조회 한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectIndvdlInfoPolicyDetail(indvdlInfoPolicy: IndvdlInfoPolicy?): IndvdlInfoPolicy?

    /**
     * 개인정보보호정책를(을) 등록한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?)

    /**
     * 개인정보보호정책를(을) 수정한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?)

    /**
     * 개인정보보호정책를(을) 삭제한다.
     * @param indvdlInfoPolicy  개인정보보호정책 정보 담김 VO
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteIndvdlInfoPolicy(indvdlInfoPolicy: IndvdlInfoPolicy?)
}
