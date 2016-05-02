package com.cybercom.confluence.competence.rest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonModel {
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "userName")
    private String userName;

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

    public PersonModel() {
    }

    public PersonModel(String id, String name, String userName){
    	this.id = id;
    	this.name = name;
    	this.userName = userName;
    	this.competences = new HashMap <String, List<String>>();
    }
    
    public PersonModel(String id, String name, String userName, Map<String, List<String>> competences) {
        this.id = id;
        this.name = name;
        this.userName= userName;
        this.competences = competences;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return userName;
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
    
    public void addCompetence(String competence, List<String> endorcers ) {
        this.competences.put(competence, endorcers);
    }
}