package de.hawhh.ants;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * JavaFX GridPane zur Darstellung des Grids.
 * @author Christian Lins
 */
public class GridView extends GridPane {
    
    private final Rectangle[][] tiles;
    
    public GridView(Grid grid) {
        tiles = new Rectangle[grid.getWidth()][grid.getHeight()];
        setGridLinesVisible(true);
        init(grid.getWidth(), grid.getHeight());
        grid.addListener((_grid, x, y, color) -> {
            tiles[x][y].setFill(color);
        });
    }
    
    private void init(int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Rectangle(15, 15, Color.AQUA);
                var block = new StackPane();
                block.getChildren().add(tiles[x][y]);
                add(block, x, y);
            }
        }
    }
    
}
