import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Class to manage the scoreboard
class Scoreboard implements Serializable {
    // List to hold scores
    private final List<Integer> scores;
    // Singleton instance of Scoreboard
    private static Scoreboard instance;
    @Serial
    private static final long serialVersionUID = 2L;

    // Constructor initializing the scoreboard
    private Scoreboard() {
        scores = new ArrayList<>();
        loadScores(); // Loading scores in the constructor
    }

    /**
     * Gets the instance of the Scoreboard class.
     *
     * @return The singleton instance of Scoreboard.
     */
    public static Scoreboard getInstance() {
        if (instance == null) {
            instance = new Scoreboard();
        }
        return instance;
    }

    /**
     * Adds a score to the scoreboard.
     *
     * @param score The score to be added.
     */
    public void addScore(int score) {
        scores.add(score);
        scores.sort(Collections.reverseOrder());
    }

    /**
     * Retrieves the list of scores.
     *
     * @return The list containing scores.
     */
    public List<Integer> getScores() {
        return scores;
    }

    /**
     * Saves the scores to a file.
     */
    public void saveScores() {
        try (FileOutputStream fileOut = new FileOutputStream("scores.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads scores from a file.
     */
    protected void loadScores() {
        File file = new File("scores.ser");
        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                instance = (Scoreboard) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the scores in descending order.
     */
    public void updateScores() {
        scores.sort(Collections.reverseOrder());
    }
}
