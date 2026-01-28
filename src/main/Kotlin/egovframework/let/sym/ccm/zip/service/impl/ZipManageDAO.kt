package egovframework.let.sym.ccm.zip.service.impl

import egovframework.let.sym.ccm.zip.service.Zip
import egovframework.let.sym.ccm.zip.service.ZipVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 *
 * 우편번호에 대한 데이터 접근 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("ZipManageDAO")
class ZipManageDAO : EgovAbstractMapper() {
    /**
     * 우편번호를 삭제한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteZip(zip: Zip?) {
        delete("ZipManageDAO.deleteZip", zip)
    }

    /**
     * 우편번호 전체를 삭제한다.
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllZip() {
        delete("ZipManageDAO.deleteAllZip", Any())
    }

    /**
     * 우편번호를 등록한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertZip(zip: Zip?) {
        insert("ZipManageDAO.insertZip", zip)
    }

    /**
     * 우편번호 엑셀파일을 등록한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertExcelZip() {
        delete("ZipManageDAO.deleteAllZip", Any())
    }


    /**
     * 우편번호 상세항목을 조회한다.
     * @param zip
     * @return Zip(우편번호)
     */
    @Throws(Exception::class)
    fun selectZipDetail(zip: Zip?): Zip? {
        return selectOne<Any?>("ZipManageDAO.selectZipDetail", zip) as Zip?
    }


    /**
     * 우편번호 목록을 조회한다.
     * @param searchVO
     * @return List(우편번호 목록)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectZipList(searchVO: ZipVO?): MutableList<*>? {
        return selectList<Any?>("ZipManageDAO.selectZipList", searchVO)
    }

    /**
     * 우편번호 총 갯수를 조회한다.
     * @param searchVO
     * @return int(우편번호 총 갯수)
     */
    @Throws(Exception::class)
    fun selectZipListTotCnt(searchVO: ZipVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("ZipManageDAO.selectZipListTotCnt", searchVO)).toInt()
    }

    /**
     * 우편번호를 수정한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateZip(zip: Zip?) {
        update("ZipManageDAO.updateZip", zip)
    }
}
