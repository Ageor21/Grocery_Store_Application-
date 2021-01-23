package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author Aaron George
 */

public class MainScreenController implements Initializable {
  
    @FXML
    private Button SearchPart;

    @FXML
    private TextField SearchPartText;

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
    private Button AddPart;

    @FXML
    private Button ModifyPart;

    @FXML
    private Button DeletePart;

    @FXML
    private Button SearchProduct;

    @FXML
    private TextField SearchProductText;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> ProductID;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;

    @FXML
    private TableColumn<Product, Double> PricePerUnit;

    @FXML
    private Button AddProduct;

    @FXML
    private Button ModifyProdcut;

    @FXML
    private Button DeleteProduct;

    @FXML
    private Button Exit;

    @FXML
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partsInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        productInventory.setAll(Inventory.getAllProducts());
        productTable.setItems(Inventory.getAllProducts());
              
        ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        partInventory.setAll(Inventory.getAllParts());
        partsTable.setItems(Inventory.getAllParts());
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
       
    }
    /**
     * Loads the add controller.
     * @param event
     * @throws IOException
     */
    @FXML
    public void addPartHandler(ActionEvent event) throws IOException {


        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
        

    }
    /**
     * Loads the add controller.
     * @param event
     * @throws IOException
     */
    @FXML
    void addProductHandler(ActionEvent event) throws IOException {
        
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    public boolean partValidation(Part part){
        boolean Match = false;
        for (int i = 0; i < productInventory.size(); i++) {
            if (productInventory.get(i).getAssociatedParts().contains(part)) {
                Match = true;
            }
        }
        return Match;
    }

    @FXML
    void deletePartHandler(ActionEvent event) {
        Part partToRemove = partsTable.getSelectionModel().getSelectedItem();
        if(partToRemove == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Selection");
            alert.setContentText("You must select an item!");
            alert.showAndWait();
            return;
        }
        if(partValidation(partToRemove)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Deletion Error");
            alert.setHeaderText("Part cannot be deleted.");
            alert.setContentText( "Part is still used by one or more products.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            Inventory.deletePart(partToRemove.getPartID());
            partInventory.remove(partToRemove);
            partsTable.refresh();
        }
    }

    public boolean productValidation(Product product) {
        boolean Match = false;
        int prodID = product.getProductID();
        for (int i = 0; i < productInventory.size(); i++) {
            if (productInventory.get(i).getProductID() == prodID) {
                if (!productInventory.get(i).getAssociatedParts().isEmpty()) {
                    Match = true;
                }
            }
        }
        return Match;
    }

    @FXML
    public void deleteProductHandler(ActionEvent event) {

        Product productToRemove = productTable.getSelectionModel().getSelectedItem();
        if(productToRemove == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Selection");
            alert.setContentText("You must select an item!");
            alert.showAndWait();
            return;
        }
        if(productValidation(productToRemove)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Product cannot be deleted.");
            alert.setContentText("Product still contains one or more parts.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Product, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            Inventory.deleteProduct(productToRemove.getProductID());
            productInventory.remove(productToRemove);
            productTable.refresh();
        }
    }

    @FXML
    public void exitHandler(ActionEvent event) {
        
        System.exit(0);

    }
    /**
     * Loads the modify controller as long as an item is selected.
     * @param event
     * @throws IOException
     */
    @FXML
    public void modifyPartHandler(ActionEvent event) throws IOException {
        try {
            Part selected = partsTable.getSelectionModel().getSelectedItem();
            if (!partInventory.isEmpty() && selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Selection");
                alert.setContentText("You must select an item!");
                alert.showAndWait();
                return;
            } else {

                Stage stage;
                Parent root;
                stage = (Stage) ModifyPart.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPart.fxml"));
                root = loader.load();
                ModifyPartController controller = loader.getController();
                Part part = partsTable.getSelectionModel().getSelectedItem();
                int index = partsTable.getSelectionModel().getSelectedIndex();
                controller.setPart(part, index);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch (IOException e){}
    }
    /**
     * Loads the modify controller as long as an item is selected.
     * @param event
     * @throws IOException
     */
    @FXML
    public void modifyProductHandler(ActionEvent event) throws IOException {
        try {
            Product productSelected = productTable.getSelectionModel().getSelectedItem();
            if (!productInventory.isEmpty() && productSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Selection");
                alert.setContentText("You must select an item!");
                alert.showAndWait();
                return;

            } else {
                Stage stage;
                Parent root;
                stage = (Stage) ModifyProdcut.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
                root = loader.load();
                ModifyProductController controller = loader.getController();
                Product product = productTable.getSelectionModel().getSelectedItem();
                int index = productTable.getSelectionModel().getSelectedIndex();
                controller.setProduct(product, index);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch (IOException e){}
    }
    /**
     * Allows you to search for a specific item by text or ID.
     * <p><b>It would be nice to implement a search that is not case-sensitive.</b></p>
     * @param event
     */
    @FXML
    public void searchPartHandler(ActionEvent event) {
        if (!SearchPartText.getText().trim().isEmpty()) {
            if(SearchPartText.getText().trim().matches("-?\\d+")){
                int partID = Integer.parseInt(SearchPartText.getText().trim());
                partsInventorySearch.setAll(Inventory.lookupPart(partID));
            }
            else {
                partsInventorySearch.setAll(Inventory.lookupPart(SearchPartText.getText().trim()));
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

    @FXML
    public void searchProductHandler(ActionEvent event) {
        if (!SearchProductText.getText().trim().isEmpty()) {
            if(SearchProductText.getText().trim().matches("-?\\d+")){
                int productID = Integer.parseInt(SearchProductText.getText().trim());
                productInventorySearch.setAll(Inventory.lookupProduct(productID));
            }
            else {
                productInventorySearch.setAll(Inventory.lookupProduct(SearchProductText.getText().trim()));
            }
        }
        productTable.setItems(productInventorySearch);
        if(productInventorySearch.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Item Not Found");
            alert.setContentText("Try Searching Another Item");
            alert.showAndWait();
        }
    }

    @FXML
    public void SearchTextHandler(MouseEvent event){
        Object source = event.getSource();
        TextField field = (TextField) source;
        field.setText("");
        if (SearchPartText == field) {
            if (partInventory.size() != 0) {
                partsTable.setItems(partInventory);
            }
        }
        if (SearchProductText == field) {
            if (productInventory.size() != 0) {
                productTable.setItems(productInventory);
            }
        }
    }

}
