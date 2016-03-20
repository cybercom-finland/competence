package com.cybercom.confluence.competence.rest.model;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceRestPersonModel {
    @XmlElement(name = "id")
    private String id;

    /**
     * Map from the competence name to the list of Confluence ids who have endorsed this.
     */
    @XmlElement(name = "competences")
    private Map<String, List<String>> competences;

    /**
     * Map from the competence name to the list of Confluence ids who have endorsed this.
     * Note that the one who has suggested this is always the only endorser here.
     */
    @XmlElement(name = "suggestedCompetences")
    private Map<String, List<String>> suggestedCompetences;

    public CompetenceRestPersonModel() {
    }

    public CompetenceRestPersonModel(String id, Map<String, List<String>> competences) {
        this.id = id;
        this.competences = competences;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setCompetences(Map<String, List<String>> competences) {
        this.competences = competences;
    }
    
    public Map<String, List<String>> getCompetences() {
        return competences;
    }

    public void setSuggestedCompetences(Map<String, List<String>> suggestedCompetences) {
        this.suggestedCompetences = suggestedCompetences;
    }
    
    public Map<String, List<String>> getSuggestedCompetences() {
        return suggestedCompetences;
    }
}