import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Set;

/**
 * A simple model of a Deer.
 * Deers age, move, breed, eat plants and die.
 * 
 * @author David J. Barnes and Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Deer extends Animal
{
    // Characteristics shared by all Deers (class variables).

    // The age at which a Deer can start to breed.
    private static final int BREEDING_AGE = 4;
    // The age to which a Deer can live.
    private static final int MAX_AGE = 30;
    // The likelihood of a Deer breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // The probability that a deer will be created in any given grid position.
    private static final double DEER_CREATION_PROBABILITY = 0.09;  
    //The maximum food Level of a deer
    private static final int MAX_FOOD_LEVEL = 20;
    
    /**
     * Create a new Deer. A Deer may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the Deer will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Deer(boolean randomAge, Field field, Location location)
    {
        super(field, location, randomAge);  
    } 
    
    /** 
     * Adds the class name and food value of plant to the canEat hashMap
     */
     public void setCanEat(){ 
        getCanEat().put(Berry.class, 5);
    } 
    
       /** 
     * @return breeding age of deer
     */
    public int getBreedingAge() {
        return BREEDING_AGE;
    } 
    
    /** 
     * @return max age of deer
     */
    public int getMaxAge() {
        return MAX_AGE;
    } 
    
    /** 
     * @return breeding probability of deer
     */
    public double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    } 
    
    /** 
     * @return the max amount of births a deer has
     */
    public int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    } 
    
    /**  
     * create a new deer object
     * @param randomAge If true, the deer will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return new deer
     */
    public Animal createActor(boolean randomAge, Field field, Location location) {
        Deer newDeer = new Deer(randomAge, field, location);
        return newDeer;
    } 
    
    /** 
     * @return deer creation probability
     */
    public double getCreationProbability() {
        return DEER_CREATION_PROBABILITY;
    } 
    
    /** 
     * @return the class deer
     */
    public Class getActorClass(){ 
        return getClass();
    }
    
     /**
     * @return max food level of deer
     */
    public int getMaxFoodLevel()
    {
        return MAX_FOOD_LEVEL;
    }
}
