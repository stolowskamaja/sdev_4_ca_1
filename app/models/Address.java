package models;


import java.util.*;
import javax. persistence.*;
import javax.validation.Constraint;

import io.ebean.*;
import io.ebean.annotation.Formula;
import play.data.validation.*;

import io.ebean.Model;

@Entity
public class Address extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private String address;

    @OneToOne()
    private Employee employee;

    public Address(Long id, @Constraints.Required String address, Employee employee) {
        this.id = id;
        this.address = address;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static final Finder<Long, Address> find = new Finder<>(Address.class);

    public static final List<Address> findAll() {
        return Address.find.all();
    }
}
