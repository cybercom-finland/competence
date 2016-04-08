package com.cybercom.confluence.competence.rest.model;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceModel {

    @XmlElement(name = "value")
    private String name;

    public CompetenceModel() {
    }

    public CompetenceModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}