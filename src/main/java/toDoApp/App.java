package toDoApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import toDoApp.Utils.HibernateUtil;

public class App extends Application {


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent welcomePage =  FXMLLoader.load(getClass().getResource("/view/WelcomePage.fxml"));
        stage.setTitle("TO DO LIST");
        stage.setScene(new Scene(welcomePage));
        stage.show();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(root));
    }
}
