package ubx.project.javarts.View;

import javafx.beans.binding.Bindings;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import ubx.project.javarts.Model.Map;
import ubx.project.javarts.Model.Size;

public class MapView extends GridPane {

    public MapView() {
        Size mapSize = Map.getInstance().getSize();
        int width = mapSize.getWidth();
        int height = mapSize.getHeight();

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                ImageView tileImageView = new ImageView(getClass()
                        .getResource("/ubx/project/javarts/mapTiles/tile_0001.png").toExternalForm());

                // Bind the tile size to the MapView's width and height
                tileImageView.fitWidthProperty().bind(Bindings.divide(this.widthProperty(), width));
                tileImageView.fitHeightProperty().bind(Bindings.divide(this.heightProperty(), height));
                tileImageView.setPreserveRatio(true); // Maintain aspect ratio

                // Add the tile to the grid
                this.add(tileImageView, col, row);
            }
        }

        // Ensure the GridPane resizes with the tiles
        this.setPrefSize(width * 50, height * 50); // Default preferred size (can be adjusted)
    }
}
