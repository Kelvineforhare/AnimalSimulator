import java.util.List;
import java.util.Random;
import java.util.HashMap;

/**
 * A simple model of a Rat.
 * Rats age, move, breed,eat plants and die.
 * 
 * @author David J. Barnes and Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Rat extends Animal
{
    // Characteristics shared by all Rats (class variables).

    // The age at which a Rat can start to breed.
    private static final int BREEDING_AGE = 1;
    // The age to which a Rat can live.
    private static final int MAX_AGE = 10;
    // The likelihood of a Rat breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;
    // The probability that a rat will be created in any given grid position.
    private static final double RAT_CREATION_PROBABILITY = 0.09; 
    //The maximum food Level of a rat
    private static final int MAX_FOOD_LEVEL = 10;
    
    /**
     * Create a new Rat. A Rat may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the Rat will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rat(boolean randomAge, Field field, Location location)
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
     * @return breeding age of rat
     */
    public int getBreedingAge() {
        return BREEDING_AGE;
    } 
    
    /** 
     * @return max age of rat
     */
    public int getMaxAge() {
        return MAX_AGE;
    } 
    
    /** 
     * @return breeding probability of rat
     */
    public double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    } 
    
    /** 
     * @return the max amount of births a rat has
     */
    public int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    } 
    
    /**  
     * Creates a new rat object
     * @param randomAge If true, the rat will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return new rat
     */
    public Animal createActor(boolean randomAge, Field field, Location location) {
        Rat newRat = new Rat(randomAge, field, location);
        return newRat;
    } 
    
    /** 
     * @return rat creation probability
     */
    public double getCreationProbability() {
        return RAT_CREATION_PROBABILITY;
    } 
    
    /** 
     * @return the class rat
     */
    public Class getActorClass(){ 
        return getClass();
    }
    
     /**
     * @return max food level of rat
     */
    public int getMaxFoodLevel()
    {
        return MAX_FOOD_LEVEL;
    }
}