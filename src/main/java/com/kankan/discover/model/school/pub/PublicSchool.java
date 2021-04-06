package com.kankan.discover.model.school.pub;

import java.util.List;

import com.kankan.discover.model.school.School;

import lombok.Data;


@Data
public class PublicSchool extends School {
    private List<FeiShaRange> feisha;
    private List<EqaoRange> eqao;
    private String language;
    private String proj;//IB AP Art Gifted Sport
}
