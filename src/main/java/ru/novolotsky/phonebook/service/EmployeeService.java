package ru.novolotsky.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.novolotsky.phonebook.exception.PageNotFoundException;
import ru.novolotsky.phonebook.model.Employee;
import ru.novolotsky.phonebook.repository.EmployeeRepository;
import ru.novolotsky.phonebook.repository.PhoneNumberRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public boolean delete(int id) {
        return employeeRepository.delete(id) != 0;
    }

    public Employee getById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getAll(int page, int size, String filter) {
        Sort sortByName = new Sort(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page - 1, size, sortByName);

        Page<Employee> currentPage = Page.empty();

        //if contains only digits
        if (filter.matches("\\d+") && filter.length() < 15) {
            currentPage = employeeRepository.findByNumber(Long.parseLong(filter), pageable);
        } else if (filter.isBlank()){
            currentPage = employeeRepository.findAll(pageable);
        } else {
            currentPage = employeeRepository.findByName(filter, pageable);
        }

        //check if page not exist
        if (currentPage.getTotalPages() != 0 && currentPage.getTotalPages() < page)
            throw new PageNotFoundException(page);

        return currentPage.getContent();
    }
}
