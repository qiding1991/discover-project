package com.kankan.discover.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kankan.discover.model.event.Event;
import com.kankan.discover.model.event.UserEvent;
import com.kankan.discover.service.EventService;


@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<Event> findEvent(String eventTypeId, Integer startIndex, Integer limit) {
        return null;
    }

    @Override
    public Event findEvent(String eventId) {
        return null;
    }

    @Override
    public List<Event> findEvent(Double longitude, Double latitude, String area, Integer timeOrder, Integer startIndex, Integer limit) {
        return null;
    }

    @Override
    public UserEvent joinEvent(UserEvent userEvent) {
        return null;
    }
}
