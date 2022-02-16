import java.util.List;

/**
 * A simple model of a Shrimp.
 * Shrimps age, move and die.
 * 
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Shrimp extends SeaCreature
{
    //Charectarstics all shrimp share

    private static final int BREEDING_AGE = 5;
    // The age to which a shrimp can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a shrimp breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 7; 
    // The probability that a shrimp will be created in any given grid position.
    private static final double SHRIMP_CREATION_PROBABILITY = 0.1;

    /**
     * Create a new shrimp
     * @param randomAge If true, the shrimp will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Shrimp(boolean randomAge, Field field, Location location)
    {
        super(field, location,randomAge); 
    } 

    /**
     * Make this shrimp act - that is: make it do
     * whatever it wants/needs to do. 
     * @param newActors A list to receive newly born shrimps
     * @param weather Current weather
     * @param step How long shrimp infected by disease 
     */
    protected void act(List<Actor> newAnimals, Weather weather , int step)
    { 
        switch(getDayOrNight()){ 
            case NIGHT:  
            break; 
            case DAY:
            incrementAge();
            if(isAlive()) {
                breedingMechanism.giveBirth(newAnimals);            
                Location newLocation = pickAllowedLocation(allowedLocations(getField().getFreeAdjacentLocations(getLocation())));  
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
     * @return maximum age shrimp can live up to
     */
    protected int getMaxAge() 
    { 
        return MAX_AGE;
    } 

    /**
     * @return probability of shrimp being created when field initially set up
     */
    protected double getCreationProbability() 
    { 
        return SHRIMP_CREATION_PROBABILITY;
    } 

    /**
     * @return new shrimp
     * @param randomAge If true, the shrimp will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Actor createActor(boolean randomAge, Field field, Location location) 
    { 
        Shrimp newShrimp = new Shrimp(randomAge, field, location);
        return newShrimp;
    } 

    /**
     * @return age at which shrimp can breed
     */
    public int getBreedingAge() 
    { 
        return BREEDING_AGE;
    }

    /**
     * @return shrimp's probability fo breeding if all breeding conditions met
     */
    public double getBreedingProbability() 
    { 
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of new shrimp produced when bred
     */
    public int getMaxLitterSize() 
    { 
        return MAX_LITTER_SIZE;
    }

    /**
     * @return class of shrimp
     */
    public Class getActorClass() 
    { 
        return getClass();
    }
}
