package egovframework.com.cmm.filter

import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

class PathRewriteFilter : Filter {
    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig?) {
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        val req = request as HttpServletRequest
        val contextPath = req.getContextPath()
        val uri = req.getRequestURI()
        val prefix = contextPath + "/pst_webapp"
        if (uri.startsWith(prefix)) {
            var forwardTo = uri.substring(prefix.length)
            if (forwardTo.isEmpty()) forwardTo = "/"
            val rd = request.getRequestDispatcher(forwardTo)
            rd.forward(request, response)
            return
        }
        chain.doFilter(request, response)
    }

    override fun destroy() {}
}

