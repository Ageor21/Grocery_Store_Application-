package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
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

public class AddPartController {

    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameAddPartText;

    @FXML
    private TextField InventoryAddPartText;

    @FXML
    private TextField PriceCostAddPartText;

    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField MaxAddPartText;

    @FXML
    private TextField MinAddPartText;

    @FXML
    private TextField MachineIDAddPartText;
    
    @FXML
    private TextField CompanyNameAddPartText;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    private boolean isOutsourced = true;

    @FXML
    private Label MACIDLabel;

    @FXML
    public void IDAddPartText(ActionEvent event) {

       
    }

    /**
     *Sets the text for when inHouse is selected.
     * @param event
     */
    @FXML
    public void InhouseHandler(ActionEvent event) {

        isOutsourced = false;
        MACIDLabel.setText("Machine ID");
        
    }

    @FXML
    public void InventoryAddPartText(ActionEvent event) {

    }

    @FXML
    public void MachineIDAddPartText(ActionEvent event) {

    }

    @FXML
    public void MaxAddPartText(ActionEvent event) {

    }

    @FXML
    public void MinAddPartText(ActionEvent event) {

    }

    @FXML
    public void NameAddPartText(ActionEvent event) {

    }

    /**
     *
     * Sets the text for when OutSourced is selected
     * @param event
     */
    @FXML
    public void OutsourcedHandler(ActionEvent event) {
        
        isOutsourced = true;
        MACIDLabel.setText("Company Name");
    }

    @FXML
    public void PriceCostAddPartText(ActionEvent event) {

    }

    /**
     * Cancels the addition of a part
     * @param event
     * @throws IOException
     */
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
     * Saves the new part.
     * <p><b>I had a problem with finding out how to throw errors, and I solved it through reading notes in my class material and used a chain of if then statements that end with saving the product.</b></p>
     * @param event
     * @throws IOException
     */
    @FXML
    public void saveHandler(ActionEvent event) throws IOException {
            try{
                int ID = 0;
                for(Part part : Inventory.getAllParts()) {

                    if(part.getPartID() > ID)

                        ID = part.getPartID();

                }

                IDAddPartText.setText(String.valueOf(++ID));
                String name = NameAddPartText.getText();
                int inventory = Integer.parseInt(InventoryAddPartText.getText());
                double priceCost = Double.parseDouble(PriceCostAddPartText.getText());
                int max = Integer.parseInt(MaxAddPartText.getText());
                int min = Integer.parseInt(MinAddPartText.getText());

                if(!(InhouseRadioButton.isSelected() || OutsourcedRadioButton.isSelected())){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select InHouse or Outsourced");
                    alert.showAndWait();
                }
                if(min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value.");
                    alert.showAndWait();
            }
                else if (inventory > max || inventory < min) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
                    alert.showAndWait();
                }
                
                else {
                
                if (InhouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(MachineIDAddPartText.getText());
                    InHouse addPart = new InHouse(ID, name, priceCost, inventory, min, max, machineID);
                
                    Inventory.addPart(addPart);

                    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                    stage.setScene(new Scene((Parent) scene));
                    stage.show();
            }
                if (OutsourcedRadioButton.isSelected()) {
                    String companyName = MachineIDAddPartText.getText();
                    Outsourced addPart = new Outsourced(ID, name, priceCost, inventory, min, max, companyName);
                
                    Inventory.addPart(addPart);

                    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                    stage.setScene(new Scene((Parent) scene));
                    stage.show();
            }
                }
 
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
        }    
    }
}


