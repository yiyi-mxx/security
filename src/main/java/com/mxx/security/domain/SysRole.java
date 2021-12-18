package com.mxx.security.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRole implements Serializable {
    private Integer id;
    private String name;
    private List<SysPermission> permissions;
}
