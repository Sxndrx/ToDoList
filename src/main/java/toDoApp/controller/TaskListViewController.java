package toDoApp.controller;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import toDoApp.model.task.Task;
import toDoApp.model.task.TaskRepo;

public class TaskListViewController {
    @FXML
    private ImageView addTaskBtn;
    @FXML
    private ListView<Task> taskListView;

    ObservableList<Task> taskObservableList;


    private StringProperty projectId;

    public TaskListViewController() {
        taskObservableList = FXCollections.observableList(TaskRepo.getTasksFromProject("5e1e2734d8895c81e963b59e"));
    }

    public void setTaskObservableList(ObservableList<Task> taskObservableList) {
        this.taskObservableList = taskObservableList;
    }

    @FXML
    void initialize(){
        taskListView.setItems(taskObservableList);
        taskListView.setCellFactory(taskList -> new TaskListViewCell());
    }
}
