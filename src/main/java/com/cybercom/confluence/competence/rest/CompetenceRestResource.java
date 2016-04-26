package com.cybercom.confluence.competence.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.cybercom.confluence.competence.rest.model.PeopleModel;
import com.cybercom.confluence.competence.rest.model.PersonModel;
import com.cybercom.confluence.competence.rest.model.CompetenceListModel;
import com.cybercom.confluence.competence.rest.model.CompetenceModel;
import com.cybercom.confluence.competence.service.CompetenceService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

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
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CompetenceRestResource {
    @Autowired
    private CompetenceService competenceService;
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final int MAX_RESULTS = 10;
    private final String redisHost;
    private final JedisPool pool;

    public CompetenceRestResource(CompetenceService competenceService) {
        this.competenceService = competenceService;
        
        redisHost = "10.35.49.187";
        String password = "FIXME_REAL_PASSWORD_HERE!";
        // 30s timeout for the flushAll.
        pool = new JedisPool(new JedisPoolConfig(), redisHost, 6379, 30 * 1000, password);
        
        new Thread() {
            public void run() {
                System.out.println("READING ALL ARTICLE TITLES IN FROM WIKIPEDIA...");
                Jedis jedis = pool.getResource();
                InputStream stream = this.getClass().getResourceAsStream("/autocomplete/enwiki-latest-all-titles-in-ns0");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line = null;
                try {
                    jedis.flushDB();
                    while((line = br.readLine()) != null) {
                        // Turning underscores into spaces.
                        line = line.replace('_', ' ');
                        // Add to Redis here.
                        jedis.zadd(AUTOCOMPLETE, 0, line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jedis.close();
                System.out.println("READ ALL ARTICLE TITLES IN FROM WIKIPEDIA INTO TRIE FOR AUTOCOMPLETION!");
            }
        }.start();
    }
    
    @GET
    @AnonymousAllowed
    @Path("autocomplete")
    public Response getAutocomplete(@QueryParam("prefix") String prefix)
    {
        Jedis jedis = pool.getResource();
        String searchParam = "[" + prefix;
        if (prefix.equals("")) {
            searchParam = "-";
        }
        Set<String> range = jedis.zrangeByLex(AUTOCOMPLETE, searchParam, "+", 0, MAX_RESULTS);
        jedis.close();
        
        return Response.ok(new CompetenceListModel(new ArrayList<String>(range))).build();
    }

    @GET
    @AnonymousAllowed
    @Path("people/{id}")
    public Response getPerson(@PathParam("id") String id) throws JSONException
    {
        return Response.ok(competenceService.getPerson(id)).build();
    }

    @GET
    @AnonymousAllowed
    @Path("people")
    public Response getPeople() throws JSONException
    {
        return Response.ok(new CompetenceListModel(competenceService.getAllPeople())).build();
    }

    @POST
    @AnonymousAllowed
    @Consumes("application/json")
    @Path("people/{id}")
    public Response addPerson(@PathParam("id") String id) throws JSONException
    {
    	if(competenceService.getPerson(id) == null){
    		competenceService.putPerson(id, new PersonModel(id));
    	}
        return Response.ok().build();
    }
    
    @POST
    @AnonymousAllowed
    @Consumes("application/json")
    @Path("addCompetence/{id}")
    public Response addCompetence(CompetenceModel cm, @PathParam("id") String id) throws JSONException
    {
    	ArrayList<String> endorser = new ArrayList<String>();
    	endorser.add(id);
    	competenceService.getPerson(id).addCompetence(cm.getName(), endorser);
        return Response.ok(competenceService.getPerson(id).getCompetences()).build();
    }
    
    @GET
    @AnonymousAllowed
    @Path("teams")
    public Response getTeams()
    {
       return Response.ok(new CompetenceModel("Teams")).build();
    }
    
    @GET
    @AnonymousAllowed
    @Path("debug")
    public Response getDebug(){
    	String id = "0";
    	CompetenceModel cm = new CompetenceModel("Java Osaamista");
    	ArrayList<String> endorser = new ArrayList<String>();
    	endorser.add("Anon");

    	competenceService.getPerson(id).addCompetence(cm.getName(), endorser);
        competenceService.addCompetenceToPerson(id);
        //pitäisi muuten toteuttaa funktio competenceService.getPersonsCompetences(id);
    	return Response.ok().build(); //palautetaan tässä vaikka lista personin competenceista
    }
    
    @GET
    @AnonymousAllowed
    @Path("/person/competences/{id}")
    public Response getPersonCompetences(@PathParam("id") String id){
    	return Response.ok(competenceService.getPerson(id).getCompetences()).build();
    }
}
