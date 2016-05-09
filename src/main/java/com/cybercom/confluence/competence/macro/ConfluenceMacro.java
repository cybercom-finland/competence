package com.cybercom.confluence.competence.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.confluence.xhtml.api.MacroDefinitionHandler;
import com.atlassian.confluence.xhtml.api.XhtmlContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfluenceMacro implements Macro
{
    private final XhtmlContent xhtmlUtils;

    public ConfluenceMacro(XhtmlContent xhtmlUtils)
    {
        this.xhtmlUtils = xhtmlUtils;
    }

    @Override
    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException
    {
        String body = conversionContext.getEntity().getBodyAsString();

        final List<MacroDefinition> macros = new ArrayList<MacroDefinition>();

        try
        {
            xhtmlUtils.handleMacroDefinitions(body, conversionContext, new MacroDefinitionHandler()
            {
                @Override
                public void handle(MacroDefinition macroDefinition)
                {
                    macros.add(macroDefinition);
                }
            });
        }
        catch (XhtmlException e)
        {
            throw new MacroExecutionException(e);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<p>");
        if (!macros.isEmpty())
        {
            builder.append("<table width=\"50%\">");
            builder.append("<tr><th>Macro Name</th><th>Has Body?</th></tr>");
            for (MacroDefinition defn : macros)
            {
                builder.append("<tr>");
                builder.append("<td>").append(defn.getName()).append("</td><td>").append(defn.hasBody()).append("</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
        }
        else
        {
            builder.append("You've done built yourself a macro! Nice work.");
        }
        builder.append("</p>");

        return builder.toString();
        
    }
    
    @Override
    public BodyType getBodyType()
    {
        return BodyType.NONE;
    }

    @Override
    public OutputType getOutputType()
    {
        return OutputType.BLOCK;
    }
}