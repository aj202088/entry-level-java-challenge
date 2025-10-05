package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import java.time.Instant;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    Map<UUID, Employee> employees = new HashMap<>();

    public EmployeeController() {
        // Mock employee data
        NewEmployee mock = new NewEmployee();
        mock.setUuid(UUID.randomUUID());
        mock.setFirstName("Ashton");
        mock.setLastName("Penalacia");
        mock.setFullName("Ashton Penalacia");
        mock.setSalary(100000);
        mock.setAge(21);
        mock.setJobTitle("Software Engineer");
        mock.setEmail("ashton.penalacia@gmail.com");
        mock.setContractHireDate(Instant.now());
        employees.put(mock.getUuid(), mock);
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(UUID uuid) {
        return employees.get(uuid);
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping
    public Employee createEmployee(Object requestBody) {
        if (!(requestBody instanceof Map)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) requestBody;
        NewEmployee newEmployee = new NewEmployee();

        newEmployee.setUuid(UUID.randomUUID());
        newEmployee.setFirstName((String) body.get("firstName"));
        newEmployee.setLastName((String) body.get("lastName"));
        newEmployee.setFullName(newEmployee.getFirstName() + " " + newEmployee.getLastName());
        newEmployee.setSalary((Integer) body.get("salary"));
        newEmployee.setAge((Integer) body.get("age"));
        newEmployee.setJobTitle((String) body.get("jobTitle"));
        newEmployee.setEmail((String) body.get("email"));
        newEmployee.setContractHireDate(Instant.now());
        employees.put(newEmployee.getUuid(), newEmployee);

        return newEmployee;
    }

    /**
     * Simple implementation of Employee interface.
     * @return Updated Employee if exists
     */
    static class NewEmployee implements Employee {
        private UUID uuid;
        private String firstName;
        private String lastName;
        private String fullName;
        private Integer salary;
        private Integer age;
        private String jobTitle;
        private String email;
        private Instant contractHireDate;
        private Instant contractTerminationDate;

        @Override
        public UUID getUuid() {
            return uuid;
        }

        @Override
        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        @Override
        public String getFirstName() {
            return firstName;
        }

        @Override
        public void setFirstName(String name) {
            this.firstName = name;
        }

        @Override
        public String getLastName() {
            return lastName;
        }

        @Override
        public void setLastName(String name) {
            this.lastName = name;
        }

        @Override
        public String getFullName() {
            return fullName;
        }

        @Override
        public void setFullName(String name) {
            this.fullName = name;
        }

        @Override
        public Integer getSalary() {
            return salary;
        }

        @Override
        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        @Override
        public Integer getAge() {
            return age;
        }

        @Override
        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String getJobTitle() {
            return jobTitle;
        }

        @Override
        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public Instant getContractHireDate() {
            return contractHireDate;
        }

        @Override
        public void setContractHireDate(Instant date) {
            this.contractHireDate = date;
        }

        @Override
        public Instant getContractTerminationDate() {
            return contractTerminationDate;
        }

        @Override
        public void setContractTerminationDate(Instant date) {
            this.contractTerminationDate = date;
        }
    }
}
