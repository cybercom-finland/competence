package com.cybercom.confluence.competence.service;

import java.util.List;

import com.cybercom.confluence.competence.rest.model.CompetencePersonModel;

public interface CompetenceService
{
    String getName();

    List<String> getAllPeople();

    void putPerson(String name, CompetencePersonModel person);
}