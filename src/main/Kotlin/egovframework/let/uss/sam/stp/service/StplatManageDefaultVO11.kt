package egovframework.let.uss.sam.stp.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 *
 * 약관내용을 처리하는 DefaultVO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class StplatManageDefaultVO : Serializable {
    /**
     * searchCondition attribute 를 리턴한다.
     * @return the String
     */
    /**
     * searchCondition attribute 값을 설정한다.
     * @return searchCondition String
     */
    /** 검색조건  */
    var searchCondition: String? = ""

    /**
     * searchKeyword attribute 를 리턴한다.
     * @return the String
     */
    /**
     * searchKeyword attribute 값을 설정한다.
     * @return searchKeyword String
     */
    /** 검색Keyword  */
    var searchKeyword: String? = ""

    /**
     * searchUseYn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * searchUseYn attribute 값을 설정한다.
     * @return searchUseYn String
     */
    /** 검색사용여부  */
    var searchUseYn: String? = ""

    /**
     * pageIndex attribute 를 리턴한다.
     * @return the int
     */
    /**
     * pageIndex attribute 값을 설정한다.
     * @return pageIndex int
     */
    /** 현재페이지  */
    var pageIndex: Int = 1

    /**
     * pageUnit attribute 를 리턴한다.
     * @return the int
     */
    /**
     * pageUnit attribute 값을 설정한다.
     * @return pageUnit int
     */
    /** 페이지갯수  */
    var pageUnit: Int = 10

    /**
     * pageSize attribute 를 리턴한다.
     * @return the int
     */
    /**
     * pageSize attribute 값을 설정한다.
     * @return pageSize int
     */
    /** 페이지사이즈  */
    var pageSize: Int = 10

    /**
     * firstIndex attribute 를 리턴한다.
     * @return the int
     */
    /**
     * firstIndex attribute 값을 설정한다.
     * @return firstIndex int
     */
    /** firstIndex  */
    var firstIndex: Int = 1

    /**
     * lastIndex attribute 를 리턴한다.
     * @return the int
     */
    /**
     * lastIndex attribute 값을 설정한다.
     * @return lastIndex int
     */
    /** lastIndex  */
    var lastIndex: Int = 1

    /**
     * recordCountPerPage attribute 를 리턴한다.
     * @return the int
     */
    /**
     * recordCountPerPage attribute 값을 설정한다.
     * @return recordCountPerPage int
     */
    /** recordCountPerPage  */
    var recordCountPerPage: Int = 10

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
