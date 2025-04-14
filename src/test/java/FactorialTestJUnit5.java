import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTestJUnit5 {

    @Test
    void testFactorialZero() {
        assertEquals(1, Factorial.calculateFactorial(0));
    }

    @Test
    void testFactorialOne() {
        assertEquals(1, Factorial.calculateFactorial(1));
    }

    @Test
    void testFactorialFive() {
        assertEquals(120, Factorial.calculateFactorial(5));
    }

    @Test
    void testFactorialNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Factorial.calculateFactorial(-1);
        });
        assertEquals("Число должно быть положительным", exception.getMessage());
    }
}