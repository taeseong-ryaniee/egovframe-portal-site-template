package egovframework.let.uss.olp.qqm.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 설문문항 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QustnrQestnManageVO : Serializable {
    /**
     * qestnrSj attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrSj attribute 값을 설정한다.
     * @return qestnrSj String
     */
    /** 설문제목  */
    var qestnrSj: String? = ""

    /**
     * qestnrQesitmId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrQesitmId attribute 값을 설정한다.
     * @return qestnrQesitmId String
     */
    /** 설문문항 ID  */
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
    /** 설문지 ID  */
    @JvmField
    var qestnrId: String? = ""

    /**
     * qestnSn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnSn attribute 값을 설정한다.
     * @return qestnSn String
     */
    /** 질문순번  */
    var qestnSn: String? = ""

    /**
     * qestnTyCode attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnTyCode attribute 값을 설정한다.
     * @return qestnTyCode String
     */
    /** 질문유형코드  */
    var qestnTyCode: String? = ""

    /**
     * qestnCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnCn attribute 값을 설정한다.
     * @return qestnCn String
     */
    /** 질문내용  */
    var qestnCn: String? = ""

    /**
     * mxmmChoiseCo attribute 를 리턴한다.
     * @return the String
     */
    /**
     * mxmmChoiseCo attribute 값을 설정한다.
     * @return mxmmChoiseCo String
     */
    /** 초대선택건수  */
    var mxmmChoiseCo: String? = ""

    /**
     * qestnrTmplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatId attribute 값을 설정한다.
     * @return qestnrTmplatId String
     */
    /** 템플릿 ID  */
    @JvmField
    var qestnrTmplatId: String? = ""

    /**
     * frstRegisterPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @return frstRegisterPnttm String
     */
    /** 최초등록자아이디  */
    var frstRegisterPnttm: String? = ""

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @return frstRegisterId String
     */
    /** 최초등록시점   */
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
    /** 최종수정시점아이디   */
    @JvmField
    var lastUpdusrId: String? = ""

    /**
     * searchMode attribute 를 리턴한다.
     * @return the String
     */
    /**
     * searchMode attribute 값을 설정한다.
     * @return searchMode String
     */
    /** 검색모드설정   */
    @JvmField
    var searchMode: String? = ""

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
