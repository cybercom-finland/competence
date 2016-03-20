package com.cybercom.confluence.competence.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;
import com.cybercom.confluence.competence.rest.model.CompetenceRestTeamModel;

@Transactional
public interface CompetenceService
{
    String getName();

    List<CompetenceRestPersonModel> getAllPeople();

    void putPerson(String name, CompetenceRestPersonModel person);

    CompetenceRestPersonModel getPerson(String id);

    void deletePerson(String confluenceId);

    void deleteTeam(Integer id);

    void putTeam(String name, List<String> members);

    CompetenceRestTeamModel getTeam(Integer id);
}