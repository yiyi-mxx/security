package com.mxx.security.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes.size()<=0||configAttributes==null){
            return;
        }
        for (Iterator<ConfigAttribute> iterator = configAttributes.iterator(); iterator.hasNext();){
            String role=iterator.next().getAttribute();
            for (GrantedAuthority grantedAuthority:authentication.getAuthorities()){
                if(grantedAuthority.getAuthority().trim().equals(role.trim())){
                    return;
                }
            }

        }
        throw new AccessDeniedException("没权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
