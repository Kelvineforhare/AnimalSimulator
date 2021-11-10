import java.util.ArrayList;
import java.util.*;

/**
 * A class representing shared characteristics of Plants.
 * 
 * add repoduction age
 * 
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public abstract class Plant extends Actor
{      
    // A shared random number generator to control reproduction.
    private static final Random rand = Randomizer.getRandom(); 
    
    //Variables that Plants have 
    
    //If plant is grown return true
    private boolean isGrown;  
    
    /**
     * Create a new Plant at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.  
     * @param randomAge If true, the Plant will have a random age.
     */
    public Plant(Field field, Location location, boolean randomAge) 
    { 
        super(field, location, randomAge);  
        checkGrown();
    } 
    
    /** 
     * Changes isGrown to true if age is greater than ripe age
     */
    protected void checkGrown(){ 
        if (getAge() >= getRipeAge()){ 
            isGrown = true;
        }
    }     
    
     /**
     * If reproduction conditions met is a chance reproduction will produce random number of plants
     * @return number of seeds produced
     */
    private int reproduce()
    {
        int seeds = 0; 
        if (getAge() >= getReproductionAge() && rand.nextDouble() <= getReproductionRate()){ //Probability that plant will reproduce
            seeds = rand.nextInt(getMaxNumSeed()); //Number of seeds produced
        }
        return seeds; 
    }
    
    /**
     * Make this Plant act - that is: make it do
     * whatever it wants/needs to do. 
     * Actions differ depending on weather and time of day
     * @param newActors A list to receive newly planted.
     */
    public void act(List<Actor> newPlants, Weather weather , int step)
    {
        Weather currentWeather = weather; 
        switch(getDayOrNight()){ 
            case NIGHT:
            // what do they do at night
            break;
            case DAY:
            switch(currentWeather){
                case RAIN: 
                incrementAge();// plants grow faster in rain
                normalAct(newPlants);
                break;
                case NORMAL:
                normalAct(newPlants); 
                break;
            }
            break;
        }
    }  
    
    private List<Location> allowedLocations(List<Location> locations) 
    {  
        List<Location> allowedLocation = new ArrayList<>();
        for(Location location : locations){ 
            if(!(location.checkIsWaterArea())){ 
                allowedLocation.add(location);
            }
        }   
        return allowedLocation;
    }
    
    /**
     * Check whether or not this plant is to repoduce at this step.
     * New plants will be placed into free adjacent locations.
     * @param newPlants A list to return newly planted plants.
     */
    public void generatePlants(List<Actor> newPlants)
    {
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = allowedLocations(field.getFreeAdjacentLocations(getLocation()));
        int seeds = 0; 
        if (rand.nextDouble() <= getReproductionRate()){ //Probability that plant will reproduce
            seeds = rand.nextInt(getMaxNumSeed()); //Number of seeds produced
        }
        for(int b = 0; b < seeds && free.size() > 0; b++) { 
            Location loc = free.remove(0);
            Plant bud = (Plant) createActor(false, field, loc);
            newPlants.add(bud);
        }
    }
    
    /** 
     * @return true if plant is grown
     */
    public boolean getIsGrown() 
    { 
        return isGrown;
    }
    
     /**
     * @return rate at which new plants produced
     */
    abstract protected double getReproductionRate();

    /**
     * @return maximum number of seeds reproduced from one plant in one step
     */
    abstract protected int getMaxNumSeed(); 

    /**
     * @return new plant
     */
    abstract protected Actor createActor(boolean randomAge, Field field, Location location);

    /**
     * @return age above which plant can be eaten
     */
    abstract protected int getRipeAge();

    /**
     * Actions of plants disregarding change in time of day or weather conditions
     */
    abstract protected void normalAct(List<Actor> newPlants);

    /**
     * @return  age above which plant can reproduce
     */
    abstract protected int getReproductionAge();
}
