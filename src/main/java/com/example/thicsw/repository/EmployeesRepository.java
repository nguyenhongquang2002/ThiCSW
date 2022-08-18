package com.example.thicsw.repository;

import com.example.thicsw.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    List<Employees> findAllByName(String name);
    List<Employees> findAllByNameContainsIgnoreCase(String name);
    List<Employees> findAllByNameAndSalary(String name, String salary);
    List<Employees> findAllByNameOrderBySalaryAsc(String name); //Asc là ít - Desc là nhiều
}
