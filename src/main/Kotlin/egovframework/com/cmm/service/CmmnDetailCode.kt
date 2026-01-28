package egovframework.com.cmm.service

import java.io.Serializable

/**
 * 공통상세코드 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class CmmnDetailCode : Serializable {
    /**
     * codeId attribute 를 리턴한다.
     * @return String
     */
    /**
     * codeId attribute 값을 설정한다.
     * @param codeId String
     */
    /*
          * 코드ID
          */
    var codeId: String? = ""

    /**
     * codeIdNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * codeIdNm attribute 값을 설정한다.
     * @param codeIdNm String
     */
    /*
          * 코드ID명
          */
    var codeIdNm: String? = ""

    /**
     * code attribute 를 리턴한다.
     * @return String
     */
    /**
     * code attribute 값을 설정한다.
     * @param code String
     */
    /*
          * 코드
          */
    var code: String? = ""

    /**
     * codeNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * codeNm attribute 값을 설정한다.
     * @param codeNm String
     */
    /*
          * 코드명
          */
    var codeNm: String? = ""

    /**
     * codeDc attribute 를 리턴한다.
     * @return String
     */
    /**
     * codeDc attribute 값을 설정한다.
     * @param codeDc String
     */
    /*
          * 코드설명
          */
    var codeDc: String? = ""

    /**
     * useAt attribute 를 리턴한다.
     * @return String
     */
    /**
     * useAt attribute 값을 설정한다.
     * @param useAt String
     */
    /*
          * 사용여부
          */
    var useAt: String? = ""

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId String
     */
    /*
          * 최초등록자ID
          */
    var frstRegisterId: String? = ""

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @param lastUpdusrId String
     */
    /*
          * 최종수정자ID
          */
    var lastUpdusrId: String? = ""


    companion object {
        private val serialVersionUID = -6508801327314181679L
    }
}
