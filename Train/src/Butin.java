import java.util.Random;

public class Butin {

    //ButinType type;
    public boolean isOnRoof;
    public int Price;
    ButinType type; 
    Wagon wagon; 
    public Butin(ButinType type, Wagon wagon){
        Train.totalButins++;
        isOnRoof = false;
        switch(type)
        {
            case Bourse: Price = new Random().nextInt(501); break;
            case Bijoux: Price = 500; break;
            case Magot: Price = 1000; break;
            default: Price = 0; break;
        }
        this.type = type; 
        this.wagon = wagon; 

    }
}

enum ButinType {
    Bourse,
    Bijoux,
    Magot
}