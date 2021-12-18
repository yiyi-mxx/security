package com.mxx.security.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {
    private Integer id;
    private Integer pid;
    private String name;
    private String url;
    private String description;
}
