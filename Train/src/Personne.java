public class Personne {
    public boolean roof=false; 
    public Wagon wagon;
    public int given_actions = 0; 
    public static int NB_BULLETS=1; 
    public int butinsValue = 0;
    public int NB_BALLES; 
    public int idx;
    public Train train;

    //public Personne(){}
    public Personne(Wagon wagon, boolean roof, Train train){

        this.wagon = wagon; 
        this.roof=roof;
        this.train = train;
        NB_BALLES=NB_BULLETS; 

    }
    
    public void climbs(){
        if (this instanceof Marshall)
        {
            System.out.println("Marshall is unable to climb");
            return;
        }
        if(!roof) {
            roof= true; 
            System.out.println("climbs"); 
        }
        else System.out.println("already climbed"); 

    }
    public void descends(){
        if (this instanceof Marshall)
        {
            System.out.println("Marshall is unable to climb");
            return;
        }
        if(roof) {
            roof=false; 
            System.out.println("descends");
        }
        else System.out.println("already descended"); 
    }
    public void advances(){
        if(wagon.next!=null){
            wagon=wagon.next;
            System.out.println("advanced");
        }
        else System.out.println("cannot advance"); 
    }
    public void returns(){
        if(wagon.prev!=null){
            wagon=wagon.prev;
            System.out.println("returned");
        }
        else System.out.println("cannot return");
    }

}
enum Direction{
    Forward, 
    Backwards, 
    Up, 
    Down
}
