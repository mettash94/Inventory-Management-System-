package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
/**
 * RUNTIME ERROR: When I directed exported the buttons from FXML they were imported into the
 * controller class as public. When i changed them to private and executed the code. I got an error.
 * I fixed it by adding @FXML and importing the corresponding class
 * */

/**
 * Controller implements the logic for the add product form screen
 *
 * @author Shwetha Mettakadapa
 */

public class AddPartFormController {
    @FXML
    private TextField inputPartName;
    @FXML
    private TextField inputPartInventory;
    @FXML
    private TextField inputPartPrice;
    @FXML
    private TextField inputPartMachineId;
    @FXML
    private TextField inputPartMin;
    @FXML
    private TextField inputPartMax;
    @FXML
    private Button SavePartButton;
    @FXML
    private Button CancelButton;
    @FXML
    private RadioButton InHouseRadio;
    @FXML
    private RadioButton OutSourcedRadio;
    @FXML
    private Text MachineIdOrCompanyName;

    /**
     * LOGICAL ERROR: After attaching event handlers to the radio buttons I ran the code
     * and got a logical error where I could click on inHouse button it would be selected and then
     * click on the OutSourced button and that would be selected too. I thought the default behavior of radio
     * button was either one would be selected not both. That wasn't the case. I had to add a ToggleGroup of the 
     * same name for both, inorder for exactly ONE to be selected and change the label text accordingly
     *
     * */

    /**
     * On Radio Button Selection of InHouse text changes to Machine ID
     * @param actionEvent selecting radio button fires an event
     */
    public void ActionInHousePart(ActionEvent actionEvent) {
        MachineIdOrCompanyName.setText("Machine ID");
    }
    /**
     * On Radio Button Selection of OutSourced text changes to CompanyName
     * @param actionEvent selecting radio button fires an event
     */
    public void ActionOutSourcedPArt(ActionEvent actionEvent) {
        MachineIdOrCompanyName.setText("Company Name");
    }

    /**
     * On pressing the Cancel Button screen returns to MainScreen
     * @param actionEvent clicking cancel button fires an event
     * @throws throws an IO exception
     */
    public void ActionCancelPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * On pressing the Save Button valid data new objects are created and push into inventory observable list
     * @param actionEvent clicking save button fires an event
     * @throws throws an io exception
     */

    public void ActionSavePartData(ActionEvent actionEvent) throws IOException {

        try {

            // Randomly generated Unique ID
            int generatedID = (int) (Math.random() * 500);

            String PartName = inputPartName.getText();

            int PartInventory = Integer.parseInt(inputPartInventory.getText());

            Double PartPrice = Double.parseDouble(inputPartPrice.getText());

            int PartMin = Integer.parseInt(inputPartMin.getText());

            int PartMax = Integer.parseInt(inputPartMax.getText());

            int MachineID;
            String CompanyName;


            // Check if max>min

            if (!(PartMax > PartMin)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max value entered should be greater than Min");
                alert.showAndWait();
            }

            //Check if inventory is between max and min values

            else if (!(PartInventory > PartMin && PartInventory < PartMax)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value should be between Max and Min");
                alert.showAndWait();
            } else {

                //When OutSourced is Selected Outsourced object is created

                if (OutSourcedRadio.isSelected()) {
                    CompanyName = inputPartMachineId.getText();
                    Outsourced addOutSourcedPart = new Outsourced(generatedID, PartName, PartPrice,
                            PartInventory, PartMin, PartMax, CompanyName);
                    Inventory.addPart(addOutSourcedPart);
                }

                // When InHouse is InHouse object is created

                if (InHouseRadio.isSelected()) {
                    MachineID = Integer.parseInt(inputPartMachineId.getText());
                    InHouse addInHousePart = new InHouse(generatedID, PartName, PartPrice,
                            PartInventory, PartMin, PartMax, MachineID);
                    Inventory.addPart(addInHousePart);
                }

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/view/MainformScreen.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();


            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.showAndWait();

        }

    }
}
