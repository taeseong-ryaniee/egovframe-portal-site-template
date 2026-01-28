package egovframework.com.cmm

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @Class Name : EgovComExcepHndlr.java
 * @Description : 공통서비스의 exception 처리 클래스
 * @Modification Information
 *
 * 수정일       수정자         수정내용
 * -------        -------     -------------------
 * 2009. 3. 13.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 13.
 * @version
 * @see
 */
class EgovComExcepHndlr : ExceptionHandler {
    /**
     * 발생된 Exception을 처리한다.
     */
    override fun occur(ex: Exception?, packageName: String?) {
        LOGGER.debug("[HANDLER][PACKAGE]::: {}", packageName)
        LOGGER.debug("[HANDLER][Exception]:::{}", ex)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovComExcepHndlr::class.java)
    }
}
