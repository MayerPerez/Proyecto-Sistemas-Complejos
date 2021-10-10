package complexsystemsproject;

public class Reglas {
    
    public static byte[] anilloSig(int regla,byte[] anilloAnt){
        byte [] anillo = new byte[anilloAnt.length];
        byte i= anilloAnt[anilloAnt.length-1];
        byte c = anilloAnt[0];
        byte d = anilloAnt[1];
        anillo[0] = evaluar(regla,i,c,d);
        i = anilloAnt[anilloAnt.length-2];;
        c = anilloAnt[anilloAnt.length-1];
        d = anilloAnt[0];
        anillo[anilloAnt.length-1] = evaluar(regla,i,c,d);
        for(int j=1;j<anilloAnt.length-1;j++){
            i = anilloAnt[j-1];;
            c = anilloAnt[j];
            d = anilloAnt[j+1];
            anillo[j] = evaluar(regla,i,c,d);
        }
        return anillo;
    }
    private static byte evaluar(int nr,byte i,byte c, byte d){
        switch(nr){
            case 15: return regla15(i,c,d);
            case 22: return regla22(i,c,d);
            case 30: return regla30(i,c,d);
            case 54: return regla54(i,c,d);
            case 110: return regla110(i,c,d);
            case 126: return regla126(i,c,d);
            default: return 0;
        }
    }
    
    private static byte regla15(byte i,byte c,byte d){
        if(i == 1 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 1 && d == 0){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 0){
            return 0;
        }
        else if(i == 0 && c == 1 && d == 1){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 0 && d == 1){
            return 1;
        }
        else{
            return 1;
        }
    }
    private static byte regla22(byte i,byte c,byte d){
        if(i == 1 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 1 && d == 0){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 0 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 0 && d == 1){
            return 1;
        }
        else{
            return 0;
        }
    }
    private static byte regla30(byte i,byte c,byte d){
        if(i == 1 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 1 && d == 0){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 1){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 0 && d == 1){
            return 1;
        }
        else{
            return 0;
        }
    }
    private static byte regla54(byte i,byte c,byte d){
        if(i == 1 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 1 && d == 0){
            return 0;
        }
        else if(i == 1 && c == 0 && d == 1){
            return 1;
        }
        else if(i == 1 && c == 0 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 0 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 0 && d == 1){
            return 1;
        }
        else{
            return 0;
        }
    }
    private static byte regla110(byte i,byte c,byte d){
        if(i == 1 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 1 && c == 0 && d == 1){
            return 1;
        }
        else if(i == 1 && c == 0 && d == 0){
            return 0;
        }
        else if(i == 0 && c == 1 && d == 1){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 0 && d == 1){
            return 1;
        }
        else{
            return 0;
        }
    }
    private static byte regla126(byte i,byte c,byte d){
        if(i == 1 && c == 1 && d == 1){
            return 0;
        }
        else if(i == 1 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 1 && c == 0 && d == 1){
            return 1;
        }
        else if(i == 1 && c == 0 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 1){
            return 1;
        }
        else if(i == 0 && c == 1 && d == 0){
            return 1;
        }
        else if(i == 0 && c == 0 && d == 1){
            return 1;
        }
        else{
            return 0;
        }
    }
}
