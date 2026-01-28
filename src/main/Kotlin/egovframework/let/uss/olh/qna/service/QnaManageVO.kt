package egovframework.let.uss.olh.qna.service

/**
 *
 * Q&A를 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QnaManageVO : QnaManageDefaultVO() {
    /**
     * qaId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qaId attribute 값을 설정한다.
     * @return qaId String
     */
    /** QA ID  */
    var qaId: String? = null

    /**
     * qestnSj attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnSj attribute 값을 설정한다.
     * @return qestnSj String
     */
    /** 질문제목  */
    var qestnSj: String? = null

    /**
     * qestnCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnCn attribute 값을 설정한다.
     * @return qestnCn String
     */
    /** 질문내용  */
    var qestnCn: String? = null

    /**
     * writngPassword attribute 를 리턴한다.
     * @return the String
     */
    /**
     * writngPassword attribute 값을 설정한다.
     * @return writngPassword String
     */
    /** 작성비밀번호  */
    @JvmField
    var writngPassword: String? = null

    /**
     * areaNo attribute 를 리턴한다.
     * @return the String
     */
    /**
     * areaNo attribute 값을 설정한다.
     * @return areaNo String
     */
    /** 지역번호  */
    var areaNo: String? = null

    /**
     * middleTelno attribute 를 리턴한다.
     * @return the String
     */
    /**
     * middleTelno attribute 값을 설정한다.
     * @return middleTelno String
     */
    /** 중간전화번호  */
    var middleTelno: String? = null

    /**
     * endTelno attribute 를 리턴한다.
     * @return the String
     */
    /**
     * endTelno attribute 값을 설정한다.
     * @return endTelno String
     */
    /** 끝전화번호  */
    var endTelno: String? = null

    /**
     * emailAdres attribute 를 리턴한다.
     * @return the String
     */
    /**
     * emailAdres attribute 값을 설정한다.
     * @return emailAdres String
     */
    /** 이메일 주소  */
    @JvmField
    var emailAdres: String? = null

    /**
     * emailAnswerAt attribute 를 리턴한다.
     * @return the String
     */
    /**
     * emailAnswerAt attribute 값을 설정한다.
     * @return emailAnswerAt String
     */
    /** 이메일 답변여부  */
    var emailAnswerAt: String? = null

    /**
     * wrterNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * wrterNm attribute 값을 설정한다.
     * @return wrterNm String
     */
    /** 작성자 명  */
    @JvmField
    var wrterNm: String? = null

    /**
     * writngDe attribute 를 리턴한다.
     * @return the String
     */
    /**
     * writngDe attribute 값을 설정한다.
     * @return writngDe String
     */
    /** 작성일자  */
    var writngDe: String? = null

    /**
     * inqireCo attribute 를 리턴한다.
     * @return the String
     */
    /**
     * inqireCo attribute 값을 설정한다.
     * @return inqireCo String
     */
    /** 조회횟수  */
    var inqireCo: String? = null

    /**
     * qnaProcessSttusCode attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qnaProcessSttusCode attribute 값을 설정한다.
     * @return qnaProcessSttusCode String
     */
    /** 질의응답처리상태코드  */
    var qnaProcessSttusCode: String? = null

    /**
     * qnaProcessSttusCodeNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qnaProcessSttusCodeNm attribute 값을 설정한다.
     * @return qnaProcessSttusCodeNm String
     */
    /** 질의응답처리상태코드명  */
    var qnaProcessSttusCodeNm: String? = null

    /**
     * answerCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * answerCn attribute 값을 설정한다.
     * @return answerCn String
     */
    /** 답변내용  */
    var answerCn: String? = null

    /**
     * answerDe attribute 를 리턴한다.
     * @return the String
     */
    /**
     * answerDe attribute 값을 설정한다.
     * @return answerDe String
     */
    /** 답변일자  */
    var answerDe: String? = null

    /**
     * passwordConfirmAt attribute 를 리턴한다.
     * @return the String
     */
    /**
     * passwordConfirmAt attribute 값을 설정한다.
     * @return passwordConfirmAt String
     */
    /** 작성비밀번호 확인여부  */
    @JvmField
    var passwordConfirmAt: String? = null

    /**
     * emplyrNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * emplyrNm attribute 값을 설정한다.
     * @return emplyrNm String
     */
    /** 답변자명  */
    var emplyrNm: String? = null

    /**
     * offmTelno attribute 를 리턴한다.
     * @return the String
     */
    /**
     * offmTelno attribute 값을 설정한다.
     * @return offmTelno String
     */
    /** 사무실전화번호  */
    var offmTelno: String? = null

    /**
     * aemailAdres attribute 를 리턴한다.
     * @return the String
     */
    /**
     * aemailAdres attribute 값을 설정한다.
     * @return aemailAdres String
     */
    /** 답변자 EMAIL 주소  */
    var aemailAdres: String? = null

    /**
     * orgnztNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * orgnztNm attribute 값을 설정한다.
     * @return orgnztNm String
     */
    /** 부서명  */
    var orgnztNm: String? = null

    /**
     * frstRegisterPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @return frstRegisterPnttm String
     */
    /** 최초등록시점  */
    var frstRegisterPnttm: String? = null

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @return frstRegisterId String
     */
    /** 최초등록자ID  */
    @JvmField
    var frstRegisterId: String? = null

    /**
     * lastUpdusrPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * @return lastUpdusrPnttm String
     */
    /** 최종수정시점  */
    var lastUpdusrPnttm: String? = null

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @return lastUpdusrId String
     */
    /** 최종수정자ID  */
    @JvmField
    var lastUpdusrId: String? = null


    companion object {
        private const val serialVersionUID = 1L
    }
}
