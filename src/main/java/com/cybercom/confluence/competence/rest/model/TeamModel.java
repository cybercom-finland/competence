package com.cybercom.confluence.competence.rest.model;

import java.util.List;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamModel {

    @XmlElement(name = "id")
    private Integer id;
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "people")
    private List<String> personList;

    public TeamModel() {
    }

    public TeamModel(int id, String name, List<String> people) {
        this.id = id;
        this.name = name;
        this.personList = people;
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
    
    public List<String> getPeople() {
        return personList;
    }

    public void setPeople(List<String> people) {
        this.personList = people;
    }
}