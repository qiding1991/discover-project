package com.kankan.discover.model.school.pri;

import com.kankan.discover.model.school.School;

import lombok.Data;


@Data
public class PrivateSchool extends School {
    private String privateType;
    private Long createTime;
    private String religious;
    private Boolean accommodation;//是否支持住宿
    private Integer accommodationCount; //多少个床位
    private String accommodationPrice;//住宿费用
    private Boolean daySchool;//
    private Integer daySchoolCount;
    private String daySchoolPrice;
    private String acceptRate;
    private Boolean interview;
    private Boolean ssat;
    private Boolean famous;
}
