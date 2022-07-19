package main;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

/** The inventory management application is an application where products and their related parts are managed.
 * Parts and products can be added, modified and deleted
 *
 * FUTURE ENHANCEMENT  A future enhancement would be to add HOW MANY PARTS a product needs.
 * for example a bike needs 4 wheels. Adding a column in the assoicated parts column and adding the number as an
 * input field would be a logical enhancement
 *
 * JAVADOC folder location C482/javadoc
 *
 * @author Shwetha Mettakadapa
 * */
public class Main extends Application {

    @Override

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainFormScreen.fxml"));
        stage.setTitle("Inventory Management");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args){

        //Add sample parts

        InHouse brakes = new InHouse(52,"Brakes", 250.00, 5, 1, 20,
                101);

        InHouse wheel = new InHouse(82,"Wheel", 80.00, 8, 1, 20,
                101);

        Outsourced seat = new Outsourced(75, "Seat",35.99, 50, 30,
                100, "Michelin");

        Inventory.addPart(brakes);
        Inventory.addPart(wheel);
        Inventory.addPart(seat);

        //Add sample product

        Product giantBike = new Product(55, "GiantBike", 999.99, 20, 20,
                100);
        giantBike.addAssociatedPart(brakes);
        giantBike.addAssociatedPart(seat);

        Inventory.addProduct(giantBike);
        launch(args);
    }
}
