package egovframework.com.cmm.service.impl

import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.FileVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * @Class Name : EgovFileMngServiceImpl.java
 * @Description : 파일정보의 관리를 위한 구현 클래스
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
@Service("EgovFileMngService")
class EgovFileMngServiceImpl : EgovAbstractServiceImpl(), EgovFileMngService {
    @Resource(name = "FileManageDAO")
    private val fileMngDAO: FileManageDAO? = null

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @see EgovFileMngService.deleteFileInfs
     */
    @Throws(Exception::class)
    override fun deleteFileInfs(fvoList: MutableList<*>) {
        fileMngDAO!!.deleteFileInfs(fvoList)
    }

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @see EgovFileMngService.insertFileInf
     */
    @Throws(Exception::class)
    override fun insertFileInf(fvo: FileVO): String? {
        val atchFileId = fvo.atchFileId

        fileMngDAO!!.insertFileInf(fvo)

        return atchFileId
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @see EgovFileMngService.insertFileInfs
     */
    @Throws(Exception::class)
    override fun insertFileInfs(fvoList: MutableList<*>): String? {
        var atchFileId: String? = ""

        if (fvoList.size != 0) {
            atchFileId = fileMngDAO!!.insertFileInfs(fvoList)
        }
        if (atchFileId === "") {
            atchFileId = null
        }
        return atchFileId
    }

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @see EgovFileMngService.selectFileInfs
     */
    @Throws(Exception::class)
    override fun selectFileInfs(fvo: FileVO?): MutableList<FileVO?>? {
        return fileMngDAO!!.selectFileInfs(fvo)
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @see EgovFileMngService.updateFileInfs
     */
    @Throws(Exception::class)
    override fun updateFileInfs(fvoList: MutableList<*>) {
        //Delete & Insert
        fileMngDAO!!.updateFileInfs(fvoList)
    }

    /**
     * 하나의 파일을 삭제한다.
     *
     * @see EgovFileMngService.deleteFileInf
     */
    @Throws(Exception::class)
    override fun deleteFileInf(fvo: FileVO?) {
        fileMngDAO!!.deleteFileInf(fvo)
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @see EgovFileMngService.selectFileInf
     */
    @Throws(Exception::class)
    override fun selectFileInf(fvo: FileVO?): FileVO? {
        return fileMngDAO!!.selectFileInf(fvo)
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @see EgovFileMngService.getMaxFileSN
     */
    @Throws(Exception::class)
    override fun getMaxFileSN(fvo: FileVO?): Int {
        return fileMngDAO!!.getMaxFileSN(fvo)
    }

    /**
     * 전체 파일을 삭제한다.
     *
     * @see EgovFileMngService.deleteAllFileInf
     */
    @Throws(Exception::class)
    override fun deleteAllFileInf(fvo: FileVO?) {
        fileMngDAO!!.deleteAllFileInf(fvo)
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @see EgovFileMngService.selectFileListByFileNm
     */
    @Throws(Exception::class)
    override fun selectFileListByFileNm(fvo: FileVO?): MutableMap<String?, Any?> {
        val result = fileMngDAO!!.selectFileListByFileNm(fvo)
        val cnt = fileMngDAO.selectFileListCntByFileNm(fvo)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @see EgovFileMngService.selectImageFileList
     */
    @Throws(Exception::class)
    override fun selectImageFileList(vo: FileVO?): MutableList<FileVO?>? {
        return fileMngDAO!!.selectImageFileList(vo)
    }
}
