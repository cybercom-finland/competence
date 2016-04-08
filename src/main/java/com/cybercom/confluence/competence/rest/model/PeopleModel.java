package com.cybercom.confluence.competence.rest.model;

import java.util.List;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PeopleModel {

    @XmlElement(name = "value")
    private List<PersonModel> peopleList;
    
    public PeopleModel() {
    }

    public PeopleModel(List<PersonModel> peopleList) {
        this.peopleList = peopleList;
    }

    public List<PersonModel> getPeople() {
        return peopleList;
    }

    public void setPeople(List<PersonModel> peopleList) {
        this.peopleList = peopleList;
    }
}