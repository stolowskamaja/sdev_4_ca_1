package models;

import java.util.*;
import javax. persistence.*;
import javax.validation.Constraint;

import io.ebean.*;
import play.data.validation.*;

import io.ebean.Model;

@Entity
@DiscriminatorValue("manager")
public class Manager extends Employee{
    public Manager() {
    }

    public Manager(Long id, @Constraints.Required String name, @Constraints.Required String email, @Constraints.Required String password, Address address, Department department) {
        super(id, name, email, password, address, department);
    }
}
