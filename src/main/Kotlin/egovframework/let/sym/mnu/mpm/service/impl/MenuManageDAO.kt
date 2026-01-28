package egovframework.let.sym.mnu.mpm.service.impl

import egovframework.let.sym.mnu.mpm.service.MenuManageVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 메뉴관리, 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("menuManageDAO")
class MenuManageDAO : EgovAbstractMapper() {
    /*### 메뉴관련 프로세스 ###*/
    /**
     * MainMenu Head Menu 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuHead(vo: MenuManageVO?): MutableList<*>? {
        return selectList<Any?>("menuManageDAO.selectMainMenuHead", vo)
    }

    /**
     * MainMenu Left Menu 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuLeft(vo: MenuManageVO?): MutableList<*>? {
        return selectList<Any?>("menuManageDAO.selectMainMenuLeft", vo)
    }

    /**
     * MainMenu Head MenuURL 조회
     * @param vo MenuManageVO
     * @return  String
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectLastMenuURL(vo: MenuManageVO?): String {
        return (String)<Object> selectOne < kotlin . Any ? > ("menuManageDAO.selectLastMenuURL", vo)
    }

    /**
     * MainMenu Left Menu 조회
     * @param vo MenuManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectLastMenuNo(vo: MenuManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("menuManageDAO.selectLastMenuNo", vo)).toInt()
    }

    /**
     * MainMenu Left Menu 조회
     * @param vo MenuManageVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectLastMenuNoCnt(vo: MenuManageVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("menuManageDAO.selectLastMenuNoCnt", vo)).toInt()
    }

    /**
     * MainMenu Head Menu 조회 - Anonymous
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuHeadByAuthor(vo: MenuManageVO?): MutableList<*>? {
        return selectList<Any?>("menuManageDAO.selectMainMenuHeadByAuthor", vo)
    }

    /**
     * MainMenu Left Menu 조회 - Anonymous
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuLeftByAuthor(vo: MenuManageVO?): MutableList<*>? {
        return selectList<Any?>("menuManageDAO.selectMainMenuLeftByAuthor", vo)
    }
}