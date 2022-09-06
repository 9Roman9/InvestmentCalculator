package com.roman.InvestmentCalculator.service;

import com.roman.InvestmentCalculator.entity.InvestmentProject;
import com.roman.InvestmentCalculator.repository.InvestmentProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvestmentProjectServiceImpl implements InvestmentProjectService{

    @Autowired
    private InvestmentProjectRepository repository;

    @Override
    public void createInvestmentProject(InvestmentProject investmentProject) {
        repository.save(investmentProject);
    }

    @Override
    public List<InvestmentProject> readInvestmentProjects() {
        List<InvestmentProject> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().sorted(Comparator.comparing(InvestmentProject::getNPV).reversed()).collect(Collectors.toList());
    }

    @Override
    public InvestmentProject readInvestmentProjectByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void updateInvestmentProject(String name, int pp, BigDecimal npv, double irr) {
        repository.update(name, pp, npv, irr);
    }

    @Override
    public void deleteInvestmentProject(String name) {
        repository.deleteByName(name);
    }
}
