import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;

// Frame to manage game settings
public class SettingsFrame extends JFrame {
    private final SnakeGame snakeGame; // Reference to the SnakeGame instance

    /**
     * Constructor to initialize the SettingsFrame.
     *
     * @param snakeGame The SnakeGame instance.
     */
    public SettingsFrame(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
        setupFrame();
        add(createSettingsPanel());
        setVisible(true);
    }

    // Method to configure frame properties
    private void setupFrame() {
        setTitle("Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
    }

    // Creates the panel containing game settings components
    private JPanel createSettingsPanel() {
        JPanel settingsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        settingsPanel.add(createLabel("Snake Color:"));
        settingsPanel.add(createColorButton());

        settingsPanel.add(createLabel("Snake Speed:"));
        settingsPanel.add(createSpeedSlider());

        settingsPanel.add(createLabel("Snake Length:"));
        settingsPanel.add(createLengthSpinner());

        settingsPanel.add(createSaveButton());

        return settingsPanel;
    }

    /**
     * Creates a JLabel with the specified text.
     *
     * @param text The text to be displayed on the label.
     * @return The JLabel with the provided text.
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        return label; // Returning the label
    }

    // Creates a button to choose snake color
    private JButton createColorButton() {
        JButton colorButton = new JButton("Choose Snake Color");
        colorButton.addActionListener(e -> {
            // ActionListener to open color chooser dialog
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            Container contentPane = frame.getContentPane();
            JColorChooser colorChooser = new JColorChooser();
            colorChooser.setPreviewPanel(new JPanel());

            // Removing unwanted color chooser panels
            AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
            for (AbstractColorChooserPanel accp : panels) {
                if (!accp.getDisplayName().equals("Swatches")) {
                    colorChooser.removeChooserPanel(accp);
                }
            }

            contentPane.add(colorChooser, BorderLayout.CENTER);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(event -> {
                Util.setSnakeColor(colorChooser.getColor());
                frame.dispose();
            });

            contentPane.add(okButton, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        return colorButton;
    }

    // Creates a slider to adjust snake speed
    private JSlider createSpeedSlider() {
        JSlider speedSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 80, Util.getSnakeSpeed());
        speedSlider.setInverted(true);
        speedSlider.addChangeListener(e -> Util.setSnakeSpeed(speedSlider.getValue()));
        return speedSlider;
    }

    // Creates a spinner to set snake length
    private JSpinner createLengthSpinner() {
        JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(Util.getSnakeLength(), 1, 100, 1));
        lengthSpinner.addChangeListener(e -> Util.setSnakeLength((int) lengthSpinner.getValue()));
        return lengthSpinner;
    }

    // Creates a button to save settings
    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Sans Serif", Font.BOLD, 20));
        saveButton.addActionListener(e -> {
            snakeGame.updateSnakePanelSettings();
            dispose();
        });
        return saveButton;
    }
}
