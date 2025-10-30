import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.calculator.Calculator;

public class CalculatorTest {

    private final Calculator calcInstance;

    public CalculatorTest() {
        this.calcInstance = new Calculator();
    }

    @Test
    @DisplayName("testSimpleEmptyInputString")
    void testSimpleEmptyInputString() {
        assertEquals(0, calcInstance.add(""), 0);
    }

    @Test
    @DisplayName("testSimpleSingleNumInputString")
    void testSimpleSingleNumInputString() {
        assertEquals(3, calcInstance.add("3"), 0);
    }

    @Test
    @DisplayName("testSimpleTwoNumInputString")
    void testSimpleTwoNumInputString() {
        assertEquals(5, calcInstance.add("1,1, 3"), 0);
    }

    @Test
    @DisplayName("testSimpleTwoNumWithSpaceInputString")
    void testSimpleTwoNumWithSpaceInputString() {
        System.out.println("testSimpleTwoNumInputString");
        assertEquals(5, calcInstance.add("1,1, 3"), 0);
    }
}
