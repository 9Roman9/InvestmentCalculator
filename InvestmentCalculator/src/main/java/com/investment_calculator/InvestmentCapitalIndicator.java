package com.investment_calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class request value of start capital that investor is going to invest into the project.
 */
public class InvestmentCapitalIndicator implements Indicator{
    private Double investCapital = null;
    @Override
    public double request() {
        Scanner a = new Scanner(System.in);
        System.out.println("Enter the start sum of money that investor is going to invest into a project (e.g. 10000)");

        while (true) {
            try {
                investCapital = a.nextDouble();
                break;
            } catch (InputMismatchException a1) {
                System.err.println("You can enter only numbers. Try again.");
                a.nextLine();
            }
        }
        if (investCapital<=0){
            System.err.println("The investment can not be less or equal to 0. So it is set as 1 by default.");
            System.out.println();
            investCapital = 1.0;
        }
        return investCapital;
    }
}

