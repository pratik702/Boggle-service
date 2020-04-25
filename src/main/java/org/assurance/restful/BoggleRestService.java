package org.assurance.restful;

import org.assurance.service.BoggleService;
import org.assurance.common.ResponseGen;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class BoggleRestService {

    @Inject
    BoggleService boggleService;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStarted(){
        return ResponseGen.success("Welcome to boggle");
    }

    @Path("alphabetWordList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlphabetList(){
        return ResponseGen.success(boggleService.getRandomAlphabets());
    }
}
