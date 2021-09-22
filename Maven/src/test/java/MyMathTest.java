import org.junit.Test;

public class MyMathTest {
    @Test(expected = ArithmeticException.class)
    public void zeroDenominatorZeroThrowException() {
         MyMath.divide(1, 0);
    }
}
