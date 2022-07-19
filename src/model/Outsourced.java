package model;


/**
 * This class models the parts manufactured Outside the Company.
 * Sub-class of the abstract class Parts
 *
 * @author Shwetha Mettakadapa
 */
public class Outsourced extends Part{
    /**
     * The name of the Company that manufactures the part
     */
    private String companyName;


    /**
     * Constructor to instantiate Outsourced object(Sub Class) which inherits methods from Super Class Parts.
     *
     * @param id the part's ID
     * @param name the part's NAME
     * @param price the part's PRICE
     * @param stock the part's number of available units in the INVENTORY
     * @param min the minimum level Of INVENTORY for the part
     * @param max the maximum level of INVENTORY for the part
     * @param companyName is the name of the company that manufactures the part. Not inherited from Super Class
     *

     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
      /** The SETTER method sets the string value of the companyName
     * @param companyName is the name of the company that manufactures the outsourced part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * The GETTER method to retrieve value for the companyName
     *
     * @return the name of the company for the part
     */
    public String getCompanyName() {
        return companyName;
    }


}
