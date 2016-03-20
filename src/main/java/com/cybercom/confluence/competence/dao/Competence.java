package com.cybercom.confluence.competence.dao;

import java.util.List;

import net.java.ao.RawEntity;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.PrimaryKey;
import net.java.ao.schema.Table;

/**
 * An active object describing a competence tag and related level.
 */
@Table("COMPETENCE_COMPETENCE")
public interface Competence extends RawEntity<String> {
    /**
     * @return For example: "JavaScript"
     */
    @NotNull
    @PrimaryKey("ID")
    public String getTag();
    public void setTag(String tag);
    /**
     * @return The list of Confluence ids who have endorsed this competence for this person.
     */
    public List<String> getEndorsements();
    public void setEndorsements(List<String> endorsements);
}
