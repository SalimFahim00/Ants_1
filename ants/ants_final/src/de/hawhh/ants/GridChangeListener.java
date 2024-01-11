package de.hawhh.ants;

import javafx.scene.paint.Color;

/**
 * Interface für die Beobachter eines Grids.
 * @author Christian Lins
 */
public interface GridChangeListener {
    void gridChanged(Grid grid, int x, int y, Color newColor);
}
