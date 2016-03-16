package com.cybercom.confluence.competence.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.cybercom.confluence.competence.api.CompetenceComponent;

import javax.inject.Named;

@ExportAsService ({CompetenceComponent.class})
@Named ("competence")
public class CompetenceComponentImpl implements CompetenceComponent
{
    public CompetenceComponentImpl()
    {
    }

    public String getName()
    {
        return "competenceComponent";
    }
}