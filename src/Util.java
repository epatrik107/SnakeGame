import java.awt.Color;

public class Util {
    // Default snake color
    private static Color snakeColor = Color.BLUE;

    // Default snake speed
    private static int snakeSpeed = 80;

    // Default snake length
    private static int snakeLength = 4;

    // Private constructor to prevent instantiation
    private Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Gets the current snake color.
     *
     * @return The current snake Color.
     */
    public static Color getSnakeColor() {
        return snakeColor; // Returning current snake color
    }

    /**
     * Sets the snake color.
     *
     * @param snakeColor The new Color for the snake.
     */
    public static void setSnakeColor(Color snakeColor) {
        Util.snakeColor = snakeColor; // Setting new snake color
    }

    /**
     * Gets the current snake speed.
     *
     * @return The current snake speed.
     */
    public static int getSnakeSpeed() {
        return snakeSpeed; // Returning current snake speed
    }

    /**
     * Sets the snake speed.
     *
     * @param snakeSpeed The new speed for the snake.
     */
    public static void setSnakeSpeed(int snakeSpeed) {
        Util.snakeSpeed = snakeSpeed; // Setting new snake speed
    }

    /**
     * Gets the current snake length.
     *
     * @return The current snake length.
     */
    public static int getSnakeLength() {
        return snakeLength; // Returning current snake length
    }

    /**
     * Sets the snake length.
     *
     * @param snakeLength The new length for the snake.
     */
    public static void setSnakeLength(int snakeLength) {
        Util.snakeLength = snakeLength; // Setting new snake length
    }
}
