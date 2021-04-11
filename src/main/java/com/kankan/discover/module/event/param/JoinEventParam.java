package com.kankan.discover.module.event.param;

import com.kankan.discover.model.event.Event;
import lombok.Data;


@Data
public class JoinEventParam {
  private String eventId;
  private String userId;

  public Event.UserEvent toUserEvent() {
    return new Event.UserEvent(userId);
  }


}
