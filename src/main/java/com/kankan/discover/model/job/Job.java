package com.kankan.discover.model.job;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import lombok.Data;


@Data
public class Job {
    @Id
    private String id;
    @GeoSpatialIndexed
    private List<Double> location;//经纬度
    private String area;//地区
    private Long publishTime;
}
