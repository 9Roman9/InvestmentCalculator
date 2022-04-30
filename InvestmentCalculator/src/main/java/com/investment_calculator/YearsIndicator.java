package com.investment_calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class requests the period of the investment project.
 */
public class YearsIndicator implements Indicator{
    private double years = 0;
    @Override
    public double request() {
        Scanner c = new Scanner(System.in);
        System.out.println("Enter the expected time (years) for investment (e.g. 5)");
        while (true) {
            try {
                years = c.nextInt();
                break;
            } catch (InputMismatchException c1){
                System.err.println("You can enter only integers. Try again.");
                c.nextLine();
            }
        }
        if (years<=0){
            System.err.println("The time of project can not be less or equal to 0. So it is set as 1 by default.");
            System.out.println();
            years = 1;
        }
        return years;
    }
}
