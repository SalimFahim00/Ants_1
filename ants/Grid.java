package de.hawhh.ants;

import java.util.Collection;
import java.util.HashSet;
import javafx.scene.paint.Color;

/**
 * Das 2D-Spielfeld.
 * Bewertung: max. 6 Punkte
 * BEWERTUNG 4,5 Punkte
 * @author XX
 */
public class Grid {
    /** Enthält die Farben des Spielfeldes. */
    private final Color[][] grid;
    
    /** Enthält eine Menge von Listenern, die auf Änderungen hier horchen. */
    private Collection<GridChangeListener> listeners;
    
    /**
     * Initialisiert das 2D-Grid mit den angegebenen Dimensionen. 
     * Die Höhe und Breite muss jeweils mindestens 2 betragen, falls nicht, wird
     * eine IllegalArgumentException geworfen.
     * @param width
     * @param height 
     */
    // BEWERTUNG 0,5 Punkte
    public Grid(int width, int height) {
        // TODO (1 Punkt)
        // TODO Stellen Sie sicher, dass die Vorbedingungen eingehalten werden.
        if(width<=2 && height<=2){
            throw new IllegalArgumentException("Muss größer oder gleich 2 sein");
        }
        
        // TODO (1 Punkt)
        // TODO Initialisieren Sie listeners-Collection mit einer geeigneten Implementierung
        // TODO zur Repräsentation der MENGE an Beobachtern.
       // HashSet <GridChangeListener> listeners = new HashSet<>();
        
        this.grid = new Color[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = Color.WHITE;
            }
        }
    }
    
    /**
     * Fügt einen Listener hinzu. Wenn die Listener-Instanz schon hinzugefügt 
     * wurde, so erfolgt keine Änderung.
     * @param listener 
     */
    public void addListener(GridChangeListener listener) {
        // TODO (1 Punkt)
        listeners.add(listener);
    }
    
    /**
     * Entfernt den angegebenen Listener.
     * @param listener
     * @return 
     */
    public boolean removeListener(GridChangeListener listener) {
        // TODO (1 Punkt)
        // TODO
        if(listeners.contains(listener)){
        listeners.remove(listener);
        return true;
        }
        return false;
        
    }
    
    /**
     * Benachrichtigt alle Beobachter über die Änderung.
     * @param x
     * @param y
     * @param newColor 
     */
    protected void fireChangedEvent(int x, int y, Color newColor) {
        // TODO (2 Punkte)
        // TODO Verwenden Sie Stream-Methoden zum Iterieren!
        
        listeners.forEach(i -> i.gridChanged(this, x, y, newColor));
    }
    
    /**
     * Gibt die Farbe an den angegebenen Koordinaten des Spielfelds zurück.
     * Sind die Koordinaten außerhalb des gültigen Bereichs, so wirft die Methode
     * eine IllegalArgumentException.
     * @param x
     * @param y
     * @return Das Color-Objekt an der angegebenen Stelle.
     */
    public Color get(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return grid[x][y];
    }
    
    /**
     * @return Höhe des Spielfeldes.
     */
    public int getHeight() {
        return grid[0].length;
    }
    
    /**
     * @return Breite des Spielfeldes. 
     */
    public int getWidth() {
        return grid.length;
    }
    
    /**
     * Setzt die Farbe an der angebenen Stelle des Spielfeldes. 
     * Bei ungültigen Koordinaten oder unerwünschter Farbe (erlaubt sind BLACK 
     * und WHITE) wird eine IllegalArgumentException geworfen.
     * Löst ein Ereignis aus, bei dem die Beobachter über eine Änderung
     * benachrichtigt werden.
     * @param x
     * @param y
     * @param color 
     */
    public void set(int x, int y, Color color) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Koordinaten ungültig");
        }
        if (!Color.BLACK.equals(color) && !Color.WHITE.equals(color)) {
            throw new IllegalArgumentException("Farbe nicht erlaubt");
        }
        
        grid[x][y] = color;
        fireChangedEvent(x, y, color);
    }
    
    /**
     * Vertauscht die Farben an den angegeben Koordinaten. Aus WHITE wird BLACK
     * und umgekehrt.
     * @param x
     * @param y 
     */
    public void switchColor(int x, int y) {
        if(get(x, y).equals(Color.BLACK)) {
            set(x, y, Color.WHITE);
        } else {
            set(x, y, Color.BLACK);
        }
    }
}
