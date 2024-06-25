import java.util.ArrayList;
import java.util.Random;

public class Wagon {
    public Wagon prev; 
    public Wagon next; 
    public int idx;
    public ArrayList<Butin> butins;
    //public Wagon(){}
    public Wagon(Wagon prev, int remaining, Train train){
        idx = Train.NB_WAGONS-remaining;
        this.prev = prev; 
        remaining--;
        butins = new ArrayList<>(); 
        // add random num of butins (1-3) to wagon
        for(int i = 0; i < (new Random().nextInt(3)+1); i++)
        {
            //select one of Butin types
            ButinType[] values = ButinType.values();
            this.addButin(new Butin(values[new Random().nextInt(2)], this));
        }
        
        if(remaining>0){
            next = new Wagon(this, remaining, train);
        }          
        else {
            next = null; 
            train.last=this; 
        }

    }

    public void addButin(Butin butin){ 
        //System.out.println(butins.size());
        butins.add(butin);
        //System.out.println(butins.size());
    }

    public void removeButin(int butinIdx)
    {
        butins.remove(butinIdx);
    }
}

