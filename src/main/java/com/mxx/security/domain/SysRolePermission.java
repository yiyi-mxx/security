package com.mxx.security.domain;

import lombok.Data;

@Data
public class SysRolePermission {
    //角色
    private String roleId;
    private String roleName;

    //权限
    private String permissionId;
    private String url;
    //... getter and settter
}