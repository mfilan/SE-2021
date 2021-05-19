package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;
    private int getRandomInt() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }
    @BeforeEach
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        for (int x = 10; x > 0; x--) {
            int num1 = getRandomInt();
            int num2 = getRandomInt();
            assertEquals(num1 + num2, calculator.add(num1, num2));
            assertEquals(num1 + num2, calculator.add(num2, num1));
        }
    }
    @Test
    void testMultiply() {
        for (int x = 10; x > 0; x--) {
            int num1 = getRandomInt();
            int num2 = getRandomInt();
            assertEquals(num1 * num2, calculator.multiply(num1, num2));
            assertEquals(num1 * num2, calculator.multiply(num2, num1));
        }
    }
    @Test
    void testAddPositiveNumbers(){
        int num1 = getRandomInt();
        int num2 = getRandomInt();
        assertEquals(num1 + num2, calculator.addPositiveNumbers(num1, num2));
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(-num1, num2));
    }
}