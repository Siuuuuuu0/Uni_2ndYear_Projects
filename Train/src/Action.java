public class Action {
    public Personne personne; 
    public ActionType type; 
    public static int NB_ACTIONS=1; 
    
    public Action(Personne personne, ActionType type){
        this.personne=personne; 
        this.type=type;     
    }
    public void Activate(){
        switch(type){
            case Up : personne.climbs(); assert(personne.roof); break;
            case Down: personne.descends();assert(!personne.roof); break; 
            case Forward :{
            Wagon tmp = personne.wagon; personne.advances();
            assert(tmp.next==null||personne.wagon==tmp.next); break; }
            case Backwards :{
            Wagon tmp = personne.wagon; personne.returns(); 
            assert(tmp.prev==null||personne.wagon==tmp.prev);break;}
            case Collect : {
            int tmp = personne.butinsValue;
            ((Bandit)personne).braquer(); 
            assert(personne.wagon.butins.isEmpty()||tmp<personne.butinsValue);
            break;}
            case ShootD: ((Bandit)personne).shoot(Direction.Down); break; 
            case ShootU: ((Bandit)personne).shoot(Direction.Up); break; 
            case ShootF: ((Bandit)personne).shoot(Direction.Forward); break;
            case ShootB: ((Bandit)personne).shoot(Direction.Backwards); break;  
        }
    }
}
enum ActionType{
    Up, Down, Forward, Backwards, Collect, ShootD, ShootU, ShootF, ShootB
}

