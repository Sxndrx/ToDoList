package toDoApp.notifier;

import javafx.application.Platform;
import toDoApp.model.models.Task;
import toDoApp.model.repo.TaskRepo;

import java.util.List;

public class NotifierOverdue extends Notifier {

    @Override
    public void run() {
        List<Task> tasks = TaskRepo.getTaskListForNotification();
        for (Task task : tasks) {
            Platform.runLater(() -> showPopUp(task));
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
