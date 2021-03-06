import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
public class HelloFX extends Application {


    @Override
    public void start(final Stage primaryStage) {
        try {
            // Localisation du fichier FXML.
           Parent root  = FXMLLoader.load(new File("/home/garcia.j/fxml/samples/HelloFX/Gradle/hellofx/src/main/java/test.fxml").toURI().toURL());
            // Chargement du FXML.

            // Création de la scène.
            final Scene scene = new Scene(root, 710, 555);
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            System.err.println("Erreur au chargement: " + ex);

        }
        primaryStage.setTitle("Bids validator A");
        primaryStage.show();
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The type Main controller.
     * Test controler not used
     */
    public abstract static class MainController implements Initializable {

        private void onButtonActivated() {
            System.out.println("Salut le monde !");
        }
    }
}