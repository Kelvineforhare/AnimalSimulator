import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of Actors.
 * 
 * @author David J. Barnes and Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public abstract class Actor
{ 
   
    // The Actors's age
    protected int age;
    // Whether the Actor is alive or not.
    private boolean alive; 
    // The Actor's field.
    private Field field;
    // The Actor's position in the field.
    private Location location;
    
    // A shared random number generator to control initial set age.
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new Actor at location in field.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge If true, the Actor will have a random age.
     */
    public Actor(Field field, Location location, boolean randomAge)
    {
        alive = true;
        this.field = field;
        setLocation(location); 
        //If randomAge true sets actor's age to random number below max age
        if(randomAge) {
            age = rand.nextInt(getMaxAge());
        }
    } 

    /**
     * Place the Actor at the new location in the given field.
     * @param newLocation The Actor's new location.
     */
    protected void setLocation(Location newLocation)
    { 
        if (newLocation != null){
            if(location != null) {
                field.clear(location);
            } 
            location = newLocation;
            field.place(this, newLocation);
        }  
    }

    /**
     * @return The Actor's location.
     */
    public Location getLocation()
    {
        return location;
    }   

    /**
     * Increase the age. This could result in the actor's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > getMaxAge()) {
            setDead();
        }
    } 

    /**
     * @return The Actor's field.
     */
    public Field getField()
    {
        return field;
    }

    /**
     * @return true if animal is alive
     */
    protected boolean isAlive(){ 
        return alive;
    }  

    /**
     * Indicate that the Actor is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * @return actor's age
     */
    public int getAge() 
    {
        return age;
    }

    /**
     * Make this Actor act - that is: make it do
     * whatever it wants/needs to do. 
     * Actions differ based on weather condition
     * @param newActors A list to receive newly born Actors
     * @param weather current weather
     */
    abstract protected void act(List<Actor> newActors,Weather weather , int step); 

    /**
     * @ return max age of actor after which actor dies
     */
    abstract protected int getMaxAge(); 

    /**
     * @return probability that a actor is created, in intial field setup
     */
    abstract protected double getCreationProbability(); 

    /**
     * @return new actor
     */
    abstract protected Actor createActor(boolean randomAge, Field field, Location location);
    
     /** 
     * @return Enumerated values day or night 
     */
    protected DayOrNight getDayOrNight() 
    { 
        return DayOrNight.getDayOrNight();
    }
}
