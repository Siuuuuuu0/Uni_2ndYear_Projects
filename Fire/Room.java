import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
    private LinkedHashMap<Direction, Boolean> directions;
    private ArrayList<Direction> remainingDirections;
    private int x;
    private int y;
    private int distance;
    private boolean hasKey; 
    private Random random = new Random();
    int randomInt;
    int possibleDoors;
    int doors; 
    int randomY = random.nextInt(5) +1;
    int randomX = random.nextInt(5)+1;
    boolean empty=false;
    boolean exit= false;
    ArrayList<ArrayList<Case>> result;
    public Room(){
        empty=true;
    }

    public Room(ArrayList<Direction> directions) {
        this.directions=new LinkedHashMap<>();
        if (directions.isEmpty()) {
            throw new IllegalArgumentException("Directions cannot be empty for a room.");
        }
        for(Direction d : directions){
            this.directions.put(d, Boolean.valueOf(true));
        }
        this.remainingDirections = new ArrayList<>(directions);
        this.x = -1;
        this.y = -1;
        this.distance=-1;
        possibleDoors = directions.size();
        doors = 0;
        hasKey = false;
    }

    public Room(Room r) {
        if (r.getDirections().isEmpty()) {
            throw new IllegalArgumentException("Directions cannot be empty for a room.");
        }
        this.directions = r.getDirectionToExit();
        this.x = r.getX();
        this.y = r.getY();
        this.remainingDirections = new ArrayList<>(r.getRemainnigDirections());
        possibleDoors = directions.size();
        hasKey = r.getKey();
    }

    public ArrayList<Direction> getDirections() {
        return new ArrayList<>(directions.keySet());
    }
    public LinkedHashMap<Direction, Boolean> getDirectionToExit(){
        return new LinkedHashMap<>(directions);
    }

    public void setDirections(ArrayList<Direction> directions) {
        this.directions = new LinkedHashMap<>();
        for(Direction d: directions){
            this.directions.put(d, Boolean.valueOf(true));
        }
        possibleDoors = directions.size();
        
    }

    public int getOpenings() {
        return remainingDirections.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        for(Map.Entry<Direction, Boolean> e : directions.entrySet()){
            sb.append( e.getKey().toString());
            sb.append("  ");
            sb.append(e.getValue().toString());
            sb.append(" - ");

        }
        if(hasKey) sb.append("K");
        if(exit) sb.append("X");
        return sb.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void removeRemainingDirection(Direction d){
        remainingDirections.remove(d);
    }
    public ArrayList<Direction> getRemainnigDirections(){
        return remainingDirections;
    }
    public void setDistance(int distance){
        this.distance=distance;
    }
    public int getDistance(){
        return distance;
    }
    public void addKey(){
        hasKey=true;
    }
    public boolean getKey(){
        return hasKey;
    }
    public void addDoor(){
            ArrayList <Direction>tempDirections = (ArrayList<Direction>)(directions.entrySet().stream()
                    .filter(Map.Entry::getValue) 
                    .map(Map.Entry::getKey) 
                    .collect(Collectors.toList()));
            try{ 
                randomInt=random.nextInt(tempDirections.size());
            }catch(Exception e){
                 randomInt=0;
                
            }
            directions.put(tempDirections.get(randomInt), Boolean.valueOf(false));
            possibleDoors--; 
            doors++;
    }
    public boolean hasPlace(){
        if (possibleDoors > 0 && doors<2)  return true; 
        return false;
    }
    public ArrayList<ArrayList<Case>> toArrayList(){
        if(!empty){

            result = new ArrayList<>();
            ArrayList <Case> temp = new ArrayList<>(); 
            for(int i =0 ; i<7; i++){
                temp.add(new Mur(y*8, x*8+i));
            }
            temp.add(null);
            if(directions.containsKey(Direction.NORTH)){
                if(!directions.get(Direction.NORTH)){
                    temp.set(3, new Porte(y*8, x*8+3, false));
                }
                else {
                    temp.set(3, new Hall(y*8, x*8+3,0,  false));
                }
            }
            result.add(temp);
            for(int i= 1; i<6; i++){
                temp = new ArrayList<>(); 
                for(int j= 0; j<8; j++){
                    if(j==0 || j==6){
                        temp.add(new Mur(y*8 + i, x*8 + j)); 
                    }
                    else if(j==7){
                        temp.add(null);
                    }
                    else{
                        if(hasKey&&i==randomY&&j==randomX){
                            temp.add(new Hall(y*8 + i, x*8 + j,0,  true));
                        }
                        else {
                            temp.add(new Hall(y*8 + i, x*8 + j, 0, false));
                        }
                    }
                }
                
                if(i==3 && directions.containsKey(Direction.WEST)){
                    if(!directions.get(Direction.WEST)){
                        temp.set(0, new Porte(y*8+i, x*8, false));
                    }
                    else{
                        temp.set(0, new Hall(y*8+i, x*8,0,  false));
                    }
                    //result.add(temp);
                }
                if(i==3 && directions.containsKey(Direction.EAST)){
                    if(!directions.get(Direction.EAST)){
                        temp.set(6, new Porte(y*8+i, x*8+6, false));
                    }
                    else{
                        temp.set(6, new Hall(y*8+i, x*8+6,0,  false));
                    }
                    temp.set(7, new Hall(y*8+i, x*8+7, 0, false));

                }
                if(i==2&&directions.containsKey(Direction.EAST)){
                    temp.set(7, new Mur(y*8+i, x*8+7));
                }
                if(i==4&&directions.containsKey(Direction.EAST)){
                    temp.set(7, new Mur(y*8+i, x*8+7));
                }
                result.add(temp);
            }
            temp = new ArrayList<>();
            for(int i =0 ; i<7; i++){
                temp.add(new Mur(y*8+6, x*8+i));
            }
            temp.add(null);
            if(directions.containsKey(Direction.SOUTH)){
                if(!directions.get(Direction.SOUTH)){
                    temp.set(3, new Porte(y*8+6, x*8+3, false));
                }
                else{
                    temp.set(3, new Hall(y*8+6, x*8+3, 0,false));
                }
                
                
            }
            
            result.add(temp);
            temp = new ArrayList<>(Collections.nCopies(8, null));
            if(directions.containsKey(Direction.SOUTH)){
                temp.set(2, new Mur(y*8+7, x*8+2));
                temp.set(3, new Hall(y*8+7, x*8+3, 0, false));
                temp.set(4, new Mur(y*8+7, x*8+4));
            }
            result.add(temp);
            
        }
        else{
            ArrayList<Case> temp =  new ArrayList<>(Collections.nCopies(8, null));
            result = new ArrayList<>(Collections.nCopies(8, temp));
            
        }
        try{
            setFire();
            if(exit){
                setExit();
            }
            setMedKit();
        }
        catch(Exception e){}
        return result;
    }

    private void setFire(){
        int chaleur = random.nextInt(5) +5;
        int chance;
        randomX = random.nextInt(10) +1;
        randomY= random.nextInt(5) +1;
        if(randomX<6){
            if(result.get(randomY).get(randomX).getKey()){
                result.get(randomY).set(randomX, new Hall(y*8+randomY, x*8+randomX, chaleur, true));
            }
            else{
                result.get(randomY).set(randomX, new Hall(y*8+randomY, x*8+randomX, chaleur));
            }
            try{
                chance =random.nextInt(1);
                if(chance==0)
                    ((Hall)result.get(randomY).get(randomX+1)).manageChaleur(chaleur-4);
            }
            catch(Exception e){}
            try{
                chance =random.nextInt(1);
                if(chance==0)
                    ((Hall)result.get(randomY-1).get(randomX)).manageChaleur(chaleur-4);
            }
            catch(Exception e){}
            try{
                chance =random.nextInt(1);
                if(chance==0)
                    ((Hall)result.get(randomY).get(randomX-1)).manageChaleur(chaleur-4);
            }
            catch(Exception e){}
            try{
                chance =random.nextInt(1);
                if(chance==0)
                    ((Hall)result.get(randomY+1).get(randomX)).manageChaleur(chaleur-4);
            }
            catch(Exception e){}
            
        }
    }
    public void setExit(boolean exit){
        this.exit =exit;
    }
    private void setExit(){
        result.get(1).set(1, new Sortie(y*8+1, x*8+1, 0));  
    }
    private void setMedKit(){
        randomX = random.nextInt(25)+1;
        randomY=random.nextInt(5)+1;
        int hp = random.nextInt(200)+50;
        if(randomX <6){
            try{
                ((Hall)result.get(randomY).get(randomX)).addKit(hp);
            }
            catch(Exception e){}
        }
    }
}
