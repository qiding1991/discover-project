package com.kankan.discover.model.school.pub;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;


@Data
public class FeiShaRange {
    @Id
    private String id;
    @Indexed
    private String schoolId;
    private String year;
    private Integer rank;
    private Integer total;
    private String rating;
    private String income;
    private String esl;
    private String edu;
}
