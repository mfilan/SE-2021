package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class FailureOrErrorTest {

    private int getRandomInt() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }
    @Test
    void test1() {
        assertTrue(false);
    }

    @Test
    void test2() {
        Calculator calculator = new Calculator();
        int num1 = getRandomInt();
        int num2 = getRandomInt();
        assertEquals(num1 + num2, calculator.addPositiveNumbers(-num1, num2));
    }

    @Test
    void test3(){
        try{
            assertTrue(false);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
