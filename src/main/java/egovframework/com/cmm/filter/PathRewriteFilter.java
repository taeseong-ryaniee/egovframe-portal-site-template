package egovframework.com.cmm.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PathRewriteFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        String prefix = contextPath + "/pst_webapp";
        if (uri.startsWith(prefix)) {
            String forwardTo = uri.substring(prefix.length());
            if (forwardTo.isEmpty()) forwardTo = "/";
            RequestDispatcher rd = request.getRequestDispatcher(forwardTo);
            rd.forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}

