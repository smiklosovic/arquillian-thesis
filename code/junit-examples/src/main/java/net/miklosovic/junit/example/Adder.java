package net.miklosovic.junit.example;

public class Adder {

    /**
     * Adds two positive numbers.
     * 
     * @param a
     * @param b
     * @return sum of {@code a} and {@code b}
     * @throws IllegalArgumentException when {@code a} or {@code b} is not positive
     */
    public int addPositiveNumbers(int a, int b) throws IllegalArgumentException {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Both arguments have to be positive.");
        }
        return a + b;
    }

}
