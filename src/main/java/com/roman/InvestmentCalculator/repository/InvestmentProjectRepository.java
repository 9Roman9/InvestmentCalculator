package com.roman.InvestmentCalculator.repository;

import com.roman.InvestmentCalculator.entity.InvestmentProject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface InvestmentProjectRepository extends CrudRepository<InvestmentProject, Long> {
    @Modifying
    @Query(value = "update project p set p.pp = :pp, p.irr = :irr, p.npv = :npv where p.name = :name", nativeQuery = true)
    void update(@RequestParam("name") String name, @RequestParam("pp") int pp, @RequestParam("npv") BigDecimal npv, @RequestParam("irr") double irr);

    @Modifying
    @Query(value = "delete project p where p.name = :name", nativeQuery = true)
    void deleteByName(@RequestParam("name") String name);

    @Query(value = "select * from project p where p.name = :name", nativeQuery = true)
    InvestmentProject findByName(@RequestParam("name") String name);
}
