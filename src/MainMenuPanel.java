import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel class for the main menu
public class MainMenuPanel extends JPanel {
    private final SnakeGame snakeGame; // Reference to the Snake game
    private final Scoreboard scoreboard; // Reference to the scoreboard

    // Constructor
    public MainMenuPanel(SnakeGame snakeGame) {
        this.snakeGame = snakeGame; // Initializing the Snake game
        setupPanel(); // Method to set up the panel
        createButtons(); // Method to create buttons
        scoreboard = Scoreboard.getInstance(); // Instantiating the scoreboard
        scoreboard.loadScores(); // Loading scores
        scoreboard.updateScores(); // Updating scores
    }

    // Method to update the scoreboard
    public void updateScoreboard() {
        scoreboard.updateScores(); // This method will update the scoreboard
    }

    // Method to set up the panel
    private void setupPanel() {
        setBackground(Color.DARK_GRAY); // Setting background color to dark gray
        setLayout(new GridBagLayout()); // Setting layout to GridBagLayout
        setPreferredSize(new Dimension(650, 650)); // Setting preferred size
    }

    // Method to create buttons
    private void createButtons() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding "Play" button
        add(createButton("Play", new Color(144,238,144), e -> snakeGame.startGame()), gbc);

        // Adding space for distance between buttons
        add(Box.createRigidArea(new Dimension(0, 50)));

        // Adding "Score Board" button
        add(createButton("Score Board", new Color(255,165,0), e -> snakeGame.openScoreBoard()), gbc);

        // Adding space for distance between buttons
        add(Box.createRigidArea(new Dimension(0, 50)));

        // Adding "Settings" button
        add(createButton("Settings", new Color(135,206,235), e -> snakeGame.openSettings()), gbc);

        // Adding space for distance between buttons
        add(Box.createRigidArea(new Dimension(0, 50)));

        // Adding "Exit" button
        add(createButton("Exit", new Color(240,128,128), this::exitGame), gbc);
    }

    // Method to create buttons
    /**
     * Creates a button with the given label, color, and ActionListener.
     *
     * @param label          The label text for the button.
     * @param color          The background color for the button.
     * @param actionListener The ActionListener for the button.
     * @return JButton with specified properties.
     */
    private JButton createButton(String label, Color color, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setFont(new Font("Sans Serif", Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setPreferredSize(new Dimension(300, 100));
        button.addActionListener(actionListener);
        return button;
    }

    // Method to exit the game
    /**
     * Exits the Snake game.
     *
     * @param e The ActionEvent triggering the exit.
     */
    private void exitGame(ActionEvent e) {
        System.exit(0); // Exiting the game
    }
}
