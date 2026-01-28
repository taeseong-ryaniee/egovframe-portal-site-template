package egovframework.let.sym.mnu.mpm.service.impl

import egovframework.let.sym.mnu.mpm.service.EgovMenuManageService
import egovframework.let.sym.mnu.mpm.service.MenuManageVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("meunManageService")
class EgovMenuManageServiceImpl : EgovAbstractServiceImpl(), EgovMenuManageService {
    @Resource(name = "menuManageDAO")
    private val menuManageDAO: MenuManageDAO? = null

    /*### 메뉴관련 프로세스 ###*/
    /**
     * MainMenu Head Menu 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectMainMenuHead(vo: MenuManageVO?): MutableList<*>? {
        return menuManageDAO!!.selectMainMenuHead(vo)
    }

    /**
     * MainMenu Head Left 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectMainMenuLeft(vo: MenuManageVO?): MutableList<*>? {
        return menuManageDAO!!.selectMainMenuLeft(vo)
    }

    /**
     * MainMenu Head MenuURL 조회
     * @param  iMenuNo  int
     * @param  sUniqId  String
     * @return String
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectLastMenuURL(iMenuNo: Int, sUniqId: String?): String? {
        val vo = MenuManageVO()
        vo.setMenuNo(selectLastMenuNo(iMenuNo, sUniqId))
        return menuManageDAO!!.selectLastMenuURL(vo)
    }

    /**
     * MainMenu Head Menu MenuNo 조회
     * @param  iMenuNo  int
     * @param  sUniqId  String
     * @return String
     * @exception Exception
     */
    @Throws(Exception::class)
    private fun selectLastMenuNo(iMenuNo: Int, sUniqId: String?): Int {
        var chkMenuNo = iMenuNo
        var cntMenuNo = 0
        while (chkMenuNo > -1) {
            chkMenuNo = selectLastMenuNoChk(chkMenuNo, sUniqId)
            if (chkMenuNo > 0) {
                cntMenuNo = chkMenuNo
            }
        }
        return cntMenuNo
    }

    /**
     * MainMenu Head Menu Last MenuNo 조회
     * @param  iMenuNo  int
     * @param  sUniqId  String
     * @return String
     * @exception Exception
     */
    @Throws(Exception::class)
    private fun selectLastMenuNoChk(iMenuNo: Int, sUniqId: String?): Int {
        val vo = MenuManageVO()
        vo.setMenuNo(iMenuNo)
        vo.setTempValue(sUniqId)
        var chkMenuNo = 0
        var cntMenuNo = 0
        cntMenuNo = menuManageDAO!!.selectLastMenuNoCnt(vo)
        if (cntMenuNo > 0) {
            chkMenuNo = menuManageDAO.selectLastMenuNo(vo)
        } else {
            chkMenuNo = -1
        }
        return chkMenuNo
    }

    /**
     * MainMenu Head Menu 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectMainMenuHeadByAuthor(vo: MenuManageVO?): MutableList<*>? {
        return menuManageDAO!!.selectMainMenuHeadByAuthor(vo)
    }

    /**
     * MainMenu Head Left 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    override fun selectMainMenuLeftByAuthor(vo: MenuManageVO?): MutableList<*>? {
        return menuManageDAO!!.selectMainMenuLeftByAuthor(vo)
    }
}