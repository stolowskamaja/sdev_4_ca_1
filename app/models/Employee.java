package models;

import java.util.*;
import javax. persistence.*;
import javax.validation.Constraint;

import io.ebean.*;
import io.ebean.annotation.Formula;
import play.data.validation.*;

import io.ebean.Model;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue("employee")
public class Employee extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String email;

    @Constraints.Required
    private String password;

    @Formula(select = "role")
    private String role;

    @OneToOne(mappedBy = "employee")
    private Address address;

    @ManyToOne
    private Department department;

    @ManyToMany
    private List<Project> projects = new ArrayList<>();

    private List<Long> projectSelect = new ArrayList<>();


    public Employee() {
    }

    public Employee(Long id, @Constraints.Required String name, @Constraints.Required String email, @Constraints.Required String password, Address address, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.department = department;
    }

    public List<Long> getProjectSelect() {
        return projectSelect;
    }

    public void setProjectSelect(List<Long> projectSelect) {
        this.projectSelect = projectSelect;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public static final Finder<Long, Employee> find = new Finder<>(Employee.class);

    public static final List<Employee> findAll() {
        return Employee.find.all();
    }

    public static Employee authenticate(String email, String password) {
        return find.query().where().eq("email", email).eq("password", password).findUnique();
    }

    public String getRole(){
        return this.role;
    }

    public static Employee getEmployeeById(String id){
        if (id ==null) {
            return null;
        } else {
            return find.query().where().eq("email", id).findUnique();
        }
    }

    public boolean isAssignedTo(Project project){
        boolean isAssigned = false;
        for (Project p : this.projects){
            if(p.getId() == project.getId()){
                isAssigned = true;
            }
        }
        return isAssigned;
    }
}
