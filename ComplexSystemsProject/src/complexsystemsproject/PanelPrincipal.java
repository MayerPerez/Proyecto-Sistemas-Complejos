package complexsystemsproject;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelPrincipal {
    
    private JFrame ventana;
    private JPanel pnlSup;
    private JPanel pnlMed1;
    private JPanel pnlMed2;
    private JPanel pnlInf;
    private JRadioButton rbtnCrear;
    private JRadioButton rbtnCargar;
    private ButtonGroup bg;
    private JLabel lblNumC;
    private JTextField txfNumC;
    private JLabel lblNumA;
    private JTextField txfNumA;
    private JLabel lblRegla;
    private final String reglas[]  = {"15","22","30","54","110","126"};
    private JComboBox cbR;
    private JLabel lblCondInic;
    private final String condiciones[] = {"Uno central","Condicion Aleatoria","Porcentaje de 1´s"};
    private JComboBox cbCI;
    private JLabel lblPorc1s;
    private JTextField txfPorc1s;
    private JLabel porc;
    private JLabel lblArch;
    private JTextField txfArch;
    private JButton btnSelecArch;
    private JButton btnAcep;
    
    private byte bytes[];
    private int num_a;
    private int num_r;
    private String nombreA;
    
    public PanelPrincipal(){
        initComponents();
        initEvents();
    }
    
    private void initComponents(){
        int w = 400;
        
        ventana = new JFrame("Principal");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(w,400);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        
        pnlSup = new JPanel(null);
        pnlMed1 = new JPanel(null);
        pnlMed2 = new JPanel(null);
        pnlInf = new JPanel(null);
        rbtnCrear = new JRadioButton("Crear simulacion.");
        rbtnCargar = new JRadioButton("Cargar simulacion.");
        bg = new ButtonGroup();
        bg.add(rbtnCrear);
        bg.add(rbtnCargar);
        lblNumC = new JLabel("Número de Celulas:");
        txfNumC = new JTextField();
        lblNumA = new JLabel("Número de Anillos:");
        txfNumA = new JTextField();
        lblRegla = new JLabel("Regla:");
        cbR = new JComboBox(reglas);
        lblCondInic = new JLabel("Condición Inicial:");
        cbCI = new JComboBox(condiciones);
        lblPorc1s = new JLabel("Porcentaje de 1´s:");
        txfPorc1s = new JTextField();
        porc = new JLabel("%");
        lblArch = new JLabel("Archivo:");
        txfArch = new JTextField("No hay archivo seleccionado.");
        btnSelecArch = new JButton();
        btnAcep = new JButton("Aceptar");
        
        pnlMed1.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlMed2.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlMed2.setVisible(false);
        rbtnCrear.setSelected(true);
        lblPorc1s.setVisible(false);
        txfPorc1s.setVisible(false);
        porc.setVisible(false);
        btnSelecArch.setIcon(new ImageIcon(new ImageIcon("folder.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        txfArch.setEditable(false);
        
        pnlSup.setBounds(0, 0, w, 70);
        rbtnCrear.setBounds(100, 10, 200, 20);
        rbtnCargar.setBounds(100, 40, 200, 20);
        pnlMed1.setBounds(0, 70, w, 260);
        lblNumC.setBounds(10, 20, 150, 20);
        txfNumC.setBounds(170, 20, 130, 20);
        lblNumA.setBounds(10, 60, 150, 20);
        txfNumA.setBounds(170, 60, 130, 20);
        lblRegla.setBounds(10, 100, 150, 20);
        cbR.setBounds(170, 100, 130, 20);
        lblCondInic.setBounds(10, 140, 150, 20);
        cbCI.setBounds(170, 140, 130, 20);
        lblPorc1s.setBounds(10,180,150,20);
        txfPorc1s.setBounds(170,180,130,20);
        porc.setBounds(300, 180, 10, 20);
        pnlMed2.setBounds(0, 70, w, 260);
        lblArch.setBounds(40, 120, 50, 30);
        txfArch.setBounds(100, 120, 190, 30);
        btnSelecArch.setBounds(295, 120, 30, 30);
        pnlInf.setBounds(0, 330, w, 45);
        btnAcep.setBounds(150, 12, 100, 20);
        
        pnlSup.add(rbtnCrear);
        pnlSup.add(rbtnCargar);
        pnlMed1.add(lblNumC);
        pnlMed1.add(txfNumC);
        pnlMed1.add(lblNumA);
        pnlMed1.add(txfNumA);
        pnlMed1.add(lblRegla);
        pnlMed1.add(cbR);
        pnlMed1.add(lblCondInic);
        pnlMed1.add(cbCI);
        pnlMed1.add(lblPorc1s);
        pnlMed1.add(txfPorc1s);
        pnlMed1.add(porc);
        pnlMed2.add(lblArch);
        pnlMed2.add(txfArch);
        pnlMed2.add(btnSelecArch);
        pnlInf.add(btnAcep);
        
        ventana.add(pnlSup);
        ventana.add(pnlMed1);
        ventana.add(pnlMed2);
        ventana.add(pnlInf);
        
        
        ventana.setVisible(true);
    }
    private void initEvents(){
        rbtnCrear.addActionListener((ActionEvent event) -> {
            pnlMed1.setVisible(true);
            pnlMed2.setVisible(false);
        });
        rbtnCargar.addActionListener((ActionEvent event) -> {
            pnlMed1.setVisible(false);
            pnlMed2.setVisible(true);
        });
        cbCI.addActionListener((ActionEvent event) -> {
            String ci = (String)cbCI.getItemAt(cbCI.getSelectedIndex ());
            if(ci.equals("Porcentaje de 1´s")){
                lblPorc1s.setVisible(true);
                txfPorc1s.setVisible(true);
                porc.setVisible(true);
            }
            else{
                lblPorc1s.setVisible(false);
                txfPorc1s.setVisible(false);
                porc.setVisible(false);
            }
        });
        btnSelecArch.addActionListener((ActionEvent event) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("ECA", "eca"); 
            fileChooser.setFileFilter(imgFilter);
            
            int result = fileChooser.showOpenDialog(ventana);
            if (result != JFileChooser.CANCEL_OPTION) {
                File file = fileChooser.getSelectedFile();
                
                try{
                    if (file.exists()) {
                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                        bytes = (byte[])in.readObject();
                        num_a = (int)in.readObject();
                        num_r = (int)in.readObject();
                        nombreA = file.getName();
                        txfArch.setText(nombreA);
                    }
                }
                catch(Exception e){
                    System.out.println("");
                }
            }
        });
        btnAcep.addActionListener((ActionEvent event) -> {
            if(rbtnCrear.isSelected()){
                try{
                    int nc = Integer.parseInt(txfNumC.getText());
                    int na = Integer.parseInt(txfNumA.getText());
                    int r = Integer.parseInt((String)cbR.getItemAt(cbR.getSelectedIndex ()));
                    String ci = (String)cbCI.getItemAt(cbCI.getSelectedIndex ());
                    int nci = 0,p1s = -1;
                    switch(ci){
                        case "Uno central" : nci = 0; break;
                        case "Condicion Aleatoria" : nci = 1; break;
                        case "Porcentaje de 1´s" : 
                            nci = 2; 
                            p1s = Integer.parseInt(txfPorc1s.getText());
                        break;
                    }
                    PanelVisual pv = new PanelVisual(nc,na,r,nci,p1s);
                    ventana.dispose();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(pnlMed1, "Dato introducido incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(rbtnCargar.isSelected()){
                PanelVisual pv = new PanelVisual(bytes,num_a,num_r,nombreA);
                ventana.dispose();
            }       
        });
    }
}
