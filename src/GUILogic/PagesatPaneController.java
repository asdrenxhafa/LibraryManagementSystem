/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Klientet;
import BLL.Pagesat;
import DAL.KlientetRepository;
import DAL.LibraryException;
import DAL.PagesatRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
public class PagesatPaneController implements Initializable {
    @FXML
    private TableView<Pagesat> table;
    @FXML
    private TableColumn<Pagesat, Integer> PagesatID_col;
    @FXML
    private TableColumn<Pagesat, Klientet> Klienti_col;
    @FXML
    private TableColumn<Pagesat, Integer> shumaPageses_col;
    @FXML
    private TableColumn<Pagesat, Date> dataPageses_col;
    @FXML
    private TableColumn<Pagesat, Date> dataSkadimit_col;
    @FXML
    private TableColumn<Pagesat, Boolean> Aktiv_col;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField filterField;
    @FXML
    private ComboBox<Klientet> Klienti_comboBox;
    @FXML
    private ComboBox<Integer> Shuma_comboBox;
    
    private ObservableList<Pagesat> oblist = FXCollections.observableArrayList();
    
    private FilteredList<Pagesat> filteredList ;
    
    private ObservableList<Klientet> comboBoxList ;
    
    private ObservableList<Integer> comboBoxList1 ;
    
    PagesatRepository pr = new PagesatRepository();
    KlientetRepository kr = new KlientetRepository();
    private final static int rowsPerPage = 25;
    private int dataSize ;
    

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
        loadShumaComboBox();
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
    
    public void loadShumaComboBox(){
        try{
            List<Integer> shuma = new ArrayList<Integer>();
            shuma.add(5);
            shuma.add(10);
            shuma.add(25);
            comboBoxList1=  FXCollections.observableArrayList(shuma);
        Shuma_comboBox.setItems(comboBoxList1);
        }catch(Exception e){
            
        }
    }
    
    
    @FXML
    public void EditButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te edituar");
            }
        Pagesat k = table.getSelectionModel().getSelectedItem();
        Klienti_comboBox.getSelectionModel().select(k.getKlientetID());
        Shuma_comboBox.getSelectionModel().select(k.getShumaPageses());
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
                if(Klienti_comboBox.getSelectionModel().getSelectedIndex()==-1 || Shuma_comboBox.getSelectionModel().getSelectedIndex()==-1) 
                    throw new LibraryException("Njera prej fushave eshte e zbrazet, ju lutem rishikoni shkrimet e juaja!");
            if(row==-1){
            Pagesat k = new Pagesat();
        k.setShumaPageses(Shuma_comboBox.getSelectionModel().getSelectedItem());
        k.setKlientetID((Klientet)Klienti_comboBox.getSelectionModel().getSelectedItem());
        k.setDataPageses(getDate());
        k.setDataSkadimit(getDateSkadimi(Shuma_comboBox.getSelectionModel().getSelectedItem()));
        k.setAktiv(true);
        pr.create(k);
        k.getKlientetID().setAktiv(true);
        kr.edit(k.getKlientetID());
            }else{
                Pagesat k = table.getSelectionModel().getSelectedItem();
        k.setShumaPageses(Shuma_comboBox.getSelectionModel().getSelectedItem());
        k.setKlientetID((Klientet)Klienti_comboBox.getSelectionModel().getSelectedItem());
        k.setDataPageses(getDate());
        k.setDataSkadimit(getDateSkadimi(Shuma_comboBox.getSelectionModel().getSelectedItem()));
        pr.edit(k);
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
    public void DeleteButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1) throw new LibraryException("Nuk mund te fshihet asgje!"); 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmim i fshirjes");
        alert.setContentText("A jeni te sigurt qe doni ta fshini kete te dhene?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        Pagesat k = table.getSelectionModel().getSelectedItem();
        pr.delete(k);
        clear();
        }else{
            //kurgjo
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
    public void AnuloButton(){
        clear();
    }
    
    
    public void loadTable(){
        try{
            List<Pagesat> pages = pr.findAll();
            oblist=  FXCollections.observableArrayList(pages);
            
            dataSize=oblist.size();
            
            PagesatID_col.setCellValueFactory(new PropertyValueFactory<>("pagesatID"));
            Klienti_col.setCellValueFactory(new PropertyValueFactory<>("klientetID"));
            shumaPageses_col.setCellValueFactory(new PropertyValueFactory<>("shumaPageses"));
            dataPageses_col.setCellValueFactory(new PropertyValueFactory<>("dataPageses"));
            dataSkadimit_col.setCellValueFactory(new PropertyValueFactory<>("dataSkadimit"));
            Aktiv_col.setCellValueFactory(new PropertyValueFactory<>("aktiv"));

            //Makes payment active if it hasnt expired yet
            for(Pagesat p : pages){
                if(getDate().compareTo(p.getDataSkadimit())>0){
                    p.setAktiv(false);
                    pr.edit(p);
                }else{
                    p.setAktiv(true);
                    pr.edit(p);
                }
            }
            
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
				} else if (k.getShumaPageses().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Librin.
				}else if (k.getAktiv().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Librin.
				}
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
        SortedList<Pagesat> sortedData = new SortedList<>(
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
        Shuma_comboBox.getSelectionModel().clearSelection();
        table.getSelectionModel().clearSelection();
    }
    
    public Date getDate(){
        Date date = new Date();
        return date;
    }
    
    public Date getDate1(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }
    
    public Date getDateSkadimi(Integer shuma){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
	
        switch(shuma){
            case 5:
                cal.add(Calendar.MONTH, 1); 
                break;
            case 10:
                cal.add(Calendar.MONTH, 3); 
                break;
            case 25:
                cal.add(Calendar.MONTH, 6); 
                break;
                default:
                return null;    
        }
        return cal.getTime();
    }
    
}
