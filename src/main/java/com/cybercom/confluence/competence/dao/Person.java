package com.cybercom.confluence.competence.dao;

import java.util.List;

import net.java.ao.OneToMany;
import net.java.ao.RawEntity;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.PrimaryKey;
import net.java.ao.schema.Table;

@Table("COMPETENCE_PERSON")
public interface Person extends RawEntity<String> {
    @NotNull
    @PrimaryKey("ID")
    public String getConfluenceId();
    public void setConfluenceId(String confluenceId);
    @OneToMany(reverse = "getPerson")
    public List<Competence> getCompetences();
    public void setCompetences(List<Competence> competences);
    @OneToMany(reverse = "getPerson")
    public List<Competence> getSuggestedCompetences();
    public void setSuggestedCompetences(List<Competence> suggestedCompetences);
}
