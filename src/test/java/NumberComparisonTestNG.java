import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparisonTestNG {

    @Test
    public void testFirstGreater() {
        assertEquals(NumberComparison.compare(5, 3), "Первое число больше");
    }

    @Test
    public void testSecondGreater() {
        assertEquals(NumberComparison.compare(3, 5), "Второе число больше");
    }

    @Test
    public void testEqual() {
        assertEquals(NumberComparison.compare(4, 4), "Числа равны");
    }
}