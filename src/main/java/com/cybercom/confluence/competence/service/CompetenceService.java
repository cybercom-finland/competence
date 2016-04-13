package com.cybercom.confluence.competence.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.cybercom.confluence.competence.rest.model.PersonModel;
import com.cybercom.confluence.competence.rest.model.TeamModel;

@Transactional
public interface CompetenceService
{
    String getName();

    List<String> getAllPeople();

    void putPerson(String name, PersonModel person);

    PersonModel getPerson(String id);

    void deletePerson(String confluenceId);

    List<String> getAllTeams();

    void putTeam(String teamId, TeamModel competenceTeam);

    TeamModel getTeam(String teamId);

    void deleteTeam(String teamId);

	void addCompetenceToPerson(String id);
}