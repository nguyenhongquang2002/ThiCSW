package com.example.thicsw.service;

import com.example.thicsw.model.Employees;
import com.example.thicsw.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceimpl implements EmployeesService {
    @Autowired

    EmployeesRepository employeesRepository;

    @Override
    public void saveEmployees(Employees e) {
        employeesRepository.save(e);
    }

    @Override
    public void deleteEmployees(Integer id) {
        employeesRepository.deleteById(id);
    }

    @Override
    public Employees findById(Integer id) {
        return employeesRepository.findById(id).get();
    }

    @Override
    public List<Employees> findAll() {
        return employeesRepository.findAll();
    }

    public List<Employees> findAllByName(String name){
        return employeesRepository.findAllByName(name);
    }
}
