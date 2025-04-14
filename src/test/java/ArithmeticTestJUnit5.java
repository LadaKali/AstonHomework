import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTestJUnit5 {

    @Test
    void testAdd() {
        assertEquals(5, Arithmetic.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(-1, Arithmetic.subtract(2, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(6, Arithmetic.multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, Arithmetic.divide(6, 3), 0.0001);
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Arithmetic.divide(5, 0);
        });
        assertEquals("Деление на ноль", exception.getMessage());
    }
}