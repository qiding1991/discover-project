package com.kankan.discover.module.event.param;

import java.time.Instant;
import java.util.List;

import org.springframework.data.geo.Point;

import com.kankan.discover.model.event.Event;

import lombok.Data;


@Data
public class CreateEventParam {
    private String eventId;
    private String picture;//活动图片
    private String title;//活动主题
    private Double price;//价格

    private String address;//活动地址
    private Point location;//获取地址

    private String website;//网址
    private String remark;//简介
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String eventDate;//活动日期
    private Boolean isOnline;//线上还是线下
    private String eventTypeId;//活动类型id

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
