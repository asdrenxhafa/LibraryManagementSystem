/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogic;

import BLL.Huazimet;
import BLL.Klientet;
import BLL.Librat;
import BLL.Pagesat;
import DAL.HuazimetRepository;
import DAL.KlientetRepository;
import DAL.PagesatRepository;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    private BarChart<?,?> Klientet_Chart;
    @FXML
    private BarChart<?,?> Librat_Chart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Text KlientTeRi_field;
    @FXML
    private Text TotalHuazime_field;
    @FXML
    private Text TotalFitime_field;

    KlientetRepository kr = new KlientetRepository();
    HuazimetRepository hr = new HuazimetRepository();
    PagesatRepository pr = new PagesatRepository();
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadKlientetChart();
        loadLibratChart();
        setNumberStatistics();
    }    
    
    
    public void setNumberStatistics(){
        try{
        List<Klientet> klientet = kr.findAll();
        List<Huazimet> huazimet = hr.findAll();
        List<Pagesat> pagesat = pr.findAll();
        
        KlientTeRi_field.setText(((Integer)klientet.size()).toString());
        TotalHuazime_field.setText(((Integer)huazimet.size()).toString());
        
        int shumaRes = 0;
        for(Pagesat p : pagesat){
            shumaRes+=(int)p.getShumaPageses();
            
        }
        
        TotalFitime_field.setText(((Integer)shumaRes).toString()+" $");
        
        
        
        }catch(Exception e){
            
        }
    }
    
    
    @FXML
    private void GenerateRaport(MouseEvent event) {
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
                set.getData().add(new XYChart.Data<>(entry.getKey().getEmri()+" "+entry.getKey().getMbiemri(), entry.getValue()));
            }
            
            Collections.sort(set.getData(),new Comparator<XYChart.Data>() {

                @Override
                public int compare(XYChart.Data o1, XYChart.Data o2) {
                    Integer xValue1 = (Integer) o1.getYValue();
                    Integer xValue2 = (Integer) o2.getYValue();
                    return (xValue2).compareTo((xValue1));
                }
                });
            
            XYChart.Series top5Set = new XYChart.Series<>();
            
            if(set.getData().size()<5){
                top5Set=set;
            }else{
            for(int i=0;i<5;i++){
                top5Set.getData().add(set.getData().get(i));
            }
            }
            
            
            Klientet_Chart.getData().addAll(top5Set);
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    public HashMap<Klientet,Integer> findTopKlientet(){
        try{
            List<Huazimet> huazimet = hr.findAll();
            HashMap<Klientet,Integer> klientet = new HashMap<Klientet,Integer>();
            
            for(Huazimet h : huazimet){
                if(!klientet.containsKey(h.getKlientetID())){
                    klientet.put(h.getKlientetID(),1);
                }else{
                    klientet.replace(h.getKlientetID(),klientet.get(h.getKlientetID())+1);
                }
            }
            
            return klientet;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void loadLibratChart(){
        XYChart.Series set = new XYChart.Series<>();
        try{
            
            HashMap<Librat,Integer> topLibrat = findTopLibrat();
            
            
            for (Map.Entry<Librat, Integer> entry : topLibrat.entrySet()) {
                set.getData().add(new XYChart.Data<>(entry.getKey().getTitulli(), entry.getValue()));
            }
            
            Collections.sort(set.getData(),new Comparator<XYChart.Data>() {

                @Override
                public int compare(XYChart.Data o1, XYChart.Data o2) {
                    Integer xValue1 = (Integer) o1.getYValue();
                    Integer xValue2 = (Integer) o2.getYValue();
                    return (xValue2).compareTo((xValue1));
                }
                });
            
            XYChart.Series top5Set = new XYChart.Series<>();
            
            if(set.getData().size()<5){
                top5Set=set;
            }else{
            for(int i=0;i<5;i++){
                top5Set.getData().add(set.getData().get(i));
            }
            }
            
            
            Librat_Chart.getData().addAll(top5Set);
                    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public HashMap<Librat,Integer> findTopLibrat(){
        try{
            List<Huazimet> huazimet = hr.findAll();
            HashMap<Librat,Integer> librat = new HashMap<Librat,Integer>();
            
            for(Huazimet h : huazimet){
                if(!librat.containsKey(h.getLibratID())){
                    librat.put(h.getLibratID(),1);
                }else{
                    librat.replace(h.getLibratID(),librat.get(h.getLibratID())+1);
                }
            }
            
            return librat;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
}
