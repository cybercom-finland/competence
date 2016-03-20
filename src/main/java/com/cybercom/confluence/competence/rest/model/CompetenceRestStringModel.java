package com.cybercom.confluence.competence.rest.model;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceRestStringModel {

    @XmlElement(name = "value")
    private String message;

    public CompetenceRestStringModel() {
    }

    public CompetenceRestStringModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}