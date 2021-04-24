package com.kankan.discover.service;

import java.util.List;

import com.kankan.discover.model.job.Job;
import com.kankan.discover.module.job.param.ApplyOrFavouriteJobParam;


public interface JobService {
  List<Job> find(Double longitude, Double latitude, Double maxDistance, String area, String searchKey,Integer searchType,  Integer timeOrder, Integer startIndex, Integer limit);

  Job findDetail(String jobId);

   List<Job> findRecent(Integer startIndex,Integer limit) ;

  Job publishJob(Job job);

  void removeJob(String jobId);

  List<Job> find(Integer startIndex, Integer pageSize);

  Long count();

  Boolean applyJob(ApplyOrFavouriteJobParam applyOrFavouriteJobParam);

  Boolean favourite(ApplyOrFavouriteJobParam applyOrFavouriteJobParam);
}
