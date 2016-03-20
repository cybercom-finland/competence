package com.cybercom.confluence.competence.rest.model;

import java.util.Map;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetencePersonModel {

    @XmlElement(name = "competences")
    private Map<String, Integer> competences;

    public CompetencePersonModel() {
    }

    public CompetencePersonModel(Map<String, Integer> competences) {
        this.competences = competences;
    }
}