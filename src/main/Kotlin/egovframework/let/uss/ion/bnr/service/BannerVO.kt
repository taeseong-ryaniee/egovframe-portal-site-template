package egovframework.let.uss.ion.bnr.service

/**
 * 배너에 대한 Vo 클래스를 정의한다.
 * 배너의 목록 항목을 관리한다.
 * @author 공통서비스개발팀 lee.m.j
 * @since 2009.08.03
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class BannerVO : Banner() {
    /**
     * @return the bannerList
     */
    /**
     * @param bannerList the bannerList to set
     */
    /**
     * 배너 목록
     */
    @JvmField
    var bannerList: MutableList<BannerVO?>? = null
    /**
     * @return the delYn
     */
    /**
     * @param delYn the delYn to set
     */
    /**
     * 삭제대상 목록
     */
    var delYn: Array<String?>?
    /**
     * @return the resultType
     */
    /**
     * @param resultType the resultType to set
     */
    /**
     * 결과 반영 타입
     * vertical : 세로
     * horizontal : 가로
     */
    @JvmField
    var resultType: String? = "horizontal"


    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
