package org.assurance.restful;

import org.assurance.service.BoggleService;
import org.assurance.common.ResponseGen;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class BoggleRestService {

    @Inject
    BoggleService boggleService;

    /**
     * Root page of the API service
     * @return
     */
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStarted(){
        return ResponseGen.success("Boggle API Service");
    }

    /**
     * Returns a list of random alphabets, along with neighboring adjacent alphabets
     * @param n size of the board
     * @return
     */
    @Path("alphabetWordList/{n}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlphabetList(@PathParam("n") Integer n){
        return ResponseGen.success(boggleService.getRandomAlphabets(n));
    }

    /**
     * Validates the word against a local dictionary from a txt file
     * @param word
     * @return
     */
    @Path("validateWord/{word}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateWord(@PathParam("word") String word){
        try{
            if (boggleService.isWordValid(word)){
                return ResponseGen.valid(true);
            }
            return ResponseGen.invalid(false);
        }
        catch (Exception e){
            return ResponseGen.internalError("Error validating word");
        }
    }
}
