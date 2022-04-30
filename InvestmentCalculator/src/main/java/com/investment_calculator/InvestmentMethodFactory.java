package com.investment_calculator;

import org.springframework.stereotype.Component;

/**
 * This class creates objects of necessary investment methods
 * depending on user's information.
 */
@Component
public class InvestmentMethodFactory implements Factory{
    public InvestmentMethod create(String type){
        switch (type){
            case "Net Present Value":
                return new NetPresentValueMethod();
            case "NPV":
                return new NetPresentValueMethod();
            case "Payback Period":
                return new PaybackPeriodMethod();
            case "Profitability Index":
                return new ProfitabilityIndexMethod();
            default:
                return null;
        }
    }
}
