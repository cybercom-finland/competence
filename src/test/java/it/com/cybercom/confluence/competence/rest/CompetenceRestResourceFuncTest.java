package it.com.cybercom.confluence.competence.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.cybercom.confluence.competence.rest.CompetenceRestResource;
import com.cybercom.confluence.competence.rest.CompetenceRestResourceModel;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class CompetenceRestResourceFuncTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {

        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/competence/1.0/message";

        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);

        CompetenceRestResourceModel message = resource.get(CompetenceRestResourceModel.class);

        assertEquals("wrong message","Hello World",message.getMessage());
    }
}
