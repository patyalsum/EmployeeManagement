package com.quarkstechnosoft.Employeemanagment.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quarkstechnosoft.Employeemanagment.dao.EmployeeRepository;
import com.quarkstechnosoft.Employeemanagment.entities.Employee;

@Service
public class EmployeemanagmentService {

	@Autowired
	private EmployeeRepository employeerepository;
	
	public List<Employee> getEmployee(){
		return employeerepository.findAll();
	}

	public List<Employee> addEmployee(Employee employee) {
		employeerepository.save(employee);
		return employeerepository.findAll();
	}

	public Optional<Employee> findEmployee(int eid) {
		return employeerepository.findById(eid);
		
	}

	public void deleteEmployee(int eid) {
		 employeerepository.deleteById(eid);	
	}
	
	public List<Employee> addEmployee(List<Employee> tempEmpList) {
		Iterator<Employee> itr=tempEmpList.iterator();
		
		while(itr.hasNext()) {
		Employee emp=itr.next();				
		employeerepository.save(emp);
		}
		return employeerepository.findAll();
	}

	public List<Employee> search(String employeename, String email, String phoneno) {
		
		return employeerepository.findByQuery(employeename,email,phoneno);
	}

}
