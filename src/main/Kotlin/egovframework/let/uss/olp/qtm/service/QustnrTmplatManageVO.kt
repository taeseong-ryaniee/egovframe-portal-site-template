package egovframework.let.uss.olp.qtm.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 설문템플릿 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QustnrTmplatManageVO : Serializable {
    /**
     * qestnrTmplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatId attribute 값을 설정한다.
     * @return qestnrTmplatId String
     */
    /** 설문템플릿 아이디  */
    var qestnrTmplatId: String? = ""

    /**
     * qestnrTmplatTy attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatTy attribute 값을 설정한다.
     * @return qestnrTmplatTy String
     */
    /** 설문템플릿 유형  */
    var qestnrTmplatTy: String? = ""

    /** 설문템플 이미지경로  */
    var qestnrTmplatImagepathnm: ByteArray?

    /**
     * qestnrTmplatCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatCn attribute 값을 설정한다.
     * @return qestnrTmplatCn String
     */
    /** 설문템플릿  설명  */
    var qestnrTmplatCn: String? = ""

    /**
     * qestnrTmplatCours attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatCours attribute 값을 설정한다.
     * @return qestnrTmplatCours String
     */
    /** 서물템플릿경로명  */
    var qestnrTmplatCours: String? = null

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
    /** 최초등록자아이디  */
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
    /** 최종수정자 시점  */
    var lastUpdusrPnttm: String? = ""

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @return lastUpdusrId String
     */
    /** 최종수정자아이디  */
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
    /** 화면 명령 처리  */
    var cmd: String? = ""

    /**
     * qestnrTmplatImagepathnm attribute 값을 설정한다.
     * @return qestnrTmplatImagepathnm byte[]
     */
    fun setQestnrTmplatImagepathnm(qestnrTmplatImagepathnm: ByteArray?) {
        this.qestnrTmplatImagepathnm = qestnrTmplatImagepathnm
    }

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
