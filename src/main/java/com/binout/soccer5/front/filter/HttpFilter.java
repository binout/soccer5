package com.binout.soccer5.front.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class HttpFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {
        config = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (!(req instanceof HttpServletRequest)) {
            throw new ServletException("This filter can only process HttpServletRequest requests");
        }
        doHttpFilter((HttpServletRequest) req, resp, chain);
    }

    protected abstract void doHttpFilter(HttpServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException;
}
