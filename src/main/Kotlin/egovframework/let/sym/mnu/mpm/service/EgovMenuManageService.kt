package egovframework.let.sym.mnu.mpm.service

/**
 * 메뉴관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovMenuManageService {
    /*### 메뉴관련 프로세스 ###*/
    /**
     * MainMenu Head Menu 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuHead(vo: MenuManageVO?): MutableList<*>?

    /**
     * MainMenu Head Left 조회
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuLeft(vo: MenuManageVO?): MutableList<*>?

    /**
     * MainMenu Head MenuURL 조회
     * @param iMenuNo int
     * @param sUniqId String
     * @return String
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectLastMenuURL(iMenuNo: Int, sUniqId: String?): String?

    /**
     * MainMenu Head Menu 조회 - Anonymous
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuHeadByAuthor(vo: MenuManageVO?): MutableList<*>?

    /**
     * MainMenu Head Left 조회 - Anonymous
     * @param vo MenuManageVO
     * @return List
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectMainMenuLeftByAuthor(vo: MenuManageVO?): MutableList<*>?
}