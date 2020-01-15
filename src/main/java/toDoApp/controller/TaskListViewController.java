package toDoApp.controller;

import com.jfoenix.controls.JFXListView;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import toDoApp.model.task.Task;
import toDoApp.model.task.TaskRepo;

import java.io.IOException;
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
        setEventHandlers();
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

    private void setEventHandlers(){
        addTaskBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> openAddTaskWindow());
    }

    private void openAddTaskWindow(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskForm.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("TO DO LIST");
        stage.show();
    }
}
