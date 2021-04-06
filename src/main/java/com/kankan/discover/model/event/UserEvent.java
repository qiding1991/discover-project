package com.kankan.discover.model.event;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.kankan.discover.module.event.param.JoinEventParam;

import lombok.Data;


@Data
public class UserEvent {
    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String eventId;
    private Long joinTimestamp;

    public UserEvent(JoinEventParam param) {
        BeanUtils.copyProperties(param,this);
    }
}
