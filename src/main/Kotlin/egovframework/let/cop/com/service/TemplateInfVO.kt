package egovframework.let.cop.com.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 템플릿 정보 관리를 위한 VO 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.03.17
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class TemplateInfVO : TemplateInf(), Serializable {
    /**
     * frstRegisterNm attribute를 리턴한다.
     *
     * @return the frstRegisterNm
     */
    /**
     * frstRegisterNm attribute 값을 설정한다.
     *
     * @param frstRegisterNm
     * the frstRegisterNm to set
     */
    /** 최초 등록자명  */
    var frstRegisterNm: String? = ""

    /**
     * lastUpdusrNm attribute를 리턴한다.
     *
     * @return the lastUpdusrNm
     */
    /**
     * lastUpdusrNm attribute 값을 설정한다.
     *
     * @param lastUpdusrNm
     * the lastUpdusrNm to set
     */
    /** 최종 수정자명  */
    var lastUpdusrNm: String? = ""

    /**
     * tmplatSeCodeNm attribute를 리턴한다.
     *
     * @return the tmplatSeCodeNm
     */
    /**
     * tmplatSeCodeNm attribute 값을 설정한다.
     *
     * @param tmplatSeCodeNm
     * the tmplatSeCodeNm to set
     */
    /** 템플릿 구분 코드명  */
    var tmplatSeCodeNm: String? = ""

    /**
     * searchBgnDe attribute를 리턴한다.
     *
     * @return the searchBgnDe
     */
    /**
     * searchBgnDe attribute 값을 설정한다.
     *
     * @param searchBgnDe
     * the searchBgnDe to set
     */
    /** 검색시작일  */
    var searchBgnDe: String? = ""

    /**
     * searchCnd attribute를 리턴한다.
     *
     * @return the searchCnd
     */
    /**
     * searchCnd attribute 값을 설정한다.
     *
     * @param searchCnd
     * the searchCnd to set
     */
    /** 검색조건  */
    var searchCnd: String? = ""

    /**
     * searchEndDe attribute를 리턴한다.
     *
     * @return the searchEndDe
     */
    /**
     * searchEndDe attribute 값을 설정한다.
     *
     * @param searchEndDe
     * the searchEndDe to set
     */
    /** 검색종료일  */
    var searchEndDe: String? = ""

    /**
     * searchWrd attribute를 리턴한다.
     *
     * @return the searchWrd
     */
    /**
     * searchWrd attribute 값을 설정한다.
     *
     * @param searchWrd
     * the searchWrd to set
     */
    /** 검색단어  */
    var searchWrd: String? = ""

    /**
     * sortOrdr attribute를 리턴한다.
     *
     * @return the sortOrdr
     */
    /**
     * sortOrdr attribute 값을 설정한다.
     *
     * @param sortOrdr
     * the sortOrdr to set
     */
    /** 정렬순서(DESC,ASC)  */
    var sortOrdr: String? = ""

    /**
     * searchUseYn attribute를 리턴한다.
     *
     * @return the searchUseYn
     */
    /**
     * searchUseYn attribute 값을 설정한다.
     *
     * @param searchUseYn
     * the searchUseYn to set
     */
    /** 검색사용여부  */
    var searchUseYn: String? = ""

    /**
     * pageIndex attribute를 리턴한다.
     *
     * @return the pageIndex
     */
    /**
     * pageIndex attribute 값을 설정한다.
     *
     * @param pageIndex
     * the pageIndex to set
     */
    /** 현재페이지  */
    @JvmField
    var pageIndex: Int = 1

    /**
     * pageUnit attribute를 리턴한다.
     *
     * @return the pageUnit
     */
    /**
     * pageUnit attribute 값을 설정한다.
     *
     * @param pageUnit
     * the pageUnit to set
     */
    /** 페이지갯수  */
    @JvmField
    var pageUnit: Int = 10

    /**
     * pageSize attribute를 리턴한다.
     *
     * @return the pageSize
     */
    /**
     * pageSize attribute 값을 설정한다.
     *
     * @param pageSize
     * the pageSize to set
     */
    /** 페이지사이즈  */
    @JvmField
    var pageSize: Int = 10

    /**
     * firstIndex attribute를 리턴한다.
     *
     * @return the firstIndex
     */
    /**
     * firstIndex attribute 값을 설정한다.
     *
     * @param firstIndex
     * the firstIndex to set
     */
    /** 첫페이지 인덱스  */
    @JvmField
    var firstIndex: Int = 1

    /**
     * lastIndex attribute를 리턴한다.
     *
     * @return the lastIndex
     */
    /**
     * lastIndex attribute 값을 설정한다.
     *
     * @param lastIndex
     * the lastIndex to set
     */
    /** 마지막페이지 인덱스  */
    @JvmField
    var lastIndex: Int = 1

    /**
     * recordCountPerPage attribute를 리턴한다.
     *
     * @return the recordCountPerPage
     */
    /**
     * recordCountPerPage attribute 값을 설정한다.
     *
     * @param recordCountPerPage
     * the recordCountPerPage to set
     */
    /** 페이지당 레코드 개수  */
    @JvmField
    var recordCountPerPage: Int = 10

    /**
     * rowNo attribute를 리턴한다.
     *
     * @return the rowNo
     */
    /**
     * rowNo attribute 값을 설정한다.
     *
     * @param rowNo
     * the rowNo to set
     */
    /** 레코드 번호  */
    var rowNo: Int = 0

    /**
     * typeFlag attribute를 리턴한다.
     *
     * @return the typeFlag
     */
    /**
     * typeFlag attribute 값을 설정한다.
     *
     * @param typeFlag
     * the typeFlag to set
     */
    /** 구분 유형  */
    @JvmField
    var typeFlag: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
