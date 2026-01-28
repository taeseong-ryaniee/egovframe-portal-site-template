package egovframework.let.cop.com.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 게시판의 이용정보를 관리하기 위한 모델 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.04.02
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class BoardUseInf : Serializable {
    /**
     * bbsId attribute를 리턴한다.
     *
     * @return the bbsId
     */
    /**
     * bbsId attribute 값을 설정한다.
     *
     * @param bbsId
     * the bbsId to set
     */
    /** 게시판 아이디  */
    @JvmField
    var bbsId: String? = ""

    /**
     * trgetId attribute를 리턴한다.
     *
     * @return the trgetId
     */
    /**
     * trgetId attribute 값을 설정한다.
     *
     * @param trgetId
     * the trgetId to set
     */
    /** 대상시스템 아이디  */
    @JvmField
    var trgetId: String? = ""

    /**
     * trgetType attribute를 리턴한다.
     * @return the trgetType
     */
    /**
     * trgetType attribute 값을 설정한다.
     * @param trgetType the trgetType to set
     */
    /** 대상 구분 (커뮤니티, 동호회)  */
    @JvmField
    var trgetType: String? = ""

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
    /** 최초 등록자 아이디  */
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
     * registSeCode attribute를 리턴한다.
     *
     * @return the registSeCode
     */
    /**
     * registSeCode attribute 값을 설정한다.
     *
     * @param registSeCode
     * the registSeCode to set
     */
    /** 등록구분코드  */
    @JvmField
    var registSeCode: String? = ""

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
    @JvmField
    var useAt: String? = ""

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
