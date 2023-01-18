import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void logout(Stage primaryStage){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exiting Kanban Client");
        alert.setHeaderText("You are about to exit the client!");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK){
            primaryStage.close();
        }
    }
    
    @Override
    /**
     * Sets the stage of the GUI
     */
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EntryScene.fxml"));
        primaryStage.setTitle("Kanban Client");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }
}
