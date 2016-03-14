package ut.com.cybercom.confluence.competence.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.cybercom.confluence.competence.rest.CompetenceRestResource;
import com.cybercom.confluence.competence.rest.CompetenceRestResourceModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

public class CompetenceRestResourceTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {
        CompetenceRestResource resource = new CompetenceRestResource();

        Response response = resource.getCompetences();
        final CompetenceRestResourceModel message = (CompetenceRestResourceModel) response.getEntity();

        assertEquals("wrong message","Competences",message.getMessage());
    }
}
