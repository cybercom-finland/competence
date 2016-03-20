package com.cybercom.confluence.competence.dao;

import net.java.ao.OneToMany;
import net.java.ao.RawEntity;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.PrimaryKey;
import net.java.ao.schema.Table;

@Table("C_PERSON")
public interface Person extends RawEntity<String> {
    @NotNull
    @PrimaryKey("ID")
    public String getConfluenceId();
    public void setConfluenceId(String confluenceId);
    @OneToMany(reverse = "getPerson")
    public Competence[] getCompetences();
    @OneToMany(reverse = "getPerson")
    public Competence[] getSuggestedCompetences();
}
