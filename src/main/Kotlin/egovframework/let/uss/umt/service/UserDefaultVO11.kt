package egovframework.let.uss.umt.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 사용자정보 VO클래스로서일반회원, 기업회원, 업무사용자의  비지니스로직 처리시 기타조건성 항을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class UserDefaultVO : Serializable {
    /**
     * sbscrbSttus attribute 값을  리턴한다.
     * @return String
     */
    /**
     * sbscrbSttus attribute 값을 설정한다.
     * @param sbscrbSttus String
     */
    /** 검색조건-회원상태     (0, A, D, P) */
    var sbscrbSttus: String? = "0"

    /**
     * searchCondition attribute 값을  리턴한다.
     * @return String
     */
    /**
     * searchCondition attribute 값을 설정한다.
     * @param searchCondition String
     */
    /** 검색조건  */
    var searchCondition: String? = ""

    /**
     * searchKeyword attribute 값을  리턴한다.
     * @return String
     */
    /**
     * searchKeyword attribute 값을 설정한다.
     * @param searchKeyword String
     */
    /** 검색Keyword  */
    var searchKeyword: String? = ""

    /**
     * searchUseYn attribute 값을  리턴한다.
     * @return String
     */
    /**
     * searchUseYn attribute 값을 설정한다.
     * @param searchUseYn String
     */
    /** 검색사용여부  */
    var searchUseYn: String? = ""

    /**
     * pageIndex attribute 값을  리턴한다.
     * @return int
     */
    /**
     * pageIndex attribute 값을 설정한다.
     * @param pageIndex int
     */
    /** 현재페이지  */
    var pageIndex: Int = 1

    /**
     * pageUnit attribute 값을  리턴한다.
     * @return int
     */
    /**
     * pageUnit attribute 값을 설정한다.
     * @param pageUnit int
     */
    /** 페이지갯수  */
    var pageUnit: Int = 10

    /**
     * pageSize attribute 값을  리턴한다.
     * @return int
     */
    /**
     * pageSize attribute 값을 설정한다.
     * @param pageSize int
     */
    /** 페이지사이즈  */
    var pageSize: Int = 10

    /**
     * firstIndex attribute 값을  리턴한다.
     * @return int
     */
    /**
     * firstIndex attribute 값을 설정한다.
     * @param firstIndex int
     */
    /** firstIndex  */
    var firstIndex: Int = 1

    /**
     * lastIndex attribute 값을  리턴한다.
     * @return int
     */
    /**
     * lastIndex attribute 값을 설정한다.
     * @param lastIndex int
     */
    /** lastIndex  */
    var lastIndex: Int = 1

    /**
     * recordCountPerPage attribute 값을  리턴한다.
     * @return int
     */
    /**
     * recordCountPerPage attribute 값을 설정한다.
     * @param recordCountPerPage int
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
