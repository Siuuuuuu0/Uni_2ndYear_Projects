import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class View extends JFrame{
    private ArrayList <Image> bandits;
    private Image marshallImg; 
    private Image wallet; 
    private Image jewels; 
    private Image chest; 
    Train train; 
    //private Wagon last;
    public View(Train train){
        // requestFocus();
        this.train = train; 

        chest = new ImageIcon(getClass().getResource("chest.png")).getImage();
        wallet = new ImageIcon(getClass().getResource("wallet.png")).getImage();
        jewels = new ImageIcon(getClass().getResource("jewels.png")).getImage();
        bandits = new ArrayList<>();
        for(int i = 0; i<4; i++){
            bandits.add(new ImageIcon(getClass().getResource("Bandit"+(i+1)+".png")).getImage());
        }
        // bandit = new ImageIcon(getClass().getResource("Bandit1.png")).getImage();
        marshallImg = new ImageIcon(getClass().getResource("Sheriff.png")).getImage();  
        
        setSize(500, 500); 
        setLayout(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
        setFocusable(true);
        requestFocusInWindow();
        
        
    }
    public void paint(Graphics g){
        super.paint(g); 
        Graphics2D g2D = (Graphics2D)g; 
        if(Train.state == GameState.End){
            g.clearRect(0, 0, getWidth(), getHeight());
        }
        else if(Train.state !=GameState.Settings){
             
            // if(!paintedWagons) {
            //     paintWagons(g2D); 
            //     paintedWagons = true; } new jPanel needed
            paintWagons(g2D);
            paintButins(g2D);
            paintBandit(g2D);
             
            paintMarshall(g2D);
        }
        

    }
    
    private void paintWagons(Graphics2D g2D){
        g2D.setColor(new Color(10, 200, 10)); 
        g2D.fillRect(0, 300, 200, 120);
        for(int i =1; i<=Train.NB_WAGONS; i++){
            if(i==5){
                g2D.setColor(Color.BLUE); 
                g2D.fillRect(4*210, 450, 200, 120);
                g2D.setColor(Color.BLACK); 
                g2D.drawLine(4*210+100, 450,  4*210+100, 420);
            }
            else if (i>5){
                g2D.setColor(Color.BLUE); 
                g2D.fillRect((10-i)*210, 450, 200, 120);
                if(i!=Train.NB_WAGONS)
                {g2D.setColor(Color.BLACK); 
                g2D.drawLine((10-i)*210-10, 510,  (10-i)*210, 510);}
            }
            else{
                g2D.setColor(Color.BLUE); 
                g2D.fillRect(i*210, 300, 200, 120);
                g2D.setColor(Color.BLACK); 
                g2D.drawLine(i*210-10, 360,  i*210, 360);
            }
            
            
        }
    }
    private void paintBandit(Graphics2D g2D){
        int j =0; 
        for(Personne p : train.personnes){
            g2D.setColor(Color.GREEN); 
            if(p.wagon.idx<5){
                if(p.roof)
                    // g2D.fillRect(p.wagon.idx*60+5, 40, 10, 10); 
                    g2D.drawImage(bandits.get(p.idx), (p.wagon.idx+1)*210 -50 - j*30, 270, 30, 30,  null);
                else 
                    // g2D.fillRect(p.wagon.idx*60+5, 70, 10, 10);
                    g2D.drawImage(bandits.get(p.idx), (p.wagon.idx+1)*210 -50 - j*30, 390, 30, 30,  null);
            }
            else{
                if(p.roof)
                    // g2D.fillRect(p.wagon.idx*60+5, 40, 10, 10); 
                    g2D.drawImage(bandits.get(p.idx), (9-p.wagon.idx)*210 +10 + j*30, 420, 30, 30,  null);
                else 
                    // g2D.fillRect(p.wagon.idx*60+5, 70, 10, 10);
                    g2D.drawImage(bandits.get(p.idx), (9-p.wagon.idx)*210 +10 + j*30, 540, 30, 30,  null);
            }

            j++;
            
        }
    }
    private void paintButins(Graphics2D g2D){
        Wagon cur = train.first;
        Image tmp;
        while(cur!=null){
            int i =0; 
            for(Butin b: cur.butins){
                
                switch (b.type){
                    case Bijoux : tmp = jewels; break;
                    case Bourse:tmp = wallet; break;
                    case Magot : tmp = chest; break;
                    default: tmp = null;  break;
                }
                if(cur.idx<5){
                    if(b.isOnRoof)
                            // g2D.fillRect(p.wagon.idx*60+5, 40, 10, 10); 
                        g2D.drawImage(tmp, (cur.idx)*210  +5 +i*20, 280, 20, 20,  null);
                    else 
                            // g2D.fillRect(p.wagon.idx*60+5, 70, 10, 10);
                        g2D.drawImage(tmp, (cur.idx)*210 +5 +i*20, 400, 20, 20,  null);
                }
                else{
                    if(b.isOnRoof)
                            // g2D.fillRect(p.wagon.idx*60+5, 40, 10, 10); 
                        g2D.drawImage(tmp, (9-cur.idx+1)*210 -15 -20*i-20, 430, 20, 20,  null);
                    else 
                        // g2D.fillRect(p.wagon.idx*60+5, 70, 10, 10);
                        g2D.drawImage(tmp, (9-cur.idx+1)*210 -15 - i*20 - 20, 550, 20, 20,  null);
                }
                i++;
            }
            cur = cur.next; 
        } 
    }
    private void paintMarshall(Graphics2D g2D){
        if(train.marshall.wagon.idx<5){
            if(train.marshall.roof)
                    // g2D.fillRect(p.wagon.idx*60+5, 40, 10, 10); 
                g2D.drawImage(marshallImg, (train.marshall.wagon.idx+1)*210 -50 - 5*30, 270, 30, 30,  null);
            else 
                    // g2D.fillRect(p.wagon.idx*60+5, 70, 10, 10);
                g2D.drawImage(marshallImg, (train.marshall.wagon.idx+1)*210 -50 - 5*30, 390, 30, 30,  null);
        }
        else{
            if(train.marshall.roof)
                    // g2D.fillRect(p.wagon.idx*60+5, 40, 10, 10); 
                    g2D.drawImage(marshallImg, (9-train.marshall.wagon.idx)*210 +10 + 5*30, 420, 30, 30,  null);
                else 
                    // g2D.fillRect(p.wagon.idx*60+5, 70, 10, 10);
                    g2D.drawImage(marshallImg, (9-train.marshall.wagon.idx)*210 +10 + 5*30, 540, 30, 30,  null);
        }
    }


}