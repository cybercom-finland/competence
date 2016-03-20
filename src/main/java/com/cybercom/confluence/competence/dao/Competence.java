package com.cybercom.confluence.competence.dao;

import java.util.List;

import net.java.ao.Entity;
import net.java.ao.schema.Table;

/**
 * An active object describing a competence tag and related level.
 */
@Table("COMPETENCE_COMPETENCE")
public interface Competence extends Entity {
    /**
     * @return For example: "JavaScript"
     */
    public String getTag();
    public void setTag(String tag);
    /**
     * @return The list of Confluence ids who have endorsed this competence for this person.
     */
    public List<String> getEndorsements();
    public void setEndorsements(List<String> endorsements);
}
