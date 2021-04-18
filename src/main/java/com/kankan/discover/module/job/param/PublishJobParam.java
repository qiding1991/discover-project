package com.kankan.discover.module.job.param;

import lombok.Data;
import org.springframework.data.geo.Point;

@Data
public class PublishJobParam {
  private String userId;
  private String title;
  private Integer payType;
  private String price;
  private String area;
  private Point location;
  private String email;
  private String phone;
  private String wx;
  private String remark;
}
