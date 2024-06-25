import java.awt.Color; 
public class Porte extends CaseTraversable{
    boolean open =false ;  
    public Porte(int lig, int col, boolean open){
        super(lig, col); this.open = open; 
    }
    @Override
    public boolean estTraversable(){
        return open;
    }
    public void setOpen(){
        open = true; 
    }
    public Color getColor(){
        if (open) return new Color (0, 255, 0); 
        return Color.BLUE   ; 
    }
    public int getChaleur(){
        return chaleur; 
    }
    public String toString(){
        return new String("P");
    }
    public void manageChaleur(int i){
        chaleur+=i;
        if(chaleur>10) chaleur=10; 
        else if(chaleur<0) chaleur=0;
        else return;  
    }

}