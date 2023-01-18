import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.application.Application;
import java.io.*;
import javafx.event.EventHandler;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.util.Scanner;


public class kanbanScreenTwo implements Initializable{

    @FXML
    private Button addTaskBtn;

    @FXML
    private Label boardTitle;

    @FXML
    private TableColumn<?, ?> complete;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField description;

    @FXML
    private TableView<?> inProgress;

    @FXML
    private AnchorPane kanbanWindow;

    @FXML
    private Button leftBtn;

    @FXML
    private Button righBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TitledPane task;

    @FXML
    private TableColumn<?, ?> toDo;

    @FXML
    private ColorPicker urgency;

    @FXML
    void btnAddTaskClicked(ActionEvent event) {
        //insert code to add new task
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {
        //insert code to delete task
    }

    @FXML
    void btnLeftClicked(ActionEvent event) {
        //insert code to move task to the left
    }

    @FXML
    void btnRightClicked(ActionEvent event) {
        //insert code to move task to the right
    }
    /*
     * Collect task descriptions
     */
    @FXML
    
    FileChooser fileChooser = new FileChooser();
    
    void getDescription(MouseEvent event){
        File file = fileChooser.showOpenDialog(new Stage());
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                description.appendText(scanner.nextLine() + "\n");
            }
        }catch(FileNotFoundException e ){
            e.printStackTrace();
        }
    }

    /* Save progress and write kanban board info onto a txt file
     * @param ActionEvent event to indicate that the button has been clicked 
     */
    @FXML
    void btnSaveClicked(ActionEvent event){
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
    
    @FXML
    void urgencyCat(ActionEvent event) {
        //code to classify urgency of task
    }
}
