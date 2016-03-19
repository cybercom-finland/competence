package com.cybercom.confluence.competence.actions;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.user.actions.AbstractUserProfileAction;

public class ManageCompetencesAction extends AbstractUserProfileAction {
    private static final long serialVersionUID = 1L;
    private BandanaManager bandanaManager;
    
    public ManageCompetencesAction() {
    }
    
    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
    
    public String execute()
    {
        return "success";
    }
}
