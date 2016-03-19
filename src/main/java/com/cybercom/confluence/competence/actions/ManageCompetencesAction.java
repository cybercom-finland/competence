package com.cybercom.confluence.competence.actions;

import com.atlassian.confluence.user.actions.AbstractUserProfileAction;

public class ManageCompetencesAction extends AbstractUserProfileAction {
    private static final long serialVersionUID = 1L;
    
    public ManageCompetencesAction() {
    }
    
    public String execute()
    {
        return "success";
    }
}
