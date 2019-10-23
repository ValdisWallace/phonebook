package ru.novolotsky.phonebook;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import ru.novolotsky.phonebook.model.Employee;
import ru.novolotsky.phonebook.model.PhoneNumber;

import java.util.*;
import java.util.stream.Collectors;

public class TestData {
    public static final int PAGE_NUMBER = 1;
    public static final int PAGE_SIZE = 4;

    public static final Employee EMPLOYEE1 = new Employee(100000, "Novolotsky Vadim Alekseevich", 26, "valdisvanwallace@gmail.com", true);
    public static final Employee EMPLOYEE2 = new Employee(100001, "Ivanov Ivan Ivanovich", 55, "ivanov@gmail.com", true);
    public static final Employee EMPLOYEE3 = new Employee(100002, "Zinoveva Zinoida Zinoidovna", 60, "zinaida007@gmail.com", false);
    public static final Employee EMPLOYEE4 = new Employee(100003, "Mihail Ivanovich", 12, "miha@gmail.com", true);
    public static final Employee EMPLOYEE5 = new Employee(100004, "Ilya Vitalievich", 32, "ilya@gmail.com", true);
    public static final Employee EMPLOYEE6 = new Employee(100005, "Ludmila Sergeevna", 55, "ludmila@gmail.com", false);
    public static final Employee EMPLOYEE7 = new Employee(100006, "Semen Semenych", 23, "sema@gmail.com", true);
    public static final Employee EMPLOYEE8 = new Employee(100007, "Irina Sergeevna", 23, "ira@gmail.com", false);
    public static final Employee EMPLOYEE9 = new Employee(100008, "Dinis Kozlov", 25, "kozlov@gmail.com", true);

    public static List<Employee> EMPLOYEES = List.of(EMPLOYEE1, EMPLOYEE2, EMPLOYEE3, EMPLOYEE4, EMPLOYEE5, EMPLOYEE6, EMPLOYEE7, EMPLOYEE8, EMPLOYEE9);

    public static final PhoneNumber PHONE_NUMBER1 = new PhoneNumber(100009, 89994624400L, false);
    public static final PhoneNumber PHONE_NUMBER2 = new PhoneNumber(100010, 89287514400L, true);

    public static List<Employee> getSortedEmployees(List<Employee> list) {
//        List<Employee> sorted = new ArrayList<>(list);
        return list.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .limit(PAGE_SIZE)
                .collect(Collectors.toList());
    }

    public static Employee getUpdatedEmployee() {
        return new Employee(100000, "Novolotsky Vadim Update", 26, "valdisvanwallace@gmail.com", true);
    }

    public static List<Employee> addAndGetEmployeesSorted(Employee employee) {
        List<Employee> list = new ArrayList<>(EMPLOYEES);
        list.add(employee);
        return getSortedEmployees(list);
    }

    public static PhoneNumber getUpdatedPhoneNumber() {
        return new PhoneNumber(100009, 89994624400L, true);
    }

    public static void assertMatch(Employee actual, Employee expected) {
        Assertions.assertThat(actual).isEqualToIgnoringGivenFields(expected, "phoneNumbers");
    }

    public static void assertMatch(PhoneNumber actual, PhoneNumber expected) {
        Assertions.assertThat(actual).isEqualToIgnoringGivenFields(expected, "employee");
    }

    public static void assertMatchEmployees(Iterable<Employee> actual, Iterable<Employee> expected) {
        Assertions.assertThat(actual).usingElementComparatorOnFields("id", "email", "age", "sex", "name").isEqualTo(expected);
    }

    public static void assertMatchNumber(Iterable<PhoneNumber> actual, Iterable<PhoneNumber> expected) {
        Assertions.assertThat(actual).usingElementComparatorOnFields("id", "number", "status").isEqualTo(expected);
    }
}
