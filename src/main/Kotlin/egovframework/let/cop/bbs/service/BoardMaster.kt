package egovframework.let.cop.bbs.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 게시판 속성정보를 담기위한 엔티티 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class BoardMaster : Serializable {
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
    @JvmField
    var bbsAttrbCode: String? = ""

    /**
     * bbsId attribute를 리턴한다.
     *
     * @return the bbsId
     */
    /**
     * bbsId attribute 값을 설정한다.
     *
     * @param bbsId
     * the bbsId to set
     */
    /** 게시판 아이디  */
    @JvmField
    var bbsId: String? = ""

    /**
     * bbsIntrcn attribute를 리턴한다.
     *
     * @return the bbsIntrcn
     */
    /**
     * bbsIntrcn attribute 값을 설정한다.
     *
     * @param bbsIntrcn
     * the bbsIntrcn to set
     */
    /** 게시판 소개  */
    @JvmField
    var bbsIntrcn: String? = ""

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
    @JvmField
    var bbsTyCode: String? = ""

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
    @JvmField
    var fileAtchPosblAt: String? = ""

    /**
     * frstRegisterId attribute를 리턴한다.
     *
     * @return the frstRegisterId
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     *
     * @param frstRegisterId
     * the frstRegisterId to set
     */
    /** 최초등록자 아이디  */
    @JvmField
    var frstRegisterId: String? = ""

    /**
     * frstRegisterPnttm attribute를 리턴한다.
     *
     * @return the frstRegisterPnttm
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     *
     * @param frstRegisterPnttm
     * the frstRegisterPnttm to set
     */
    /** 최초등록시점  */
    var frstRegisterPnttm: String? = ""

    /**
     * lastUpdusrId attribute를 리턴한다.
     *
     * @return the lastUpdusrId
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     *
     * @param lastUpdusrId
     * the lastUpdusrId to set
     */
    /** 최종수정자 아이디  */
    @JvmField
    var lastUpdusrId: String? = ""

    /**
     * lastUpdusrPnttm attribute를 리턴한다.
     *
     * @return the lastUpdusrPnttm
     */
    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     *
     * @param lastUpdusrPnttm
     * the lastUpdusrPnttm to set
     */
    /** 최종수정시점  */
    var lastUpdusrPnttm: String? = ""

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
    @JvmField
    var posblAtchFileNumber: Int = 0

    /**
     * posblAtchFileSize attribute를 리턴한다.
     *
     * @return the posblAtchFileSize
     */
    /**
     * posblAtchFileSize attribute 값을 설정한다.
     *
     * @param posblAtchFileSize
     * the posblAtchFileSize to set
     */
    /** 첨부가능파일사이즈  */
    @JvmField
    var posblAtchFileSize: String? = ""

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
    @JvmField
    var replyPosblAt: String? = ""

    /**
     * tmplatId attribute를 리턴한다.
     *
     * @return the tmplatId
     */
    /**
     * tmplatId attribute 값을 설정한다.
     *
     * @param tmplatId
     * the tmplatId to set
     */
    /** 템플릿 아이디  */
    @JvmField
    var tmplatId: String? = ""

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
    @JvmField
    var useAt: String? = ""

    /**
     * bbsUseFlag attribute를 리턴한다.
     *
     * @return the bbsUseFlag
     */
    /**
     * bbsUseFlag attribute 값을 설정한다.
     *
     * @param bbsUseFlag
     * the bbsUseFlag to set
     */
    /** 사용플래그  */
    @JvmField
    var bbsUseFlag: String? = ""

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
     * registSeCode attribute를 리턴한다.
     *
     * @return the registSeCode
     */
    /**
     * registSeCode attribute 값을 설정한다.
     *
     * @param registSeCode
     * the registSeCode to set
     */
    /** 등록구분코드  */
    @JvmField
    var registSeCode: String? = ""

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
     * tmplatNm attribute를 리턴한다.
     *
     * @return the tmplatNm
     */
    /**
     * tmplatNm attribute 값을 설정한다.
     *
     * @param tmplatNm
     * the tmplatNm to set
     */
    /** 템플릿 명  */
    open var tmplatNm: String? = ""

    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    /**
     * option attribute를 리턴한다.
     * @return the option
     */
    /**
     * option attribute 값을 설정한다.
     * @param option the option to set
     */
    /** 추가 option (댓글-comment, 만족도조사-stsfdg)  */
    @JvmField
    var option: String? = ""

    /**
     * commentAt attribute를 리턴한다.
     * @return the commentAt
     */
    /**
     * commentAt attribute 값을 설정한다.
     * @param commentAt the commentAt to set
     */
    /** 댓글 여부  */
    @JvmField
    var commentAt: String? = ""

    /**
     * stsfdgAt attribute를 리턴한다.
     * @return the stsfdgAt
     */
    /**
     * stsfdg attribute 값을 설정한다.
     * @param stsfdgAt the stsfdgAt to set
     */
    /** 만족도조사  */
    @JvmField
    var stsfdgAt: String? = ""

    /**/------------------------------- */

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}