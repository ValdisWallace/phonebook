### ТЗ
Тестовое задание:
Написать приложение на Spring Boot с использованием сборщика Maven.

Приложение должно представлять собой северную часть (бэкенд) телефонного справочника. Архитектура приложения MVC.

Справочник состоит из личных данных о сотрудниках (фио, возраст, пол, email) и данных о телефонах (номер, флаг личный/рабочий).

Должны быть реализованы  rest-сервисы:
1. добавление данных о сотруднике
2. привязка номера телефона к сотруднику
3. удаление сведений об  определённом номере телефона
4. Удаление сведений о сотруднике, включая все номера его телефонов.
5. Получение списка всех сотрудников с их номерами телефонов

Не обязательные требования (будет плюсом):
1. Постраничный вывод списка сотрудников
2. Реализовать сервис поиска сотрудников, где в качестве параметра передаётся фио (или его часть) либо номер телефона (или его часть).
В качестве СУБД желательно использовать postgres 9.6. Для запросов в приложении можно использовать любую ORM, или использовать jdbctemplate.
Результат присылать в виде исходного кода, инструкции по сборке и запуску, схемы базы данных.
Модель данных в БД, HTTP запросах и ответах - на свое усмотрение. При этом их правильность и оптимальность имеет важное значение.
Все HTTP запросы и ответы - в формате json.

### Создать базу данных Postgresql
### В папке phonebook\src\main\resources настроить подключения к базе данных в файле application.properties и выпольнить скрипт для создания таблиц initDB.sql и наполнения populateDB.sql
### Создать jar файл с помощью mvn -package
### запустить jar файл

### curl samples (application deployed in application context `employees`).
> For windows use `Git Bash`

#### get All Employees with Pagination (default page = 1, default size = 4)
`curl -s http://localhost:8080/employees`

#### get All Employees by Page 2
`curl -s http://localhost:8080/employees?page=2`

#### get Employees with filter by Name
`curl -s http://localhost:8080/employees?filter=Vadim`

#### get Employees with filter by Number
`curl -s http://localhost:8080/employees?filter=8999`

#### create Employee
`curl -s -X POST -d '{"name": "Lida Ivanovna", "age": 31, "email": "lida@mail.ru", "sex": false}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/employees`

#### create Phone number
`curl -s -X POST -d '{"number": 89998973453, "status": false}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/employees/100002/phonenumber`

#### update Phone number to Employee
`curl -s -X PUT -d '{"id": 100011, "number": 89456667432, "status": false}' -H 'Content-Type: application/json' http://localhost:8080/employees/100001/phonenumber`

#### delete Employee
`curl -s -X DELETE http://localhost:8080/employees/100001`

#### delete Phone number
`curl -s -X DELETE http://localhost:8080/employees/phonenumber/89994624400`

#### update Employee
`curl -s -X PUT -d '{"id": 100001, "name": "Ivanov Ivan", "age": 55, "email": "ivanov@gmail.com", "sex": true}' -H 'Content-Type: application/json' http://localhost:8080/employees`
