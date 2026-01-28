package egovframework.com.cmm.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 * 수정일       수정자         수정내용
 * -------        -------     -------------------
 * 2009. 3. 25.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 */
class FileVO : Serializable {
    /**
     * atchFileId attribute를 리턴한다.
     *
     * @return the atchFileId
     */
    /**
     * atchFileId attribute 값을 설정한다.
     *
     * @param atchFileId
     * the atchFileId to set
     */
    /**
     * 첨부파일 아이디
     */
    @JvmField
    var atchFileId: String? = ""
    /**
     * creatDt attribute를 리턴한다.
     *
     * @return the creatDt
     */
    /**
     * creatDt attribute 값을 설정한다.
     *
     * @param creatDt
     * the creatDt to set
     */
    /**
     * 생성일자
     */
    var creatDt: String? = ""
    /**
     * fileCn attribute를 리턴한다.
     *
     * @return the fileCn
     */
    /**
     * fileCn attribute 값을 설정한다.
     *
     * @param fileCn
     * the fileCn to set
     */
    /**
     * 파일내용
     */
    var fileCn: String? = ""
    /**
     * fileExtsn attribute를 리턴한다.
     *
     * @return the fileExtsn
     */
    /**
     * fileExtsn attribute 값을 설정한다.
     *
     * @param fileExtsn
     * the fileExtsn to set
     */
    /**
     * 파일확장자
     */
    @JvmField
    var fileExtsn: String? = ""
    /**
     * fileMg attribute를 리턴한다.
     *
     * @return the fileMg
     */
    /**
     * fileMg attribute 값을 설정한다.
     *
     * @param fileMg
     * the fileMg to set
     */
    /**
     * 파일크기
     */
    var fileMg: String? = ""
    /**
     * fileSn attribute를 리턴한다.
     *
     * @return the fileSn
     */
    /**
     * fileSn attribute 값을 설정한다.
     *
     * @param fileSn
     * the fileSn to set
     */
    /**
     * 파일연번
     */
    @JvmField
    var fileSn: String? = ""
    /**
     * fileStreCours attribute를 리턴한다.
     *
     * @return the fileStreCours
     */
    /**
     * fileStreCours attribute 값을 설정한다.
     *
     * @param fileStreCours
     * the fileStreCours to set
     */
    /**
     * 파일저장경로
     */
    @JvmField
    var fileStreCours: String? = ""
    /**
     * orignlFileNm attribute를 리턴한다.
     *
     * @return the orignlFileNm
     */
    /**
     * orignlFileNm attribute 값을 설정한다.
     *
     * @param orignlFileNm
     * the orignlFileNm to set
     */
    /**
     * 원파일명
     */
    @JvmField
    var orignlFileNm: String? = ""
    /**
     * streFileNm attribute를 리턴한다.
     *
     * @return the streFileNm
     */
    /**
     * streFileNm attribute 값을 설정한다.
     *
     * @param streFileNm
     * the streFileNm to set
     */
    /**
     * 저장파일명
     */
    @JvmField
    var streFileNm: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
