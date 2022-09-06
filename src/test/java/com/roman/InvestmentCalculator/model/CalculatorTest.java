package com.roman.InvestmentCalculator.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    void findPPAverageCashFlow() {
        int PP = calculator.findPP(BigDecimal.valueOf(100),BigDecimal.valueOf(30));
        assertEquals(4,PP);
    }

    @Test
    void findPPDifferentCashFlow() {
        int PP = calculator.findPP(BigDecimal.valueOf(100),
                List.of(BigDecimal.valueOf(20), BigDecimal.valueOf(50),
                        BigDecimal.valueOf(40),BigDecimal.valueOf(30),
                        BigDecimal.valueOf(10)));
        assertEquals(3,PP);
    }

    @Test
    void findPPDifferentCashFlowWithLessPeriods() {
        int PP = calculator.findPP(BigDecimal.valueOf(100),
                List.of(BigDecimal.valueOf(20), BigDecimal.valueOf(50)));
        assertEquals(-1,PP);
    }

    @Test
    void findNPVAverageCashFlow(){
        BigDecimal actualResult = calculator.findNPV(BigDecimal.valueOf(1000),
                BigDecimal.valueOf(300),4,10);
        assertEquals(BigDecimal.valueOf(-49.05).setScale(2,RoundingMode.HALF_UP),actualResult);
    }

    @Test
    void findNPVDifferentCashFlow() {
        BigDecimal actualResult = calculator.findNPV(BigDecimal.valueOf(1000),
                List.of(BigDecimal.valueOf(200),BigDecimal.valueOf(100),
                        BigDecimal.valueOf(400),BigDecimal.valueOf(500)),
                10);
        assertEquals(BigDecimal.valueOf(-93.5).setScale(2, RoundingMode.HALF_UP),actualResult);
    }

    @Test
    void findIRRAverageCashFlow() {
        double actualResult = calculator.findIRR(BigDecimal.valueOf(1000),
                BigDecimal.valueOf(300),4);
        assertEquals(7.8,actualResult);
    }

    @Test
    void findIRRDifferentCashFlow() {
        double actualResult = calculator.findIRR(BigDecimal.valueOf(1000),
                List.of(BigDecimal.valueOf(200),BigDecimal.valueOf(100),
                        BigDecimal.valueOf(400),BigDecimal.valueOf(500)));
        assertEquals(6.4,actualResult);
    }
}