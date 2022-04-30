package com.investment_calculator;


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class requests value of interest that investor receives from the investment project.
 */
public class InterestIndicator implements Indicator{
    @Override
    public double request() {
        Scanner b = new Scanner(System.in);
        System.out.println("Enter the interest of potential project income (e.g. 0.15)");
        double interest;
        while (true) {
            try {
                interest = b.nextDouble();
                break;
            } catch (InputMismatchException b1) {
                System.err.println("You can enter only numbers. Try again.");
                b.nextLine();
            }
        }
        if (interest <=0){
            System.err.println("The interest of project income can not be less or equal to 0. So it is set as 0.01 by default.");
            System.out.println();
            interest = 0.01;
        }
        return interest;
    }
}

