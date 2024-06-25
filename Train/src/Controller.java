import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener; 

public class Controller {
    private Train train;
    private View view;  
    private JLabel turnLabel; 
    private JLabel actionsLabel; 
    //private MyPanel panel;
    private ArrayList<JComponent> components;
    public Controller(Train train, View view){ 
        components = new ArrayList<>(); 
        this.train = train; 
        this.view = view; 

        nbPlayers();
    }
    private void listener(){
        view.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) { 
                if(Train.state == GameState.Instructions){
                    if(e.getKeyCode()== KeyEvent.VK_RIGHT)
                        addAction(train.personnes.get(train.turn), ActionType.ShootF);
                    else if(e.getKeyCode()== KeyEvent.VK_LEFT)
                        addAction(train.personnes.get(train.turn), ActionType.ShootB);
                    else if(e.getKeyCode()== KeyEvent.VK_UP)
                        addAction(train.personnes.get(train.turn), ActionType.ShootU);
                    else if(e.getKeyCode()== KeyEvent.VK_DOWN)
                        addAction(train.personnes.get(train.turn), ActionType.ShootD);
                    else if(e.getKeyCode()== KeyEvent.VK_W)
                        addAction(train.personnes.get(train.turn), ActionType.Up);
                    else if(e.getKeyCode()== KeyEvent.VK_S)
                        addAction(train.personnes.get(train.turn), ActionType.Down);
                    else if(e.getKeyCode()== KeyEvent.VK_A)
                        addAction(train.personnes.get(train.turn), ActionType.Backwards);
                    else if(e.getKeyCode()== KeyEvent.VK_D)
                        addAction(train.personnes.get(train.turn), ActionType.Forward);
                    else if(e.getKeyCode()== KeyEvent.VK_C)
                        addAction(train.personnes.get(train.turn), ActionType.Collect);
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
            
        });

    }
    private void listener(JButton b){
        components.add(b); 
        b.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) { 
                if(Train.state == GameState.Instructions){
                    if(e.getKeyCode()== KeyEvent.VK_RIGHT)
                        addAction(train.personnes.get(train.turn), ActionType.ShootF);
                    else if(e.getKeyCode()== KeyEvent.VK_LEFT)
                        addAction(train.personnes.get(train.turn), ActionType.ShootB);
                    else if(e.getKeyCode()== KeyEvent.VK_UP)
                        addAction(train.personnes.get(train.turn), ActionType.ShootU);
                    else if(e.getKeyCode()== KeyEvent.VK_DOWN)
                        addAction(train.personnes.get(train.turn), ActionType.ShootD);
                    else if(e.getKeyCode()== KeyEvent.VK_W)
                        addAction(train.personnes.get(train.turn), ActionType.Up);
                    else if(e.getKeyCode()== KeyEvent.VK_S)
                        addAction(train.personnes.get(train.turn), ActionType.Down);
                    else if(e.getKeyCode()== KeyEvent.VK_A)
                        addAction(train.personnes.get(train.turn), ActionType.Backwards);
                    else if(e.getKeyCode()== KeyEvent.VK_D)
                        addAction(train.personnes.get(train.turn), ActionType.Forward);
                    else if(e.getKeyCode()== KeyEvent.VK_C)
                        addAction(train.personnes.get(train.turn), ActionType.Collect);
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
            
        });

    }
    private void buttons(){
        JButton b = new JButton("Action"); 
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Action) performActions(); 
            }
        });
        b.setBounds(850, 100, 100, 50); 
        listener(b);
        view.add(b); 
        
        JButton climb = new JButton("Monter");
        climb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.Up); 
            }
        });
        climb.setBounds(55, 50, 100, 50);  
        listener(climb);
        view.add(climb); 
        
        JButton descend = new JButton("Descendre");
        descend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.Down); 
            }
        });
        descend.setBounds(215, 50, 100, 50);  
        listener(descend);
        view.add(descend);
        
        JButton advance = new JButton("Retourner");
        advance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.Forward); 
            }
        });
        advance.setBounds(375, 50, 100, 50); 
        listener(advance); 
        view.add(advance);

        JButton return_ = new JButton("Avancer");
        return_.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.Backwards); 
            }
        });
        return_.setBounds(535, 50, 100, 50); 
        listener(return_);
        view.add(return_);

        JButton collect = new JButton("Collecter");
        collect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.Collect); 
            }
        });
        collect.setBounds(695, 50, 100, 50); 
        listener(collect); 
        view.add(collect); 
        JButton shootU = new JButton("Tirer en haut");
        shootU.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.ShootU); 
            }
        });
        shootU.setBounds(50, 150, 150, 50); 
        listener(shootU);
        view.add(shootU); 
        JButton shootD = new JButton("Tirer en dessous");
        shootD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.ShootD); 
            }
        });
        shootD.setBounds(250, 150, 150, 50); 
        listener(shootD);
        view.add(shootD); 
        JButton shootF = new JButton("Tirer en devant");
        shootF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.ShootF); 
            }
        });
        shootF.setBounds(450, 150, 150, 50); 
        listener(shootF);
        view.add(shootF); 
        JButton shootB = new JButton("Tirer en arriere");
        shootB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Train.state == GameState.Instructions) addAction(train.personnes.get(train.turn), ActionType.ShootB); 
            }
        });
        shootB.setBounds(650, 150, 150, 50); 
        listener(shootB);
        view.add(shootB); 
        JButton delete = new JButton("Delete"); 
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(Train.state == GameState.Instructions){
                    if(train.personnes.get(train.turn).given_actions!=0)
                        {
                            train.actions.get(train.personnes.get(train.turn)).remove(train.personnes.get(train.turn).given_actions-1);
                            train.personnes.get(train.turn).given_actions--;
                            String s = actionsLabel.getText();
                            actionsLabel.setText(matcher(s)); 
                            
                        }
                    }

            }
        });
        delete.setBounds(1000, 100, 100, 50); 
        listener(delete);
        view.add(delete); 

    }
    public void Spawn(int i){
        for(Personne p : train.personnes){
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
            train.infos.put(p, temp); 
            view.repaint(); 
        }
    }
    public void addAction(Personne p , ActionType type){
        if(p.given_actions!=Action.NB_ACTIONS){
            if(train.actions.containsKey(p))
                train.actions.get(p).add(new Action(p, type)); 
            
            else{
                ArrayList<Action> temp = new ArrayList<>(); 
                temp.add(new Action(p, type)); 
                train.actions.put(p, temp); 

            }
            actionsLabel.setText(actionsLabel.getText() + "->" +actionToString(type));
            
            view.repaint();
            p.given_actions++;
        } 

        
        // System.out.println(matcher(actionsLabel.getText()));
        
        if(p.given_actions == Action.NB_ACTIONS) {
            JButton confirm = new JButton("Confirm"); 
             
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    actionsLabel.setText("");
                    train.turn = (train.turn+1)%Train.NB_PLAYERS; 
                    turnLabel.setText("Player " + (train.turn+1));
                    p.given_actions=0; 
                    if(train.turn==0){
                        Train.state = GameState.Action;
                    }
                    view.remove(confirm);
                    view.repaint();
                }
            });
            confirm.setBounds(1150, 100, 100, 50);
            view.add(confirm); 
            view.repaint();
            // actionsLabel.setText("");
            //         turn = (turn+1)%NB_PLAYERS; 
            //         turnLabel.setText("Player " + (turn+1));
            //         p.given_actions=0; 
            //         if(turn==0){
            //             state = GameState.Action;
            //         }
            
        }
        
    }
    private void performActions() {
        train.performActions();
        view.repaint();
    }
    private void nbPlayers(){ 
        JLabel label = new JLabel("Choose nb of players"); 
        label.setBounds(200, 300, 200, 50);
        view.add(label); 
        JSlider players = new JSlider(JSlider.HORIZONTAL,
                                      1, 4, 1);
        players.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                Train.NB_PLAYERS = players.getValue(); 
            }
        });
        players.setBounds(250, 350, 100, 50);
        view.add(players); 


        JLabel label2 = new JLabel("Choose nb of wagons"); 
        label2.setBounds(400, 300, 200, 50); 
        view.add(label2); 
        JSlider wagons = new JSlider(JSlider.HORIZONTAL,
                                      2, 10, 2);
        wagons.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                Train.NB_WAGONS = wagons.getValue(); 
            }
        });
        wagons.setBounds(450, 350, 100, 50);
        view.add(wagons); 


        JLabel label3 = new JLabel("Choose nb of actions"); 
        label3.setBounds(600, 300, 200, 50);
        view.add(label3); 
        JSlider actions = new JSlider(JSlider.HORIZONTAL,
                                      1, 6, 1);
        actions.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                Action.NB_ACTIONS = actions.getValue(); 
            }
        });
        actions.setBounds(650, 350, 100, 50);
        view.add(actions); 


        JLabel label4 = new JLabel("Choose nb of bullets"); 
        label4.setBounds(800, 300, 200, 50);
        view.add(label4); 
        JSlider bullets= new JSlider(JSlider.HORIZONTAL,
                                      1, 20, 1);
        bullets.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                Personne.NB_BULLETS = bullets.getValue(); 
            }
        });
        bullets.setBounds(850, 350, 100, 50);
        view.add(bullets);


        JButton ok = new JButton("OK");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.remove(label);
                view.remove(players); 
                view.remove(wagons);
                view.remove(label2); 
                view.remove(label3);
                view.remove(actions);  
                view.remove(bullets); 
                view.remove(label4); 
                view.remove(ok);
                train.init();
                turnLabel = new JLabel("Player1"); 
                turnLabel.setBounds(100, 0, 100, 50); 
                actionsLabel = new JLabel(""); 
                actionsLabel.setBounds(200, 0, 600, 50); 
                view.add(turnLabel); 
                view.add(actionsLabel); 
                view.repaint(); 
                listener();
                buttons();
                
            }
        });
        ok.setBounds(300, 400, 100, 50); 
        view.add(ok); 
    }
    private String actionToString(ActionType t){
        switch(t){
            case Up : return new String("Monter"); 
            case Down : return new String("Descendre"); 
            case Forward : return new String("Retourner"); 
            case Backwards : return new String("Avancer"); 
            case Collect : return new String("Collecter"); 
            case ShootB: return new String("Tirer en arriere"); 
            case ShootF: return new String("Tirer devant");
            case ShootU: return new String("Tirer en haut");
            case ShootD: return new String("Tirer en dessous");
            default: return null; 
        }
    }
    private String matcher(String inputString){
        String regexPattern = "^(.*)->.*$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
    public String toString(){
        return "working"; 
    }
    public void end(){
        for(JComponent j :components) view.remove(j); 
    }

}
