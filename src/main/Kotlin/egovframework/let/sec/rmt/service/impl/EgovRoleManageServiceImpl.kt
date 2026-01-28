package egovframework.let.sec.rmt.service.impl

import egovframework.let.sec.rmt.service.EgovRoleManageService
import egovframework.let.sec.rmt.service.RoleManage
import egovframework.let.sec.rmt.service.RoleManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 롤관리에 관한 ServiceImpl 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovRoleManageService")
class EgovRoleManageServiceImpl : EgovAbstractServiceImpl(), EgovRoleManageService {
    @Resource(name = "roleManageDAO")
    var roleManageDAO: RoleManageDAO? = null

    /**
     * 등록된 롤 정보 조회
     * @param roleManageVO RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectRole(roleManageVO: RoleManageVO?): RoleManageVO? {
        return roleManageDAO!!.selectRole(roleManageVO)
    }

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
    </RoleManageVO> */
    @Throws(Exception::class)
    override fun selectRoleList(roleManageVO: RoleManageVO?): MutableList<RoleManageVO?>? {
        return roleManageDAO!!.selectRoleList(roleManageVO)
    }

    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun deleteRole(roleManage: RoleManage?) {
        roleManageDAO!!.deleteRole(roleManage)
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun updateRole(roleManage: RoleManage?) {
        roleManageDAO!!.updateRole(roleManage)
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage RoleManage
     * @param roleManageVO RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun insertRole(roleManage: RoleManage, roleManageVO: RoleManageVO): RoleManageVO? {
        roleManageDAO!!.insertRole(roleManage)
        roleManageVO.setRoleCode(roleManage.getRoleCode())
        return roleManageDAO!!.selectRole(roleManageVO)
    }

    /**
     * 목록조회 카운트를 반환한다
     * @param roleManageVO RoleManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectRoleListTotCnt(roleManageVO: RoleManageVO?): Int {
        return roleManageDAO!!.selectRoleListTotCnt(roleManageVO)
    }

    /**
     * 등록된 모든 롤 정보 목록 조회
     * @param roleManageVO - 등록할 정보가 담긴 RoleManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectRoleAllList(roleManageVO: RoleManageVO?): MutableList<RoleManageVO?>? {
        return roleManageDAO!!.selectRoleAllList(roleManageVO)
    }
}