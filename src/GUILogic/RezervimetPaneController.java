/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Klientet;
import BLL.Librat;
import BLL.Perdoruesit;
import BLL.Rezervimet;
import DAL.KlientetRepository;
import DAL.LibraryException;
import DAL.LibratRepository;
import DAL.RezervimetRepository;
import java.net.URL;
import java.util.Date;
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

/**
 * FXML Controller class
 *
 * @author Asdren
 */
public class RezervimetPaneController implements Initializable {
    @FXML
    private TableView<Rezervimet> table;
    @FXML
    private TableColumn<Rezervimet, Integer> RezervimetID_col;
    @FXML
    private TableColumn<Rezervimet, Date> DataRezervimit_col;
    @FXML
    private TableColumn<Rezervimet, Klientet> Klienti_col;
    @FXML
    private TableColumn<Rezervimet, Librat> Libri_col;
    @FXML
    private TableColumn<Rezervimet, Boolean> Aktiv_col;
    @FXML
    private TextField filterField;
    @FXML
    private ComboBox Klienti_comboBox;
    @FXML
    private ComboBox Libri_comboBox;
    @FXML
    private Pagination pagination;
            
    private ObservableList<Rezervimet> oblist ;
    
    private FilteredList<Rezervimet> filteredList ;
    
    private ObservableList<Klientet> comboBoxList ;
    
    private ObservableList<Librat> comboBoxList1 ;
    
    RezervimetRepository rr = new RezervimetRepository();
    KlientetRepository kr = new KlientetRepository();
    LibratRepository lr = new LibratRepository();
    
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
        loadKlientetComboBox();
        loadLibratComboBox();
        pagination.setPageFactory(this::createPage);
    }
    
    //method to create page inside pagination view
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, oblist.size());
        table.setItems(FXCollections.observableArrayList(oblist.subList(fromIndex, toIndex)));
        
        return table;
    }
    
    
    public void loadKlientetComboBox(){
        try{
            List<Klientet> klient = kr.findAll();
            comboBoxList=  FXCollections.observableArrayList(klient);
        Klienti_comboBox.setItems(comboBoxList);
        }catch(Exception e){
            
        }
    }
    
    public void loadLibratComboBox(){
        try{
            List<Librat> libra = lr.findAll();
            comboBoxList1=  FXCollections.observableArrayList(libra);
        Libri_comboBox.setItems(comboBoxList1);
        }catch(Exception e){
            
        }
    }
    
    @FXML
    public void EditButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te edituar");
            }
        Rezervimet k = table.getSelectionModel().getSelectedItem();
        Klienti_comboBox.getSelectionModel().select(k.getKlientetID());
        Libri_comboBox.getSelectionModel().select(k.getLibratID());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    @FXML
    public void RuajButton(){
        try{
            int row = table.getSelectionModel().getSelectedIndex();
                if(Klienti_comboBox.getSelectionModel().getSelectedIndex()==-1 || Libri_comboBox.getSelectionModel().getSelectedIndex()==-1) 
                    throw new LibraryException("Njera prej fushave eshte e zbrazet, ju lutem rishikoni shkrimet e juaja!");
            if(row==-1){
            Rezervimet k = new Rezervimet();
        k.setDRez(getDate());
        k.setKlientetID((Klientet)Klienti_comboBox.getSelectionModel().getSelectedItem());
        k.setLibratID((Librat)Libri_comboBox.getSelectionModel().getSelectedItem());
        k.setAktiv(true);
                if(k.getKlientetID().getAktiv()==true){
                    rr.create(k);
                }else{
                    throw new LibraryException("Klienti nuk eshte aktiv");
                }
            }else{
                Rezervimet k = table.getSelectionModel().getSelectedItem();
                if(k.getKlientetID().getAktiv()==true){
        k.setKlientetID((Klientet)Klienti_comboBox.getSelectionModel().getSelectedItem());
        k.setLibratID((Librat)Libri_comboBox.getSelectionModel().getSelectedItem());
        rr.edit(k);
                }else{
                    throw new LibraryException("Klienti nuk eshte aktiv");
                }
            }
        clear();
        loadTable();
        searchField();
        }catch(LibraryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    @FXML
    public void DeleteButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1) throw new LibraryException("Nuk mund te fshihet asgje!"); 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmim i fshirjes");
        alert.setContentText("A jeni te sigurt qe doni ta fshini kete te dhene?");
        Optional<ButtonType> result = alert.showAndWait();
        Rezervimet k = table.getSelectionModel().getSelectedItem();
        rr.delete(k);
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
    public void AnuloButton(){
        clear();
    }
    
    
    public void loadTable(){
        try{
            List<Rezervimet> rezervim = rr.findAll();
            oblist=  FXCollections.observableArrayList(rezervim);
            RezervimetID_col.setCellValueFactory(new PropertyValueFactory<>("rezervimetID"));
            DataRezervimit_col.setCellValueFactory(new PropertyValueFactory<>("dRez"));
            Klienti_col.setCellValueFactory(new PropertyValueFactory<>("klientetID"));
            Libri_col.setCellValueFactory(new PropertyValueFactory<>("libratID"));
            Aktiv_col.setCellValueFactory(new PropertyValueFactory<>("aktiv"));

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
				
				if (k.getKlientetID().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Klientin.
				} else if (k.getLibratID().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Librin.
				}   else  
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
        SortedList<Rezervimet> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredList.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
    
    
    @FXML
    public void clearTableSelection(){
        table.getSelectionModel().clearSelection();
    }
    
    public void clear(){
        Klienti_comboBox.getSelectionModel().clearSelection();
        Libri_comboBox.getSelectionModel().clearSelection();
        table.getSelectionModel().clearSelection();
    }
    
    public Date getDate(){
        Date date = new Date();
        return date;
    }
    
   
    
}
