package com.quarkstechnosoft.Employeemanagment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="employee")
public class Employee {
	
	@Id
	@GeneratedValue
	@Column(name="eid")
	private Integer eid;
	
	@Column(name="employeename")
	private String employeename;

	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;

	@Column(name="phoneno")
	private String phoneno;
	
	public Employee() {
		
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	
}
