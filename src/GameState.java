import java.io.Serial;
import java.io.Serializable;
import java.awt.Color;

// Class that stores the game state
public class GameState implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // X coordinates for the segments of the snake
    private int[] xCoor;

    // Y coordinates for the segments of the snake
    private int[] yCoor;

    // Number of segments in the snake
    private int dots;

    // X coordinate of the apple on the board
    private int appleX;

    // Y coordinate of the apple on the board
    private int appleY;

    // Movement directions
    private boolean leftDirection;
    private boolean rightDirection;
    private boolean upDirection;
    private boolean downDirection;

    // Game state
    private boolean inGame;

    // Color of the snake
    private Color snakeColor;

    // Speed of the snake
    private int snakeSpeed;

    // Length of the snake
    private int snakeLength;

    // Getters for the attributes

    public int[] getX() {
        return xCoor;
    }

    public int[] getY() {
        return yCoor;
    }

    public int getDots() {
        return dots;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public boolean isRightDirection() {
        return rightDirection;
    }

    public boolean isUpDirection() {
        return upDirection;
    }

    public boolean isDownDirection() {
        return downDirection;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    // Method to save the game state
    public void saveGameState(int[] x, int[] y, int dots, int appleX, int appleY,
                              boolean leftDirection, boolean rightDirection, boolean upDirection,
                              boolean downDirection, boolean inGame, Color snakeColor,
                              int snakeSpeed, int snakeLength) {
        this.xCoor = x;
        this.yCoor = y;
        this.dots = dots;
        this.appleX = appleX;
        this.appleY = appleY;
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
        this.upDirection = upDirection;
        this.downDirection = downDirection;
        this.inGame = inGame;
        this.snakeColor = snakeColor;
        this.snakeSpeed = snakeSpeed;
        this.snakeLength = snakeLength;
    }
}
