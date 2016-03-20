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
        CompetenceService service = new CompetenceServiceImpl(null, null);
        assertEquals("names do not match!", "competenceService", service.getName());
    }
}