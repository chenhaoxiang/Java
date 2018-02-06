package com.uifuture.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{
    private static final long serialVersionUID = 5205810816225483373L;
    private Integer id;
    private String name;
}