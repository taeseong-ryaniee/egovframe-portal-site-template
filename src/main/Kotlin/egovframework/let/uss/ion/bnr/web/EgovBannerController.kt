package egovframework.let.uss.ion.bnr.web

import egovframework.com.cmm.EgovMessageSource
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovFileMngService
import egovframework.com.cmm.service.EgovFileMngUtil
import egovframework.com.cmm.service.FileVO
import egovframework.let.uss.ion.bnr.service.Banner
import egovframework.let.uss.ion.bnr.service.BannerVO
import egovframework.let.uss.ion.bnr.service.EgovBannerService
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource

/**
 * 배너에 대한 controller 클래스를 정의한다.
 * 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 공통서비스개발팀 lee.m.j
 * @since 2009.08.03
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovBannerController {
    @Resource(name = "egovMessageSource")
    var egovMessageSource: EgovMessageSource? = null

    @Autowired
    private val fileMngService: EgovFileMngService? = null

    @Resource(name = "EgovFileMngUtil")
    private val fileUtil: EgovFileMngUtil? = null

    @Resource(name = "egovBannerService")
    private val egovBannerService: EgovBannerService? = null

    /** Message ID Generation  */
    @Resource(name = "egovBannerIdGnrService")
    private val egovBannerIdGnrService: EgovIdGnrService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 배너 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/uss/ion/bnr/selectBannerListView.do")
    @Throws(Exception::class)
    fun selectBannerListView(): String {
        return "/uss/ion/bnr/EgovBannerList"
    }

    /**
     * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
     * @param bannerVO - 배너 VO
     * @return String - 리턴 URL
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/ion/bnr/selectBannerList.do"])
    @Throws(Exception::class)
    fun selectBannerList(@ModelAttribute("bannerVO") bannerVO: BannerVO, model: ModelMap): String {
        /** paging  */

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(bannerVO.pageIndex)
        paginationInfo.setRecordCountPerPage(bannerVO.pageUnit)
        paginationInfo.setPageSize(bannerVO.pageSize)

        bannerVO.firstIndex = paginationInfo.getFirstRecordIndex()
        bannerVO.lastIndex = paginationInfo.getLastRecordIndex()
        bannerVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        bannerVO.setBannerList(egovBannerService!!.selectBannerList(bannerVO))

        model.addAttribute("bannerList", bannerVO.bannerList)

        val totCnt = egovBannerService.selectBannerListTotCnt(bannerVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/uss/ion/bnr/EgovBannerList"
    }

    /**
     * 등록된 배너의 상세정보를 조회한다.
     * @param bannerVO - 배너 Vo
     * @return String - 리턴 Url
     */
    @RequestMapping(value = ["/uss/ion/bnr/getBanner.do"])
    @Throws(Exception::class)
    fun selectBanner(
        @RequestParam("bannerId") bannerId: String?,
        @ModelAttribute("bannerVO") bannerVO: BannerVO,
        model: ModelMap
    ): String {
        bannerVO.bannerId = bannerId

        model.addAttribute("banner", egovBannerService!!.selectBanner(bannerVO))
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.select"))

        return "/uss/ion/bnr/EgovBannerUpdt"
    }

    /**
     * 배너등록 화면으로 이동한다.
     * @param banner - 배너 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = ["/uss/ion/bnr/addViewBanner.do"])
    @Throws(Exception::class)
    fun insertViewBanner(@ModelAttribute("bannerVO") bannerVO: BannerVO?, model: ModelMap): String {
        model.addAttribute("banner", bannerVO)
        return "/uss/ion/bnr/EgovBannerRegist"
    }

    /**
     * 배너정보를 신규로 등록한다.
     * @param banner - 배너 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = ["/uss/ion/bnr/addBanner.do"])
    @Throws(Exception::class)
    fun insertBanner(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("banner") banner: Banner,
        @ModelAttribute("bannerVO") bannerVO: BannerVO,
        bindingResult: BindingResult,
        status: SessionStatus,
        model: ModelMap
    ): String {
        beanValidator!!.validate(banner, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            model.addAttribute("bannerVO", bannerVO)
            return "/uss/ion/bnr/EgovBannerRegist"
        } else {
            var result: MutableList<FileVO>? = null

            val uploadFolder = ""
            var bannerImage: String? = ""
            var atchFileId: String? = ""

            val files = multiRequest.getFileMap()

            if (!files.isEmpty()) {
                result = fileUtil!!.parseFileInf(files, "BNR_", 0, "", uploadFolder)
                atchFileId = fileMngService!!.insertFileInfs(result)

                var vo = result.get(0)
                val iter = result.iterator()

                while (iter.hasNext()) {
                    vo = iter.next()
                    bannerImage = vo.orignlFileNm
                }
            }

            val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO

            banner.bannerId = egovBannerIdGnrService!!.getNextStringId()
            banner.bannerImage = bannerImage
            banner.bannerImageFile = atchFileId
            banner.userId = user.id
            bannerVO.bannerId = banner.bannerId
            status.setComplete()
            model.addAttribute("message", egovMessageSource!!.getMessage("success.common.insert"))
            model.addAttribute("banner", egovBannerService!!.insertBanner(banner, bannerVO))

            return "/uss/ion/bnr/EgovBannerUpdt"
        }
    }

    /**
     * 기 등록된 배너정보를 수정한다.
     * @param banner - 배너 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = ["/uss/ion/bnr/updtBanner.do"])
    @Throws(Exception::class)
    fun updateBanner(
        multiRequest: MultipartHttpServletRequest,
        @ModelAttribute("banner") banner: Banner,
        bindingResult: BindingResult,
        status: SessionStatus?,
        model: ModelMap
    ): String {
        beanValidator!!.validate(banner, bindingResult) //validation 수행

        if (bindingResult.hasErrors()) {
            model.addAttribute("bannerVO", banner)
            return "/uss/ion/bnr/EgovBannerUpdt"
        } else {
            var result: MutableList<FileVO>? = null

            val uploadFolder = ""
            var bannerImage: String? = ""
            var atchFileId: String? = ""

            val files = multiRequest.getFileMap()

            if (!files.isEmpty()) {
                result = fileUtil!!.parseFileInf(files, "BNR_", 0, "", uploadFolder)
                atchFileId = fileMngService!!.insertFileInfs(result)

                var vo: FileVO? = null
                val iter: MutableIterator<FileVO?> = result.iterator()

                while (iter.hasNext()) {
                    vo = iter.next()
                    bannerImage = vo!!.orignlFileNm
                }

                if (vo == null) {
                    banner.isAtchFile = false
                } else {
                    banner.bannerImage = bannerImage
                    banner.bannerImageFile = atchFileId
                    banner.isAtchFile = true
                }
            } else {
                banner.isAtchFile = false
            }

            val user = EgovUserDetailsHelper.getAuthenticatedUser() as LoginVO
            banner.userId = user.id

            egovBannerService!!.updateBanner(banner)

            return "forward:/uss/ion/bnr/getBanner.do"
        }
    }

    /**
     * 기 등록된 배너정보를 삭제한다.
     * @param banner Banner
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/uss/ion/bnr/removeBanner.do"])
    @Throws(Exception::class)
    fun deleteBanner(
        @RequestParam("bannerId") bannerId: String?,
        @ModelAttribute("banner") banner: Banner,
        status: SessionStatus,
        model: ModelMap
    ): String {
        banner.bannerId = bannerId
        egovBannerService!!.deleteBanner(banner)
        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))

        return "forward:/uss/ion/bnr/selectBannerList.do"
    }

    /**
     * 기 등록된 배너정보목록을 일괄 삭제한다.
     * @param banners String
     * @param banner Banner
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = ["/uss/ion/bnr/removeBannerList.do"])
    @Throws(Exception::class)
    fun deleteBannerList(
        @RequestParam("bannerIds") bannerIds: String,
        @ModelAttribute("banner") banner: Banner,
        status: SessionStatus,
        model: ModelMap
    ): String {
        val strBannerIds: Array<String?> = bannerIds.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in strBannerIds.indices) {
            banner.bannerId = strBannerIds[i]
            egovBannerService!!.deleteBanner(banner)
        }

        status.setComplete()
        model.addAttribute("message", egovMessageSource!!.getMessage("success.common.delete"))

        return "forward:/uss/ion/bnr/selectBannerList.do"
    }

    /**
     * 배너가 특정화면에 반영된 결과를 조회한다.
     * @param bannerVO - 배너 VO
     * @return String - 리턴 Url
     */
    @RequestMapping(value = ["/uss/ion/bnr/getBannerImage.do"])
    @Throws(Exception::class)
    fun selectBannerResult(@ModelAttribute("bannerVO") bannerVO: BannerVO, model: ModelMap): String {
        val fileList = egovBannerService!!.selectBannerResult(bannerVO)
        model.addAttribute("fileList", fileList)
        model.addAttribute("resultType", bannerVO.resultType)

        return "/uss/ion/bnr/EgovBannerView"
    }

    /**
     * MyPage에 배너정보를 제공하기 위해 목록을 조회한다.
     * @param bannerVO - 배너 VO
     * @return String - 리턴 URL
     * @throws Exception
     */
    @RequestMapping(value = ["/uss/ion/bnr/selectBannerMainList.do"])
    @Throws(Exception::class)
    fun selectBannerMainList(@ModelAttribute("bannerVO") bannerVO: BannerVO, model: ModelMap): String {
        /** paging  */

        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(bannerVO.pageIndex)
        paginationInfo.setRecordCountPerPage(5)
        paginationInfo.setPageSize(bannerVO.pageSize)

        bannerVO.firstIndex = paginationInfo.getFirstRecordIndex()
        bannerVO.lastIndex = paginationInfo.getLastRecordIndex()
        bannerVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        bannerVO.setBannerList(egovBannerService!!.selectBannerList(bannerVO))

        model.addAttribute("bannerList", bannerVO.bannerList)

        return "/uss/ion/bnr/EgovBannerMainList"
    }
}
