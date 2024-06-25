import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class MainPanel extends JPanel implements ActionListener{
    private Terrain terrain;
    private double x;
    private double y;
    private int cellSize;
    private int space;
    private Image wall;
    private Image key;
    private Image medKit; 
    private boolean pause =false;
    ResumeButton resumeButton;
    FenetreJeu fj; 
    RestartButton restartButton;
    // Action resume = new AbstractAction(){
    //     @Override
    //     public void actionPerformed(ActionEvent e){
    //         pause =false;
    //         remove(resumeButton);
    //         repaint();
    //         fj.resume();
        
    //     }
        
    // };
    public MainPanel(Terrain terrain, double yF, double xF, FenetreJeu fj){
        setFocusable(true);
        this.terrain=terrain;
        y=(yF/15*14);
        x=xF;
        cellSize=(int)(y/9);
        space =(int)((x-9*cellSize)/2);
        repaint();
        wall = new ImageIcon("Sprite-stone-wall4.png").getImage(); 
        key = new ImageIcon("Sprite-key-withoutBG-sparks.png").getImage();
        medKit= new ImageIcon("Sprite-AidKit.png").getImage();
        resumeButton = new ResumeButton((int)(x/10), (int)(y/10));
        restartButton= new RestartButton(50, 50);
        this.fj=fj;
        //resume.putValue(Action.MNEMONIC_KEY, 114);
    }
    public void paintComponent(Graphics g) {
        //Dimension d = getContentPane().
        Graphics2D g2D=(Graphics2D)g;
        super.paintComponent(g2D);
            for (int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    Case temp = terrain.getVisibleMap().get(i).get(j);
                    if (temp!=null&&temp instanceof Mur){
                        g2D.drawImage(wall, space+i*cellSize, j*cellSize, cellSize, cellSize, null);
                    }
                    else if(temp!=null){
                        g2D.setColor(temp.getColor()); 
                        g2D.fillRect(space+i*cellSize, j*cellSize, cellSize, cellSize);
                        if(temp instanceof Hall && temp.getKey()){
                            //g.setColor(new Color(255, 255, 0)); 
                            //g.fillOval(space+i*cellSize+cellSize/4, j*cellSize+cellSize/4, (int)(cellSize/2), (int)(cellSize/2));
                            g2D.drawImage(key, space+i*cellSize+cellSize/4, j*cellSize+cellSize/4, (int)(cellSize/2), (int)(cellSize/2), null ) ;
                        }
                        if(temp instanceof Hall&& ((Hall)temp).getMedKit()>0)
                        { 
                            g2D.drawImage(medKit, space+i*cellSize+cellSize/5, j*cellSize+cellSize/5, cellSize/5*3, cellSize/5*3, null);
                        }
                        if(temp instanceof CaseTraversable){
                            if(((CaseTraversable)temp).contientJoueur()) {
                                g2D.setColor(Color.CYAN); 
                                g2D.fillRect(space+i*cellSize+cellSize/4, j*cellSize+cellSize/4, cellSize/2, cellSize/2);
                            }
                        }
                    }
                    else {
                        g2D.setColor(Color.WHITE); 
                        g2D.fillRect(space+i*cellSize, j*cellSize, cellSize, cellSize);
                    }
                    
                }
            }
        if(pause){
            drawPauseMenu(g2D);
        }
    }
    private void drawPauseMenu(Graphics2D g2D){
        int windowX= (int)(space);
        int windowY =(int)(y/5);
        int windowWidth = (int)(x/5*3);
        int windowHeigth = (int)(y/5*3);
        drawSubWindow(g2D, windowX, windowY, windowWidth, windowHeigth, new Color(0, 0, 0, 75));
        add(resumeButton);
        resumeButton.setBounds((int)(x/20*9), (int)(y/20*9), (int)(x/10), (int)(y/10));
        resumeButton.repaint();
        resumeButton.addActionListener(this);
        resumeButton.setMnemonic(KeyEvent.VK_R);
        add(restartButton);
        restartButton.setBounds((int)(x/20*9), (int)(y/20*6), (int)(x/10), (int)(y/10));
        restartButton.repaint();
        restartButton.addActionListener(this);
        restartButton.setMnemonic(KeyEvent.VK_S);
        
        //resumeButton.setAction(resume);
        //resumeButton.setMnemonic('r');

    }
    private void drawSubWindow(Graphics2D g2D, int windowX,int windowY,int windowWidth,int windowHeigth, Color c){
        g2D.setColor(c);
        g2D.fillRect(windowX, windowY, windowWidth, windowHeigth);
    }
    public void setPause(boolean pause){
        this.pause = pause;
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resumeButton){
            pause =false;
            remove(resumeButton);
            remove(restartButton);
            repaint();
            fj.resume();
        }
        else if(e.getSource()==restartButton){
            pause=false;
            remove(resumeButton);
            remove(restartButton);
            repaint();
            fj.restart();
        }
    }

}
