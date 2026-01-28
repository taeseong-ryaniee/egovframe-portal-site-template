package egovframework.com.cmm.service.impl

import egovframework.com.cmm.service.FileVO
import org.springframework.stereotype.Repository

/**
 * @Class Name : EgovFileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
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
@Repository("FileManageDAO")
class FileManageDAO : EgovComAbstractDAO() {
    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertFileInfs(fileList: MutableList<*>): String? {
        var vo = fileList.get(0) as FileVO
        val atchFileId = vo.atchFileId

        insert("FileManageDAO.insertFileMaster", vo)

        val iter: MutableIterator<*> = fileList.iterator()
        while (iter.hasNext()) {
            vo = iter.next() as FileVO

            insert("FileManageDAO.insertFileDetail", vo)
        }

        return atchFileId
    }

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param vo
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertFileInf(vo: FileVO?) {
        insert("FileManageDAO.insertFileMaster", vo)
        insert("FileManageDAO.insertFileDetail", vo)
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fileList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateFileInfs(fileList: MutableList<*>) {
        var vo: FileVO?
        val iter: MutableIterator<*> = fileList.iterator()
        while (iter.hasNext()) {
            vo = iter.next() as FileVO?

            insert("FileManageDAO.insertFileDetail", vo)
        }
    }

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fileList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteFileInfs(fileList: MutableList<*>) {
        val iter: MutableIterator<*> = fileList.iterator()
        var vo: FileVO?
        while (iter.hasNext()) {
            vo = iter.next() as FileVO?

            delete("FileManageDAO.deleteFileDetail", vo)
        }
    }

    /**
     * 하나의 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteFileInf(fvo: FileVO?) {
        delete("FileManageDAO.deleteFileDetail", fvo)
    }

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileInfs(vo: FileVO?): MutableList<FileVO?>? {
        return selectList<FileVO?>("FileManageDAO.selectFileList", vo)
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getMaxFileSN(fvo: FileVO?): Int {
        return (selectOne<kotlin.Any?>("FileManageDAO.getMaxFileSN", fvo) as Int?)!!
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileInf(fvo: FileVO?): FileVO? {
        return selectOne<Any?>("FileManageDAO.selectFileInf", fvo) as FileVO?
    }

    /**
     * 전체 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllFileInf(fvo: FileVO?) {
        update("FileManageDAO.deleteCOMTNFILE", fvo)
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileListByFileNm(fvo: FileVO?): MutableList<FileVO?>? {
        return selectList<FileVO?>("FileManageDAO.selectFileListByFileNm", fvo)
    }

    /**
     * 파일명 검색에 대한 목록 전체 건수를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectFileListCntByFileNm(fvo: FileVO?): Int {
        return (selectOne<kotlin.Any?>("FileManageDAO.selectFileListCntByFileNm", fvo) as Int?)!!
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectImageFileList(vo: FileVO?): MutableList<FileVO?>? {
        return selectList<FileVO?>("FileManageDAO.selectImageFileList", vo)
    }
}
