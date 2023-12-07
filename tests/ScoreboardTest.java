import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ScoreboardTest {

    @Test
    public void addScore() {
        Scoreboard scoreboard = Scoreboard.getInstance();
        scoreboard.addScore(100);
        scoreboard.addScore(200);
        scoreboard.addScore(80);
        scoreboard.addScore(70);
        scoreboard.addScore(60);
        scoreboard.addScore(50);
        scoreboard.addScore(40);
        scoreboard.addScore(30);
        scoreboard.addScore(0);
        scoreboard.addScore(0);
        assertEquals(10, scoreboard.getScores().size());
        scoreboard.saveScores();
    }

    @Test
    public void getScores() {
        Scoreboard scoreboard = Scoreboard.getInstance();
        scoreboard.loadScores();
        assertNotNull(scoreboard.getScores());
    }

    @Test
    public void updateScores() {
        Scoreboard scoreboard = Scoreboard.getInstance();
        scoreboard.addScore(100);
        scoreboard.addScore(200);
        scoreboard.addScore(60);
        scoreboard.addScore(110);
        scoreboard.addScore(120);
        scoreboard.addScore(150);
        scoreboard.addScore(250);
        scoreboard.addScore(1);
        scoreboard.addScore(2);
        scoreboard.addScore(0);
        scoreboard.updateScores();
        assertTrue(isSortedDesc(scoreboard.getScores()));
        scoreboard.loadScores();
    }

    private boolean isSortedDesc(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
