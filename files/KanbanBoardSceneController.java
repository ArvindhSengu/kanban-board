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

public class KanbanBoardSceneController {

    @FXML
    private Button addTaskBtn;

    @FXML
    private Button archiveListBtn;

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
    private Button newCategoryBtn;

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

    }

    /*
     * Delete category (ex: to do list)
     * @param
     */
    @FXML
    void btnArchiveListClicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {

    }

    @FXML
    void btnLeftClicked(ActionEvent event) {

    }

    @FXML
    void btnNewCatClicked(ActionEvent event) {

    }

    @FXML
    void btnRightClicked(ActionEvent event) {

    }

    /* Save progress and write kanban board info onto a txt file
     * @param
     */
    @FXML
    void btnSaveClicked(ActionEvent event) {

    }

    @FXML
    void urgencyCat(ActionEvent event) {

    }

}
