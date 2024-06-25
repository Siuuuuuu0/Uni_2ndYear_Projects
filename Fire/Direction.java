

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;
    @Override
    public String toString() {
        switch (this) {
            case NORTH:
                return "north";
            case SOUTH:
                return "south";
            case EAST:
                return "east";
            case WEST:
                return "west";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
    public Direction getOposite(){
        switch(this){

        case NORTH: return Direction.SOUTH; 
        case SOUTH: return Direction.NORTH; 
        case EAST: return Direction.WEST; 
        case WEST: return Direction.EAST;
        default : throw new IllegalArgumentException("No direction provided");
        }
    }

}
