package org.samarskii.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter",
        servletNames = {"index", "post"},
        initParams = {
        @WebInitParam(name = "encoding",value = "UTF8")
})
public class EncodingFilter implements Filter {

    String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init filter");
        encoding = filterConfig.getInitParameter("encoding");
        System.out.println("Encoding: " + encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter URI: " + ((HttpServletRequest)servletRequest).getRequestURI());
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding(encoding);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("end filter");
    }

    @Override
    public void destroy() {

    }
}
