package models;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax. persistence.*;
import javax.validation.Constraint;

import io.ebean.*;
import io.ebean.annotation.Formula;
import play.data.format.Formats;
import play.data.validation.*;

import io.ebean.Model;

@Entity
public class Project extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String description;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date deadline;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    public Project() {
    }

    public Project(Long id, String name, String description, ZonedDateTime createdAt, Date deadline, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.employees = employees;
    }

    public String getCreatedAtTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
        return formatter.format(createdAt.toLocalTime());
    }

    public LocalDate getCreatedAtDate(){
        return createdAt.toLocalDate();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public static final Finder<Long, Project> find = new Finder<>(Project.class);

    public static final List<Project> findAll() {
        return Project.find.all();
    }


}
