-- 1. Из таблиц locations и countries выбрать адреса (location_id, street_address, city, state_province, country_name) всех подразделений
CREATE VIEW hw_db.department_location 
AS
SELECT dep.location_id, street_address, city, state_province, country_name
FROM hw_db.departments dep
JOIN hw_db.locations loc ON loc.location_id = dep.location_id
JOIN hw_db.countries c ON c.country_id = loc.country_id;

-- 2. Из таблиц employees и departments выбрать имена сотрудников (first_name, last name) и названия подразделений (department_name) в которых они работают.

CREATE VIEW hw_db.employees_department 
AS
SELECT first_name, last_name, department_name
FROM hw_db.employees   e
JOIN hw_db.departments d ON e.department_id = d.department_id;

-- 3. Из таблиц locations, employees и departments выбрать имена (first_name, last_name), должность (job_id), номер подразделения (department_id), 
-- и названия подразделения для сотрудников из города London

CREATE VIEW hw_db.employees_from_london
AS
SELECT first_name, last_name, job_id, e.department_id, d.department_name
FROM hw_db.employees   e
JOIN hw_db.locations   loc ON loc.city LIKE("London")
JOIN hw_db.departments d   ON e.department_id = d.department_id 
AND d.location_id = loc.location_id;

-- 4. Из таблиц employees, departments и locations выбрать названия подразделений (department_name), имя сотрудника (first_name), и город (city)

CREATE VIEW hw_db.employees_department_location 
AS
SELECT first_name, last_name,d.department_name, loc.city
FROM hw_db.employees   e
JOIN hw_db.locations   loc
JOIN hw_db.departments d   ON e.department_id = d.department_id 
AND d.location_id = loc.location_id;