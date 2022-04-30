package com.investment_calculator;

import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * This class requests information regarding necessary method of investment analysis
 * and return the name of such method.
 */
@Component
public class InvestmentMethodRequest implements InputRequest{

    @Override
    public String request() {
        String investmentMethod;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the investment method that you want use for estimation of investment project:");
        investmentMethod = scanner.nextLine();
        return investmentMethod;
    }
}
