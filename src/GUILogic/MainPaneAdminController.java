/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Perdoruesit;
import DAL.PerdoruesitRepository;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asdren
 */
public class MainPaneAdminController implements Initializable {
    @FXML
    private BorderPane bPane;
    @FXML
    private Text UserName_field;
    @FXML
    private Circle ProfilePic;
    
    PerdoruesitRepository pr = new PerdoruesitRepository();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadImage();
    }    

    public void loadImage(){
       try{
        Perdoruesit p = pr.findByOnline();
            File foto = new File(p.getFoto());
            UserName_field.setText(p.getEmri()+ " "+p.getMbiemri());
            Image im = new Image("file:"+foto.getAbsolutePath(),false);
            ProfilePic.setFill(new ImagePattern(im));
       }catch(Exception e){
                e.printStackTrace();
                }
       }
    
    @FXML
    public void KlientetButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/KlientetPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void HuazimetButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/HuazimetPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void LibratButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/LibratPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void RezervimetButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/RezervimetPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void StatistikatButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/StatistikatPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void PagesatButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/PagesatPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void PerdoruesitButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/PerdoruesitPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
    @FXML
    public void ReportABugButton(){
        Parent root = null; 
        try {
            root=FXMLLoader.load(getClass().getResource("/GUIView/ReportABugPane.fxml"));
        } catch (Exception e) {
        }
        bPane.setCenter(root);
    }
    
            
    @FXML
    public void LogoutButton(){
        try{
            
            List<Perdoruesit> perdoruesi = pr.findAll();
            
            for(Perdoruesit p : perdoruesi){
                p.setOnline(false);
                pr.edit(p);
            }
            
            
        Parent root = FXMLLoader.load(getClass().getResource("/GUIView/login.fxml"));
                Scene scene = new Scene(root);
        
                Stage stage = (Stage)((Node)bPane).getScene().getWindow();
                
//                stage.setMaximized(true);
                
                stage.setScene(scene);
                stage.setX(620);
                stage.setY(260);
                stage.show();
        }catch(Exception e){
            
        }
    }
    
    
    
}
