/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit
 */
public class AuthenticationFilter implements Filter {

    public static final String AUTH_KEY = "app.user.name";
    private FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) req).getSession().getAttribute(AUTH_KEY) == null) {
            ((HttpServletResponse) resp).sendRedirect("index.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }
}
