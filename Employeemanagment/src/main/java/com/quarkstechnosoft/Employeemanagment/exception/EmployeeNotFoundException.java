package com.quarkstechnosoft.Employeemanagment.exception;

public class EmployeeNotFoundException extends Exception{
		private int eid;
		public EmployeeNotFoundException(int eid) {
		        super(String.format("Employee not found with eid : '%s'", eid));
		        }
		}


