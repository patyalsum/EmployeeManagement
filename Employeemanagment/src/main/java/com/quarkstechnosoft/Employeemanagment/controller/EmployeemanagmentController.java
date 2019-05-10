package com.quarkstechnosoft.Employeemanagment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.quarkstechnosoft.Employeemanagment.entities.Employee;
import com.quarkstechnosoft.Employeemanagment.exception.EmployeeNotFoundException;
import com.quarkstechnosoft.Employeemanagment.service.EmployeemanagmentService;


@RestController
@RequestMapping(value="/api")
public class EmployeemanagmentController {

	@Autowired
	private EmployeemanagmentService employeemanagmentservice;
	
	//To get all the employees
	@GetMapping(value = "/all")
    public List<Employee> getAllEmployee() {
        return employeemanagmentservice.getEmployee();
	}
	
	//To create a new employee
	@PostMapping(value="/load") 
	public List<Employee> persist(@RequestBody Employee employee) {
		return employeemanagmentservice.addEmployee(employee);
	}
	
	
	//To Delete a Employee
    @DeleteMapping("/employee/{eid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "eid") int eid) throws EmployeeNotFoundException {
    	employeemanagmentservice.findEmployee(eid).orElseThrow(() -> new EmployeeNotFoundException(eid));
    	
    	employeemanagmentservice.deleteEmployee(eid);
    	return ResponseEntity.status(HttpStatus.OK)
    	        .body("Employee deleted with eid " + eid);
    }
	
    
  // Upload a excel
    @PostMapping("/upload")
    public List<Employee> fileupload(@RequestParam("file") MultipartFile file) throws IOException
    {
    	List<Employee> tempEmpList = new ArrayList<Employee>();
        try(XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream()))
        {
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	Employee tempEmployee = new Employee();

            XSSFRow row = worksheet.getRow(i);

//            tempEmployee.setEid((int) row.getCell(0).getNumericCellValue());
            tempEmployee.setEmployeename(row.getCell(0).getStringCellValue());
            tempEmployee.setEmail(row.getCell(1).getStringCellValue());
            tempEmployee.setAddress(row.getCell(2).getStringCellValue());
            tempEmployee.setPhoneno(NumberToTextConverter.toText(row.getCell(3).getNumericCellValue()));
            tempEmpList.add(tempEmployee);   
        }
        }catch(IOException e) {e.printStackTrace();}
        return employeemanagmentservice.addEmployee(tempEmpList);
    	
    }
    
    
    //Search with employeename,email and phone no
    @GetMapping("/employee")
   public List<Employee> customSearch(@RequestParam Map<String,String> customQuery)
    {
    	String employeename=customQuery.get("name");
    	String email=customQuery.get("email");
    	String phoneno=customQuery.get("phoneNo");
    	
    	return employeemanagmentservice.search(employeename,email,phoneno);	
    }
    
    
	
		
}
