package com.cybercom.confluence.competence.service;

import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.atlassian.activeobjects.tx.Transactional;
import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;
import com.cybercom.confluence.competence.rest.model.CompetenceRestTeamModel;

@Transactional
public interface CompetenceService
{
    String getName();

    List<String> getAllPeople() throws JSONException;

    void putPerson(String name, CompetenceRestPersonModel person);

    CompetenceRestPersonModel getPerson(String id) throws JSONException;

    void deletePerson(String confluenceId);

    List<String> getAllTeams() throws JSONException;

    void putTeam(String teamId, CompetenceRestTeamModel competenceTeam);

    CompetenceRestTeamModel getTeam(String teamId);

    void deleteTeam(String teamId);
}