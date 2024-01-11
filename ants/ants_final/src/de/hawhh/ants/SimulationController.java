package de.hawhh.ants;

import javafx.event.Event;
import javafx.scene.control.Button;

/**
 * Controller zur Steuerung der Simulation.
 * Bewertung: max. 4 Punkte
 * BEWERTUNG 1P
 * @author XX
 */
public class SimulationController {

    public void createNewSimulation(SimulationViewer view) {
        var sim = Simulation.create();

        GridView gridView = new GridView(sim.getGrid());

        view.getLayout().setCenter(gridView);
        view.getStage().sizeToScene();
    }

    public void startSimulation(Event evt) {
        if (Simulation.current() == null) {
            return;
        }
        Simulation.current().start();
    }

    /**
     * Pausiert eine Simulation oder setzt sie fort.
     * @param evt 
     */
    // BEWERTUNG 1P
    public void pauseOrContinue(Event evt) {
        // TODO (4 Punkte)
        // Wurde die Simulation pausiert, soll sich der Buttontext auf ">" ändern,
        // ansonsten wenn sie fortgesetzt wurde wieder auf "||".
        // Läuft keine Simulation, soll nichts passieren.
        // Nutzen Sie geeignete Methode von evt, um auf das Button-Objekt zuzugreifen.
       /** synchronized (evt) {
          if(Simulation.current().togglePause()==true){
            
        }  
        }
        
            
        }
        */
         Simulation currentSimulation;
        currentSimulation = Simulation.current();
    if (currentSimulation == null) {
        return;
    }

    // Zugriff auf das Button-Objekt über das Event
    Button button = (Button) evt.getSource();

    // Toggle-Pause für die aktuelle Simulation
    boolean simulationPaused = currentSimulation.togglePause();

    // Ändere den Buttontext basierend auf dem aktuellen Pausenstatus
    if (simulationPaused) {
        button.setText(">");
    } else {
        button.setText("||");
    }
    }
}

