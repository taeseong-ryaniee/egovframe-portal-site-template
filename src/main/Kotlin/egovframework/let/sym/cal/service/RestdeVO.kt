package egovframework.let.sym.cal.service

import java.io.Serializable

/**
 *
 * 휴일 VO 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class RestdeVO : Restde(), Serializable {
    /**
     * searchCondition attribute 를 리턴한다.
     * @return String
     */
    /**
     * searchCondition attribute 값을 설정한다.
     * @param searchCondition String
     */
    /** 검색조건  */
    var searchCondition: String? = ""

    /**
     * searchKeyword attribute 를 리턴한다.
     * @return String
     */
    /**
     * searchKeyword attribute 값을 설정한다.
     * @param searchKeyword String
     */
    /** 검색Keyword  */
    var searchKeyword: String? = ""

    /**
     * searchUseYn attribute 를 리턴한다.
     * @return String
     */
    /**
     * searchUseYn attribute 값을 설정한다.
     * @param searchUseYn String
     */
    /** 검색사용여부  */
    var searchUseYn: String? = ""

    /**
     * pageIndex attribute 를 리턴한다.
     * @return int
     */
    /**
     * pageIndex attribute 값을 설정한다.
     * @param pageIndex int
     */
    /** 현재페이지  */
    @JvmField
    var pageIndex: Int = 1

    /**
     * pageUnit attribute 를 리턴한다.
     * @return int
     */
    /**
     * pageUnit attribute 값을 설정한다.
     * @param pageUnit int
     */
    /** 페이지갯수  */
    @JvmField
    var pageUnit: Int = 10

    /**
     * pageSize attribute 를 리턴한다.
     * @return int
     */
    /**
     * pageSize attribute 값을 설정한다.
     * @param pageSize int
     */
    /** 페이지사이즈  */
    @JvmField
    var pageSize: Int = 10

    /**
     * firstIndex attribute 를 리턴한다.
     * @return int
     */
    /**
     * firstIndex attribute 값을 설정한다.
     * @param firstIndex int
     */
    /** firstIndex  */
    @JvmField
    var firstIndex: Int = 1

    /**
     * lastIndex attribute 를 리턴한다.
     * @return int
     */
    /**
     * lastIndex attribute 값을 설정한다.
     * @param lastIndex int
     */
    /** lastIndex  */
    @JvmField
    var lastIndex: Int = 1

    /**
     * recordCountPerPage attribute 를 리턴한다.
     * @return int
     */
    /**
     * recordCountPerPage attribute 값을 설정한다.
     * @param recordCountPerPage int
     */
    /** recordCountPerPage  */
    @JvmField
    var recordCountPerPage: Int = 10

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
