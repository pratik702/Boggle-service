package org.assurance.service;

import javax.ejb.Stateless;

@Stateless
public class BoggleService {

    public String getRandomAlphabets(){
        return "a,b,c,d";
    }
}
