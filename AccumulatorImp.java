package com.metapack.assignments;

import java.util.Arrays;

/**
 * Class implementing {@link Accumulator} interface with additional method for supporting {@link Integer} overflow handling with a use of {@link Math#addExact(int, int)}.
 *
 * @author Michał Plata
 */
public class AccumulatorImp implements Accumulator {

    private int totalSum = 0;

    //Implementation conforming to Accumulator interface. Vulnerable to integer overflow BUT using streams :)
    @Override
    public int accumulate(int... values) {
        int runningSum = Arrays.stream(values).sum();
        this.totalSum += runningSum;
        return runningSum;
    }

    //Suggestion on how to handle Integer overflow. Sadly no streams as they are stateless :(
    public int accumulateWithExceptionHandling(int... values) throws AccumulatorIntegerOverflowException {
        int runningSum = 0;
        try {
            for (int variable : values) {
                runningSum = Math.addExact(runningSum, variable);
            }
        } catch (ArithmeticException exception) {
            throw new AccumulatorIntegerOverflowException(exception.getMessage());
        }

        this.totalSum += runningSum;
        return runningSum;
    }

    @Override
    public int getTotal() {
        return totalSum;
    }

    /*
        In accordance to Accumulator.java:20 'Calling {@code accumulator.reset()} would reset the total value to 0.'
        I suspect an error in Accumulator.java:43 which states 'Resets the running value to 0.'
    */
    @Override
    public void reset() {
        this.totalSum = 0;
    }
}
