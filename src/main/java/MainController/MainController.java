package MainController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.*;
import java.net.URL;

import java.util.ResourceBundle;

public class MainController<event> implements Initializable {

    @FXML
    public JFXButton browse;
    public JFXTextField pathway;
    public JFXButton scan;
    public JFXTextArea log;

    private Stage primaryStage;


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
                        "/home/garcia.j/fxml/samples/HelloFX/Gradle/hellofx/src/main/java/MainController/number.py",
                        selectedDirectory[0].getAbsolutePath());

                Process p = pb.start();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    log.appendText( line+ System.lineSeparator());

                }
            } catch (Exception eX) {
                System.out.println(eX);
            }
            log.setScrollTop(Double.MAX_VALUE);
        });

        log.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                log.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });



    }


}
