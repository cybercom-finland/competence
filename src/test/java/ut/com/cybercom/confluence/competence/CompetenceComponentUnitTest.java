package ut.com.cybercom.confluence.competence;

import org.junit.Test;

import com.cybercom.confluence.competence.service.CompetenceService;
import com.cybercom.confluence.competence.service.impl.CompetenceServiceImpl;

import static org.junit.Assert.assertEquals;

public class CompetenceComponentUnitTest
{
    @Test
    public void testMyName()
    {
        CompetenceService component = new CompetenceServiceImpl(null);
        assertEquals("names do not match!", "competenceComponent",component.getName());
    }
}