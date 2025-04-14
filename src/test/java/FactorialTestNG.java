import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTestNG {

    @Test
    public void testFactorialZero() {
        assertEquals(Factorial.calculateFactorial(0), 1);
    }

    @Test
    public void testFactorialOne() {
        assertEquals(Factorial.calculateFactorial(1), 1);
    }

    @Test
    public void testFactorialFive() {
        assertEquals(Factorial.calculateFactorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Число должно быть положительным")
    public void testFactorialNegative() {
        Factorial.calculateFactorial(-1);
    }
}