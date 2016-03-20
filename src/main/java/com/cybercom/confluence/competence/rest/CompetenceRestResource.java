package com.cybercom.confluence.competence.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.cybercom.confluence.competence.rest.model.CompetenceRestStringListModel;
import com.cybercom.confluence.competence.rest.model.CompetenceRestStringModel;
import com.cybercom.confluence.competence.service.CompetenceService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    private CompetenceService competenceComponent;
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final int MAX_RESULTS = 10;
    private final String redisHost;
    private final JedisPool pool;

    public CompetenceRestResource(CompetenceService competenceComponent) {
        this.competenceComponent = competenceComponent;
        
        // TODO: Put in a cloud Redis here.
        redisHost = "localhost";
        // 30s timeout for the flushAll.
        pool = new JedisPool(new JedisPoolConfig(), redisHost, 6379, 30 * 1000);
        
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
        System.out.println("Found: " + range.toString());
        System.out.println("CompetenceComponent: " + competenceComponent);
        jedis.close();
        return Response.ok(new CompetenceRestStringListModel(new ArrayList<String>(range))).build();
    }

    @GET
    @AnonymousAllowed
    @Path("people")
    public Response getPeople()
    {
        return Response.ok(new CompetenceRestStringListModel(competenceComponent.getAllPeople())).build();
    }

    @GET
    @AnonymousAllowed
    @Path("teams")
    public Response getTeams()
    {
       return Response.ok(new CompetenceRestStringModel("Teams")).build();
    }
}
