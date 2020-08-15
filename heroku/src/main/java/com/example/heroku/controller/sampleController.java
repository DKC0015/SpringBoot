package com.example.heroku.controller;

import com.example.heroku.Exception.ResourceNotFoundException;
import com.example.heroku.Model.EmployeeModel;
import com.example.heroku.Repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class sampleController {

    @Autowired
    private EmpRepo empRepo;

    @GetMapping("/employees")
    public List<EmployeeModel> getEmployees() {

        return empRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeModel> getEmployeesByNum(@PathVariable(value = "id") Long Num) throws ResourceNotFoundException {

        EmployeeModel employee = empRepo.findById(Num)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + Num));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public EmployeeModel addEmployee(@Valid @RequestParam (name = "Employee ID") Long empId,
                                     @Valid @RequestParam (name = "Employee First name") String empName1,
                                     @Valid @RequestParam (name = "Employee Last name") String empName2,
                                     @Valid @RequestParam (name = "Email") String email,
                                     @Valid @RequestParam (name = "Address") String add) {

        EmployeeModel emp = new EmployeeModel();
        emp.setEmployeeNumber(empId);
        emp.setFirstName(empName1);
        emp.setLastName(empName2);
        emp.setEmailId(email);
        emp.setAddress(add);

        return empRepo.save(emp);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable(value = "id") Long num, @Valid @RequestBody EmployeeModel employeeDetails) throws ResourceNotFoundException {

        EmployeeModel employee = empRepo.findById(num)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this ID : " + num));

        employee.setAddress(employeeDetails.getAddress());
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());

        EmployeeModel updated = empRepo.save(employee);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Long> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        EmployeeModel employee = empRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        empRepo.delete(employee);
        Map<String, Long> response = new HashMap<>();
        response.put("deleted", employeeId);
        return response;
    }
}
