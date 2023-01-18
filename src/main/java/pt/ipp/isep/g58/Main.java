package pt.ipp.isep.g58;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage window) throws IOException {
        MenuController.setWindow(window);
        Parent root = FXMLLoader.load(Main.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(root, 320, 240);
        window.setTitle("Group 58");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) throws Exception {
        //ExampleCreator.createExamples();
        DeSerialize.main();
        AutomaticReviewController.runAutomaticReview();
        launch();
    }
}
