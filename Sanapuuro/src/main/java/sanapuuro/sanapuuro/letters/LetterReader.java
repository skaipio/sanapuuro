/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class LetterReader {
    private Pattern linePattern = Pattern.compile("([a-z])\\s(\\d\\d?)\\s(0\\.\\d+)\\D*");
    private String englishLettersPath = "assets/english_letters";
    
    public List<Letter> getLetters(){
        List<Letter> letters = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(englishLettersPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                letters.add(this.parseLetterFrom(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(e.getCause());
        }
        return letters;
    }
    
    private Letter parseLetterFrom(String line) {
        Matcher matcher = this.linePattern.matcher(line);
        matcher.matches();
        char character = matcher.group(1).charAt(0);
        int score = Integer.parseInt(matcher.group(2));
        float frequency = Float.parseFloat(matcher.group(3));
        return new Letter(character, score, frequency);
    }
}
