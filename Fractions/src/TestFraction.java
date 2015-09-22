package designpatterns.Fraction;

import java.util.*;


public class TestFraction
  {
    public static void main(String[] args)
      {
        long numerator, denominator;
        Scanner in = new Scanner(System.in);
        Fraction zero = new Fraction(0, 1);
        Fraction f1, f2;

        do
          {

            System.out.println("Enter four integers representing two fractions (\"0 1 0 1\" to terminate):  ");

            numerator = in.nextLong();
            denominator = in.nextLong();
            f1 = new Fraction(numerator, denominator);

            numerator = in.nextInt();
            denominator = in.nextInt();
            f2 = new Fraction(numerator, denominator);

            if (!f1.equals(zero) && !f2.equals(zero))
              {
                System.out.println("f1 = " + f1 + "    f2 = " + f2);
                System.out.println();

                System.out.println("f1.toDouble() = " + f1.toDouble()
                    + "    f2.toDouble() = " + f2.toDouble());
                System.out.println();

                System.out.println((f1.compareTo(f2) < 0)  ? "f1 < f2"  : "NOT f1 < f2");
                System.out.println((f1.compareTo(f2) == 0) ? "f1 == f2" : "NOT f1 == f2");
                System.out.println((f1.compareTo(f2) > 0)  ? "f1 > f2"  : "NOT f1 > f2");
                System.out.println();

                System.out.println("-f1 = " + f1.negate());
                System.out.println();

                System.out.println("f1 + f2 = " + f1.add(f2));
                System.out.println("f1 - f2 = " + f1.subtract(f2));
                System.out.println("f1 * f2 = " + f1.multiply(f2));
                System.out.println("f1 / f2 = " + f1.divide(f2));

                System.out.println();
              }
          }
        while (!f1.equals(zero) && !f2.equals(zero));

        // Fraction f = new Fraction(6.2);      // should not be permitted
      }
  }
