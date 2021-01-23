package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Aaron George
 */

public class AddProductController implements Initializable{

    Product newProduct;
    
    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField NameAddPartText;

    @FXML
    private TextField InventoryAddPartText;

    @FXML
    private TextField AddPriceTextField;

    @FXML
    private TextField MaxAddPartText;

    @FXML
    private TextField MinAddPartText;

    @FXML
    private TextField SaveProductTextField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PriceCostPerUnit;

    @FXML
    private Button AddProduct;

    @FXML
    private TableView<Part> associatedPartTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartID;

    @FXML
    private TableColumn<Part, String> associatedPartName;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventory;

    @FXML
    private TableColumn<Part, Double> associatedPartPrice;

    @FXML
    private Button DeleteProduct;

    @FXML
    private Button SaveProduct;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SearchProduct;

    @FXML
    private ObservableList<Part> partsInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        updatePartTable();
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        newProduct = new Product(0, null, 0.0, 0, 0, 0);
        associatedPartTable.setItems(newProduct.getAssociatedParts());
        
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       
        
    }
    
    @FXML
    void AddPriceTextField(ActionEvent event) {

    }
    /**
     * Allows you to add an assoc part.
     * @param event
     * @throws IOException
     */
    @FXML
    void AddProductHandler(ActionEvent event) throws IOException {
  
        Part singlePart = partsTable.getSelectionModel().getSelectedItem();
        newProduct.setAssociatedParts(singlePart);
       
    }

    @FXML
    public void CancelButton(ActionEvent event) throws IOException {

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
     * Handles deleting assoc parts
     * @param event
     */
    @FXML
    public void DeleteProductHandler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            newProduct.getAssociatedParts().remove(associatedPartTable.getSelectionModel().getSelectedItem());

        }
        
    }

    @FXML
    public void IDAddPartText(ActionEvent event) {

    }

    @FXML
    public void InventoryAddPartText(ActionEvent event) {

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
     *Saves the product.
     * @param event
     * @throws IOException
     */
    @FXML
    public void SaveProductHandler(ActionEvent event) throws IOException {

        try{
            int ID = 0;
            for(Product product : Inventory.getAllProducts()) {

                if(product.getProductID() > ID)
                    ID = product.getProductID();

            }

            String name = NameAddPartText.getText();
            int inventory = Integer.parseInt(InventoryAddPartText.getText());
            double priceCost = Double.parseDouble(AddPriceTextField.getText());
            int max = Integer.parseInt(MaxAddPartText.getText());
            int min = Integer.parseInt(MinAddPartText.getText());
            IDAddPartText.setText(String.valueOf(++ID));

            double minCost = 0;
                for (int p = 0; p < associatedPartList.size(); p++) {
                    minCost += associatedPartList.get(p).getPrice();
                }
                if (name.trim().isEmpty() || name.trim().toLowerCase().equals("part name")) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setTitle("ERROR: INVALID FORMAT");
                    newAlert.setHeaderText("Error");
                    newAlert.setContentText("Product Name must be entered!");
                    newAlert.showAndWait();
                    return;
                }
                if (min > max) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setTitle("ERROR: INVALID FORMAT");
                    newAlert.setHeaderText("Error");
                    newAlert.setContentText("Product Min must be less than Product Max!");
                    newAlert.showAndWait();
                    return;
                }
                if (inventory < min) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setTitle("ERROR: INVALID FORMAT");
                    newAlert.setHeaderText("Error");
                    newAlert.setContentText("Product Inventory Level must be between the Product's Min and Max values!");
                    newAlert.showAndWait();
                    return;
                }
                if (inventory > max) {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setTitle("ERROR: INVALID FORMAT");
                    newAlert.setHeaderText("Error");
                    newAlert.setContentText("Product Inventory Level must be between the Product's Min and Max values!");
                    newAlert.showAndWait();
                    return;
                }
                if(priceCost < 0)
                {
                    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                    newAlert.setTitle("ERROR: INVALID FORMAT");
                    newAlert.setHeaderText("Error");
                    newAlert.setContentText("Product must have a price greater than 0!");
                    newAlert.showAndWait();
                    return;
                }
                else {
                    Product newProduct = new Product(ID, name, priceCost, inventory, min, max);
                    newProduct.setProductID(ID);
                    newProduct.setName(name);
                    newProduct.setPrice(priceCost);
                    newProduct.setMax(max);
                    newProduct.setMin(min);
                    newProduct.setStock(inventory);

                    Inventory.addProduct(newProduct);

                    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                    stage.setScene(new Scene((Parent) scene));
                    stage.show();
                }
            }catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();
            }
    }

    @FXML
    public void SaveProductTextField(ActionEvent event) {

    }

    /**
     * Allows you to search for parts to add to Associated parts.
     * @param event
     */
    @FXML
    public void searchProductHandler(ActionEvent event) {

        if (!SaveProductTextField.getText().trim().isEmpty()) {
            if(SaveProductTextField.getText().trim().matches("-?\\d+")){
                int partID = Integer.parseInt(SaveProductTextField.getText().trim());
                partsInventorySearch.setAll(Inventory.lookupPart(partID));
            }
            else {
                partsInventorySearch.setAll(Inventory.lookupPart(SaveProductTextField.getText().trim()));
            }
        }
        partsTable.setItems(partsInventorySearch);
        if(partsInventorySearch.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Item Not Found");
            alert.setContentText("Try Searching Another Item");
            alert.showAndWait();
        }
    }
    
    public void updatePartTable() {
        partsTable.setItems(Inventory.getAllParts());
        
    }
}
