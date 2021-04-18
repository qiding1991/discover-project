package com.kankan.discover.module.event.param;

import lombok.Data;


@Data
public class CreateEventTypeParam {
    private String eventId;
    private String eventName;
    private Integer eventOrder;
}
