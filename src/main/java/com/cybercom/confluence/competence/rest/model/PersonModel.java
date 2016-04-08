package com.cybercom.confluence.competence.rest.model;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonModel {
    @XmlElement(name = "id")
    private String id;

    /**
     * Map from the competence name to the list of Confluence ids who have endorsed this.
     */
    @XmlElement(name = "competences")
    private Map<CompetenceModel, List<PersonModel>> competences;

    /**
     * Map from the competence name to the list of Confluence ids who have endorsed this.
     * Note that the one who has suggested this is always the only endorser here.
     */
    @XmlElement(name = "suggestedCompetences")
    private Map<CompetenceModel, List<PersonModel>> suggestedCompetences;

    public PersonModel() {
    }

    public PersonModel(String id){
    	this.id = id;
    }
    
    public PersonModel(String id, Map<CompetenceModel, List<PersonModel>> competences) {
        this.id = id;
        this.competences = competences;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setCompetences(Map<CompetenceModel, List<PersonModel>> competences) {
        this.competences = competences;
    }
    
    public Map<CompetenceModel, List<PersonModel>> getCompetences() {
        return competences;
    }

    public void setSuggestedCompetences(Map<CompetenceModel, List<PersonModel>> suggestedCompetences) {
        this.suggestedCompetences = suggestedCompetences;
    }
    
    public Map<CompetenceModel, List<PersonModel>> getSuggestedCompetences() {
        return suggestedCompetences;
    }
}