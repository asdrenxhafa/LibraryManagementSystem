/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Perdoruesit;
import DAL.JavaMailUtil;
import DAL.JavaMailUtilBug;
import DAL.PerdoruesitRepository;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Asdren
 */
public class ReportABugPaneController implements Initializable {
    @FXML
    private TextField Titulli_field;
    @FXML
    private RadioButton Low_CheckBox;
    @FXML
    private RadioButton Medium_CheckBox;
    @FXML
    private RadioButton High_CheckBox;
    @FXML
    private TextArea Permbajtja_field;
    @FXML 
    private ToggleGroup radioButtons;
    
    PerdoruesitRepository pr = new PerdoruesitRepository();

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
    public void dergoButton(){
        sendEmail();
    }
    
    public void sendEmail(){
        try{
            List<Perdoruesit> perdoruesi = pr.findAll();
            
            
            for(Perdoruesit p : perdoruesi){
                JavaMailUtilBug.sendEmail(p.getEmail(),Titulli_field.getText()+"\n","Niveli i problemit: "+getNiveliProblemit()+"\n"+"\n"+Permbajtja_field.getText());
                System.out.println("Email u dergua me sukses");
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getNiveliProblemit(){
        if(Low_CheckBox.isSelected()){
            return "Low";
        }else if(Medium_CheckBox.isSelected()){
            return "Medium";
        }else if(High_CheckBox.isSelected()){
            return "High";
        }
        return "";
    }
    
}
