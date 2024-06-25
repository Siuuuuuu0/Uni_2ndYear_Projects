import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class PanelBar extends JPanel implements ActionListener{
    JMenuItem exitMenu;
    JMenu helpMenu;
    JMenuItem pauseMenu;
    JMenuBar menu;
    FenetreJeu fj;
    public PanelBar(FenetreJeu fj){
        //ButtonAction button = new ButtonAction("Help", "This a help button");
        menu= new JMenuBar();
        //menu.setBounds(0, 0, 20, 20);
        exitMenu= new JMenuItem("Exit");
        // helpMenu = new JMenuItem(button);
        // helpMenu.getInputMap().put(KeyStroke.getKeyStroke('h'), "Click Me Button");
        // helpMenu.getActionMap().put("Click Me Button", button);
        pauseMenu = new JMenuItem("Pause");
        helpMenu=new JMenu("Help");
        exitMenu.addActionListener(this);
        pauseMenu.addActionListener(this);
        // pauseMenu.setMnemonic('P');
        // pauseMenu.setFocusable(true);
        //exitMenu.setMnemonic(KeyEvent.VK_E);
        menu.add(exitMenu);
        // exitMenu.setBounds(0, 0, 5, 10);
        menu.add(pauseMenu);
        // pauseMenu.setBounds(10, 0, 5, 10);
        menu.add(helpMenu);
        
        
        add(menu);
        this.fj=fj;
    }
    // public JMenu getExit(){
    //     return exitMenu;
    // }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==exitMenu){
            fj.startMenu();
        }
        else if(e.getSource()==pauseMenu){
            fj.pause();
        }
    }
    // class ButtonAction extends AbstractAction
    // {
    //     public ButtonAction(String text, String desc)
    //     {
    //         super(text);
    //         putValue(SHORT_DESCRIPTION, desc);
    //     }

    //     @Override
    //     public void actionPerformed(ActionEvent ae)
    //     {
    //         System.out.println("Pressed");
    //     }
    // }
    
    
}
