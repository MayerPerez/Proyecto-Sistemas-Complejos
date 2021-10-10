package complexsystemsproject;

import java.util.Random;

public class CondIniciales {
    public static byte[] sinCondInicial(int nx){
        byte[] anillo = new byte[nx];
        for(int i=0;i<nx;i++){
            anillo[i] = 0;
        }
        return anillo;
    }
    
    public static byte[] unoCentral(int nx){
        byte[] anillo = new byte[nx];
        anillo[(nx/2)] = 1;
        return anillo;
    }
    
    public static byte[] condAleatoria(int nx){
        byte[] anillo = new byte[nx];
        Random rnd = new Random();
        for(int i=0;i<nx;i++){
            anillo[i] = (byte)rnd.nextInt(2);
        }
        return anillo;
    }
    
    public static byte[] condPorcentaje1s(int nx,int porcentaje){
        byte[] anillo = new byte[nx];
        int n1s = (int) (((float)porcentaje/100)*nx);
        Random rnd = new Random();
        boolean flag = false;
        for(int i=0;i<nx;i++){
            if(!flag){
                anillo[i] = (byte)rnd.nextInt(2);
                if(anillo[i] == 1){
                    n1s--;
                    flag = true;
                }
            }
            else
                anillo[i] = 0;
        }
        if(n1s != 0){
            int p;
            for(int i=0;i<n1s;i++){
                p = rnd.nextInt(nx);
                if(anillo[p] == 0){
                    anillo[p] = 1;
                }
                else
                    i--;
            }
        }
        return anillo;
    }
}
