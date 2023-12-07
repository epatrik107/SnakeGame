import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

class SnakePanel extends JPanel {
    private boolean paused = false;
    private static final int B_WIDTH = 650;
    private static final int B_HEIGHT = 650;
    private static final int DOT_SIZE = 10;
    private static final int ALL_DOTS = 900;
    private int[] xCoo = new int[ALL_DOTS];
    private int[] yCoo = new int[ALL_DOTS];
    private int dots;
    private int appleX;
    private int appleY;
    private GameState gameState;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private final Random random = new Random();

    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private boolean addedToScoreboard = false;


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(B_WIDTH, B_HEIGHT); // Játékmező méretének beállítása
    }


    public SnakePanel() {
        initBoard();
        requestFocusInWindow();
        gameState = new GameState();


    }

    /**
     * Saves the current game state to a serialized file.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */

    private void saveGameState() throws IOException {
        gameState.saveGameState(xCoo, yCoo, dots, appleX, appleY, leftDirection, rightDirection, upDirection, downDirection, inGame, Util.getSnakeColor(), Util.getSnakeSpeed(), Util.getSnakeLength());
        try (FileOutputStream fileOut = new FileOutputStream("gameState.ser"); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(gameState);

        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    /**
     * Loads the game state from a serialized file.
     */
   private void loadGameState() {
       try (FileInputStream fileIn = new FileInputStream("gameState.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {

           gameState = (GameState) in.readObject();
           xCoo = gameState.getX();
           yCoo = gameState.getY();
           dots = gameState.getDots();
           appleX = gameState.getAppleX();
           appleY = gameState.getAppleY();
           leftDirection = gameState.isLeftDirection();
           rightDirection = gameState.isRightDirection();
           upDirection = gameState.isUpDirection();
           downDirection = gameState.isDownDirection();
           inGame = gameState.isInGame();
           Util.setSnakeColor(gameState.getSnakeColor());
           Util.setSnakeSpeed(gameState.getSnakeSpeed());
           Util.setSnakeLength(gameState.getSnakeLength());

       } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
       }
   }

    /**
     * Initializes the game board and sets up the initial game state.
     */
    private void initBoard() {
        addKeyListener(new TAdapter());
        requestFocusInWindow();
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
    }
    /**
     * Initializes the game by setting up the snake and apple positions, and starts the game timer.
     */
    public void initGame() {
        dots = Util.getSnakeLength();

        for (int z = 0; z < dots; z++) {
            xCoo[z] = 50 - z * 10;
            yCoo[z] = 50;
        }

        locateApple();

        Timer timer = new Timer(Util.getSnakeSpeed(), e -> {
            if (inGame && !paused) { // Only move the snake if the game is not paused
                checkApple();
                move();
                checkCollision();
            }
            repaint();
        });
        timer.start();
    }
    /**
     * Checks if the snake has eaten the apple and updates the game state accordingly.
     */
    private void checkApple() {
        if ((xCoo[0] == appleX) && (yCoo[0] == appleY)) {
            dots++;
            locateApple();
        }
    }
    /**
     * Moves the snake based on its direction.
     */
    private void move() {
        for (int z = dots; z > 0; z--) {
            xCoo[z] = xCoo[(z - 1)];
            yCoo[z] = yCoo[(z - 1)];
        }

        if (leftDirection) {
            xCoo[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            xCoo[0] += DOT_SIZE;
        }

        if (upDirection) {
            yCoo[0] -= DOT_SIZE;
        }

        if (downDirection) {
            yCoo[0] += DOT_SIZE;
        }

    }
    /**
     * Checks for a collision between the snake and the game borders or itself.
     */
    private void checkCollision() {
        Scoreboard scoreboard;
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (xCoo[0] == xCoo[z]) && (yCoo[0] == yCoo[z])) {
                inGame = false;
                break;
            }
        }
        if (yCoo[0] >= B_HEIGHT || yCoo[0] < 0 || xCoo[0] >= B_WIDTH || xCoo[0] < 0) {
            inGame = false;

        }
        if (!inGame && !addedToScoreboard) {

            // Játék vége - hozzáadás a Scoreboard-hoz, de ne jelenítse meg
            scoreboard = Scoreboard.getInstance();  // Csak egyszer hozzuk létre
            scoreboard.addScore(dots - Util.getSnakeLength());
            addedToScoreboard = true;
            scoreboard.saveScores();
        }

    }
    /**
     * Checks for a collision between the snake and the game borders or itself.
     */
    private void locateApple() {
        boolean appleOnSnake = true;
        while (appleOnSnake) {
            appleX = random.nextInt(650 / DOT_SIZE) * DOT_SIZE;
            appleY = random.nextInt(650 / DOT_SIZE) * DOT_SIZE;

            appleOnSnake = false;
            for (int z = 0; z < dots; z++) {
                if (appleX == xCoo[z] && appleY == yCoo[z]) {
                    appleOnSnake = true;
                    break;
                }
            }
        }
    }

    /**
     * Overrides the paintComponent method to render the game elements on the panel.
     *
     * @param g The Graphics object used for rendering.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!paused) {
            doDrawing(g);
        }
    }
    /**
     * Draws the game elements on the panel, including the snake, apple, and score.
     *
     * @param g The Graphics object used for drawing.
     */
    private void doDrawing(Graphics g) {
        if (inGame) {
            if (!paused) {
                g.setColor(Color.red);
                g.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE);

                for (int z = 0; z < dots; z++) {
                    if (z == 0) {
                        g.setColor(Color.green);
                        g.fillRect(xCoo[z], yCoo[z], DOT_SIZE, DOT_SIZE);
                    } else {
                        g.setColor(Util.getSnakeColor());
                        g.fillRect(xCoo[z], yCoo[z], DOT_SIZE, DOT_SIZE);
                    }
                }
                String score = "Score: " + (dots - Util.getSnakeLength()); // Assuming each apple increases 'dots' by 1
                g.setColor(Color.white);
                g.drawString(score, B_WIDTH - 75, 20);
                Toolkit.getDefaultToolkit().sync();
            }
        } else {
            gameOver(g);

        }


    }
    /**
     * Displays the "Game Over" message when the game ends.
     *
     * @param g The Graphics object used for drawing.
     */
    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);


    }
    /**
     * Displays the pause screen with options to save, load, or return to the menu.
     */
    private void showPauseScreen() {
        JFrame pauseFrame = new JFrame("Pause Menu");
        pauseFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pauseFrame.setSize(200, 200);
        pauseFrame.setLocationRelativeTo(null);
        pauseFrame.setLayout(new GridLayout(4, 1));

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton menuButton = new JButton("Menu");

        saveButton.addActionListener(e -> {
            try {
                saveGameState();
            } catch (IOException ex) {
                throw new IllegalArgumentException(ex);
            }
        });

        loadButton.addActionListener(e -> loadGameState());

        menuButton.addActionListener(e -> {
            SnakeGame.getFrame().dispose();
            pauseFrame.dispose();
            pauseFrame.setVisible(false);
            new SnakeGame();

        });

        pauseFrame.add(new JLabel("Paused", SwingConstants.CENTER));
        pauseFrame.add(saveButton);
        pauseFrame.add(loadButton);
        pauseFrame.add(menuButton);

        pauseFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                paused = false; // Játék folytatása a bezáráskor
                requestFocusInWindow(); // Visszanyerjük a fókuszt a játékmezőre
            }
        });

        // Megjelenítjük a dialógust
        pauseFrame.setVisible(true);
    }

    /**
     * Hides the pause screen and resumes the game when the pause window is closed.
     */
    public void hidePauseScreen() {
        addKeyListener(new TAdapter());
        removeAll();
        revalidate();
        repaint();
        paused = false;
    }
    /**
     * Inner class that handles keyboard input for controlling the snake's direction.
     */
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE) {
                paused = !paused;
                if (!paused) {
                    hidePauseScreen();
                } else {
                    showPauseScreen();
                }
            }

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

}
