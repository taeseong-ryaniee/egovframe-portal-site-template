package egovframework.let.uss.olh.faq.service

/**
 *
 * FAQ를 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class FaqManageVO : FaqManageDefaultVO() {
    /**
     * faqId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * faqId attribute 값을 설정한다.
     * @return faqId String
     */
    /** FAQ ID  */
    @JvmField
    var faqId: String? = null

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
     * atchFileId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * atchFileId attribute 값을 설정한다.
     * @return atchFileId String
     */
    /** 첨부파일ID  */
    @JvmField
    var atchFileId: String? = null

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
