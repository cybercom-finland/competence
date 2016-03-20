package com.cybercom.confluence.competence.service;

import java.util.List;

import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;

public interface CompetenceService
{
    String getName();

    List<CompetenceRestPersonModel> getAllPeople();

    void putPerson(String name, CompetenceRestPersonModel person);

    CompetenceRestPersonModel getPerson(String id);
}