import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sql.rowset.spi.SyncFactory;

public class GameKeyListener implements KeyListener{
    // Terrain terrain;
    // MainPanel mainPanel;
    FenetreJeu fj; 
    boolean isNull;
    public GameKeyListener(FenetreJeu fj){
        // this.terrain=terrain;
        // this.mainPanel=mainPanel;
        this.fj=fj;
    }
    @Override
    public void keyTyped(KeyEvent e){}; 
    @Override
    public void keyReleased(KeyEvent e){
        if(!isNull){
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //System.out.println("Running");
                fj.getTerrain().bougerJoueur(Direction.WEST); fj.getMainPanel().repaint(); 
                if (fj.getTerrain().getJoueur().getCase() instanceof Sortie) fj.ecranFinal(fj.getTerrain().getJoueur().getResistance());
                else return; 

            }  
            else if (e.getKeyCode() == KeyEvent.VK_UP){
                // System.out.println("Running");
                fj.getTerrain().bougerJoueur(Direction.NORTH);fj.getMainPanel().repaint();
                if (fj.getTerrain().getJoueur().getCase() instanceof Sortie) fj.ecranFinal(fj.getTerrain().getJoueur().getResistance());
                else return;   
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                fj.getTerrain().bougerJoueur(Direction.SOUTH); fj.getMainPanel().repaint();   
                if (fj.getTerrain().getJoueur().getCase() instanceof Sortie) fj.ecranFinal(fj.getTerrain().getJoueur().getResistance());
                else return; 
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                fj.getTerrain().bougerJoueur(Direction.EAST); fj.getMainPanel().repaint();   
                if (fj.getTerrain().getJoueur().getCase() instanceof Sortie) fj.ecranFinal(fj.getTerrain().getJoueur().getResistance());
                else return; 
            }
            else if(e.getKeyCode()==KeyEvent.VK_M){
                if(fj.getTerrain().getJoueur().getMedKits()>0) fj.getTerrain().getJoueur().useMedKit();
            }
            else return; 
        }
        else return;
        

    }; 
    @Override
    public void keyPressed(KeyEvent e){
    }; 
    // public void update(Terrain terrain, MainPanel mainPanel){
    //     this.terrain=terrain; 
    //     this.mainPanel=mainPanel;
    // }
    public void setNull(boolean isNull){
        this.isNull=isNull;
    }
}
