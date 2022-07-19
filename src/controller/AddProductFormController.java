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
import java.util.ResourceBundle;


/**
 * Controller implements the logic for the Add product form screen
 *
 * @author Shwetha Mettakadapa
 */

public class AddProductFormController implements Initializable {
    public TextField SearchPartButton;
    @FXML
    private TextField inputProductID;
    @FXML
    private TextField inputProductName;
    @FXML
    private TextField inputProductInv;
    @FXML
    private TextField inputProductPrice;
    @FXML
    private TextField inputProductMax;
    @FXML
    private TextField inputProductMin;
    @FXML
    private Button AddProductSaveButton;
    @FXML
    private Button RemoveButton;
    @FXML
    private TableView AssociatedPartsTable;
    @FXML
    private TableColumn AssociatedPartID;
    @FXML
    private TableColumn AssociatedPartName;
    @FXML
    private TableColumn AssociatedPartInv;
    @FXML
    private TableColumn AssociatedPartPrice;
    @FXML
    private Button AddPartButton;
    @FXML
    private TableColumn PartIDColumn;
    @FXML
    private TableColumn PartNameColumn;
    @FXML
    private TableColumn PartInvColumn;
    @FXML
    private TableColumn PartPriceColumn;
    @FXML
    private TableView AllPartsTable;
    @FXML
    private Button CancelButton;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     * Cancel button cancels adding a product and loads the main screen
     *
     * @param actionEvent event action from cancel button
     * @throws throws an IO Exception
     */

    public void ActionCancelProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialize and populate the table with parts from all parts list and associated parts from associated part list.
     *
     * @param resourceBundle localizes root object
     * @param  url of the resource bundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AllPartsTable.setItems(Inventory.getAllParts());
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load data from associated parts list into table view

        AssociatedPartsTable.setItems(associatedPartsList);
        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * When add part button is clicked the selected part is added to associated part list
     *
     * @param actionEvent add part button click fires an event
     */

    public void ActionAddPartToAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) AllPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part to be ADDED");
            alert.showAndWait();

        } else {
            associatedPartsList.add(selectedPart);
        }
    }

    /**
     * When Remove Associated part button is clicked the selected part is added to associated part list
     *
     * @param actionEvent remove button click fires an event
     */

    public void ActionRemoveAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) AssociatedPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part to be removed");
            alert.showAndWait();

        } else {
            associatedPartsList.remove(selectedPart);
        }

    }

    /**
     * Saves all the new entered data including the product data, and associated part data if the table has been updated
     * @param actionEvent save button click fires an event
     */

    public void ActionSaveProductData(ActionEvent actionEvent) throws IOException {

        //Generate a unique ID
        int generatedID = (int) (Math.random() * 3000);


        String name = inputProductName.getText();
        int inv = Integer.parseInt(inputProductInv.getText());
        double price = Double.parseDouble(inputProductPrice.getText());
        int max = Integer.parseInt(inputProductMax.getText());
        int min = Integer.parseInt(inputProductMin.getText());

        // Check if max>min

        if (!(max > min)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Max value entered should be greater than Min");
            alert.showAndWait();
        }

        //Check if inventory is between max and min values

        else if (!(inv > min && inv < max)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value should be between Max and Min");
            alert.showAndWait();
        } else {
            Product newProduct = new Product(generatedID, name, price, inv, min, max);

            for (Part part : associatedPartsList) {
                if (part != associatedPartsList)
                    Product.addAssociatedPart(part);
            }

            Inventory.getAllProducts().add(newProduct);

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();

        }
    }

    /**
     * Searches for a part by name or id and displays it
     *
     * @param actionEvent writing text into the search text field fires an event
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