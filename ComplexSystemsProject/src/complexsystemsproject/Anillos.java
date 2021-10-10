package complexsystemsproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Anillos extends JPanel{
        
        private List<Point> fillCells;
        private int num_ce;
        private int num_an;
        private int tam = 10;
        private int contAnillo = 0;
        private byte [] condIni;
        private byte [] aux;
        private int Regla;
        private Color colorF = Color.WHITE;
        private Color colorL = Color.BLACK;
        private Color colorC = Color.BLUE;
        
        private ArrayList<Integer> densidad = new ArrayList<>();
        private int contAux = 0;
        private ArrayList<Double> promedio = new ArrayList<>();
        private int sumaAux = 0;
        private double doubleAux;
        private ArrayList<Double> varianza = new ArrayList<>();
        private double dvAux;
        
        public Anillos(int nc,int na) {
            fillCells = new ArrayList<>();
            num_ce = nc;
            num_an = na;
            condIni = new byte[nc];
            
            this.setBackground(colorF);
            añadirEvento();
        }
        public Anillos(byte[] ci,int na) {
            fillCells = new ArrayList<>();
            num_ce = ci.length;
            num_an = na;
            condIni = ci;
            contAux = 0;
            for(int i=0;i<num_ce;i++){
                if(condIni[i] == 1){
                    fillCells.add(new Point(i, contAnillo));
                    contAux++;
                }
            }
            densidad.add(contAux);
            sumaAux = contAux;
            promedio.add((double)contAux);
            varianza.add(0.0);
            contAnillo++;
            repaint();
            
            this.setBackground(colorF);
            añadirEvento();
        }
        
        
        private void añadirEvento(){
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int cellY = (e.getY())/tam;
                    if(cellY == 0){
                        int cellX = (e.getX())/tam;
                        if(cellX < num_ce){
                            if(condIni[cellX] == 0){
                                fillCells.add(new Point(cellX, cellY));
                                condIni[cellX] = 1;
                            }
                            else{
                                fillCells.remove(new Point(cellX, cellY));
                                condIni[cellX] = 0;
                            }
                            repaint();
                        }
                    }
                }
            });
        }
        
        public byte[] getCondInit(){
            return condIni;
        }
        
        public void aumentarTamaño(){
            if(tam>=2 && tam<=14){
                tam+=3;
                repaint();
            }
        }
        public void reducirTamaño(){
            if(tam>=5 && tam<=17){
                tam-=3;
                repaint();
            }
        }
        public void insertarCondInit(byte [] condI){
            condIni = condI;
            aux = condI;
            contAnillo = 0;
            fillCells.clear();
            densidad.clear();
            contAux = 0;
            for(int i=0;i<num_ce;i++){
                if(condI[i] == 1){
                    fillCells.add(new Point(i, contAnillo));
                    contAux++;
                }
            }
            densidad.add(contAux);
            sumaAux = contAux;
            promedio.add((double)contAux);
            varianza.add(0.0);
            contAnillo++;
            repaint();
        }
        
        public void mantenerCondInit(){
            aux = condIni;
            fillCells.clear();
            contAnillo = 0;
            densidad.clear();
            contAux = 0;
            for(int i=0;i<num_ce;i++){
                if(condIni[i] == 1){
                    fillCells.add(new Point(i, contAnillo));
                    contAux++;
                }
            }
            densidad.add(contAux);
            sumaAux = contAux;
            promedio.add((double)contAux);
            varianza.add(0.0);
            contAnillo++;
            repaint();
        }
        
        public void definirRegla(int r){
            Regla = r;
        }
        
        public ArrayList<Integer> getDensidad(){
            return densidad;
        }
        public ArrayList<Double> getMedia(){
            return promedio;
        }
        public ArrayList<Double> getVarianza(){
            return varianza;
        }
        
        public void nextRing(){
            if(contAnillo < num_an){
                aux = Reglas.anilloSig(Regla, aux);
                contAux = 0;
                for(int i=0;i<num_ce;i++){
                    if(aux[i] == 1){
                        fillCells.add(new Point(i, contAnillo));
                        contAux++;
                    }
                }
                densidad.add(contAux);
                sumaAux += contAux;
                doubleAux = (double)sumaAux/densidad.size();
                promedio.add(doubleAux);
                dvAux = 0;
                for(int i=0;i<densidad.size();i++){
                    double daux1 = densidad.get(i)-doubleAux;
                    dvAux += Math.pow(daux1, 2);
                }
                dvAux = dvAux/densidad.size();
                varianza.add(dvAux);
                contAnillo++;
                repaint();
            }
        }
        
        public void cambiarColorF(Color cF){
            colorF = cF;
            this.setBackground(colorF);
        }
        
        public void cambiarColorL(Color cL){
            colorL = cL;
            repaint();
        }
        
        public void cambiarColorC(Color cC){
            colorC = cC;
            repaint();
        }
        
        @Override
        public LayoutManager getLayout(){
            return null;
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension((num_ce*tam)+1, (num_an*tam)+1);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Point fillCell : fillCells) {
                int cellX = (fillCell.x * tam);
                int cellY = (fillCell.y * tam);
                g.setColor(colorC);
                g.fillRect(cellX, cellY, tam, tam);
            }
            g.setColor(colorL);
            for (int i = 0; i <= num_ce*tam; i += tam) {
                g.drawLine(i, 0, i, num_an*tam);
            }
            for (int i = 0; i <= num_an*tam; i += tam) {
                g.drawLine(0, i, num_ce*tam, i);
            }
        }
        
    }
