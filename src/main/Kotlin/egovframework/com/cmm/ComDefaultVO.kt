package egovframework.com.cmm

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * @Class Name : ComDefaultVO.java
 * @Description : ComDefaultVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    조재영         최초 생성
 *
 * @author 공통서비스 개발팀 조재영
 * @since 2009.02.01
 * @version 1.0
 * @see
 */
open class ComDefaultVO : Serializable {
    /** 검색조건  */
    @JvmField
    var searchCondition: String? = ""

    /** 검색Keyword  */
    @JvmField
    var searchKeyword: String? = ""

    /** 검색사용여부  */
    var searchUseYn: String? = ""

    /** 현재페이지  */
    @JvmField
    var pageIndex: Int = 1

    /** 페이지갯수  */
    @JvmField
    var pageUnit: Int = 10

    /** 페이지사이즈  */
    @JvmField
    var pageSize: Int = 10

    /** firstIndex  */
    @JvmField
    var firstIndex: Int = 1

    /** lastIndex  */
    @JvmField
    var lastIndex: Int = 1

    /** recordCountPerPage  */
    @JvmField
    var recordCountPerPage: Int = 10

    /**
     * searchKeywordFrom attribute를 리턴한다.
     * @return String
     */
    /**
     * searchKeywordFrom attribute 값을 설정한다.
     * @param searchKeywordFrom String
     */
    /** 검색KeywordFrom  */
    var searchKeywordFrom: String? = ""

    /**
     * searchKeywordTo attribute를 리턴한다.
     * @return String
     */
    /**
     * searchKeywordTo attribute 값을 설정한다.
     * @param searchKeywordTo String
     */
    /** 검색KeywordTo  */
    var searchKeywordTo: String? = ""

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }


    companion object {
        private val serialVersionUID = -6062858939907510631L
    }
}
