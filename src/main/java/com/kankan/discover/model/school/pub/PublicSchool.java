package com.kankan.discover.model.school.pub;

import com.kankan.discover.model.school.School;

import lombok.Data;


@Data
public class PublicSchool extends School {
    private FeiShaRange feisha;
    private EqaoRange eqao;
}
