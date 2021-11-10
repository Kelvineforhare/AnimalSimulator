
/**
 * Interface for all actors able to feed 
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public interface ActorCanEat
{
    /**
     * @return field actor is in
     */
    abstract public Field getField();
    
    /**
     * @return location of actor on field
     */
    abstract public Location getLocation();
    
    /**
     * Checks if food level is below maximum food level
     */
    abstract public void checkFoodLevel();
    
    /**
     * Sets new value to food value
     * @param newFoodValue updated food value
     */
    abstract public void setFoodLevel(int newFoodValue);  
    
    /**
     * @return age of actor
     */
    abstract public int getAge();
    
    /**
     * @return maximum value food level can hold
     */
    abstract public int getMaxFoodLevel();
    
    /**
     * @return current value held in food value
     */
    abstract public int getFoodLevel();
}
