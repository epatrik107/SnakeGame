import javax.swing.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SnakeGameTest {

    @Test
    public void startGame_addsGameLogicToFrame() {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.startGame();

        assertNotNull(SnakeGame.getFrame().getContentPane().getComponent(0));
        assertTrue(SnakeGame.getFrame().getContentPane().getComponent(0) instanceof SnakePanel);
    }

    @Test
    public void openSettings_opensSettingsFrame() {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.openSettings();

        JFrame settingsFrame = (JFrame) Frame.getFrames()[Frame.getFrames().length - 1];
        assertEquals("Settings", settingsFrame.getTitle());
    }

    @Test
    public void openScoreBoard_opensScoreboardFrame() {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.openScoreBoard();

        JFrame scoreboardFrame = (JFrame) Frame.getFrames()[Frame.getFrames().length - 1];
        assertEquals("Scoreboard", scoreboardFrame.getTitle());
    }


}
