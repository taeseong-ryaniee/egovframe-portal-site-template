package egovframework.com.cmm

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class EgovComOthersExcepHndlr : ExceptionHandler {
    override fun occur(exception: Exception?, packageName: String?) {
        LOGGER.error(packageName, exception)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovComOthersExcepHndlr::class.java)
    }
}
