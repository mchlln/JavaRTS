package ubx.project.javarts.Model;

public class Map {
    private static Map instance = null;
    private Size size;
    private MapTileStatus[][] tiles;

    private Map(){
    }

    public MapTileStatus getTileStatus(Position position){
        if (tiles == null){
            throw new IllegalStateException("Map has not been initialized yet");
        }
        return tiles[position.x][position.y]; //Need to protect more
    }

    public boolean isAreaFree(Position position, Size size){
        return false;
    }

    public void Construct(Position position, Size size){

    }

    public void Destruct(Position position, Size size){

    }

    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }
}
