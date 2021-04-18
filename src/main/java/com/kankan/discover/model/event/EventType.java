package com.kankan.discover.model.event;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.kankan.discover.module.event.param.CreateEventTypeParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class EventType {
    @Id
    private String eventId;
    private String eventName;
    private Integer eventOrder;

    public EventType(CreateEventTypeParam typeParam) {
        BeanUtils.copyProperties(typeParam,this);
    }
}
