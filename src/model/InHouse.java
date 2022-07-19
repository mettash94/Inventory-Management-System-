package model;

/**
 * This class models the parts manufactured In-House.
 * Sub-class of the abstract class Parts
 *
 * @author Shwetha Mettakadapa
 */

public class InHouse extends Part {
    /**
     * Machine ID for the parts manufactured In-house
     */
    private int machineId;

    /**
     * Constructor to instantiate InHouse object(Sub Class) which inherits methods from Super Class Parts.
     *
     * @param id the part's ID
     * @param name the part's NAME
     * @param price the part's PRICE
     * @param stock the part's number of available units in the INVENTORY
     * @param min the minimum level Of INVENTORY for the part
     * @param max the maximum level of INVENTORY for the part
     * @param machineId the machine ID for the part not inherited from super class
     *
     *
     */


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * The SETTER method that sets the value the machineId
     *
     * @param machineId the machineId of the part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * The GETTER method retrieves value for the machineId
     *
     * @return machineId of the part
     */
    public int getMachineId() {
        return machineId;
    }

}
