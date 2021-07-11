package com.kankan.discover.service;

import com.kankan.discover.model.school.School;
import com.kankan.discover.model.schoolv2.SchoolV2Info;
import com.kankan.discover.model.schoolv2.SchoolV2Info.SchoolEQAOInfo;
import java.util.List;


public interface SchoolV2InfoService {

  List<SchoolV2Info> list(Integer startIndex, Integer limit);

  List<SchoolV2Info> findNearBy(Double longitude, Double latitude);

  List<SchoolV2Info> findByArea(String area);

  List<SchoolV2Info> sortByDistance(Double longitude, Double latitude, Integer startIndex, Integer limit);

  List<SchoolV2Info> sortByFeiSha(Integer startIndex, Integer limit);

  List<SchoolV2Info> filter(Integer bizScope, String language, String proj, Integer startIndex, Integer limit);

  SchoolV2Info detail(String id);

  List<SchoolV2Info> sortByHot(Integer startIndex, Integer limit);

  List<SchoolV2Info> filter(Integer bizScope, Integer startIndex, Integer limit);

  void removeSchool(String schoolId);

  Long count();

  School addNewSchool(SchoolV2Info school);

  void saveSchool(SchoolV2Info school);

  void addEQAO(SchoolEQAOInfo eqaoInfo);

}
