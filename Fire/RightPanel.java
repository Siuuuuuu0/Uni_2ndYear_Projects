import java.awt.*;
import java.util.ArrayList;
// import java.util.Timer;
// import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class RightPanel extends JPanel{
    int yF;
    int xF;
    private int health;
    private Image key; 
    
    private int keys;
    private double sizeBar;
    int xSize;
    int ySize;
    int medKits;
    private Image medKit;
    private ArrayList<Image> nums;
    //private int medKitValue;
    
    public RightPanel(int yF, int xF, int health, int keys){
        this.xF=xF;this.yF=yF;
        this.health=health; 
        this.keys=keys;
        key = new ImageIcon("Sprite-key-withoutBG-sparks.png").getImage();
        medKit = new ImageIcon("Sprite-AidKit.png").getImage(); 
        
        ySize=(int)(yF/15*14);
        xSize=(int)(xF-((xF-yF/15*14)/2+yF/15*14));
        nums = new ArrayList<>();
        for(int i =0; i<10; i++){
            // String s = new String("");
            // s+=("Sprite-Num"); s+=(String.valueOf(i)); s+=(".png");
            nums.add(new ImageIcon(new String("Sprite-Num"+String.valueOf(i)+".png")).getImage());
        }
        sizeBar= xSize/5*3;
        medKits=0;
        // keys=0;
        // health=800;
        //medKitValue=0;

        //setBackground(Color.GRAY);
        repaint();
    }
    public void paintComponent(Graphics g){
        Graphics2D g2D= (Graphics2D)g;
        super.paintComponent(g2D);
        g2D.setBackground(Color.GRAY);
        g2D.setColor(Color.BLACK);
        g2D.fillRect((int)(xSize/5), (int)(ySize/10), (int)(sizeBar), (int)(ySize/10));
        g2D.setColor(Color.RED);
        g2D.fillRect((int)(xSize/5), (int)(ySize/10), (int)(sizeBar/800*health), (int)(ySize/10));
        // if(medKitValue>0){
        //     g2D.setColor(Color.GRAY);
        //     g2D.setFont(new Font("Arial", Font.BOLD, 4));
        //     g2D.drawString(new String("+" +String.valueOf(medKitValue)), (int)(xSize/20*9), (int)(ySize/20*3));
        //     /*TimerTask task = new TimerTask(){
        //         public void run(){
        //             g2D.setColor(Color.BLACK);
        //             g2D.fillRect((int)(xSize/5), (int)(ySize/10), (int)(sizeBar), (int)(ySize/10));
        //             g2D.setColor(Color.RED);
        //             g2D.fillRect((int)(xSize/5), (int)(ySize/10), (int)(sizeBar/800*health), (int)(ySize/10));
        //         }
        //     };
        //     Timer timer = new Timer();
        //     timer.schedule(task, 1000);
        //     */medKitValue=0;
        // }
        if(keys>9){
            g2D.drawImage(nums.get(keys/10), 0, (int)(ySize/10*3), (int)(xSize/4), (int)(xSize/4), null);
        }
        
        g2D.drawImage(nums.get(keys%10), (int)(xSize/4), (int)(ySize/10*3), (int)(xSize/4), (int)(xSize/4), null);
        g2D.drawImage(key, (int)(xSize/2), (int)(ySize/10*3), (int)(xSize/4), (int)(xSize/4),  null); 
        g2D.setFont(new Font("MV Boli", Font.BOLD, (int)(xSize/10)));
        if(medKits>9){
            g2D.drawImage(nums.get(medKits/10), 0, (int)(ySize/5*3), (int)(xSize/4), (int)(xSize/4), null);
        }
        g2D.drawImage(nums.get(medKits%10), (int)(xSize/4), (int)(ySize/5*3), (int)(xSize/4), (int)(xSize/4), null);
        g2D.drawImage(medKit, (int)(xSize/2), (int)(ySize/5*3), (int)(xSize/4), (int)(xSize/4),  null);

    
        
    }
    
    public void setHealth(int health){
        this.health=health;

    }
    public void setKeys(int keys){
        this.keys=keys;
        

    }
    public void setMedKits(int medKits){
        this.medKits=medKits;
        
    }
    // public void useMedKit(int medKitValue){
    //     this.medKitValue=medKitValue;
    // }
}
