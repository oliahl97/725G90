// HighScoreManager.java
package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Denna klass hanterar spelets high score-lista.
 * 
 * Sparar och laddar poäng från en fil och håller de högsta poängen sorterade.
 */
public class HighScoreManager {

    private static final String HIGHSCORE_FILE = "highscores.txt";
    private static final int MAX_SCORES = 10;
    private List<Integer> highScores;

    public HighScoreManager() {
        highScores = loadHighScores();
    }

    /**
     * Laddar highscore-listan från en fil.
     * Om filen saknas skapas en tom lista.
     * 
     * @return En lista med high scores.
     */
    private List<Integer> loadHighScores() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("Ingen tidigare highscore-lista hittades.");
        }
        Collections.sort(scores, Collections.reverseOrder());  // Sortera högsta först
        return scores;
    }

    private void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            for (int score : highScores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            System.out.println("Kunde inte spara highscore-listan.");
        }
    }
    
    private void sortHighScores() {
    	 Collections.sort(highScores, Collections.reverseOrder());  // Sortera högsta först
         if (highScores.size() > MAX_SCORES) {
             highScores.remove(highScores.size() - 1);  // Ta bort lägsta poängen om det finns fler än 10
         }
    }

    public void addScore(int score) {
        highScores.add(score);
        sortHighScores();
        saveHighScores();
    }

    public List<Integer> getHighScores() {
        return highScores;
    }
}