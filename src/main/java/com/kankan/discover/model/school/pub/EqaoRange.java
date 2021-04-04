package com.kankan.discover.model.school.pub;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;


@Data
public class EqaoRange {

    @Id
    private String id;
    @Indexed
    private String schoolId;
    private String year;
    @Data
    public  static  class  EqaoDetail{

        private Integer rank;
        private String reading;
        private String writing;
        private String math;
        private String students;
    }

    private EqaoDetail grade3;
    private EqaoDetail grade6;
    private EqaoDetail grade9;

}
