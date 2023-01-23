import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application implements Initializable{
    public static void main(String[] args) {
        launch(args);
    }

    public void logout(Stage primaryStage){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exiting Kanban Client");
        alert.setHeaderText("You are about to exit the client!");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK){  
            btnSaveClicked();
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
    
    private TextField description;

    FileChooser fileChooser = new FileChooser();
    
    void getDescription(MouseEvent event) throws FileNotFoundException{
        File file = fileChooser.showOpenDialog(new Stage());

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            description.appendText(scanner.nextLine() + "\n");
        }
        scanner.close();
    }

    @FXML
    void btnSaveClicked(){
        File file = fileChooser.showSaveDialog(new Stage());//start making file to put info in
        if(file != null){
            saveSystem(file, description.getText());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fileChooser.setInitialDirectory(new File("C:"));
    }

    public void saveSystem(File file, String content){
        try{
             PrintWriter printWriter = new PrintWriter(file);//write the file
             printWriter.write(content);
             printWriter.close();
            }catch (FileNotFoundException e ){
                e.printStackTrace();
            }
       

    }
}
