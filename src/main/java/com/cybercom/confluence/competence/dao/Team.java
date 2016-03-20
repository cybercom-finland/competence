package com.cybercom.confluence.competence.dao;

import java.util.List;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.Table;

/**
 * An active object describing a team.
 */
@Preload
@Table("COMPETENCE_TEAM")
public interface Team extends Entity {
    public String getName();
    public void setName(String name);
    /**
     * @return The list of Confluence ids who are in this team.
     */
    public List<String> getEndorsements();
    public void setEndorsements(List<String> endorsements);
}
