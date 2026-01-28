package egovframework.let.uss.ion.bnr.service.impl

import egovframework.com.cmm.service.FileVO
import egovframework.let.uss.ion.bnr.service.Banner
import egovframework.let.uss.ion.bnr.service.BannerVO
import egovframework.let.uss.ion.bnr.service.EgovBannerService
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.Resource

/**
 * 배너에 대한 ServiceImpl 클래스를 정의한다.
 * 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 공통서비스개발팀 lee.m.j
 * @since 2009.08.03
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("egovBannerService")
class EgovBannerServiceImpl : EgovAbstractServiceImpl(), EgovBannerService {
    @Resource(name = "bannerDAO")
    private val bannerDAO: BannerDAO? = null

    /**
     * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
     * @param bannerVO - 배너 VO
     * @return List - 배너 목록
     */
    @Throws(Exception::class)
    override fun selectBannerList(bannerVO: BannerVO?): MutableList<BannerVO?>? {
        return bannerDAO!!.selectBannerList(bannerVO)
    }

    /**
     * 배너목록 총 갯수를 조회한다.
     * @param bannerVO - 배너 VO
     * @return int - 배너 카운트 수
     */
    @Throws(Exception::class)
    override fun selectBannerListTotCnt(bannerVO: BannerVO?): Int {
        return bannerDAO!!.selectBannerListTotCnt(bannerVO)
    }

    /**
     * 등록된 배너의 상세정보를 조회한다.
     * @param bannerVO - 배너 VO
     * @return BannerVO - 배너 VO
     */
    @Throws(Exception::class)
    override fun selectBanner(bannerVO: BannerVO?): BannerVO? {
        return bannerDAO!!.selectBanner(bannerVO)
    }

    /**
     * 배너정보를 신규로 등록한다.
     * @param banner - 배너 model
     */
    @Throws(Exception::class)
    override fun insertBanner(banner: Banner, bannerVO: BannerVO): BannerVO? {
        bannerDAO!!.insertBanner(banner)
        bannerVO.setBannerId(banner.getBannerId())
        return selectBanner(bannerVO)
    }

    /**
     * 기 등록된 배너정보를 수정한다.
     * @param banner - 배너 model
     */
    @Throws(Exception::class)
    override fun updateBanner(banner: Banner?) {
        bannerDAO!!.updateBanner(banner)
    }

    /**
     * 기 등록된 배너정보를 삭제한다.
     * @param banner - 배너 model
     */
    @Throws(Exception::class)
    override fun deleteBanner(banner: Banner?) {
        deleteBannerFile(banner)
        bannerDAO!!.deleteBanner(banner)
    }

    /**
     * 기 등록된 배너정보의 이미지파일을 삭제한다.
     * @param banner - 배너 model
     */
    @Throws(Exception::class)
    override fun deleteBannerFile(banner: Banner?) {
        val fileVO = bannerDAO!!.selectBannerFile(banner) as FileVO
        val file = File(fileVO.fileStreCours + fileVO.streFileNm)
        file.delete()
    }

    /**
     * 배너가 특정화면에 반영된 결과를 조회한다.
     * @param bannerVO - 배너 VO
     * @return BannerVO - 배너 VO
     */
    @Throws(Exception::class)
    override fun selectBannerResult(bannerVO: BannerVO?): MutableList<BannerVO?>? {
        return bannerDAO!!.selectBannerResult(bannerVO)
    }
}