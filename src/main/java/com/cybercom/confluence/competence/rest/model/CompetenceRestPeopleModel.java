package com.cybercom.confluence.competence.rest.model;

import java.util.List;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceRestPeopleModel {

    @XmlElement(name = "value")
    private List<CompetenceRestPersonModel> results;

    public CompetenceRestPeopleModel() {
    }

    public CompetenceRestPeopleModel(List<CompetenceRestPersonModel> results) {
        this.results = results;
    }

    public List<CompetenceRestPersonModel> getResults() {
        return results;
    }

    public void setResults(List<CompetenceRestPersonModel> results) {
        this.results = results;
    }
}