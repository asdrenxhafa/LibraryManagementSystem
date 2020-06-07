/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Autoret;
import BLL.Librat;
import BLL.Rafti;
import DAL.AutoretRepository;
import DAL.LibraryException;
import DAL.LibratRepository;
import DAL.RaftiRepository;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asdren
 */
public class LibratPaneController implements Initializable {
    @FXML
    private BorderPane bPane;
    @FXML
    private TableView<Librat> table;
    @FXML
    private TableColumn<Librat, Integer> LibratID_col;
    @FXML
    private TableColumn<Librat, String> Titulli_col;
    @FXML
    private TableColumn<Librat, Rafti> RaftiID_col;
    @FXML
    private TableColumn<Librat, Collection<Autoret>> Autoret_col;
    @FXML
    private TableColumn<Librat, Boolean> ELire_col;
    @FXML
    private TextField Titulli_field;
    @FXML
    private TextField filterField;
    @FXML
    private ComboBox Rafti_comboBox;
    @FXML
    private ComboBox Autoret_comboBox;
    @FXML 
    private Pagination pagination;
    
    private ObservableList<Librat> oblist;
    
    private FilteredList<Librat> filteredList;
    
    private ObservableList<Rafti> comboBoxlist;
    
    private ObservableList<Autoret> AutoretcomboBoxlist;

    LibratRepository lr=new LibratRepository();
    RaftiRepository rr=new RaftiRepository();
    AutoretRepository ar = new AutoretRepository();
    
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
        loadRaftiComboBox();
        pagination.setPageFactory(this::createPage);
    }    
    
    //method to create page inside pagination view
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, oblist.size());
        table.setItems(FXCollections.observableArrayList(oblist.subList(fromIndex, toIndex)));
        
        return table;
    }
    
    
    
  public void loadRaftiComboBox(){
      try{
          List<Rafti> rafti= rr.findAll();
          comboBoxlist=FXCollections.observableArrayList(rafti);
          Rafti_comboBox.setItems(comboBoxlist);
      }catch(Exception e){
          
      }
  }
  
  public void loadAutoretComboBox(){
      try{
          List<Autoret> autoret= ar.findAll();
          AutoretcomboBoxlist=FXCollections.observableArrayList(autoret);
          Autoret_comboBox.setItems(AutoretcomboBoxlist);
      }catch(Exception e){
          
      }
  }
  
  @FXML
    public void AutoretComboBoxButton(){
        loadAutoretComboBox();
    }

    @FXML
    public void EditButton(){
        try{
            if(table.getSelectionModel().getSelectedIndex()==-1){
                throw new LibraryException("Nuk keni selektuar asgje per te edituar");
            }
        Librat k = table.getSelectionModel().getSelectedItem();
        Titulli_field.setText(k.getTitulli());
        Rafti_comboBox.getSelectionModel().select(k.getRaftiID());
        Autoret_comboBox.getSelectionModel().select(k.getAutoretCollection());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(e.toString());
            alert.show();
        }
    }
    
    @FXML
    public void shtoAutorButton(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/GUIView/ShtoAutorPane.fxml"));
                Scene scene = new Scene(root);
                
                
                Stage stage = new Stage();
                
               
                stage.setScene(scene);
                stage.show();
                
        }catch(Exception e){
        }
    }
    
    
    
    @FXML
    public void RuajButton(){
        try{
            int row = table.getSelectionModel().getSelectedIndex();
            if(row==-1){
            Librat k = new Librat();
        k.setTitulli(Titulli_field.getText());
        k.setRaftiID((Rafti)Rafti_comboBox.getSelectionModel().getSelectedItem());
        k.addAutoret((Autoret) Autoret_comboBox.getSelectionModel().getSelectedItem());
        k.setELire(true);
        if(!lr.findExists(k)){
        lr.create(k);
        }else{
            throw new LibraryException("Ky liber ekziston");
        }
            }else{
                Librat k = table.getSelectionModel().getSelectedItem();
        k.setTitulli(Titulli_field.getText());
        k.setRaftiID((Rafti)Rafti_comboBox.getSelectionModel().getSelectedItem());
        k.addAutoret((Autoret) Autoret_comboBox.getSelectionModel().getSelectedItem());
        if(lr.findExistsAutori((Autoret) Autoret_comboBox.getSelectionModel().getSelectedItem(), k)){
            throw new LibraryException("Nuk mund te ju bashkangjitet autori i njejt");
        }else{
        lr.edit(k);
        }
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
        Librat k = table.getSelectionModel().getSelectedItem();
        lr.delete(k);
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
    public void AnuloButton(){
        clear();
    }
    
    
    public void loadTable(){
        try{
            List<Librat> libra = lr.findAll();
            oblist=  FXCollections.observableArrayList(libra);
            LibratID_col.setCellValueFactory(new PropertyValueFactory<>("libratID"));
            Titulli_col.setCellValueFactory(new PropertyValueFactory<>("titulli"));
            RaftiID_col.setCellValueFactory(new PropertyValueFactory<>("raftiID"));
            Autoret_col.setCellValueFactory(new PropertyValueFactory<>("autoretCollection"));
            ELire_col.setCellValueFactory(new PropertyValueFactory<>("eLire"));
            table.setItems(oblist);
            table.refresh();
        }catch(Exception e){
            e.printStackTrace();
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
				
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (k.getTitulli().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
                                } else if(k.getRaftiID().toString().toLowerCase().contains(lowerCaseFilter)){
                                    return true;
                                }else
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
        SortedList<Librat> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredList.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
    
    
    
    @FXML
    public void clearTableSelection(){
        table.getSelectionModel().clearSelection();
    }
    
    
    public void clear(){
        Titulli_field.setText("");
        Rafti_comboBox.getSelectionModel().clearSelection();
        Autoret_comboBox.getSelectionModel().clearSelection();
        table.getSelectionModel().clearSelection();
    }
    
}
