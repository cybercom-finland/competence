package com.cybercom.confluence.competence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.cybercom.confluence.competence.dao.Competence;
import com.cybercom.confluence.competence.dao.Person;
import com.cybercom.confluence.competence.rest.model.CompetenceRestPersonModel;
import com.cybercom.confluence.competence.service.CompetenceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CompetenceServiceImpl implements CompetenceService
{
    private final ActiveObjects ao;

    public CompetenceServiceImpl(ActiveObjects ao)
    {
        this.ao = ao;
    }
    

    public String getName()
    {
        return "competenceComponent";
    }

    @Override
    public List<CompetenceRestPersonModel> getAllPeople() {
        List<CompetenceRestPersonModel> results = new ArrayList<CompetenceRestPersonModel>();
        for (Person person : ao.find(Person.class)) {
            results.add(personToRestPerson(person));
        }
        return results;
    }

    private CompetenceRestPersonModel personToRestPerson(Person person) {
        CompetenceRestPersonModel competencePerson = new CompetenceRestPersonModel();
        competencePerson.setId(person.getConfluenceId());
        Map<String, List<String>> competences = new HashMap<String, List<String>>();
        for (Competence competence : person.getCompetences()) {
            competences.put(competence.getTag(), competence.getEndorsements());
        }
        competencePerson.setCompetences(competences);
        Map<String, List<String>> suggestedCompetences = new HashMap<String, List<String>>();
        for (Competence suggestedCompetence : person.getSuggestedCompetences()) {
            suggestedCompetences.put(suggestedCompetence.getTag(), suggestedCompetence.getEndorsements());
        }
        competencePerson.setSuggestedCompetences(suggestedCompetences);
        return competencePerson;
    }


    @Override
    public void putPerson(String confluenceId, CompetenceRestPersonModel competencePerson) {
        final Person person = ao.create(Person.class);
        person.setConfluenceId(confluenceId);
        person.setSuggestedCompetences(new ArrayList<String>());
        final List<Competence> competences = new ArrayList<Competence>();
        for (Entry<String, List<String>> entry : competencePerson.getCompetences().entrySet()) {
            Competence competence = ao.create(Competence.class);
            competence.setTag(entry.getKey());
            competence.setEndorsements(entry.getValue());
            competence.save();
            competences.add(competence);
        }
        person.setCompetences(competences);
        person.save();
    }


    @Override
    public CompetenceRestPersonModel getPerson(String id) {
        return personToRestPerson(ao.get(Person.class, id));
    }
}