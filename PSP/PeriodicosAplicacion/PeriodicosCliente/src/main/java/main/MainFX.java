package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;


public class MainFX  {
    @Inject
    FXMLLoader fxmlLoader;

    public void start(@Observes @StartupScene Stage primaryStage) {
        try {
            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream("/fxml/inicio.fxml"));
            primaryStage.setScene(new Scene(fxmlParent, 300, 100));
            primaryStage.setTitle("IES Quevedo");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
