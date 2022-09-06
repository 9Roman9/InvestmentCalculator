package com.roman.InvestmentCalculator.model;

import com.roman.InvestmentCalculator.entity.InvestmentProject;
import com.roman.InvestmentCalculator.service.InvestmentProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component("investmentManager")
public class InvestmentManager {

    @Autowired
    private Calculator calculator;
    @Autowired
    private Parser parser;
    @Autowired
    InvestmentProjectServiceImpl service;
    private double discountRate;

    public void addInvestmentProject(String name, String initialInvestment, String annuity, String differentCashFlow, String period, String discountRate) throws IOException {
        int PP;
        BigDecimal NPV;
        double IRR;
        Optional<String> discountRateOptional = Optional.ofNullable(discountRate);
        this.discountRate = Double.parseDouble(discountRateOptional.orElse(parser.findBankRate()));
        if (Optional.ofNullable(annuity).isEmpty()){
            List<BigDecimal> cashFlowList = transformCashFlows(differentCashFlow);
            PP = calculator.findPP(new BigDecimal(initialInvestment),cashFlowList);
            NPV = calculator.findNPV(new BigDecimal(initialInvestment),cashFlowList,this.discountRate);
            IRR = calculator.findIRR(new BigDecimal(initialInvestment),cashFlowList);
        } else {
            PP = calculator.findPP(new BigDecimal(initialInvestment), new BigDecimal(annuity));
            NPV = calculator.findNPV(new BigDecimal(initialInvestment), new BigDecimal(annuity),Integer.parseInt(period),this.discountRate);
            IRR = calculator.findIRR(new BigDecimal(initialInvestment),new BigDecimal(annuity),Integer.parseInt(period));
        }
        InvestmentProject investmentProject = new InvestmentProject(name,PP,NPV,IRR);
        service.createInvestmentProject(investmentProject);
    }

    public List<InvestmentProject> readInvestmentProjects(){
        return service.readInvestmentProjects();
    }

    public void updateInvestmentProject(String name, String initialInvestment, String annuity, String differentCashFlow, String period, String discountRate) throws IOException {
        InvestmentProject project = service.readInvestmentProjectByName(name);
        int PP;
        BigDecimal NPV;
        double IRR;
        Optional<String> discountRateOptional = Optional.ofNullable(discountRate);
        this.discountRate = Double.parseDouble(discountRateOptional.orElse(parser.findBankRate()));
        if (Optional.ofNullable(annuity).isEmpty()){
            List<BigDecimal> cashFlowList = transformCashFlows(differentCashFlow);
            PP = calculator.findPP(new BigDecimal(initialInvestment),cashFlowList);
            NPV = calculator.findNPV(new BigDecimal(initialInvestment),cashFlowList,this.discountRate);
            IRR = calculator.findIRR(new BigDecimal(initialInvestment),cashFlowList);
        } else {
            PP = calculator.findPP(new BigDecimal(initialInvestment), new BigDecimal(annuity));
            NPV = calculator.findNPV(new BigDecimal(initialInvestment), new BigDecimal(annuity),Integer.parseInt(period),this.discountRate);
            IRR = calculator.findIRR(new BigDecimal(initialInvestment),new BigDecimal(annuity),Integer.parseInt(period));
        }
        service.updateInvestmentProject(project.getName(), PP, NPV, IRR);
    }

    public void deleteInvestmentProject(String name){
        service.deleteInvestmentProject(name);
    }

    public List<BigDecimal> transformCashFlows(String cashFlowsString){
        List<BigDecimal> cashFlows = new ArrayList<>();
        String[] cashFlowsArray = cashFlowsString.split(",");
        Arrays.stream(cashFlowsArray).forEach((p) -> cashFlows.add(new BigDecimal(p)));
        return cashFlows;
    }
}
