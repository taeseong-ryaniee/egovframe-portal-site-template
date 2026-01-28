package egovframework.com.cmm.service

import egovframework.com.cmm.EgovWebUtil.filePathBlackList
import egovframework.let.utl.fcc.service.EgovStringUtil
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.annotation.Resource

/**
 * @author 공통 서비스 개발팀 이삼섭
 * @version 1.0
 * @Class Name  : EgovFileMngUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 *
 * 수정일         수정자                   수정내용
 * -------          --------        ---------------------------
 * 2009.02.13       이삼섭                  최초 생성
 * 2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * @see
 * @since 2009. 02. 13
 */
@Component("EgovFileMngUtil")
class EgovFileMngUtil {
    @Resource(name = "propertiesService")
    protected var propertyService: EgovPropertyService? = null

    @Resource(name = "egovFileIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     *
     * @param files
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun parseFileInf(
        files: MutableMap<String?, MultipartFile?>,
        KeyStr: String?,
        fileKeyParam: Int,
        atchFileId: String?,
        storePath: String?
    ): MutableList<FileVO?> {
        var fileKey = fileKeyParam

        var storePathString: String? = ""
        var atchFileIdString: String? = ""

        if (storePath == null || "" == storePath) {
            storePathString = propertyService!!.getString("Globals.fileStorePath")
        } else {
            storePathString = propertyService!!.getString(storePath)
        }

        if (atchFileId == null || "" == atchFileId) {
            atchFileIdString = idgenService!!.getNextStringId()
        } else {
            atchFileIdString = atchFileId
        }

        val saveFolder = File(filePathBlackList(storePathString))

        if (!saveFolder.exists() || saveFolder.isFile()) {
            saveFolder.mkdirs()
        }

        val itr: MutableIterator<MutableMap.MutableEntry<String?, MultipartFile>> = files.entries.iterator()
        var file: MultipartFile
        var filePath = ""
        val result: MutableList<FileVO?> = ArrayList<FileVO?>()
        var fvo: FileVO?

        while (itr.hasNext()) {
            val entry = itr.next()

            file = entry.value
            val orginFileName = file.getOriginalFilename()

            //--------------------------------------
            // 원 파일명이 없는 경우 처리
            // (첨부가 되지 않은 input file type)
            //--------------------------------------
            if ("" == orginFileName) {
                continue
            }

            /**/------------------------------------* /
            val index = orginFileName!!.lastIndexOf(".")
            //String fileName = orginFileName.substring(0, index);
            val fileExt = orginFileName.substring(index + 1)
            val newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey
            val _size = file.getSize()

            if ("" != orginFileName) {
                filePath = storePathString + File.separator + newName
                file.transferTo(File(filePathBlackList(filePath)))
            }
            fvo = FileVO()
            fvo.setFileExtsn(fileExt)
            fvo.setFileStreCours(storePathString)
            fvo.setFileMg(_size.toString())
            fvo.setOrignlFileNm(orginFileName)
            fvo.setStreFileNm(newName)
            fvo.setAtchFileId(atchFileIdString)
            fvo.setFileSn(fileKey.toString())

            //writeFile(file, newName, storePathString);
            result.add(fvo)

            fileKey++
        }

        return result
    }

    companion object {
        const val BUFF_SIZE: Int = 2048

        private val LOGGER: Logger? = LoggerFactory.getLogger(EgovFileMngUtil::class.java)
    }
}
