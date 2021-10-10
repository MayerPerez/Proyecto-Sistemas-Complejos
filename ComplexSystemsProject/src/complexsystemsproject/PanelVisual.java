package complexsystemsproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;


public class PanelVisual {
    private JFrame ventana;
    private JFrame vSettings;
    private JPanel panelInfo;
    private JLabel nombreArch;
    private JButton play;
    private JButton stop;
    private JButton nextRing;
    private JButton zoomIn;
    private JButton zoomOut;
    private JButton settings;
    private JButton save;
    private JButton upload;
    private JButton captura;
    private JButton graficas;
    private JButton genEvo;
    private JLabel lblColorF;
    private JButton btnColorF;
    private JLabel lblColorL;
    private JButton btnColorL;
    private JLabel lblColorC;
    private JButton btnColorC;
    private JLabel lblRegla;
    private JLabel lblNumC;
    private JLabel lblNumAn;
    private JLabel numIteraciones;
    private JScrollPane scroll;
    private Anillos anillos;
    private Timer timer;
    
    private int nr;
    private int nc;
    private int na;
    private int nci;
    private int p1s;
    private int contA =0;
    private String nombreA = "Nombre Archivo";
    
    public PanelVisual(int Nc,int Na,int Nr,int Nci,int P1s){
        nc = Nc;
        na = Na;
        nr = Nr;
        nci = Nci;
        p1s = P1s;
        initAnillos();
        initComponents();
        initAux();
        intiEvents();
    }
    public PanelVisual(byte[] CondI,int Na, int Nr, String NomA){
        nc = CondI.length;
        na = Na;
        nr = Nr;
        nombreA = NomA;
        initAnillosLoad(CondI);
        initComponents();
        initAux();
        intiEvents();
    }
    private void initComponents(){
        ventana = new JFrame("Simulador");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(1350,700);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        
        panelInfo = new JPanel(null);
        nombreArch = new JLabel(nombreA);
        play = new JButton();
        stop = new JButton();
        nextRing = new JButton();
        zoomIn = new JButton();
        zoomOut = new JButton();
        settings = new JButton();
        captura = new JButton();
        save = new JButton();
        upload = new JButton();
        graficas = new JButton();
        genEvo = new JButton();
        lblColorF = new JLabel("Color de Fondo:");
        btnColorF = new JButton();
        lblColorL = new JLabel("Color de Lineas:");
        btnColorL = new JButton();
        lblColorC = new JLabel("Color de Celulas:");
        btnColorC = new JButton();
        lblRegla = new JLabel("Regla: "+nr);
        lblNumC = new JLabel("Numero de celulas Maximas: "+nc);
        lblNumAn = new JLabel("Numero de anillos Maximos: "+na);
        numIteraciones = new JLabel("Iteraciones: "+contA);
        
        //initAnillos();
        
        play.setIcon(new ImageIcon(new ImageIcon("play.png").getImage()
                .getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        stop.setIcon(new ImageIcon(new ImageIcon("stop.png").getImage()
                .getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        nextRing.setIcon(new ImageIcon(new ImageIcon("next.png").getImage()
                .getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        zoomIn.setIcon(new ImageIcon(new ImageIcon("zoom_in.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        zoomOut.setIcon(new ImageIcon(new ImageIcon("zoom_out.png").getImage()
                .getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        settings.setIcon(new ImageIcon(new ImageIcon("settings.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        save.setIcon(new ImageIcon(new ImageIcon("save.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        upload.setIcon(new ImageIcon(new ImageIcon("upload.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        captura.setIcon(new ImageIcon(new ImageIcon("captura.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        graficas.setIcon(new ImageIcon(new ImageIcon("grafica.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        genEvo.setIcon(new ImageIcon(new ImageIcon("genEvo.png").getImage()
                .getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        btnColorF.setBackground(Color.WHITE);
        btnColorL.setBackground(Color.BLACK);
        btnColorC.setBackground(Color.BLUE);
        
        play.setToolTipText("Iniciar Simulacion");
        stop.setToolTipText("Parar Simulación");
        nextRing.setToolTipText("Siguiente Anillo");
        zoomIn.setToolTipText("Aumentar tamaño");
        zoomOut.setToolTipText("Dismiir tamaño");
        settings.setToolTipText("Cambiar configuracion inicial o regla");
        save.setToolTipText("Guardar");
        upload.setToolTipText("Cargar");
        captura.setToolTipText("Tomar captura");
        graficas.setToolTipText("Graficas");
        genEvo.setToolTipText("Generar Evoluciones");
        btnColorF.setToolTipText("Cambiar color de fondo");
        btnColorL.setToolTipText("Cambiar color de lineas");
        btnColorC.setToolTipText("Cambiar color de celulas");
        
        panelInfo.setBounds(0, 0, 1350, 90);
        panelInfo.setBackground(Color.yellow);
        nombreArch.setBounds(575, 0, 200, 30);
        nombreArch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        play.setBounds(0, 30, 30, 30);
        stop.setBounds(30, 30, 30, 30);
        nextRing.setBounds(60, 30, 30, 30);
        zoomIn.setBounds(90, 30, 30, 30);
        zoomOut.setBounds(120, 30, 30, 30);
        settings.setBounds(150, 30, 30, 30);
        save.setBounds(180, 30, 30, 30);
        upload.setBounds(210, 30, 30, 30);
        captura.setBounds(240, 30, 30, 30);
        graficas.setBounds(270, 30, 30, 30);
        genEvo.setBounds(300, 30, 30, 30);
        lblColorF.setBounds(340, 30, 90, 30);//310
        btnColorF.setBounds(430, 30, 30, 30);//400
        lblColorL.setBounds(500, 30, 100, 30);//470
        btnColorL.setBounds(600, 30, 30, 30);//570
        lblColorC.setBounds(650, 30, 100, 30);//620
        btnColorC.setBounds(750, 30, 30, 30);//720
        lblRegla.setBounds(10, 60, 70, 30);
        lblNumC.setBounds(90, 60, 200, 30);
        lblNumAn.setBounds(300, 60, 200, 30);
        numIteraciones.setBounds(510, 60, 100, 30);
        scroll.setBounds(10, 100, 1325, 560);
        
        panelInfo.add(nombreArch);
        panelInfo.add(play);
        panelInfo.add(stop);
        panelInfo.add(nextRing);
        panelInfo.add(zoomIn);
        panelInfo.add(zoomOut);
        panelInfo.add(settings);
        panelInfo.add(save);
        panelInfo.add(upload);
        panelInfo.add(captura);
        panelInfo.add(graficas);
        panelInfo.add(genEvo);
        panelInfo.add(lblColorF);
        panelInfo.add(btnColorF);
        panelInfo.add(lblColorL);
        panelInfo.add(btnColorL);
        panelInfo.add(lblColorC);
        panelInfo.add(btnColorC);
        panelInfo.add(lblRegla);
        panelInfo.add(lblNumC);
        panelInfo.add(lblNumAn);
        panelInfo.add(numIteraciones);
        
        ventana.add(panelInfo);
        ventana.add(scroll);
        
        ventana.setVisible(true);
    }
    
    private void initAnillos(){
        scroll = new JScrollPane();
        anillos = new Anillos(nc,na);//--------------------Anillos
        scroll.setViewportView(anillos);
        anillos.definirRegla(nr);
        switch(nci){
                case 0: 
                    anillos.insertarCondInit(CondIniciales.unoCentral(nc));
                break;
                case 1: 
                    anillos.insertarCondInit(CondIniciales.condAleatoria(nc));
                break;
                case 2: 
                    anillos.insertarCondInit(CondIniciales.condPorcentaje1s(nc, p1s));
                break;
        }
    }
    
    private void initAnillosLoad(byte[] condIn){
        scroll = new JScrollPane();
        anillos = new Anillos(nc,na);//--------------------Anillos
        scroll.setViewportView(anillos);
        anillos.definirRegla(nr);
        anillos.insertarCondInit(condIn);
    }
    
    private void initAux(){
        timer = new Timer(50, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                anillos.nextRing();
                contA++;
                numIteraciones.setText("Iteraciones: "+contA);
                if(contA == na-1){
                    timer.stop();
                }
            }
        });
    }
    
    private void initSettings(){
        
        int h = 350;
        int w = 170;
        
        vSettings = new JFrame("Configuraciones");
        vSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vSettings.setSize(h,w);
        vSettings.setResizable(false);
        vSettings.setLocationRelativeTo(null);
        vSettings.setLayout(null);
        
        JPanel pSettings = new JPanel(null);
        JLabel Regla = new JLabel("Regla:");
        String reglas[]  = {"15","22","30","54","110","126"}; 
        JComboBox cb1 = new  JComboBox (reglas); 
        JLabel condI = new JLabel("Condicion Inicial:");
        String condiciones[] = {"Mantener condicion","Uno central","Condicion Aleatoria","Porcentaje de 1´s"};
        JComboBox cb2 = new  JComboBox (condiciones); 
        JLabel lblPorc1s = new JLabel("Porcentaje de 1´s:");;
        JTextField txfPorc1s = new JTextField();
        JLabel porc = new JLabel("%");
        JButton acep = new JButton("Aceptar");
        
        lblPorc1s.setVisible(false);
        txfPorc1s.setVisible(false);
        porc.setVisible(false);
        
        pSettings.setBounds(0, 0, h, w);
        Regla.setBounds(10, 15, 110, 20);
        cb1.setBounds(130, 15, 150, 20);
        condI.setBounds(10, 50, 110, 20);
        cb2.setBounds(130, 50, 150, 20);
        lblPorc1s.setBounds(10,85,110,20);
        txfPorc1s.setBounds(130,85,150,20);
        porc.setBounds(280, 85, 10, 20);
        acep.setBounds(135, 120, 80, 20);
        
        
        pSettings.add(Regla);
        pSettings.add(cb1);
        pSettings.add(condI);
        pSettings.add(cb2);
        pSettings.add(lblPorc1s);
        pSettings.add(txfPorc1s);
        pSettings.add(porc);
        pSettings.add(acep);
        
        vSettings.add(pSettings);
        
        cb2.addActionListener((ActionEvent event) -> {
            String ci = (String)cb2.getItemAt(cb2.getSelectedIndex ());
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
        
        acep.addActionListener((ActionEvent event) -> {
            int r = Integer.parseInt((String)cb1.getItemAt(cb1.getSelectedIndex ()));
            anillos.definirRegla(r);
            lblRegla.setText("Regla: "+r);
            contA = 0;
            numIteraciones.setText("Iteraciones: "+contA);
            String ci = (String)cb2.getItemAt(cb2.getSelectedIndex ());
            switch(ci){
                case "Mantener condicion" : 
                    anillos.mantenerCondInit();
                break;
                case "Uno central" : 
                    anillos.insertarCondInit(CondIniciales.unoCentral(nc));
                    nombreArch.setText("Archivo Nuevo");
                break;
                case "Condicion Aleatoria" : 
                    anillos.insertarCondInit(CondIniciales.condAleatoria(nc));
                    nombreArch.setText("Archivo Nuevo");
                break;
                case "Porcentaje de 1´s" : 
                    p1s = Integer.parseInt(txfPorc1s.getText());
                    anillos.insertarCondInit(CondIniciales.condPorcentaje1s(nc, p1s));
                    nombreArch.setText("Archivo Nuevo");
                break;
            }
            vSettings.dispose();
        });
        
        vSettings.setVisible(true);
    }
    
    private void intiEvents(){
        play.addActionListener((ActionEvent event) -> {
            if(contA != na-1){
                timer.start();
            }
        });
        stop.addActionListener((ActionEvent event) -> {
            timer.stop();
        });
        nextRing.addActionListener((ActionEvent event) -> {
            if(contA != na-1){
                anillos.nextRing();
                contA++;
                numIteraciones.setText("Iteraciones: "+contA);
            }
        });
        zoomIn.addActionListener((ActionEvent event) -> {
            anillos.aumentarTamaño();
        });
        zoomOut.addActionListener((ActionEvent event) -> {
            anillos.reducirTamaño();
        });
        settings.addActionListener((ActionEvent event) -> {
            initSettings();
        });
        save.addActionListener((ActionEvent event) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("ECA", "eca"); 
            fileChooser.setFileFilter(imgFilter);
            
            int result = fileChooser.showSaveDialog(panelInfo);
            if (result != JFileChooser.CANCEL_OPTION) {
                File file = fileChooser.getSelectedFile();
                try{
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(anillos.getCondInit());
                    out.writeObject(na);
                    out.writeObject(nr);
                    nombreArch.setText(file.getName());
                }
                catch(Exception e){
                    System.out.println("");
                }
            }
        });
        upload.addActionListener((ActionEvent event) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("ECA", "eca"); 
            fileChooser.setFileFilter(imgFilter);
            
            int result = fileChooser.showOpenDialog(panelInfo);
            if (result != JFileChooser.CANCEL_OPTION) {
                File file = fileChooser.getSelectedFile();
                try{
                    if (file.exists()) {
                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                        byte bytes[] = (byte[])in.readObject();
                        int num_a = (int)in.readObject();
                        int num_r = (int)in.readObject();
                        PanelVisual pv = new PanelVisual(bytes,num_a,num_r,file.getName());
                    }
                }
                catch(Exception e){
                    System.out.println("");
                }
            }
        });
        captura.addActionListener((ActionEvent event) -> {
            BufferedImage image = new BufferedImage(anillos.getWidth(), anillos.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();
                    anillos.printAll(g);
                    g.dispose();
                    try {
                        ImageIO.write(image, "jpg", new File("Paint.jpg"));
                        ImageIO.write(image, "png", new File("Paint.png"));
                    } catch (IOException exp) {
                        exp.printStackTrace();
                    }
        });
        graficas.addActionListener((ActionEvent event) -> {
            Graficas graf = new Graficas(anillos.getDensidad(),anillos.getMedia(),anillos.getVarianza());
        });
        genEvo.addActionListener((ActionEvent event) -> {
            Generador gen = new Generador();
        });
        btnColorF.addActionListener((ActionEvent event) -> {
            JColorChooser Selectorcolor=new JColorChooser();
            Color color=Selectorcolor.showDialog(null, "Seleccione un Color", Color.WHITE);
            if(color != null){
                btnColorF.setBackground(color);
                anillos.cambiarColorF(color);
            }
        });
        btnColorL.addActionListener((ActionEvent event) -> {
            JColorChooser Selectorcolor=new JColorChooser();
            Color color=Selectorcolor.showDialog(null, "Seleccione un Color", Color.BLACK);
            if(color != null){
                btnColorL.setBackground(color);
                anillos.cambiarColorL(color);
            }
        });
        btnColorC.addActionListener((ActionEvent event) -> {
            JColorChooser Selectorcolor=new JColorChooser();
            Color color=Selectorcolor.showDialog(null, "Seleccione un Color", Color.BLUE);
            if(color != null){
                btnColorC.setBackground(color);
                anillos.cambiarColorC(color);
            }
        });
    }
    
}
