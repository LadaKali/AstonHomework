import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparisonTestJUnit5 {

    @Test
    void testFirstGreater() {
        assertEquals("Первое число больше", NumberComparison.compare(5, 3));
    }

    @Test
    void testSecondGreater() {
        assertEquals("Второе число больше", NumberComparison.compare(3, 5));
    }

    @Test
    void testEqual() {
        assertEquals("Числа равны", NumberComparison.compare(4, 4));
    }
}
