package com.kankan.discover.service.impl;

import java.time.Instant;
import java.util.List;

import com.kankan.discover.module.job.param.ApplyOrFavouriteJobParam;
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

import com.kankan.discover.model.job.Job;
import com.kankan.discover.service.JobService;


@Service
public class JobServiceImpl implements JobService {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<Job> find(Double longitude, Double latitude, Double maxDistance, String area,String searchKey, Integer timeOrder, Integer startIndex, Integer limit) {
    Query query = new Query().skip(startIndex).limit(limit);
    if (ObjectUtils.anyNotNull(longitude, latitude)) {
      Point point = new Point(longitude, latitude);
      query.addCriteria(Criteria.where("location").nearSphere(point).maxDistance(maxDistance));
    } else if (StringUtils.isNotEmpty(area)) {
      query.addCriteria(Criteria.where("area").is(area));
    }else if(StringUtils.isNotBlank(searchKey)){
      query.addCriteria(Criteria.where("remark").regex(searchKey));
    }


    Direction direction = Direction.DESC;
    if (timeOrder == 1) {
      direction = Direction.ASC;
    }
    query.with(Sort.by(direction, "publishTime"));
    List<Job> jobList = mongoTemplate.find(query, Job.class);
    return jobList;
  }

  @Override
  public Job findDetail(String jobId) {
    Query query = new Query().addCriteria(Criteria.where("_id").is(jobId));
    //查看次数加一
    Update update = new Update().inc("readCount", 1);
    return mongoTemplate.findAndModify(query, update, Job.class);
  }

  @Override
  public List<Job> findRecent(Integer startIndex,Integer limit) {
    Query query = new Query().with(Sort.by(Direction.DESC, "publishTime")).skip(startIndex).limit(limit);
    return mongoTemplate.find(query, Job.class);
  }

  @Override
  public Job publishJob(Job job) {
    Job result = mongoTemplate.insert(job);
    return result;
  }

  @Override
  public void removeJob(String jobId) {
    Query query = new Query().addCriteria(Criteria.where("_id").is(jobId));
    mongoTemplate.remove(query, Job.class);
  }

  @Override
  public List<Job> find(Integer startIndex, Integer pageSize) {
    Query query = new Query();
    query.skip(startIndex).limit(pageSize);
    List<Job> jobList = mongoTemplate.find(query, Job.class);
    return jobList;
  }

  @Override
  public Long count() {
    return mongoTemplate.count(new Query(), Job.class);
  }

  @Override
  public Boolean applyJob(ApplyOrFavouriteJobParam param) {
    return applyOrFavouriteJob(param.getUserId(), param.getJobId(), "apply");
  }

  @Override
  public Boolean favourite(ApplyOrFavouriteJobParam param) {
    return applyOrFavouriteJob(param.getUserId(), param.getJobId(), "favourite");
  }


  public Boolean applyOrFavouriteJob(String userId, String jobId, String type) {
    Query query = new Query().addCriteria(Criteria.where("_id").is(jobId).and(type)
      .elemMatch(Criteria.where("userId").is(userId)));
    Job applyJob = mongoTemplate.findOne(query, Job.class);
    if (applyJob != null) {
      return false;
    }
    query = new Query().addCriteria(Criteria.where("_id").is(jobId));
    Update update = new Update().addToSet(type, new Job.Apply(userId, Instant.now().toEpochMilli()));
    mongoTemplate.updateMulti(query, update, Job.class);
    return true;
  }

}
