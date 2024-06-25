import java.awt.Color;

public class Sortie extends CaseTraversable{
    int chaleur;
    public Sortie(int lig, int col, int chaleur){
        super(lig, col); this.chaleur= chaleur; 
    }
    public boolean estTraversable(){return true; }
    public Color getColor(){
        return Color.YELLOW; 
    }
    public int getChaleur(){
        return 0; 
    }
    @Override
    public String toString(){
        return new String("S");
    }
    public void manageChaleur(int i){
        chaleur+=i;
        if(chaleur>10) chaleur=10; 
        else if(chaleur<0) chaleur=0;
        else return;  
    }
}