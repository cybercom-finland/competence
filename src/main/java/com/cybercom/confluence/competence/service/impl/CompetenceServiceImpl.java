package com.cybercom.confluence.competence.service.impl;

import com.atlassian.bandana.BandanaManager;
import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;
import com.cybercom.confluence.competence.rest.model.CompetenceRestTeamModel;
import com.cybercom.confluence.competence.service.CompetenceService;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
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
    public List<String> getAllPeople() throws JSONException {
        return Lists.newArrayList(bandanaManager.getKeys(new PeopleContext()));
    }

    @Override
    public List<String> getAllTeams() throws JSONException {
        return Lists.newArrayList(bandanaManager.getKeys(new TeamsContext()));
    }

    @Override
    public void putPerson(String confluenceId, CompetenceRestPersonModel competencePerson) {
        PeopleContext peopleContext = new PeopleContext();
        bandanaManager.setValue(peopleContext, confluenceId, competencePerson);
    }

    @Override
    public void putTeam(String teamId, CompetenceRestTeamModel competenceTeam) {
        TeamsContext teamsContext = new TeamsContext();
        bandanaManager.setValue(teamsContext, teamId, competenceTeam);
    }

    @Override
    public CompetenceRestPersonModel getPerson(String confluenceId) {
        PeopleContext peopleContext = new PeopleContext();
        return (CompetenceRestPersonModel) bandanaManager.getValue(peopleContext, confluenceId);
    }

    @Override
    public CompetenceRestTeamModel getTeam(String teamId) {
        TeamsContext teamsContext = new TeamsContext();
        return (CompetenceRestTeamModel) bandanaManager.getValue(teamsContext, teamId);
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
}