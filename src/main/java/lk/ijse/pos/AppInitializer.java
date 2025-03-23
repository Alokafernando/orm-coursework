package lk.ijse.pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import java.awt.*;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/OptionSelector.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("serenity mental health theropy center");

//        Image image = new Image(getClass().getResourceAsStream("/image/car-logo-illustration_910054-55266.jpg"));
//        stage.getIcons().add(image);

        stage.show();
    }
}