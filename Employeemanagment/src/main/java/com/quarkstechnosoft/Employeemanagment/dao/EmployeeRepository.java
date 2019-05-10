package com.quarkstechnosoft.Employeemanagment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quarkstechnosoft.Employeemanagment.entities.Employee;

@Repository("employeerepository")
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query("select e from Employee e where (?1 is null or e.employeename = ?1) and (?2 is null or e.email = ?2 ) and (?3 is null or e.phoneno = ?3 )")
	List<Employee> findByQuery(String employeename,String email,String phoneno);
}
