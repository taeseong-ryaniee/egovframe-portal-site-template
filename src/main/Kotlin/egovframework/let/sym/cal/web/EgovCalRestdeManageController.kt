package egovframework.let.sym.cal.web

import egovframework.com.cmm.ComDefaultCodeVO
import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.service.EgovCmmUseService
import egovframework.let.sym.cal.service.EgovCalRestdeManageService
import egovframework.let.sym.cal.service.Restde
import egovframework.let.sym.cal.service.RestdeVO
import org.apache.commons.collections.map.ListOrderedMap
import org.egovframe.rte.fdl.property.EgovPropertyService
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springmodules.validation.commons.DefaultBeanValidator
import java.util.*
import javax.annotation.Resource
import kotlin.math.ceil
import kotlin.math.floor

/**
 *
 * 공휴일에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Controller
class EgovCalRestdeManageController {
    /** RestdeManageService  */
    @Resource(name = "RestdeManageService")
    private val restdeManageService: EgovCalRestdeManageService? = null

    /** EgovPropertyService  */
    @Resource(name = "propertiesService")
    protected var propertiesService: EgovPropertyService? = null

    /** EgovCmmUseService  */
    @Resource(name = "EgovCmmUseService")
    private val cmmUseService: EgovCmmUseService? = null

    /** beanValidator  */
    @Autowired
    private val beanValidator: DefaultBeanValidator? = null

    /**
     * 달력 메인창을 호출한다.
     * @param model
     * @return "/cmm/sym/cal/EgovNormalCalPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/callCalPopup.do"])
    @Throws(Exception::class)
    fun callCalendar(
        model: ModelMap?
    ): String {
        return "/cmm/sym/cal/EgovCalPopup"
    }

    /**
     * 달력을 호출한다.
     * @param model
     * @return "/cmm/sym/cal/EgovNormalCalPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/callCal.do"])
    @Throws(Exception::class)
    fun callCal(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }

        cal.set(iYear, iMonth - 1, 1)

        val firstWeek = cal.get(Calendar.DAY_OF_WEEK)
        val lastDay = cal.getActualMaximum(Calendar.DATE)
        var week = cal.get(Calendar.DAY_OF_WEEK)

        val year = iYear.toString()
        val month = iMonth.toString()

        //String day    = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        restde.startWeekMonth = firstWeek
        restde.lastDayMonth = lastDay
        restde.year = year
        restde.month = month

        val CalInfoList: MutableList<ListOrderedMap?> = ArrayList<ListOrderedMap?>()
        var tmpDay = ""

        /**
         * 계산... START
         */
        for (i in 0..41) {
            val map = ListOrderedMap()
            val cc = i + 1
            val dd = cc - firstWeek + 1

            if (dd > 0 && dd <= lastDay) {
                tmpDay = dd.toString()
            } else {
                tmpDay = ""
            }

            map.put("year", year)
            map.put("month", month)
            map.put("day", tmpDay)
            map.put("cellNum", cc)
            map.put("weeks", (cc - 1) / 7 + 1)
            map.put("week", (week - 1) % 7 + 1)
            map.put("restAt", if ((week - 1) % 7 + 1 == 1) "Y" else "N")

            if (dd > 0 && dd <= lastDay) {
                week++
            }
            CalInfoList.add(map)
        }

        /**
         * 계산... END
         */
        model.addAttribute("resultList", CalInfoList)

        return "/cmm/sym/cal/EgovCalendar"
    }

    /**
     * 일반달력 팝업 메인창을 호출한다.
     * @param model
     * @return "/cmm/sym/cal/EgovNormalCalPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/EgovNormalCalPopup.do"])
    @Throws(Exception::class)
    fun callNormalCalPopup(
        model: ModelMap?
    ): String {
        return "/sym/cal/EgovNormalCalPopup"
    }

    /**
     * 일반달력 팝업 정보를 조회한다.
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovNormalCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/EgovselectNormalCalendar.do"])
    @Throws(Exception::class)
    fun selectNormalRestdePopup(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }


        /* DB를 사용할 경우 처리
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));
		
		cal.set(iYear,iMonth-1,1);
		
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List CalInfoList = restdeManageService.selectNormalRestdePopup(restde);
        */
        cal.set(iYear, iMonth - 1, 1)

        val firstWeek = cal.get(Calendar.DAY_OF_WEEK)
        val lastDay = cal.getActualMaximum(Calendar.DATE)
        var week = cal.get(Calendar.DAY_OF_WEEK)

        val year = iYear.toString()
        val month = iMonth.toString()

        //String day    = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        restde.startWeekMonth = firstWeek
        restde.lastDayMonth = lastDay
        restde.year = year
        restde.month = month

        val CalInfoList: MutableList<ListOrderedMap?> = ArrayList<ListOrderedMap?>()
        var tmpDay = ""

        /**
         * 계산... START
         */
        for (i in 0..41) {
            val map = ListOrderedMap()
            val cc = i + 1
            val dd = cc - firstWeek + 1

            if (dd > 0 && dd <= lastDay) {
                tmpDay = dd.toString()
            } else {
                tmpDay = ""
            }

            map.put("year", year)
            map.put("month", month)
            map.put("day", tmpDay)
            map.put("cellNum", cc)
            map.put("weeks", (cc - 1) / 7 + 1)
            map.put("week", (week - 1) % 7 + 1)
            map.put("restAt", if ((week - 1) % 7 + 1 == 1) "Y" else "N")

            if (dd > 0 && dd <= lastDay) {
                week++
            }
            CalInfoList.add(map)
        }


        /**
         * 계산... END
         */
        model.addAttribute("resultList", CalInfoList)
        return "/sym/cal/EgovNormalCalendar"
    }


    /**
     * 행정달력 팝업 메인창을 호출한다.
     * @param model
     * @return "/cmm/sym/cal/EgovAdministCalPopup"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/EgovAdministCalPopup.do"])
    @Throws(Exception::class)
    fun callAdministCalPopup(
        model: ModelMap?
    ): String {
        return "/cmm/sym/cal/EgovAdministCalPopup"
    }

    /**
     * 행정달력 팝업 정보를 조회한다.
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovAdministCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cmm/EgovselectAdministCalendar.do"])
    @Throws(Exception::class)
    fun selectAdministRestdePopup(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, 1)

        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)

        model.addAttribute("resultList", restdeManageService!!.selectAdministRestdePopup(restde))

        return "/cmm/sym/cal/EgovAdministCalendar"
    }

    /**
     * 일반달력 일간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovNormalDayCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovNormalDayCalendar.do"])
    @Throws(Exception::class)
    fun selectNormalDayCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()


        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        if (restde.day == null || restde.day == "") {
            restde.day = cal.get(Calendar.DATE).toString()
        }

        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()
        val iDay = restde.day!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, iDay)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)

        cal.set(iYear, iMonth - 1, restde.day!!.toInt())
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        restde.year = cal.get(Calendar.YEAR).toString()
        restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        restde.day = cal.get(Calendar.DAY_OF_MONTH).toString()
        restde.week = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)


        //List CalInfoList          = restdeManageService.selectNormalDayCal(restde);
        //List NormalWeekRestdeList = restdeManageService.selectNormalDayRestde(restde);
        model.addAttribute("resultList", restdeManageService!!.selectNormalDayCal(restde))
        model.addAttribute("RestdeList", restdeManageService.selectNormalDayRestde(restde))

        return "/cmm/sym/cal/EgovNormalDayCalendar"
    }

    /**
     * 일반달력 주간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovNormalWeekCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovNormalWeekCalendar.do"])
    @Throws(Exception::class)
    fun selectNormalWeekCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        if (restde.day == null || restde.day == "") {
            restde.day = cal.get(Calendar.DATE).toString()
        }

        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)

        cal.set(iYear, iMonth - 1, restde.day!!.toInt())
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        val iStartWeek = restde.startWeekMonth
        val iLastDate = restde.lastDayMonth
        var iDayWeek = cal.get(Calendar.DAY_OF_WEEK)

        var iMaxWeeks = floor((iLastDate / 7).toDouble()).toInt()
        iMaxWeeks = iMaxWeeks + ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0).toInt()
        restde.maxWeeks = iMaxWeeks

        if (iMaxWeeks < restde.weeks) {
            restde.weeks = iMaxWeeks
        }

        val vo = Restde()
        val weekCal = Calendar.getInstance()
        weekCal.setTime(cal.getTime())

        if (restde.weeks != 0) {
            weekCal.set(Calendar.DATE, (restde.weeks - 1) * 7 + 1)
            if (restde.weeks > 1) {
                iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK)
                weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1))
            }
            restde.day = (weekCal.get(Calendar.DAY_OF_MONTH) + 1).toString()
        }

        iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK)

        // 일요일
        weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1))
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_1", restdeManageService!!.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_1", restdeManageService.selectNormalDayRestde(vo))


        // 월요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_2", restdeManageService.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_2", restdeManageService.selectNormalDayRestde(vo))

        // 화요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_3", restdeManageService.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_3", restdeManageService.selectNormalDayRestde(vo))

        // 수요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        //List CalInfoList_4          = restdeManageService.selectNormalDayCal(vo);
        //List NormalWeekRestdeList_4 = restdeManageService.selectNormalDayRestde(vo);
        model.addAttribute("resultList_4", restdeManageService.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_4", restdeManageService.selectNormalDayRestde(vo))

        // 목요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_5", restdeManageService.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_5", restdeManageService.selectNormalDayRestde(vo))

        // 금요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_6", restdeManageService.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_6", restdeManageService.selectNormalDayRestde(vo))


        // 토요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_7", restdeManageService.selectNormalDayCal(vo))
        model.addAttribute("RestdeList_7", restdeManageService.selectNormalDayRestde(vo))

        model.addAttribute("resultList", restdeManageService.selectNormalDayCal(restde))

        return "/cmm/sym/cal/EgovNormalWeekCalendar"
    }

    /**
     * 일반달력 월간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovNormalMonthCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovNormalMonthCalendar.do"])
    @Throws(Exception::class)
    fun selectNormalMonthCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, 1)

        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)

        model.addAttribute("resultList", restdeManageService!!.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList", restdeManageService.selectNormalMonthRestde(restde))

        return "/cmm/sym/cal/EgovNormalMonthCalendar"
    }

    /**
     * 일반달력 연간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovNormalYearCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovNormalYearCalendar.do"])
    @Throws(Exception::class)
    fun selectNormalYearCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()


        /* 월별확인 */

        /* 1월 */
        iMonth = 1
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_1", restdeManageService!!.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_1", restdeManageService.selectNormalMonthRestde(restde))

        /* 2월 */
        iMonth = 2
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_2", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_2", restdeManageService.selectNormalMonthRestde(restde))

        /* 3월 */
        iMonth = 3
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_3", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_3", restdeManageService.selectNormalMonthRestde(restde))

        /* 4월 */
        iMonth = 4
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_4", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_4", restdeManageService.selectNormalMonthRestde(restde))

        /* 5월 */
        iMonth = 5
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_5", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_5", restdeManageService.selectNormalMonthRestde(restde))

        /* 6월 */
        iMonth = 6
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_6", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_6", restdeManageService.selectNormalMonthRestde(restde))

        /* 7월 */
        iMonth = 7
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_7", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_7", restdeManageService.selectNormalMonthRestde(restde))

        /* 8월 */
        iMonth = 8
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_8", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_8", restdeManageService.selectNormalMonthRestde(restde))

        /* 9월 */
        iMonth = 9
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_9", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_9", restdeManageService.selectNormalMonthRestde(restde))

        /* 10월 */
        iMonth = 10
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_10", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_10", restdeManageService.selectNormalMonthRestde(restde))

        /* 11월 */
        iMonth = 11
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_11", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_11", restdeManageService.selectNormalMonthRestde(restde))

        /* 12월 */
        iMonth = 12
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_12", restdeManageService.selectNormalRestdePopup(restde))
        model.addAttribute("RestdeList_12", restdeManageService.selectNormalMonthRestde(restde))

        return "/cmm/sym/cal/EgovNormalYearCalendar"
    }


    /**
     * 행정달력 일간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovAdministDayCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovAdministDayCalendar.do"])
    @Throws(Exception::class)
    fun selectAdministDayCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()


        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        if (restde.day == null || restde.day == "") {
            restde.day = cal.get(Calendar.DATE).toString()
        }

        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()
        val iDay = restde.day!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, iDay)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)

        cal.set(iYear, iMonth - 1, restde.day!!.toInt())
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        restde.year = cal.get(Calendar.YEAR).toString()
        restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        restde.day = cal.get(Calendar.DAY_OF_MONTH).toString()
        restde.week = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)

        model.addAttribute("resultList", restdeManageService!!.selectAdministDayCal(restde))
        model.addAttribute("RestdeList", restdeManageService.selectAdministDayRestde(restde))

        return "/cmm/sym/cal/EgovAdministDayCalendar"
    }


    /**
     * 행정달력 주간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovAdministWeekCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovAdministWeekCalendar.do"])
    @Throws(Exception::class)
    fun selectAdministWeekCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        if (restde.day == null || restde.day == "") {
            restde.day = cal.get(Calendar.DATE).toString()
        }

        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)

        cal.set(iYear, iMonth - 1, restde.day!!.toInt())
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        val iStartWeek = restde.startWeekMonth
        val iLastDate = restde.lastDayMonth
        var iDayWeek = cal.get(Calendar.DAY_OF_WEEK)

        var iMaxWeeks = floor((iLastDate / 7).toDouble()).toInt()
        iMaxWeeks = iMaxWeeks + ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0).toInt()
        restde.maxWeeks = iMaxWeeks

        if (iMaxWeeks < restde.weeks) {
            restde.weeks = iMaxWeeks
        }

        val vo = Restde()
        val weekCal = Calendar.getInstance()
        weekCal.setTime(cal.getTime())

        if (restde.weeks != 0) {
            weekCal.set(Calendar.DATE, (restde.weeks - 1) * 7 + 1)
            if (restde.weeks > 1) {
                iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK)
                weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1))
            }
            restde.day = (weekCal.get(Calendar.DAY_OF_MONTH) + 1).toString()
        }
        //List CalInfoList = restdeManageService.selectAdministDayCal(restde);
        model.addAttribute("resultList", restdeManageService!!.selectAdministDayCal(restde))

        iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK)

        // 일요일
        weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1))
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        //List CalInfoList_1          = restdeManageService.selectAdministDayCal(vo);
        //List AdministWeekRestdeList_1 = restdeManageService.selectAdministDayRestde(vo);
        model.addAttribute("resultList_1", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_1", restdeManageService.selectAdministDayRestde(vo))


        // 월요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_2", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_2", restdeManageService.selectAdministDayRestde(vo))

        // 화요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_3", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_3", restdeManageService.selectAdministDayRestde(vo))

        // 수요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_4", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_4", restdeManageService.selectAdministDayRestde(vo))

        // 목요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_5", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_5", restdeManageService.selectAdministDayRestde(vo))

        // 금요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)
        model.addAttribute("resultList_6", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_6", restdeManageService.selectAdministDayRestde(vo))

        // 토요일
        weekCal.add(Calendar.DATE, 1)
        vo.year = weekCal.get(Calendar.YEAR).toString()
        vo.month = (weekCal.get(Calendar.MONTH) + 1).toString()
        vo.day = weekCal.get(Calendar.DAY_OF_MONTH).toString()
        vo.week = weekCal.get(Calendar.DAY_OF_WEEK)

        model.addAttribute("resultList_7", restdeManageService.selectAdministDayCal(vo))
        model.addAttribute("RestdeList_7", restdeManageService.selectAdministDayRestde(vo))

        return "/cmm/sym/cal/EgovAdministWeekCalendar"
    }

    /**
     * 행정달력 월간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovAdministMonthCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovAdministMonthCalendar.do"])
    @Throws(Exception::class)
    fun selectAdministMonthCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()
        restde.month = iMonth.toString()

        cal.set(iYear, iMonth - 1, 1)

        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)

        model.addAttribute("resultList", restdeManageService!!.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList", restdeManageService.selectAdministMonthRestde(restde))

        return "/cmm/sym/cal/EgovAdministMonthCalendar"
    }


    /**
     * 행정달력 연간
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovAdministYearCalendar"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovAdministYearCalendar.do"])
    @Throws(Exception::class)
    fun selectAdministYearCalendar(
        restde: Restde,
        model: ModelMap
    ): String {
        val cal = Calendar.getInstance()

        if (restde.year == null || restde.year == "") {
            restde.year = cal.get(Calendar.YEAR).toString()
        }
        if (restde.month == null || restde.month == "") {
            restde.month = (cal.get(Calendar.MONTH) + 1).toString()
        }
        var iYear = restde.year!!.toInt()
        var iMonth = restde.month!!.toInt()

        if (iMonth < 1) {
            iYear--
            iMonth = 12
        }
        if (iMonth > 12) {
            iYear++
            iMonth = 1
        }
        if (iYear < 1) {
            iYear = 1
            iMonth = 1
        }
        if (iYear > 9999) {
            iYear = 9999
            iMonth = 12
        }
        restde.year = iYear.toString()


        /* 월별확인 */

        /* 1월 */
        iMonth = 1
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_1", restdeManageService!!.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_1", restdeManageService.selectAdministMonthRestde(restde))


        /* 2월 */
        iMonth = 2
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_2", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_2", restdeManageService.selectAdministMonthRestde(restde))


        /* 3월 */
        iMonth = 3
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_3", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_3", restdeManageService.selectAdministMonthRestde(restde))


        /* 4월 */
        iMonth = 4
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_4", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_4", restdeManageService.selectAdministMonthRestde(restde))


        /* 5월 */
        iMonth = 5
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_5", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_5", restdeManageService.selectAdministMonthRestde(restde))


        /* 6월 */
        iMonth = 6
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_6", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_6", restdeManageService.selectAdministMonthRestde(restde))


        /* 7월 */
        iMonth = 7
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_7", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_7", restdeManageService.selectAdministMonthRestde(restde))


        /* 8월 */
        iMonth = 8
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_8", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_8", restdeManageService.selectAdministMonthRestde(restde))


        /* 9월 */
        iMonth = 9
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_9", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_9", restdeManageService.selectAdministMonthRestde(restde))


        /* 10월 */
        iMonth = 10
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_10", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_10", restdeManageService.selectAdministMonthRestde(restde))


        /* 11월 */
        iMonth = 11
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)
        model.addAttribute("resultList_11", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_11", restdeManageService.selectAdministMonthRestde(restde))


        /* 12월 */
        iMonth = 12
        restde.month = iMonth.toString()
        cal.set(iYear, iMonth - 1, 1)
        restde.startWeekMonth = cal.get(Calendar.DAY_OF_WEEK)
        restde.lastDayMonth = cal.getActualMaximum(Calendar.DATE)

        model.addAttribute("resultList_12", restdeManageService.selectAdministRestdePopup(restde))
        model.addAttribute("RestdeList_12", restdeManageService.selectAdministMonthRestde(restde))

        return "/cmm/sym/cal/EgovAdministYearCalendar"
    }


    /**
     * 휴일을 삭제한다.
     * @param loginVO
     * @param restde
     * @param model
     * @return "forward:/sym/cal/EgovRestdeList.do"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovRestdeRemove.do"])
    @Throws(Exception::class)
    fun deleteRestde(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        restde: Restde?,
        model: ModelMap?
    ): String {
        restdeManageService!!.deleteRestde(restde)
        return "forward:/sym/cal/EgovRestdeList.do"
    }


    /**
     * 휴일 세부내역을 조회한다.
     * @param loginVO
     * @param restde
     * @param model
     * @return "/cmm/sym/cal/EgovRestdeDetail"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovRestdeDetail.do"])
    @Throws(Exception::class)
    fun selectRestdeDetail(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        restde: Restde?,
        model: ModelMap
    ): String {
        val vo = restdeManageService!!.selectRestdeDetail(restde)
        model.addAttribute("result", vo)

        return "/cmm/sym/cal/EgovRestdeDetail"
    }

    /**
     * 휴일 리스트를 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "/cmm/sym/cal/EgovRestdeList"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovRestdeList.do"])
    @Throws(Exception::class)
    fun selectRestdeList(
        @ModelAttribute("loginVO") loginVO: LoginVO?,
        @ModelAttribute("searchVO") searchVO: RestdeVO,
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

        model.addAttribute("resultList", restdeManageService!!.selectRestdeList(searchVO))

        val totCnt = restdeManageService.selectRestdeListTotCnt(searchVO)
        paginationInfo.setTotalRecordCount(totCnt)
        model.addAttribute("paginationInfo", paginationInfo)

        return "/cmm/sym/cal/EgovRestdeList"
    }

    /**
     * 휴일을 수정한다.
     * @param loginVO
     * @param restde
     * @param bindingResult
     * @param commandMap
     * @param model
     * @return "/cmm/sym/cal/EgovRestdeModify"
     * @throws Exception
     */
    @RequestMapping(value = ["/sym/cal/EgovRestdeModify.do"])
    @Throws(Exception::class)
    fun updateRestde(
        @ModelAttribute("loginVO") loginVO: LoginVO,
        @ModelAttribute("restde") restde: Restde,
        bindingResult: BindingResult,
        @RequestParam commandMap: MutableMap<String?, Any?>,
        model: ModelMap
    ): String {
        val sCmd: String = (if (commandMap.get("cmd") == null) "" else commandMap.get("cmd") as kotlin.String?)!!
        if (sCmd == "") {
            val vo = restdeManageService!!.selectRestdeDetail(restde)
            model.addAttribute("restde", vo)

            val CodeVO = ComDefaultCodeVO()
            CodeVO.codeId = "COM017"
            model.addAttribute("restdeCode", cmmUseService!!.selectCmmCodeDetail(CodeVO))

            return "/cmm/sym/cal/EgovRestdeModify"
        } else if (sCmd == "Modify") {
            beanValidator!!.validate(restde, bindingResult)
            if (bindingResult.hasErrors()) {
                val CodeVO = ComDefaultCodeVO()
                CodeVO.codeId = "COM017"
                model.addAttribute("restdeCode", cmmUseService!!.selectCmmCodeDetail(CodeVO))

                return "/cmm/sym/cal/EgovRestdeModify"
            }

            restde.lastUpdusrId = loginVO.uniqId
            restdeManageService!!.updateRestde(restde)
            return "forward:/sym/cal/EgovRestdeList.do"
        } else {
            return "forward:/sym/cal/EgovRestdeList.do"
        }
    }
}