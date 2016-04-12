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
    private List<String> competences;

    public CompetenceListModel() {
    }

    public CompetenceListModel(List<String> competences) {
        this.competences = competences;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }
    
    public void addCompetence(String newCompetence){
    	if(!competences.contains(newCompetence))
    		competences.add(newCompetence);
    	else
    		System.out.println("The list already contains competence: " + newCompetence);
    }
}