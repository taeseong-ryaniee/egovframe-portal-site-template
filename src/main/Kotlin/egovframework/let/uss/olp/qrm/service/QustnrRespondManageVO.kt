package egovframework.let.uss.olp.qrm.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 설문응답자관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QustnrRespondManageVO : Serializable {
    /**
     * qestnrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrId attribute 값을 설정한다.
     * @return qestnrId String
     */
    /** 설문지ID  */
    @JvmField
    var qestnrId: String? = ""

    /**
     * qestnrRespondId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrRespondId attribute 값을 설정한다.
     * @return qestnrRespondId String
     */
    /** 설문응답자아이디  */
    var qestnrRespondId: String? = ""

    /**
     * sexdstnCode attribute 를 리턴한다.
     * @return the String
     */
    /**
     * sexdstnCode attribute 값을 설정한다.
     * @return sexdstnCode String
     */
    /** 설별코드  */
    var sexdstnCode: String? = ""

    /**
     * occpTyCode attribute 를 리턴한다.
     * @return the String
     */
    /**
     * occpTyCode attribute 값을 설정한다.
     * @return occpTyCode String
     */
    /** 직업유형코드  */
    var occpTyCode: String? = ""

    /**
     * respondNm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * respondNm attribute 값을 설정한다.
     * @return respondNm String
     */
    /** 응답자명  */
    var respondNm: String? = ""

    /**
     * brth attribute 를 리턴한다.
     * @return the String
     */
    /**
     * brth attribute 값을 설정한다.
     * @return brth String
     */
    /** 생년월일  */
    var brth: String? = ""

    /**
     * areaNo attribute 를 리턴한다.
     * @return the String
     */
    /**
     * areaNo attribute 값을 설정한다.
     * @return areaNo String
     */
    /** 첫번째전화번호  */
    var areaNo: String? = ""

    /**
     * middleTelno attribute 를 리턴한다.
     * @return the String
     */
    /**
     * middleTelno attribute 값을 설정한다.
     * @return middleTelno String
     */
    /** 두번째전화번호  */
    var middleTelno: String? = ""

    /**
     * endTelno attribute 를 리턴한다.
     * @return the String
     */
    /**
     * endTelno attribute 값을 설정한다.
     * @return endTelno String
     */
    /** 마지막전화번호  */
    var endTelno: String? = ""

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
    /** 최초등록자ID  */
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
    /** 최종수정ID  */
    @JvmField
    var lastUpdusrId: String? = ""

    /**
     * qestnrTmplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatId attribute 값을 설정한다.
     * @return qestnrTmplatId String
     */
    /** 설문템플릿ID  */
    var qestnrTmplatId: String? = ""

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
