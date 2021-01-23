package Model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aaron George
 */
public class Inventory {

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();


    public int partListSize() {
	return allParts.size();
	}

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    /**
     * This looks up specific parts
     *
     */
    public static Part lookupPart(int partId) {
        for(Part p : allParts) {
            if(p.getPartID() == partId)
                return p;
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partNameToLookUp) {
        if (!allParts.isEmpty()) {
            ObservableList searchPartsList = FXCollections.observableArrayList();
            for (Part p : getAllParts()) {
                if (p.getName().contains(partNameToLookUp)) {
                    searchPartsList.add(p);
                }
            }
            return searchPartsList;
        }
        return null;
    }
    /**
     * This looks up specific products
     */
    public static Product lookupProduct(int productId) {
        for (Product p : allProducts) {
            if (p.getProductID() == productId) {
                return p;
            }
        }
        return null;
    }
    public static ObservableList<Product> lookupProduct(String productNameToLookUp) {
        if (!allProducts.isEmpty()) {
            ObservableList searchProductsList = FXCollections.observableArrayList();
            for (Product p : getAllProducts()) {
                if (p.getName().contains(productNameToLookUp)) {
                    searchProductsList.add(p);
                }
            }
            return searchProductsList;
        }
        return null;
    }
    /**
     * This updates specific parts
     */
    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }
    /**
     * This updates specific products
     */
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }
    /**
     * This allows you to delete a part
     */
    public static boolean deletePart(int partID) {
        for (Part p : allParts) {
            if (p.getPartID() == partID) {
                allParts.remove(p);
                return true;
            }
        }
        return false;
    }
    /**
     * This allows you to delete a product
     */
    public static boolean deleteProduct(int productID) {
        for (Product p : allProducts){
            if (p.getProductID() == productID) {
                allProducts.remove(p);
                return true;
            }
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }




}

