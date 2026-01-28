package egovframework.let.uss.ion.bnr.service

import egovframework.com.cmm.ComDefaultVO

/**
 * 배너에 대한 model 클래스를 정의한다.
 * 배너의 일련번호, 배너명, 링크URL, 배너설명, 반영여부 항목을 관리한다.
 * @author 공통서비스개발팀 lee.m.j
 * @since 2009.08.03
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
open class Banner : ComDefaultVO() {
    /**
     * @return the bannerId
     */
    /**
     * @param bannerId the bannerId to set
     */
    /**
     * 배너 ID
     */
    @JvmField
    var bannerId: String? = null
    /**
     * @return the bannerNm
     */
    /**
     * @param bannerNm the bannerNm to set
     */
    /**
     * 배너 명
     */
    var bannerNm: String? = null
    /**
     * @return the linkUrl
     */
    /**
     * @param linkUrl the linkUrl to set
     */
    /**
     * 링크 URL
     */
    var linkUrl: String? = null
    /**
     * @return the bannerImage
     */
    /**
     * @param bannerImage the bannerImage to set
     */
    /**
     * 배너 이미지
     */
    @JvmField
    var bannerImage: String? = null
    /**
     * @return the bannerImageFile
     */
    /**
     * @param bannerImageFile the bannerImageFile to set
     */
    /**
     * 배너 이미지 파일
     */
    @JvmField
    var bannerImageFile: String? = null
    /**
     * @return the bannerDc
     */
    /**
     * @param bannerDc the bannerDc to set
     */
    /**
     * 배너 설명
     */
    var bannerDc: String? = null
    /**
     * @return the sortOrdr
     */
    /**
     * @param sortOrdr the sortOrdr to set
     */
    /**
     * 정렬 순서
     */
    var sortOrdr: String? = null
    /**
     * @return the reflctAt
     */
    /**
     * @param reflctAt the reflctAt to set
     */
    /**
     * 반영여부
     */
    var reflctAt: String? = null
    /**
     * @return the userId
     */
    /**
     * @param userId the userId to set
     */
    /**
     * 사용자 ID
     */
    @JvmField
    var userId: String? = null
    /**
     * @return the regDate
     */
    /**
     * @param regDate the regDate to set
     */
    /**
     * 등록일자
     */
    var regDate: String? = null
    /**
     * @return the isAtchFile
     */
    /**
     * @param isAtchFile the isAtchFile to set
     */
    /**
     * 파일첨부여부
     */
    @JvmField
    var isAtchFile: Boolean = false

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
