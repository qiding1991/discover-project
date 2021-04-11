package com.kankan.discover.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.kankan.discover.model.event.Event;

import com.kankan.discover.service.EventService;


@Service
public class EventServiceImpl implements EventService {
  @Autowired
  private MongoTemplate mongoTemplate;


  @Override

  public List<Event> findEvent(String eventTypeId, Integer startIndex, Integer limit) {
    Query query = new Query(Criteria.where("eventTypeId").is(eventTypeId));
    query.skip(startIndex).limit(limit);
    List<Event> infoList = mongoTemplate.find(query, Event.class);
    return infoList;
  }

  @Override
  public Event findEvent(String eventId) {
    Query query = new Query(Criteria.where("_id").is(eventId));
    return mongoTemplate.findOne(query, Event.class);
  }

  @Override
  public List<Event> findEvent(Double longitude, Double latitude, String area, Integer timeOrder, Integer startIndex, Integer limit) {
    return null;
  }

  @Override
  public void joinEvent(String eventId, Event.UserEvent userEvent) {
    Query query = new Query(Criteria.where("eventId").is(eventId));
    Update update = new Update().addToSet("userList", userEvent);
    mongoTemplate.findAndModify(query, update, Event.class);
  }


  @Override
  public List<Event> findEvent(Integer startIndex, Integer pageSize) {
    Query query = new Query();
    query.skip(startIndex).limit(pageSize);
    List<Event> infoList = mongoTemplate.find(query, Event.class);
    return infoList;
  }

  @Override
  public Long count() {
    return mongoTemplate.count(new Query(), Event.class);
  }

  @Override
  public void remove(String eventId) {
    Query query = new Query(Criteria.where("_id").is(eventId));
    mongoTemplate.remove(query, Event.class);
  }
}
