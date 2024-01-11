package de.hawhh.ants;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.print.Collation;
import javafx.scene.paint.Color;

/**
 * Eine Simulation von Langton's Ants.
 * Bewertung: max. 10 Punkte
 * BEWERTUNG 0P
 * @author XX
 */
public class Simulation {
    /** Wie viele Ants in der Simulation mitmachen */
    public static int NUM_ANTS = 3;
    
    /** Höhe und Breite des Gitters */
    public static int GRID_SIZE = 40;
    
    /** Ein Verweis auf die aktuell laufende Simulation (Singleton-Muster) */
    private static Simulation current = null;
    
    /**
     * Liefert die aktuell laufende Simulation zurück.
     * @return Eine Referenz auf ein Simulation-Objekt oder null. 
     */
    public static Simulation current() {
        return current;
    }
    
    /** 
     * Erstellt eine neue Simulation. Die neue Simulation ist zunächst nicht
     * gestartet und muss dann noch mit start() gestartet werden. Falls bereits
     * eine Simulation läuft, so wird diese vorher beendet.
     * @return Eine Referenz auf ein Simulation-Objekt.
     */
    public static Simulation create() {
        current = new Simulation();
        return current();
    }
    
    /** Das Grid, auf dem die Simulation läuft */
    private final Grid grid;
    
    /** Die Ants-Objekte (Threads) */
    private final Ant[] ants = new Ant[NUM_ANTS];
    
    private boolean paused = false;
    
  //  private Simulation() {
    //    this.grid = new Grid(GRID_SIZE, GRID_SIZE);
        
        // TODO (4 Punkte)
        // Jede Grid-Änderung (siehe GridChangeListener) soll auf der
        // Konsole ausgegeben werden.
        // Erzeugen Sie dazu eine eigene Implementierung eines GridChangeListeners,
        // die Sie hier an this.grid anbinden können. Nutzen Sie eine
        // Implementierung Ihrer Wahl!
           private Simulation() {
        this.grid = new Grid(GRID_SIZE, GRID_SIZE);

         this.grid.addListener((Grid grid1, int x, int y, Color newColor) -> {
             System.out.println("Grid change at (" + x + ", " + y + ") to color " + newColor);
        });
    }
        // Attach an anonymous GridChangeListener to the grid for console output
     //   this.grid.addListener((Grid grid1, int x, int y, Color newColor) -> {
       //     System.out.println("Grid change at (" + x + ", " + y + ") to color " + newColor);
      //  });
  //  }
       // );
   // }
        
  //  }
    
    /**
     * @return Das mit dieser Simulation verknüpfte Grid.
     */
    public Grid getGrid() {
        return grid;
    }
    
    /** 
     * Startet die Simulation. Dazu werden alle Ant-Threads erstellt und 
     * gestartet.
     * @return true, wenn die Simulation gestartet wurde, false wenn sie schon
     * läuft.
     */
    public boolean start() {
        // TODO (2 Punkte)
        synchronized (this) {
             if (paused) {
                return false;
            }
        
        Stream.generate(() -> new Ant(grid)).limit(GRID_SIZE).collect(Collectors.toList());
            current.start();
            return true;
         // TODO Anpassen
        }
    }
    
    /**
     * Stoppt die laufende Simulation. Wenn die Simulation nicht läuft, hat der
     * Aufruf von stop() keinen Effekt.
     */
    public void stop() {
       
    }
    
    /**
     * Pausiert die Simulation oder setzt sie fort. Ruft setPause an jeder Ant 
     * auf.
     * @return Liefert true zurück, wenn die Simulation nach dem Aufruf pausiert
     * ist, andernfalls false.
     */
    public boolean togglePause() {
        // TODO (2 Punkte)
        synchronized (current) {
            
        
                if(current.equals(togglePause())){
                    try {
                        current.wait();
                        return true;
                    } catch (InterruptedException ex) {
                    }
                }
                    
        return false; // TODO Anpassen
        }
    }
}
