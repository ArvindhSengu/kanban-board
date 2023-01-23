import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class KanbanBoardController implements Initializable{
    
    @FXML
    private Button addTaskBtn;

    @FXML
    private Label boardTitle;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Accordion completeAccordion;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField description;

    @FXML
    private Accordion inProgAccordion;

    @FXML
    private AnchorPane kanbanWindow;

    @FXML
    private Button leftBtn;

    @FXML
    private Button rightBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TitledPane formatPane;

    @FXML
    private Accordion toDoAccordion;

    @FXML
    private ColorPicker urgency;
    
    //static String filePath1;
    TitledPane newTask = new TitledPane();
    List<String> list = new ArrayList<String>();
    ChoiceBox<String> urgencyChoice = new ChoiceBox<String>();
    static TaskHandler th;
    
    public void passFilePath(String filePath){
        th = new TaskHandler(filePath);
    }
    
    @FXML
    void btnAddTaskClicked(ActionEvent event) {
        addTask(toDoAccordion);
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {
        //th.deleteTask(null);
    }

    @FXML
    void btnLeftClicked(ActionEvent event) {
        
    }

    @FXML
    void btnRightClicked(ActionEvent event) {

    }

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

    /**
     * Save progress and write kanban board info onto a txt file
     * @param event To indicate that the button has been clicked
     */
    @FXML
    void btnSaveClicked(ActionEvent event) {
        File file = fileChooser.showSaveDialog(new Stage());//start making file to put info in
        if(file != null){
            saveSystem(file, description.getText());
        }
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


    public void addTask(Accordion accordion){    
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("New Task");
        textInput.getDialogPane().setContentText("Task name: ");
        Optional<String> result = textInput.showAndWait();
        TextField input = textInput.getEditor();
        if(input.getText() != null && input.getText().toString().length() != 0){
            TitledPane newTask = new TitledPane();
            AnchorPane taskback = new AnchorPane();
            TextArea taskText = new TextArea();
            ChoiceBox<String> urgencyChoice = new ChoiceBox<String>();
            urgencyChoice.setValue("Urgency Select");
            ObservableList<String> list = urgencyChoice.getItems();
            list.add("HIGH");
            list.add("MID");
            list.add("LOW");
            
            taskback.setPrefSize(300,277);

            urgencyChoice.setPrefSize(160,30);

            taskText.setPrefSize(250,180);
            taskText.setLayoutX(8);
            taskText.setLayoutY(42);
            taskText.setWrapText(true);
            // Find a way to fix off-center textArea
            
            newTask.setPrefSize(300,263);
                
            taskback.getChildren().addAll(urgencyChoice,taskText);
            newTask.setContent(taskback);

            urgencyChoice.setOnAction(e ->{
                String myColor = urgencyChoice.getValue();

                if(myColor.equals("HIGH")){
                    newTask.setTextFill(Color.RED);
                }
                else if(myColor.equals("MID")){
                    newTask.setTextFill(Color.YELLOW);
                }
                else if(myColor.equals("LOW")){
                    newTask.setTextFill(Color.GREEN);
                }
            });

            newTask.setText(input.getText());
            newTask.setTextFill(Color.GREEN);
            accordion.getPanes().add(newTask);
    }
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fileChooser.setInitialDirectory(new File("C:"));
    }
}

