import javax.swing.*;
import java.io.Serializable;
/**
 * The SnakeGame class represents the main class of the Snake Game application.
 * It handles the initialization of the game, main menu, settings, and game logic.
 */
public class SnakeGame implements Serializable {

    // Reference to the main frame
    private static JFrame frame;

    // Reference to the main menu panel
    private final MainMenuPanel mainMenuPanel;

    // Renamed GameLogic object for clarity
    private SnakePanel gameLogic;

    /**
     * Constructor for SnakeGame.
     * Initializes the main frame and the main menu panel.
     */
    public SnakeGame() {
        frame = new JFrame("JAVA CONDA"); // Creating main frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Setting close operation
        frame.setResizable(false); // Making frame not resizable

        mainMenuPanel = new MainMenuPanel(this); // Creating main menu panel
        frame.add(mainMenuPanel); // Adding main menu panel to the frame

        frame.pack(); // Packing components in the frame
        frame.setLocationRelativeTo(null); // Centering the frame on the screen
        frame.setVisible(true); // Setting frame visibility
    }

    /**
     * Retrieves the main frame.
     *
     * @return The main JFrame.
     */
    public static JFrame getFrame() {
        return frame; // Returning the main frame
    }

    /**
     * Starts the game.
     * Removes all content from the frame and initializes the SnakePanel for the game logic.
     */
    public void startGame() {
        frame.getContentPane().removeAll(); // Removing all content from the frame
        gameLogic = new SnakePanel(); // Initializing the SnakePanel for game logic
        frame.add(gameLogic); // Adding game board to the frame

        frame.revalidate(); // Revalidating frame
        frame.repaint(); // Repainting frame
        gameLogic.requestFocusInWindow(); // Requesting focus for game logic
    }

    /**
     * Opens the settings frame.
     */
    public void openSettings() {
        new SettingsFrame(this); // Creating settings frame
    }

    /**
     * Opens the scoreboard frame.
     */
    public void openScoreBoard() {
        ScoreboardFrame scoreboard = new ScoreboardFrame(); // Creating scoreboard frame
        scoreboard.setVisible(true); // Setting scoreboard frame visibility
    }

    /**
     * Updates settings for the SnakePanel and updates the scoreboard.
     */
    public void updateSnakePanelSettings() {
        if (gameLogic != null) { // Checking if SnakePanel is initialized
            gameLogic.initGame(); // Initializing game logic
            mainMenuPanel.updateScoreboard(); // Updating the scoreboard
        }
    }

    /**
     * Main method to start the SnakeGame application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGame::new); // Starting the SnakeGame application
    }
}
