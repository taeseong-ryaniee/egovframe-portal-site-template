package egovframework.let.cop.com.service.impl

import egovframework.let.cop.com.service.EgovUserInfManageService
import egovframework.let.cop.com.service.UserInfVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 협업에서 사용할 사용자 조회 서비스 기능 구현 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("EgovUserInfManageService")
class EgovUserInfManageServiceImpl : EgovAbstractServiceImpl(), EgovUserInfManageService {
    @Resource(name = "EgovUserInfManageDAO")
    private val userInfDAO: EgovUserInfManageDAO? = null

    /**
     * 동호회 운영자 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectClubOprtrList
     */
    @Throws(Exception::class)
    override fun selectClubOprtrList(userVO: UserInfVO?): MutableMap<String?, Any?> {
        val result = userInfDAO!!.selectClubOprtrList(userVO)
        val cnt = userInfDAO.selectClubOprtrListCnt(userVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 동호회 사용자 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectClubUserList
     */
    @Throws(Exception::class)
    override fun selectClubUserList(userVO: UserInfVO?): MutableMap<String?, Any?> {
        val result = userInfDAO!!.selectClubUserList(userVO)
        val cnt = userInfDAO.selectClubUserListCnt(userVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 커뮤니티 관리자 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectCmmntyMngrList
     */
    @Throws(Exception::class)
    override fun selectCmmntyMngrList(userVO: UserInfVO?): MutableMap<String?, Any?> {
        val result = userInfDAO!!.selectCmmntyMngrList(userVO)
        val cnt = userInfDAO.selectCmmntyMngrListCnt(userVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 커뮤니티 사용자 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectCmmntyUserList
     */
    @Throws(Exception::class)
    override fun selectCmmntyUserList(userVO: UserInfVO?): MutableMap<String?, Any?> {
        val result = userInfDAO!!.selectCmmntyUserList(userVO)
        val cnt = userInfDAO.selectCmmntyUserListCnt(userVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 사용자 정보에 대한 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectUserList
     */
    @Throws(Exception::class)
    override fun selectUserList(userVO: UserInfVO?): MutableMap<String?, Any?> {
        val result = userInfDAO!!.selectUserList(userVO)
        val cnt = userInfDAO.selectUserListCnt(userVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 동호회에 대한 모든 사용자 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectAllClubUser
     */
    @Throws(Exception::class)
    override fun selectAllClubUser(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return userInfDAO!!.selectAllClubUser(userVO)
    }

    /**
     * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
     *
     * @see EgovUserInfManageService.selectAllCmmntyUser
     */
    @Throws(Exception::class)
    override fun selectAllCmmntyUser(userVO: UserInfVO?): MutableList<UserInfVO?>? {
        return userInfDAO!!.selectAllCmmntyUser(userVO)
    }
}
