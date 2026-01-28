package egovframework.com.cmm

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.context.ServletContextAware
import javax.servlet.ServletContext

/**
 * ImagePaginationRenderer.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class RenewPaginationRenderer : AbstractPaginationRenderer(), ServletContextAware {
    private var servletContext: ServletContext? = null

    fun initVariables() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getContextPath={}", servletContext!!.getContextPath())
        }

        firstPageLabel =
            "<li class=\"btn\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"first\">처음</a></li>"
        previousPageLabel =
            "<li class=\"btn\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"btn prev\">이전</a></li>"
        currentPageLabel = "<li><strong>{0}</strong></li>"
        otherPageLabel = "<li><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a></li>"
        nextPageLabel =
            "<li class=\"btn\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"btn next\">다음</a></li>"
        lastPageLabel =
            "<li class=\"btn\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"btn last\">마지막</a></li>"
    }

    override fun setServletContext(servletContext: ServletContext) {
        this.servletContext = servletContext
        initVariables()
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ImagePaginationRenderer::class.java)
    }
}
