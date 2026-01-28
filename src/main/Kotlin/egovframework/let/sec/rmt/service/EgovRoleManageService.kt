package egovframework.let.sec.rmt.service

/**
 * 롤관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovRoleManageService {
    /**
     * 등록된 롤 정보 조회
     * @param roleManageVO RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectRole(roleManageVO: RoleManageVO?): RoleManageVO?

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
    </RoleManageVO> */
    @Throws(Exception::class)
    fun selectRoleList(roleManageVO: RoleManageVO?): MutableList<RoleManageVO?>?

    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun deleteRole(roleManage: RoleManage?)

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage RoleManage
     * @exception Exception
     */
    @Throws(Exception::class)
    fun updateRole(roleManage: RoleManage?)

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage RoleManage
     * @param roleManageVO RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun insertRole(roleManage: RoleManage?, roleManageVO: RoleManageVO?): RoleManageVO?

    /**
     * 목록조회 카운트를 반환한다
     * @param roleManageVO RoleManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectRoleListTotCnt(roleManageVO: RoleManageVO?): Int

    /**
     * 등록된 모든 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
    </RoleManageVO> */
    @Throws(Exception::class)
    fun selectRoleAllList(roleManageVO: RoleManageVO?): MutableList<RoleManageVO?>?
}