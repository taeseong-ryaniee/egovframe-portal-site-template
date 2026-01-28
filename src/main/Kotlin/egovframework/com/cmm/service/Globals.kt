package egovframework.com.cmm.service

/**
 * Class Name : Globals.java
 * Description : 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 * Modification Information
 *
 * 수정일         수정자                   수정내용
 * -------    --------    ---------------------------
 * 2009.01.19    박지욱          최초 생성
 *
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009. 01. 19
 * @version 1.0
 * @see
 */
object Globals {
    //파일 업로드 원 파일명
    const val ORIGIN_FILE_NM: String = "originalFileName"

    //파일 확장자
    const val FILE_EXT: String = "fileExtension"

    //파일크기
    const val FILE_SIZE: String = "fileSize"

    //업로드된 파일명
    const val UPLOAD_FILE_NM: String = "uploadFileName"

    //파일경로
    const val FILE_PATH: String = "filePath"
}
