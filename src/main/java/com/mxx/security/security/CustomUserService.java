package com.mxx.security.security;

import com.mxx.security.dao.UserDao;
import com.mxx.security.domain.SysRole;
import com.mxx.security.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user=userDao.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        List<SysRole> roles=userDao.getRoleByUsername(username);
        Collection<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for(SysRole role:roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        user.setGrantedAuthorities(grantedAuthorities);
        return user;
    }
}
