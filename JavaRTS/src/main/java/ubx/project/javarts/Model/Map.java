package ubx.project.javarts.Model;

import ubx.project.javarts.Exception.MapTileStateException;

/**
 * Design Pattern Singleton : must have only one map in the game
 */
public class Map {
    private static Map instance = null;
    private Size size;
    private MapTileStatus[][] tiles;

    private Map(){
        // WITH LITTLE TILES this.size = new Size(80,25);
        this.size = new Size(40,12);
        this.tiles = new MapTileStatus[size.getWidth()][size.getHeight()];
    }

    /**
     * @param position of a tile
     * @return the status of the tile at the given positon
     */
    public MapTileStatus getTileStatus(Position position){
        if (tiles == null){
            throw new MapTileStateException("Map has not been initialized yet");
        }
        return tiles[position.getX()][position.getY()]; //Need to protect more
    }

    /**
     * Checks if the area at the specified position and size is free.
     *
     * @param position the starting position to check.
     * @param size the dimensions of the area to check.
     * @return true if the area is free, false if any tile is occupied.
     */
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
     * Sets all tiles in the given area as OCCUPIED.
     * ⚠️ Does not check if the area is free ⚠️
     *
     * @param position of the beggining of the area
     * @param size dimensions of the area
     */
    public void construct(Position position, Size size){
        for (int x = 0; x < size.getWidth(); x++){
            for (int y = 0; y < size.getHeight(); y++){
                tiles[position.getX()+x][position.getY()+y] = MapTileStatus.OCCUPIED;
            }
        }
    }

    /**
     * Sets all tiles in the given area as FREE.
     * Throws an exception if one of the tiles is not OCCUPIED before.
     *
     * @param position of the beggining of the area
     * @param size dimensions of the area
     */
    public void destruct(Position position, Size size){
        for (int x = 0; x < size.getWidth(); x++){
            for (int y = 0; y < size.getHeight(); y++){
                if (tiles[position.getX()+x][position.getY()+y] == MapTileStatus.FREE){
                    throw new MapTileStateException("Trying to free an already free map");
                }
                tiles[position.getX()+x][position.getY()+y] = MapTileStatus.FREE;
            }
        }
    }

    /**
     * Design pattern Singleton
     * Creates an instance of the Map if none were created or
     * returns the one created before in the private field.
     *
     * @return unique instance of the Map
     */
    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }

    /**
     * @return {@link Size} of the map
     */
    public Size getSize(){
        return size;
    }

    /**
     * @return the array of all tiles from the map
     */
    public MapTileStatus[][] getTiles(){
        return tiles;
    }
}
