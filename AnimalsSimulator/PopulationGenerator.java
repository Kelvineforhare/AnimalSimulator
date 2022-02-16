import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.util.HashMap;
import java.util.Set;

/**
 * Create the actor population which occupies the simulation field and set their color. 
 * 
 * 
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class PopulationGenerator
{
    // The field of the simulation.
    private Field field; 
    // A graphical view of the simulation.
    private SimulatorView view; 
    //Stores actors present in simulation and their creation probability
    private HashMap<Actor, Double> animalCreation; 
    //holds the size of waterin field
    private int waterSize;
    /**
     * Constructor for objects of class PopulationGenerator
     * @param field The field of the simulation.
     * @param view The graphical view of the simulation.
     * @param waterSize The size of water area in field
     */
    public PopulationGenerator(Field field, SimulatorView view , int waterSize)
    {
        this.field = field;
        this.view = view; 
        this.waterSize = waterSize;
        animalCreation = new HashMap<>();

        setAnimalColor();
        setAnimalCreation();

    }

    /**
     * Sets color for all animals in simulation
     */
    private void setAnimalColor()
    {
        view.setColor(Deer.class, Color.yellow);
        view.setColor(Bear.class, Color.orange);
        view.setColor(Wolf.class, Color.red);
        view.setColor(Rat.class, Color.pink); 
        view.setColor(Berry.class, Color.magenta);   
        view.setColor(Cod.class, Color.black); 
        view.setColor(Shrimp.class, Color.orange);
    }

    /**
     * Creates animal objects and adds them and their creation
     * probability to animalCreation
     */
    private void setAnimalCreation()
    {
        //used to set new animals
        Bear b1 = new Bear(true,null,null); 
        Deer d1 = new Deer(true,null,null); 
        Wolf w1 = new Wolf(true,null,null); 
        Rat r1 = new Rat(true,null,null); 
        Berry b2 = new Berry(true,null,null); 
        Cod c1 = new Cod(true,null,null); 
        Shrimp s1 = new Shrimp(true,null,null);
        animalCreation.put(b1, b1.getCreationProbability()); 
        animalCreation.put(d1, d1.getCreationProbability()); 
        animalCreation.put(w1, w1.getCreationProbability()); 
        animalCreation.put(r1, r1.getCreationProbability()); 
        animalCreation.put(b2, b2.getCreationProbability()); 
        animalCreation.put(c1, c1.getCreationProbability()); 
        animalCreation.put(s1, s1.getCreationProbability());
    }

    /**
     * Randomly populate the field with actors.
     * Actors are limited to where they can be created in the field based on their type
     * Sea creatures can only be created in water and all other actors on land
     * @return A list of the actors that occupy the field.
     */
    public List<Actor> populate()
    {
        List<Actor> actors = new ArrayList<Actor>();
        Random rand = Randomizer.getRandom(); 
        field.clear(); 
        Set<Actor> animalKey = animalCreation.keySet(); 
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {    
                if (col >= waterSize){ //all actors past water size must be sea creatures
                    Location location = new Location(row, col);  
                    for(Actor actor:animalKey){ 
                        if(rand.nextDouble() <= animalCreation.get(actor)) {  
                            if (actor instanceof SeaCreature){  
                                Actor newActor = actor.createActor(true, field, location);   
                                actors.add(newActor);    
                                break;
                            }   
                        }
                    }
                }  
                else{ 
                    for(Actor actor : animalKey){ 
                        if(rand.nextDouble() <= animalCreation.get(actor)) {  
                            if (col < waterSize && !(actor instanceof SeaCreature) ){   
                                Location location = new Location(row,col); 
                                Actor newActor = actor.createActor(true, field, location);   
                                actors.add(newActor);   
                                break;
                            } 
                        } 
                    } 
                }
            }
        }  
        return actors;
    } 
} 
