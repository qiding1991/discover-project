package com.kankan.discover.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.kankan.discover.model.event.EventType;
import com.kankan.discover.service.EventTypeService;


@Service
public class EventTypeServiceImpl implements EventTypeService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public EventType save(EventType eventName) {
        return mongoTemplate.insert(eventName);
    }

    @Override
    public List<EventType> eventList() {
        return mongoTemplate.findAll(EventType.class);
    }

    @Override
    public void remove(String eventId) {
        Query query = Query.query(Criteria.where("_id").is(eventId));
        mongoTemplate.remove(query);
        return;
    }
}
