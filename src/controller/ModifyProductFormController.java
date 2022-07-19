package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller implements the logic for the modify product form screen
 *
 * @author Shwetha Mettakadapa
 */

public class  ModifyProductFormController implements Initializable {
    public Button ModifyProductSaveButton;
    public TextField SearchPartButton;
    @FXML
    private Button RemovePartButton;
    @FXML
    private Button CancelButton;
    @FXML
    private Button addButton;
    @FXML
    private TableView AllPartsTable;
    @FXML
    private TableColumn partID;
    @FXML
    private TableColumn partName;
    @FXML
    private TableColumn partInv;
    @FXML
    private TableColumn partPrice;
    @FXML
    private TableView AssociatedPartTable;
    @FXML
    private TableColumn AssociatedPartID;
    @FXML
    private TableColumn AssociatedPartName;
    @FXML
    private TableColumn AssociatedPartInv;
    @FXML
    private TableColumn AssociatedPartPrice;
    @FXML
    private TextField productID;
    @FXML
    private TextField productName;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productMin;

    private int currentIndex;

    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     * Initialize and populate the form with data referencing the index
     * @param selectedIndex index of the array list
     * @param product given product
     *
     */

    @FXML
    public void getSelectedProduct(int selectedIndex, Product product){

        currentIndex = selectedIndex;

        productID.setText(String.valueOf(product.getId()));
        productName.setText(String.valueOf(product.getName()));
        productInv.setText(String.valueOf(product.getStock()));
        productPrice.setText(String.valueOf(product.getPrice()));
        productMax.setText(String.valueOf(product.getMax()));
        productMin.setText(String.valueOf(product.getMin()));

        // Use the getter and put all the associated parts into an observable list

        for (Part part: product.getAllAssociatedParts()) {
            associatedPartsList.add(part);
        }
    }

    /**
     * Initialize and populate the all parts table and the associated parts table
     * @param url url of resourcebundle
     * @param resourceBundle localizes root object
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AllPartsTable.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add

        AssociatedPartTable.setItems(associatedPartsList);
        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


    /**
     * Cancel Button click returns to mainscreen
     * @param actionEvent cancel button click fires an event
     */

    public void ActionCancelModify(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    /**
     * ADD Button add parts from all parts table to associated parts table
     * @param actionEvent add button click fires an event
     */
    public void ActionAddPartInModify(ActionEvent actionEvent) {
        Part selectedPart = (Part) AllPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part to be removed");
            alert.showAndWait();

        } else {
            associatedPartsList.add(selectedPart);
        }
    }

    /**
     * Remove ASsociated Part Button deletes selected part from the associated part table
     * @param actionEvent remove button click fires an event
     */
    public void ActionRemoveAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) AssociatedPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part to be removed");
            alert.showAndWait();

        } else {
            associatedPartsList.remove(selectedPart);
            Product.deleteAssociatedPart(selectedPart);
        }
    }


    /**
     * Saves all the new modified including the product data, and associated part data if the table has been updated
     * @param actionEvent save button click fires an event
     */
    public void ActionSaveModify(ActionEvent actionEvent) throws IOException {

            int id = Integer.parseInt(productID.getText());
            String name = productName.getText();
            int stock = Integer.parseInt(productInv.getText());
            double price = Double.parseDouble(productPrice.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());

            if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value should be in between max and min");
                alert.showAndWait();
                return;
            } else if (min >= max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "max value should be greater than min");
                alert.showAndWait();
                return;
            }
            Product updatedProduct = new Product(id, name, price, stock, min, max);
            Inventory.updateProduct(currentIndex, updatedProduct);


        //Main Screen is loaded

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
           // Object scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainformScreen.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.show();


    }

    /**
     * Searches for a part by name or id and displays it
     *
     * @param actionEvent search action fires an event
     */

    public void ActionSearchPart(ActionEvent actionEvent) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchedParts = FXCollections.observableArrayList();
        String searchString = SearchPartButton.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                searchedParts.add(part);
            }
        }

        AllPartsTable.setItems(searchedParts);

        if (searchedParts.size() == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);

            alertError.setContentText("Part Not Found. Enter Different Name or ID");
            alertError.showAndWait();

            AllPartsTable.setItems((allParts));


        }
    }
}
