package com.investment_calculator;

import java.text.DecimalFormat;

/**
 * This method obtains values of necessary indicators
 * and calculates result of Profitability Index method.
 */
public class ProfitabilityIndexMethod implements InvestmentMethod{
    private double investmentCapital;
    private double cashFlow;
    private double interest;
    private double rateOfCentralBank;
    private double years;
    double sumDiscountedCashFlow;

    InvestmentCapitalIndicator investmentCapitalIndicator;
    InterestIndicator interestIndicator;
    YearsIndicator yearsIndicator;
    RateOfCentralBankIndicator rateOfCentralBankIndicator;
    @Override
    public void receiveIndicators() {
        investmentCapitalIndicator = new InvestmentCapitalIndicator();
        interestIndicator = new InterestIndicator();
        yearsIndicator = new YearsIndicator();
        rateOfCentralBankIndicator = new RateOfCentralBankIndicator();

        investmentCapital = investmentCapitalIndicator.request();
        interest = interestIndicator.request();
        years = yearsIndicator.request();
        rateOfCentralBank = rateOfCentralBankIndicator.request()/100;
    }

    @Override
    public String calculate() {
        receiveIndicators();
        cashFlow=investmentCapital*interest;
        for (int i=0; i<years; i++) {
            double percantage = 1+rateOfCentralBank;
            double discountYear = i + 1;
            double discountedPercentage = Math.pow(percantage, discountYear);
            double discountedCashFlow = cashFlow / discountedPercentage;
            sumDiscountedCashFlow = sumDiscountedCashFlow + discountedCashFlow;
        }
        double percentage = (1 + rateOfCentralBank);
        double discountYear = years;
        double discountPercentage = Math.pow(percentage, discountYear);
        double fullSumDiscountedCashFlow = sumDiscountedCashFlow + investmentCapital/discountPercentage;

        double profitabilityIndex = fullSumDiscountedCashFlow/investmentCapital;
        String formattedProfitabilityIndexResult = new DecimalFormat("#0.00").format(profitabilityIndex);
        return "The Profitability Index is " + formattedProfitabilityIndexResult + ". The project is profitable if Profitability Index is more than 1.";
    }
}
