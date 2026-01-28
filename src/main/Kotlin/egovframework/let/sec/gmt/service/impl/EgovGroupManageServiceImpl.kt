package egovframework.let.sec.gmt.service.impl

import egovframework.let.sec.gmt.service.EgovGroupManageService
import egovframework.let.sec.gmt.service.GroupManage
import egovframework.let.sec.gmt.service.GroupManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 그룹관리에 관한 ServiceImpl 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovGroupManageService")
class EgovGroupManageServiceImpl : EgovAbstractServiceImpl(), EgovGroupManageService {
    @Resource(name = "groupManageDAO")
    private val groupManageDAO: GroupManageDAO? = null

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param groupManageVO GroupManageVO
     * @return List<GroupManageVO>
     * @exception Exception
    </GroupManageVO> */
    @Throws(Exception::class)
    override fun selectGroupList(groupManageVO: GroupManageVO?): MutableList<GroupManageVO?>? {
        return groupManageDAO!!.selectGroupList(groupManageVO)
    }

    /**
     * 검색조건에 따른 그룹정보를 조회
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectGroup(groupManageVO: GroupManageVO?): GroupManageVO? {
        return groupManageDAO!!.selectGroup(groupManageVO)
    }

    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param groupManage GroupManage
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun insertGroup(groupManage: GroupManage, groupManageVO: GroupManageVO): GroupManageVO? {
        groupManageDAO!!.insertGroup(groupManage)
        groupManageVO.setGroupId(groupManage.getGroupId())
        return groupManageDAO.selectGroup(groupManageVO)
    }

    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param groupManage GroupManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateGroup(groupManage: GroupManage?) {
        groupManageDAO!!.updateGroup(groupManage)
    }

    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param groupManage GroupManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun deleteGroup(groupManage: GroupManage?) {
        groupManageDAO!!.deleteGroup(groupManage)
    }

    /**
     * 목록조회 카운트를 반환한다
     * @param groupManageVO GroupManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectGroupListTotCnt(groupManageVO: GroupManageVO?): Int {
        return groupManageDAO!!.selectGroupListTotCnt(groupManageVO)
    }
}