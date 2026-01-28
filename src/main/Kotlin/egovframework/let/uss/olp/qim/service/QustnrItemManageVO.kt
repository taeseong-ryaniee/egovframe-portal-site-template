package egovframework.let.uss.olp.qim.service

import java.io.Serializable

/**
 * 설문항목관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QustnrItemManageVO : Serializable {
    /**
     * qestnrQesitmId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrQesitmId attribute 값을 설정한다.
     * @return qestnrQesitmId String
     */
    /** 설문문항 아이디  */
    @JvmField
    var qestnrQesitmId: String? = ""

    /**
     * qestnrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrId attribute 값을 설정한다.
     * @return qestnrId String
     */
    /** 설문지 아이디  */
    var qestnrId: String? = ""

    /**
     * iemSn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * iemSn attribute 값을 설정한다.
     * @return iemSn String
     */
    /** 항목순번  */
    var iemSn: String? = ""

    /**
     * qustnrIemId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qustnrIemId attribute 값을 설정한다.
     * @return qustnrIemId String
     */
    /** 항목내용  */
    var qustnrIemId: String? = ""

    /**
     * iemCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * iemCn attribute 값을 설정한다.
     * @return iemCn String
     */
    /** 설문항목아이디  */
    var iemCn: String? = ""

    /**
     * etcAnswerAt attribute 를 리턴한다.
     * @return the String
     */
    /**
     * etcAnswerAt attribute 값을 설정한다.
     * @return etcAnswerAt String
     */
    /** 키타답변여부  */
    var etcAnswerAt: String? = ""

    /**
     * qestnrTmplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatId attribute 값을 설정한다.
     * @return qestnrTmplatId String
     */
    /** 설문항목(을)를 아이디  */
    var qestnrTmplatId: String? = ""

    /**
     * frstRegisterPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @return frstRegisterPnttm String
     */
    /** 최초등록시점   */
    var frstRegisterPnttm: String? = ""

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @return frstRegisterId String
     */
    /** 최초등록아이디  */
    @JvmField
    var frstRegisterId: String? = ""

    /**
     * lastUpdusrPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * @return lastUpdusrPnttm String
     */
    /** 최종수정일  */
    var lastUpdusrPnttm: String? = ""

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @return lastUpdusrId String
     */
    /** 최종수정자 아이디  */
    @JvmField
    var lastUpdusrId: String? = ""

    /**
     * cmd attribute 를 리턴한다.
     * @return the String
     */
    /**
     * cmd attribute 값을 설정한다.
     * @return cmd String
     */
    /** 컨트롤 명령어  */
    var cmd: String? = ""


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
