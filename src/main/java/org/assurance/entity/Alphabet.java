package org.assurance.entity;

import java.util.List;

public class Alphabet {

    private Character alphabet;
    private List<Character> neighbors;

    public Character getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Character alphabet) {
        this.alphabet = alphabet;
    }

    public List<Character> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Character> neighbors) {
        this.neighbors = neighbors;
    }

    public Alphabet(Character alphabet, List<Character> neighbors) {
        this.alphabet = alphabet;
        this.neighbors = neighbors;
    }
}
