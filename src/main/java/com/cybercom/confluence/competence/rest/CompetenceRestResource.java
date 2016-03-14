package com.cybercom.confluence.competence.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Returns all the competences and teams for the purposes of drawing the tag cloud.
 * Also implements the PUT/POST for actions:
 * - Endorse, unendorse a competence for a person (PUT competences)
 * - Add/remove person from a team (POST teams)
 * - Define competences for a person (POST competences)
 */
public class CompetenceRestResource {

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/competences")
    public Response getCompetences()
    {
       return Response.ok(new CompetenceRestResourceModel("Competences")).build();
    }

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/teams")
    public Response getTeams()
    {
       return Response.ok(new CompetenceRestResourceModel("Teams")).build();
    }
}