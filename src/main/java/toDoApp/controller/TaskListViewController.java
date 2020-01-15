package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import toDoApp.model.task.Task;
import toDoApp.model.task.TaskRepo;

import java.util.List;

public class TaskListViewController {
    @FXML
    private ImageView addTaskBtn;
    @FXML
    private JFXListView<Task> taskListView;

    ObservableList<Task> taskObservableList;


    private StringProperty projectId;

    public TaskListViewController() {
        taskObservableList = FXCollections.observableArrayList();
    }

    public void setTaskObservableList(ObservableList<Task> taskObservableList) {
        this.taskObservableList = taskObservableList;
    }

    @FXML
    void initialize(){
        taskListView.setItems(taskObservableList);
        taskListView.setCellFactory(taskList -> new TaskListViewCell());
    }

    public void setProjectTaskList(String projectId){
        List<Task> list = TaskRepo.getTasksFromProject(projectId);
        if(list.isEmpty()){
            taskObservableList.clear();
        }
        else {
            taskObservableList.clear();
            taskObservableList.addAll(list);
        }
    }


}
