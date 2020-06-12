/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Perdoruesit;
import BLL.Roli;
import DAL.LibraryException;
import DAL.PerdoruesitRepository;
import DAL.RoliRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leu
 */
public class PerdoruesitPaneController implements Initializable {

    @FXML
    private TableView<Perdoruesit> table;
    @FXML
    private TableColumn<Perdoruesit, Integer> PerdoruesitID_col;
    @FXML
    private TableColumn<Perdoruesit, String> Emri_col;
    @FXML
    private TableColumn<Perdoruesit, String> Mbiemri_col;
    @FXML
    private TableColumn<Perdoruesit, String> Email_col;
    @FXML
    private TableColumn<Perdoruesit, String> Password_col;
    @FXML
    private TableColumn<Perdoruesit, Roli> RoliID_col;
    @FXML
    private TextField filterField;
    @FXML
    private TextField Emri_field;
    @FXML
    private TextField Mbiemri_field;
    @FXML
    private TextField Email_field;
    @FXML
    private TextField Password_field;
    @FXML
    private ComboBox RoliID_box;
    @FXML
    private TextField ProfilePic_field;
    @FXML
    private Pagination pagination;

    private ObservableList<Perdoruesit> oblist;
    
    private FilteredList<Perdoruesit> filteredList;
    
    private ObservableList<Roli> comboBoxlist;
    
    PerdoruesitRepository pr=new PerdoruesitRepository();
    RoliRepository rr = new RoliRepository();
    
    private final static int rowsPerPage = 25;
    private int dataSize;
    
//    RoliRepository rr = new RoliRepository();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        searchField();
        loadRoliIDcomboBox();
        pagination.setPageFactory(this::createPage);
    }
    
    //method to create page inside pagination view
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, oblist.size());
        table.setItems(FXCollections.observableArrayList(oblist.subList(fromIndex, toIndex)));
        
        return table;
    }

    public void loadRoliIDcomboBox(){
        try{
            List<Roli> rol =rr.findAll();
          comboBoxlist=FXCollections.observableArrayList(rol);
          RoliID_box.setItems(comboBoxlist);
                
        }catch(Exception e){
            
        }
    }
    
    @FXML
    public void AddPictureButton(){
        FileChooser fc = new FileChooser(); 
        
        String [] asd = {"*.jpg", "*.png"};
        
//        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", asd);
        
        
        fc.getExtensionFilters().add(imageFilter);
        
        Stage stage = new Stage();
        File file = fc.showOpenDialog(stage);
        if (file != null) {
                    ProfilePic_field.setText(file.getAbsolutePath()); 
                }
    }
    
    @FXML
    public void FshiFotoButton(){
        ProfilePic_field.setText(null);
    }
    
    @FXML
    private void EditButton() {
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te edituar");
            }
        Perdoruesit p = table.getSelectionModel().getSelectedItem();
        Emri_field.setText(p.getEmri());
        Mbiemri_field.setText(p.getMbiemri());
        Email_field.setText(p.getEmail());
        Password_field.setText(p.getPassword());
        ProfilePic_field.setText(p.getFoto());
        RoliID_box.getSelectionModel().select(p.getRoliID());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }

    @FXML
    private void DeleteButton() {
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1) throw new LibraryException("Nuk mund te fshihet asgje!"); 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmim i fshirjes");
        alert.setContentText("A jeni te sigurt qe doni ta fshini kete te dhene?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        Perdoruesit p = table.getSelectionModel().getSelectedItem();
        pr.delete(p);
        clear();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        loadTable();
        searchField();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
        
    }

    
    @FXML
    private void RuajButton() {
        try{
            int row = table.getSelectionModel().getSelectedIndex();
            String[] fields = {(Emri_field.getText()),(Mbiemri_field.getText()),(Email_field.getText())};
            for(String s : fields){
                if(s.trim().isEmpty() || s == null) throw new LibraryException("Njera prej fushave eshte e zbrazet, ju lutem rishikoni shkrimet e juaja!");                
            }
            if(!Email_field.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) 
                throw new LibraryException("Formati i E-mail eshte gabim. Ju lutem rishikoni perseri!");
            if(row==-1){
                File file = new File(ProfilePic_field.getText());
                Perdoruesit p = new Perdoruesit();
                p.setEmail(Email_field.getText());
                String hashedName = file.getName().substring(0,file.getName().lastIndexOf(".")).concat(p.getEmail()).hashCode()+"";
                String hashedFile = hashedName + file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
                copyFile(ProfilePic_field.getText(),new File("").getAbsolutePath()+"/src/Pictures/"+hashedFile);
                p.setEmri(Emri_field.getText());
                p.setMbiemri(Mbiemri_field.getText());
                p.setPassword(Password_field.getText());
                p.setRoliID((Roli)RoliID_box.getSelectionModel().getSelectedItem());
                p.setOnline(false);
                p.setFoto(new File("").getAbsolutePath()+"\\src\\Pictures\\"+hashedFile);
                if(!pr.findExists(p)){
                    pr.create(p);
                }else{
                    throw new LibraryException("Ky perdorues ekziston");
                }
            }else{
                File file = new File(ProfilePic_field.getText());
                Perdoruesit p = table.getSelectionModel().getSelectedItem();
                p.setEmail(Email_field.getText());
                String hashedName = file.getName().substring(0,file.getName().lastIndexOf(".")).concat(p.getEmail()).hashCode()+"";
                String hashedFile = hashedName + file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
                copyFile(ProfilePic_field.getText(),"C:\\.Users\\Asdren\\Documents\\NetBeansProjects\\LibraryManagementSystem\\src\\Pictures\\"+hashedFile);
                p.setEmri(Emri_field.getText());
                p.setMbiemri(Mbiemri_field.getText());
                p.setPassword(Password_field.getText());
                p.setFoto(new File("").getAbsolutePath()+"\\src\\Pictures\\"+hashedFile);
                pr.edit(p);
            }
        clear();
        loadTable();
        searchField();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }

    @FXML
    private void AnuloButton() {
        clear();
    }
    
    private String maskPass(String password) {
        String output = "";
        for(int i = 0; i < password.length(); i++) {
            output += "\u2022";
        }
        return output;
    }
    
    public void loadTable(){
        try{
            List<Perdoruesit> perdoruesi = pr.findAll();
            oblist=  FXCollections.observableArrayList(perdoruesi);
            PerdoruesitID_col.setCellValueFactory(new PropertyValueFactory<>("perdoruesitID"));
            Emri_col.setCellValueFactory(new PropertyValueFactory<>("emri"));
            Mbiemri_col.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
            Email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            Password_col.setCellValueFactory(new PropertyValueFactory<>("password"));
            RoliID_col.setCellValueFactory(new PropertyValueFactory<>("roliID"));

            table.setItems(oblist);
            table.refresh();
            
        }catch(Exception e){
            
        }
    }
    
    public void searchField(){
        try{
        // Wrap the ObservableList in a FilteredList (initially display all data).
        filteredList = new FilteredList<>(oblist, b -> true);
		
                int totalPage = (int) (Math.ceil(oblist.size() * 1.0 / rowsPerPage));
                pagination.setPageCount(totalPage);
        
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(k -> {
                            pagination.setPageCount((int) (Math.ceil(filteredList.size() * 1.0 / rowsPerPage)));
                            
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare Emri, Mbiemri and nrTel of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (k.getEmri().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Emrin.
				} else if (k.getMbiemri().toLowerCase().contains(lowerCaseFilter)) {
					return true;
                                }// Filter matches Mbiemrin.
				     else  
				    	 return false; // Does not match.
			});
		changeTableView(pagination.getCurrentPageIndex(),rowsPerPage);
		});
                
        pagination.setCurrentPageIndex(0);
        changeTableView(0, rowsPerPage);
        pagination.currentPageIndexProperty().addListener(
            (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), rowsPerPage));
        }catch(Exception e){
        }
    }
    
    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, oblist.size());

        int minIndex = Math.min(toIndex, filteredList.size());
        SortedList<Perdoruesit> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredList.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
    
    
    
     public void clear(){
        Emri_field.setText("");
        Mbiemri_field.setText("");
        Email_field.setText("");
        Password_field.setText("");
        RoliID_box.getSelectionModel().clearSelection();
        ProfilePic_field.setText(null);
        table.getSelectionModel().clearSelection();
    }
     
    @FXML
    private void clearTableSelection() {
        table.getSelectionModel().clearSelection();
    }
    
    public void copyFile(String s,String d){
        try{
        File sourceFile = new File(s);
    File destinationFile = new File(d);

    FileInputStream fileInputStream = new FileInputStream(sourceFile);
    FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

    int bufferSize;
    byte[] bufffer = new byte[512];
    while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
        fileOutputStream.write(bufffer, 0, bufferSize);
    }
    fileInputStream.close();
    fileOutputStream.close();
        }catch(Exception e){
            
        }
    }
    
}
