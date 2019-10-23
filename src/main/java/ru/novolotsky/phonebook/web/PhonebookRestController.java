package ru.novolotsky.phonebook.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.novolotsky.phonebook.model.Employee;
import ru.novolotsky.phonebook.model.PhoneNumber;
import ru.novolotsky.phonebook.service.EmployeeService;
import ru.novolotsky.phonebook.service.PhoneNumberService;

import java.util.List;

@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhonebookRestController {

    private Logger log = LoggerFactory.getLogger(PhonebookRestController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping
    public List<Employee> getAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(name = "size", defaultValue = "4", required = false) int size,
                                 @RequestParam(name = "filter", defaultValue = "", required = false) String filter) {

        return employeeService.getAll(page, size, filter);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee create(@RequestBody Employee employee) {
        log.info("Create {}", employee);
        return employeeService.save(employee);
    }

    @PostMapping(value = "{id}/phonenumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void create(@RequestBody PhoneNumber phoneNumber, @PathVariable("id") int employeeId) {
        log.info("bind {} to employeeId {}", phoneNumber, employeeId);
        phoneNumberService.create(phoneNumber, employeeId);
    }

    @DeleteMapping(value = "/phonenumber/{number}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNumber(@PathVariable("number") long number) {
        log.info("delete phonenumber {}", number);
        phoneNumberService.delete(number);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") int id) {
        log.info("delete employee {}", id);
        employeeService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody Employee employee) {
        log.info("update {}", employee);
        employeeService.save(employee);
    }

    @PutMapping(value = "{id}/phonenumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateNumber(@RequestBody PhoneNumber phoneNumber,@PathVariable("id") int employeeId) {
        log.info("update {} to {}", phoneNumber, employeeId);
        phoneNumberService.update(phoneNumber, employeeId);
    }


}