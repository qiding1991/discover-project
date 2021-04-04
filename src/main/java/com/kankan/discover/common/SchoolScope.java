package com.kankan.discover.common;



public enum  SchoolScope {
    PRIMARY_SCHOOL(1,"小学"),JUNIOR_MIDDLE_SCHOOL(2,"中学"),HIGH_SCHOOL(3,"高中");
    private Integer code;
    private String name;
    SchoolScope(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
