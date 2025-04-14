import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticTestNG {

    @Test
    public void testAdd() {
        assertEquals(Arithmetic.add(2, 3), 5);
    }

    @Test
    public void testSubtract() {
        assertEquals(Arithmetic.subtract(2, 3), -1);
    }

    @Test
    public void testMultiply() {
        assertEquals(Arithmetic.multiply(2, 3), 6);
    }

    @Test
    public void testDivide() {
        assertEquals(Arithmetic.divide(6, 3), 2.0, 0.0001);
    }

    @Test(expectedExceptions = ArithmeticException.class, expectedExceptionsMessageRegExp = "Деление на ноль")
    public void testDivideByZero() {
        Arithmetic.divide(5, 0);
    }
}

