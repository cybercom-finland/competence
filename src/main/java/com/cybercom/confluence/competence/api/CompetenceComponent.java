package com.cybercom.confluence.competence.api;

import java.util.List;

import com.cybercom.confluence.competence.rest.CompetencePersonModel;

public interface CompetenceComponent
{
    String getName();

    List<String> getAllPeople();

    void putPerson(String name, CompetencePersonModel person);
}