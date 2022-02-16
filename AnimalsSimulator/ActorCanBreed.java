import java.util.List;
/**
 * Interface for all actors that are able to breed
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public interface ActorCanBreed
{
    /**
     * @return List of actors that are adjacent to actor
     */
    abstract public List<ActorCanBreed> meet();
    
    /**
     * @return maximum number of offspring produced in breeding
     */
    abstract public int getMaxLitterSize();
    
    /**
     * @return probability that actor will breed if all breeding conditions met
     */
    abstract public double getBreedingProbability();
    
    /**
     * @return minimum age needed before actor can breed
     */
    abstract public int getBreedingAge();
    
    /**
     * @return true of actor female
     */
    abstract public boolean getIsFemale();
    
    /**
     * @return age of actor
     */
    abstract public int getAge();
    
    /**
     * @return current field actor on
     */
    abstract public Field getField();
    
    /**
     * @return location of actor
     */
    abstract public Location getLocation();
    
    /**
     * @return new actor
     */
    abstract public Actor createActor(boolean randomAge, Field field, Location location);
    
    /**
     * @return class of actor
     */
    abstract public Class getActorClass();
    
    /**
     * @return true of animal above breeding age
     */
    abstract public boolean checkBreedingAge();
    
    /**
     * @return breeding mechanism object for actor
     */
    abstract public BreedingMechanism getBreedingMechanism();
    
    /**
     * @ return list of allowed location actors can move into
     */
    abstract public List<Location> allowedLocations(List<Location> locations);
}
