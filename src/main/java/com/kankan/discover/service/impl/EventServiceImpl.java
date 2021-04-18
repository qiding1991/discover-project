package com.kankan.discover.service.impl;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.kankan.discover.model.event.Event;
import com.kankan.discover.model.event.Event.UserEvent;
import com.kankan.discover.service.EventService;


@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override

    public List<Event> findEvent(String eventTypeId, Integer startIndex, Integer limit) {
        Query query = new Query();
        if(StringUtils.isNotBlank(eventTypeId)){
            query.addCriteria(Criteria.where("eventTypeId").is(eventTypeId));
        }
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

    @Override
    public Event save(Event event) {
        return mongoTemplate.save(event);
    }

    @Override
    public List<Event> findByTime(String eventDate, Integer startIndex, Integer limit) {
       Query query =new Query(Criteria.where("eventDate").is(eventDate))
               .with(Sort.by(Direction.DESC,"startTime"))
               .skip(startIndex)
               .limit(limit);
       return mongoTemplate.find(query,Event.class);
    }

    @Override
    public List<Event> findEvent(Double longitude, Double latitude, Double maxDistance, String area, String searchKey, Integer timeOrder,
            Integer startIndex, Integer limit) {
        Query query = new Query().skip(startIndex).limit(limit);
        if (ObjectUtils.anyNotNull(longitude, latitude)) {
            Point point = new Point(longitude, latitude);
            query.addCriteria(Criteria.where("location").nearSphere(point).maxDistance(maxDistance));
        } else if (StringUtils.isNotEmpty(area)) {
            query.addCriteria(Criteria.where("area").is(area));
        }else if(StringUtils.isNotBlank(searchKey)){
            query.addCriteria(Criteria.where("title").regex(searchKey));
        }
        Direction direction = Direction.DESC;
        if (timeOrder == 1) {
            direction = Direction.ASC;
        }
        query.with(Sort.by(direction,"eventDate","startTime"));
        List<Event> eventList= mongoTemplate.find(query, Event.class);
        return eventList;
    }

    @Override
    public Boolean favourite(String eventId, String userId) {
          return addUser(eventId,userId,"favourite");
    }

    @Override
    public Boolean joinEvent(String eventId, String userId) {
        return addUser(eventId,userId,"user");
    }

    @Override
    public List<Event> recentEvent(Integer startIndex, Integer limit) {
        Query query = new Query();
        query.skip(startIndex).limit(limit);
        query.with(Sort.by(Direction.DESC,"eventDate","startTime"));
        List<Event> infoList = mongoTemplate.find(query, Event.class);
        return infoList;
    }


    public Boolean addUser(String eventId, String userId,String keyword){
        //判断是否参加
        Query query = new Query(Criteria.where("eventId").is(eventId)
                .and(keyword).elemMatch(Criteria.where("userId").is(userId)));

        if (mongoTemplate.findOne(query, Event.class) != null) {
            return false;
        }
        query = new Query(Criteria.where("eventId").is(eventId));
        Update update = new Update().addToSet(keyword, new UserEvent(userId));
        mongoTemplate.findAndModify(query, update, Event.class);
        return true;
    }


}
