package edu.wit.comp1050;

public class Fraction {

    /**
     * Creates a new Fraction instance
     *
     * @param n Numerator
     * @param d Denominator
     * @throws IllegalArgumentException When denominator == 0
     */
    public Fraction(int n, int d) throws IllegalArgumentException {
        _numerator = n;
        if (d == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0!");
        }
        _denominator = d;

        // If the denominator is negative, adjust the fraction to have the denominator
        // be positive, and flip the sign of the numerator. This will make later calculations
        // much simpler.
        if (_denominator < 0) {
            _denominator = Math.abs(_denominator);
            _numerator = -_numerator;
        }
    }

    /**
     * Compute Fraction a + Fraction b
     *
     * @param a Fraction 1
     * @param b Fraction 2
     * @return Fraction a + b
     */
    public static Fraction add(Fraction a, Fraction b) {
        // If the denominators are already equals, just sum the numerators
        int a_num = a.get_numerator(), a_den = a.get_denominator();
        int b_num = b.get_numerator(), b_den = b.get_denominator();

        if (a_den == b_den) {
            return new Fraction(a_num + b_num, b_den);
        }

        // Calculate the LCD, adjust the numerators, and perform the addition
        int lcd = LCD(a_den, b_den);
        int a_num_new = a.get_numerator() * (lcd / a_den);
        int b_num_new = b.get_numerator() * (lcd / b_den);

        // New fraction: (sum of adjusted numerators) / least common denominator
        return new Fraction((a_num_new + b_num_new), lcd);
    }

    /**
     * Compute Fraction a - Fraction b
     *
     * @param a Fraction 1
     * @param b Fraction 2
     * @return Fraction a - b
     */
    public static Fraction sub(Fraction a, Fraction b) {
        int neg_b_num = -b.get_numerator();
        Fraction neg_b = new Fraction(neg_b_num, b.get_denominator());
        Fraction sum = Fraction.add(a, neg_b);
        return sum;
    }

    /**
     * Compute Fraction a * b
     *
     * @param a Factor
     * @param b Factor
     * @return Fraction a * b
     */
    public static Fraction mul(Fraction a, Fraction b) {
        Fraction product = new Fraction(
                a.get_numerator() * b.get_numerator(),
                a.get_denominator() * b.get_denominator()
        );
        return product;
    }

    /**
     * Compute Fraction a / Fraction b
     *
     * @param a Dividend
     * @param b Divisor
     * @return Fraction a / b
     */
    public static Fraction div(Fraction a, Fraction b) {
        return Fraction.mul(a, b.reciprocal());
    }

    /**
     * Reduces a Fraction using Euclid's algorithm.
     *
     * @return A new Fraction representing the most-reduced form. Does not deal with improper fractions.
     */
    public Fraction reduce() {
        int gcd = GCD(_numerator, _denominator);
        assert gcd != 0;
        return new Fraction(_numerator / gcd, _denominator / gcd);
    }

    // Getters
    public int get_numerator() {
        return _numerator;
    }

    public int get_denominator() {
        return _denominator;
    }

    /**
     * Computes the greatest common divisor between 2 integers
     * @return Greatest Common Divisor
     */
    private static int GCD(int i, int j) {
        int num1 = Math.abs(i);
        int num2 = Math.abs(j);

        while (num1 > 0) {
            int tmp = num1;
            num1 = num2 % num1;
            num2 = tmp;
        }
        return num2;
    }

    /**
     * Computes Least Common Denominator between 2 integers
     *
     * @param i int 1
     * @param j int 2
     * @return LCD(i, j)
     */
    private static int LCD(int i, int j) {
        return LCM(i, j);
    }

    /**
     * Computes Least Common Multiple between 2 integers
     *
     * @param i int 1
     * @param j int 2
     * @return LCM(i, j)
     */
    private static int LCM(int i, int j) {
        return (i * j) / GCD(i, j);
    }

    /**
     * Computes reciprocal of this Fraction
     *
     * @return Reciprocal of f
     */
    private Fraction reciprocal() {
        return new Fraction(this.get_denominator(), this.get_numerator());
    }

    // Fields
    private int _numerator;     // integer numerator
    private int _denominator;   // integer denominator, cannot be 0
}
