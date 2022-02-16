import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

/**
 * A simple model of a cod.
 * Cods age, move, hunt animals, and die.
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Cod extends SeaCreature
implements ActorCanEat
{
    //Charectaristics all cod share

    //The minimum age cod need to be to be able to breed
    private static final int BREEDING_AGE = 15;
    // The age to which a Cod can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a Cod breeding.
    private static final double BREEDING_PROBABILITY = 0.055;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2; 
    // The probability that a Cod will be created in any given grid position.
    private static final double COD_CREATION_PROBABILITY = 0.04;   
    // Maximum food level of cod
    private static final int MAX_FOOD_LEVEL = 15;

    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();  

    // variable that hold foodLevel of cod
    private int foodLevel;
    // Holds the actors and their food levels which the cod can eat
    private HashMap<Class<? extends Actor>,Integer> canEat;
    // Holds object that allows cod to acces feed methods 
    private FeedingMechanism feedingMechanism;

    /**
     * Create a cod actor; create and set actors cpd can eat
     * Set food level of cod
     * @param randomAge If true, the shrimp will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cod(boolean randomAge, Field field, Location location)
    {
        super(field, location,randomAge);  
        canEat = new HashMap<>(); 
        setCanEat(); 
        feedingMechanism = new FeedingMechanism(this);
        feedingMechanism.setInitialFoodValue();
    } 

    /**  
     * Adds the class name and food value of prey to the canEat hashMap
     */
    public void setCanEat(){ 
        canEat.put(Shrimp.class, 7); 
    }   

    /**
     * Make this cod act - that is: make it do
     * whatever it wants/needs to do. 
     * Actions differ from night and day
     * @param newActors A list to receive newly born cod
     * @param weather Current weather
     * @param step How long cod infected by disease 
     */
    protected void act(List<Actor> newAnimals ,Weather weather, int step)
    { 
        switch(getDayOrNight()){ 
            case NIGHT:  
            break; 
            case DAY: 
            incrementAge();
            if(isAlive()) {
                breedingMechanism.giveBirth(newAnimals);            
                Location newLocation = feedingMechanism.findFood(canEat,weather);  
                if (newLocation == null){ 
                    newLocation = pickAllowedLocation(allowedLocations(getField().getFreeAdjacentLocations(getLocation()))); 
                } 
                // See if it was possible to move. 
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                } 
            }
            break;
        } 
    }

    /**
     * @return maxmim age cod can reach
     */
    protected int getMaxAge() 
    { 
        return MAX_AGE;
    } 

    /**
     * @return probability cod will be created when setting up field
     */
    protected double getCreationProbability() 
    { 
        return COD_CREATION_PROBABILITY;
    } 

    /**
     * Create new cod object
     * @return new cod object
     * @param randomAge If true, the cod will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Actor createActor(boolean randomAge, Field field, Location location) 
    { 
        Cod newCod = new Cod(randomAge, field, location);
        return newCod;
    } 

    /**
     * @return minimum age nedded to be able to breed
     */
    public int getBreedingAge() 
    { 
        return BREEDING_AGE;
    }

    /**
     * @return probability breeding will occur if all breeding conditions met
     */
    public double getBreedingProbability() 
    { 
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of offspring that can be produced
     */
    public int getMaxLitterSize() 
    { 
        return MAX_LITTER_SIZE;
    }

    /**
     * @return Cod class
     */
    public Class getActorClass() 
    { 
        return getClass();
    }  

    /**
     * @return maximum food level
     */
    public int getMaxFoodLevel()
    {
        return MAX_FOOD_LEVEL;
    }

    /**
     * @return current food level
     */
    public int getFoodLevel()
    {
        return foodLevel;
    }

    /**
     * Sets food level to new value
     * @param value New value for food level
     */
    public void setFoodLevel(int value)
    {
        foodLevel = value;
    }

    /**
     * Does not allow food level to increase above max food level
     */
    public void checkFoodLevel()
    {
        if (foodLevel > getMaxFoodLevel()){
            foodLevel = getMaxFoodLevel();
        }
    }
}
