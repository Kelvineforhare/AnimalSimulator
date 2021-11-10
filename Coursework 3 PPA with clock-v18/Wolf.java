import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.HashMap;

/**
 * A simple model of a Wolf.
 * Wolves age, move, hunt animals, and die.
 * 
 * @author David J. Barnes , Michael Kölling , Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Wolf extends Animal
{
    // Characteristics shared by all Wolfes (class variables).

    // The age at which a Wolf can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a Wolf can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a Wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.0;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2; 
    // The probability that a Wolf will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY = 0.05;
    //The maximum food Level of a wolf
    private static final int MAX_FOOD_LEVEL = 40;

    /**
     * Create a Wolf.
     * 
     * @param randomAge If true, the Wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location,randomAge); 
    }   
   
    /** 
     * Adds the class name and food value of prey to the canEat hashMap
     */
    public void setCanEat(){ 
        getCanEat().put(Rat.class, 7); 
        getCanEat().put(Deer.class, 15); 
    } 

    /** 
     * @return breeding age of wolf
     */
    public int getBreedingAge() {
        return BREEDING_AGE;
    } 

    /** 
     * @return max age of wolf
     */
    public int getMaxAge() {
        return MAX_AGE;
    } 

    /** 
     * @return breeding probability of wolf
     */
    public double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    } 

    /** 
     * @return the max amount of births a wolf has
     */
    public int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    } 

    /**  
     * create a new wolf object
     * @param randomAge If true, the Wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return new wolf
     */
    public Animal createActor(boolean randomAge, Field field, Location location) {
        Wolf newWolf = new Wolf(randomAge, field, location);
        return newWolf;
    } 

    /** 
     * @return wolf creation probability
     */
    public double getCreationProbability() {
        return WOLF_CREATION_PROBABILITY;
    } 

    /** 
     * @return the class wolf
     */
    public Class getActorClass() 
    { 
        return getClass();
    }

    /**
     * @return max food level of wolf
     */
    public int getMaxFoodLevel()
    {
        return MAX_FOOD_LEVEL;
    }

}
