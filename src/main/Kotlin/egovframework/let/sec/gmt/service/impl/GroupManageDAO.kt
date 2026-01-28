package egovframework.let.sec.gmt.service.impl

import egovframework.let.sec.gmt.service.GroupManage
import egovframework.let.sec.gmt.service.GroupManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 그룹관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("groupManageDAO")
class GroupManageDAO : EgovAbstractMapper() {
    /**
     * 검색조건에 따른 그룹정보를 조회
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectGroup(groupManageVO: GroupManageVO?): GroupManageVO? {
        return selectOne<Any?>("groupManageDAO.selectGroup", groupManageVO) as GroupManageVO?
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectGroupList(groupManageVO: GroupManageVO?): MutableList<GroupManageVO?>? {
        return selectList<GroupManageVO?>("groupManageDAO.selectGroupList", groupManageVO)
    }

    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param groupManage GroupManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertGroup(groupManage: GroupManage?) {
        insert("groupManageDAO.insertGroup", groupManage)
    }

    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param groupManage GroupManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateGroup(groupManage: GroupManage?) {
        update("groupManageDAO.updateGroup", groupManage)
    }

    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param groupManage GroupManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteGroup(groupManage: GroupManage?) {
        delete("groupManageDAO.deleteGroup", groupManage)
    }

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param groupManageVO GroupManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectGroupListTotCnt(groupManageVO: GroupManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("groupManageDAO.selectGroupListTotCnt", groupManageVO)).toInt()
    }
}