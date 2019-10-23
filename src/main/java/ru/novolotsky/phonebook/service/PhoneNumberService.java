package ru.novolotsky.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novolotsky.phonebook.model.PhoneNumber;
import ru.novolotsky.phonebook.repository.EmployeeRepository;
import ru.novolotsky.phonebook.repository.PhoneNumberRepository;

@Service
public class PhoneNumberService {
    @Autowired
    private PhoneNumberRepository numberRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void create(PhoneNumber phoneNumber, int employeeId) {
        phoneNumber.setEmployee(employeeRepository.getOne(employeeId));
        numberRepository.save(phoneNumber);
    }

    public void update(PhoneNumber phoneNumber, int employeeId) {
        numberRepository.update(phoneNumber.getId(),
                phoneNumber.getNumber(),
                phoneNumber.isStatus(),
                employeeRepository.getOne(employeeId));
    }

    @Transactional
    public boolean delete(long number) {
        return numberRepository.delete(number) != 0;
    }

    public PhoneNumber getByNumber(long number) {
        return numberRepository.getByNumber(number);
    }
}
