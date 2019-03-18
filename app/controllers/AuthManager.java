package controllers;

import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

import models.*;

public class AuthManager extends Action.Simple{

    public CompletionStage<Result> call(Http.Context ctx){
        String id = ctx.session().get("email");
        if (id != null) {
            Employee emp = Employee.getEmployeeById(id);
            if (emp.getRole().equals("manager")) {
                return delegate.call(ctx);
            }
        }
        ctx.flash().put("error", "Manager login required");

        return CompletableFuture.completedFuture(redirect(routes.HomeController.index()));
    }
}
