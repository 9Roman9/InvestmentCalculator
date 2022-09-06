package com.roman.InvestmentCalculator.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Component("calculator")
public class Calculator {

    public int findPP(BigDecimal initialInvestment, List<BigDecimal> cashFlow){
        BigDecimal sum = BigDecimal.ZERO;
        for (int p = 0; p < cashFlow.size(); p++){
            sum = sum.add(cashFlow.get(p));
            if (sum.compareTo(initialInvestment)>=1) return p+1;
        }
        return -1;
    }

    public int findPP(BigDecimal initialInvestment, BigDecimal averageCashFlow){
        BigDecimal sum = BigDecimal.ZERO;
        for (int p = 0; p < Integer.MAX_VALUE; p++){
            sum = sum.add(averageCashFlow);
            if (sum.compareTo(initialInvestment)>=1) return p+1;
        }
        return  0;
    }

    public BigDecimal findNPV(BigDecimal initialInvestment, List<BigDecimal> cashFlow, double discountRate){
        BigDecimal sum = BigDecimal.ZERO;
        double finalRate;
        for (int t = 1; t <= cashFlow.size(); t++){
            finalRate = Math.pow(1+discountRate/100,t);
            sum = sum.add(cashFlow.get(t-1).divide(BigDecimal.valueOf(finalRate),2, RoundingMode.HALF_UP));
        }
        return sum.subtract(initialInvestment);
    }

    public BigDecimal findNPV(BigDecimal initialInvestment, BigDecimal averageCashFlow, int period, double discountRate){
        BigDecimal sum = BigDecimal.ZERO;
        double finalRate;
        for (int t = 1; t <= period; t++){
            finalRate = Math.pow(1+discountRate/100,t);
            sum = sum.add(averageCashFlow.divide(BigDecimal.valueOf(finalRate),2, RoundingMode.HALF_UP));
        }
        return sum.subtract(initialInvestment);
    }

    public double findIRR(BigDecimal initialInvestment, List<BigDecimal> cashFlow){
        BigDecimal NPV = BigDecimal.valueOf(-1);
        double IRR = 0;
        double finalRate;
        while (NPV.doubleValue()<0){
            IRR += 0.001;
            BigDecimal sum = BigDecimal.ZERO;
            for (int t = 1; t <= cashFlow.size(); t++){
                finalRate = Math.pow(1+IRR,t);
                sum = sum.add(cashFlow.get(t-1).divide(BigDecimal.valueOf(finalRate),2, RoundingMode.HALF_UP));
            }
            NPV = initialInvestment.subtract(sum);
        }
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(IRR*100));
    }

    public double findIRR(BigDecimal initialInvestment, BigDecimal cashFlow, int period){
        BigDecimal NPV = BigDecimal.valueOf(-1);
        double IRR = 0;
        double finalRate;
        while (NPV.doubleValue()<0){
            IRR += 0.001;
            BigDecimal sum = BigDecimal.ZERO;
            for (int t = 1; t <= period; t++){
                finalRate = Math.pow(1+IRR,t);
                sum = sum.add(cashFlow.divide(BigDecimal.valueOf(finalRate),2, RoundingMode.HALF_UP));
            }
            NPV = initialInvestment.subtract(sum);
        }
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(IRR*100));
    }
}
