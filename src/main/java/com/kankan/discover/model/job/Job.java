package com.kankan.discover.model.job;

import com.kankan.discover.module.job.param.PublishJobParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Job {
  @Id
  private String id;
  private Long publishTime;
  private String userId;
  private Integer readCount;

  @GeoSpatialIndexed(name = "location")
  private Point location;//经纬度
  @Indexed
  private String area;//地区
  private String title;
  private String price;
  private String email;
  private String phone;
  private String wx;
  private String remark;
  private Integer payType;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public  static class Apply {
      private String userId;
      private Long timestamp;
  }
  private List<Apply> apply;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public  static class Favourite {
    private String userId;
    private Long timestamp;
  }
  private List<Favourite> favourite;











  public Job(PublishJobParam publishJobParam) {
    BeanUtils.copyProperties(publishJobParam, this);
    this.publishTime = Instant.now().toEpochMilli();
  }
}
