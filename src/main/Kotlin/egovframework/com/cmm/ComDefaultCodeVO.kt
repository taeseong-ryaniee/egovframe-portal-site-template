package egovframework.com.cmm

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class ComDefaultCodeVO : Serializable {
    /**
     * codeId attribute를 리턴한다.
     *
     * @return the codeId
     */
    /**
     * codeId attribute 값을 설정한다.
     *
     * @param codeId
     * the codeId to set
     */
    /** 코드 ID  */
    @JvmField
    var codeId: String? = ""

    /**
     * code attribute를 리턴한다.
     *
     * @return the code
     */
    /**
     * code attribute 값을 설정한다.
     *
     * @param code
     * the code to set
     */
    /** 상세코드  */
    var code: String? = ""

    /**
     * codeNm attribute를 리턴한다.
     *
     * @return the codeNm
     */
    /**
     * codeNm attribute 값을 설정한다.
     *
     * @param codeNm
     * the codeNm to set
     */
    /** 코드명  */
    var codeNm: String? = ""

    /**
     * codeDc attribute를 리턴한다.
     *
     * @return the codeDc
     */
    /**
     * codeDc attribute 값을 설정한다.
     *
     * @param codeDc
     * the codeDc to set
     */
    /** 코드설명  */
    var codeDc: String? = ""

    /**
     * tableNm attribute를 리턴한다.
     *
     * @return the tableNm
     */
    /**
     * tableNm attribute 값을 설정한다.
     *
     * @param tableNm
     * the tableNm to set
     */
    /** 특정테이블명  */
    @JvmField
    var tableNm: String? = "" //특정테이블에서 코드정보를추출시 사용

    /**
     * haveDetailCondition attribute를 리턴한다.
     *
     * @return the haveDetailCondition
     */
    /**
     * haveDetailCondition attribute 값을 설정한다.
     *
     * @param haveDetailCondition
     * the haveDetailCondition to set
     */
    /** 상세 조건 여부  */
    var haveDetailCondition: String? = "N"

    /**
     * detailCondition attribute를 리턴한다.
     *
     * @return the detailCondition
     */
    /**
     * detailCondition attribute 값을 설정한다.
     *
     * @param detailCondition
     * the detailCondition to set
     */
    /** 상세 조건  */
    var detailCondition: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
