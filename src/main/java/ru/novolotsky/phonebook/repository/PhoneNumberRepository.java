package ru.novolotsky.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.novolotsky.phonebook.model.Employee;
import ru.novolotsky.phonebook.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM PhoneNumber n WHERE n.number=:number")
    int delete(@Param("number") long number);

    @Query("SELECT p FROM PhoneNumber p WHERE p.number=:number")
    PhoneNumber getByNumber(@Param("number") long number);

    @Transactional
    @Modifying
    @Query("UPDATE PhoneNumber p SET p.number=:number, p.status=:status, p.employee=:employee WHERE p.id=:id")
    int update(@Param("id") int id,
               @Param("number") long number,
               @Param("status") boolean status,
               @Param("employee") Employee employee);
}
