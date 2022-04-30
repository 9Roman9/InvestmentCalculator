package com.investment_calculator;

/**
 * This interface sets methods for obtaining values of necessary indicators
 * and calculation of result of investment analysis method.
 */
public interface InvestmentMethod {
    void receiveIndicators();
    String calculate();
}
