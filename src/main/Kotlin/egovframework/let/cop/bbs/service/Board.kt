package egovframework.let.cop.bbs.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 게시물에 대한 데이터 처리 모델 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.06
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class Board : Serializable {
    /**
     * atchFileId attribute를 리턴한다.
     * @return the atchFileId
     */
    /**
     * atchFileId attribute 값을 설정한다.
     * @param atchFileId the atchFileId to set
     */
    /**
     * 게시물 첨부파일 아이디
     */
    @JvmField
    var atchFileId: String? = ""
    /**
     * bbsId attribute를 리턴한다.
     * @return the bbsId
     */
    /**
     * bbsId attribute 값을 설정한다.
     * @param bbsId the bbsId to set
     */
    /**
     * 게시판 아이디
     */
    @JvmField
    var bbsId: String? = ""
    /**
     * frstRegisterId attribute를 리턴한다.
     * @return the frstRegisterId
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId the frstRegisterId to set
     */
    /**
     * 최초등록자 아이디
     */
    @JvmField
    var frstRegisterId: String? = ""
    /**
     * frstRegisterPnttm attribute를 리턴한다.
     * @return the frstRegisterPnttm
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @param frstRegisterPnttm the frstRegisterPnttm to set
     */
    /**
     * 최초등록시점
     */
    @JvmField
    var frstRegisterPnttm: String? = ""
    /**
     * lastUpdusrId attribute를 리턴한다.
     * @return the lastUpdusrId
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @param lastUpdusrId the lastUpdusrId to set
     */
    /**
     * 최종수정자 아이디
     */
    @JvmField
    var lastUpdusrId: String? = ""
    /**
     * lastUpdusrPnttm attribute를 리턴한다.
     * @return the lastUpdusrPnttm
     */
    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * @param lastUpdusrPnttm the lastUpdusrPnttm to set
     */
    /**
     * 최종수정시점
     */
    var lastUpdusrPnttm: String? = ""
    /**
     * ntceBgnde attribute를 리턴한다.
     * @return the ntceBgnde
     */
    /**
     * ntceBgnde attribute 값을 설정한다.
     * @param ntceBgnde the ntceBgnde to set
     */
    /**
     * 게시시작일
     */
    @JvmField
    var ntceBgnde: String? = ""
    /**
     * ntceEndde attribute를 리턴한다.
     * @return the ntceEndde
     */
    /**
     * ntceEndde attribute 값을 설정한다.
     * @param ntceEndde the ntceEndde to set
     */
    /**
     * 게시종료일
     */
    @JvmField
    var ntceEndde: String? = ""
    /**
     * ntcrId attribute를 리턴한다.
     * @return the ntcrId
     */
    /**
     * ntcrId attribute 값을 설정한다.
     * @param ntcrId the ntcrId to set
     */
    /**
     * 게시자 아이디
     */
    @JvmField
    var ntcrId: String? = ""
    /**
     * ntcrNm attribute를 리턴한다.
     * @return the ntcrNm
     */
    /**
     * ntcrNm attribute 값을 설정한다.
     * @param ntcrNm the ntcrNm to set
     */
    /**
     * 게시자명
     */
    @JvmField
    var ntcrNm: String? = ""
    /**
     * nttCn attribute를 리턴한다.
     * @return the nttCn
     */
    /**
     * nttCn attribute 값을 설정한다.
     * @param nttCn the nttCn to set
     */
    /**
     * 게시물 내용
     */
    @JvmField
    var nttCn: String? = ""
    /**
     * nttId attribute를 리턴한다.
     * @return the nttId
     */
    /**
     * nttId attribute 값을 설정한다.
     * @param nttId the nttId to set
     */
    /**
     * 게시물 아이디
     */
    @JvmField
    var nttId: Long = 0L
    /**
     * nttNo attribute를 리턴한다.
     * @return the nttNo
     */
    /**
     * nttNo attribute 값을 설정한다.
     * @param nttNo the nttNo to set
     */
    /**
     * 게시물 번호
     */
    @JvmField
    var nttNo: Long = 0L
    /**
     * nttSj attribute를 리턴한다.
     * @return the nttSj
     */
    /**
     * nttSj attribute 값을 설정한다.
     * @param nttSj the nttSj to set
     */
    /**
     * 게시물 제목
     */
    @JvmField
    var nttSj: String? = ""
    /**
     * parnts attribute를 리턴한다.
     * @return the parnts
     */
    /**
     * parnts attribute 값을 설정한다.
     * @param parnts the parnts to set
     */
    /**
     * 부모글번호
     */
    @JvmField
    var parnts: String? = "0"
    /**
     * password attribute를 리턴한다.
     * @return the password
     */
    /**
     * password attribute 값을 설정한다.
     * @param password the password to set
     */
    /**
     * 패스워드
     */
    @JvmField
    var password: String? = ""
    /**
     * inqireCo attribute를 리턴한다.
     * @return the inqireCo
     */
    /**
     * inqireCo attribute 값을 설정한다.
     * @param inqireCo the inqireCo to set
     */
    /**
     * 조회수
     */
    @JvmField
    var inqireCo: Int = 0
    /**
     * replyAt attribute를 리턴한다.
     * @return the replyAt
     */
    /**
     * replyAt attribute 값을 설정한다.
     * @param replyAt the replyAt to set
     */
    /**
     * 답장여부
     */
    @JvmField
    var replyAt: String? = ""
    /**
     * replyLc attribute를 리턴한다.
     * @return the replyLc
     */
    /**
     * replyLc attribute 값을 설정한다.
     * @param replyLc the replyLc to set
     */
    /**
     * 답장위치
     */
    @JvmField
    var replyLc: String? = "0"
    /**
     * sortOrdr attribute를 리턴한다.
     * @return the sortOrdr
     */
    /**
     * sortOrdr attribute 값을 설정한다.
     * @param sortOrdr the sortOrdr to set
     */
    /**
     * 정렬순서
     */
    open var sortOrdr: Long = 0L
    /**
     * useAt attribute를 리턴한다.
     * @return the useAt
     */
    /**
     * useAt attribute 값을 설정한다.
     * @param useAt the useAt to set
     */
    /**
     * 사용여부
     */
    @JvmField
    var useAt: String? = ""
    /**
     * ntceEnddeView attribute를 리턴한다.
     * @return the ntceEnddeView
     */
    /**
     * ntceEnddeView attribute 값을 설정한다.
     * @param ntceEnddeView the ntceEnddeView to set
     */
    /**
     * 게시 종료일
     */
    var ntceEnddeView: String? = ""
    /**
     * ntceBgndeView attribute를 리턴한다.
     * @return the ntceBgndeView
     */
    /**
     * ntceBgndeView attribute 값을 설정한다.
     * @param ntceBgndeView the ntceBgndeView to set
     */
    /**
     * 게시 시작일
     */
    var ntceBgndeView: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
