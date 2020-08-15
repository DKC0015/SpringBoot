package com.example.heroku.Repo;

import com.example.heroku.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<EmployeeModel, Long> {
}
