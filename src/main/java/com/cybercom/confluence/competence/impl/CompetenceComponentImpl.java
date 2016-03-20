package com.cybercom.confluence.competence.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.cybercom.confluence.competence.api.CompetenceComponent;
import com.cybercom.confluence.competence.rest.CompetencePersonModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

public class CompetenceComponentImpl implements CompetenceComponent
{
    private ActiveObjects ao;

    public CompetenceComponentImpl()
    {
    }
    

    public String getName()
    {
        return "competenceComponent";
    }

    @Override
    public List<String> getAllPeople() {
        System.out.println("AO: " + ao);
        return new ArrayList<String>();
    }

    @Override
    public void putPerson(String name, CompetencePersonModel person) {
    }
}