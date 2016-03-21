package com.cybercom.confluence.competence.bandana;

import com.atlassian.bandana.BandanaContext;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;
import com.atlassian.confluence.setup.bandana.KeyedBandanaContext;

public class TeamsContext implements KeyedBandanaContext {
    private static final long serialVersionUID = 1L;

    @Override
    public BandanaContext getParentContext() {
        return new ConfluenceBandanaContext();
    }

    @Override
    public boolean hasParentContext() {
        return true;
    }

    @Override
    public String getContextKey() {
        return "competence_teams";
    }

}
