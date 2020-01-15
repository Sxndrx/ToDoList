package toDoApp.controller;

import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import toDoApp.exceptions.EmptyTitleException;
import toDoApp.model.task.Task;


public class TaskFormController {
    @FXML
    private Label taskName;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXToggleButton priority;
    @FXML
    private JFXToggleButton notify;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton editBtn;

    private Task selectedTask;
    private boolean addNew = true;
    private ObservableList<Task> tasks;

    @FXML
    void initialize(){
        setEventHandlers();
    }

    private void setEventHandlers() {
        saveBtn.setOnMouseClicked(event -> {
            if(!addNew){
                save();
            }else{
                update();
            }
        });
        editBtn.setOnMouseClicked(event -> setDisabled(false));
    }

    private void setDisabled(boolean disabled) {

    }

    private void update() {
    }

    private void save() {

    }

    private void validateInput() throws EmptyTitleException {
        if (title.getText() == null) {
            throw new EmptyTitleException();
        }
    }

}
