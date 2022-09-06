package com.roman.InvestmentCalculator.model;

import com.roman.InvestmentCalculator.entity.InvestmentProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class InvestmentManagerTest {

    @Autowired
    private InvestmentManager manager;

    @Test
    void addInvestmentProjectWithDiscountRateAndCashFlow() throws IOException {
        manager.addInvestmentProject("Schema","1000","200",null,"2","8");
    }

    @Test
    void addInvestmentProjectWithoutDiscountRateAndCashFlow() throws IOException {
        manager.addInvestmentProject("Bonds","1000","200",null,"2",null);
    }

    @Test
    void addInvestmentProjectWithDiscountRateAndDifferentCashFlow() throws IOException {
        List<String> flows = List.of("200","500","100");
        manager.addInvestmentProject("StockMarket","1000",null,"5,10","2","8");
    }

    @Test
    void readInvestmentProjects() throws IOException {
        manager.addInvestmentProject("Building","1000","200",null,"2","8");
        manager.addInvestmentProject("Securities","1000","200",null,"2",null);
        List<InvestmentProject> projects = manager.readInvestmentProjects();
        assertEquals(BigDecimal.valueOf(-643.34).setScale(2, RoundingMode.HALF_UP),projects.get(0).getNPV());
        assertEquals(BigDecimal.valueOf(-712).setScale(2, RoundingMode.HALF_UP),projects.get(1).getNPV());
    }

    @Test
    void updateInvestmentProject() throws IOException {
        manager.addInvestmentProject("RealEstate","1000","200",null,"2","8");
        manager.updateInvestmentProject("Building","1000",null,
                List.of("100","200","300","400"),null,null);
    }

    @Test
    void deleteInvestmentProject() throws IOException {
        manager.addInvestmentProject("House","1000","200","100,200","2","8");
        manager.deleteInvestmentProject("House");
    }
}