package com.cybercom.confluence.competence.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;

@Transactional
public interface CompetenceService
{
    String getName();

    List<CompetenceRestPersonModel> getAllPeople();

    void putPerson(String name, CompetenceRestPersonModel person);

    CompetenceRestPersonModel getPerson(String id);
}