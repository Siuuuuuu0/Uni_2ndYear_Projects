import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Joueur{
    private CaseTraversable c;
    private int resistance;
    private int cles;
    private ArrayList<Integer> medKits;
    //int usedMedKit;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
        medKits=new ArrayList<>();
        //usedMedKit=0;
    }

    public void bouge(Case cible) {
        c= (CaseTraversable)cible; 
        resistance-=(c).getChaleur(); 
        //System.out.println(resistance); 
        /*System.out.println("\n"+cible.lig); 
        System.out.println("\n"+cible.lig); */
    }
        /*else if (cible instanceof Porte && cles>0) {
                ((Porte)cible).setOpen();
                cles--; 
                
        }
        else{}*/

    
    public void loseResistance(){
        resistance-=c.getChaleur(); 
    }
    public int getResistance(){ 
        return resistance; 
    }
    public Case getCase(){
        return c; 
    }
    public void manageKey(int i){
        cles+=i; 
    }
    public int getKey(){
        return cles; 
    }
    public void gainResistance(int resistance){
        this.resistance+=resistance;
        if(this.resistance>800){
            this.resistance=800;
        }
    }
    public void addMedKit(int medKit){
        if(medKit>0){
            medKits.add(Integer.valueOf(medKit));
        }
    }
    public void useMedKit(){
        resistance+=medKits.get(medKits.size()-1);
        //usedMedKit=medKits.get((medKits.size()-1));
        medKits.remove(medKits.size()-1);
        if(resistance>800) resistance=800;
    }
    public int getMedKits(){
        return medKits.size();
    }
    // public int getUsedMedKit(){
    //     return usedMedKit;
    // }
    // public void setUsedMedKit(){
    //     usedMedKit=0;
    // }
    
}
