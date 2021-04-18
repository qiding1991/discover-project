package com.kankan.discover.model.event;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.kankan.discover.module.event.param.CreateEventParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Event {
  @Id
  private String eventId;
  private String picture;//活动图片
  private String title;//活动主题
  private Double price;//价格
  @GeoSpatialIndexed
  private List<Double> location;//活动地点
  private String website;//网址
  private String remark;//简介
  private Long startTime;//开始时间
  private Long endTime;//结束时间
  private String eventDate;//活动日期
  private Boolean isOnline;//线上还是线下
  private String eventTypeId;//活动类型id

  private List<UserEvent> user;
  private List<UserEvent> favourite;//收藏



  public Event(CreateEventParam param) {
    BeanUtils.copyProperties(param,this);
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class UserEvent {
    private String userId;
    private Long joinTimestamp;

    public UserEvent(String userId) {
      this.userId = userId;
      this.joinTimestamp = Instant.now().toEpochMilli();
    }
  }
}
