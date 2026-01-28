package egovframework.com.cmm.util

import java.util.logging.Level
import java.util.logging.Logger

/**
 * Utility class  to support to logging information
 * @author Vincent Han
 * @since 2014.09.18
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
object EgovBasicLogger {
    private val IGNORE_INFO_LEVEL: Level = Level.OFF
    private val DEBUG_INFO_LEVEL: Level = Level.FINEST
    private val INFO_INFO_LEVEL: Level = Level.INFO

    private val ignoreLogger: Logger = Logger.getLogger("ignore")
    private val debugLogger: Logger = Logger.getLogger("debug")
    private val infoLogger: Logger = Logger.getLogger("info")

    /**
     * 기록이나 처리가 불필요한 경우 사용.
     * @param message
     * @param exception
     */
    /**
     * 기록이나 처리가 불필요한 경우 사용.
     * @param message
     * @param exception
     */
    @JvmOverloads
    fun ignore(message: String?, exception: Exception? = null) {
        if (exception == null) {
            ignoreLogger.log(IGNORE_INFO_LEVEL, message)
        } else {
            ignoreLogger.log(IGNORE_INFO_LEVEL, message, exception)
        }
    }

    /**
     * 디버그 정보를 기록하는 경우 사용.
     * @param message
     * @param exception
     */
    /**
     * 디버그 정보를 기록하는 경우 사용.
     * @param message
     * @param exception
     */
    @JvmOverloads
    fun debug(message: String?, exception: Exception? = null) {
        if (exception == null) {
            debugLogger.log(DEBUG_INFO_LEVEL, message)
        } else {
            debugLogger.log(DEBUG_INFO_LEVEL, message, exception)
        }
    }

    /**
     * 일반적이 정보를 기록하는 경우 사용.
     * @param message
     * @param exception
     */
    fun info(message: String?) {
        infoLogger.log(INFO_INFO_LEVEL, message)
    }
}
