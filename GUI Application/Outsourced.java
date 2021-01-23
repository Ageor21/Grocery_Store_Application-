package Model;

/**
 *
 * @author Aaron George
 */
public class Outsourced extends Part {
    
    private String companyName;

    public Outsourced(int partID, String name, double price, int inStock, int min, int max, String companyName) {
        super(partID, name, price, inStock, min, max);
        this.companyName = companyName;
    }
    /**
     * These are the getter and setter fot the class
     */
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
   
    
}
