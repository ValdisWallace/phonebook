package ru.novolotsky.phonebook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.novolotsky.phonebook.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Query(value = "SELECT * FROM employees WHERE name SIMILAR TO CONCAT('(% |)', :name, '(| %)')",
            nativeQuery = true)
    Employee findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM employees WHERE name SIMILAR TO CONCAT('(% |)', :name, '(| %)')",
            nativeQuery = true)
    Page<Employee> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM employees WHERE id IN " +
            "(SELECT employee_id FROM phone_numbers WHERE CAST(number as text) SIMILAR TO CONCAT('%', :number, '%'))",
            nativeQuery = true)
    Page<Employee> findByNumber(@Param("number") long number, Pageable pageable);
}
