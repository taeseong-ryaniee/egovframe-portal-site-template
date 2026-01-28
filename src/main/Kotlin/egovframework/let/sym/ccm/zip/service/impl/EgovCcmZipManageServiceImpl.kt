package egovframework.let.sym.ccm.zip.service.impl

import egovframework.let.sym.ccm.zip.service.EgovCcmZipManageService
import egovframework.let.sym.ccm.zip.service.Zip
import egovframework.let.sym.ccm.zip.service.ZipVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import java.io.InputStream
import javax.annotation.Resource

//import org.egovframe.rte.fdl.excel.EgovExcelService;
/**
 *
 * 우편번호에 대한 서비스 구현클래스를 정의한다
 * @author 공통서비스 개발팀 이중호.
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("ZipManageService")
class EgovCcmZipManageServiceImpl : EgovAbstractServiceImpl(), EgovCcmZipManageService {
    @Resource(name = "ZipManageDAO")
    private val zipManageDAO: ZipManageDAO? = null

    //@Resource(name = "excelZipService")
    //private EgovExcelService excelZipService;
    /**
     * 우편번호를 삭제한다.
     */
    @Throws(Exception::class)
    override fun deleteZip(zip: Zip?) {
        zipManageDAO!!.deleteZip(zip)
    }

    /**
     * 우편번호 전체를 삭제한다.
     */
    @Throws(Exception::class)
    override fun deleteAllZip() {
        zipManageDAO!!.deleteAllZip()
    }

    /**
     * 우편번호를 등록한다.
     */
    @Throws(Exception::class)
    override fun insertZip(zip: Zip?) {
        zipManageDAO!!.insertZip(zip)
    }

    /**
     * 우편번호 엑셀파일을 등록한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertExcelZip(file: InputStream?) {
//		zipManageDAO.insertExcelZip();
//		excelZipService.uploadExcel("ZipManageDAO.insertExcelZip", file, 2, (long) 5000);
    }


    /**
     * 우편번호 상세항목을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectZipDetail(zip: Zip?): Zip? {
        val ret = zipManageDAO!!.selectZipDetail(zip)
        return ret
    }

    /**
     * 우편번호 목록을 조회한다.
     */
    @Throws(Exception::class)
    override fun selectZipList(searchVO: ZipVO?): MutableList<*>? {
        return zipManageDAO!!.selectZipList(searchVO)
    }

    /**
     * 우편번호 총 갯수를 조회한다.
     */
    @Throws(Exception::class)
    override fun selectZipListTotCnt(searchVO: ZipVO?): Int {
        return zipManageDAO!!.selectZipListTotCnt(searchVO)
    }

    /**
     * 우편번호를 수정한다.
     */
    @Throws(Exception::class)
    override fun updateZip(zip: Zip?) {
        zipManageDAO!!.updateZip(zip)
    }
}
