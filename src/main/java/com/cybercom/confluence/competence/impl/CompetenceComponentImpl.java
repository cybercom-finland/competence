package com.cybercom.confluence.competence.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.cybercom.confluence.competence.api.CompetenceComponent;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService ({CompetenceComponent.class})
@Named ("competence")
public class CompetenceComponentImpl implements CompetenceComponent
{
    @ComponentImport
    private final ApplicationProperties applicationProperties;

    @Inject
    public CompetenceComponentImpl(final ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    public String getName()
    {
        if(null != applicationProperties)
        {
            return "competenceComponent:" + applicationProperties.getDisplayName();
        }
        
        return "competenceComponent";
    }
}