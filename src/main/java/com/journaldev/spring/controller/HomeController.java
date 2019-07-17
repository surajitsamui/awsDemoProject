package com.journaldev.spring.controller;

import com.journaldev.spring.model.Employee;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        System.out.println("Home Page Requested, locale = " + locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user(@Validated User user, Model model) {
        System.out.println("User Page Requested");
        model.addAttribute("userName", user.getUserName());
        return "user";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getEmpoyees(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("javainuse", "password"));
        ResponseEntity<Employee> empList = restTemplate.getForEntity("http://localhost:8091/angularEmployees", Employee.class);
        System.out.println(empList);
        model.addAttribute("employee", empList);
        return "employee";
    }

    @RequestMapping(value = "/employeesList", method = RequestMethod.GET)
    public String getEmpoyeesList(Model model) {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("javainuse", "password"));
        ResponseEntity<List<Employee>> rateResponse = restTemplate.exchange("http://surajitawsdemo.us-west-2.elasticbeanstalk.com/viewEmployees", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });
        List<Employee> empList = rateResponse.getBody();
        model.addAttribute("employee", empList);
        return "employee";
    }
    
    @RequestMapping(value = "/employeesListNew", method = RequestMethod.GET)
    public String getEmpoyeesListNew(Model model) {
        model.addAttribute("employee", employees);
        return "employee";
    }
    
    
    
    private static List<Employee> createList() {
        List<Employee> tempEmployees = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("emp1");
        emp1.setDesignation("manager");
        emp1.setEmpId("1");
        emp1.setSalary(3000);

        Employee emp2 = new Employee();
        emp2.setName("emp2");
        emp2.setDesignation("developer");
        emp2.setEmpId("2");
        emp2.setSalary(3000);
        tempEmployees.add(emp1);
        tempEmployees.add(emp2);
        return tempEmployees;
    }
    private List<Employee> employees = createList();
}
