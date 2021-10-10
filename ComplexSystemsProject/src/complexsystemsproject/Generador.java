package complexsystemsproject;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Generador {
    private JFrame ventana;
    private JPanel pnlP;
    private JRadioButton r22;
    private JRadioButton r54;
    private ButtonGroup group;
    private JLabel lblNumC;
    private JTextField jtxfNumC;
    private JButton btnAcept;
    private String nombre;
    
    public Generador(){
        initComponents();
        initEvents();
    }
    
    private void initComponents(){
        ventana = new JFrame("Generador");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(300,200);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        
        pnlP = new JPanel(null);
        r22 = new JRadioButton("Regla 22");
        r54 = new JRadioButton("Regla 54");
        group = new ButtonGroup();
        lblNumC = new JLabel("Tamaño de la configuracion:");
        jtxfNumC = new JTextField();
        btnAcept = new JButton("Aceptar");
        
        group.add(r22);
        group.add(r54);
        r22.setSelected(true);
        
        pnlP.setBounds(0, 0, 300, 200);
        r22.setBounds(100, 20, 100, 20);
        r54.setBounds(100, 40, 100, 20);
        lblNumC.setBounds(10, 80, 170, 20);
        jtxfNumC.setBounds(190, 80, 50, 20);
        btnAcept.setBounds(110,120,80,20);
        
        pnlP.add(r22);
        pnlP.add(r54);
        pnlP.add(lblNumC);
        pnlP.add(jtxfNumC);
        pnlP.add(btnAcept);
        
        ventana.add(pnlP);
        ventana.setVisible(true);
    }
    
    private void initEvents(){
        btnAcept.addActionListener((ActionEvent event) -> {
            int nc = Integer.parseInt(jtxfNumC.getText());
            FileWriter fichero = null;
            PrintWriter pw = null;
            if(nc>=3 && nc<=31){
                int nr,nN;
                if(r22.isSelected()){
                    nr = 22;
                }
                else{
                    nr = 54;
                }
                byte anAux[];
                try{
                    nombre = "evolucionesR"+nr+"N_"+nc+".txt";
                    fichero = new FileWriter(nombre);
                    pw = new PrintWriter(fichero);
                    for(int i=0;i<Math.pow(2, nc);i++){
                        anAux = getBits(i,nc);
                        anAux = Reglas.anilloSig(nr, anAux);
                        nN = getInt(anAux);
                        pw.println(i+" --> "+nN);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                   try {
                   if (null != fichero)
                      fichero.close();
                   } catch (Exception e2) {
                      e2.printStackTrace();
                   }
                   JOptionPane.showMessageDialog(ventana, "El archivo se genero correctemente con el nombre \""+nombre+"\"",
                           "Generado Exitosamente", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(ventana, "El tamaño debe estar entre 3 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private byte[] getBits(int f,int nB){
        byte g[] = new byte[nB];
        int aux,nf = f;
        int cont = 0;
        for(int i =nB-1; i>=0;i--){
            aux = (int) Math.pow(2, i);
            if(nf >= aux){
                nf = nf - aux;
                g[cont] = 1;
            }
            else
                g[cont] = 0;
            cont ++;
        }
        return g;
    }
    private int getInt(byte g[]){
        int f = 0;
        int c=0;
        for(int i=g.length-1;i>=0;i--){
            if(g[i] == 1){
                f+=Math.pow(2, c);
            }
            c++;
        }
        return f;
    }
    
    
}

