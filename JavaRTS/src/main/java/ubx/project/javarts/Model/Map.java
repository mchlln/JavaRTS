package ubx.project.javarts.Model;

import ubx.project.javarts.Exception.MapTileStateException;

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
        return tiles[position.getX()][position.getY()]; //Need to protect more
    }

    public boolean isAreaFree(Position position, Size size){
        for (int x = 0; x < size.getWidth(); x++){
            for (int y = 0; y < size.getHeight(); y++){
                if (tiles[position.getX()+x][position.getY()+y] == MapTileStatus.OCCUPIED){ // Access do not store a Position object
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ⚠️ Does not check if the area is free
     */
    public void Construct(Position position, Size size){
        for (int x = 0; x < size.getWidth(); x++){
            for (int y = 0; y < size.getHeight(); y++){
                tiles[position.getX()+x][position.getY()+y] = MapTileStatus.OCCUPIED;
            }
        }
    }

    public void Destruct(Position position, Size size){
        for (int x = 0; x < size.getWidth(); x++){
            for (int y = 0; y < size.getHeight(); y++){
                if (tiles[position.getX()+x][position.getY()+y] == MapTileStatus.FREE){
                    throw new MapTileStateException("Trying to free an already free map");
                }
                tiles[position.getX()+x][position.getY()+y] = MapTileStatus.FREE;
            }
        }
    }

    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }
}
