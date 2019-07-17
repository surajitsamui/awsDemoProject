package com.journaldev.spring.service;

/**
 *
 * @author surajit.samui
 */



import com.journaldev.spring.model.Employee;
import java.util.List;


public interface EmployeeService {
	public List<Employee> getAllEmployees();
        public Employee createEmployee(String name, String empId);
        public void deleteEmployee(String empId);

}