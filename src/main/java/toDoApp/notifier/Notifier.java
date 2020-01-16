package toDoApp.notifier;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.types.ObjectId;
import toDoApp.Utils.Utils;
import toDoApp.controller.NotificationController;
import toDoApp.model.models.Task;
import toDoApp.model.repo.TaskRepo;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

public class Notifier implements Runnable {

    private long sleepTime;
    private Task nextTask;
    private boolean checkAgain;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double width = screenSize.getWidth();
    private final double height = screenSize.getHeight();
    private final double stageHeight = 80;
    private final double stageWidth = 350;


    @Override
    public void run() {
        while(true){
            nextTask = TaskRepo.getNextTaskNotification();
            sleepTime = Utils.toDate(nextTask.getNotificationDate()).getTime() - Date.from(Instant.now()).getTime();
            if(sleepTime>30000){
                sleepTime = 30000;
                checkAgain = true;
            }else{
                checkAgain = false;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!checkAgain) {
                Platform.runLater(() -> showPopUp(nextTask));
            }
        }
    }

    void showPopUp(Task task){
        Stage stage = new Stage();
        stage.setHeight(stageHeight);
        stage.setWidth(stageWidth);
        stage.setX(width-stageWidth-10);
        stage.setY(height- stageHeight -10);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NotificationPopUp.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            NotificationController notificationController = fxmlLoader.getController();
            notificationController.setTask(task);
            stage.setScene(new Scene(root));
            stage.show();
            stage.setAlwaysOnTop(true);
            stage.toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
