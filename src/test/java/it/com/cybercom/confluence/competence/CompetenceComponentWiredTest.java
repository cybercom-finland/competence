package it.com.cybercom.confluence.competence;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;
import com.cybercom.confluence.competence.service.CompetenceService;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class CompetenceComponentWiredTest
{
    private final ApplicationProperties applicationProperties;
    private final CompetenceService competenceService;

    public CompetenceComponentWiredTest(ApplicationProperties applicationProperties, CompetenceService competenceService)
    {
        this.applicationProperties = applicationProperties;
        this.competenceService = competenceService;
    }

    @Test
    public void testMyName()
    {
        assertEquals("names do not match!", "competenceService", competenceService.getName());
    }
}