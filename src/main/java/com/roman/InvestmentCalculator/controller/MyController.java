package com.roman.InvestmentCalculator.controller;

import com.roman.InvestmentCalculator.model.InvestmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("investor")
public class MyController {

    @Autowired
    private InvestmentManager manager;

    @GetMapping
    public String menu(){
        return "menu";
    }

    @GetMapping("/add")
    public String add(Model model){
        return "add";
    }

    @PostMapping ("/add/{name}")
    public String addDone(Model model, @RequestParam String name, String initialInvestment,
                          String annuity, String differentCashFlow, String period,
                          String customDiscountRate) throws IOException {
        try {
            manager.addInvestmentProject(name, initialInvestment, annuity, differentCashFlow, period, customDiscountRate);
        } catch (Exception e) {
            return "exceptions/incorrectData";
        }
        model.addAttribute("name",name);
        return "addDone";
    }

    @GetMapping("/read")
    public String read(Model model){
        model.addAttribute("projects",manager.readInvestmentProjects());
        return "read";
    }

    @GetMapping("/update")
    public String update(Model model){
        return "update";
    }

    @PostMapping ("/update/{name}")
    public String updateDone(Model model, @RequestParam String name, String initialInvestment,
                          String annuity, String differentCashFlow, String period,
                          String customDiscountRate) throws IOException {
        try {
             manager.updateInvestmentProject(name,initialInvestment,annuity,differentCashFlow,period,customDiscountRate);
        } catch (Exception e){
            return "exceptions/incorrectData";
        }
       model.addAttribute("name",name);
        return "updateDone";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @PostMapping ("/delete/{name}")
    public String deleteDone(Model model, @RequestParam String name){
        try {
            manager.deleteInvestmentProject(name);
        } catch (Exception e){
            return "exceptions/incorrectProject";
        }
        model.addAttribute("name",name);
        return "deleteDone";
    }

    @PostMapping(value = {"/add","/update"})
    public String absentName(){
        return "exceptions/absentName";
    }
}
