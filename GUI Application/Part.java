package Model;

/**
 *
 * @author Aaron George
 */

public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Part(int partID, String name, double price, int inStock, int min, int max) {
        this.id = partID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    /**
     * This is a getter for the class
     */
    public int getPartID() {
        return id;
    }
    /**
     * This is a setter for the class
     */
    public void setPartID(int partID) {
        this.id = partID;
    }
    /**
     * This is a getter for the class
     */
    public String getName() {
        return name;
    }
    /**
     * This is a setter for the class
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This is a getter for the class
     */
    public double getPrice() {
        return price;
    }
    /**
     * This is a setter for the class
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * This is a getter for the class
     */
    public int getInStock() {
        return inStock;
    }
    /**
     * This is a setter for the class
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
    /**
     * This is a getter for the class
     */
    public int getMin() {
        return min;
    }
    /**
     * This is a setter for the class
     */
    public void setMin(int min) {
        this.min = min;
    }
    /**
     * This is a getter for the class
     */
    public int getMax() {
        return max;
    }
    /**
     * This is a setter for the class
     */
    public void setMax(int max) {
        this.max = max;
    }

}



