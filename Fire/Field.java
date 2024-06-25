import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private LinkedHashMap<Integer, ArrayList<Room>> distances;
    //private Map<Integer, ArrayList<Room>> sortedDistances;
    private ArrayList<Room> bottom; 
    private ArrayList<Room> right;
    private ArrayList<Room> left;
    private ArrayList<Room> up;
    private Room[][] field;
    private ArrayList<Room> openings;
    private Random random;
    private Direction nextDir;
    private ArrayList<Direction> directions;
    private int randomInt;
    private Room randomRoom;
    private int roomIdx;
    private Room currentRoom;
    private int currentX;
    private int currentY;
    private int goalX;
    private int goalY;
    private ArrayList<ArrayList<Direction>> allPosibilities;
    private int randomInt2;
    private int width; 
    private int height; 
    private int spawnX;
    private int spawnY;
    public Field(int width, int height, int spawnX,int spawnY){
        this.height=height;
        this.width=width;
        this.spawnX=spawnX;
        this.spawnY=spawnY;
        distances = new LinkedHashMap<>();
        allPosibilities = new ArrayList<>();
        bottom = new ArrayList<>();
        up = new ArrayList<>();
        right = new ArrayList<>();
        left = new ArrayList<>();
        setAllPosibilities();
        populate();
        field = new Room[height][width];
        directions= new ArrayList<>();
        directions.add(Direction.NORTH); directions.add(Direction.SOUTH); directions.add(Direction.WEST); directions.add(Direction.EAST);  
        field[spawnY][spawnX]= new Room(directions);
        field[spawnY][spawnX].setDistance(0);
        distances.put(Integer.valueOf(field[spawnY][spawnX].getDistance()), new ArrayList<>() );
        distances.get(Integer.valueOf(0)).add(field[spawnY][spawnX]);
        openings=new ArrayList<>();
        openings.add(field[spawnY][spawnX]);
        field[spawnY][spawnX].setCoords(spawnX, spawnY);
        random = new Random();
        
        currentX=spawnX;
        currentY=spawnY;
        generate();
    
        //sortedDistances = new TreeMap<>(distances);
        /*sortedDistances = distances.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));*/
        /*for(Map.Entry<Integer, ArrayList<Room>> e : distances.entrySet()){
            StringBuilder sb = new StringBuilder(" ");
            Integer dist = e.getKey();
            sb.append(dist.toString());
            for(Room r: e.getValue()){
                sb.append(r.toString());
                sb.append("|");
            }
            System.out.println(sb);
        }*/
        setDoors();
        setExit();
        left= null;
        right=null; 
        up=null;
        bottom=null;
        allPosibilities=null;
        currentRoom=null;
        randomRoom=null;


    }
    private void setAllPosibilities(){
        ArrayList<Direction> temp = new ArrayList<>();
        temp.add(Direction.NORTH); temp.add(Direction.SOUTH); temp.add(Direction.WEST); temp.add(Direction.EAST);
        allPosibilities.add(new ArrayList<Direction>(temp));
        
        temp.remove(3);
        
        temp.remove(2);
        
        allPosibilities.add(new ArrayList<Direction>(temp));
        
        temp.remove(1);
        
        temp.add(Direction.WEST);
        
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(1);
        temp.add(Direction.EAST);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(1);
        temp.remove(0);
        temp.add(Direction.SOUTH);
        temp.add(Direction.WEST);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(1);
        temp.add(Direction.EAST);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(1);
        temp.remove(0);
        temp.add(Direction.WEST);
        temp.add(Direction.EAST);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(1);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(0);
        temp.add(Direction.EAST);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(0);
        temp.add(Direction.SOUTH);
        allPosibilities.add(new ArrayList<Direction>(temp));
        temp.remove(0);
        temp.add(Direction.NORTH);
        allPosibilities.add(new ArrayList<Direction>(temp));

    }
    private void populate(){
        //up.add(new Room(allPosibilities.get(0)));
        up.add(new Room(allPosibilities.get(1)));
        up.add(new Room(allPosibilities.get(2)));
        up.add(new Room(allPosibilities.get(3)));
        up.add(new Room(allPosibilities.get(1)));
        up.add(new Room(allPosibilities.get(2)));
        up.add(new Room(allPosibilities.get(3)));
        up.add(new Room(allPosibilities.get(1)));
        up.add(new Room(allPosibilities.get(2)));
        up.add(new Room(allPosibilities.get(3)));
        up.add(new Room(allPosibilities.get(10)));
        
        
        //bottom.add(new Room(allPosibilities.get(0)));
        bottom.add(new Room(allPosibilities.get(1)));
        bottom.add(new Room(allPosibilities.get(4)));
        bottom.add(new Room(allPosibilities.get(5)));
        bottom.add(new Room(allPosibilities.get(1)));
        bottom.add(new Room(allPosibilities.get(4)));
        bottom.add(new Room(allPosibilities.get(5)));
        bottom.add(new Room(allPosibilities.get(1)));
        bottom.add(new Room(allPosibilities.get(4)));
        bottom.add(new Room(allPosibilities.get(5)));
        bottom.add(new Room(allPosibilities.get(9)));
        
        //right.add(new Room(allPosibilities.get(0)));
        right.add(new Room(allPosibilities.get(3)));
        right.add(new Room(allPosibilities.get(5)));
        right.add(new Room(allPosibilities.get(6)));
        right.add(new Room(allPosibilities.get(3)));
        right.add(new Room(allPosibilities.get(5)));
        right.add(new Room(allPosibilities.get(6)));
        right.add(new Room(allPosibilities.get(3)));
        right.add(new Room(allPosibilities.get(5)));
        right.add(new Room(allPosibilities.get(6)));
        right.add(new Room(allPosibilities.get(8)));
        
        //left.add(new Room(allPosibilities.get(0)));
        left.add(new Room(allPosibilities.get(2)));
        left.add(new Room(allPosibilities.get(4)));
        left.add(new Room(allPosibilities.get(6)));
        left.add(new Room(allPosibilities.get(2)));
        left.add(new Room(allPosibilities.get(4)));
        left.add(new Room(allPosibilities.get(6)));
        left.add(new Room(allPosibilities.get(2)));
        left.add(new Room(allPosibilities.get(4)));
        left.add(new Room(allPosibilities.get(6)));
        left.add(new Room(allPosibilities.get(7)));
        
    }
    private void setCase(){
        if(goalY>=0&&goalY<height&&goalX<width&&goalX>=0&&field[goalY][goalX]==null){
            field[goalY][goalX] = new Room(randomRoom);
            field[goalY][goalX].setCoords(goalX, goalY);
            field[goalY][goalX].setDistance(1+field[currentY][currentX].getDistance());
            field[goalY][goalX].removeRemainingDirection(nextDir.getOposite());
            field[currentY][currentX].removeRemainingDirection(nextDir);
            openings.get(randomInt).removeRemainingDirection(nextDir);
            if(distances.size()<= field[goalY][goalX].getDistance()){
                distances.put(Integer.valueOf(field[goalY][goalX].getDistance()), new ArrayList<>());
                distances.get(Integer.valueOf(field[goalY][goalX].getDistance())).add(field[goalY][goalX]);
            }
            else{
                distances.get(Integer.valueOf(field[goalY][goalX].getDistance())).add(field[goalY][goalX]);
            }
            if(field[goalY][goalX].getOpenings()>=1){
                openings.add(field[goalY][goalX]);
            }
            if(field[currentY][currentX].getOpenings()== 0){
                openings.remove(randomInt);
            }
        }
        else if(goalY>=0&&goalY<height&&goalX<width&&goalX>=0&&field[goalY][goalX].getDirections().contains(nextDir.getOposite())){
            field[currentY][currentX].setDistance(Math.min(field[currentY][currentX].getDistance(), field[goalY][goalX].getDistance()+1));
            openings.get(randomInt).setDistance(Math.min(field[currentY][currentX].getDistance(), field[goalY][goalX].getDistance()+1));
            openings.get(openings.indexOf(field[goalY][goalX])).removeRemainingDirection(nextDir.getOposite());
            field[goalY][goalX].removeRemainingDirection(nextDir.getOposite());
            openings.get(openings.indexOf(field[goalY][goalX])).setDistance(Math.min(field[goalY][goalX].getDistance(), field[currentY][currentX].getDistance()+1));
            field[goalY][goalX].setDistance(Math.min(field[goalY][goalX].getDistance(), field[currentY][currentX].getDistance()+1));
            field[currentY][currentX].removeRemainingDirection(nextDir);
            openings.get(randomInt).removeRemainingDirection(nextDir);
            if(field[currentY][currentX].getOpenings()== 0){
                openings.remove(randomInt);
            }
            if(field[goalY][goalX].getOpenings()==0){
                openings.remove(field[goalY][goalX]);
            }
        }
        else{
            directions=new ArrayList<>(currentRoom.getDirections());
            directions.remove(directions.indexOf(nextDir));
            field[currentY][currentX].setDirections(new ArrayList<Direction>(directions));
            openings.get(randomInt).setDirections(new ArrayList<Direction>(directions));
            openings.get(randomInt).removeRemainingDirection(nextDir);
            field[currentY][currentX].removeRemainingDirection(nextDir);
            if(field[currentY][currentX].getOpenings()==0){
                openings.remove(randomInt);
            }
        }
    }
    private void generate(){
        if(openings.isEmpty()) return;
        try{
            randomInt = random.nextInt(openings.size());
        }catch (Exception e){
            randomInt=0;
        }
        currentRoom = openings.get(randomInt);
        currentX= currentRoom.getX(); 
        currentY= currentRoom.getY();
        directions= new ArrayList<Direction>(currentRoom.getRemainnigDirections());
        
        try{
        randomInt2 = random.nextInt(directions.size());
        }catch(Exception e){
            randomInt2=0;
        }
        nextDir = directions.get(randomInt2);
        roomIdx = random.nextInt(up.size());
        
        switch (nextDir){
            case NORTH: 
            
            randomRoom= bottom.get(roomIdx); 
            
            goalY= currentY-1;
            goalX= currentX;
            break;
            case SOUTH:
            
            randomRoom= up.get(roomIdx);
            
            goalY= currentY+1;
            goalX= currentX;
            break;
            case EAST:
            
            randomRoom= left.get(roomIdx);
            
            goalY= currentY;
            goalX= currentX+1;
            break;
            case WEST: 
            
            randomRoom= right.get(roomIdx);
            
            goalY= currentY;
            goalX= currentX-1;
            break;

        }
        setCase();
        generate();





    }
    private void setDoors(){
        ArrayList<Integer> nbDoors = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Room>> ele : distances.entrySet()){
            if(ele.getKey()>1){
                try{
                    nbDoors.add(Integer.valueOf(random.nextInt((int)Math.ceil((ele.getValue().size())/2)-1)+1));
                }
                catch(Exception e ){
                    nbDoors.add(Integer.valueOf(1));
                }
                ArrayList<Room> temp = new ArrayList<>(ele.getValue());
                for(int i = nbDoors.get(ele.getKey()-2) ; i>0; i--){
                    randomInt = random.nextInt(temp.size());
                    while(!temp.get(randomInt).hasPlace()){
                        temp.remove(randomInt);
                        randomInt = random.nextInt(temp.size()); 
                    }
                    field[temp.get(randomInt).getY()][temp.get(randomInt).getX()].addDoor();
                    temp.set(randomInt, field[temp.get(randomInt).getY()][temp.get(randomInt).getX()]);  
                }
            }
        }
        for(Map.Entry<Integer, ArrayList<Room>> ele : distances.entrySet()){
            if(ele.getKey()>0&&ele.getKey()<distances.size()-1){
                ArrayList<Room> temp = new ArrayList<>(ele.getValue());
                for(int i = nbDoors.get(ele.getKey()-1); i>0; i--){
                    randomInt= random.nextInt(temp.size()); 
                    field[temp.get(randomInt).getY()][temp.get(randomInt).getX()].addKey();
                    temp.remove(randomInt);
                }
            }
        }
        // for(Integer i: nbDoors){
        //     System.out.println(i.toString());
        // }
        // for(Integer i:nbDoors){
        //     System.out.print(i.toString()+ " ");
        // }
        // System.out.println("");
    }
    public void setExit(){
        ArrayList<Room> temp= distances.get(distances.size()-1);
        int r = random.nextInt(temp.size());
        field[temp.get(r).getY()][temp.get(r).getX()].setExit(true);
    }
    public Room[][] getField() {
        for(int i = 0; i<height; i++){
            for(int j=0; j<width; j++){
                if(field[i][j]==null) field[i][j]=new Room();
            }
        }
        return field;
    }
    public void printField(){
        for(int i=0; i<6; i++){
            System.out.print("||");
            for(int j=0; j<6; j++){
                try{
                    System.out.print(field[i][j].toString());
                    System.out.print(" "+field[i][j].getDistance());
                }
                catch(Exception e){
                    System.out.print(" -- ");
                }
                System.out.print("||"); 
            }
            System.out.print("\n");
        }
        
    }
}
    

