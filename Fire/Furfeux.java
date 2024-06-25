
import java.util.TimerTask;

import java.util.Timer; 

//import javax.swing.Timer;

public class Furfeux{

    // Terrain terrain;
    // Joueur joueur;
    FenetreJeu graphic;

    public Furfeux() {
        // this.terrain = new Terrain();
        // this.joueur = terrain.getJoueur();
        graphic= new FenetreJeu();
    }

    

    public boolean partieFinie() {
        //game end when exits
        /*if(this.joueur.getResistance() <= 0)
        {
            return true;
        }
        return false;*/
        if (graphic.getJoueur().getCase() instanceof Sortie) return true; 
        return false; 
    }
    
    public static void main(String[] args) {
        
        
        Furfeux jeu = new Furfeux();
        
    }
}
