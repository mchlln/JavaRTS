package ubx.project.javarts.View;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.Model.Map;
import ubx.project.javarts.Model.Size;

public class MapView extends GridPane {
    int TILE_SIZE=5;

    public MapView() {
        Size mapSize = Map.getInstance().getSize();
        for (int col = 0; col < mapSize.getWidth(); col++) {
            for (int row = 0; row < mapSize.getHeight(); row++) {
                ImageView tileImageView = new ImageView(getClass().getResource("/ubx/project/javarts/mapTiles/tile_0001.png").toExternalForm());
                this.add(tileImageView, col, row);
            }
        }
    }
}
