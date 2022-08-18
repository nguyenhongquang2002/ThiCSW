package com.example.thicsw.service;

import com.example.thicsw.model.Employees;

import java.util.List;

public interface EmployeesService {
    public void saveEmployees(Employees e);
    public void deleteEmployees(Integer id);
    public Employees findById(Integer id);
    public List<Employees> findAll();
    public List<Employees> findAllByName(String name);
}
