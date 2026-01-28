package egovframework.let.uss.olp.qri.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 설문조사 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QustnrRespondInfoVO : Serializable {
    /**
     * qestnrQesrspnsId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrQesrspnsId attribute 값을 설정한다.
     * @return qestnrQesrspnsId String
     */
    /** 설문응답ID  */
    var qestnrQesrspnsId: String? = ""

    /**
     * qestnrQesitmId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrQesitmId attribute 값을 설정한다.
     * @return qestnrQesitmId String
     */
    /** 설문문항ID  */
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
    /** 설문ID  */
    @JvmField
    var qestnrId: String? = ""

    /**
     * qestnrTmplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatId attribute 값을 설정한다.
     * @return qestnrTmplatId String
     */
    /** 설문템플릿ID  */
    @JvmField
    var qestnrTmplatId: String? = ""

    /**
     * qustnrIemId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qustnrIemId attribute 값을 설정한다.
     * @return qustnrIemId String
     */
    /** 설문항목ID  */
    @JvmField
    var qustnrIemId: String? = ""

    /**
     * respondAnswerCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * respondAnswerCn attribute 값을 설정한다.
     * @return respondAnswerCn String
     */
    /** 응답자답변내용  */
    @JvmField
    var respondAnswerCn: String? = ""

    /**
     * respondNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * respondNm attribute 값을 설정한다.
     * @return respondNm String
     */
    /** 응답자명  */
    @JvmField
    var respondNm: String? = ""

    /**
     * etcAnswerCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * etcAnswerCn attribute 값을 설정한다.
     * @return etcAnswerCn String
     */
    /** 기타답변내용  */
    @JvmField
    var etcAnswerCn: String? = ""

    /**
     * frstRegisterPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @return frstRegisterPnttm String
     */
    /** 최초등록시점  */
    var frstRegisterPnttm: String? = ""

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @return frstRegisterId String
     */
    /** 최등등록시점ID  */
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
    /** 최종수정시점  */
    var lastUpdusrPnttm: String? = ""

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @return lastUpdusrId String
     */
    /** 최종수정시점ID  */
    @JvmField
    var lastUpdusrId: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
