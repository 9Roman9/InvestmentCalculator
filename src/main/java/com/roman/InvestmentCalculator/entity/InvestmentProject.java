package com.roman.InvestmentCalculator.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "project")
public class InvestmentProject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private int PP;
    private BigDecimal NPV;
    private double IRR;

    public InvestmentProject(String name, int PP, BigDecimal NPV, double IRR) {
        this.name = name;
        this.PP = PP;
        this.NPV = NPV;
        this.IRR = IRR;
    }

    public InvestmentProject() {
    }

    public String getName() {
        return name;
    }

    public int getPP() {
        return PP;
    }

    public double getIRR() {
        return IRR;
    }

    public BigDecimal getNPV() {
        return NPV;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public void setNPV(BigDecimal NPV) {
        this.NPV = NPV;
    }

    public void setIRR(double IRR) {
        this.IRR = IRR;
    }
}
