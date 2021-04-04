package com.kankan.discover.model.school;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
public class School {
    @Id
    private String id;//主键
    private Integer schoolType;//学校类型 SchoolType
    private String name;//学校名称
    private String area;//地区
    private List<Double> location;//经纬度
    private List<Integer> bizScope;//经营范围
    private Integer minLevel;//最低年级
    private Integer maxLevel;//最大年级
    private String phone;//电话
    private String fax;//传真
    private String website;//网站
}
