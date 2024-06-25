import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreJeu extends JFrame{
    private Terrain terrain;
    private MainPanel mainPanel;
    private taskBar TASK;
    private boolean finished = false;  
    private JPanel levels; 
    private int selectedLevel;
    private PanelBar panelBar;
    private Dimension size;
    private double x;
    private double y;
    private RightPanel rightPanel;
    private boolean running = false;
    private boolean hasKeyListener=false;
    private GameKeyListener gameKeyListener; 
    // ArrayList< 

    public FenetreJeu() {
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        size = Toolkit.getDefaultToolkit().getScreenSize();
        x=size.getWidth();
        y=size.getHeight();
        setPreferredSize(size);
        //cellSize = (int)(5);
        setPreferredSize(size);
        gameKeyListener=new GameKeyListener(this);
        addKeyListener(gameKeyListener);
        gameKeyListener.setNull(true);
        setFocusable(true);
        TASK = new taskBar(); 
        TASK.start(); 
        running=false;
        startMenu();
        
    };
    public void Game(){
        terrain = new Terrain();
        //mainPanel.setPreferredSize(new Dimension(9 * tailleCase, 9 * tailleCase));
        terrain.setNewJoueur();
        if(levels!=null) remove(levels);
        //revalidate();
        
        setTitle("Furfeux");
        
        setLayout(null);
        mainPanel = new MainPanel(terrain, y,x, this);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        //getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        //frame.setLayout(null);
        
        //add(bar);
        
        
        //bar.setPreferredSize(new Dimension((int)((panelN.getSize().getWidth())*4/5), (int)((panelN.getSize().getWidth())*4/5)));
        // panelBar = new PanelBar(terrain.getJoueur().getResistance(), terrain.getJoueur().getKey(), x, y);
        panelBar = new PanelBar(this);

        //keyPanel.setPreferredSize(new Dimension((int)((panelN.getSize().getWidth())/5), (int)((panelN.getSize().getWidth())/5)));
        
        getContentPane().add(panelBar);
        panelBar.setBounds(0, 0, (int)x, (int)(y/15));
        
        //frame.setPreferredSize(new Dimension(largeur, hauteur));
        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, (int)(y/15), (int)((x-y/15*14)/2+y/15*14), (int)(y/15*14));
        rightPanel = new RightPanel((int)y, (int)x, 800, 0);
        getContentPane().add(rightPanel);
        rightPanel.setBounds((int)((x-y/15*14)/2+y/15*14), (int)(y/15), (int)(x-((x-y/15*14)/2+y/15*14)),(int)(y/15*14));
        pack();
        //for(KeyListener kl :getKeyListeners()) removeKeyListener(kl);
        // if(hasKeyListener){
        //     gameKeyListener.update(terrain, mainPanel);
        // } 
        // else{ 
        //     gameKeyListener= new GameKeyListener(this, terrain, mainPanel);
        //     //frame.setVisible(true);
        //     addKeyListener(gameKeyListener);
        // }
       //mainPanel.setFocusable(true);
        gameKeyListener.setNull(false);
        hasKeyListener=true;
        requestFocusInWindow();
        running=true;
        
        setVisible(true);
    }
    private class taskBar extends Thread{
        @Override
        public void run(){
            while(!finished){
                System.out.print("");
                while(running){
                    terrain.Feu();
                    terrain.getJoueur().loseResistance();
                    // panelBar.setHealth(terrain.getJoueur().getResistance()); 
                    // panelBar.setKeys(terrain.getJoueur().getKey());
                    // panelBar.repaint();
                    rightPanel.setHealth(terrain.getJoueur().getResistance()); 
                    rightPanel.setKeys(terrain.getJoueur().getKey());
                    rightPanel.setMedKits(terrain.getJoueur().getMedKits());
                    //rightPanel.useMedKit(terrain.getJoueur().getUsedMedKit());
                    rightPanel.repaint();
                    //terrain.getJoueur().setUsedMedKit();
                    mainPanel.repaint();
                    if(terrain.getJoueur().getResistance()==0){
                    ecranFinal(0); 
                    }
                    try {
                    Thread.sleep(100);
                    } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    return;
                    }
                    
                
                }   
                //break;
            }
            // ecranFinal(terrain.getJoueur().getResistance());
            return;
        }
    }
    


        
    //public void startScreen(){}

    public void ecranFinal(int n) {
        if(hasKeyListener) removeKeyListener(gameKeyListener);
        running=false;
        finished = true;
        remove(panelBar);
        remove(rightPanel);
        remove(mainPanel);
        getContentPane().removeAll();
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        getContentPane().add(label);
        repaint();
        setVisible(true);
    }
    public void startMenu(){
        // if(hasKeyListener) removeKeyListener(gameKeyListener);
        gameKeyListener.setNull(true);
        hasKeyListener=false;
        running=false;
        if (panelBar != null) remove(panelBar);
        if (mainPanel != null) remove(mainPanel);
        if (rightPanel != null) remove(rightPanel);
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        setTitle("Start Menu");
        //getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        //startButton= new JButton("Start Game"); 
        levels=new JPanel(); 
        levels.setLayout(new GridLayout(3,3));
        //add(startButton, BorderLayout.NORTH);
        //add(leaderBoard, BorderLayout.CENTER);
        //x=(int)(getSize().getWidth());
        //y=(int)(getSize().getHeight());
        levels.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 1; i <= 3; i++) {
            for(int j=1; j<=3; j++){
                JButton levelButton = new JButton("Level " + (i*j));
                levelButton.addActionListener(new LevelSelectionListener((i*j)));
                levelButton.setFont(new Font("Times New Roman", Font.ITALIC, 10));
                levels.add(levelButton);
                //levelButton.setPreferredSize(new Dimension(1, 1));
                //levelButton.setPreferredSize(new Dimension((int)(300/50), (int)(300/50)));
                JLabel label = new JLabel("Best score: ");
                //label.setPreferredSize(new Dimension(10, 10));
                //label.setVerticalAlignment(0);
                //label.setSize(new Dimension(5, 5));
                label.setFont(new Font("Times New Roman", Font.ITALIC, 10));
                levels.add(label);
                //System.out.println("working"+i +j);

            }
        }
        //setPreferredSize(new Dimension(9*36, 9*36));
        getContentPane().add(levels, BorderLayout.CENTER);
        /* startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add logic to start the game
            JOptionPane.showMessageDialog(StartMenu.this, "Game is starting!");
        }
        });*/
        //revalidate();
        repaint();
        pack();
        setVisible(true);


    }
    // public void paintComponent(Graphics g){
    //     Graphics2D g2D= (Graphics2D)g;
    //     super.paintComponents(g2D);
    //     drawPauseMenu(g2D);
    //     // new Color(64, 64, 64, 50)
    // }

    
    private class LevelSelectionListener implements ActionListener {
        private int level;
        public LevelSelectionListener(int level) {
            this.level = level;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Add logic to handle level selection
            JOptionPane.showMessageDialog(FenetreJeu.this, "Selected Level " + level);
            selectedLevel=level;
            Game();
        }
    }
    public void pause(){
        running=false;
        mainPanel.setPause(true);
        // removeKeyListener(gameKeyListener);
        gameKeyListener.setNull(true);
        hasKeyListener=false;

    }
    public void resume(){ 
        // gameKeyListener=new GameKeyListener(this, terrain, mainPanel);
        // for (KeyListener kl : getKeyListeners()){removeKeyListener(kl);}
        // addKeyListener(gameKeyListener);

        gameKeyListener.setNull(false);
        hasKeyListener=true;
        running =true;
    }
    public int getLevel(){
        return selectedLevel;
    }
    public Terrain getTerrain(){
        return terrain;
    }
    public Joueur getJoueur(){
        return terrain.getJoueur();
    }
    public void restart(){
        // if(hasKeyListener) removeKeyListener(gameKeyListener);
        // hasKeyListener=false;
        running=false;
        gameKeyListener.setNull(true);
        remove(mainPanel);
        remove(panelBar);
        remove(rightPanel);
        mainPanel=null;
        panelBar=null;
        rightPanel=null;
        terrain=null;
        getContentPane().removeAll();

        //removeKeyListener(gameKeyListener);
        Game();
    }
    public MainPanel getMainPanel(){
        return mainPanel;
    }
}
