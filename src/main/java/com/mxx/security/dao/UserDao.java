package com.mxx.security.dao;

import com.mxx.security.domain.SysRole;
import com.mxx.security.domain.SysRolePermission;
import com.mxx.security.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
     SysUser getUserByUsername(String username);
     List<SysRolePermission> getAllRolePermission();
     List<SysRole> getRoleByUsername(String username);
}
