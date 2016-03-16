package com.cybercom.confluence.competence.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.illucit.instatrie.trie.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
@Path("/")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CompetenceRestResource {
    public CompetenceRestResource() {
        new Thread() {
            public void run() {
                System.out.println("READING ALL ARTICLE TITLES IN FROM WIKIPEDIA...");
                InputStream stream = this.getClass().getResourceAsStream("/autocomplete/enwiki-latest-all-titles-in-ns0");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line = null;
                try {
                    while((line = br.readLine()) != null) {
                        // Add to Redis Trie here.
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("READ ALL ARTICLE TITLES IN FROM WIKIPEDIA INTO TRIE FOR AUTOCOMPLETION!");
            }
        }.start();
    }
    
    @GET
    @AnonymousAllowed
    @Path("autocomplete")
    public Response getAutocomplete()
    {
       return Response.ok(new CompetenceRestResourceModel("Autocomplete")).build();
    }

    @GET
    @AnonymousAllowed
    @Path("people")
    public Response getPeople()
    {
       return Response.ok(new CompetenceRestResourceModel("People")).build();
    }

    @GET
    @AnonymousAllowed
    @Path("teams")
    public Response getTeams()
    {
       return Response.ok(new CompetenceRestResourceModel("Teams")).build();
    }
}
