import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * Simulator has land and water area and models forest
 * 
 * @author David J. Barnes and Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 200;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 150;    
    // Default size of water on field
    private static final int DEFAULT_WATER_SIZE = 112; 

    // Holds the size of water
    private int waterSize;

    // List of Actors in the field.
    private List<Actor> actor;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;  
    // used to get weather
    private Weather weather = Weather.NORMAL;
    // holds current weather
    private Weather currentWeather = weather.getNormal();

    public static void main(String[] args){
        Simulator simulator = new Simulator();
        simulator.runLongSimulation();
    }

    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH , DEFAULT_WATER_SIZE); 
    } 

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     * @param waterSize Size of water. Must be less than width of field
     */
    public Simulator(int depth, int width , int waterSize)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        } 
        if(waterSize > width){ 
            System.out.println("Water size cannot be larger than simulation size"); 
            this.waterSize = (int) 0.75 * width; // Default size set based on inputted width
        }
        this.waterSize = waterSize;
        actor = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width); 

        Location.setWaterArea(waterSize);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4320 steps).
     */
    public void runLongSimulation()
    {
        simulate(3600);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep(); 
            //delay(200);   // uncomment this to run more slowly
        } 
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each actor
     * Weather conditions change every 5 steps
     * 
     */
    public void simulateOneStep()
    {
        step++; 
        DayOrNight.incrementTime(step); 

        // Provide space for newborn Actors.
        List<Actor> newActor = new ArrayList<>();     
        // Let all actors  act.  
        if (step % 5 == 0){ //works every 5 steps
            currentWeather = weather.getWeather(); 
        } 

        for(Iterator<Actor> it = actor.iterator(); it.hasNext(); ) {
            Actor actor = it.next(); 
            if(! actor.isAlive()) {
                it.remove();
            }
            actor.act(newActor,currentWeather,step);
        }

        // Add the newly born actors to the main lists.
        actor.addAll(newActor);

        view.showStatus(step, field, currentWeather , waterSize);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actor.clear();
        PopulationGenerator popGen = new PopulationGenerator(field, view , waterSize);
        actor = popGen.populate();   
        int test = waterSize;
        // Show the starting state in the view.
        view.showStatus(step, field, currentWeather, waterSize);
    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }

}
