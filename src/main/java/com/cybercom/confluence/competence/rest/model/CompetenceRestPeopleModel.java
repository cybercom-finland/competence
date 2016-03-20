package com.cybercom.confluence.competence.rest.model;

import java.util.List;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceRestPeopleModel {

    @XmlElement(name = "value")
    private List<String> results;

    public CompetenceRestPeopleModel() {
    }

    public CompetenceRestPeopleModel(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}