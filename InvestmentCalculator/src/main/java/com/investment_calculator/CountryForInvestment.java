package com.investment_calculator;

import java.util.Scanner;

/**
 * This class requests the country where the investor is going to invest money.
 */
public class CountryForInvestment implements InputRequest{
    @Override
    public String request() {
        Scanner d = new Scanner(System.in);
        System.out.println("Enter the country where you are going to invest:");
        return d.next();
    }
}