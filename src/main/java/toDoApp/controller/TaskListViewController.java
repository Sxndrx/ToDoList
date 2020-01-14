package toDoApp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import toDoApp.model.task.Task;

public class TaskListViewController {
    @FXML
    private ImageView addTaskBtn;
    @FXML
    private ListView<Task> taskListView;

    ObservableList<Task> taskObservableList;

    public TaskListViewController() {
        taskObservableList = FXCollections.emptyObservableList();
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
