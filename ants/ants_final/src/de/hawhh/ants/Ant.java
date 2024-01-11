package de.hawhh.ants;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Stellt eine Ameise nach Langton dar.
 * Bewertung: max. 8 Punkte
 * // BEWERTUNG 0 P
 * @author XX
 */
public class Ant extends Thread {
    /** Wie viele Millisekunden soll nach jedem Schritt gewartet werden? */
    public static final int SLEEP_TIME = 500;
    
    /** Referenz zum Spielfeld */
    private final Grid grid;
    
    /** Die Position der Ant auf dem Spielfeld. */
    private int x, y;
    
    /** Die Bewegungsrichtung der Ant auf dem Spielfeld. */
    private int dirX, dirY;
    
    /** Ist der Thread gerade pausiert? */
    private boolean paused = false;
    
    public Ant(Grid grid) {
        this.grid = grid;
        
        Random rng = new Random();
        x = rng.nextInt(grid.getWidth());
        y = rng.nextInt(grid.getHeight());
        dirX = rng.nextInt(-1, 2);
        if (dirX == 0) {
            dirY = rng.nextInt(-1, 2);
        }
    }
    
    /**
     * Wendet die Bewegungsregeln nach Langton an.
     */
    protected void applyRules() {
        Color color = grid.get(x, y);
        if (color.equals(Color.BLACK)) {
            // At a black square, turn 90° counter-clockwise, flip the color of 
            // the square, move forward one unit
            turnCounterClockwise();
            grid.set(x, y, Color.WHITE);
            moveStep();
        } else if (color.equals(Color.WHITE)) {
            // At a white square, turn 90° clockwise, flip the color of the 
            // square, move forward one unit
            turnClockwise();
            grid.set(x, y, Color.BLACK);
            moveStep();
        }
    }
    
    /**
     * Die Ant macht einen Schritt in die derzeitige Richtung.
     */
    private void moveStep() {
        x = (x + dirX + grid.getWidth()) % grid.getWidth();
        y = (y + dirY + grid.getHeight()) % grid.getHeight();
    }
    
    /**
     * Die Ant dreht sich im Uhrzeigersinn.
     */
    private void turnClockwise() {
        if (dirX == 0 && dirY == 0) {
            dirX = 1;
        } else if (dirX == 1) {
            dirX = 0;
            dirY = -1;
        } else if (dirY == -1) {
            dirX = -1;
            dirY = 0;
        } else if (dirX == -1) {
            dirX = 0;
            dirY = 1;
        } else if (dirY == 1) {
            dirX = 1;
            dirY = 0;
        }
    }
    
    /**
     * Die Ant dreht sich gegen den Uhrzeigersinn.
     */
    private void turnCounterClockwise() {
        if (dirX == 0 && dirY == 0) {
            dirX = -1;
        } else if (dirX == -1) {
            dirX = 0;
            dirY = -1;
        } else if (dirY == -1) {
            dirX = 1;
            dirY = 0;
        } else if (dirX == 1) {
            dirX = 0;
            dirY = 1;
        } else if (dirY == 1) {
            dirX = -1;
            dirY = 0;
        }
    }
    
    /**
     * run-Methode des Ant-Threads. Führt kontinuierlich Bewegungen aus und 
     * wartet nach jedem Schritt einige Millisekunden. Der Thread soll 
     * blockieren, wenn die Simulation pausiert ist.
     */
    // BEWERTUNG 0P
    @Override
    public void run() {
        // TODO (5 Punkte)
        // Die Ant soll kontinuierlich laufen bis sie unterbrochen oder 
        // pausiert wird und nach jedem Schritt SLEEP_TIME Millisekunden warten
        synchronized (this) {
            while(this.equals(paused)){
                try {
                    this.wait(SLEEP_TIME);
                } catch (InterruptedException ex) {
                }
            }
        }
            
        }
    
    
    /**
     * Pausiert oder weckt den Thread.
     * @param state 
     */
// BEWERTUNG 0P
    public void setPause(boolean state){
        // TODO (3 Punkte)
       paused = state;
       if (!state) {
            synchronized (this) {
                this.notify();
            }
       }
    }
}
