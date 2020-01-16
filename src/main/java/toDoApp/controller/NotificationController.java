package toDoApp.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import toDoApp.model.models.Task;
import toDoApp.model.repo.TaskRepo;

public class NotificationController {
    @FXML
    private JFXButton okBtn;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private Label info;

    private Task task;
    @FXML
    void initialize(){
        setEventHandlers();
    }

    private void setEventHandlers() {
        okBtn.setOnMouseClicked(event -> close());
        closeBtn.setOnMouseClicked(event -> close());
    }

    private void confirmNotification() {
        task.setNotify(false);
        TaskRepo.updateTask(task);
    }

    private void close() {
       Stage stage = (Stage) okBtn.getScene().getWindow();
       stage.close();
    }

    public void setTask(Task task){
        this.task = task;
        confirmNotification();
        setGraphic();
    }

    private void setGraphic(){
        info.setWrapText(true);
        info.setText("Upcoming task: " +
                task.getTitle() +
                "\nfrom project: " +
                task.getProject().getTitle());
    }
}
