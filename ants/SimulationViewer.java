package de.hawhh.ants;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX Applikation zur Darstellung der Simulation.
 * Bewertung: max. 3 Punkte
 * BEWERTUNG 0P
 * @author XX
 */
public class SimulationViewer extends Application {

    private final SimulationController controller = new SimulationController();
    private final BorderPane layout = new BorderPane();
    private Stage stage;
    
    public BorderPane getLayout() {
        return layout;
    }
    
    public Stage getStage() {
        return stage;
    }
    
    /**
     * Start und Initialisierung der Oberfläche.
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception { 
        this.stage = stage;
        
        // Button zum Erstellen einer neuen Simulation
        Button newSim = new Button("Neue Simulation");
        
        // TODO Verknüpfen Sie die Button-Click-Aktion mit createNewSimulation() im Controllers.
        // TODO Nutzen Sie dazu ein Lambda-Objekt oder Methodenreferenz!
        //controller.createNewSimulation(this);
        newSim.setOnAction(e -> controller.createNewSimulation(this));
        
        // Button zum Starten einer Simulation
        Button startSim = new Button("Start");
        // TODO Verknüpfen Sie die Button-Click-Aktion mit startSimulation() im Controllers.
        
       // startSim.setOnAction(e -> controller.startSimulation());
       // startSim.addEventFilter(EventType.ROOT, eh);
        // Button zum Pausieren/Fortsetzung einer Simulation
        Button pauseSim = new Button("||");
        // TODO Verknüpfen Sie die Button-Click-Aktion mit pauseOrContinue() im Controller.
        
        ToolBar toolbar = new ToolBar(newSim, startSim, pauseSim);
        layout.setTop(toolbar);
        
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Ants Simulation");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
        stage.setOnCloseRequest((evt) -> {
            System.out.println("Shutdown simulation");
            Simulation.current().stop();
        });
    }
    
}
