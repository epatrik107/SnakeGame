import org.junit.Test;
import java.awt.Color;
import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void setSnakeColor_changesColor() {
        Color newColor = Color.RED;
        Util.setSnakeColor(newColor);
        assertEquals(newColor, Util.getSnakeColor());
    }

    @Test
    public void getSnakeSpeed_returnsInitialValue() {
        int expectedSpeed = 80;
        assertEquals(expectedSpeed, Util.getSnakeSpeed());
    }

    @Test
    public void setSnakeSpeed_changesSpeed() {
        int newSpeed = 100;
        Util.setSnakeSpeed(newSpeed);
        assertEquals(newSpeed, Util.getSnakeSpeed());
    }

    @Test
    public void getSnakeLength_returnsInitialValue() {
        int expectedLength = 4;
        assertEquals(expectedLength, Util.getSnakeLength());
    }

    @Test
    public void setSnakeLength_changesLength() {
        int newLength = 6;
        Util.setSnakeLength(newLength);
        assertEquals(newLength, Util.getSnakeLength());
    }
}
