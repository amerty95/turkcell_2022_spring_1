package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
public class FilterConfig implements Filter {

    final AuditingConfig auditingConfig;
    final InfoRepository infoRepository;
    public FilterConfig(AuditingConfig auditingConfig, InfoRepository infoRepository) {
        this.auditingConfig = auditingConfig;
        this.infoRepository = infoRepository;
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("Server UP");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        if ( !url.contains("h2-console") ) {
            String userName = "";
            if (auditingConfig.getCurrentAuditor().isPresent()) {
                userName = "" + auditingConfig.getCurrentAuditor().get();
            }
            String agent = req.getHeader("user-agent");
            String ipAddress = req.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = req.getRemoteAddr();
            }
            String sessionId = req.getSession().getId();

            Info i = new Info();
            i.setAgent(agent);
            i.setDate(new Date());
            i.setIpAddress(ipAddress);
            i.setSessionId(sessionId);
            i.setUrl(url);
            i.setUserName(userName);
            infoRepository.save(i);
        }

        filterChain.doFilter( req, res );
    }

    @Override
    public void destroy() {
        System.out.println("Server Down");
        Filter.super.destroy();
    }

}
