package controllers;

import models.Task;

import services.TaskPersistenceService;

import views.html.index;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Application extends Controller {

    @Inject
    private TaskPersistenceService taskPersist;

    public Result index() {
        return ok(index.render("hello, world", Form.form(Task.class)));
    }

    @Transactional
    public Result addTask() {
        Form<Task> form = Form.form(Task.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(index.render("hello, world", form));
        }

        Task task = form.get();

        taskPersist.saveTask(task);
        return redirect(routes.Application.index());
    }

    @Transactional
    public Result getTasks() {
        List<Task> tasks = taskPersist.fetchAllTasks();
        return ok(play.libs.Json.toJson(tasks));
    }
}
