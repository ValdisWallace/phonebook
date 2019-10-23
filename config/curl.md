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

#### bind Phone number to Employee
`curl -s -X PUT -d '{"id": 100005, "number": 89456667432, "status": false}' -H 'Content-Type: application/json' http://localhost:8080/employees/100001/phonenumber`

#### delete Employee
`curl -s -X DELETE http://localhost:8080/employees/100001`

#### delete Phone number
`curl -s -X DELETE http://localhost:8080/employees/phonenumber/89994624400`

#### update Employee
`curl -s -X PUT -d '{"id": 100001, "name": "Ivanov Ivan", "age": 55, "email": "ivanov@gmail.com", "sex": true}' -H 'Content-Type: application/json' http://localhost:8080/employees`

