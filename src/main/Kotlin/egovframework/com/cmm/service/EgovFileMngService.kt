package egovframework.com.cmm.service

/**
 * @Class Name : EgovFileMngService.java
 * @Description : 파일정보의 관리를 위한 서비스 인터페이스
 * @Modification Information
 *
 * 수정일       수정자         수정내용
 * -------        -------     -------------------
 * 2009. 3. 25.     이삼섭    최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 */
interface EgovFileMngService {
    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileInfs(fvo: FileVO?): MutableList<FileVO?>?

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvo
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertFileInf(fvo: FileVO?): String?

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertFileInfs(fvoList: MutableList<*>?): String?

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fvoList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateFileInfs(fvoList: MutableList<*>?)

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fvoList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteFileInfs(fvoList: MutableList<*>?)

    /**
     * 하나의 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteFileInf(fvo: FileVO?)

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileInf(fvo: FileVO?): FileVO?

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getMaxFileSN(fvo: FileVO?): Int

    /**
     * 전체 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllFileInf(fvo: FileVO?)

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileListByFileNm(fvo: FileVO?): MutableMap<String?, Any?>?

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectImageFileList(vo: FileVO?): MutableList<FileVO?>?
}
