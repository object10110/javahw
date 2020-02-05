package org.samarskii.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authorizeFilter",
        servletNames = {"admin", "delete"})
public class AuthorizeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        boolean auth = false;
        Object auth1 = ((HttpServletRequest) servletRequest).getSession().getAttribute("auth");
        try {
            auth = Boolean.parseBoolean(auth1.toString());
        } catch (Exception ex) {
        }
        if (auth) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
