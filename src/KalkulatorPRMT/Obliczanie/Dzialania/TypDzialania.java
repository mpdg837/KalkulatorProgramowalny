package KalkulatorPRMT.Obliczanie.Dzialania;

public class TypDzialania {

    String znakDzialania;
    int type;
    private int index;
    public TypDzialania(){

    }

    public void setIndex(int index){ this.index= index;}
    public int getIndex(){return this.index;}
    public int getIntegerType(){
        return type;
    }

    public String getZnakDzialania(){
        return znakDzialania;
    }
}
