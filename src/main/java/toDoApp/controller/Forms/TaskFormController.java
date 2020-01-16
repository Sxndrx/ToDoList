package toDoApp.controller.Forms;

import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import toDoApp.Utils.Utils;
import toDoApp.Utils.exceptions.EmptyDateException;
import toDoApp.Utils.exceptions.EmptyTitleException;
import toDoApp.Utils.exceptions.WrongNotifacationException;
import toDoApp.model.models.Project;
import toDoApp.model.models.Task;
import toDoApp.model.repo.TaskRepo;


public class TaskFormController implements IFormController {

    @FXML
    private JFXTimePicker timePicker;
    @FXML
    private JFXDatePicker notDatePicker;
    @FXML
    private JFXTimePicker notTimePicker;
    @FXML
    private Label taskName;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXToggleButton priorityButton;
    @FXML
    private JFXToggleButton notifyButton;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton editBtn;

    private Task selectedTask;
    private Project project;
    private Task parentTask;
    private boolean addNew = true;
    private ObservableList<Task> tasks;

    @FXML
    void initialize() {
        setEventHandlers();
    }

    @Override
    public void setEventHandlers() {
        saveBtn.setOnMouseClicked(event -> save());
        editBtn.setOnMouseClicked(event -> setDisabled(false));
        notifyButton.setOnAction(event -> setDisabledNotificationDate());
    }


    @Override
    public void save() {
        try {
            validateInput();
            if (addNew) {
                addNewObject();
            } else {
                update();
            }
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (EmptyTitleException e) {
            Utils.setWarningOnTitle(title);
        } catch (EmptyDateException e) {
            Utils.setWarningOnDatePicker(datePicker);
            Utils.setWarningOnTimePicker(timePicker);
        } catch (WrongNotifacationException e) {
            Utils.setWarningOnDatePicker(notDatePicker);
            Utils.setWarningOnTimePicker(notTimePicker);
        }

    }

    @Override
    public void validateInput() throws EmptyTitleException, EmptyDateException, WrongNotifacationException {
        Utils.validateTitle(title);
        Utils.validateDate(datePicker);
        Utils.validateTime(timePicker);
        if (notifyButton.isSelected()) {
            Utils.validateNotificationDate(notDatePicker, notTimePicker);
        }
    }

    @Override
    public void addNewObject() {
        Task task = new Task();
        setTask(task);
        tasks.add(task);
        TaskRepo.addTask(task);
    }


    @Override
    public void update() {
        setTask(selectedTask);
        TaskRepo.updateTask(selectedTask);
        tasks.set(tasks.indexOf(selectedTask), selectedTask);
    }

    @Override
    public void showMore() {
        setWindow();
        setDisabled(true);
    }

    @Override
    public void setWindow() {
        editBtn.setVisible(true);
        taskName.setText(selectedTask.getTitle());
        title.setText(selectedTask.getTitle());
        description.setText(selectedTask.getDescription());
        datePicker.setValue(selectedTask.getDate().toLocalDate());
        timePicker.setValue(selectedTask.getDate().toLocalTime());
        notifyButton.setSelected(selectedTask.getNotify());
        if (selectedTask.getNotify()) {
            notDatePicker.setValue(selectedTask.getNotificationDate().toLocalDate());
            notTimePicker.setValue(selectedTask.getNotificationDate().toLocalTime());
        }
        priorityButton.setSelected(selectedTask.getPriority());
        notifyButton.setDisable(selectedTask.getNotify());
    }


    @Override
    public void setDisabled(boolean disabled) {
        title.setDisable(disabled);
        description.setDisable(disabled);
        datePicker.setDisable(disabled);
        timePicker.setDisable(disabled);
        priorityButton.setDisable(disabled);
        notifyButton.setDisable(disabled);
        saveBtn.setDisable(disabled);
        if (!disabled) {
            setDisabledNotificationDate();
        }
    }


    private void setTask(Task task) {
        task.setTitle(title.getText());
        if (description.getText() != null) {
            task.setDescription(description.getText());
        }
        task.setPriority(priorityButton.isSelected());
        task.setNotify(notifyButton.isSelected());
        if (notifyButton.isSelected()) {
            task.setNotificationDate(Utils.toLocalDateTime(notDatePicker.getValue(), notTimePicker.getValue()));
        }
        task.setDate(Utils.toLocalDateTime(datePicker.getValue(), timePicker.getValue()));
        if (addNew) {
            if (parentTask != null) {
                task.setParentTask(parentTask);
            }
            task.setProject(project);
        }

    }

    private void setDisabledNotificationDate() {
        notDatePicker.setDisable(!notifyButton.isSelected());
        notTimePicker.setDisable(!notifyButton.isSelected());
    }

    public void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public void setAddNewFormMode(boolean addNew) {
        this.addNew = addNew;
        if (!addNew) {
            showMore();
        }
    }
}
