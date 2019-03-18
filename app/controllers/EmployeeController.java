package controllers;

import controllers.routes;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class EmployeeController extends Controller{

    private FormFactory formFactory;

    @Inject
    public EmployeeController(FormFactory f) {
        this.formFactory = f;
    }


    @Security.Authenticated(Secured.class)
    public Result employees(){
        List<Employee> employeeList = Employee.findAll();

        Employee employee = Employee.getEmployeeById(session().get("email"));

        Form<Employee> employeeForm = formFactory.form(Employee.class);


        return ok(employees.render(employeeList, employee, employeeForm));


    }


    @Security.Authenticated(Secured.class)
    public Result employee(Long id){
        Employee managedEmployee;
        Employee loggedInEmployee = Employee.getEmployeeById(session().get("email"));
        List<Project> allProjects = Project.findAll();


        try {
            managedEmployee = Employee.find.byId(id);
        } catch(Exception ex){
            return badRequest("error");
        }

        return ok(employee.render(loggedInEmployee, managedEmployee, allProjects));
    }

    @Security.Authenticated(Secured.class)
    @With(AuthManager.class)
    @Transactional
    public Result addProject(Long projectId, Long employeeId ){
        Project project = Project.find.ref(projectId);
        Employee employee = Employee.find.ref(employeeId);


        if (employee.isAssignedTo(project) == false) {

            employee.addProject(project);

            employee.update();
        }


        return redirect(controllers.routes.EmployeeController.employee(employeeId));
    }

    @Security.Authenticated(Secured.class)
    @With(AuthManager.class)
    @Transactional
    public Result removeProject(Long projectId, Long employeeId ){
        Project project = Project.find.ref(projectId);
        Employee employee = Employee.find.ref(employeeId);


        if (employee.isAssignedTo(project)) {

            employee.removeProject(project);

            employee.update();
        }


        return redirect(controllers.routes.EmployeeController.employee(employeeId));
    }

    @With(AuthManager.class)
    public Result updateEmployee(Long id){
        Employee managedEmployee = Employee.find.byId(id);
        Form<Employee> employeeForm = formFactory.form(Employee.class).fill(managedEmployee);
        return ok(updateEmployee.render(employeeForm, Employee.getEmployeeById(session().get("email")), managedEmployee));
    }

    @With(AuthManager.class)
    public Result updateEmployeeSubmit(Long id){
        Form<Employee> employeeForm = formFactory.form(Employee.class).bindFromRequest().discardingErrors();
        Employee managedEmployee = Employee.find.byId(id);


        String name = employeeForm.get().getName();
        String email = employeeForm.get().getEmail();
        String password = employeeForm.get().getPassword();
        Department department = Department.find.byId(Long.parseLong(employeeForm.field("department.id").getValue().get())) ;
        managedEmployee.setName(name);
        managedEmployee.setEmail(email);
        if(!password.isEmpty()) {
            managedEmployee.setPassword(password);
        }
        managedEmployee.setDepartment(department);
        managedEmployee.update();
        return redirect(controllers.routes.EmployeeController.employees());

    }

}
