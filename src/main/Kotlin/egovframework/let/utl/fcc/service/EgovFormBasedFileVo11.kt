package egovframework.let.utl.fcc.service

import java.io.Serializable

/**
 * @Class Name  : EgovFormBasedFileVo.java
 * @Description : Form-based File Upload VO
 * @Modification Information
 *
 * 수정일         수정자                   수정내용
 * -------          --------        ---------------------------
 * 2009.08.26       한성곤                  최초 생성
 *
 * @author 공통컴포넌트 개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 * @see
 * Copyright
 */
class EgovFormBasedFileVo : Serializable {
    /**
     * fileName attribute를 리턴한다.
     * @return the fileName
     */
    /**
     * fileName attribute 값을 설정한다.
     * @param fileName the fileName to set
     */
    /** 파일명  */
    var fileName: String? = ""
    /**
     * contentType attribute를 리턴한다.
     * @return the contentType
     */
    /**
     * contentType attribute 값을 설정한다.
     * @param contentType the contentType to set
     */
    /** ContextType  */
    var contentType: String? = ""
    /**
     * serverSubPath attribute를 리턴한다.
     * @return the serverSubPath
     */
    /**
     * serverSubPath attribute 값을 설정한다.
     * @param serverSubPath the serverSubPath to set
     */
    /** 하위 디렉토리 지정  */
    var serverSubPath: String? = ""
    /**
     * physicalName attribute를 리턴한다.
     * @return the physicalName
     */
    /**
     * physicalName attribute 값을 설정한다.
     * @param physicalName the physicalName to set
     */
    /** 물리적 파일명  */
    var physicalName: String? = ""
    /**
     * size attribute를 리턴한다.
     * @return the size
     */
    /**
     * size attribute 값을 설정한다.
     * @param size the size to set
     */
    /** 파일 사이즈  */
    var size: Long = 0L
}
