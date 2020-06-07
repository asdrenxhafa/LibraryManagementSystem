/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Huazimet;
import BLL.Klientet;
import DAL.HuazimetRepository;
import DAL.KlientetRepository;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
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
public class StatistikatPaneController implements Initializable {
    @FXML
    private Button btn_generate_raport;
    @FXML
    private BarChart<?,?> Klientet_Chart;
    @FXML
    private BarChart<?,?> Librat_Chart;

    KlientetRepository kr = new KlientetRepository();
    HuazimetRepository hr = new HuazimetRepository();
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadKlientetChart();
    }    
    
    
    @FXML
    private void GenerateRaport(MouseEvent event) {
        try{
       Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LibraryMS","DesktopUser","asdreni123");
       String reportPath = "C:\\Users\\Asdren\\JaspersoftWorkspace\\MyReports\\Reporti.jrxml";
       JasperDesign jdesign = JRXmlLoader.load(reportPath);
       String query = "select * from Klientet";
       
       JRDesignQuery updateQuery = new JRDesignQuery();
       updateQuery.setText(query);
       
       jdesign.setQuery(updateQuery);
       
       JasperReport jr = JasperCompileManager.compileReport(jdesign);
       
       JasperPrint jp =  JasperFillManager.fillReport(jr, null,con);
       
       
       
       JasperViewer.viewReport(jp);
       con.close();
}
 
catch(Exception ex){
    ex.printStackTrace();
}
        
    }
    
    
    public void loadKlientetChart(){
        XYChart.Series set = new XYChart.Series<>();
        try{
            
            HashMap<Klientet,Integer> topKlientet = findTopKlientet();
            
            
            for (Map.Entry<Klientet, Integer> entry : topKlientet.entrySet()) {
                set.getData().add(new XYChart.Data<>(entry.getKey().getEmri(), entry.getValue()));
            }
            
            
            
            
            Klientet_Chart.getData().addAll(set);
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    
    
    
    
    
    public HashMap<Klientet,Integer> findTopKlientet(){
        try{
            List<Huazimet> huazimet = hr.findAll();
            HashMap<Klientet,Integer> klientet = new HashMap<>();
            
            for(Huazimet h : huazimet){
                if(!klientet.containsKey(h.getKlientetID())){
                    klientet.put(h.getKlientetID(),1);
                }else{
                    klientet.replace(h.getKlientetID(),klientet.get(h.getKlientetID())+1);
                }
            }
            
            sortByValue(klientet);
            
            HashMap<Klientet,Integer> res = new HashMap<Klientet,Integer>();
            int i =0;
            for (Map.Entry<Klientet, Integer> entry : klientet.entrySet()) {
                i++;
                if(i>5){
                    break;
                }
                    res.put(entry.getKey(),entry.getValue());
                
            }
            
            return res;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    public static HashMap<Klientet, Integer> sortByValue(HashMap<Klientet, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Klientet, Integer> > list = 
               new LinkedList<Map.Entry<Klientet, Integer> >(hm.entrySet());
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Klientet, Integer> >() { 
            public int compare(Map.Entry<Klientet, Integer> o1,  
                               Map.Entry<Klientet, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<Klientet, Integer> temp = new LinkedHashMap<Klientet, Integer>(); 
        for (Map.Entry<Klientet, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
    
}
