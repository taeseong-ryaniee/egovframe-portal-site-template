package egovframework.let.cop.com.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 템플릿 정보를 관리하기 위한 모델 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.03.17
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class TemplateInf : Serializable {
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
     * tmplatCours attribute를 리턴한다.
     *
     * @return the tmplatCours
     */
    /**
     * tmplatCours attribute 값을 설정한다.
     *
     * @param tmplatCours
     * the tmplatCours to set
     */
    /** 템플릿 경로  */
    var tmplatCours: String? = ""

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
    var tmplatNm: String? = ""

    /**
     * tmplatSeCode attribute를 리턴한다.
     *
     * @return the tmplatSeCode
     */
    /**
     * tmplatSeCode attribute 값을 설정한다.
     *
     * @param tmplatSeCode
     * the tmplatSeCode to set
     */
    /** 탬플릿 구분코드  */
    @JvmField
    var tmplatSeCode: String? = ""

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
    var useAt: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
