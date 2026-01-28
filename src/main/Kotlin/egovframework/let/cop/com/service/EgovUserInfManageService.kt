package egovframework.let.cop.com.service

/**
 * 협업 기능에서 사용자 정보를 관리하기 위한 서비스 인터페이스 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovUserInfManageService {
    /**
     * 사용자 정보에 대한 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectUserList(userVO: UserInfVO?): MutableMap<String?, Any?>?

    /**
     * 커뮤니티 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectCmmntyUserList(userVO: UserInfVO?): MutableMap<String?, Any?>?

    /**
     * 커뮤니티 관리자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectCmmntyMngrList(userVO: UserInfVO?): MutableMap<String?, Any?>?

    /**
     * 동호회 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectClubUserList(userVO: UserInfVO?): MutableMap<String?, Any?>?

    /**
     * 동호회 운영자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectClubOprtrList(userVO: UserInfVO?): MutableMap<String?, Any?>?

    /**
     * 동호회에 대한 모든 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAllClubUser(userVO: UserInfVO?): MutableList<UserInfVO?>?

    /**
     * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAllCmmntyUser(userVO: UserInfVO?): MutableList<UserInfVO?>?
}
