package com.kankan.discover.service;

import java.util.List;

import com.kankan.discover.model.school.School;


public interface SchoolService <T extends School> {
     List<T> list(Integer startIndex,Integer limit);

     List<T> findNearBy(Double longitude, Double latitude);

     List<T> findByArea(String area);

    List<T> sortByDistance(Double longitude, Double latitude, Integer startIndex, Integer limit);

    List<T> sortByFeiSha(Integer startIndex, Integer limit);

    List<T> filter(Integer bizScope, String language, String proj, Integer startIndex, Integer limit);

    T detail(String id);

    List<T> sortByHot(Integer startIndex, Integer limit);

    List<T> filter(Integer bizScope, Integer startIndex, Integer limit);
}
