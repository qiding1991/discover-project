package com.kankan.discover.service;

import java.util.List;

import com.kankan.discover.model.job.Job;


public interface JobService {
    List<Job> find(Double longitude, Double latitude, String area, Integer timeOrder, Integer startIndex, Integer limit);

    Job findDetail(String jobId);

    Job publishJob(Job job);
}
