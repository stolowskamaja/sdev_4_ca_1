package controllers;

import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;


public class HomeController extends Controller {



    public Result index() {
        Employee employee = Employee.getEmployeeById(session().get("email"));
        return ok(index.render(employee));
    }





}
