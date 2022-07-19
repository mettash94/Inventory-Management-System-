package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Logic for the entry point Main Screen of the java fx application
 */


public class MainScreenController implements Initializable {
    public TextField ProductSearchButton;
    @FXML
    private Button ModifyProductButton;
    @FXML
    private TextField InputSearchPartButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableView ProductTable;
    @FXML
    private TableColumn ProductID;
    @FXML
    private TableColumn ProductName;
    @FXML
    private TableColumn ProductInv;
    @FXML
    private TableColumn ProductPrice;
    @FXML
    private Button MainScreenModifyButton;
    @FXML
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Part, Integer> PartIDColumn;
    @FXML
    private TableColumn<Part, String> PartNameColumn;
    @FXML
    private TableColumn<Part, Integer> PartInventoryColumn;
    @FXML
    private TableColumn<Part, Double> PartPriceColumn;
    @FXML
    private Button AddPartButton;
    @FXML
    private Button AddProductButton;

    public int indexOfPart;




    /**
     * Loads the AddProduct Form and controller.
     *
     * @param actionEvent
     */

    @FXML
    public void ActionAddProduct(ActionEvent actionEvent) throws IOException {
        System.out.println("Button Clicked");

        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads the Modify Product Form and controller .
     *
     * @param actionEvent
     */

    public void ActionModifyProductPage(ActionEvent actionEvent) throws IOException {
        Product selectedProductToModify = (Product) ProductTable.getSelectionModel().getSelectedItem();

        if (selectedProductToModify == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Please Select a Product to be modified");
            alertError.showAndWait();

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();
            ModifyProductFormController MPFC = loader.getController();
            MPFC.getSelectedProduct(ProductTable.getSelectionModel().getSelectedIndex(),
                    (Product) ProductTable.getSelectionModel().getSelectedItem());


            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        }
    }


    /**
     * Loads the AddPart Form and controller .
     *
     * @param actionEvent
     */

    public void ActionAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Loads the ModifyPart Form and controller .
     *
     * @param actionEvent
     */
    public void ActionGoToModifyPage(ActionEvent actionEvent) throws IOException {

       Part selectedPartToModify = PartTableView.getSelectionModel().getSelectedItem();

        if (selectedPartToModify == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Please Select a Part to be modified");
            alertError.showAndWait();

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();
            ModifyPartFormController mpfc = loader.getController();
            mpfc.getSelectedPart(PartTableView.getSelectionModel().getSelectedIndex(),
                    PartTableView.getSelectionModel().getSelectedItem());


            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        }


    }


    /**
     * Loads all the Part data from PARTS OBSERVABLE LIST and all Product data from Product List
     *
     * @param url
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        PartTableView.setItems(Inventory.getAllParts());
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        ProductTable.setItems(Inventory.getAllProducts());
        ProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Exits the main screen, closes the stage
     *
     * @param actionEvent triggers an event
     */
    public void ActionExitMain(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Deletes a selected Part
     *
     * @param actionEvent triggers an event
     */
    public void ActionDeletePart(ActionEvent actionEvent) {
        Part selectedPart = (Part) PartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Please Select a Part to be deleted");
            alertError.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure, you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }

    }

    /**
     * Deletes a selected product
     *
     * @param actionEvent triggers an event
     */

    public void ActionDeleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = (Product) ProductTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Please Select a Product to be deleted");
            alertError.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure, you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (selectedProduct.getAllAssociatedParts().size() > 0) {
                    Alert cantDelete = new Alert(Alert.AlertType.ERROR);
                    cantDelete.setTitle("Error Message");
                    cantDelete.setContentText("Remove associated parts before you delete the product.");
                    cantDelete.showAndWait();
                    return;

                }
                Inventory.deleteProduct(selectedProduct);

            }
        }
    }

    /**
     * Searches for a part by name or id and displays it
     *
     * @param actionEvent triggers an event
     */

    public void ActionPartSearch(ActionEvent actionEvent) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchedParts = FXCollections.observableArrayList();
        String searchString = InputSearchPartButton.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                searchedParts.add(part);
            }
        }

        PartTableView.setItems(searchedParts);

        if (searchedParts.size() == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);

            alertError.setContentText("Part Not Found. Enter Different Name or ID");
            alertError.showAndWait();

            PartTableView.setItems((allParts));


        }

    }

    /**
     * Searches for a product by name or id and displays it
     *
     * @param actionEvent
     */
    public void ActionProductSearch(ActionEvent actionEvent) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> searchedProduct = FXCollections.observableArrayList();
        String searchString = ProductSearchButton.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                searchedProduct.add(product);
            }
        }

        ProductTable.setItems(searchedProduct);

        if (searchedProduct.size() == 0) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Product Not Found. Enter Different Name or ID");
            alertError.showAndWait();

            ProductTable.setItems((allProducts));


        }
    }
}
