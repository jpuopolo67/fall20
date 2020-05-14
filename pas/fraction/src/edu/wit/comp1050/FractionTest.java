package edu.wit.comp1050;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Fraction class
 */
class FractionTest {

    @Nested
    @DisplayName("Creation Tests")
    class CreationTest {

        @Test
        void zeroDenominatorTest() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Fraction(2, 0));
        }

        @Test
        void positiveNumeratorPositiveDenominatorTest() {
            Fraction f = new Fraction(1, 4);
            assertAll("1/4 -> 1/4",
                    () -> assertEquals(1, f.get_numerator()),
                    () -> assertEquals(4, f.get_denominator()));
        }

        @Test
        void positiveNumeratorNegativeDenominatorTest() {
            Fraction f = new Fraction(1, -4);
            assertAll("1/-4 -> -1/4",
                    () -> assertEquals(-1, f.get_numerator()),
                    () -> assertEquals(4, f.get_denominator()));
        }

        @Test
        void negativeNumeratorPositiveDenominatorTest() {
            Fraction f = new Fraction(-1, 4);
            assertAll("-1/4 -> -1/4",
                    () -> assertEquals(-1, f.get_numerator()),
                    () -> assertEquals(4, f.get_denominator()));
        }

        @Test
        void negativeNumeratorNegativeDenominatorTest() {
            Fraction f = new Fraction(-1, -4);
            assertAll("-1/-4 -> 1/4",
                    () -> assertEquals(1, f.get_numerator()),
                    () -> assertEquals(4, f.get_denominator()));
        }
    }

    @Nested
    @DisplayName("Reduction Tests")
    class ReductionTest {
        @Test
        void reduceTest1() {
            Fraction f = new Fraction(4, 8);
            Fraction r = f.reduce();
            assertAll("Fraction reduced 1",
                    () -> assertEquals(1, r.get_numerator()),
                    () -> assertEquals(2, r.get_denominator()));
        }

        @Test
        void reduceTest2() {
            Fraction f = new Fraction(12, 3);
            Fraction r = f.reduce();
            assertAll("Fraction reduced 2",
                    () -> assertEquals(4, r.get_numerator()),
                    () -> assertEquals(1, r.get_denominator()));
        }

        @Test
        void reduceWithNegativeDenominatorTest() {
            Fraction f = new Fraction(2, -4);
            Fraction r = f.reduce();
            assertAll("Fraction reduced with negative denominator",
                    () -> assertEquals(-1, r.get_numerator()),
                    () -> assertEquals(2, r.get_denominator()));
        }
    }

    @Nested
    @DisplayName("Addition Tests")
    class AdditionTest {
        @Test
        void addTest() {
            Fraction a = new Fraction(1,2);
            Fraction b = new Fraction(2, 3);
            Fraction sum = Fraction.add(a, b);
            assertAll("(1/2) + (2/3)",
                    () -> assertEquals(7, sum.get_numerator()),
                    () -> assertEquals(6, sum.get_denominator()));
        }

        @Test
        void addWithReduceTest() {
            Fraction a = new Fraction(1,2);
            Fraction b = new Fraction(2, 3);
            Fraction sum = Fraction.add(a, b);
            assertAll("(1/2) + (2/3)",
                    () -> assertEquals(7, sum.get_numerator()),
                    () -> assertEquals(6, sum.get_denominator()));

            Fraction r = sum.reduce();
            assertAll("(1/2) + (2/3) reduced",
                    () -> assertEquals(7, r.get_numerator()),
                    () -> assertEquals(6, r.get_denominator()));
        }

        @Test
        void addWithNegativeNumeratorTest() {
            Fraction a = new Fraction(3,4);
            Fraction b = new Fraction(-1, 4);
            Fraction sum = Fraction.add(a, b);
            assertAll("(3/4) + (-1/4)",
                    () -> assertEquals(2, sum.get_numerator()),
                    () -> assertEquals(4, sum.get_denominator()));
        }
    }

    @Nested
    @DisplayName("Subtraction Tests")
    class SubtractTest {
        @Test
        void subPositiveTest() {
            Fraction a = new Fraction(3, 4);
            Fraction b = new Fraction(1, 4);
            Fraction diff = Fraction.sub(a, b);
            assertAll("(3/4) - (1/4)",
                    () -> assertEquals(2, diff.get_numerator()),
                    () -> assertEquals(4, diff.get_denominator()));
        }

        @Test
        void subNegativeTest() {
            Fraction a = new Fraction(3, 4);
            Fraction b = new Fraction(-1, 4);
            Fraction diff = Fraction.sub(a, b);
            assertAll("(3/4) - (-1/4)",
                    () -> assertEquals(4, diff.get_numerator()),
                    () -> assertEquals(4, diff.get_denominator()));
        }

        @Test
        void subPositiveFromNegative() {
            Fraction a = new Fraction(-3, 4);
            Fraction b = new Fraction(1, 4);
            Fraction diff = Fraction.sub(a, b);
            assertAll("(-3/4) - (1/4)",
                    () -> assertEquals(-4, diff.get_numerator()),
                    () -> assertEquals(4, diff.get_denominator()));
        }

        @Test
        void subNegativeFromNegative() {
            Fraction a = new Fraction(-3, 4);
            Fraction b = new Fraction(-1, 4);
            Fraction diff = Fraction.sub(a, b);
            assertAll("(-3/4) - (-1/4)",
                    () -> assertEquals(-2, diff.get_numerator()),
                    () -> assertEquals(4, diff.get_denominator()));
        }
    }

    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplyTest {
        @Test
        void mulOneSetOfFractionsTest() {
            Fraction a = new Fraction(1, 2);
            Fraction b = new Fraction(3, 4);
            Fraction product = Fraction.mul(a, b);
            assertEquals(3, product.get_numerator());
            assertEquals(8, product.get_denominator());
        }

        @Test
        void mulSeveralSetsOfFractionsTest() {
            Fraction a = new Fraction(4,3);
            Fraction b = new Fraction( -1, -5);
            Fraction product = Fraction.mul(a, b);
            assertAll("Fraction set 1 mul",
                    () -> assertEquals(4, product.get_numerator()),
                    () -> assertEquals(15, product.get_denominator()));

            a = new Fraction(5,6);
            b = new Fraction( -3, 7);
            Fraction product2 = Fraction.mul(a, b);
            // Need product2 because lambda function below demands final or effectively
            // final object on which to operate.
            assertAll("Fraction set 2 mul",
                    () -> assertEquals(-15, product2.get_numerator()),
                    () -> assertEquals(42, product2.get_denominator()));

        }

        @Test
        void mulAndReduceFractionTest() {
            Fraction a = new Fraction(4, 6);
            Fraction b = new Fraction(2, 3);
            Fraction p = Fraction.mul(a, b);
            assertAll("4/6 * 2/3",
                    () -> assertEquals(8, p.get_numerator()),
                    () -> assertEquals(18, p.get_denominator()));
            Fraction r = p.reduce();
            assertAll("4/6 * 2/3 reduced",
                    () -> assertEquals(4, r.get_numerator()),
                    () -> assertEquals(9, r.get_denominator()));
        }
    }

    @Nested
    @DisplayName("Division Tests")
    class DivisionTest {
        @Test
        void divTest() {
            Fraction a = new Fraction(3, 8);
            Fraction b = new Fraction(1, 2);
            Fraction q = Fraction.div(a, b);
            assertAll("(3/8) / (1/2)",
                    () -> assertEquals(6, q.get_numerator()),
                    () -> assertEquals(8, q.get_denominator()));

        }

        @Test
        void divReduceTest() {
            Fraction a = new Fraction(3, 8);
            Fraction b = new Fraction(1, 2);
            Fraction r = Fraction.div(a, b).reduce();
            assertAll("(3/8) / (1/2) reduced",
                    () -> assertEquals(3, r.get_numerator()),
                    () -> assertEquals(4, r.get_denominator()));
        }
    }
}