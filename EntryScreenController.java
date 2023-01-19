import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.File;

public class EntryScreenController {

    @FXML
    private TextField fileField;

    @FXML
    private Button importBtn;

    @FXML
    private TextField nameField;

    @FXML
    private Button newBtn;

    @FXML
    void importFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Kanban Board");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        try{
            File selectedFile = fileChooser.showSaveDialog(stage);
            filePath = selectedFile.getAbsolutePath;
        }
        catch(Exception ex){
        }
    }

}
