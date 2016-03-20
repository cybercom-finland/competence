package com.cybercom.confluence.competence.dao;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.Table;

/**
 * An active object describing a team.
 */
@Preload
@Table("C_TEAM")
public interface Team extends Entity {
    public String getName();
    public void setName(String name);
    /**
     * @return The list of Confluence ids who are in this team.
     * This is a JSON array.
     */
    public String getMembers();
    public void setMembers(String members);
}
