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

    private static String absolutePath = "";

    /**
     * Verifies if a file is on the users computer
     * @param tField The text field
     * @return The absolute path of the file
     */
    protected String verifyFile(TextField tField) {
        boolean fileFound = false;
        int count = 0;
        while (true) {
          if (fileFound) {
            System.out.println("FILE FOUND! "); // Alan code goes here
            break;
          } 
          else {
            if(count>0){
              System.out.println();
              System.out.println("Invalid file!");
            }
            count++;
            String fileName = "";
            fileName = tField.getText();
            String pathName = System.getProperty("user.dir");
            fileFound = searchDir(pathName, fileName);
          }
        }
        return absolutePath;
      }
     
      /**
       * Recursively searches directories for a file
       * @param pathName Name of the file path
       * @param fileName Name of the file
       * @return Boolean based on if the file is found
       */
    protected boolean searchDir(String pathName, String fileName) {
        File path = new File(pathName);
        for (File file : path.listFiles()) {
          if (file.isDirectory()) {
            if (searchDir(file.getAbsolutePath(), fileName)) {
              return true;
            }
            } else {
            if (file.getName().equals(fileName)) {
            absolutePath = file.getAbsolutePath();
              return true;
            }
          }
        }
        return false;
    }

    @FXML
    void importFile(ActionEvent event) {
        verifyFile(fileField);
        // Alan method
    }

}
