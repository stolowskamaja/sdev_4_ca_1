package models;

import java.util.*;
import javax. persistence.*;
import javax.validation.Constraint;

import io.ebean.*;
import io.ebean.annotation.Formula;
import play.data.validation.*;

import io.ebean.Model;

@Entity
public class Department extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @OneToMany
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(Long id, @Constraints.Required String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void addEmployee(Employee emp){
        this.employees.add(emp);
    }

    public static final Finder<Long, Department> find = new Finder<>(Department.class);

    public static final List<Department> findAll() {
        return Department.find.all();
    }

    public static Department getDepartmentById(String id){
        if (id == null) {
            return null;
        } else {
            return find.query().where().eq("name", id).findUnique();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap();

        // Get all the categories from the database and add them to the options hash map
        for (Department department: Department.findAll()) {
            options.put(department.getId().toString(), department.getName());
        }
        return options;
    }
}
