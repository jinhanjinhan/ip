public class AddDeadlineCommand extends AddCommand {

    private final String TASK_TYPE = "deadline";
    public AddDeadlineCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        super(userInput, taskManager, fileManager);
    }

    @Override
    public void runCommand() throws TaskManagerException {
        String taskInfo;
        try {
            taskInfo = userInput.split("\\s+", 3)[2];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                    "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                    "/ {Input task description here}\" to add a task)",
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
        }

        String taskDescription;
        Task t;
        try {
            String[] taskInfoArray = taskInfo.split("/", 2);
            taskDescription = taskInfoArray[0];

            // Check if the task already exists
            if (this.taskManager.checkDuplicateTask(taskDescription)) {
                throw new TaskManagerException("This task is already in your list! " +
                        "Maybe you can try renaming it and input again?",
                        TaskManagerException.ErrorType.DUPLICATE_TASK);
            }
            t = new Deadline(taskDescription, taskInfoArray[1], this.TASK_TYPE);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                    "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                    "/ {Input task description here}\" to add a task)",
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
        }

        this.tasks.add(t);
        this.fileManager.writeTasksToFile(this.tasks);
        System.out.println("\uD83C\uDF89 Got it! I've added: \"" + taskDescription + "\" to your list!");
        System.out.println("\uD83C\uDFAF You now have " + this.tasks.size() + " tasks in the list. Keep going!");
    }
}
