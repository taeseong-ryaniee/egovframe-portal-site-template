package egovframework.let.uss.olp.qmc.service

import java.io.Serializable

/**
 * 설문관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class QustnrManageVO : Serializable {
    /**
     * qestnrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrId attribute 값을 설정한다.
     * @return qestnrId String
     */
    /** 설문지ID  */
    var qestnrId: String? = ""

    /**
     * qestnrSj attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrSj attribute 값을 설정한다.
     * @return qestnrSj String
     */
    /**  설문제목  */
    var qestnrSj: String? = ""

    /**
     * qestnrPurps attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrPurps attribute 값을 설정한다.
     * @return qestnrPurps String
     */
    /**  설문목적  */
    var qestnrPurps: String? = ""

    /**
     * qestnrWritngGuidanceCn attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrWritngGuidanceCn attribute 값을 설정한다.
     * @return qestnrWritngGuidanceCn String
     */
    /**  설문작성안내내용  */
    var qestnrWritngGuidanceCn: String? = ""

    /**
     * qestnrBeginDe attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrBeginDe attribute 값을 설정한다.
     * @return qestnrBeginDe String
     */
    /**  설문시작일자  */
    var qestnrBeginDe: String? = ""

    /**
     * qestnrEndDe attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrEndDe attribute 값을 설정한다.
     * @return qestnrEndDe String
     */
    /**  설문종료일자  */
    var qestnrEndDe: String? = ""

    /**
     * qestnrTrget attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTrget attribute 값을 설정한다.
     * @return qestnrTrget String
     */
    /**  설문대상  */
    var qestnrTrget: String? = ""

    /**
     * qestnrTmplatId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatId attribute 값을 설정한다.
     * @return qestnrTmplatId String
     */
    /**  설문시작일자  */
    var qestnrTmplatId: String? = ""

    /**
     * qestnrTmplatTy attribute 를 리턴한다.
     * @return the String
     */
    /**
     * qestnrTmplatTy attribute 값을 설정한다.
     * @return qestnrTmplatTy String
     */
    /**  설문템플릿유형  */
    var qestnrTmplatTy: String? = ""

    /**
     * frstRegisterPnttm attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @return frstRegisterPnttm String
     */
    /**  최초등록시점  */
    var frstRegisterPnttm: String? = ""

    /**
     * frstRegisterId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @return frstRegisterId String
     */
    /**  최초등록자아이디  */
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
    /**  최종수정시점  */
    var lastUpdusrPnttm: String? = ""

    /**
     * lastUpdusrId attribute 를 리턴한다.
     * @return the String
     */
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @return lastUpdusrId String
     */
    /**  최종수정자아이디  */
    @JvmField
    var lastUpdusrId: String? = ""


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
