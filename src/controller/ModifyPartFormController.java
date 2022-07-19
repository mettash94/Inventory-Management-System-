package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
/**
 * Controller implements the logic for the modify part form screen
 *
 * @author Shwetha Mettakadapa
 */

public class ModifyPartFormController {
    @FXML
    private ToggleGroup InHouseOrOutSourced;
    @FXML
    private Button ModifyPartSaveButton;
    @FXML
    private TextField partModifyInputID;
    @FXML
    private TextField partModifyInputName;
    @FXML
    private TextField partInputModifyInv;
    @FXML
    private TextField partInputModifyPrice;
    @FXML
    private TextField InputMachineOrCompany;
    @FXML
    private TextField partModifyInputMin;
    @FXML
    private TextField partInputModifyMax;
    @FXML
    private Text LabelMachineOrCompany;
    @FXML
    private RadioButton InHouseRadioModify;
    @FXML
    private RadioButton OutSourcedModifyRadio;
    @FXML
    private Button ModifyPartCancelButton;
    private int currentIndex;




    /**
     * Receive information from the main screen.
     * @param part selected part
     * @param selectedIndex index of the part
     */
    public void getSelectedPart(int selectedIndex, Part part){

        currentIndex = selectedIndex;

        System.out.println(currentIndex);

        if (part instanceof InHouse) {
            InHouseRadioModify.setSelected(true);
            InputMachineOrCompany.setText(String.valueOf(((InHouse) part).getMachineId()));
        }
        else {
            OutSourcedModifyRadio.setSelected(true);
            InputMachineOrCompany.setText(((Outsourced) part).getCompanyName());
            //InputMachineOrCompany.setText(String.valueOf(((Outsourced) part).getCompanyName()));
            LabelMachineOrCompany.setText("Company Name");
        }

        partModifyInputID.setText(String.valueOf(part.getId()));
        partModifyInputName.setText(String.valueOf(part.getName()));
        partInputModifyInv.setText(String.valueOf(part.getStock()));
        partInputModifyPrice.setText(String.valueOf(part.getPrice()));
        partInputModifyMax.setText(String.valueOf(part.getMax()));
        partModifyInputMin.setText(String.valueOf(part.getMin()));
    }



    /**
     * Cancels attempt to modify the part and returns to main screen
     *
     * @param actionEvent Cancel button action.
     */

    public void ActionCancelModifyPart(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * When InHouse radio button is selected text label changes to machine ID
     *
     * @param actionEvent radio button action
     */

    public void ActionInHouseIsSelected(ActionEvent actionEvent) {
        LabelMachineOrCompany.setText("Machine ID");
    }


    /**
     * When OutSourced radio button is selected text label changes to Company Name
     *
     * @param actionEvent radio button action
     */

    public void ActionOutSourcedIsSelected(ActionEvent actionEvent) {
        LabelMachineOrCompany.setText("Company Name");


    }




    /**
     * When save  button is click the modified data is updated into the observable list using the
     * updatePart method
     * Main Screen is loaded with the updated data
     *
     * @param actionEvent save button action
     */

     public void ActionSaveModifyPart(ActionEvent actionEvent) throws IOException {
        int partID = Integer.parseInt(partModifyInputID.getText());
        String PartName = partModifyInputName.getText();
        int inv = Integer.parseInt(partInputModifyInv.getText());
        double price = Double.parseDouble(partInputModifyPrice.getText());
        int min = Integer.parseInt(partModifyInputMin.getText());
        int max = Integer.parseInt(partInputModifyMax.getText());
        int machineID;
        String companyName;

         // Check if max>min

         if (!(max > min)) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Max value entered should be greater than Min");
             alert.showAndWait();
             return;
         }

         //Check if inventory is between max and min values

         if (!(inv > min && inv < max)) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value should be between Max and Min");
             alert.showAndWait();
             return;
         }

         //When OutSourced is Selected Outsourced object is created

         if (OutSourcedModifyRadio.isSelected()) {
             companyName = InputMachineOrCompany.getText();
             Outsourced modifiedPart = new Outsourced(partID, PartName, price, inv, min, max, companyName);
             Inventory.updatePart(currentIndex, modifiedPart);
         }

         // When InHouse radio is selected  InHouse object is created

         if (InHouseRadioModify.isSelected()) {
             machineID= Integer.parseInt(InputMachineOrCompany.getText());
             InHouse modifiedPart = new InHouse(partID, PartName, price, inv, min, max, machineID);

             System.out.println(currentIndex);

             Inventory.updatePart(currentIndex, modifiedPart);
         }

         //Main Screen is loaded

         Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
         Object scene = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
         stage.setScene(new Scene((Parent) scene));
         stage.show();
    }
}
