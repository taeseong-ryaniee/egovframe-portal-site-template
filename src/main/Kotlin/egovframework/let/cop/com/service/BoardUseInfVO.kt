package egovframework.let.cop.com.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 게시판의 이용정보를 관리하기 위한 VO 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class BoardUseInfVO : BoardUseInf(), Serializable {
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
    var sortOrdr: Long = 0L

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
     * registSeCodeNm attribute를 리턴한다.
     *
     * @return the registSeCodeNm
     */
    /**
     * registSeCodeNm attribute 값을 설정한다.
     *
     * @param registSeCodeNm
     * the registSeCodeNm to set
     */
    /** 등록구분 코드명  */
    var registSeCodeNm: String? = ""

    /**
     * cmmntyId attribute를 리턴한다.
     *
     * @return the cmmntyId
     */
    /**
     * cmmntyId attribute 값을 설정한다.
     *
     * @param cmmntyId
     * the cmmntyId to set
     */
    /** 커뮤니티 아이디  */
    var cmmntyId: String? = ""

    /**
     * cmmntyNm attribute를 리턴한다.
     *
     * @return the cmmntyNm
     */
    /**
     * cmmntyNm attribute 값을 설정한다.
     *
     * @param cmmntyNm
     * the cmmntyNm to set
     */
    /** 커뮤니티 명  */
    var cmmntyNm: String? = ""

    /**
     * clbId attribute를 리턴한다.
     *
     * @return the clbId
     */
    /**
     * clbId attribute 값을 설정한다.
     *
     * @param clbId
     * the clbId to set
     */
    /** 동호회 아이디  */
    var clbId: String? = ""

    /**
     * clbNm attribute를 리턴한다.
     *
     * @return the clbNm
     */
    /**
     * clbNm attribute 값을 설정한다.
     *
     * @param clbNm
     * the clbNm to set
     */
    /** 동호회 명  */
    var clbNm: String? = ""

    /**
     * bbsNm attribute를 리턴한다.
     *
     * @return the bbsNm
     */
    /**
     * bbsNm attribute 값을 설정한다.
     *
     * @param bbsNm
     * the bbsNm to set
     */
    /** 게시판 명  */
    var bbsNm: String? = ""

    /**
     * userNm attribute를 리턴한다.
     *
     * @return the userNm
     */
    /**
     * userNm attribute 값을 설정한다.
     *
     * @param userNm
     * the userNm to set
     */
    /** 사용자 명  */
    var userNm: String? = ""

    /**
     * provdUrl attribute를 리턴한다.
     *
     * @return the provdUrl
     */
    /**
     * provdUrl attribute 값을 설정한다.
     *
     * @param provdUrl
     * the provdUrl to set
     */
    /** 제공 URL  */
    @JvmField
    var provdUrl: String? = ""

    /**
     * bbsTyCode attribute를 리턴한다.
     * @return the bbsTyCode
     */
    /**
     * bbsTyCode attribute 값을 설정한다.
     * @param bbsTyCode the bbsTyCode to set
     */
    /** 게시판 유형코드  */
    @JvmField
    var bbsTyCode: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
