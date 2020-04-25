package org.assurance.service;

import org.assurance.entity.Alphabet;

import javax.ejb.Stateless;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BoggleService {

    private int boardSize;

    private void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     *
     * @param n Size of board
     * @return List of Alphabet objects that consist of random alphabets along with their neighnors on the board
     */
    public List<Alphabet> getRandomAlphabets(Integer n){
        setBoardSize(n);
        String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<Character> selectedAlphabets = new ArrayList<>();
        for(int i = 0; i < boardSize*boardSize; i++){
            int index = (int)(ALPHABETS.length() * Math.random());
            Character c = ALPHABETS.charAt(index);
            ALPHABETS = ALPHABETS.substring(0,index) + ALPHABETS.substring(index+1, ALPHABETS.length());
            selectedAlphabets.add(c);
        }

        // Create a 2D array of selected alphabets
        Character[][] matrix = insertAlphabetsIntoMatrix(selectedAlphabets);

        List<Alphabet> returnAlphabets = new ArrayList<>();
        for( Character c: selectedAlphabets){
            returnAlphabets.add(new Alphabet(c,getNeighboringAlphabets(matrix, c)));
        }
        return returnAlphabets;
    }

    /**
     *
     * @param alphabets List of selected alphabets
     * @return A 2D array consisting of the selected alphabets, the represents the boggle board
     */
    private Character[][] insertAlphabetsIntoMatrix(List<Character> alphabets){
        int counter = 0;
        Character[][] matrix = new Character[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                matrix[i][j] = alphabets.get(counter);
                counter++;
            }
        }
        return matrix;
    }

    /**
     *
     * @param matrix 2D array representing the boggle board
     * @param alphabet selected alphabet
     * @return Coordinates of an alphabet on the board as an array on size 2. Example - [0,0], [0,1], etc
     */
    private int[] getCoordinatesOfAlphabet(Character[][] matrix, Character alphabet){
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if (matrix[i][j] == alphabet){
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    /**
     *
     * @param matrix 2D array representing the boggle board
     * @param coordinates Coordinates of an alphabet on the board as an array on size 2.
     * @return alphabet from the given coordinates
     */
    private Character getAlphabetFromCoordinates(Character[][] matrix, int[] coordinates){
        return matrix[coordinates[0]][coordinates[1]];
    }

    /**
     *
     * @param matrix 2D array representing the boggle board
     * @param c Current alphabet at hand
     * @return List of characters that lie adjacent to the current alphabet on the board
     */
    private List<Character> getNeighboringAlphabets(Character[][] matrix, Character c){
        int[] coordinates = getCoordinatesOfAlphabet(matrix, c);
        Integer x = coordinates[0];
        Integer y = coordinates[1];
        List neighbors = new ArrayList<Character>();
        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){
                if (i >= 0 && i < boardSize){
                    if (j >= 0 && j < boardSize){
                        if (!(i == x && j == y)){
                            neighbors.add(getAlphabetFromCoordinates(matrix, new int[] {i,j}));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Validates the word against a local dictionary txt file
     * @param word Currently played word
     * @return true or false
     */
    public Boolean isWordValid(String word){
        try {
            InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream("dictionary.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String i;
            while ((i=reader.readLine()) != null){
                if (word.equals(i)){
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
