package com.kankan.discover.service;

import java.util.List;

import com.kankan.discover.model.event.Event;
import com.kankan.discover.model.event.UserEvent;


public interface EventService {
  List<Event> findEvent(String eventTypeId, Integer startIndex, Integer limit);

  Event findEvent(String eventId);

  List<Event> findEvent(Double longitude, Double latitude, String area, Integer timeOrder, Integer startIndex, Integer limit);

  UserEvent joinEvent(UserEvent userEvent);

  List<Event> findEvent(Integer  startIndex, Integer pageSize);

  Long count();

  void remove(String eventId);
}
