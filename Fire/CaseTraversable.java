import java.awt.Color; 
public abstract class CaseTraversable extends Case{
    Joueur joueur; 
    int chaleur; 
    public CaseTraversable(int lig, int col){
        super(lig, col); chaleur =0; 
    }
    public boolean estTraversable(){
        return true;
    }
    public abstract int getChaleur(); 
    /*public Color getColor() { 
        switch (chaleur ) {
            case 0 : return new Color(255, 255, 255); 
            case 1: case 2: case 3: return new Color(255, 36, 0) ; 
            case 4:case 5: case 6: return new Color( 205, 92, 92) ;
            case 7: case 8: case 9: case 10: return new Color (124, 10, 2); 
            default: return null; 

        }
    }*/
    public void entre(Joueur j){
        joueur=j; 
    }
    public void sort(){
        joueur=null; 
    }
    public boolean contientJoueur(){
        if(joueur!=null) return true;
        return false; 
    }
    public abstract void manageChaleur(int i);
}