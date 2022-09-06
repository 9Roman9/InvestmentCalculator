package com.roman.InvestmentCalculator.service;

import com.roman.InvestmentCalculator.entity.InvestmentProject;

import java.math.BigDecimal;
import java.util.List;

public interface InvestmentProjectService {
    void createInvestmentProject(InvestmentProject investmentProject);
    List<InvestmentProject> readInvestmentProjects();
    InvestmentProject readInvestmentProjectByName(String name);
    void updateInvestmentProject(String name, int pp, BigDecimal npv, double irr);
    void deleteInvestmentProject(String name);
}