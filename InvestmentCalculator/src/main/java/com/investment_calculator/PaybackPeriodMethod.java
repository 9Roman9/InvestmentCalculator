package com.investment_calculator;

/**
 * This method obtains values of necessary indicators
 * and calculates result of Payback Period method.
 */
public class PaybackPeriodMethod implements InvestmentMethod{
    private double interest;
    @Override
    public void receiveIndicators() {
        InterestIndicator interestIndicator = new InterestIndicator();
        interest = interestIndicator.request();
    }

    @Override
    public String calculate() {
        receiveIndicators();
        double paybackPeriod = 1/interest;
        if (paybackPeriod>1) {
            return "Payback Period is " + (int)Math.ceil(paybackPeriod) + " years. The project is good if the Payback Period is less than 7 years";
        } else {
            return "Payback Period is " + (int)Math.ceil(paybackPeriod) + " year. The project is good if the Payback Period is less than 7 years";
        }
    }
}
