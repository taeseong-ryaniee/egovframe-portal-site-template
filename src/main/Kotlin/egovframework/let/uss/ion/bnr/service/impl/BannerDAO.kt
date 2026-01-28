package egovframework.let.uss.ion.bnr.service.impl

import egovframework.com.cmm.service.FileVO
import egovframework.let.uss.ion.bnr.service.Banner
import egovframework.let.uss.ion.bnr.service.BannerVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 배너에 대한 DAO 클래스를 정의한다.
 * 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 공통서비스개발팀 lee.m.j
 * @since 2009.08.03
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("bannerDAO")
class BannerDAO : EgovAbstractMapper() {
    /**
     * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
     * @param bannerVO - 배너 Vo
     * @return List - 배너 목록
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectBannerList(bannerVO: BannerVO?): MutableList<BannerVO?>? {
        return selectList<BannerVO?>("bannerDAO.selectBannerList", bannerVO)
    }

    /**
     * 배너목록 총 갯수를 조회한다.
     * @param bannerVO BannerVO
     * @return int
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectBannerListTotCnt(bannerVO: BannerVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("bannerDAO.selectBannerListTotCnt", bannerVO)).toInt()
    }

    /**
     * 등록된 배너의 상세정보를 조회한다.
     * @param bannerVO - 배너 Vo
     * @return BannerVO - 배너 Vo
     *
     * @param bannerVO
     */
    @Throws(Exception::class)
    fun selectBanner(bannerVO: BannerVO?): BannerVO? {
        return selectOne<Any?>("bannerDAO.selectBanner", bannerVO) as BannerVO?
    }

    /**
     * 배너정보를 신규로 등록한다.
     * @param banner - 배너 model
     */
    @Throws(Exception::class)
    fun insertBanner(banner: Banner?) {
        insert("bannerDAO.insertBanner", banner)
    }

    /**
     * 기 등록된 배너정보를 수정한다.
     * @param banner - 배너 model
     */
    @Throws(Exception::class)
    fun updateBanner(banner: Banner?) {
        update("bannerDAO.updateBanner", banner)
    }

    /**
     * 기 등록된 배너정보를 삭제한다.
     * @param banner - 배너 model
     *
     * @param banner
     */
    @Throws(Exception::class)
    fun deleteBanner(banner: Banner?) {
        delete("bannerDAO.deleteBanner", banner)
    }

    /**
     * 기 등록된 배너정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
     * @param banner - 배너 model
     * @return FileVO - 파일 VO
     */
    @Throws(Exception::class)
    fun selectBannerFile(banner: Banner?): FileVO? {
        return selectOne<Any?>("bannerDAO.selectBannerFile", banner) as FileVO?
    }

    /**
     * 배너가 특정화면에 반영된 결과를 조회한다.
     * @param bannerVO - 배너 VO
     * @return BannerVO - 배너 VO
     * @exception Exception
     */
    @Throws(Exception::class)
    fun selectBannerResult(bannerVO: BannerVO?): MutableList<BannerVO?>? {
        return selectList<BannerVO?>("bannerDAO.selectBannerResult", bannerVO)
    }
}
