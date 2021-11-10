import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.HashMap;

/**
 * A simple model of a Bear.
 * Bears age, move, eat Prey, and die.
 * 
 * @author David J. Barnes , Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Bear extends Animal
{
    // Characteristics shared by all Beares (class variables).

    // The age at which a Bear can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a Bear can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a Bear breeding.
    private static final double BREEDING_PROBABILITY = 0.06;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The probability that a Bear will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.05;
    //The maximum food Level of a bear
    private static final int MAX_FOOD_LEVEL = 40;

    /**
     * Create a Bear. A Bear can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Bear will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Bear(boolean randomAge, Field field, Location location)
    { 
        super(field, location, randomAge); 
    }
    
    /** 
     * Adds the class name and food value of prey to the canEat hashMap
     */
    public void setCanEat(){ 
        getCanEat().put(Rat.class, 7); 
        getCanEat().put(Deer.class, 15); 
        getCanEat().put(Berry.class, 5); 
        getCanEat().put(Cod.class, 4);
    }
    
    /** 
     * @return breeding age of bear
     */
    public int getBreedingAge() {
        return BREEDING_AGE;
    } 
    
    /** 
     * @return max age of bear
     */
    public int getMaxAge() {
        return MAX_AGE;
    } 
    
    /** 
     * @return breeding probability of bear
     */
    public double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    } 
    
    /** 
     * @return the max amount of births a bear has
     */
    public int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    } 
    
    /**  
     * create a new bearobject
     * @param randomAge If true, the bearwill have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return new bear
     */
    public Animal createActor(boolean randomAge, Field field, Location location) {
        Bear newBear= new Bear(randomAge, field, location);
        return newBear;
    } 
    
    /** 
     * @return bearcreation probability
     */
    public double getCreationProbability() {
        return BEAR_CREATION_PROBABILITY;
    } 
    
    /** 
     * @return the class bear
     */
    public Class getActorClass(){ 
        return getClass();
    }
    
    /**
     * @return max food level of bear
     */
    public int getMaxFoodLevel()
    {
        return MAX_FOOD_LEVEL;
    }
}
