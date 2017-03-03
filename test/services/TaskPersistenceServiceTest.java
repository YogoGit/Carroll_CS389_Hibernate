package services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import configs.AppConfig;
import configs.TestDataConfig;

import jpa.Task;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

import javax.inject.Inject;

@ContextConfiguration(classes = {AppConfig.class, TestDataConfig.class})
public class TaskPersistenceServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Inject
    private TaskPersistenceService taskPersist;

    @Test
    public void emptyListTest() {
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should be empty", list.isEmpty());
    }

    @Test
    public void saveValidTaskTest() {
        assertTrue("List should be empty", taskPersist.fetchAllTasks().isEmpty());

        final Task t = new Task();
        t.setContents("contents");
        taskPersist.saveTask(t);
        final List<Task> list = taskPersist.fetchAllTasks();
        assertTrue("List should have one element", list.size() == 1);
    }
}
