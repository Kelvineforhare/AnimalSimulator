import java.util.*;

/**
 * A simple model of a Berry.
 * Berries age, reproduce and die.
 * 
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Berry extends Plant
{ 
    // Characteristics shared by all Berries (class variables). 

    //Likelihood of a berry reproducing
    private static final double REPRODUCTION_RATE = 0.09; 
    //Max age berry can reach before dying
    private static final int MAX_AGE = 10; 
    //The age at which berries can be consumed
    private static final int RIPE_AGE = 4; 
    //The maximum number of berries that can be planted
    private static final int MAX_SEED_NUM = 4;  
    //The probability that a berry will be created in any given grid position.
    private static final double BERRY_CREATION_PROBABILITY = 0.08;
    //The age above which berry can reproduce
    private static final int REPRODUCTION_AGE = 5;

    /**
     * Create a new Berry. A Berry may be created with age
     * zero or with a random age.
     * 
     * @param randomAge If true, the Berry will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Berry(boolean randomAge, Field field, Location location)
    {
        super(field, location, randomAge);
    }

    /**
     * Make this Berry act - that is: make it do
     * whatever it wants/needs to do. 
     * Without any weather conditions appied
     * @param newPlant A list to receive newly produced Plants.
     */
    public void normalAct(List<Actor> newPlants) 
    { 
        checkGrown();
        incrementAge(); 
        if(isAlive()) {
            generatePlants(newPlants);
        }
        else {
            // Overcrowding.
            setDead();
        }
    } 

    /** 
     * @return max age of rat
     */
    public int getMaxAge() 
    { 
        return MAX_AGE;
    }  

    /** 
     * @return rat creation probability
     */
    public double getCreationProbability() 
    { 
        return BERRY_CREATION_PROBABILITY;
    }

    /** 
     * @return reproduction rate of berry
     */
    protected double getReproductionRate()
    {
        return  REPRODUCTION_RATE;
    }

    /**  
     * Creates a new berry object
     * @param randomAge If true, the berry will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return new berry
     */
    protected Plant createActor(boolean randomAge, Field field, Location location)
    {
        Berry berry = new Berry (randomAge, field, location);
        return berry;
    }  

    /** 
     * @return maximum number of seed 1 berry can produce
     */
    protected int getMaxNumSeed()
    {
        return MAX_SEED_NUM;
    }

    /** 
     * @return age at which berry can be eaten
     */
    protected int getRipeAge()
    {
        return RIPE_AGE;
    }

    /**
     * @return age above which berry can reproduce
     */
    protected int getReproductionAge()
    {
        return REPRODUCTION_AGE;
    }
}
