package egovframework.let.uss.sam.stp.service


/**
 *
 * 약관내용을 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class StplatManageVO : StplatManageDefaultVO() {
    /**
     * useStplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * useStplatId attribute 값을 설정한다.
     * @return useStplatId String
     */
    /** 이용약관 ID  */
    var useStplatId: String? = null

    /**
     * useStplatNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * useStplatNm attribute 값을 설정한다.
     * @return useStplatNm String
     */
    /** 이용약관명  */
    var useStplatNm: String? = null

    /**
     * useStplatCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * useStplatCn attribute 값을 설정한다.
     * @return useStplatCn String
     */
    /** 이용약관내용  */
    var useStplatCn: String? = null

    /**
     * infoProvdAgreCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * infoProvdAgreCn attribute 값을 설정한다.
     * @return infoProvdAgreCn String
     */
    /** 정보제공동의내용  */
    var infoProvdAgreCn: String? = null

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
    var lastUpdusrId: String? = null


    companion object {
        private const val serialVersionUID = 1L
    }
}
