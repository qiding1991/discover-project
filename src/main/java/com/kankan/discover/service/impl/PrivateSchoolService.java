package com.kankan.discover.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.kankan.discover.model.school.School;
import com.kankan.discover.model.school.pri.PrivateSchool;
import com.kankan.discover.model.school.pub.PublicSchool;
import com.kankan.discover.service.SchoolService;

@Service
public class PrivateSchoolService implements SchoolService<PrivateSchool> {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<PrivateSchool> list(Integer startIndex, Integer limit) {
        Query query = new Query().skip(startIndex).limit(limit);
        return mongoTemplate.find(query, PrivateSchool.class);
    }

    @Override
    public List<PrivateSchool> findNearBy(Double longitude, Double latitude) {
        Point point = new Point(longitude, latitude);
        Query query = new Query(Criteria.where("location").nearSphere(point));
        return mongoTemplate.find(query,PrivateSchool.class);
    }

    @Override
    public List<PrivateSchool> findByArea(String area) {
        Query query=new Query(Criteria.where("area").is(area));
        return mongoTemplate.find(query,PrivateSchool.class);
    }

    @Override
    public List<PrivateSchool> sortByDistance(Double longitude, Double latitude, Integer startIndex, Integer limit) {
        Point point = new Point(longitude, latitude);
        Query query = new Query(Criteria.where("location").nearSphere(point)).skip(startIndex).limit(limit);
        return mongoTemplate.find(query,PrivateSchool.class);
    }

    @Override
    public List<PrivateSchool> sortByFeiSha(Integer startIndex, Integer limit) {
        throw new RuntimeException("不支持该操作");
    }

    @Override
    public List<PrivateSchool> filter(Integer bizScope, String language, String proj, Integer startIndex, Integer limit) {
       throw new RuntimeException("不支持该操作");
    }

    @Override
    public PrivateSchool detail(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query,PrivateSchool.class);
    }

    @Override
    public List<PrivateSchool> sortByHot(Integer startIndex, Integer limit) {
        Query query = new Query().skip(startIndex).limit(limit);
        return mongoTemplate.find(query,PrivateSchool.class);
    }

    @Override
    public List<PrivateSchool> filter(Integer bizScope, Integer startIndex, Integer limit) {
        Query query=new Query(Criteria.where("bizScope").is(bizScope))
                .skip(startIndex).limit(limit);//距离排序
        return mongoTemplate.find(query,PrivateSchool.class);
    }
}
