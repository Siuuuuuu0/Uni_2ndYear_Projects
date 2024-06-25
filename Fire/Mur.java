import java.awt.*; 
public class Mur extends Case{
    public Mur(int lig, int col){
        super(lig, col);
    }
    
    public boolean estTraversable(){
        return false;
    }
    public Color getColor(){
        return Color.BLACK; 
    }
    public String toString(){
        return new String("M");
    }
}