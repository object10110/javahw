use wrpracti_bookinfo;
-- 1. Write a query to display the names (first_name, last_name) using alias name “First Name", "Last Name"
select FIRST_NAME as 'First Name', LAST_NAME 'Last Name'
from employees;

# 2. Write a query to get unique department ID from employee table
select distinct DEPARTMENT_ID
from employees;

# 3. Write a query to get all employee details from the employee table order by first name, descending.
select FIRST_NAME, LAST_NAME, SALARY
from employees
order by FIRST_NAME DESC;

# 4. Write a query to get the employee ID, name (first_name, last_name), salary in ascending order of salary.

# 5. Write a query to get the total salaries payable to employees.
select sum(SALARY) 'salaries'
from employees;

# 6. Write a query to get the maximum and minimum salary from employees table
select max(SALARY) 'max salary', min(SALARY) 'min salary'
from employees;

# 7. Write a query to get the average salary and number of employees in the employees table
select avg(SALARY) 'average salary', count(EMPLOYEE_ID) 'number of emploees'
from employees;

# 8. Write a query to get the number of employees working with the company
select count(*)
from employees;

# 9.
select JOB_TITLE, count(JOB_TITLE) 'number of emploees', avg(SALARY) seleries
from employees
         join jobs
              on jobs.JOB_ID = employees.JOB_ID
group by JOB_TITLE
order by seleries desc
;

use hospital;
insert into roles(name)
values ('admin');
update users
set role_id=3
where id = 7;
select count(*)
from roles;
select count(*)
from users;
select users.id, login, password, enable, name 'role'
from users,
     roles
where role_id = roles.id;

select users.id, login, password, enable, name 'role'
from users
         inner join roles
                    on users.role_id = roles.id;

