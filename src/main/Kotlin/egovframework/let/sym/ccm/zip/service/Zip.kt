package egovframework.let.sym.ccm.zip.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 우편번호 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class Zip : Serializable {
    /**
     * zip attribute 를 리턴한다.
     * @return String
     */
    /**
     * zip attribute 값을 설정한다.
     * @param zip String
     */
    /*
          * 우편번호
          */
    @JvmField
    var zip: String? = ""

    /**
     * sn attribute 를 리턴한다.
     * @return int
     */
    /**
     * sn attribute 값을 설정한다.
     * @param sn int
     */
    /*
          * 일련번호
          */
    var sn: Int = 0

    /**
     * ctprvnNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * ctprvnNm attribute 값을 설정한다.
     * @param ctprvnNm String
     */
    /*
          * 시도명
          */
    var ctprvnNm: String? = ""

    /**
     * signguNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * signguNm attribute 값을 설정한다.
     * @param signguNm String
     */
    /*
          * 시군구명
          */
    var signguNm: String? = ""

    /**
     * emdNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * emdNm attribute 값을 설정한다.
     * @param emdNm String
     */
    /*
          * 읍면동명
          */
    var emdNm: String? = ""

    /**
     * liBuldNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * liBuldNm attribute 값을 설정한다.
     * @param liBuldNm String
     */
    /*
          * 리건물명
          */
    var liBuldNm: String? = ""

    /**
     * lnbrDongHo attribute 를 리턴한다.
     * @return String
     */
    /**
     * lnbrDongHo attribute 값을 설정한다.
     * @param lnbrDongHo String
     */
    /*
          * 번지동호
          */
    var lnbrDongHo: String? = ""

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId String
     */
    /*
          * 최초등록자ID
          */
    @JvmField
    var frstRegisterId: String? = ""

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @param lastUpdusrId String
     */
    /*
          * 최종수정자ID
          */
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
