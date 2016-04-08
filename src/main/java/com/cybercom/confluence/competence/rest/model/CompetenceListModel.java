package com.cybercom.confluence.competence.rest.model;

/**
 * Holds a list of all competences
 */

import java.util.List;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceListModel {

    @XmlElement(name = "value")
    private List<String> results;

    public CompetenceListModel() {
    }

    public CompetenceListModel(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}