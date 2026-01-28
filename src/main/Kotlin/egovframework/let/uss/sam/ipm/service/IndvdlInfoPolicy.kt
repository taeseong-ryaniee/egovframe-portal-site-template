package egovframework.let.uss.sam.ipm.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 개인정보보호정책 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력
</pre> */
class IndvdlInfoPolicy : Serializable {
    /**
     * indvdlInfoId 리턴
     *
     * @return the indvdlInfoId
     */
    /**
     * indvdlInfoId 설정
     *
     * @param indvdlInfoId the indvdlInfoId to set
     */
    /** 개인정보보호정책 아이디  */
    var indvdlInfoId: String? = null

    /**
     * indvdlInfoNm 리턴
     *
     * @return the indvdlInfoNm
     */
    /**
     * indvdlInfoNm 설정
     *
     * @param indvdlInfoNm the indvdlInfoNm to set
     */
    /** 개인정보보호정책 명  */
    var indvdlInfoNm: String? = null

    /**
     * indvdlInfoDc 리턴
     *
     * @return the indvdlInfoDc
     */
    /**
     * indvdlInfoDc 설정
     *
     * @param indvdlInfoDc the indvdlInfoDc to set
     */
    /** 개인정보보호정책 내용  */
    var indvdlInfoDc: String? = null

    /**
     * indvdlInfoYn 리턴
     *
     * @return the indvdlInfoYn
     */
    /**
     * indvdlInfoYn 설정
     *
     * @param indvdlInfoYn the indvdlInfoYn to set
     */
    /** 개인정보보호정책 동의여부  */
    var indvdlInfoYn: String? = null

    /**
     * frstRegisterPnttm 리턴
     *
     * @return the frstRegisterPnttm
     */
    /**
     * frstRegisterPnttm 설정
     *
     * @param frstRegisterPnttm the frstRegisterPnttm to set
     */
    /** 최초등록시점  */
    var frstRegisterPnttm: String? = null

    /**
     * frstRegisterId 리턴
     *
     * @return the frstRegisterId
     */
    /**
     * frstRegisterId 설정
     *
     * @param frstRegisterId the frstRegisterId to set
     */
    /** 최초등록아이디  */
    var frstRegisterId: String? = null

    /**
     * lastUpdusrPnttm 리턴
     *
     * @return the lastUpdusrPnttm
     */
    /**
     * lastUpdusrPnttm 설정
     *
     * @param lastUpdusrPnttm the lastUpdusrPnttm to set
     */
    /** 최종수정일  */
    var lastUpdusrPnttm: String? = null

    /**
     * lastUpdusrId 리턴
     *
     * @return the lastUpdusrId
     */
    /**
     * lastUpdusrId 설정
     *
     * @param lastUpdusrId the lastUpdusrId to set
     */
    /** 최종수정자 아이디  */
    var lastUpdusrId: String? = null

    /**
     * cmd 리턴
     *
     * @return the cmd
     */
    /**
     * cmd 설정
     *
     * @param cmd the cmd to set
     */
    /** 컨트롤 명령어  */
    var cmd: String? = null


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
