import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;
public class RestartButton extends JButton{
    int width; 
    int height;
    Image restart;
    public RestartButton(int width, int height){
        this.width=width;
        this.height=height;
        setOpaque(false);
        setBackground(new Color(0,0,0,0));
        restart = new ImageIcon("Restart-PNG-File.png").getImage();
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        // g2D.setColor(new Color(0, 0, 0, 100));
        // g2D.fillRect(0, 0, width, height);
        g2D.drawImage(restart, (int)(width/20), 0, height, height, null);
        g2D.setFont(new Font("Serif", Font.BOLD, 10));
        g2D.setColor(Color.BLACK);
        g2D.drawString("Restart", (int)(width/10+height), (int)(height/2));
    }
}
