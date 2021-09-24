package br.ufscar.dsw1.filter;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = { "*.jsp" })
public class JSPFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        String path = req.getRequestURI();
        String contextPath = req.getServletContext().getContextPath() + "/";

        if (path.equals(contextPath)) {
            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/post/dashboard").forward(request, response);
        }
    }
}
