import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;


public class Train extends JFrame{
    public Marshall marshall; 
    public HashMap<Personne, ArrayList<JLabel>> infos;  
    public int turn = 0; 
    public static int NB_PLAYERS=1; 
    public static GameState state = GameState.Settings;  
    public HashMap<Personne, ArrayList<Action>> actions; 
    public static int NB_WAGONS; 
    private int count=0; 
    public ArrayList<Personne> personnes;
    public Locomotive first;
    public Wagon last; 
    public static int totalButins=0; 
    View view; 
    Controller controller; 
    public Train(){
        view = new View(this); 
        controller = new Controller(this, view);  
        infos = new HashMap<>();   
        actions = new HashMap<>();
        personnes = new ArrayList<>();  
    }

    public Personne Spawn(int i){
        Personne p = new Bandit(last, false, this);
        personnes.add(p);
        ArrayList<JLabel> temp = new ArrayList<>(); 
        JLabel name =new JLabel(((Bandit)p).name); 
        name.setBounds(100+200*i, 540, 200, 30); 
        view.add(name); 
        temp.add(name); 
        JLabel bullets = new JLabel("Bullets: "+p.NB_BALLES); 
        bullets.setBounds(100+200*i, 570, 200, 30); 
        view.add(bullets);
        JLabel values = new JLabel("Valeur des butins collectes: "+p.butinsValue); 
        values.setBounds(100+200*i, 600, 200, 30); 
        view.add(values);
        temp.add(bullets);
        temp.add(values);  
        infos.put(p, temp); 
        view.repaint(); 

        return p; 
    }
    
    public void performActions() {
        for(Personne p: actions.keySet()){
            try {actions.get(p).get(count).Activate();  } catch(Exception e){}
            infos.get(p).get(1).setText("Bullets: "+p.NB_BALLES);
            infos.get(p).get(2).setText("Valeur des butins collectes: "+p.butinsValue);
        }; 
        marshall.move(); 
        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        marshall.scare();
        
        count++; 
        if(count== Action.NB_ACTIONS) {
            for(Personne p: actions.keySet()){
                actions.replace(p, new ArrayList<>()); 
            } 
            state = GameState.Instructions; 
            count = 0;
        }
        if(Train.totalButins<=0){
            state = GameState.End;
            controller.end(); 
            Label l = new Label("Scores :"); 
            l.setBounds(400, 200, 200, 100);
            view.add(l);
            int i =0; 
            for(Personne p : personnes){
                if(! (p instanceof Marshall)){
                    l = new Label(((Bandit)p).name + " :" + p.butinsValue); 
                    l.setBounds(400, 300 +i*100, 200, 100); 
                    view.add(l);
                }
            }
        }
    }
    public String toString(){
        return "ur gay"; 
    }
    public void init(){ 
        first = new Locomotive(Train.NB_WAGONS, this);
        // removeAll(); 
        state = GameState.Instructions; 
        for(int i=0; i<Train.NB_PLAYERS; i++){
            Spawn(i); 
        }
        marshall = new Marshall(first, this);
    }

}
enum GameState {
    Instructions, 
    Action, 
    Settings, 
    End
}
