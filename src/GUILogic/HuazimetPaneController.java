/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Huazimet;
import BLL.Klientet;
import BLL.Librat;
import DAL.HuazimetRepository;
import DAL.KlientetRepository;
import DAL.LibraryException;
import DAL.LibratRepository;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class HuazimetPaneController implements Initializable {
    @FXML
    private TableView<Huazimet> table;
    @FXML
    private TableColumn<Huazimet, Integer> HuazimetID_col;
    @FXML
    private TableColumn<Huazimet, Date> DataHuazimit_col;
    @FXML
    private TableColumn<Huazimet, Date> DataPritjes_col;
    @FXML
    private TableColumn<Huazimet, Date> DataKthimit_col;
    @FXML
    private TableColumn<Huazimet, Klientet> Klienti_col;
    @FXML
    private TableColumn<Huazimet, Librat> Libri_col;
    @FXML
    private TableColumn<Huazimet, Boolean> Aktiv_col;
    @FXML
    private TextField filterField;
    @FXML
    private ComboBox<Klientet> Klienti_comboBox;
    @FXML
    private ComboBox<Librat> Libri_comboBox;
    @FXML
    private Pagination pagination;
    
    private ObservableList<Huazimet> oblist ;
    
    private FilteredList<Huazimet> filteredList ;
    
    private ObservableList<Klientet> comboBoxList ;
    
    private ObservableList<Librat> comboBoxList1 ;
    
    HuazimetRepository hr = new HuazimetRepository();
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
            List<Librat> libra = lr.findELire();
            comboBoxList1=  FXCollections.observableArrayList(libra);
        Libri_comboBox.setItems(comboBoxList1);
        }catch(Exception e){
            
        }
    }
    
    
    @FXML
    public void LoadLibratButton(){
        loadLibratComboBox();
    }
    
    
    @FXML
    public void EditButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te edituar");
            }
        Huazimet k = table.getSelectionModel().getSelectedItem();
        Klienti_comboBox.getSelectionModel().select(k.getKlientetID());
        Libri_comboBox.getSelectionModel().select(k.getLibratID());
        k.getLibratID().setELire(true);
        Libri_comboBox.getSelectionModel().getSelectedItem().setELire(false);
                    lr.edit(k.getLibratID());
                    lr.edit(Libri_comboBox.getSelectionModel().getSelectedItem());
        
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
            Huazimet k = new Huazimet();
        k.setDataMarrjes(getDate());
        k.setDataPritjes(getDatePritjes());
        k.setDataKthimit(null);
        k.setKlientetID((Klientet)Klienti_comboBox.getSelectionModel().getSelectedItem());
        k.setLibratID((Librat)Libri_comboBox.getSelectionModel().getSelectedItem());
        k.setAktiv(true);
                if(k.getKlientetID().getAktiv()==true && k.getLibratID().getELire()==true){
                    hr.create(k);
                    k.getLibratID().setELire(false);
                    lr.edit(k.getLibratID());
                }else{
                    throw new LibraryException("Klienti nuk eshte aktiv ose libra nuk eshte e lire");
                    
                }
            }else{
                Huazimet k = table.getSelectionModel().getSelectedItem();
                if(k.getKlientetID().getAktiv()==true){
        k.setKlientetID((Klientet)Klienti_comboBox.getSelectionModel().getSelectedItem());
        k.setLibratID((Librat)Libri_comboBox.getSelectionModel().getSelectedItem());
        hr.edit(k);
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
    public void KtheButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te Kthyer");
            }
            int row = table.getSelectionModel().getSelectedIndex();
            if(!(row==-1)){
                Huazimet k = table.getSelectionModel().getSelectedItem();
                if(k.getAktiv()==true){
                k.setDataKthimit(getDate());
                k.setAktiv(false);
                hr.edit(k);
                k.getLibratID().setELire(true);
                    lr.edit(k.getLibratID());
                }else{
                    throw new LibraryException("Huazimi i selektuar smund te kthehet sepse eshte i kthyer");
                }
            }else{
                throw new LibraryException("Nuk keni selektuar asgje per te kthyer");
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
        Huazimet k = table.getSelectionModel().getSelectedItem();
        hr.delete(k);
        k.getLibratID().setELire(true);
                    lr.edit(k.getLibratID());
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
    private void GjeneroRaportButton(MouseEvent event) {
        try{
       Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LibraryMS","DesktopUser","asdreni123");
       String reportPath = new File("").getAbsolutePath()+"/src/Reports/HuazimiReport.jrxml";
       JasperDesign jdesign = JRXmlLoader.load(reportPath);
       String query = "SELECT TOP(20) * FROM huazimet ORDER BY Huazimet_ID DESC";
       
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
            List<Huazimet> huazim = hr.findAll();
            oblist=  FXCollections.observableArrayList(huazim);
            HuazimetID_col.setCellValueFactory(new PropertyValueFactory<>("huazimetID"));
            DataHuazimit_col.setCellValueFactory(new PropertyValueFactory<>("dataMarrjes"));
            DataPritjes_col.setCellValueFactory(new PropertyValueFactory<>("dataPritjes"));
            DataKthimit_col.setCellValueFactory(new PropertyValueFactory<>("dataKthimit"));
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
				// If filter text is empty, display all huazimet.
								
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
        SortedList<Huazimet> sortedData = new SortedList<>(
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
    
    public Date getDatePritjes(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 20);
        return cal.getTime();
    }
    
    public void dergoEmail(Klientet k){
        
    }
    
}
