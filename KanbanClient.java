import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KanbanClient extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * Sets the stage of the GUI
     */
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EntryScreen.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Kanban Client");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}