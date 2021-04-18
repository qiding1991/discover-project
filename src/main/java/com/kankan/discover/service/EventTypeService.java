package com.kankan.discover.service;

import java.util.List;

import com.kankan.discover.model.event.Event;
import com.kankan.discover.model.event.EventType;


public interface EventTypeService {
  //分类管理
  EventType save(EventType eventName);
  List<EventType> eventList();
  void remove(String eventId);
}
