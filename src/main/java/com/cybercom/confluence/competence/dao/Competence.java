package com.cybercom.confluence.competence.dao;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.Table;

/**
 * An active object describing a competence tag and related level.
 */
@Preload
@Table("C_COMPETENCE")
public interface Competence extends Entity {
    /**
     * @return For example: "JavaScript"
     */
    @NotNull
    public String getTag();
    public void setTag(String tag);
    /**
     * @return The list of Confluence ids who have endorsed this competence for this person.
     * This is a JSON array.
     */
    public String getEndorsements();
    public void setEndorsements(String endorsements);
    
    public Person getPerson();
    public void setPerson(Person person);
}
