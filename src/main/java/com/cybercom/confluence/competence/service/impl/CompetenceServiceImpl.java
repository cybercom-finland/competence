package com.cybercom.confluence.competence.service.impl;

import com.atlassian.bandana.BandanaManager;
import com.cybercom.confluence.competence.rest.model.PersonModel;
import com.cybercom.confluence.competence.rest.model.TeamModel;
import com.cybercom.confluence.competence.service.CompetenceService;

import java.util.List;
import com.google.common.collect.Lists;

import com.cybercom.confluence.competence.bandana.*;

public class CompetenceServiceImpl implements CompetenceService
{
    private final BandanaManager bandanaManager;

    public CompetenceServiceImpl(BandanaManager bandanaManager)
    {
        this.bandanaManager = bandanaManager;
    }
    

    public String getName()
    {
        return "competenceService";
    }

    @Override
    public List<String> getAllPeople() {
        return Lists.newArrayList(bandanaManager.getKeys(new PeopleContext()));
    }

    @Override
    public List<String> getAllTeams() {
        return Lists.newArrayList(bandanaManager.getKeys(new TeamsContext()));
    }

    @Override
    public void putPerson(String confluenceId, PersonModel competencePerson) {
        PeopleContext peopleContext = new PeopleContext();
        bandanaManager.setValue(peopleContext, confluenceId, competencePerson);
    }

    @Override
    public void putTeam(String teamId, TeamModel competenceTeam) {
        TeamsContext teamsContext = new TeamsContext();
        bandanaManager.setValue(teamsContext, teamId, competenceTeam);
    }

    @Override
    public PersonModel getPerson(String confluenceId) {
        PeopleContext peopleContext = new PeopleContext();
        return (PersonModel) bandanaManager.getValue(peopleContext, confluenceId);
    }

    @Override
    public TeamModel getTeam(String teamId) {
        TeamsContext teamsContext = new TeamsContext();
        return (TeamModel) bandanaManager.getValue(teamsContext, teamId);
    }

    @Override
    public void deletePerson(String confluenceId) {
        PeopleContext peopleContext = new PeopleContext();
        bandanaManager.removeValue(peopleContext, confluenceId);
    }

    @Override
    public void deleteTeam(String teamId) {
        TeamsContext teamsContext = new TeamsContext();
        bandanaManager.removeValue(teamsContext, teamId);
    }


	@Override
	public void addCompetenceToPerson(String id) {
		PeopleContext peopleContext = new PeopleContext();
		bandanaManager.getValue(peopleContext, id);
		
	}
}