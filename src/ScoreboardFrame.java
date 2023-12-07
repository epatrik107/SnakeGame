import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

// Frame displaying the scoreboard
class ScoreboardFrame extends JFrame implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    private final Scoreboard scoreboard; // Reference to the scoreboard

    // Constructor initializing the ScoreboardFrame
    public ScoreboardFrame() {
        scoreboard = Scoreboard.getInstance();
        initializeUI();
    }

    // Sets up the user interface for the ScoreboardFrame
    private void initializeUI() {
        setTitle("Scoreboard"); // Setting title
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        JButton topButton = new JButton("TOP 10");
        topButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        topButton.setEnabled(false);
        topButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        topButton.setHorizontalAlignment(SwingConstants.CENTER);
        topButton.setMaximumSize(new Dimension(120, 30));

        // Centering the TOP 10 button in a separate panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(topButton);
        panel.add(buttonPanel);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        panel.add(scorePanel);

        refreshScores(scorePanel);
    }

    /**
     * Refreshes the scoreboard in the JPanel.
     *
     * @param panel The JPanel to display the scores.
     */
    public void refreshScores(JPanel panel) {
        List<Integer> scores = scoreboard.getScores();

        int size = Math.min(scores.size(), 10);

        for (int i = 0; i < size; i++) {
            JButton scoreButton = new JButton(String.format("%2d.   [*]   %3d", (i + 1), scores.get(i)));
            scoreButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            scoreButton.setEnabled(false);
            panel.add(scoreButton);
        }

        panel.revalidate();
        panel.repaint();
    }
}
