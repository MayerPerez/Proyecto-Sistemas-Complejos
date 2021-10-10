
package complexsystemsproject;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graficas {
    private JFrame ventana;
    private JPanel pnlP;
    private JRadioButton gDyM;
    private JRadioButton gVar;
    private ButtonGroup group;
    private JPanel pnl1;
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private ChartPanel panelG;
    private JButton btnDatosSig;
    private JButton btnDatosAnt;
    private JPanel pnl1Var;
    private DefaultCategoryDataset datasetVar;
    private JFreeChart chartVar;
    private ChartPanel panelGVar;
    private JButton btnDatosSigVar;
    private JButton btnDatosAntVar;
    private int limite = 15;//16
    private int limInf;
    private int limSup;
    private int limInfVar;
    private int limSupVar;
    private ArrayList<Integer> densidad;
    private ArrayList<Double> media;
    private ArrayList<Double> varianza;
    
    public Graficas(ArrayList<Integer> Densidad,ArrayList<Double> Media,ArrayList<Double> Varianza){
        densidad = Densidad;
        media = Media;
        varianza = Varianza;
        initComponents();
        initEvents();
    }
    
    private void initComponents(){
        ventana = new JFrame("Graficas");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(700,500);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        
        pnlP = new JPanel(null);
        gDyM = new JRadioButton("Densidad y Media");
        gVar = new JRadioButton("Varianza");
        group = new ButtonGroup();
        group.add(gDyM);
        group.add(gVar);
        gDyM.setSelected(true);
        
        pnl1 = new JPanel(null);
        btnDatosSig = new JButton("Datos Siguientes");
        btnDatosAnt = new JButton("Datos Anteriores");
        
        dataset = new DefaultCategoryDataset();
        if(densidad.size()<= limite){
            for(int i=0;i<densidad.size();i++){
                dataset.addValue(densidad.get(i),"densidad",String.valueOf(i));
                dataset.addValue(media.get(i),"promedio",String.valueOf(i));
            }
            btnDatosSig.setVisible(false);
        }
        else{
            for(int i=0;i<limite;i++){
                dataset.addValue(densidad.get(i),"densidad",String.valueOf(i));
                dataset.addValue(media.get(i),"promedio",String.valueOf(i));
            }
            limInf = 0;
            limSup = limite;
            btnDatosSig.setVisible(true);
        }
        chart = ChartFactory.createLineChart("Densidad y Media", "Anillo", "Valores", dataset,
                PlotOrientation.VERTICAL,true,true,false);
        panelG = new ChartPanel(chart);
        btnDatosAnt.setVisible(false);
        
        
        pnl1Var = new JPanel(null);
        btnDatosSigVar = new JButton("Datos Siguientes");
        btnDatosAntVar = new JButton("Datos Anteriores");
        
        datasetVar = new DefaultCategoryDataset();
        if(varianza.size()<= limite){
            for(int i=0;i<densidad.size();i++){
                datasetVar.addValue(varianza.get(i),"varianza",String.valueOf(i));
            }
            btnDatosSigVar.setVisible(false);
        }
        else{
            for(int i=0;i<limite;i++){
                datasetVar.addValue(varianza.get(i),"varianza",String.valueOf(i));
            }
            limInfVar = 0;
            limSupVar = limite;
            btnDatosSigVar.setVisible(true);
        }
        chartVar = ChartFactory.createLineChart("Varianza", "Anillo", "Varianza", datasetVar,
                PlotOrientation.VERTICAL,true,true,false);
        panelGVar = new ChartPanel(chartVar);
        
        
        btnDatosAntVar.setVisible(false);
        
        pnlP.setBounds(0,0,700,30);
        gDyM.setBounds(150,0,150,30);
        gVar.setBounds(400,0,150,30);
        panelG.setBounds(0,30,700,400);
        pnl1.setBounds(0,435,700,30);
        btnDatosAnt.setBounds(20,0,150,30);
        btnDatosSig.setBounds(530,0,150,30);
        panelGVar.setBounds(0,30,700,400);
        pnl1Var.setBounds(0,435,700,30);
        btnDatosAntVar.setBounds(20,0,150,30);
        btnDatosSigVar.setBounds(530,0,150,30);
        
        
        
        pnlP.add(gDyM);
        pnlP.add(gVar);
        pnl1.add(btnDatosSig);
        pnl1.add(btnDatosAnt);
        pnl1Var.add(btnDatosSigVar);
        pnl1Var.add(btnDatosAntVar);
        
        panelGVar.setVisible(false);
        pnl1Var.setVisible(false);
        
        ventana.add(pnlP);
        ventana.add(panelG);
        ventana.add(pnl1);
        ventana.add(panelGVar);
        ventana.add(pnl1Var);
        
        ventana.setVisible(true);
    }
    
    private void initEvents(){
        gDyM.addActionListener((ActionEvent event) -> {
            panelGVar.setVisible(false);
            pnl1Var.setVisible(false);
            panelG.setVisible(true);
            pnl1.setVisible(true);
        });
        gVar.addActionListener((ActionEvent event) -> {
            panelG.setVisible(false);
            pnl1.setVisible(false);
            panelGVar.setVisible(true);
            pnl1Var.setVisible(true);
        });
        btnDatosSig.addActionListener((ActionEvent event) -> {
            limInf+=limite;
            limSup+=limite;
            dataset.clear();
            if(limSup >= densidad.size()){
                for(int i=limInf;i<densidad.size();i++){
                    dataset.addValue(densidad.get(i),"densidad",String.valueOf(i));
                    dataset.addValue(media.get(i),"promedio",String.valueOf(i));
                }
                btnDatosSig.setVisible(false);
            }
            else{
                for(int i=limInf;i<limSup;i++){
                    dataset.addValue(densidad.get(i),"densidad",String.valueOf(i));
                    dataset.addValue(media.get(i),"promedio",String.valueOf(i));
                }
            }
            btnDatosAnt.setVisible(true);
        });
        btnDatosAnt.addActionListener((ActionEvent event) -> {
            limInf-=limite;
            limSup-=limite;
            dataset.clear();
            if(limInf <= 0){
                for(int i=0;i<limSup;i++){
                    dataset.addValue(densidad.get(i),"densidad",String.valueOf(i));
                    dataset.addValue(media.get(i),"promedio",String.valueOf(i));
                }
                btnDatosAnt.setVisible(false);
            }
            else{
                for(int i=limInf;i<limSup;i++){
                    dataset.addValue(densidad.get(i),"densidad",String.valueOf(i));
                    dataset.addValue(media.get(i),"promedio",String.valueOf(i));
                }
            }
            btnDatosSig.setVisible(true);
        });
        btnDatosSigVar.addActionListener((ActionEvent event) -> {
            limInfVar+=limite;
            limSupVar+=limite;
            datasetVar.clear();
            if(limSupVar >= varianza.size()){
                for(int i=limInfVar;i<varianza.size();i++){
                    datasetVar.addValue(varianza.get(i),"varianza",String.valueOf(i));
                }
                btnDatosSigVar.setVisible(false);
            }
            else{
                for(int i=limInfVar;i<limSupVar;i++){
                    datasetVar.addValue(varianza.get(i),"varianza",String.valueOf(i));
                }
            }
            btnDatosAntVar.setVisible(true);
        });
        btnDatosAntVar.addActionListener((ActionEvent event) -> {
            limInfVar-=limite;
            limSupVar-=limite;
            datasetVar.clear();
            if(limInfVar <= 0){
                for(int i=0;i<limSupVar;i++){
                    datasetVar.addValue(varianza.get(i),"varianza",String.valueOf(i));
                }
                btnDatosAntVar.setVisible(false);
            }
            else{
                for(int i=limInfVar;i<limSupVar;i++){
                    datasetVar.addValue(varianza.get(i),"varianza",String.valueOf(i));
                }
            }
            btnDatosSigVar.setVisible(true);
        });
    }
}
