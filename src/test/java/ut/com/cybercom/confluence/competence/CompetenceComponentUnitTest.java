package ut.com.cybercom.confluence.competence;

import org.junit.Test;
import com.cybercom.confluence.competence.api.CompetenceComponent;
import com.cybercom.confluence.competence.impl.CompetenceComponentImpl;

import static org.junit.Assert.assertEquals;

public class CompetenceComponentUnitTest
{
    @Test
    public void testMyName()
    {
        CompetenceComponent component = new CompetenceComponentImpl(null);
        assertEquals("names do not match!", "competenceComponent",component.getName());
    }
}