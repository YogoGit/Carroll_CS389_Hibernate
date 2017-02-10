package controllers;

import models.Task;

import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("hello, world", Form.form(Task.class)));
    }

    @Transactional
    public static Result addTask() {
        Form<Task> form = Form.form(Task.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(index.render("hello, world", form));
        }

        Task task = form.get();
        JPA.em().persist(task);
        return redirect(routes.Application.index());
    }

    @Transactional
    public static Result getTasks() {
        List<Task> tasks = JPA.em().createQuery("from Task", Task.class).getResultList();
        return ok(play.libs.Json.toJson(tasks));
    }
}
