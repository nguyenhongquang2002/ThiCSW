package com.example.thicsw.controller;

import com.example.thicsw.model.Employees;
import com.example.thicsw.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    @Autowired
    EmployeesService employeesService;

    @RequestMapping(value ="list",method = RequestMethod.GET)
    public ResponseEntity<List<Employees>> findAllEmployees(){
        List<Employees> lsEmployees = employeesService.findAll();
        if(lsEmployees.size()==0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<List<Employees>>(lsEmployees,HttpStatus.OK);
    }



    @RequestMapping(value ="employeesbyname",method = RequestMethod.GET)
    public ResponseEntity<List<Employees>> findAllEmployees(@PathParam("name") String name){
        List<Employees> lsEmployees = employeesService.findAllByName(name);
        if(lsEmployees.size()==0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<List<Employees>>(lsEmployees,HttpStatus.OK);
    }


    @RequestMapping(value ="create",method = RequestMethod.POST)
    public ResponseEntity<Employees> saveNewEmployees(@RequestBody Employees employees ){
        employeesService.saveEmployees(employees);
        return new ResponseEntity<Employees>(employees,HttpStatus.OK);
    }
//    http://localhost:8080/updateUser
    @RequestMapping(value ="update",method = RequestMethod.PUT)
    public ResponseEntity<Employees> saveNewEmployees(
        @Param("id") Integer id,
        @RequestBody Employees employees ){
        Employees e = employeesService.findById(id);
        e.setName(employees.getName());
        e.setSalary(employees.getSalary());
        employeesService.saveEmployees(e);
        return new ResponseEntity<Employees>(e,HttpStatus.OK);
    }

    @RequestMapping(value ="delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Employees> deleteEmployees(@PathVariable(value = "id") Integer id){
        employeesService.deleteEmployees(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName()  + " "
                    + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
