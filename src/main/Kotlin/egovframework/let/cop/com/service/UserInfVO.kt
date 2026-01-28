package egovframework.let.cop.com.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 사용자 정보 조회를 위한 VO  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class UserInfVO : Serializable {
    /**
     * uniqId attribute를 리턴한다.
     *
     * @return the uniqId
     */
    /**
     * uniqId attribute 값을 설정한다.
     *
     * @param uniqId
     * the uniqId to set
     */
    /** 유일 아이디  */
    @JvmField
    var uniqId: String? = ""

    /**
     * userId attribute를 리턴한다.
     *
     * @return the userId
     */
    /**
     * userId attribute 값을 설정한다.
     *
     * @param userId
     * the userId to set
     */
    /** 사용자 아이디  */
    var userId: String? = ""

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
     * userZip attribute를 리턴한다.
     *
     * @return the userZip
     */
    /**
     * userZip attribute 값을 설정한다.
     *
     * @param userZip
     * the userZip to set
     */
    /** 사용자 우편번호  */
    var userZip: String? = ""

    /**
     * userAdres attribute를 리턴한다.
     *
     * @return the userAdres
     */
    /**
     * userAdres attribute 값을 설정한다.
     *
     * @param userAdres
     * the userAdres to set
     */
    /** 사용자 주소  */
    var userAdres: String? = ""

    /**
     * userEmail attribute를 리턴한다.
     *
     * @return the userEmail
     */
    /**
     * userEmail attribute 값을 설정한다.
     *
     * @param userEmail
     * the userEmail to set
     */
    /** 사용자 이메일  */
    var userEmail: String? = ""

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
     * trgetId attribute를 리턴한다.
     *
     * @return the trgetId
     */
    /**
     * trgetId attribute 값을 설정한다.
     *
     * @param trgetId
     * the trgetId to set
     */
    /** 대상 아이디  */
    @JvmField
    var trgetId: String? = ""

    /**
     * useAt attribute를 리턴한다.
     *
     * @return the useAt
     */
    /**
     * useAt attribute 값을 설정한다.
     *
     * @param useAt
     * the useAt to set
     */
    /** 사용여부  */
    var useAt: String? = "Y"

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
     * clubId attribute를 리턴한다.
     *
     * @return the clubId
     */
    /**
     * clubId attribute 값을 설정한다.
     *
     * @param clubId
     * the clubId to set
     */
    /** 동호회 아이디  */
    var clubId: String? = ""

    /**
     * deletedAt attribute를 리턴한다.
     *
     * @return the deletedAt
     */
    /**
     * deletedAt attribute 값을 설정한다.
     *
     * @param deletedAt
     * the deletedAt to set
     */
    /** 대상 중지 여부 (커뮤니티 또는 동호회)  */
    var deletedAt: String? = "N"

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
