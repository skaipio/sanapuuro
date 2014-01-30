/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.letters;

import sanapuuro.sanapuuro.utils.LetterFrequencyComparator;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author skaipio
 */
public class LetterReader implements LetterPool {

    private Pattern linePattern = Pattern.compile("([a-z])\\s(\\d\\d?)\\s(0\\.\\d+)\\D*");
    private String englishLettersPath = "assets/english_letters";
    private final Random random;
    private final LetterFrequencyComparator letterFreqComparator = new LetterFrequencyComparator();
    private List<Letter> sortedLetters;

    public LetterReader(Random randomizer) {
        this.random = randomizer;
        this.sortedLetters = this.readAndSortLetters();
    }

    private List<Letter> readAndSortLetters() {
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
        Collections.sort(letters, this.letterFreqComparator);
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

    public Letter getLetterMatchingCharacter(char c) {
        if (this.sortedLetters == null) {
            throw new NullPointerException("No letters assigned yet.");
        }
        for (Letter letter : this.sortedLetters) {
            if (letter.character == c) {
                return letter;
            }
        }
        return null;
    }

    public Letter getRandomLetter() {
        if (this.sortedLetters == null) {
            throw new NullPointerException("No letters assigned yet.");
        }
        float rnd = (float) random.nextDouble();
        float accumulated = 0;
        int i = 0;
        Letter letter;
        do {
            letter = this.sortedLetters.get(i);
            accumulated += letter.frequency;
            i++;
        } while (accumulated < rnd && i < this.sortedLetters.size());
        return letter;
    }
}
