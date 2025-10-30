import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals(5, calcInstance.add("1,1,3"), 0);
    }

    @Test
    @DisplayName("testSimpleTwoNumWithSpaceInputString")
    void testTwoNumWithSpaceInputString() {
        assertEquals(5, calcInstance.add("1,1 , 3"), 0);
    }

    @Test
    @DisplayName("testSingleNegativeNumInputString")
    void testNegativeNumInputString() {
        final int negativeNum = -3;
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class, 
            () -> { 
                calcInstance.add("1,"+negativeNum+" , 3"); 
            }
        );
        String expected = "negative numbers not allowed "+negativeNum;

        assertEquals(expected, e.getMessage());
    }

    @Test
    @DisplayName("testMultipleNegativeNumInputString")
    void testMultipleNegativeNumInputString() {
        final String negativeNums = "-1,-2\n-3\n-4";
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class, 
            () -> { 
                calcInstance.add("1,"+negativeNums+" , 3"); 
            }
        );
            
        final String commaSeperatedNegatives = String.join(",",negativeNums.split("[,\\n]"));

        String expected = "negative numbers not allowed "+commaSeperatedNegatives;
        System.err.println(e.getMessage());
        assertEquals(expected, e.getMessage());
    }

    /**
     * Delimiter tests
     */
    @Test
    @DisplayName("testWithNewlineInputString")
    void testWithNewlineInputString() {
        assertEquals(9, calcInstance.add("1\n1\n 3,4"), 0);
    }
}
