package controllers;

import models.Task;

import services.TaskPersistenceService;
import services.TaskPersistenceServiceImpl;

import views.html.index;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class Application extends Controller {

    private static final TaskPersistenceService taskPersist = new TaskPersistenceServiceImpl();

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

        taskPersist.saveTask(task);
        return redirect(routes.Application.index());
    }

    @Transactional
    public static Result getTasks() {
        List<Task> tasks = taskPersist.fetchAllTasks();
        return ok(play.libs.Json.toJson(tasks));
    }
}
