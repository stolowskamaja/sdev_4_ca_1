package controllers;

import play.mvc.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

import models.*;

public class LoginController extends Controller {
    private FormFactory formFactory;

    @Inject
    public LoginController(FormFactory f) {
        this.formFactory = f;
    }

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(login.render(loginForm, Employee.getEmployeeById(session().get("email"))));
    }

    public Result logout(){
        session().clear();
        flash("success", "You have been logged out");
        return redirect(routes.LoginController.login());
    }

    public Result loginSubmit() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm, Employee.getEmployeeById(session().get("email"))));

        } else {
            session().clear();

            session("email", loginForm.get().getEmail());

            return redirect(controllers.routes.HomeController.index());
        }
    }
}

