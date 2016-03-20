package com.cybercom.confluence.competence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.bandana.BandanaManager;
import com.cybercom.confluence.competence.dao.Competence;
import com.cybercom.confluence.competence.dao.Person;
import com.cybercom.confluence.competence.dao.Team;
import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;
import com.cybercom.confluence.competence.rest.model.CompetenceRestTeamModel;
import com.cybercom.confluence.competence.service.CompetenceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

public class CompetenceServiceImpl implements CompetenceService
{
    private final ActiveObjects ao;
    private final BandanaManager bandanaManager;

    public CompetenceServiceImpl(ActiveObjects ao, BandanaManager bandanaManager)
    {
        this.ao = ao;
        this.bandanaManager = bandanaManager;
    }
    

    public String getName()
    {
        return "competenceService";
    }

    @Override
    public List<CompetenceRestPersonModel> getAllPeople() throws JSONException {
        System.out.println("BandanaManager: " + this.bandanaManager);
        List<CompetenceRestPersonModel> results = new ArrayList<CompetenceRestPersonModel>();
        for (Person person : ao.find(Person.class)) {
            results.add(personToRestPerson(person));
        }
        return results;
    }

    private CompetenceRestPersonModel personToRestPerson(Person person) throws JSONException {
        CompetenceRestPersonModel competencePerson = new CompetenceRestPersonModel();
        competencePerson.setId(person.getConfluenceId());
        Map<String, List<String>> competences = new HashMap<String, List<String>>();
        for (Competence competence : person.getCompetences()) {
            competences.put(competence.getTag(), toArray(new JSONArray(competence.getEndorsements())));
        }
        competencePerson.setCompetences(competences);
        Map<String, List<String>> suggestedCompetences = new HashMap<String, List<String>>();
        for (Competence suggestedCompetence : person.getSuggestedCompetences()) {
            suggestedCompetences.put(suggestedCompetence.getTag(),
                    toArray(new JSONArray(suggestedCompetence.getEndorsements())));
        }
        competencePerson.setSuggestedCompetences(suggestedCompetences);
        return competencePerson;
    }

    private List<String> toArray(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }


    @Override
    public void putPerson(String confluenceId, CompetenceRestPersonModel competencePerson) {
        final Person person = restPersonToPerson(confluenceId, competencePerson);
        person.save();
    }

    private Person restPersonToPerson(String confluenceId, CompetenceRestPersonModel competencePerson) {
        final Person person = ao.create(Person.class);
        person.setConfluenceId(confluenceId);
        for (Entry<String, List<String>> entry : competencePerson.getCompetences().entrySet()) {
            Competence competence = ao.create(Competence.class);
            competence.setTag(entry.getKey());
            competence.setEndorsements(new JSONArray(entry.getValue()).toString());
            competence.setPerson(person);
            competence.save();
        }
        for (Entry<String, List<String>> entry : competencePerson.getSuggestedCompetences().entrySet()) {
            Competence suggestedCompetence = ao.create(Competence.class);
            suggestedCompetence.setTag(entry.getKey());
            suggestedCompetence.setEndorsements(new JSONArray(entry.getValue()).toString());
            suggestedCompetence.setPerson(person);
            suggestedCompetence.save();
        }
        return person;
    }


    @Override
    public void deletePerson(String confluenceId) {
        Person person = ao.get(Person.class, confluenceId);
        ao.delete(person);
    }

    @Override
    public void deleteTeam(Integer id) {
        Team team = ao.get(Team.class, id);
        ao.delete(team);
    }

    @Override
    public void putTeam(String name, List<String> members) {
        final Team team = ao.create(Team.class);
        team.setName(name);
        team.setMembers(new JSONArray(members).toString());
        team.save();
    }

    @Override
    public CompetenceRestTeamModel getTeam(Integer id) throws JSONException {
        return teamToRestTeam(ao.get(Team.class, id));
    }

    private CompetenceRestTeamModel teamToRestTeam(Team team) throws JSONException {
        return new CompetenceRestTeamModel(team.getID(), team.getName(),
                toArray(new JSONArray(team.getMembers())));
    }


    @Override
    public CompetenceRestPersonModel getPerson(String id) throws JSONException {
        return personToRestPerson(ao.get(Person.class, id));
    }
}