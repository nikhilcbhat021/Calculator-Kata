import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.calculator.Calculator;

public class CalculatorTest {

    private final Calculator calcInstance;

    public CalculatorTest() {
        this.calcInstance = new Calculator();
    }

    @Test
    void testSimpleEmptyInputString() {
        System.out.println("testSimpleEmptyInputString , NIKHIL c bhat");
        assertEquals(0, calcInstance.add(""), 0);
    }

}
