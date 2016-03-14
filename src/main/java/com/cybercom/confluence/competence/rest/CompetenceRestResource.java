package com.cybercom.confluence.competence.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Returns all the competences and teams for the purposes of drawing the tag cloud.
 * 
 * GET /people: Returns the list of all people
 * 
 * GET /people/[person]: Returns all the competence and team data for a person.
 * 
 * GET /teams: Returns the list of all teams
 * 
 * PUT /teams: "<TeamName>": Adds a new team with name
 * 
 * DELETE /teams/[team]: Deletes the team
 * 
 * PUT /people/[person]: {<endorser>: {suggest: [<competenceA>], add: [<competenceB>], remove: [<competenceC>]}}
 * - Endorse, unendorse a competence for a person
 * 
 * PUT /teams/[team]: {add: [<personA>, <personB>], remove: [<personC>]}
 * - Add/remove person from a team
 */
public class CompetenceRestResource {

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/people")
    public Response getPeople()
    {
       return Response.ok(new CompetenceRestResourceModel("People")).build();
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