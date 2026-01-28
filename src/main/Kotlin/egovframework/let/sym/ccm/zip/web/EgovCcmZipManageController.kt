package egovframework.let.sym.ccm.zip.web

import egovframework.com.cmm.LoginVO
import egovframework.let.sym.ccm.zip.service.EgovCcmZipManageService
import egovframework.let.sym.ccm.zip.service.Zip
import egovframework.let.sym.ccm.zip.service.ZipVO
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springmodules.validation.commons.DefaultBeanValidator
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

/**
 *
 * 우편번호에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovCcmZipManageController {
    @Resource(name = "ZipManageService")
    private val zipManageService: EgovCcmZipManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 우편번호 찾기 팝업 메인창을 호출한다.
     * @param model
     * @return "/cmm/sym/zip/EgovCcmZipSearchPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/EgovCcmZipSearchPopup.do"])
    @Throws(Exception::class)
    fun callNormalCalPopup(
        model: ModelMap?
    ): String {
        return "/cmm/sym/zip/EgovCcmZipSearchPopup"
    }

    /**
     * 우편번호 찾기 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return "/cmm/sym/zip/EgovCcmZipSearchList"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/EgovCcmZipSearchList.do"])
    @Throws(Exception::class)
    fun selectZipSearchList(
        @ModelAttribute("searchVO") searchVO: ZipVO,
        model: ModelMap
    ): String {
        /** EgovPropertyService.sample  */
        searchVO.pageUnit = propertiesService!!.getInt("pageUnit")
        searchVO.pageSize = propertiesService!!.getInt("pageSize")

        /** pageing  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(searchVO.pageIndex)
        paginationInfo.setRecordCountPerPage(searchVO.pageUnit)
        paginationInfo.setPageSize(searchVO.pageSize)

        searchVO.firstIndex = paginationInfo.getFirstRecordIndex()
        searchVO.lastIndex = paginationInfo.getLastRecordIndex()
        searchVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        model.addAttribute("resultList", zipManageService!!.selectZipList(searchVO))

        val totCnt = zipManageService.selectZipListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/cmm/sym/zip/EgovCcmZipSearchList"
    }

    /**
     * 우편번호를 삭제한다.
     * @param loginVO
     * @param zip
     * @param model
     * @return "forward:/sym/ccm/zip/EgovCcmZipList.do"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/ccm/zip/EgovCcmZipRemove.do"])
    @Throws(Exception::class)
    fun deleteZip(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        zip: Zip?,
        model: ModelMap?
    ): String {
        zipManageService!!.deleteZip(zip)
        return "forward:/sym/ccm/zip/EgovCcmZipList.do"
    }

    /**
     * 우편번호를 등록한다.
     * @param loginVO
     * @param zip
     * @param bindingResult
     * @param model
     * @return "/cmm/sym/zip/EgovCcmZipRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/ccm/zip/EgovCcmZipRegist.do"])
    @Throws(Exception::class)
    fun insertZip(
        @ModelAttribute("loginVO") loginVO: LoginVO,
        @ModelAttribute("zip") zip: Zip,
        bindingResult: BindingResult,
        model: ModelMap?
    ): String {
        if (zip.zip == null
            || zip.zip == ""
        ) {
            return "/cmm/sym/zip/EgovCcmZipRegist"
        }

        beanValidator!!.validate(zip, bindingResult)
        if (bindingResult.hasErrors()) {
            return "/cmm/sym/zip/EgovCcmZipRegist"
        }

        zip.frstRegisterId = loginVO.uniqId
        zipManageService!!.insertZip(zip)
        return "forward:/sym/ccm/zip/EgovCcmZipList.do"
    }

    /**
     * 엑셀파일을 업로드하여 우편번호를 등록한다.
     * @param loginVO
     * @param request
     * @param commandMap
     * @param model
     * @return "/cmm/sym/zip/EgovCcmExcelZipRegist"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/ccm/zip/EgovCcmExcelZipRegist.do"])
    @Throws(Exception::class)
    fun insertExcelZip(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        request: HttpServletRequest?,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: Model?
    ): String {
        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        if (sCmd == "") {
            return "/cmm/sym/zip/EgovCcmExcelZipRegist"
        }

        val multiRequest = request as MultipartHttpServletRequest
        val files = multiRequest.getFileMap()

        val itr: MutableIterator<MutableMap.MutableEntry<String?, MultipartFile>> = files.entries.iterator()
        var file: MultipartFile

        while (itr.hasNext()) {
            val entry = itr.next()

            file = entry.value
            if ("" != file.getOriginalFilename()) {
                //zipManageService.deleteAllZip();
                //excelZipService.uploadExcel("ZipManageDAO.insertExcelZip", file.getInputStream(), 2);
                zipManageService!!.insertExcelZip(file.getInputStream())
            }
            if (file.getInputStream() != null) {
                file.getInputStream().close()
            }
        }

        return "forward:/sym/ccm/zip/EgovCcmZipList.do"
    }

    /**
     * 우편번호 상세항목을 조회한다.
     * @param loginVO
     * @param zip
     * @param model
     * @return "/cmm/sym/zip/EgovCcmZipDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/ccm/zip/EgovCcmZipDetail.do"])
    @Throws(Exception::class)
    fun selectZipDetail(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        zip: Zip?,
        model: ModelMap
    ): String {
        val vo = zipManageService!!.selectZipDetail(zip)
        model.addAttribute("result", vo)

        return "/cmm/sym/zip/EgovCcmZipDetail"
    }

    /**
     * 우편번호 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "/cmm/sym/zip/EgovCcmZipList"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/ccm/zip/EgovCcmZipList.do"])
    @Throws(Exception::class)
    fun selectZipList(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        @ModelAttribute("searchVO") searchVO: ZipVO,
        model: ModelMap
    ): String {
        /** EgovPropertyService.sample  */
        searchVO.pageUnit = propertiesService!!.getInt("pageUnit")
        searchVO.pageSize = propertiesService!!.getInt("pageSize")

        /** pageing  */
        val paginationInfo = PaginationInfo()
        paginationInfo.setCurrentPageNo(searchVO.pageIndex)
        paginationInfo.setRecordCountPerPage(searchVO.pageUnit)
        paginationInfo.setPageSize(searchVO.pageSize)

        searchVO.firstIndex = paginationInfo.getFirstRecordIndex()
        searchVO.lastIndex = paginationInfo.getLastRecordIndex()
        searchVO.recordCountPerPage = paginationInfo.getRecordCountPerPage()

        model.addAttribute("resultList", zipManageService!!.selectZipList(searchVO))

        val totCnt = zipManageService.selectZipListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/cmm/sym/zip/EgovCcmZipList"
    }

    /**
     * 우편번호를 수정한다.
     * @param loginVO
     * @param zip
     * @param bindingResult
     * @param commandMap
     * @param model
     * @return "/cmm/sym/zip/EgovCcmZipModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/ccm/zip/EgovCcmZipModify.do"])
    @Throws(Exception::class)
    fun updateZip(
        @ModelAttribute("loginVO") loginVO: LoginVO,
        @ModelAttribute("zip") zip: Zip,
        bindingResult: BindingResult,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        if (sCmd == "") {
            val vo = zipManageService!!.selectZipDetail(zip)
            model.addAttribute("zip", vo)

            return "/cmm/sym/zip/EgovCcmZipModify"
        } else if (sCmd == "Modify") {
            beanValidator!!.validate(zip, bindingResult)
            if (bindingResult.hasErrors()) {
                return "/cmm/sym/zip/EgovCcmZipModify"
            }

            zip.lastUpdusrId = loginVO.uniqId
            zipManageService!!.updateZip(zip)

            return "forward:/sym/ccm/zip/EgovCcmZipList.do"
        } else {
            return "forward:/sym/ccm/zip/EgovCcmZipList.do"
        }
    }
}