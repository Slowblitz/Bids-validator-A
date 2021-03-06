package MainController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.annotation.processing.Filer;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * The type Main controller.
 *
 * @param <event> the type parameter
 */
public class MainController<event> implements Initializable {

    /**
     * The Browse.
     */
    @FXML
    public JFXButton browse;
    /**
     * The Pathway.
     */
    public JFXTextField pathway;
    /**
     * The Scan.
     */
    public JFXButton scan;
    /**
     * The Log.
     */
    public JFXTextArea log;
    /**
     * The Pass.
     */
    public Text pass;

    private Stage primaryStage;
    private Object ActionEvent;


    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("Initialize !");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        final File[] selectedDirectory = new File[1];

        browse.setOnAction(e -> {
            selectedDirectory[0] = directoryChooser.showDialog(primaryStage);
            pathway.setText(selectedDirectory[0].getAbsolutePath());
            System.out.println(selectedDirectory[0].getAbsolutePath());
            log.setScrollTop(Double.MAX_VALUE);
        });

        scan.setOnAction(e -> {
            try {

                ProcessBuilder pb = new ProcessBuilder("python",
                      "number.py",
                        selectedDirectory[0].getAbsolutePath());

                Process p = pb.start();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    log.appendText( line+ System.lineSeparator());
                    log.setScrollTop(Double.MAX_VALUE);
                    if(line.contains("ERROR") ){
                        pass.setText("BIBS validator failed ");
                        pass.setFill(Color.RED);
                    }
                    pass.setVisible(true);
                }
            } catch (Exception eX) {
                System.out.println(eX);
            }
            log.setScrollTop(Double.MAX_VALUE);
        });

        log.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
            log.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
            //use Double.MIN_VALUE to scroll to the top
        });




    }


}
