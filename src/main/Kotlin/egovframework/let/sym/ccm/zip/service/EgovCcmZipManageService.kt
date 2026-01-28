package egovframework.let.sym.ccm.zip.service

import java.io.InputStream

/**
 *
 * 우편번호에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovCcmZipManageService {
    /**
     * 우편번호를 삭제한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteZip(zip: Zip?)

    /**
     * 우편번호 전체를 삭제한다.
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteAllZip()

    /**
     * 우편번호를 등록한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertZip(zip: Zip?)

    /**
     * 우편번호 엑셀파일을 등록한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertExcelZip(file: InputStream?)

    /**
     * 우편번호 상세항목을 조회한다.
     * @param zip
     * @return Zip(우편번호)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectZipDetail(zip: Zip?): Zip?

    /**
     * 우편번호 목록을 조회한다.
     * @param searchVO
     * @return List(우편번호 목록)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectZipList(searchVO: ZipVO?): MutableList<*>?

    /**
     * 우편번호 총 갯수를 조회한다.
     * @param searchVO
     * @return int(우편번호 총 갯수)
     */
    @Throws(Exception::class)
    fun selectZipListTotCnt(searchVO: ZipVO?): Int

    /**
     * 우편번호를 수정한다.
     * @param zip
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateZip(zip: Zip?)
}
