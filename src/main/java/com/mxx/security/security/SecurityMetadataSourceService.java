package com.mxx.security.security;

import com.mxx.security.dao.UserDao;
import com.mxx.security.domain.SysRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class SecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    UserDao userDao;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request=((FilterInvocation)object).getRequest();
        HashMap<String,Collection<ConfigAttribute>> map=new HashMap<>();
        List<SysRolePermission> rolePermissions=userDao.getAllRolePermission();
        for (SysRolePermission rolePermission:rolePermissions){
            String url=rolePermission.getUrl();
            String roleName=rolePermission.getRoleName();
            ConfigAttribute role=new SecurityConfig(roleName);
            if(map.containsKey(url)){
                map.get(url).add(role);
            }else {
                Collection<ConfigAttribute> configAttributes=new ArrayList<>();
                configAttributes.add(role);
                map.put(url,configAttributes);
            }
        }
        for(Iterator<String> iterator=map.keySet().iterator();iterator.hasNext();){
            String url=iterator.next();
            if(new AntPathRequestMatcher(url).matches(request)){
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
