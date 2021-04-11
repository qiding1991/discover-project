package com.kankan.discover.module.school.param;

import com.kankan.discover.common.SchoolType;
import com.kankan.discover.model.school.School;
import com.kankan.discover.model.school.pri.PrivateSchool;
import com.kankan.discover.model.school.pub.PublicSchool;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UpdateSchoolParam {
  private PrivateSchoolParam privateSchool;
  private PublicSchoolParam publicSchool;

  public School getSchool(String schoolId, Integer schoolType) {
    if (SchoolType.PUBLIC == SchoolType.schoolType(schoolType)) {
      PublicSchool school = new PublicSchool();
      school.setId(schoolId);
      BeanUtils.copyProperties(this.publicSchool, school);
      return school;
    }
    PrivateSchool school = new PrivateSchool();
    school.setId(schoolId);
    BeanUtils.copyProperties(this.privateSchool, school);
    return school;
  }
}
