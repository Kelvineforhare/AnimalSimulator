import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.Random; 

/**
 * Class that contains methods required to simulate feeding
 * Only animals that implement ActorCanFeed may use these methods
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01

 */
public class FeedingMechanism
{
    
    // holds actor that is currently finding food 
    private ActorCanEat thisActor;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();  

    /**
     * Create an object that contains methods that simulate feeding
     * @param thisActor Actor that is currently finding food 
     */
    public FeedingMechanism(ActorCanEat thisActor)
    {
        this.thisActor = thisActor;
    }

     /**  
     * Scope of findFood changes with weather
     * @param potentialPrey the hashmap of the prey and food value that actor consumes 
     * @param weather the current weather
     * @return location of the food
     */
    public Location findFood(HashMap<Class<? extends Actor>, Integer> potentialPrey,Weather weather)
    {
        Field field = thisActor.getField();
        List<Location> adjacent = field.adjacentLocations(thisActor.getLocation());
        Iterator<Location> it = adjacent.iterator(); 
        switch(weather){ 
            case FOG: //Only 1 random adjacent location is checked for food
            foodLocation(potentialPrey, field.randomAdjacentLocation(thisActor.getLocation()),field);
            break;
            case NORMAL: //All adjacent locations are checked for food
            while(it.hasNext()) {  
                Location where = foodLocation(potentialPrey, it.next(), field);
                if (where != null){ 
                    return where;
                }
            } 
            break;
        }  
        return null;
    } 

    /**  
     * Checks location to see if it is actors prey
     * @param potentialPrey the hashmap of the prey and food value that actor consumes 
     * @param field the field 
     * @param Location the location to check
     * @return location the location of the actor it can eat
     */
    public Location foodLocation(HashMap<Class<? extends Actor>, Integer> potentialPrey,Location location, Field field)
    {
        Location where = location;          
        Object potentialFood = field.getObjectAt(where); 
        Set<Class<? extends Actor>> preyKeys = potentialPrey.keySet(); 
        if (potentialFood != null){
            for (Class preyType: preyKeys){ 
                if (preyType.equals(potentialFood.getClass())){
                    Actor food = (Actor) potentialFood;
                    if(food.isAlive()){ 
                        if(food instanceof Plant){ //Check if plant is ripe
                            Plant plantFood = (Plant) food; 
                            if(!plantFood.getIsGrown()){ 
                                return null;
                            }
                        }
                        food.setDead();
                        thisActor.setFoodLevel(potentialPrey.get(food.getClass()) + thisActor.getFoodLevel());
                        thisActor.checkFoodLevel();
                        return where;
                    }
                }
            } 
        }
        return null;
    }
    
    /**
     * Sets the actor's food level
     * If newborn has max food level otheriwse random food 
     * level provided
     */
    public void setInitialFoodValue() 
    { 
        if (thisActor.getAge() == 0){
            thisActor.setFoodLevel(thisActor.getMaxFoodLevel());
        }
        else{
            thisActor.setFoodLevel(rand.nextInt(thisActor.getMaxFoodLevel() + 1));
        } 
    }  
}
