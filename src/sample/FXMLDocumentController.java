/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    private final String FILE = "src/playlist.txt";
    Mp3  song;
    int play_on=-1;
    int number_list=0;
    private Stage stage;

    @FXML
    private Label song_name;

    @FXML
    private Label appName;

    @FXML
    private Button play;

    @FXML
    private Button open;

    @FXML
    private Button stop;
    @FXML
    private Button exit;
    @FXML
    private ListView<String> list_view;
    ArrayList<String> list_copy = new ArrayList<>();
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    void exit(ActionEvent event) throws FileNotFoundException, IOException {

        File file = new File (FILE);
        try (PrintWriter printWriter = new PrintWriter (file)) {
            list.stream().forEachOrdered(item -> printWriter.println(item));
        }

        System.exit(0x0);
    }

    public void init(Stage stage){

        this.stage=stage;
    }


    @FXML
    void open(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null){
            play_on=0;
            play.setDisable(false);
            song = new Mp3(file.getAbsolutePath());
        }
    }

    @FXML
    void play(ActionEvent event) {

        if(song!=null&& play_on==0 ){

            song.play();
            play_on=-1;
            stop.setDisable(false);
            open.setDisable(true);
            String name_file;
            name_file = song.filename;
            if (!list.contains(name_file) || number_list >= 6) {
                number_list++;
                if (number_list > 6) {
                    list.set(number_list % 7, name_file);
                    list_copy.set(number_list % 7, name_file);
                } else {
                    list.add(name_file);
                    list_copy.add(name_file);
                }
            }
            list_view.setItems(list);
            song_name.setText(name_file);
        }

    }

    @FXML
    void stop(ActionEvent event) {

        if(song!=null){
            play_on=0;
            open.setDisable(false);
            song.close();
            song_name.setText("Press Open or Play to continue enjoying!");
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        System.exit(0x0);

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        song_name.setText("No Song Playing");
        appName.setText("Rocker!!");
        play.setDisable(true);
        stop.setDisable(true);
        song=null;

        try {

            File file = new File(FILE);
            boolean exists = file.exists();
            if( exists){
                List<String> readFile = Files.readAllLines(file.toPath());
                list.setAll(readFile);
                list_view.setItems(list);
            }
            else{
                ObservableList noContent= FXCollections.observableArrayList("No playlist found!");
                list_view.setItems(noContent);
            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

}