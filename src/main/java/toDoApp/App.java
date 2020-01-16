package toDoApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import toDoApp.controller.NotificationController;
import toDoApp.notifier.Notifier;
import toDoApp.notifier.NotifierOverdue;

public class App extends Application {


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TO DO LIST");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        Thread notifierThread = new Thread(new Notifier());
        notifierThread.setDaemon(true);
        notifierThread.start();
        Thread overdueThread = new Thread(new NotifierOverdue());
        overdueThread.setDaemon(true);
        overdueThread.start();
    }
}
