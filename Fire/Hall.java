import java.awt.Color;

public class Hall extends CaseTraversable{
    private boolean cle =false ; 
    private int medKit;
    private int chaleur; 
    public Hall(int lig, int col){
        super(lig, col);
    }
    public Hall(int lig, int col, boolean cle){
        super(lig, col); this.cle= cle; 
    }
    public Hall(int lig, int col, int chaleur){
        super(lig, col); this.chaleur= chaleur; 
    }
    public Hall(int lig, int col, int chaleur, boolean cle){
        super(lig, col); this.chaleur= chaleur; this.cle=cle;
    }
    public boolean getKey(){
        return cle; 
    }
    public void addKit(int medKit){
        this.medKit = medKit;
    }
    public void retrieveMedKit(){
        medKit=0;
    }
    public int getMedKit(){
        return medKit;
    }
    /*public Color getColor(){
        return Color.LIGHT_GRAY; 
    }*/
    public Color getColor() { 
        switch (chaleur ) {
            case 0 : return Color.LIGHT_GRAY; 
            case 1:  return new Color(230, 0, 38) ; 
            case 2: return new Color(227, 0, 24); 
            case 3: return new Color(242, 0, 60); 
            case 4:return new Color( 217, 0, 76) ;
            case 5: return new Color(171, 75, 82);
            case 6: return new Color(164, 90, 82); 
            case 7 : return new Color(203, 65, 84); 
            case 8:  return new Color (156, 37, 66); 
            case 9: return new Color(132, 27, 47);  
            case 10: return new Color(78, 22, 9); 
            default: return Color.LIGHT_GRAY; 

        }
    }
    public void takeKey(){
        cle=false; 
    }
    public int getChaleur(){
        return chaleur; 
    }
    public void manageChaleur(int i){
        chaleur+=i;
        if(chaleur>10) chaleur=10; 
        else if(chaleur<0) chaleur=0;
        else return;  
    }
    public void setKey(boolean key){
        cle = key;
    }
    public String toString(){
        return new String("H");
    }
    
}