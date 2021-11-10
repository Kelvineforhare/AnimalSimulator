import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

/**
 *  A class representing shared characteristics of Sea Creatures.
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public abstract class SeaCreature extends Actor
implements ActorCanBreed
{
    // A shared random number generator
    private static final Random rand = Randomizer.getRandom();   
    // If animal is female set as true
    private boolean isFemale; 
    // Holds object to access breeding methods
    protected BreedingMechanism breedingMechanism; 
    
    /**
     * Create a new Sea Creature at location in field.
     * make foodLevel random
     * @param randomAge If true, the sea creature  will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public SeaCreature(Field field, Location location, boolean randomAge )
    {
        super(field, location, randomAge);  
        isFemale = rand.nextBoolean();
        breedingMechanism = new BreedingMechanism(this);
    } 

    /** 
     * Returns a list of all animal actors adjacent to sea creature
     * @return list of adjacent sea creatures of same type
     */
    public List<ActorCanBreed> meet() 
    { 
        List<ActorCanBreed> adjacentActors = new ArrayList<ActorCanBreed>();
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Actor actor = (Actor) field.getObjectAt(where);  
            if (actor != null && actor.getClass().equals(getClass())){ 
                adjacentActors.add((ActorCanBreed) actor); 
            }
        }
        return adjacentActors;
    }  

    /**
     * Produces a list of locations that are on water that sea creatures can move into
     * One of that locations on list randomly selected
     * @return randomly selected location from list fo allowed locations
     */
    public List<Location> allowedLocations(List<Location> locations) 
    {  
        List<Location> allowedLocation = new ArrayList<>();
        for(Location location : locations){ 
            if(location.checkIsWaterArea() == true){ 
                allowedLocation.add(location);
            }
        }   
        if(allowedLocation.size() == 0){ 
            return null;
        }
        return allowedLocation;
    }
    
    /**
     * Randomly selects location from allowed locations
     * @return Location from list allowed locations
     * @param allowedLocation list of allowed locations actor can move to
     */
    public Location pickAllowedLocation(List<Location> allowedLocation)
    {
        if (allowedLocation != null){
        int randomLocation = rand.nextInt(allowedLocation.size()); 
        Location allowed = allowedLocation.get(randomLocation);
        return allowedLocation.get(randomLocation);}
        else{
            return null;
        }
    }

    /**
     * @return BreedingMechanism object
     */
    public BreedingMechanism getBreedingMechanism()
    {
        return breedingMechanism; 
    }

    /**
     * An animal can breed if it has reached the breeding age. 
     * @return true if age above breeding age
     */
    public boolean checkBreedingAge()
    {
        return age >= getBreedingAge();
    }
    
    /** 
     * @return true if animal is female
     */
    public boolean getIsFemale() 
    { 
        return isFemale;
    } 
    
    /**
     * Make this sea creature act - that is: make it do
     * whatever it wants/needs to do. 
     * Actions differ from night and day
     * @param newActors A list to receive newly born sea creatures
     * @param weather Current weather
     * @param step How long sea creature infected by disease 
     */
    abstract protected void act(List<Actor> newAnimals , Weather weather ,int step);
    
    /**
     * @return maximum age of sea creature
     */
    abstract protected int getMaxAge(); 

    /**
     * @return probability sea craeture created on field when initialised
     */
    abstract protected double getCreationProbability(); 

    /**
     * @return new sea creature 
     */
    abstract public Actor createActor(boolean randomAge, Field field, Location location); 

    /**
     * @return minimum age needed to be to breed
     */
    abstract public int getBreedingAge();

    /**
     * @return probability of breeding if all breeding condtions met 
     */
    abstract public double getBreedingProbability();

    /**
     * @return maximum number of offspring if gives birth
     */
    abstract public int getMaxLitterSize(); 

    /**
     * @return class
     */
    abstract public Class getActorClass();
}
