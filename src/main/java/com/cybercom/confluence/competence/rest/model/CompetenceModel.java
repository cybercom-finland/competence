package com.cybercom.confluence.competence.rest.model;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceModel {

    @XmlElement(name = "value")
    private String message;

    public CompetenceModel() {
    }

    public CompetenceModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}