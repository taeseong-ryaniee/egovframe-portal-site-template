package egovframework.let.cop.com.service.impl

import egovframework.let.cop.com.service.UserInfVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 협업 활용 사용자 정보 조회를 위한 데이터 접근 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("EgovUserInfManageDAO")
class EgovUserInfManageDAO : EgovAbstractMapper() {
    /**
     * 사용자 정보에 대한 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectUserList(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectUserList", userVO)
    }

    /**
     * 사용자 정보에 대한 목록 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectUserListCnt(userVO: UserInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("EgovUserInfManageDAO.selectUserListCnt", userVO)).toInt()
    }

    /**
     * 커뮤니티 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectCmmntyUserList(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectCmmntyUserList", userVO)
    }

    /**
     * 커뮤니티 사용자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectCmmntyUserListCnt(userVO: UserInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("EgovUserInfManageDAO.selectCmmntyUserListCnt", userVO)).toInt()
    }

    /**
     * 커뮤니티 관리자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectCmmntyMngrList(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectCmmntyMngrList", userVO)
    }

    /**
     * 커뮤니티 관리자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectCmmntyMngrListCnt(userVO: UserInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("EgovUserInfManageDAO.selectCmmntyMngrListCnt", userVO)).toInt()
    }

    /**
     * 동호회 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectClubUserList(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectClubUserList", userVO)
    }

    /**
     * 동호회 사용자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectClubUserListCnt(userVO: UserInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("EgovUserInfManageDAO.selectClubUserListCnt", userVO)).toInt()
    }

    /**
     * 동호회 운영자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectClubOprtrList(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectClubOprtrList", userVO)
    }

    /**
     * 동호회 운영자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectClubOprtrListCnt(userVO: UserInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("EgovUserInfManageDAO.selectClubOprtrListCnt", userVO)).toInt()
    }

    /**
     * 동호회에 대한 모든 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAllClubUser(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectAllClubUser", userVO)
    }

    /**
     * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectAllCmmntyUser(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return selectList<UserInfVO?>("EgovUserInfManageDAO.selectAllCmmntyUser", userVO)
    }
}
