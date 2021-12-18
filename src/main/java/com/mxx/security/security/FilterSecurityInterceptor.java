package com.mxx.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;

public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    SecurityMetadataSourceService securityMetadataSourceService;
    void setMyAccessDecisionManager(MyAccessDecisionManager accessDecisionManager){
        super.setAccessDecisionManager(accessDecisionManager);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation=new FilterInvocation(servletRequest,servletResponse,filterChain);
        InterceptorStatusToken token =super.beforeInvocation(filterInvocation);
        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(),filterInvocation.getResponse());
        }finally{
            super.afterInvocation(token,null);
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSourceService;
    }
}
