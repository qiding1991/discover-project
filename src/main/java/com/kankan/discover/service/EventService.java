package com.kankan.discover.service;

import java.util.List;

import com.kankan.discover.model.event.Event;


public interface EventService {
    List<Event> findEvent(String eventTypeId, Integer startIndex, Integer limit);

    Event findEvent(String eventId);



    List<Event> findEvent(Integer startIndex, Integer pageSize);

    Long count();

    void remove(String eventId);

    Event save(Event event);

    List<Event> findByTime(String eventTime, Integer startIndex, Integer limit);

    List<Event> findEvent(Double longitude, Double latitude, Double maxDistance, String area, String searchKey, Integer timeOrder, Integer startIndex, Integer limit);

    Boolean favourite(String eventId, String userId);
    Boolean joinEvent(String eventId,String userId);

    List<Event> recentEvent(Integer startIndex, Integer limit);
}
