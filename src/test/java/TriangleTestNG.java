import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleTestNG {

    @Test
    public void testValidTriangle() {
        assertEquals(Triangle.calculateArea(3, 4, 5), 6.0, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Числа должны быть положительными")
    public void testNegativeSide() {
        Triangle.calculateArea(-3, 4, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Неверные стороны треугольника")
    public void testInvalidTriangle() {
        Triangle.calculateArea(1, 1, 3);
    }

    @Test
    public void testEquilateralTriangle() {
        assertEquals(Triangle.calculateArea(5, 5, 5), 4.330127, 0.0001);
    }
}