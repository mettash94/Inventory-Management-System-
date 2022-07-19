package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This concrete class demonstrates a product and its associated parts from the abstract class Parts
 *
 * @author Shwetha Mettakadapa

 */
public class Product  {
    /**
     * Product ID
     */
    private int id;

    /**
     * Product NAME
     */
    private String name;

    /**
     * Product PRICE
     */
    private double price;

    /**
     * Number of available products in the inventory
     */
    private int stock;

    /**
     * Minimum number of products in the inventory
     */
    private int min;

    /**
     * Maximum number of products in the inventory
     */
    private int max;


    /**
     * An observable list object that lists the associatedParts for a product
     * <> contains name of the class. AssociatedParts is the name of the observable list
     */

    public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Constructor to instantiate Product class
     *
     * @param id    is the product's ID
     * @param name  is the product's NAME
     * @param price is the product's PRICE
     * @param stock the product's number of available units in the INVENTORY
     * @param min   the minimum level Of INVENTORY for the product
     * @param max   the maximum level of INVENTORY for the product
     */

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * The SETTER method to set value of the ID  of datatype INT
     *
     * @param id is the product's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The SETTER method to set value of the NAME of datatype STRING
     *
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The SETTER method to set value of the PRiCE of datatype DOUBLE
     *
     * @param price is the product's PRICE
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The SETTER method to set value of the STOCK of datatype INT
     *
     * @param stock the product's number of available units in the INVENTORY
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * The SETTER method to set value of the STOCK of datatype INT
     *
     * @param min the minimum level Of INVENTORY for the product
     */

    public void setMin(int min) {
        this.min = min;
    }

    /**
     * The SETTER method to set value of the STOCK of datatype INT
     *
     * @param max the maximum level Of INVENTORY for the product
     */

    public void setMax(int max) {
        this.max = max;
    }


    /**
     * The GETTER method for product ID
     *
     * @return id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * The GETTER method for product NAME
     *
     * @return name of the product
     */

    public String getName() {
        return name;
    }

    /**
     * The GETTER method for product PRICE
     *
     * @return price of the product
     */

    public double getPrice() {
        return price;
    }

    /**
     * The GETTER method to get value of the STOCK
     *
     * @return stock the product's number of available units in the INVENTORY
     */

    public int getStock() {
        return stock;
    }

    /**
     * The GETTER method to get value of the MIN
     *
     * @return min the minimum level Of INVENTORY for the product
     */


    public int getMin() {
        return min;
    }

    /**
     * The GETTER method to get value of the MAX
     *
     * @return max the maximum level Of INVENTORY for the product
     */

    public int getMax() {
        return max;
    }

    /**
     * Adds a part to the observable list of parts called associatedParts
     *
     * @param part The part to be added to the list
     */
    public static void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Checks to see if the part is on the associatedPart list and then deletes it
     * @param selectedAssociatedPart the part to be deleted
     * @return boolean type,true or false
     */
    public static boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }


    /**
     * GETTER method for list of all parts associated with the product
     * @return associatedParts observable list
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}