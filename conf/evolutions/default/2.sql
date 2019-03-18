# --- Sample dataset

# --- !Ups

insert into department (name) values ('Customer Support');
insert into department (name) values ('Human Resource Management');
insert into department (name) values ('Business');
insert into department (name) values ('Marketing');
insert into department (name) values ('Web Development');
insert into department (name) values ('Software Development');
insert into department (name) values ('Quality Assurance');

insert into employee (email, name, password, role, department_id) values ( 'employee1@employee.com', 'Employee1', 'password', 'employee', 1);
insert into employee (email, name, password, role, department_id) values ( 'employee2@employee.com', 'Employee2', 'password', 'employee', 2 );
insert into employee (email, name, password, role, department_id) values ( 'employee3@employee.com', 'Employee3', 'password', 'employee', 3 );
insert into employee (email, name, password, role, department_id) values ( 'employee4@employee.com', 'Employee4', 'password', 'employee', 4 );
insert into employee (email, name, password, role, department_id) values ( 'employee5@employee.com', 'Employee5', 'password', 'employee', 5 );
insert into employee (email, name, password, role, department_id) values ( 'employee6@employee.com', 'Employee6', 'password', 'employee', 6 );
insert into employee (email, name, password, role, department_id) values ( 'employee7@employee.com', 'Employee7', 'password', 'employee', 7 );

insert into employee (email, name, password, role, department_id) values ( 'manager1@manager.com', 'Manager1', 'password', 'manager', 1 );
insert into employee (email, name, password, role, department_id) values ( 'manager2@manager.com', 'Manager2', 'password', 'manager', 2 );
insert into employee (email, name, password, role, department_id) values ( 'manager3@manager.com', 'Manager3', 'password', 'manager', 3 );
insert into employee (email, name, password, role, department_id) values ( 'manager4@manager.com', 'Manager4', 'password', 'manager', 4 );
insert into employee (email, name, password, role, department_id) values ( 'manager5@manager.com', 'Manager5', 'password', 'manager', 5 );
insert into employee (email, name, password, role, department_id) values ( 'manager6@manager.com', 'Manager6', 'password', 'manager', 6 );
insert into employee (email, name, password, role, department_id) values ( 'manager7@manager.com', 'Manager7', 'password', 'manager', 7 );

insert into address (address, employee_id) values ('122  Witney Way KNIGHTON', 1);
insert into address (address, employee_id) values ('59  George Street BROXTON', 2);
insert into address (address, employee_id) values ('133  Jedburgh Road BRANSTON', 3);
insert into address (address, employee_id) values ('20  Abingdon Road GALMPTON', 4);
insert into address (address, employee_id) values ('84  Stone Cellar Road KINGSFORD', 5);
insert into address (address, employee_id) values ('120  Whitchurch Road ENNERDALE BRIDGE', 6);

insert into project (name, description, created_at) values ('Project 1', 'making a project1', TO_DATE('2019-01-05-11', 'YYYY-MM-DD-HH24') );
insert into project (name, description, created_at) values ('Project 2', 'making a project2', TO_DATE('2019-02-05-16', 'YYYY-MM-DD-HH24') );
insert into project (name, description, created_at) values ('Project 3', 'making a project3', TO_DATE('2019-03-05-17', 'YYYY-MM-DD-HH24') );
insert into project (name, description, created_at) values ('Project 4', 'making a project4', TO_DATE('2019-01-19-9', 'YYYY-MM-DD-HH24') );
insert into project (name, description, created_at) values ('Project 5', 'making a project5', TO_DATE('2019-01-25-13', 'YYYY-MM-DD-HH24') );


insert into employee_project (employee_id, project_id) values (1, 1);
insert into employee_project (employee_id, project_id) values (1, 2);
insert into employee_project (employee_id, project_id) values (2, 2);
insert into employee_project (employee_id, project_id) values (3, 3);
insert into employee_project (employee_id, project_id) values (4, 4);
insert into employee_project (employee_id, project_id) values (5, 5);

