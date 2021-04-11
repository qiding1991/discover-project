package com.kankan.discover.module.school.param;

import lombok.Data;

import java.util.List;

@Data
public class PublicSchoolParam {
  private String name;//学校名称
  private String area;//地区
  private List<Double> location;//经纬度
  private List<Integer> bizScope;//经营范围
  private Integer minLevel;//最低年级
  private Integer maxLevel;//最大年级
  private String phone;//电话
  private String fax;//传真
  private String website;//网站
  private String language;
  private String proj;//IB AP Art Gifted Sport
}
