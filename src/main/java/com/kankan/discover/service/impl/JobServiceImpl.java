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
import org.springframework.stereotype.Service;

import com.kankan.discover.model.job.Job;
import com.kankan.discover.service.JobService;


@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Job> find(Double longitude, Double latitude, String area, Integer timeOrder, Integer startIndex, Integer limit) {
        Query query = new Query().skip(startIndex).limit(limit);
        if (ObjectUtils.anyNotNull(longitude, latitude)) {
            Point point = new Point(longitude, latitude);
            query.addCriteria(Criteria.where("location").nearSphere(point));
        } else if (StringUtils.isNotEmpty(area)) {
            query.addCriteria(Criteria.where("area").is(area));
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
        return mongoTemplate.findOne(query, Job.class);
    }

    @Override
    public Job publishJob(Job job) {
        Job result = mongoTemplate.save(job);
        return result;
    }
}
