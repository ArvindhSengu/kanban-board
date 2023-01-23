import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

public class KanbanBoardController implements Initializable{
    
    @FXML
    private Button addTaskBtn;
    
    @FXML
    private Button renameTaskBtn;

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
    
    TitledPane newTask = new TitledPane();
    List<String> list = new ArrayList<String>();
    ChoiceBox<String> urgencyChoice = new ChoiceBox<String>();
    static TaskHandler th;
    
    public void passFilePath(String filePath){
        th = new TaskHandler(filePath);
    }

    public void importFilePath(){
        th.readFile();
    }
    public void loadFile(){
        ArrayList<TaskDetails> tasks = new ArrayList<TaskDetails>();
        tasks = th.getTaskDetails(); 
        for(TaskDetails td : tasks){
            System.out.println(td.toString());
        }
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getCat().equals("to do")){
                System.out.println(tasks.get(i).getCat());
                //addTask(toDoAccordion,tasks.get(i).getName(), tasks.get(i).getDescr(), tasks.get(i).getUrg(), tasks.get(i).getCat());
            }
            else if(tasks.get(i).getCat().equals("in progress")){
                System.out.println(tasks.get(i).getCat());
                //addTask(inProgAccordion, tasks.get(i).getName(), tasks.get(i).getDescr(), tasks.get(i).getUrg(), tasks.get(i).getCat());
            }
            else if(tasks.get(i).getCat().equals("complete")){
                System.out.println(tasks.get(i).getCat());
                //addTask(completeAccordion,tasks.get(i).getName(), tasks.get(i).getDescr(), tasks.get(i).getUrg(), tasks.get(i).getCat());
            }
            
        }
    }
    
    @FXML
    void btnAddTaskClicked(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("New Task");
        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name: ");
        TextField taskCategoryField = new TextField();
        taskCategoryField.setPromptText("Task Category: ");

        gridPane.add(taskNameField, 0, 0);
        //gridPane.add(new Label("To:"), 1, 0);
        gridPane.add(taskCategoryField, 2, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
        int i = th.searchTask(taskNameField.getText());
        boolean j = verifyCat(taskCategoryField.getText());
        if(taskNameField != null && taskNameField.getText().toString().length() != 0 && i == -1 && j == true){
            if(cleanString(taskCategoryField.getText()).equals("todo")){
                addTask(toDoAccordion, taskNameField.getText(), "", "LOW", "to do");
            }
            else if(cleanString(taskCategoryField.getText()).equals("inprogress")){
                addTask(inProgAccordion, taskNameField.getText(), "", "LOW", "in progress");
            }
            else{
                addTask(completeAccordion, taskNameField.getText(), "", "LOW", "complete");
            }
        }
    }
    @FXML
    void btnDeleteClicked(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Delete Task");
        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name: ");
        TextField taskCategoryField = new TextField();
        taskCategoryField.setPromptText("Task Category: ");

        gridPane.add(taskNameField, 0, 0);
        gridPane.add(taskCategoryField, 2, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
        int i = th.searchTask(taskNameField.getText());
        boolean j = verifyCat(taskCategoryField.getText());
        if(taskNameField != null && taskNameField.getText().toString().length() != 0 && i != -1 && j == true){
            if(cleanString(taskCategoryField.getText()).equals("todo")){
                delTask(toDoAccordion, taskNameField.getText(), "to do");
                th.deleteTask(taskNameField.getText());
            }
            else if(cleanString(taskCategoryField.getText()).equals("inprogress")){
                delTask(inProgAccordion, taskNameField.getText(), "in progress");
                th.deleteTask(taskNameField.getText());
            }
            else if(cleanString(taskCategoryField.getText()).equals("complete")){
                delTask(completeAccordion, taskNameField.getText(), "complete");
                th.deleteTask(taskNameField.getText());
        }
    }
}
    @FXML
    void renameBtnClicked(ActionEvent event){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Rename Task");
        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name: ");
        TextField taskCategoryField = new TextField();
        taskCategoryField.setPromptText("Task Category: ");
        TextField newTaskNameField = new TextField();
        newTaskNameField.setPromptText("New Task Name: ");

        gridPane.add(taskNameField, 0, 0);
        gridPane.add(taskCategoryField, 1, 0);
        gridPane.add(newTaskNameField, 2, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
        int i = th.searchTask(taskNameField.getText());
        boolean j = verifyCat(taskCategoryField.getText());
        if(taskNameField != null && taskNameField.getText().toString().length() != 0 && newTaskNameField!= null & newTaskNameField.getText().toString().length() != 0  && j == true){
            if(cleanString(taskCategoryField.getText()).equals("todo")){
                renameTask(toDoAccordion, taskNameField.getText(), newTaskNameField.getText());
                th.updateTaskName(taskNameField.getText(), newTaskNameField.getText());
            }
            else if(cleanString(taskCategoryField.getText()).equals("inprogress")){
                renameTask(inProgAccordion, taskNameField.getText(), newTaskNameField.getText());
                th.updateTaskName(taskNameField.getText(), newTaskNameField.getText());
            }
            else if(cleanString(taskCategoryField.getText()).equals("complete")){
                renameTask(completeAccordion, taskNameField.getText(), newTaskNameField.getText());
                th.updateTaskName(taskNameField.getText(), newTaskNameField.getText());
            }
        }

    }

    public void renameTask(Accordion category, String name, String newName){
        category.getPanes().get(th.searchTask(name) + 1).setText(newName);
        th.updateTaskName(name, newName);
    }

    public void delTask(Accordion category, String name, String categoryName){
        category.getPanes().remove(th.searchTask(name) + 1);
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


    public void addTask(Accordion category, String name, String desc, String urgency, String categoryName){    
        th.addTask(new TaskDetails(name, "", urgency, categoryName));
        th.refreshFile();
        TitledPane newTask = new TitledPane();
        AnchorPane taskback = new AnchorPane();
        TextArea taskText = new TextArea();
        ChoiceBox<String> urgencyChoice = new ChoiceBox<String>();
        taskText.setText(desc);
        urgencyChoice.setValue(urgency);
        ObservableList<String> list = urgencyChoice.getItems();
        list.add("HIGH");
        list.add("MID");
        list.add("LOW");
        taskback.setPrefSize(300,277);

        Button leftBtn = new Button();
        Button rightBtn = new Button();

        leftBtn.setLayoutX(170);
        leftBtn.setLayoutY(0);
        leftBtn.setText("Left");
        leftBtn.setPrefWidth(50);
        rightBtn.setLayoutX(220);
        rightBtn.setLayoutY(0);
        rightBtn.setText("Right");
        rightBtn.setPrefWidth(50);

        urgencyChoice.setPrefSize(160,30);

        taskText.setPrefSize(250,180);
        taskText.setLayoutX(8);
        taskText.setLayoutY(42);
        taskText.setWrapText(true);
        // Find a way to fix off-center textArea
        
        newTask.setPrefSize(300,263);
        newTask.setMaxSize(300, 263);
        taskback.getChildren().addAll(urgencyChoice,taskText, leftBtn, rightBtn);
        newTask.setContent(taskback);
        urgencyChoice.setOnAction(e ->{
            String myColor = urgencyChoice.getValue();

            if(myColor.equals("HIGH")){
                newTask.setTextFill(Color.RED);
                th.updateUrg(name, "HIGH");
            }
            else if(myColor.equals("MID")){
                newTask.setTextFill(Color.YELLOW);
                th.updateUrg(name, "MID");
            }
            else if(myColor.equals("LOW")){
                newTask.setTextFill(Color.GREEN);
                th.updateUrg(name, "LOW");
            }
            //th.sortTasks();
            //category.getPanes().remove(newTask);
            //category.getPanes().add(th.searchTask(name) + 1, newTask);
        });

        newTask.setText(name);
        if(urgency.equals("HIGH")){
            urgencyChoice.setValue("HIGH");
            newTask.setTextFill(Color.RED);
        }
        else if(urgency.equals("MID")){
            newTask.setTextFill(Color.YELLOW);
        }
        else if(urgency.equals("LOW")){
            newTask.setTextFill(Color.GREEN);
        }
        category.getPanes().add(newTask);

        leftBtn.setOnAction(e ->{
            if(category.equals(inProgAccordion)){
                addTask(toDoAccordion, name, desc, urgencyChoice.getValue(), "to do");
                delTask(toDoAccordion, name, categoryName);
                th.leftArrow(name);
            }
            else if(category.equals(completeAccordion)){
                addTask(inProgAccordion, name, desc, urgencyChoice.getValue(), "in progress");
                delTask(completeAccordion, name, categoryName);
                th.leftArrow(name);
            }
        }); 
        
        rightBtn.setOnAction(e ->{
            if(category.equals(toDoAccordion)){
                addTask(inProgAccordion, name, desc, urgencyChoice.getValue(), "in progress");
                delTask(inProgAccordion, name, categoryName);
                th.rightArrow(name);
            }
            else if(category.equals(inProgAccordion)){
                addTask(completeAccordion, name, desc, urgencyChoice.getValue(), "complete");
                delTask(completeAccordion, name, categoryName);
                th.rightArrow(name);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fileChooser.setInitialDirectory(new File("C:"));
    }
    public boolean verifyCat(String text){
        if(text.replace(" ", "").toLowerCase().equals("todo") || text.replace(" ", "").toLowerCase().equals("inprogress") || text.replace(" ", "").toLowerCase().equals("complete")){
            return true;
        }
        return false;
    }
    public String cleanString(String text){
        return text.replace(" ", "").toLowerCase();
    }
}


