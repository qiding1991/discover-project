package com.kankan.discover.service.impl;

import java.util.List;

import com.kankan.discover.model.school.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.kankan.discover.model.school.pub.PublicSchool;
import com.kankan.discover.service.SchoolService;


@Service
public class PublicSchoolService implements SchoolService<PublicSchool> {

  @Autowired
  private MongoTemplate mongoTemplate;


  @Override
  public List<PublicSchool> list(Integer startIndex, Integer limit) {
    Query query = new Query().skip(startIndex).limit(limit);
    return mongoTemplate.find(query, PublicSchool.class);
  }

  @Override
  public List<PublicSchool> findNearBy(Double longitude, Double latitude) {
    Point point = new Point(longitude, latitude);
    Query query = new Query(Criteria.where("location").nearSphere(point));
    return mongoTemplate.find(query, PublicSchool.class);
  }

  @Override
  public List<PublicSchool> findByArea(String area) {
    Query query = new Query(Criteria.where("area").is(area));
    return mongoTemplate.find(query, PublicSchool.class);
  }

  @Override
  public List<PublicSchool> sortByDistance(Double longitude, Double latitude, Integer startIndex, Integer limit) {
    Point point = new Point(longitude, latitude);
    Query query = new Query(Criteria.where("location").nearSphere(point))
      .skip(startIndex).limit(limit);//距离排序
    return mongoTemplate.find(query, PublicSchool.class);

  }

  @Override
  public List<PublicSchool> sortByFeiSha(Integer startIndex, Integer limit) {
    Query query = new Query()
//                .with(Sort.by(Direction.ASC,"")) TODO 菲莎的排名的计算方式是啥？
      .skip(startIndex).limit(limit);//距离排序
    return mongoTemplate.find(query, PublicSchool.class);
  }

  @Override
  public List<PublicSchool> filter(Integer bizScope, String language, String proj, Integer startIndex, Integer limit) {
    Query query = new Query(Criteria.where("proj").is(proj).and("language").is(language).and("bizScope").is(bizScope))
      .skip(startIndex).limit(limit);//距离排序
    return mongoTemplate.find(query, PublicSchool.class);
  }

  @Override
  public PublicSchool detail(String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    return mongoTemplate.findOne(query, PublicSchool.class);
  }

  @Override
  public List<PublicSchool> sortByHot(Integer startIndex, Integer limit) {
    throw new RuntimeException("公立学校，不支持该搞作");
  }

  @Override
  public List<PublicSchool> filter(Integer bizScope, Integer startIndex, Integer limit) {
    throw new RuntimeException("公立学校，不支持该搞作");
  }

  @Override
  public void removeSchool(String schoolId) {
    Query query = Query.query(Criteria.where("_id").is(schoolId));
    mongoTemplate.remove(query, PublicSchool.class);
  }

  @Override
  public Long count() {
    return mongoTemplate.count(new Query(), PublicSchool.class);
  }

  @Override
  public School addNewSchool(School school) {
    return mongoTemplate.save(school);
  }

  @Override
  public void saveSchool(School school) {
    mongoTemplate.save(school);
  }
}
