package AaronGeorge;

import Model.InHouse;
import Model.Inventory;
import Model.Product;
import Model.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aaron George
 */
public class AaronGeorge extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        
        Part part1 = new InHouse(1, "SSD", 150.0, 5, 5, 5, 1);
        Inventory.addPart(part1);
        
        Part part2 = new InHouse(2, "DRAM", 100.0, 10, 10, 10, 10);
        Inventory.addPart(part2);
        
        Part part3 = new InHouse(3, "GPU", 85.0, 12, 12, 12, 12);
        Inventory.addPart(part3);
       
        Product product1 = new Product(1, "PS5", 500.0, 5, 5, 5);
        Inventory.addProduct(product1);
        product1.setAssociatedParts(part1);

        Product product2 = new Product(2, "PS4 Pro", 400.0, 10, 10, 10);
        Inventory.addProduct(product2);
        product2.setAssociatedParts(part2);

        Product product3 = new Product(3, "PS4 Digital", 300.0, 12, 12, 12);
        Inventory.addProduct(product3);
        product3.setAssociatedParts(part3);

        launch(args);
    }
    
}

