//Redesign and implement the Rational class using BigInteger for the numerator and denominator. 

import java.math.*;
import java.util.Scanner;

public class Project1 {  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter rational r1 with numerator and denominator seperated by a space: ");
        String n1 = input.next();
        String d1 = input.next();

        System.out.print("Enter rational r2 with numerator and denominator seperated by a space: ");
        String n2 = input.next();
        String d2 = input.next();

        RationalUsingBigInteger r1 = new RationalUsingBigInteger(
            new BigInteger(n1), new BigInteger(d1));
        RationalUsingBigInteger r2 = new RationalUsingBigInteger(
            new BigInteger(n2), new BigInteger(d2));

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.doubleValue());
    }
}

class RationalUsingBigInteger extends Number 
    implements Comparable<RationalUsingBigInteger> {

    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = gcd(numerator, denominator);
        this.numerator = numerator.abs().divide(gcd);
        this.denominator = denominator.abs().divide(gcd);
    }

    BigInteger getNumerator() {
        return this.numerator;
    }

    BigInteger getDenominator() {
        return this.denominator;
    }

    private static BigInteger gcd(BigInteger numerator, BigInteger denominator) {
        BigInteger n1 = numerator.abs();
        BigInteger n2 = denominator.abs();
        BigInteger gcd = BigInteger.ZERO;

        BigInteger k = BigInteger.ONE;
        while ((k.compareTo(n1) <= 0) && (k.compareTo(n2) <= 0)) {
            if ((n1.mod(k).compareTo(BigInteger.ZERO) == 0) && (n2.mod(k).compareTo(BigInteger.ZERO) == 0))
                gcd = k;
            k = k.add(BigInteger.ONE);
        }
        return gcd;
    }

    public double doubleValue() {
        return this.numerator.doubleValue() / this.denominator.doubleValue();
    }
    public int intValue() {
        return (int)this.doubleValue();
    } 
    public float floatValue() {
        return (float)this.doubleValue();
    } 
    public long longValue() {
        return (long)this.doubleValue();
    }

    public RationalUsingBigInteger add(RationalUsingBigInteger number) {

        BigInteger n = this.numerator.multiply(number.getDenominator()).add(this.denominator.multiply(number.getNumerator()));
        BigInteger d = this.denominator.multiply(number.getDenominator());
        return new RationalUsingBigInteger(n, d);
    }

    public RationalUsingBigInteger subtract(RationalUsingBigInteger number) {
        BigInteger n = this.numerator.multiply(number.getDenominator()).subtract(this.denominator.multiply(number.getNumerator()));
        BigInteger d = this.denominator.multiply(number.getDenominator());
        return new RationalUsingBigInteger(n, d);
    }

    public RationalUsingBigInteger multiply(RationalUsingBigInteger number) {
        BigInteger n = this.numerator.multiply(number.getNumerator());
        BigInteger d = this.denominator.multiply(number.getDenominator());
        return new RationalUsingBigInteger(n, d);
    }

    public RationalUsingBigInteger divide(RationalUsingBigInteger number) {
        BigInteger n = this.numerator.multiply(number.getDenominator());
        BigInteger d = this.denominator.multiply(number.getNumerator());
        return new RationalUsingBigInteger(n, d);
    }

    @Override
    public String toString() {
        if (this.denominator.compareTo(BigInteger.ONE) == 0)
            return this.numerator + "";
        else
            return this.numerator + "/" + this.denominator;
    }

    @Override
    public int compareTo(RationalUsingBigInteger o) {
        if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) > 0) {
            return 1;
        }
        else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0) {
            return -1;
        }
        else return 0;
    }
}