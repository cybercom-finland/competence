package ut.com.cybercom.confluence.competence;

import org.junit.Test;
import com.cybercom.confluence.competence.api.MyPluginComponent;
import com.cybercom.confluence.competence.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}