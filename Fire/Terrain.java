
import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

import java.lang.Math;

public class Terrain {
    private Field field;
    private Room[][] rooms;
    private Joueur joueur;
    int height = 6;
    int width =6;
    
    int spawnX=2; 
    int spawnY = 2;
    ArrayList<ArrayList<Case>> cases ; 
    public Terrain (){
        field = new Field(width, height, spawnX, spawnY);
        rooms = field.getField();
        createCases();
    }
    public void setNewJoueur(){
        joueur = new Joueur((CaseTraversable)cases.get(spawnY*8+3).get(spawnX*8+3), 800, 0);
        ((CaseTraversable)cases.get(spawnY*8+3).get(spawnX*8+3)).entre(joueur);
    }
    private void createCases(){
        
        cases = new ArrayList<>(); 
        ArrayList<ArrayList<ArrayList<Case>>> tempCases = new ArrayList<>(Collections.nCopies(width, null)); 
        for(int i =0; i<height; i++){
            for(int j=0; j<width; j++){
                tempCases.set(j, rooms[i][j].toArrayList());
            }
            for(int j =0; j<8; j++){
                ArrayList<Case> tempArr = new ArrayList<>();
                for(int jj= 0; jj<width; jj++){
                    tempArr.addAll(tempCases.get(jj).get(j));
                }
                cases.add(tempArr);
            }
        }
        // field.printField();
        // System.out.println("");
        // for(ArrayList<Case> ele : cases){
        //     for(Case c: ele){
        //         try{
        //             System.out.print(c.toString());
        //         }
        //         catch(Exception e){
        //             System.out.print(" ");
        //         }
        //     }
        //     System.out.println("");
        // }
        
    }
    
    public void Feu(){
        
        for(int i=0; i<height*8; i++){
            for(int j=0; j<width*8; j++){
                int res=0;
                for(int ii=i-1; ii<i+1; ii++){
                    for(int jj=j-1; jj<j+1; jj++){
                        try{
                            res+= ((CaseTraversable)cases.get(ii).get(jj)).getChaleur();
                        }
                        catch (Exception e){}
                    }
                }
                try{
                    Random rand =new Random(); 
                    int n= rand.nextInt(200); 
                    if(n<res) ((CaseTraversable)cases.get(i).get(j)).manageChaleur(1);
                    else if(n>190) ((CaseTraversable)cases.get(i).get(j)).manageChaleur(-1);
                    else {}
                }catch (Exception e){}

            }
        }
        //Fire for doors
    }
    public Joueur getJoueur() { return this.joueur; }

    /*public ArrayList<CaseTraversable> getVoisinesTraversables(int lig, int col) {
        /* À compléter 
    }*/

    public ArrayList<ArrayList<Case>> getVisibleMap(){
        Case temp = joueur.getCase();
        ArrayList<ArrayList<Case>> res = new ArrayList<>() ;  
        for(int i= temp.lig -4; i<=temp.lig+4; i++){
            ArrayList<Case> temp2 =new ArrayList<>(); 
            for(int j =temp.col -4; j<=temp.col+4; j++){
                try{
                    if((Math.pow((temp.lig-cases.get(i).get(j).lig), 2)+Math.pow((temp.col-cases.get(i).get(j).col), 2) )<10) {
                        temp2.add(cases.get(i).get(j));
                    }
                    else{ 
                        temp2.add(null); 
                    }
                }
                catch(Exception e){
                    temp2.add(null);  
                }
            }
            res.add(temp2); 
        }
        return res; 
        

    }
    public void bougerJoueur(Direction dir){
        Case temp = joueur.getCase(); 
        int col= temp.col; 
        int lig= temp.lig; 
        switch (dir) {
            case NORTH: { 
                if (col==0) return;
                else if (cases.get(lig).get(col-1) instanceof CaseTraversable){
                    if(cases.get(lig).get(col-1) instanceof Hall){
                        ((CaseTraversable)(cases.get(lig).get(col))).sort(); 
                        joueur.bouge(cases.get(lig).get(col-1));
                        ((CaseTraversable)cases.get(lig).get(col-1)).entre(joueur); 
                        if (((Hall)cases.get(lig).get(col-1)).getKey()) {
                            ((Hall)cases.get(lig).get(col-1)).takeKey();
                            joueur.manageKey(1);
                        }
                        //joueur.gainResistance(((Hall)cases.get(lig).get(col-1)).getMedKit());
                        joueur.addMedKit(((Hall)cases.get(lig).get(col-1)).getMedKit());
                        ((Hall)cases.get(lig).get(col-1)).retrieveMedKit();
                        
                        

                    return;}
                    else if(cases.get(lig).get(col-1) instanceof Porte){
                        if(((Porte)cases.get(lig).get(col-1)).estTraversable()){
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig).get(col-1));
                            ((CaseTraversable)cases.get(lig).get(col-1)).entre(joueur); 
                            return;
                        }
                        else if(joueur.getKey()>0){
                            ((Porte)cases.get(lig).get(col-1)).setOpen(); 
                            joueur.manageKey(-1); 
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig).get(col-1));
                            ((CaseTraversable)cases.get(lig).get(col-1)).entre(joueur); 
                            return;
                        }
                        else return;
                    }
                    else 
                    {((CaseTraversable)(cases.get(lig).get(col))).sort(); 
                            joueur.bouge(cases.get(lig).get(col-1));
                            ((CaseTraversable)cases.get(lig).get(col-1)).entre(joueur); 
                            return;
                    }
                }
                else return; 
            }
            case SOUTH: {
                if(col==width*8) return; 
                else if (cases.get(lig).get(col+1) instanceof CaseTraversable){
                    if(cases.get(lig).get(col+1) instanceof Hall){
                        ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                        joueur.bouge(cases.get(lig).get(col+1));
                        ((CaseTraversable)cases.get(lig).get(col+1)).entre(joueur); 
                        if (((Hall)cases.get(lig).get(col+1)).getKey()) {
                            ((Hall)cases.get(lig).get(col+1)).takeKey();
                            joueur.manageKey(1);
                        }
                        //joueur.gainResistance(((Hall)cases.get(lig).get(col+1)).getMedKit());
                        joueur.addMedKit(((Hall)cases.get(lig).get(col+1)).getMedKit());
                        ((Hall)cases.get(lig).get(col+1)).retrieveMedKit();

                    return;}
                    else if(cases.get(lig).get(col+1) instanceof Porte){
                        if(((Porte)cases.get(lig).get(col+1)).estTraversable()){
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig).get(col+1));
                            ((CaseTraversable)cases.get(lig).get(col+1)).entre(joueur); 
                            return;
                        }
                        else if(joueur.getKey()>0){
                            ((Porte)cases.get(lig).get(col+1)).setOpen(); 
                            joueur.manageKey(-1); 
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig).get(col+1));
                            ((CaseTraversable)cases.get(lig).get(col+1)).entre(joueur); 
                            return;
                        }
                        else return;
                    }
                    else {
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig).get(col+1));
                            ((CaseTraversable)cases.get(lig).get(col+1)).entre(joueur); 
                            return;
                    }
                }
                else return; 
            }
            case EAST:{
                if(lig==height*8) return;
                else if (cases.get(lig+1).get(col) instanceof CaseTraversable){
                    if(cases.get(lig+1).get(col) instanceof Hall){
                        ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                        joueur.bouge(cases.get(lig+1).get(col));
                        ((CaseTraversable)cases.get(lig+1).get(col)).entre(joueur); 
                        if (((Hall)cases.get(lig+1).get(col)).getKey()) {
                            ((Hall)cases.get(lig+1).get(col)).takeKey();
                            joueur.manageKey(1);
                        }
                        //joueur.gainResistance(((Hall)cases.get(lig+1).get(col)).getMedKit());
                        joueur.addMedKit(((Hall)cases.get(lig+1).get(col)).getMedKit());
                        ((Hall)cases.get(lig+1).get(col)).retrieveMedKit();

                    return;}
                    else if(cases.get(lig+1).get(col) instanceof Porte){
                        if(((Porte)cases.get(lig+1).get(col)).estTraversable()){
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig+1).get(col));
                            ((CaseTraversable)cases.get(lig+1).get(col)).entre(joueur); 
                            return;
                        }
                        else if(joueur.getKey()>0){
                            ((Porte)cases.get(lig+1).get(col)).setOpen(); 
                            joueur.manageKey(-1); 
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig+1).get(col));
                            ((CaseTraversable)cases.get(lig+1).get(col)).entre(joueur); 
                            return;
                        }
                        else return; 
                    }
                    else {
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig+1).get(col));
                            ((CaseTraversable)cases.get(lig+1).get(col)).entre(joueur); 
                            return; 
                    }
                }
                else return; 
            }
            case WEST:{
                if(lig==0) return;
                else if (cases.get(lig-1).get(col) instanceof CaseTraversable){
                    if(cases.get(lig-1).get(col) instanceof Hall){
                        ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                        joueur.bouge(cases.get(lig-1).get(col));
                        ((CaseTraversable)cases.get(lig-1).get(col)).entre(joueur); 
                        if (((Hall)cases.get(lig-1).get(col)).getKey()) {
                            ((Hall)cases.get(lig-1).get(col)).takeKey();
                            joueur.manageKey(1);
                        }
                        //joueur.gainResistance(((Hall)cases.get(lig-1).get(col)).getMedKit());
                        joueur.addMedKit(((Hall)cases.get(lig-1).get(col)).getMedKit());
                        ((Hall)cases.get(lig-1).get(col)).retrieveMedKit();

                    return;}
                    else if(cases.get(lig-1).get(col) instanceof Porte){
                        if(((Porte)cases.get(lig-1).get(col)).estTraversable()){
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig-1).get(col));
                            ((CaseTraversable)cases.get(lig-1).get(col)).entre(joueur); 
                            return;
                        }
                        else if(joueur.getKey()>0){
                            ((Porte)cases.get(lig-1).get(col)).setOpen(); 
                            joueur.manageKey(-1); 
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig-1).get(col));
                            ((CaseTraversable)cases.get(lig-1).get(col)).entre(joueur); 
                            return;
                        }
                        else return; 
                    }
                    else {
                            ((CaseTraversable)cases.get(lig).get(col)).sort(); 
                            joueur.bouge(cases.get(lig-1).get(col));
                            ((CaseTraversable)cases.get(lig-1).get(col)).entre(joueur); 
                            return;
                    }
                }
                else return; 
            }
            default: System.out.println("WTF"); break;
        }
        
    }
}
