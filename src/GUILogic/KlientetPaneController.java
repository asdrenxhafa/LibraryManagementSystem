/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Huazimet;
import BLL.Klientet;
import BLL.Pagesat;
import DAL.KlientetRepository;
import DAL.LibraryException;
import DAL.PagesatRepository;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Asdren
 */
public class KlientetPaneController implements Initializable {
    @FXML
    private TableView<Klientet> table;
    @FXML
    private TableColumn<Klientet, Integer> KlientetID_col;
    @FXML
    private TableColumn<Klientet, String> Emri_col;
    @FXML
    private TableColumn<Klientet, String> Mbiemri_col;
    @FXML
    private TableColumn<Klientet, String> Email_col;
    @FXML
    private TableColumn<Klientet, String> NrTel_col;
    @FXML
    private TableColumn<Klientet, Boolean> Aktiv_col;
    @FXML
    private TextField filterField;
    @FXML
    private TextField Emri_field;
    @FXML
    private TextField Mbiemri_field;
    @FXML
    private TextField Email_field;
    @FXML
    private TextField NrTel_field;
    @FXML
    private Pagination pagination;
    @FXML
    private CheckBox aktiv_checkBox;
    
    private ObservableList<Klientet> oblist ;
    
    private FilteredList<Klientet> filteredList ;
    
    KlientetRepository kr = new KlientetRepository();
    
    PagesatRepository pr = new PagesatRepository();
    
    private final static int rowsPerPage = 25;
    private int dataSize;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        searchField();
        aktivBox();
        pagination.setPageFactory(this::createPage);
    }    
    
    //method to create page inside pagination view
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, oblist.size());
        table.setItems(FXCollections.observableArrayList(oblist.subList(fromIndex, toIndex)));
        
        return table;
    }
    

    @FXML
    public void EditButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te edituar");
            }
        Klientet k = table.getSelectionModel().getSelectedItem();
        Emri_field.setText(k.getEmri());
        Mbiemri_field.setText(k.getMbiemri());
        Email_field.setText(k.getEmail());
        NrTel_field.setText(k.getNumriTel());
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    @FXML
    public void RuajButton(){
        try{
            int row = table.getSelectionModel().getSelectedIndex();
            String[] fields = {(Emri_field.getText()),(Mbiemri_field.getText()),(Email_field.getText()),(NrTel_field.getText())};
            for(String s : fields){
                if(s.trim().isEmpty() || s == null) throw new LibraryException("Njera prej fushave eshte e zbrazet, ju lutem rishikoni shkrimet e juaja!");                
            }
            if(!Email_field.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) 
                throw new LibraryException("Formati i E-mail eshte gabim. Ju lutem rishikoni perseri!"); 
            if(!NrTel_field.getText().matches("[0-9]{9}")) 
                throw new LibraryException("Numri i telefonit duhet te kete 9 numra!");
            if(row==-1){
            Klientet k = new Klientet();
        k.setEmri(Emri_field.getText());
        k.setMbiemri(Mbiemri_field.getText());
        k.setEmail(Email_field.getText());
        k.setNumriTel(NrTel_field.getText());
        k.setAktiv(false);
        if(!kr.findExists(k)){
        kr.create(k);    
        }else{
            throw new LibraryException("Ky klient Ekziston");
        }
            }else{
                Klientet k = table.getSelectionModel().getSelectedItem();
        k.setEmri(Emri_field.getText());
        k.setMbiemri(Mbiemri_field.getText());
        k.setEmail(Email_field.getText());
        k.setNumriTel(NrTel_field.getText());
        k.setAktiv(false);
        kr.edit(k);
            }
        clear();
        loadTable();
        searchField();
        aktivBox();
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    
    @FXML
    public void DeleteButton(){
        try{
        if(table.getSelectionModel().getSelectedIndex()==-1) throw new LibraryException("Nuk mund te fshihet asgje!"); 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Konfirmim i fshirjes");
        alert.setContentText("A jeni te sigurt qe doni ta fshini kete te dhene?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Klientet k = table.getSelectionModel().getSelectedItem();
            kr.delete(k);
            clear();
            
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        loadTable();
            searchField();
            aktivBox();
        }catch(LibraryException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    @FXML
    private void GjeneroRaportButton(MouseEvent event) {
        try{
       Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LibraryMS","DesktopUser","asdreni123");
       String reportPath = new File("").getAbsolutePath()+"/src/Reports/Reporti.jrxml";
       JasperDesign jdesign = JRXmlLoader.load(reportPath);
       String query = "select * from Klientet";
       
       JRDesignQuery updateQuery = new JRDesignQuery();
       updateQuery.setText(query);
       
       jdesign.setQuery(updateQuery);
       
       JasperReport jr = JasperCompileManager.compileReport(jdesign);
       
       JasperPrint jp =  JasperFillManager.fillReport(jr, null,con);
       
       
       
       JasperViewer.viewReport(jp,false);
       con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    
    
    @FXML
    public void AnuloButton(){
        clear();
    }
    
    
    public void loadTable(){
        try{
            List<Klientet> klient = kr.findAll();
//            Makes a client active if he has a active payment
                List<Klientet> klientMePagesAktive = pr.findByKlient();
              for(Klientet k : klientMePagesAktive){
                    k.setAktiv(true);
                    kr.edit(k);
              }
              for(Klientet k :klient){
              if(!klientMePagesAktive.contains(k)){
                  k.setAktiv(false);
                  kr.edit(k);
              }    
              }
              
            oblist=  FXCollections.observableArrayList(klient);
            KlientetID_col.setCellValueFactory(new PropertyValueFactory<>("klientetID"));
            Emri_col.setCellValueFactory(new PropertyValueFactory<>("emri"));
            Mbiemri_col.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
            Email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            NrTel_col.setCellValueFactory(new PropertyValueFactory<>("numriTel"));
            Aktiv_col.setCellValueFactory(new PropertyValueFactory<>("aktiv"));
            
            
            table.setItems(oblist);
            table.refresh();
        }catch(Exception e){
            
        }
    }
    
    public void aktivBox(){
         try{
        // Wrap the ObservableList in a FilteredList (initially display all data).
        filteredList = new FilteredList<>(oblist, b -> true);
		
                int totalPage = (int) (Math.ceil(oblist.size() * 1.0 / rowsPerPage));
                pagination.setPageCount(totalPage);
                
		// 2. Set the filter Predicate whenever the filter changes.
		aktiv_checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    filteredList.setPredicate(k -> {
                        pagination.setPageCount((int) (Math.ceil(filteredList.size() * 1.0 / rowsPerPage)));
                        
                        if (!aktiv_checkBox.isSelected()) {
                            return true;
                        }else if(aktiv_checkBox.isSelected() ){
                            if( k.getAktiv())
                                return true;
                            else
                                return false;
                        }
                        
                        if (filterField.getText().trim().length() > 0) {
                            searchField();
                        }
                        return false;
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
				
                            

                                // If filter text is empty, display all huazimet.                            
                                if(newValue == null || newValue.isEmpty() && !aktiv_checkBox.isSelected()){
                                    return true;
                                } else if (newValue == null || newValue.isEmpty() && aktiv_checkBox.isSelected()) {
                                    if(k.getAktiv()){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
				
                                
				// Compare Emri, Mbiemri and nrTel of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
                                if(aktiv_checkBox.isSelected()){
                                    
				if (k.getEmri().toLowerCase().contains(lowerCaseFilter) && k.getAktiv()) {
					return true; // Filter matches Emrin.
				} 
                                else if (k.getMbiemri().toLowerCase().contains(lowerCaseFilter) && k.getAktiv()) {
					return true; // Filter matches Mbiemrin.
				} else if (k.getNumriTel().toLowerCase().contains(lowerCaseFilter) && k.getAktiv()){
				     return true; // Filter matches Numri tel
                                } 
                                        else
				    	 return false; // Does not match.
                                
                                
                                }else{
                                    if (k.getEmri().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Emrin.
				} 
                                else if (k.getMbiemri().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Mbiemrin.
				} else if (k.getNumriTel().toLowerCase().contains(lowerCaseFilter)){
				     return true; // Filter matches Numri tel
                                } 
                                        else
				    	 return false; // Does not match.
                                }
                                
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
        SortedList<Klientet> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredList.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
    
    
    @FXML
    public void clearTableSelection(){
        table.getSelectionModel().clearSelection();
    }
    
    
    public void clear(){
        Emri_field.setText("");
        Mbiemri_field.setText("");
        Email_field.setText("");
        NrTel_field.setText("");
        table.getSelectionModel().clearSelection();
    }
    
    public void makeActive(){
        try{
        //Makes a client active if he has a active payment
                List<Klientet> klientet = pr.findByKlient();
              for(Klientet k : klientet){
                  k.setAktiv(true);
                  kr.edit(k);
              }
        }catch(LibraryException e){
            
        }
        
    }
    
}
