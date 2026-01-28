package egovframework.let.sec.rmt.service.impl

import egovframework.let.sec.rmt.service.RoleManage
import egovframework.let.sec.rmt.service.RoleManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 롤관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("roleManageDAO")
class RoleManageDAO : EgovAbstractMapper() {
    /**
     * 등록된 롤 정보 조회
     * @param roleManageVO RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectRole(roleManageVO: RoleManageVO?): RoleManageVO? {
        return selectOne<Any?>("roleManageDAO.selectRole", roleManageVO) as RoleManageVO?
    }

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
    </RoleManageVO> */
    @Throws(Exception::class)
    fun selectRoleList(roleManageVO: RoleManageVO?): MutableList<RoleManageVO?>? {
        return selectList<RoleManageVO?>("roleManageDAO.selectRoleList", roleManageVO)
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertRole(roleManage: RoleManage?) {
        insert("roleManageDAO.insertRole", roleManage)
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateRole(roleManage: RoleManage?) {
        update("roleManageDAO.updateRole", roleManage)
    }

    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteRole(roleManage: RoleManage?) {
        delete("roleManageDAO.deleteRole", roleManage)
    }

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param roleManageVO RoleManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectRoleListTotCnt(roleManageVO: RoleManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("roleManageDAO.selectAuthorListTotCnt", roleManageVO)).toInt()
    }

    /**
     * 등록된 모든 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
    </RoleManageVO> */
    @Throws(Exception::class)
    fun selectRoleAllList(roleManageVO: RoleManageVO?): MutableList<RoleManageVO?>? {
        return selectList<RoleManageVO?>("roleManageDAO.selectRoleAllList", roleManageVO)
    }
}