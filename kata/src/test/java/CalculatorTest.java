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
    @DisplayName("testInvalidInputString")
    void testInvalidInputString() {
        assertThrows(
            NumberFormatException.class, 
            () -> { 
                calcInstance.add("1,a, 3"); 
            }
        );
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
    @DisplayName("testWithNewline")
    void testWithNewline() {
        assertEquals(9, calcInstance.add("1\n1\n 3,4"), 0);
    }

    @Test
    @DisplayName("testCustomSingleShortDelimiter")
    void testCustomSingleShortDelimiter() {
        assertEquals(5, calcInstance.add("//;\n1;1; 1;1;1"), 0);
        assertEquals(5, calcInstance.add("//*\n1*1* 1*1*1"), 0);
    }

    @Test
    @DisplayName("testCustomSingleLongDelimiter")
    void testCustomSingleLongDelimiter() {
        assertEquals(13, calcInstance.add("//**\n1**11**1"), 0);
    }

    @Test
    @DisplayName("testCustomMultipleShortDelimiters")
    void testWithCustomMultipleDelimiters() {
        assertEquals(14, calcInstance.add("//[*][\\n]\n1*11\\n1*1"), 0);
        assertEquals(17, calcInstance.add("//[*][|][\\n]\n1|2|12\\n1*1"), 0);
    }
    
    @Test
    @DisplayName("testCustomMultipleLongDelimiters")
    void testCustomMultipleLongDelimiters() {
        assertEquals(14, calcInstance.add("//[***][||]\n1***11***1||1"), 0);
        assertEquals(17, calcInstance.add("//[*][|*][\\n]\n1|*2|*12*1\\n1"), 0);
    }

    /**
     * Large Numbers Tests
     */
    @Test
    @DisplayName("testLargeNumbers")
    void testLargeNumbers() {
        assertEquals(1003, calcInstance.add("1,1000,1\n1"), 0);
        assertEquals(3, calcInstance.add("1,1001,1\n1"), 0);
    }
}
