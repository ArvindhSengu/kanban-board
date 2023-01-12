import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class kanbanScreenTwo {

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
    private Button newCategoryBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TitledPane task;

    @FXML
    private TableColumn<?, ?> toDo;

    @FXML
    private ColorPicker urgency;

}