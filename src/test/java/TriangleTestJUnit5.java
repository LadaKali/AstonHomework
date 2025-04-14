import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTestJUnit5 {

    @Test
    void testValidTriangle() {
        assertEquals(6.0, Triangle.calculateArea(3, 4, 5), 0.0001);
    }

    @Test
    void testNegativeSide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Triangle.calculateArea(-3, 4, 5);
        });
        assertEquals("Числа должны быть положительными", exception.getMessage());
    }

    @Test
    void testInvalidTriangle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Triangle.calculateArea(1, 1, 3);
        });
        assertEquals("Неверные стороны треугольника", exception.getMessage());
    }

    @Test
    void testEquilateralTriangle() {
        assertEquals(4.330127, Triangle.calculateArea(5, 5, 5), 0.0001);
    }
}