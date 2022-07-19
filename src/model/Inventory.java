package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The inventory class store all the products and associated part objects in an observable list
 * Contains all the data needed for the application
 *
 * @author Shwetha Mettakadapa
 */

public class Inventory {

    /**
     * An observable list of all parts in the inventory.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * An observable list of all products in the inventory.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     * Add a part to the inventory.
     *
     * @param newPart new part object to be added to the allPart list.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }



    /** Access all parts
     * @return All parts in the observable list
     *
     * */

    public static ObservableList<Part> getAllParts() {

        return allParts;
    }

    /** Access all products
     * @return All products in the observable list
     *
     * */
    public static ObservableList<Product> getAllProducts(){

        return allProducts;
    }


    /**
     * Add a product to the inventory.
     *
     * @param newProduct new Product object to be added to the allPart list.
     */

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    /**
     * Iterate through the observable list allParts and return part if found
     * @param partId the part ID to be searched for
     * @return part is found then searchedProduct equals the part  or null(default value)
     */

    public static Part lookupPart(int partId) {
        Part searchedPart= null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                searchedPart = part;
            }
        }
        return searchedPart;

    }

    /**
     * Iterate through the observable list allProducts and return product if found
     * @param productId the product ID to be searched for
     * @return product is found then searchedProduct equals the product  or null(default value)
     */
    public static Product lookupProduct(int productId) {
        Product searchedProduct = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                searchedProduct = product;
            }
        }

        return searchedProduct;
    }
    /**
     * An observable list called searchedPArt is created with partName if it matches the existing allParts list
     * @param partName name of the part being searched for
     * @return searched part
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> searchedPart = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                searchedPart.add(part);
            }
        }

        return searchedPart;
    }

    /**
     * An observable list called searchedProduct is created with productName if it matches the existing allParts list
     * @param productName of the product being searched for
     * @return searched part
     */

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> searchedProduct = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                searchedProduct.add(product);
            }
        }

        return searchedProduct;
    }

    /**
     * Updates or replaces a part in the observable list
     * @param index index of the part that needs to be updated.
     * @param selectedPart new part
     */
    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }

    /**
     * Updates or replaces a product in the observable list
     * @param index index of the product that needs to be updated.
     * @param selectedProduct new product.
     */
    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }

    /**
     *Deletes a part from observable list
     *
     * @param selectedPart The part to be deleted
     * @return true or false
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Deletes a product from the list of parts.
     *
     * @param selectedProduct The product to be deleted.
     * @return true or false
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }


}
