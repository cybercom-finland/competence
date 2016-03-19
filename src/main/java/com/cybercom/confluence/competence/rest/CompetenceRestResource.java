package com.cybercom.confluence.competence.rest;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private BandanaManager bandanaManager;
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final long MAX_RESULTS = 10;
    // TODO: Put in a cloud Redis here.
    private static final String redisHost = "localhost";
    private static final JedisPool pool = new JedisPool(new JedisPoolConfig(), redisHost);

    public CompetenceRestResource() {
        new Thread() {
            public void run() {
                System.out.println("READING ALL ARTICLE TITLES IN FROM WIKIPEDIA...");
                Jedis jedis = pool.getResource();
                InputStream stream = this.getClass().getResourceAsStream("/autocomplete/enwiki-latest-all-titles-in-ns0");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line = null;
                try {
                    while((line = br.readLine()) != null) {
                        // Add to Redis Trie here.
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
    
    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
    
    @GET
    @AnonymousAllowed
    @Path("autocomplete")
    public Response getAutocomplete(@QueryParam("prefix") String prefix)
    {
        Jedis jedis = pool.getResource();
        Long start = jedis.zrank(AUTOCOMPLETE, prefix);
        if (start == null) {
            // No completions found.
            jedis.close();
            return Response.noContent().build();
        }
        List<String> results = new ArrayList<String>();
        Set<String> range = jedis.zrange(prefix, start, start + MAX_RESULTS - 1);
        for(String entry: range) {
            if(!entry.startsWith(prefix)) {
                break;
            }
            results.add(entry);
        }
        System.out.println("BandanaManager: " + bandanaManager);
        jedis.close();
        return Response.ok(new CompetenceRestStringListModel(results)).build();
    }

    @GET
    @AnonymousAllowed
    @Path("people")
    public Response getPeople()
    {
       return Response.ok(new CompetenceRestStringModel("People")).build();
    }

    @GET
    @AnonymousAllowed
    @Path("teams")
    public Response getTeams()
    {
       return Response.ok(new CompetenceRestStringModel("Teams")).build();
    }
}
