package com.binout.soccer5.front.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends HttpFilter {

    public static final String AUTH_KEY = "app.user.name";

    public void doHttpFilter(HttpServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute(AUTH_KEY) == null) {
            ((HttpServletResponse) resp).sendRedirect("index.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }

}
