package egovframework.let.cop.bbs.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 게시물 관리를 위한 VO 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.19
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class BoardVO : Board(), Serializable {
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
    @JvmField
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
    @JvmField
    var searchWrd: String? = ""

    /** 정렬순서(DESC,ASC)  */
    private var sortOrdr = 0L

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
    @JvmField
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
     * isExpired attribute를 리턴한다.
     *
     * @return the isExpired
     */
    /**
     * isExpired attribute 값을 설정한다.
     *
     * @param isExpired
     * the isExpired to set
     */
    /** 유효여부  */
    @JvmField
    var isExpired: String? = "N"

    /**
     * parntsSortOrdr attribute를 리턴한다.
     *
     * @return the parntsSortOrdr
     */
    /**
     * parntsSortOrdr attribute 값을 설정한다.
     *
     * @param parntsSortOrdr
     * the parntsSortOrdr to set
     */
    /** 상위 정렬 순서  */
    var parntsSortOrdr: String? = ""

    /**
     * parntsReplyLc attribute를 리턴한다.
     *
     * @return the parntsReplyLc
     */
    /**
     * parntsReplyLc attribute 값을 설정한다.
     *
     * @param parntsReplyLc
     * the parntsReplyLc to set
     */
    /** 상위 답변 위치  */
    var parntsReplyLc: String? = ""

    /**
     * bbsTyCode attribute를 리턴한다.
     *
     * @return the bbsTyCode
     */
    /**
     * bbsTyCode attribute 값을 설정한다.
     *
     * @param bbsTyCode
     * the bbsTyCode to set
     */
    /** 게시판 유형코드  */
    var bbsTyCode: String? = ""

    /**
     * bbsAttrbCode attribute를 리턴한다.
     *
     * @return the bbsAttrbCode
     */
    /**
     * bbsAttrbCode attribute 값을 설정한다.
     *
     * @param bbsAttrbCode
     * the bbsAttrbCode to set
     */
    /** 게시판 속성코드  */
    var bbsAttrbCode: String? = ""

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
    @JvmField
    var bbsNm: String? = ""

    /**
     * fileAtchPosblAt attribute를 리턴한다.
     *
     * @return the fileAtchPosblAt
     */
    /**
     * fileAtchPosblAt attribute 값을 설정한다.
     *
     * @param fileAtchPosblAt
     * the fileAtchPosblAt to set
     */
    /** 파일첨부가능여부  */
    var fileAtchPosblAt: String? = ""

    /**
     * posblAtchFileNumber attribute를 리턴한다.
     *
     * @return the posblAtchFileNumber
     */
    /**
     * posblAtchFileNumber attribute 값을 설정한다.
     *
     * @param posblAtchFileNumber
     * the posblAtchFileNumber to set
     */
    /** 첨부가능파일숫자  */
    var posblAtchFileNumber: Int = 0

    /**
     * replyPosblAt attribute를 리턴한다.
     *
     * @return the replyPosblAt
     */
    /**
     * replyPosblAt attribute 값을 설정한다.
     *
     * @param replyPosblAt
     * the replyPosblAt to set
     */
    /** 답장가능여부  */
    var replyPosblAt: String? = ""

    /**
     * plusCount attribute를 리턴한다.
     * @return the plusCount
     */
    /**
     * plusCount attribute 값을 설정한다.
     * @param this.isPlusCount the plusCount to set
     */
    /** 조회 수 증가 여부  */
    var isPlusCount: Boolean = false

    //---------------------------------
    // 2009.06.29 : 2단계 기능 추가
    //---------------------------------
    /**
     * subPageIndex attribute를 리턴한다.
     * @return the subPageIndex
     */
    /**
     * subPageIndex attribute 값을 설정한다.
     * @param subPageIndex the subPageIndex to set
     */
    /** 하위 페이지 인덱스 (댓글 및 만족도 조사 여부 확인용)  */
    @JvmField
    var subPageIndex: String? = ""

    /**/------------------------------- */

    /**
     * sortOrdr attribute를 리턴한다.
     *
     * @return the sortOrdr
     */
    override fun getSortOrdr(): Long {
        return sortOrdr
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     *
     * @param sortOrdr
     * the sortOrdr to set
     */
    override fun setSortOrdr(sortOrdr: Long) {
        this.sortOrdr = sortOrdr
    }

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}