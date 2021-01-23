
package Model;

/**
 *
 * @author Aaron George
 */
public class InHouse extends Part {
    
    private int machineID;

    public InHouse(int partID, String name, double price, int inStock, int min, int max, int machineID) {
        super(partID, name, price, inStock, min, max);
        this.machineID = machineID;
    }

    /**
     * This is the getter
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * This is the setter
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

   
    
    
    
}
    
    
    
   