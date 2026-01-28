package egovframework.let.sym.cal.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 휴일 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class Restde : Serializable {
    /**
     * restdeNo attribute 를 리턴한다.
     * @return int
     */
    /**
     * restdeNo attribute 값을 설정한다.
     * @param restdeNo int
     */
    /*
          * 휴일번호
          */
    var restdeNo: Int = 0

    /**
     * restdeDe attribute 를 리턴한다.
     * @return String
     */
    /**
     * restdeDe attribute 값을 설정한다.
     * @param restdeDe String
     */
    /*
          * 휴일일자
          */
    var restdeDe: String? = ""

    /**
     * restdeNm attribute 를 리턴한다.
     * @return String
     */
    /**
     * restdeNm attribute 값을 설정한다.
     * @param restdeNm String
     */
    /*
          * 휴일명
          */
    var restdeNm: String? = ""

    /**
     * restdeDc attribute 를 리턴한다.
     * @return String
     */
    /**
     * restdeDc attribute 값을 설정한다.
     * @param restdeDc String
     */
    /*
          * 휴일설명
          */
    var restdeDc: String? = ""

    /**
     * restdeSe attribute 를 리턴한다.
     * @return String
     */
    /**
     * restdeSe attribute 값을 설정한다.
     * @param restdeSe String
     */
    /*
          * 휴일구분
          */
    var restdeSe: String? = ""

    /**
     * restdeSeCode attribute 를 리턴한다.
     * @return String
     */
    /**
     * restdeSeCode attribute 값을 설정한다.
     * @param restdeSeCode String
     */
    /*
          * 휴일구분코드
          */
    var restdeSeCode: String? = ""

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
     * year attribute 를 리턴한다.
     * @return String
     */
    /**
     * year attribute 값을 설정한다.
     * @param year String
     */
    /*
          * 년
          */
    @JvmField
    var year: String? = ""

    /**
     * month attribute 를 리턴한다.
     * @return String
     */
    /**
     * month attribute 값을 설정한다.
     * @param month String
     */
    /*
          * 월
          */
    @JvmField
    var month: String? = ""

    /**
     * day attribute 를 리턴한다.
     * @return String
     */
    /**
     * day attribute 값을 설정한다.
     * @param day String
     */
    /*
          * 일
          */
    @JvmField
    var day: String? = ""

    /**
     * restdeAt attribute 를 리턴한다.
     * @return String
     */
    /**
     * restdeAt attribute 값을 설정한다.
     * @param restdeAt String
     */
    /*
          * 휴일여부
          */
    var restdeAt: String? = ""

    /**
     * cellNum attribute 를 리턴한다.
     * @return int
     */
    /**
     * cellNum attribute 값을 설정한다.
     * @param cellNum int
     */
    /*
          * 달력셀
          */
    var cellNum: Int = 0

    /**
     * weeks attribute 를 리턴한다.
     * @return int
     */
    /**
     * weeks attribute 값을 설정한다.
     * @param weeks int
     */
    /*
          * 월별 주순위
          */
    @JvmField
    var weeks: Int = 0

    /**
     * maxWeeks attribute 를 리턴한다.
     * @return int
     */
    /**
     * maxWeeks attribute 값을 설정한다.
     * @param maxWeeks int
     */
    /*
          * 월 주수
          */
    @JvmField
    var maxWeeks: Int = 0

    /**
     * week attribute 를 리턴한다.
     * @return int
     */
    /**
     * week attribute 값을 설정한다.
     * @param week int
     */
    /*
          * 요일
          */
    @JvmField
    var week: Int = 0

    /**
     * startWeekMonth attribute 를 리턴한다.
     * @return int
     */
    /**
     * startWeekMonth attribute 값을 설정한다.
     * @param startWeekMonth int
     */
    /*
          * 시작요일 
          */
    @JvmField
    var startWeekMonth: Int = 0

    /**
     * lastDayMonth attribute 를 리턴한다.
     * @return int
     */
    /**
     * lastDayMonth attribute 값을 설정한다.
     * @param lastDayMonth int
     */
    /*
          * 마지막 일자
          */
    @JvmField
    var lastDayMonth: Int = 0

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
