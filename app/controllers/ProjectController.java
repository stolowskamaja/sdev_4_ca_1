package controllers;

import controllers.routes;
import models.*;
import play.data.*;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.db.ebean.*;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProjectController extends Controller {

    private FormFactory formFactory;

    @Inject
    public ProjectController(FormFactory f) {
        this.formFactory = f;
    }


    @Security.Authenticated(Secured.class)
    public Result projects(){
        List<Project> allProjects = Project.findAll();

        return ok(projects.render(Employee.getEmployeeById(session().get("email")), allProjects));


    }

    @Security.Authenticated(Secured.class)
    public Result addNewProject(){

        Form<Project> projectForm = formFactory.form(Project.class);

        return ok(addNewProject.render(projectForm, Employee.getEmployeeById(session().get("email"))));


    }

    @With(AuthManager.class)
    @Transactional
    @Security.Authenticated(Secured.class)
    public Result addProjectSubmit(){

        Form<Project> projectForm = formFactory.form(Project.class).bindFromRequest();
        Project newProject;

        if (projectForm.hasErrors()) {
            return badRequest(addNewProject.render(projectForm, Employee.getEmployeeById(session().get("email"))));

        } else {
            newProject = projectForm.get();
            newProject.save();
        }

        return redirect(routes.ProjectController.projects());

    }



    @With(AuthManager.class)
    @Security.Authenticated(Secured.class)
    @Transactional
    public Result deleteProject(Long id){
        Project project = Project.find.ref(id);

        project.delete();

        flash("success", "Project has been deleted.");

        return redirect(controllers.routes.ProjectController.projects());


    }



}
