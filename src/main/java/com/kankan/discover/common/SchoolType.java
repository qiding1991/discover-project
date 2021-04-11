package com.kankan.discover.common;


public enum SchoolType {
  PUBLIC(1, "公立学校"), PRIVATE(2, "私立学校");
  private Integer code;
  private String desc;

  SchoolType(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static SchoolType schoolType(Integer code) {
    if (code == 1) {
      return PUBLIC;
    }
    return PRIVATE;
  }
}
