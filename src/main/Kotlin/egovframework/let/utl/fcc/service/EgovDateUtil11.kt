package egovframework.let.utl.fcc.service

import com.ibm.icu.util.ChineseCalendar
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.security.SecureRandom
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Date 에 대한 Util 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.02.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovDateUtil {
    /**
     * 연도를 입력 받아 해당 연도 2월의 말일(일수)를 문자열로 반환한다.
     *
     * @param year
     * @return 해당 연도 2월의 말일(일수)
     */
    fun leapYear(year: Int): String {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return "29"
        }

        return "28"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovDateUtil::class.java)

        /**
         *
         * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년, 월, 일을
         * 증감한다. 년, 월, 일은 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
         *
         * <pre>
         * DateUtil.addYearMonthDay("19810828", 0, 0, 19)  = "19810916"
         * DateUtil.addYearMonthDay("20060228", 0, 0, -10) = "20060218"
         * DateUtil.addYearMonthDay("20060228", 0, 0, 10)  = "20060310"
         * DateUtil.addYearMonthDay("20060228", 0, 0, 32)  = "20060401"
         * DateUtil.addYearMonthDay("20050331", 0, -1, 0)  = "20050228"
         * DateUtil.addYearMonthDay("20050301", 0, 2, 30)  = "20050531"
         * DateUtil.addYearMonthDay("20050301", 1, 2, 30)  = "20060531"
         * DateUtil.addYearMonthDay("20040301", 2, 0, 0)   = "20060301"
         * DateUtil.addYearMonthDay("20040229", 2, 0, 0)   = "20060228"
         * DateUtil.addYearMonthDay("20040229", 2, 0, 1)   = "20060301"
        </pre> *
         *
         * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @param  year 가감할 년. 0이 입력될 경우 가감이 없다
         * @param  month 가감할 월. 0이 입력될 경우 가감이 없다
         * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
         * @return  yyyyMMdd 형식의 날짜 문자열
         * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
         * 입력 값이 `null`인 경우.
         */
        fun addYearMonthDay(sDate: String, year: Int, month: Int, day: Int): String {
            val dateStr: String = validChkDate(sDate)

            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            try {
                cal.setTime(sdf.parse(dateStr))
            } catch (e: ParseException) {
                throw IllegalArgumentException("Invalid date format: " + dateStr)
            }

            if (year != 0) cal.add(Calendar.YEAR, year)
            if (month != 0) cal.add(Calendar.MONTH, month)
            if (day != 0) cal.add(Calendar.DATE, day)
            return sdf.format(cal.getTime())
        }

        /**
         *
         * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년을
         * 증감한다. `year`는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
         *
         * <pre>
         * DateUtil.addYear("20000201", 62)  = "20620201"
         * DateUtil.addYear("20620201", -62) = "20000201"
         * DateUtil.addYear("20040229", 2)   = "20060228"
         * DateUtil.addYear("20060228", -2)  = "20040228"
         * DateUtil.addYear("19000101", 200) = "21000101"
        </pre> *
         *
         * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @param  year 가감할 년. 0이 입력될 경우 가감이 없다
         * @return  yyyyMMdd 형식의 날짜 문자열
         * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
         * 입력 값이 `null`인 경우.
         */
        fun addYear(dateStr: String, year: Int): String {
            return addYearMonthDay(dateStr, year, 0, 0)
        }

        /**
         *
         * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 월을
         * 증감한다. `month`는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
         *
         * <pre>
         * DateUtil.addMonth("20010201", 12)  = "20020201"
         * DateUtil.addMonth("19800229", 12)  = "19810228"
         * DateUtil.addMonth("20040229", 12)  = "20050228"
         * DateUtil.addMonth("20050228", -12) = "20040228"
         * DateUtil.addMonth("20060131", 1)   = "20060228"
         * DateUtil.addMonth("20060228", -1)  = "20060128"
        </pre> *
         *
         * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @param  month 가감할 월. 0이 입력될 경우 가감이 없다
         * @return  yyyyMMdd 형식의 날짜 문자열
         * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
         * 입력 값이 `null`인 경우.
         */
        fun addMonth(dateStr: String, month: Int): String {
            return addYearMonthDay(dateStr, 0, month, 0)
        }

        /**
         *
         * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 일(day)를
         * 증감한다. `day`는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
         * <br></br><br></br>
         * 위에 정의된 addDays 메서드는 사용자가 ParseException을 반드시 처리해야 하는 불편함이
         * 있기 때문에 추가된 메서드이다.
         *
         * <pre>
         * DateUtil.addDay("19991201", 62) = "20000201"
         * DateUtil.addDay("20000201", -62) = "19991201"
         * DateUtil.addDay("20050831", 3) = "20050903"
         * DateUtil.addDay("20050831", 3) = "20050903"
         * // 2006년 6월 31일은 실제로 존재하지 않는 날짜이다 -> 20060701로 간주된다
         * DateUtil.addDay("20060631", 1) = "20060702"
        </pre> *
         *
         * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
         * @return  yyyyMMdd 형식의 날짜 문자열
         * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
         * 입력 값이 `null`인 경우.
         */
        fun addDay(dateStr: String, day: Int): String {
            return addYearMonthDay(dateStr, 0, 0, day)
        }

        /**
         *
         * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 `dateStr1`과 `
         * dateStr2` 사이의 일 수를 구한다.<br></br>
         * `dateStr2`가 `dateStr1` 보다 과거 날짜일 경우에는
         * 음수를 반환한다. 동일한 경우에는 0을 반환한다.
         *
         * <pre>
         * DateUtil.getDaysDiff("20060228","20060310") = 10
         * DateUtil.getDaysDiff("20060101","20070101") = 365
         * DateUtil.getDaysDiff("19990228","19990131") = -28
         * DateUtil.getDaysDiff("20060801","20060802") = 1
         * DateUtil.getDaysDiff("20060801","20060801") = 0
        </pre> *
         *
         * @param  dateStr1 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @param  dateStr2 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @return  일 수 차이.
         * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
         * 입력 값이 `null`인 경우.
         */
        @JvmStatic
        fun getDaysDiff(sDate1: String, sDate2: String): Int {
            val dateStr1: String = validChkDate(sDate1)
            val dateStr2: String = validChkDate(sDate2)

            require(!(!checkDate(sDate1) || !checkDate(sDate2))) { "Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2 }
            val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())

            var date1: Date? = null
            var date2: Date? = null
            try {
                date1 = sdf.parse(dateStr1)
                date2 = sdf.parse(dateStr2)
            } catch (e: ParseException) {
                throw IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2)
            }
            val days1 = ((date1.getTime() / 3600000) / 24).toInt()
            val days2 = ((date2.getTime() / 3600000) / 24).toInt()

            return days2 - days1
        }

        /**
         *
         * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 유효한 날짜인지 검사.
         *
         * <pre>
         * DateUtil.checkDate("1999-02-35") = false
         * DateUtil.checkDate("2000-13-31") = false
         * DateUtil.checkDate("2006-11-31") = false
         * DateUtil.checkDate("2006-2-28")  = false
         * DateUtil.checkDate("2006-2-8")   = false
         * DateUtil.checkDate("20060228")   = true
         * DateUtil.checkDate("2006-02-28") = true
        </pre> *
         *
         * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
         * @return  유효한 날짜인지 여부
         */
        fun checkDate(sDate: String): Boolean {
            val dateStr: String = validChkDate(sDate)

            val year = dateStr.substring(0, 4)
            val month = dateStr.substring(4, 6)
            val day = dateStr.substring(6)

            return checkDate(year, month, day)
        }

        /**
         *
         * 입력한 년, 월, 일이 유효한지 검사.
         *
         * @param  year 연도
         * @param  month 월
         * @param  day 일
         * @return  유효한 날짜인지 여부
         */
        fun checkDate(year: String?, month: String?, day: String?): Boolean {
            try {
                val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

                val result = formatter.parse(year + "." + month + "." + day)
                val resultStr = formatter.format(result)
                if (resultStr.equals(year + "." + month + "." + day, ignoreCase = true)) return true
                else return false
            } catch (e: ParseException) {
                return false
            }
        }

        /**
         * 날짜형태의 String의 날짜 포맷 및 TimeZone을 변경해 주는 메서드
         *
         * @param  strSource       바꿀 날짜 String
         * @param  fromDateFormat  기존의 날짜 형태
         * @param  toDateFormat    원하는 날짜 형태
         * @param  strTimeZone     변경할 TimeZone(""이면 변경 안함)
         * @return  소스 String의 날짜 포맷을 변경한 String
         */
        fun convertDate(
            strSource: String?,
            fromDateFormat: String?,
            toDateFormat: String?,
            strTimeZone: String?
        ): String {
            var simpledateformat: SimpleDateFormat? = null
            var date: Date? = null
            var _fromDateFormat = ""
            var _toDateFormat = ""

            if (EgovStringUtil.isNullToString(strSource).trim { it <= ' ' } == "") {
                return ""
            }
            if (EgovStringUtil.isNullToString(fromDateFormat).trim { it <= ' ' } == "") _fromDateFormat =
                "yyyyMMddHHmmss" // default값

            if (EgovStringUtil.isNullToString(toDateFormat).trim { it <= ' ' } == "") _toDateFormat =
                "yyyy-MM-dd HH:mm:ss" // default값


            try {
                simpledateformat = SimpleDateFormat(_fromDateFormat, Locale.getDefault())
                date = simpledateformat.parse(strSource)
                if (EgovStringUtil.isNullToString(strTimeZone).trim { it <= ' ' } != "") {
                    simpledateformat.setTimeZone(TimeZone.getTimeZone(strTimeZone))
                }
                simpledateformat = SimpleDateFormat(_toDateFormat, Locale.getDefault())
            } catch (exception: ParseException) {
                LOGGER.debug("{}", exception)
            }
            if (simpledateformat != null && simpledateformat.format(date) != null) {
                return simpledateformat.format(date)
            } else {
                return ""
            }
        }

        /**
         * yyyyMMdd 형식의 날짜문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다<br></br>
         * <pre>
         * ex) 20030405, ch(.) -> 2003.04.05
         * ex) 200304, ch(.) -> 2003.04
         * ex) 20040101,ch(/) --> 2004/01/01 로 리턴
        </pre> *
         *
         * @param date yyyyMMdd 형식의 날짜문자열
         * @param ch 구분자
         * @return 변환된 문자열
         */
        fun formatDate(sDate: String, ch: String): String {
            val dateStr: String = validChkDate(sDate)

            val str = dateStr.trim { it <= ' ' }
            var yyyy = ""
            var mm = ""
            var dd = ""

            if (str.length == 8) {
                yyyy = str.substring(0, 4)
                if (yyyy == "0000") return ""

                mm = str.substring(4, 6)
                if (mm == "00") return yyyy

                dd = str.substring(6, 8)
                if (dd == "00") return yyyy + ch + mm

                return yyyy + ch + mm + ch + dd
            } else if (str.length == 6) {
                yyyy = str.substring(0, 4)
                if (yyyy == "0000") return ""

                mm = str.substring(4, 6)
                if (mm == "00") return yyyy

                return yyyy + ch + mm
            } else if (str.length == 4) {
                yyyy = str.substring(0, 4)
                if (yyyy == "0000") return ""
                else return yyyy
            } else return ""
        }

        /**
         * HH24MISS 형식의 시간문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다 <br></br>
         * <pre>
         * ex) 151241, ch(/) -> 15/12/31
        </pre> *
         *
         * @param str HH24MISS 형식의 시간문자열
         * @param ch 구분자
         * @return 변환된 문자열
         */
        fun formatTime(sTime: String, ch: String): String {
            val timeStr: String = validChkTime(sTime)
            return timeStr.substring(0, 2) + ch + timeStr.substring(2, 4) + ch + timeStr.substring(4, 6)
        }

        /**
         *
         * 입력받은 연도가 윤년인지 아닌지 검사한다.
         *
         * <pre>
         * DateUtil.isLeapYear(2004) = false
         * DateUtil.isLeapYear(2005) = true
         * DateUtil.isLeapYear(2006) = true
        </pre> *
         *
         * @param  year 연도
         * @return  윤년 여부
         */
        fun isLeapYear(year: Int): Boolean {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                return false
            }
            return true
        }

        @JvmStatic
        val today: String
            /**
             * 현재(한국기준) 날짜정보를 얻는다.                     <BR></BR>
             * 표기법은 yyyy-mm-dd                                  <BR></BR>
             * @return  String      yyyymmdd형태의 현재 한국시간.   <BR></BR>
             */
            get() = getCurrentDate("")

        /**
         * 현재(한국기준) 날짜정보를 얻는다.                     <BR></BR>
         * 표기법은 yyyy-mm-dd                                  <BR></BR>
         * @return  String      yyyymmdd형태의 현재 한국시간.   <BR></BR>
         */
        fun getCurrentDate(dateType: String): String {
            val aCalendar = Calendar.getInstance()

            val year = aCalendar.get(Calendar.YEAR)
            val month = aCalendar.get(Calendar.MONTH) + 1
            val date = aCalendar.get(Calendar.DATE)
            var strDate = (year.toString() + (if (month < 10) "0" + month.toString() else month.toString())
                    + (if (date < 10) "0" + date.toString() else date.toString()))

            if ("" != dateType) strDate = convertDate(strDate, "yyyyMMdd", dateType)

            return strDate
        }

        /**
         * 날짜형태의 String의 날짜 포맷만을 변경해 주는 메서드
         * @param sDate 날짜
         * @param sTime 시간
         * @param sFormatStr 포멧 스트링 문자열
         * @return 지정한 날짜/시간을 지정한 포맷으로 출력
         * @See Letter  Date or Time Component  Presentation  Examples
         * G  Era designator  Text  AD
         * y  Year  Year  1996; 96
         * M  Month in year  Month  July; Jul; 07
         * w  Week in year  Number  27
         * W  Week in month  Number  2
         * D  Day in year  Number  189
         * d  Day in month  Number  10
         * F  Day of week in month  Number  2
         * E  Day in week  Text  Tuesday; Tue
         * a  Am/pm marker  Text  PM
         * H  Hour in day (0-23)  Number  0
         * k  Hour in day (1-24)  Number  24
         * K  Hour in am/pm (0-11)  Number  0
         * h  Hour in am/pm (1-12)  Number  12
         * m  Minute in hour  Number  30
         * s  Second in minute  Number  55
         * S  Millisecond  Number  978
         * z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00
         * Z  Time zone  RFC 822 time zone  -0800
         *
         *
         *
         * Date and Time Pattern  Result
         * "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT
         * "EEE, MMM d, ''yy"  Wed, Jul 4, '01
         * "h:mm a"  12:08 PM
         * "hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time
         * "K:mm a, z"  0:08 PM, PDT
         * "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM
         * "EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700
         * "yyMMddHHmmssZ"  010704120856-0700
         */
        fun convertDate(sDate: String, sTime: String, sFormatStr: String): String {
            val dateStr: String = validChkDate(sDate)
            val timeStr: String = validChkTime(sTime)

            var cal: Calendar? = null
            cal = Calendar.getInstance()

            cal.set(Calendar.YEAR, dateStr.substring(0, 4).toInt())
            cal.set(Calendar.MONTH, dateStr.substring(4, 6).toInt() - 1)
            cal.set(Calendar.DAY_OF_MONTH, dateStr.substring(6, 8).toInt())
            cal.set(Calendar.HOUR_OF_DAY, timeStr.substring(0, 2).toInt())
            cal.set(Calendar.MINUTE, timeStr.substring(2, 4).toInt())

            val sdf = SimpleDateFormat(sFormatStr, Locale.ENGLISH)

            return sdf.format(cal.getTime())
        }

        /**
         * 입력받은 일자 사이의 임의의 일자를 반환
         * @param sDate1 시작일자
         * @param sDate2 종료일자
         * @return 임의일자
         */
        fun getRandomDate(sDate1: String, sDate2: String): String {
            val dateStr1: String = validChkDate(sDate1)
            val dateStr2: String = validChkDate(sDate2)

            var randomDate: String = null

            val sYear: Int
            val sMonth: Int
            val sDay: Int
            val eYear: Int
            val eMonth: Int
            val eDay: Int

            sYear = dateStr1.substring(0, 4).toInt()
            sMonth = dateStr1.substring(4, 6).toInt()
            sDay = dateStr1.substring(6, 8).toInt()

            eYear = dateStr2.substring(0, 4).toInt()
            eMonth = dateStr2.substring(4, 6).toInt()
            eDay = dateStr2.substring(6, 8).toInt()

            val beginDate = GregorianCalendar(sYear, sMonth - 1, sDay, 0, 0)
            val endDate = GregorianCalendar(eYear, eMonth - 1, eDay, 23, 59)

            require(endDate.getTimeInMillis() >= beginDate.getTimeInMillis()) { "Invalid input date : " + sDate1 + "~" + sDate2 }

            val r = SecureRandom()

            val rand =
                ((r.nextLong() ushr 1) % (endDate.getTimeInMillis() - beginDate.getTimeInMillis() + 1)) + beginDate.getTimeInMillis()

            val cal = GregorianCalendar()
            //SimpleDateFormat calformat = new SimpleDateFormat("yyyy-MM-dd");
            val calformat = SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
            cal.setTimeInMillis(rand)
            randomDate = calformat.format(cal.getTime())

            // 랜덤문자열를 리턴
            return randomDate
        }

        /**
         * 입력받은 양력일자를 변환하여 음력일자로 반환
         * @param sDate 양력일자
         * @return 음력일자
         */
        fun toLunar(sDate: String): MutableMap<String?, String?> {
            val dateStr: String = validChkDate(sDate)

            val hm: MutableMap<String?, String?> = HashMap<String?, String?>()
            hm.put("day", "")
            hm.put("leap", "0")

            if (dateStr.length != 8) {
                return hm
            }

            val cal: Calendar
            val lcal: ChineseCalendar?

            cal = Calendar.getInstance()
            lcal = ChineseCalendar()

            cal.set(Calendar.YEAR, dateStr.substring(0, 4).toInt())
            cal.set(Calendar.MONTH, dateStr.substring(4, 6).toInt() - 1)
            cal.set(Calendar.DAY_OF_MONTH, dateStr.substring(6, 8).toInt())

            lcal.setTimeInMillis(cal.getTimeInMillis())

            val year = (lcal.get(ChineseCalendar.EXTENDED_YEAR) - 2637).toString()
            val month = (lcal.get(ChineseCalendar.MONTH) + 1).toString()
            val day = lcal.get(ChineseCalendar.DAY_OF_MONTH).toString()
            val leap = lcal.get(ChineseCalendar.IS_LEAP_MONTH).toString()

            val pad4Str = "0000"
            val pad2Str = "00"

            val retYear = (pad4Str + year).substring(year.length)
            val retMonth = (pad2Str + month).substring(month.length)
            val retDay = (pad2Str + day).substring(day.length)

            val SDay = retYear + retMonth + retDay

            hm.put("day", SDay)
            hm.put("leap", leap)

            return hm
        }

        /**
         * 입력받은 음력일자를 변환하여 양력일자로 반환
         * @param sDate 음력일자
         * @param iLeapMonth 음력윤달여부(IS_LEAP_MONTH)
         * @return 양력일자
         */
        fun toSolar(sDate: String, iLeapMonth: Int): String {
            val dateStr: String = validChkDate(sDate)

            val cal: Calendar
            val lcal: ChineseCalendar?

            cal = Calendar.getInstance()
            lcal = ChineseCalendar()

            lcal.set(ChineseCalendar.EXTENDED_YEAR, dateStr.substring(0, 4).toInt() + 2637)
            lcal.set(ChineseCalendar.MONTH, dateStr.substring(4, 6).toInt() - 1)
            lcal.set(ChineseCalendar.DAY_OF_MONTH, dateStr.substring(6, 8).toInt())
            lcal.set(ChineseCalendar.IS_LEAP_MONTH, iLeapMonth)

            cal.setTimeInMillis(lcal.getTimeInMillis())

            val year = cal.get(Calendar.YEAR).toString()
            val month = (cal.get(Calendar.MONTH) + 1).toString()
            val day = cal.get(Calendar.DAY_OF_MONTH).toString()

            val pad4Str = "0000"
            val pad2Str = "00"

            val retYear = (pad4Str + year).substring(year.length)
            val retMonth = (pad2Str + month).substring(month.length)
            val retDay = (pad2Str + day).substring(day.length)

            return retYear + retMonth + retDay
        }

        /**
         * 입력받은 요일의 영문명을 국문명의 요일로 반환
         * @param sWeek 영문 요일명
         * @return 국문 요일명
         */
        fun convertWeek(sWeek: String): String? {
            var retStr: String? = null

            if (sWeek == "SUN") {
                retStr = "일요일"
            } else if (sWeek == "MON") {
                retStr = "월요일"
            } else if (sWeek == "TUE") {
                retStr = "화요일"
            } else if (sWeek == "WED") {
                retStr = "수요일"
            } else if (sWeek == "THR") {
                retStr = "목요일"
            } else if (sWeek == "FRI") {
                retStr = "금요일"
            } else if (sWeek == "SAT") {
                retStr = "토요일"
            }

            return retStr
        }

        /**
         * 입력일자의 유효 여부를 확인
         * @param sDate 일자
         * @return 유효 여부
         */
        fun validDate(sDate: String): Boolean {
            val dateStr: String = validChkDate(sDate)

            val cal: Calendar
            var ret = false

            cal = Calendar.getInstance()

            cal.set(Calendar.YEAR, dateStr.substring(0, 4).toInt())
            cal.set(Calendar.MONTH, dateStr.substring(4, 6).toInt() - 1)
            cal.set(Calendar.DAY_OF_MONTH, dateStr.substring(6, 8).toInt())

            val year = cal.get(Calendar.YEAR).toString()
            val month = (cal.get(Calendar.MONTH) + 1).toString()
            val day = cal.get(Calendar.DAY_OF_MONTH).toString()

            val pad4Str = "0000"
            val pad2Str = "00"

            val retYear = (pad4Str + year).substring(year.length)
            val retMonth = (pad2Str + month).substring(month.length)
            val retDay = (pad2Str + day).substring(day.length)

            val retYMD = retYear + retMonth + retDay

            if (sDate == retYMD) {
                ret = true
            }

            return ret
        }

        /**
         * 입력일자, 요일의 유효 여부를 확인
         * @param     sDate 일자
         * @param     sWeek 요일 (DAY_OF_WEEK)
         * @return    유효 여부
         */
        fun validDate(sDate: String, sWeek: Int): Boolean {
            val dateStr: String = validChkDate(sDate)

            val cal: Calendar
            var ret = false

            cal = Calendar.getInstance()

            cal.set(Calendar.YEAR, dateStr.substring(0, 4).toInt())
            cal.set(Calendar.MONTH, dateStr.substring(4, 6).toInt() - 1)
            cal.set(Calendar.DAY_OF_MONTH, dateStr.substring(6, 8).toInt())

            val Week = cal.get(Calendar.DAY_OF_WEEK)

            if (validDate(sDate)) {
                if (sWeek == Week) {
                    ret = true
                }
            }

            return ret
        }

        /**
         * 입력시간의 유효 여부를 확인
         * @param     sTime 입력시간
         * @return    유효 여부
         */
        fun validTime(sTime: String): Boolean {
            val timeStr: String = validChkTime(sTime)

            val cal: Calendar
            var ret = false

            cal = Calendar.getInstance()

            cal.set(Calendar.HOUR_OF_DAY, timeStr.substring(0, 2).toInt())
            cal.set(Calendar.MINUTE, timeStr.substring(2, 4).toInt())

            val HH = cal.get(Calendar.HOUR_OF_DAY).toString()
            val MM = cal.get(Calendar.MINUTE).toString()

            val pad2Str = "00"

            val retHH = (pad2Str + HH).substring(HH.length)
            val retMM = (pad2Str + MM).substring(MM.length)

            val retTime = retHH + retMM

            if (sTime == retTime) {
                ret = true
            }

            return ret
        }

        /**
         * 입력된 일자에 연, 월, 일을 가감한 날짜의 요일을 반환
         * @param sDate 날짜
         * @param year 연
         * @param month 월
         * @param day 일
         * @return 계산된 일자의 요일(DAY_OF_WEEK)
         */
        fun addYMDtoWeek(sDate: String, year: Int, month: Int, day: Int): String {
            var dateStr: String = validChkDate(sDate)

            dateStr = addYearMonthDay(dateStr, year, month, day)

            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
            try {
                cal.setTime(sdf.parse(dateStr))
            } catch (e: ParseException) {
                throw IllegalArgumentException("Invalid date format: " + dateStr)
            }

            val rsdf = SimpleDateFormat("E", Locale.ENGLISH)

            return rsdf.format(cal.getTime())
        }

        /**
         * 입력된 일자에 연, 월, 일, 시간, 분을 가감한 날짜, 시간을 포멧스트링 형식으로 반환
         * @param sDate 날짜
         * @param sTime 시간
         * @param year 연
         * @param month 월
         * @param day 일
         * @param hour 시간
         * @param minute 분
         * @param formatStr 포멧스트링
         * @return
         */
        fun addYMDtoDayTime(
            sDate: String,
            sTime: String,
            year: Int,
            month: Int,
            day: Int,
            hour: Int,
            minute: Int,
            formatStr: String
        ): String {
            var dateStr: String = validChkDate(sDate)
            val timeStr: String = validChkTime(sTime)

            dateStr = addYearMonthDay(dateStr, year, month, day)

            dateStr = convertDate(dateStr, timeStr, "yyyyMMddHHmm")

            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyyMMddHHmm", Locale.ENGLISH)

            try {
                cal.setTime(sdf.parse(dateStr))
            } catch (e: ParseException) {
                throw IllegalArgumentException("Invalid date format: " + dateStr)
            }

            if (hour != 0) {
                cal.add(Calendar.HOUR, hour)
            }

            if (minute != 0) {
                cal.add(Calendar.MINUTE, minute)
            }

            val rsdf = SimpleDateFormat(formatStr, Locale.ENGLISH)

            return rsdf.format(cal.getTime())
        }

        /**
         * 입력된 일자를 int 형으로 반환
         * @param sDate 일자
         * @return int(일자)
         */
        fun datetoInt(sDate: String): Int {
            return convertDate(sDate, "0000", "yyyyMMdd").toInt()
        }

        /**
         * 입력된 시간을 int 형으로 반환
         * @param sTime 시간
         * @return int(시간)
         */
        fun timetoInt(sTime: String): Int {
            return convertDate("00000101", sTime, "HHmm").toInt()
        }

        /**
         * 입력된 일자 문자열을 확인하고 8자리로 리턴
         * @param sDate
         * @return
         */
        fun validChkDate(dateStr: String): String {
            var _dateStr = dateStr

            require(!(dateStr == null || !(dateStr.trim { it <= ' ' }.length == 8 || dateStr.trim { it <= ' ' }.length == 10))) { "Invalid date format: " + dateStr }
            if (dateStr.length == 10) {
                _dateStr = EgovStringUtil.removeMinusChar(dateStr)
            }
            return _dateStr
        }

        /**
         * 입력된 일자 문자열을 확인하고 8자리로 리턴
         * @param sDate
         * @return
         */
        fun validChkTime(timeStr: String): String {
            var _timeStr = timeStr

            if (_timeStr.length == 5) {
                _timeStr = EgovStringUtil.remove(_timeStr, ':')
            }
            require(!(_timeStr == null || _timeStr.trim { it <= ' ' }.length != 4)) { "Invalid time format: " + _timeStr }

            return _timeStr
        }
    }
}