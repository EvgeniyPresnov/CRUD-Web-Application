package ru.home.webapp.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * This class organizes the encoding before calling the servlet.
 * This is useful because the browsers typically do not set a character
 * encoding even if specified in the HTML page or form
 *
 * @author Evgeniy Presnov
 */
@WebFilter("/")
public final class EncodingFilter implements Filter {
    private static final String ENCODING = "UTF-8";

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {

    }
}
