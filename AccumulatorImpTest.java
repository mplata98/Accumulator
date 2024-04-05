package com.metapack.assignments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link AccumulatorImp} class.
 *
 * @author Michał Plata
 */

public class AccumulatorImpTest {
    private AccumulatorImp accumulatorImp;

    @BeforeEach
    public void init() {
        accumulatorImp = new AccumulatorImp();
    }

    @Test
    public void returnsDefaultValues() {
        assertEquals(0, accumulatorImp.getTotal());
    }

    @Test
    public void returnsZeroWhenEmptyArray() {
        int[] emptyArray = new int[]{};
        int running = accumulatorImp.accumulate(emptyArray);
        int total = accumulatorImp.getTotal();
        assertEquals(running, total);
        assertEquals(0, total);
    }

    @Test
    public void returnsCorrectSumsInRegularScenario() {
        int running1 = accumulatorImp.accumulate(1, 2, 3);
        int running2 = accumulatorImp.accumulate(4);
        int total = accumulatorImp.getTotal();
        assertEquals(6, running1);
        assertEquals(4, running2);
        assertEquals(10, total);
    }

    @Test
    public void resetsAccumulator() {
        int running1 = accumulatorImp.accumulate(1, 2, 3);
        accumulatorImp.reset();
        assertNotEquals(running1, accumulatorImp.getTotal());
    }

    @Test
    public void returnsCorrectRunningSumsAfterReset() {
        int running1 = accumulatorImp.accumulate(1, 2, 3);
        accumulatorImp.reset();
        int running2 = accumulatorImp.accumulate(4);
        int total = accumulatorImp.getTotal();
        assertEquals(6, running1);
        assertEquals(4, running2);
        assertNotEquals(10, total);
        assertEquals(4, total);
    }

    @Test
    public void doesNotHandleIntegerOverflow() {
        int sum = accumulatorImp.accumulate(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 6);
        assertEquals(-9, sum);
    }

    @Test
    public void doesHandleIntegerOverflow() {
        assertThrowsExactly(AccumulatorIntegerOverflowException.class, () -> accumulatorImp.accumulateWithExceptionHandling(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 6));
    }

    /*
    Section dedicated to testing accumulateWithExceptionHandling method
    direct copy-paste of accumulate testcases
     */

    @Test
    public void returnsZeroWhenEmptyArrayWithExceptionHandling() {
        int running;
        int[] emptyArray = new int[]{};
        try {
            running = accumulatorImp.accumulateWithExceptionHandling(emptyArray);
        } catch (AccumulatorIntegerOverflowException e) {
            throw new RuntimeException(e);
        }
        int total = accumulatorImp.getTotal();
        assertEquals(running, total);
        assertEquals(0, total);
    }

    @Test
    public void returnsCorrectSumsInRegularScenarioWithExceptionHandling() {
        int running1;
        int running2;
        try {
            running1 = accumulatorImp.accumulateWithExceptionHandling(1, 2, 3);
            running2 = accumulatorImp.accumulateWithExceptionHandling(4);
        } catch (AccumulatorIntegerOverflowException e) {
            throw new RuntimeException(e);
        }

        int total = accumulatorImp.getTotal();
        assertEquals(6, running1);
        assertEquals(4, running2);
        assertEquals(10, total);
    }
}
