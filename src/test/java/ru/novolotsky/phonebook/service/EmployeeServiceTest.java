package ru.novolotsky.phonebook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.novolotsky.phonebook.model.Employee;

import java.util.List;

import static ru.novolotsky.phonebook.TestData.*;

@SpringBootTest
@Sql(scripts = "classpath:populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Test
    void create() {
        Employee newEmployee = new Employee(null, "Andrey", 14, "vitya@mail.ru", true);
        Employee created = employeeService.save(new Employee(newEmployee));
        newEmployee.setId(created.getId());
        assertMatch(created, newEmployee);
        assertMatchEmployees(employeeService.getAll(PAGE_NUMBER, PAGE_SIZE, ""), addAndGetEmployeesSorted(created));
    }

    @Test
    void delete() {
        employeeService.delete(100002);
        assertMatchEmployees(List.of(EMPLOYEE9, EMPLOYEE5, EMPLOYEE8, EMPLOYEE2),
                employeeService.getAll(PAGE_NUMBER, PAGE_SIZE, ""));
    }

    @Test
    void get() {
        assertMatch(EMPLOYEE1, employeeService.getById(100000));
    }

    @Test
    void getAll() {
        assertMatchEmployees(getSortedEmployees(EMPLOYEES), employeeService.getAll(PAGE_NUMBER, PAGE_SIZE, ""));
    }

    @Test
    void updateEmployee() {
        employeeService.save(getUpdatedEmployee());
        assertMatch(getUpdatedEmployee(), employeeService.getById(100000));
    }

    @Test
    void updatePhoneNumber() {
        phoneNumberService.update(getUpdatedPhoneNumber(), 100000);
        assertMatch(getUpdatedPhoneNumber(), phoneNumberService.getByNumber(89994624400L));
    }

    @Test
    void getByName() {
        assertMatchEmployees(List.of(EMPLOYEE1), employeeService.getAll(1, 4, "Vadim"));
    }

    @Test
    void getByPhoneNumber() {
        assertMatchEmployees(List.of(EMPLOYEE1), employeeService.getAll(1, 4, "89994624400"));
    }
}