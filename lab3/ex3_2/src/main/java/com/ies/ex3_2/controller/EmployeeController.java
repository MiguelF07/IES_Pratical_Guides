package com.ies.ex3_2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.ex3_2.exception.EmailDoesNotExistException;
import com.ies.ex3_2.exception.ResourceNotFoundException;
import com.ies.ex3_2.model.Employee;
import com.ies.ex3_2.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees") //All employees resources are fetched
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}") //One Employee resource is fetched
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    
    @PostMapping("/employees") //A new employee resource is created
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}") //Employee resource is updated
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}") //Employee resource is deleted
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // @GetMapping("/employees?email={email}")
    // public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable(value = "email") String employeeEmail)
    //     throws ResourceNotFoundException {
    //         Long id=0L;
    //         for(Employee e: employeeRepository.findAll())
    //         {
    //             if(e.getEmailId().equals(employeeEmail))
    //             {
    //                 id = e.getId();
    //             }
    //         }
    //     Employee employee = employeeRepository.findById(id)
    //       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this email :: " + employeeEmail));
    //     return ResponseEntity.ok().body(employee);
    // }
    @GetMapping("/employees?email={email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable(value = "email") String employeeEmail)
    throws EmailDoesNotExistException {
        Employee employee = employeeRepository.findByEmailId(employeeEmail)
          .orElseThrow(() -> new EmailDoesNotExistException("Employee not found for this email :: " + employeeEmail));
        return ResponseEntity.ok().body(employee);
    }
}