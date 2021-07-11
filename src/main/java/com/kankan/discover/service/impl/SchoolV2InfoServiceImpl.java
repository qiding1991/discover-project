package com.kankan.discover.service.impl;

import com.google.common.collect.ImmutableMap;
import com.kankan.discover.model.school.School;
import com.kankan.discover.model.school.pub.PublicSchool;
import com.kankan.discover.model.schoolv2.SchoolV2Info;
import com.kankan.discover.model.schoolv2.SchoolV2Info.SchoolEQAOInfo;
import com.kankan.discover.service.SchoolV2InfoService;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchoolV2InfoServiceImpl implements SchoolV2InfoService {

  @Autowired
  private MongoTemplate mongoTemplate;

  private final Class<SchoolV2Info> myClass = SchoolV2Info.class;


  @Override
  public List<SchoolV2Info> list(Integer startIndex, Integer limit) {
    Query query = new Query().skip(startIndex).limit(limit);
    return mongoTemplate.find(query, myClass);
  }

  @Override
  public List<SchoolV2Info> findNearBy(Double longitude, Double latitude) {
    Point point = new Point(longitude, latitude);
    Query query = new Query(Criteria.where("location").nearSphere(point));
    return mongoTemplate.find(query, myClass);
  }

  @Override
  public List<SchoolV2Info> findByArea(String area) {
    Query query = new Query(Criteria.where("city").is(area));
    return mongoTemplate.find(query, myClass);
  }

  @Override
  public List<SchoolV2Info> sortByDistance(Double longitude, Double latitude, Integer startIndex, Integer limit) {
    Point point = new Point(longitude, latitude);
    Query query = new Query(Criteria.where("location").nearSphere(point))
        .skip(startIndex).limit(limit);//距离排序
    return mongoTemplate.find(query, myClass);
  }

  @Override
  public List<SchoolV2Info> sortByFeiSha(Integer startIndex, Integer limit) {
    return null;
  }

  @Override
  public List<SchoolV2Info> filter(Integer bizScope, String language, String proj, Integer startIndex, Integer limit) {
    return null;
  }

  @Override
  public SchoolV2Info detail(String id) {
    Query query = new Query(Criteria.where("schoolID").is(id));
    return mongoTemplate.findOne(query, SchoolV2Info.class);
  }

  @Override
  public List<SchoolV2Info> sortByHot(Integer startIndex, Integer limit) {
    return null;
  }

  @Override
  public List<SchoolV2Info> filter(Integer bizScope, Integer startIndex, Integer limit) {
    return null;
  }

  @Override
  public void removeSchool(String schoolId) {
    Query query = new Query(Criteria.where("schoolID").is(schoolId));
    mongoTemplate.remove(query, myClass);
  }

  @Override
  public Long count() {
    return mongoTemplate.count(new Query(), myClass);
  }

  @Override
  public School addNewSchool(SchoolV2Info school) {
    return null;
  }

  @Override
  public void saveSchool(SchoolV2Info school) {
    mongoTemplate.save(school);
  }

  @Override
  public void addEQAO(SchoolEQAOInfo eqaoInfo) {
    Query query = Query.query(Criteria.where("schoolID").is(eqaoInfo.getSchoolID()));
    //删除旧的
    Update update = new Update().pull("eaqoInfoList", ImmutableMap.of("yearMark", eqaoInfo.getYearMark()));
    UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, myClass);
    log.info("updateResult={}", updateFirst);

    //增加新的
    update = new Update().addToSet("eaqoInfoList", eqaoInfo);
    mongoTemplate.updateFirst(query, update, myClass);
  }
}
