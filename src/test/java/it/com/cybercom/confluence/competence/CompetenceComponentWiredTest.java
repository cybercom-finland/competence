package it.com.cybercom.confluence.competence;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.cybercom.confluence.competence.api.CompetenceComponent;
import com.atlassian.sal.api.ApplicationProperties;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class CompetenceComponentWiredTest
{
    private final ApplicationProperties applicationProperties;
    private final CompetenceComponent competenceComponent;

    public CompetenceComponentWiredTest(ApplicationProperties applicationProperties, CompetenceComponent competenceComponent)
    {
        this.applicationProperties = applicationProperties;
        this.competenceComponent = competenceComponent;
    }

    @Test
    public void testMyName()
    {
        assertEquals("names do not match!", "competenceComponent", competenceComponent.getName());
    }
}