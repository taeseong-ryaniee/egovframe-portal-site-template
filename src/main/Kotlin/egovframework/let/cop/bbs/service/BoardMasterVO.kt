package egovframework.let.cop.bbs.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 게시판 속성 정보를 관리하기 위한 VO  클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class BoardMasterVO : BoardMaster(), Serializable {
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
    /** firstIndex  */
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
    /** lastIndex  */
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
    /** recordCountPerPage  */
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
    /** rowNo  */
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
     * bbsTyCodeNm attribute를 리턴한다.
     *
     * @return the bbsTyCodeNm
     */
    /**
     * bbsTyCodeNm attribute 값을 설정한다.
     *
     * @param bbsTyCodeNm
     * the bbsTyCodeNm to set
     */
    /** 게시판유형 코드명  */
    var bbsTyCodeNm: String? = ""

    /**
     * bbsAttrbCodeNm attribute를 리턴한다.
     *
     * @return the bbsAttrbCodeNm
     */
    /**
     * bbsAttrbCodeNm attribute 값을 설정한다.
     *
     * @param bbsAttrbCodeNm
     * the bbsAttrbCodeNm to set
     */
    /** 게시판속성 코드명  */
    var bbsAttrbCodeNm: String? = ""

    /** 템플릿 명  */
    private var tmplatNm: String? = ""

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
     * authFlag attribute를 리턴한다.
     *
     * @return the authFlag
     */
    /**
     * authFlag attribute 값을 설정한다.
     *
     * @param authFlag
     * the authFlag to set
     */
    /** 권한지정 여부  */
    var authFlag: String? = ""

    /**
     * tmplatCours attribute를 리턴한다.
     *
     * @return the tmplatCours
     */
    /**
     * tmplatCours attribute 값을 설정한다.
     *
     * @param tmplatCours
     * the tmplatCours to set
     */
    /** 템플릿경로  */
    @JvmField
    var tmplatCours: String? = ""

    /**
     * tmplatNm attribute를 리턴한다.
     *
     * @return the tmplatNm
     */
    override fun getTmplatNm(): String? {
        return tmplatNm
    }

    /**
     * tmplatNm attribute 값을 설정한다.
     *
     * @param tmplatNm
     * the tmplatNm to set
     */
    override fun setTmplatNm(tmplatNm: String?) {
        this.tmplatNm = tmplatNm
    }

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
