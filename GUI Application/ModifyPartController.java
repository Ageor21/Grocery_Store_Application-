package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Inventory;
import Model.Part;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Aaron George
 */

public class ModifyPartController {

    Part selectedPart;
    int selectedIndex;
    
    
    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameInhouseModifyPartText;

    @FXML
    private TextField InventoryInhouseModifyPartText;

    @FXML
    private TextField PriceCostInhouseModifyPartText;

    @FXML
    private TextField MaxInhouseModifyPartText;

    @FXML
    private TextField MinInhouseModifyPartText;

    @FXML
    private TextField MachineIDInhouseModifyPartText;

    @FXML
    private Button CancellButton;

    @FXML
    private Button SaveButton;

    @FXML
    void InhouseHandler(ActionEvent event) {
        MACIDLabel.setText("Machine ID");

    }

    @FXML
    void InventoryInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void MachineIDInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void MaxInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void MinInhouseModifyPartText(ActionEvent event) {

    }
    
    @FXML
    private Label MACIDLabel;

    @FXML
    public void NameInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    public void OutsourcedHandler(ActionEvent event) {

        MACIDLabel.setText("Company Name");
    }

    @FXML
    public void PriceCostInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    public void cancelHandler(ActionEvent event) throws IOException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
          
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
        }

    }
    /**
     * Saves the modified part
     * @param event
     * @throws IOException
     */
    @FXML
    public void saveHandler(ActionEvent event) throws IOException {
       boolean done = false;
       int id = selectedPart.getPartID();
       String name = NameInhouseModifyPartText.getText();
       int inventory = Integer.parseInt(InventoryInhouseModifyPartText.getText());
       double price = Double.parseDouble(PriceCostInhouseModifyPartText.getText());
       int max = Integer.parseInt(MaxInhouseModifyPartText.getText());
       int min = Integer.parseInt(MinInhouseModifyPartText.getText());
        if (!(InhouseRadioButton.isSelected() || OutsourcedRadioButton.isSelected())) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("You must select either the InHouse button or Outsourced button.");
            newAlert.showAndWait();
            done = true;
        }
        if (name.trim().isEmpty() || name.trim().toLowerCase().equals("part name")) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("Part Name must be entered");
            newAlert.showAndWait();
            return;
        }
        if (min > max) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("Part Min must be less than Part Max");
            newAlert.showAndWait();
            return;
        }
        if (inventory < min) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("Part Inventory Level must be between the Part's Min and Max values.");
            newAlert.showAndWait();
            return;
        }
        if (inventory > max) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("Part Inventory Level must be between the Part's Min and Max values.");
            newAlert.showAndWait();
            return;
        }

        if (done) {
            return;
        } else if (OutsourcedRadioButton.isSelected() && MachineIDInhouseModifyPartText.getText().trim().isEmpty()) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("Part Company Name must be entered.");
            newAlert.showAndWait();
            return;
        } else if (InhouseRadioButton.isSelected() && Integer.parseInt(MachineIDInhouseModifyPartText.getText()) < 0) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR: INVALID FORMAT");
            newAlert.setHeaderText("Error");
            newAlert.setContentText("Part Machine ID must be greater than 0.");
            newAlert.showAndWait();
            return;

        }
       if (InhouseRadioButton.isSelected()) {
           
           int machineID = Integer.parseInt(MachineIDInhouseModifyPartText.getText());
           
           InHouse inhousePart = new InHouse(id, name, price, inventory, min, max, machineID);
           Inventory.getAllParts().set(selectedIndex, inhousePart);
       }
       
       if (OutsourcedRadioButton.isSelected()) {
           
           String companyName = MachineIDInhouseModifyPartText.getText();
          
           Outsourced outsourcedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
           Inventory.getAllParts().set(selectedIndex, outsourcedPart);
       }
        
       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
       stage.setScene(new Scene((Parent) scene));
       stage.show();
       

    }
    /**
     * Uploads the selected part.
     * @param part
     * @param index
     */
    public void setPart(Part part, int index) {
        selectedPart = part;
        selectedIndex = index;
        
         if (part instanceof InHouse) {

            InHouse newPart = (InHouse) part;
            InhouseRadioButton.setSelected(true);
            MACIDLabel.setText("Machine ID");
            this.NameInhouseModifyPartText.setText(newPart.getName());
            this.InventoryInhouseModifyPartText.setText((Integer.toString(newPart.getInStock())));
            this.PriceCostInhouseModifyPartText.setText((Double.toString(newPart.getPrice())));
            this.MinInhouseModifyPartText.setText((Integer.toString(newPart.getMin())));
            this.MaxInhouseModifyPartText.setText((Integer.toString(newPart.getMax())));
            this.MachineIDInhouseModifyPartText.setText((Integer.toString(newPart.getMachineID())));

        }

        if (part instanceof Outsourced) {

            Outsourced newPart = (Outsourced) part;
            OutsourcedRadioButton.setSelected(true);
            MACIDLabel.setText("Company Name");
            this.NameInhouseModifyPartText.setText(newPart.getName());
            this.InventoryInhouseModifyPartText.setText((Integer.toString(newPart.getInStock())));
            this.PriceCostInhouseModifyPartText.setText((Double.toString(newPart.getPrice())));
            this.MinInhouseModifyPartText.setText((Integer.toString(newPart.getMin())));
            this.MaxInhouseModifyPartText.setText((Integer.toString(newPart.getMax())));
            this.MachineIDInhouseModifyPartText.setText(newPart.getCompanyName());
        }  
    }
}
