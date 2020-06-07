/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Autoret;
import BLL.Librat;
import DAL.AutoretRepository;
import DAL.LibraryException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asdren
 */
public class ShtoAutorPaneController implements Initializable {
    @FXML
    private TextField Emri_field;
    @FXML
    private TextField Mbiemri_field;
    @FXML
    private Button ShtoButton;
    @FXML
    private Button AnuloButton;
    
    AutoretRepository ar = new AutoretRepository();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void AnuloButton(){
        clear();
    }
    
    @FXML
    public void RuajButton(){
        try{
            Autoret k = new Autoret();
        k.setEmri(Emri_field.getText());
        k.setMbiemri(Mbiemri_field.getText());
        if(!ar.findExists(k)){
            ar.create(k);
            Stage asd = (Stage)Emri_field.getScene().getWindow();
            asd.close();
        }else{
                    throw new LibraryException("Ky autor ekziston");
        }
    }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    
    public void clear(){
        Emri_field.setText(null);
        Mbiemri_field.setText(null);
    }
    
}
