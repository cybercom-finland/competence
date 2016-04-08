package com.cybercom.confluence.competence.rest.model;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamModel {

    @XmlElement(name = "id")
    private Integer id;
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "people")
    private PeopleModel peopleList;

    public TeamModel() {
    }

    public TeamModel(int id, String name, PeopleModel people) {
        this.id = id;
        this.name = name;
        this.peopleList = people;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public PeopleModel getPeople() {
        return peopleList;
    }

    public void setPeople(PeopleModel people) {
        this.peopleList = people;
    }
    
    public void addPerson(PersonModel person){
    	if(!peopleList.getPeople().contains(person)){
    		peopleList.getPeople().add(person);
    	}
    }
    
    public void removePerson(PersonModel person){
    	if(peopleList.getPeople().contains(person)){
    		peopleList.getPeople().remove(person);
    	}
    }
}