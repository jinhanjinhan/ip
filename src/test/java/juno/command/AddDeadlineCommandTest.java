package juno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Deadline;
import juno.task.Task;

public class AddDeadlineCommandTest {

    private TaskManager mockTaskManager;
    private FileManager mockFileManager;
    private ArrayList<Task> mockTaskList;

    @BeforeEach
    void startTestEnv() {
        this.mockTaskManager = mock(TaskManager.class);
        this.mockFileManager = mock(FileManager.class);

        this.mockTaskList = new ArrayList<>();
        when(this.mockTaskManager.getTasksArray()).thenReturn(this.mockTaskList);
    }

    @Test
    public void runCommand_validAddDeadlineCommand_success() throws TaskManagerException {

        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(
                "add deadline Finish homework /2024 10 11 11.59PM", this.mockTaskManager, this.mockFileManager);

        addDeadlineCommand.runCommand();

        assertEquals(1, this.mockTaskList.size());
        Task t = this.mockTaskList.get(0);
        assertInstanceOf(Deadline.class, t);
        assertEquals("Finish homework ", t.getDescription());

        verify(this.mockFileManager).writeTasksToFile(this.mockTaskList);
    }
}
