package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        boolean status = req.getSession().getAttribute("admin") == null;
        if ( url.equals("") || url.equals("/") || url.equals("/adminLogin") ) {
            if ( !status ) {
                res.sendRedirect("http://localhost:8090/dashboard");
            }
        }else {
            if ( status ) {
                res.sendRedirect("http://localhost:8090/");
            }
        }

        chain.doFilter(req, res);
    }

}
