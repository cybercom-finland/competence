package com.cybercom.confluence.competence.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceRestResourceModel {

    @XmlElement(name = "value")
    private String message;

    public CompetenceRestResourceModel() {
    }

    public CompetenceRestResourceModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}