import java.util.List;
import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A class representing shared characteristics of Animals.
 * 
 * @author David J. Barnes and Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public abstract class Animal extends Actor
implements ActorCanBreed, ActorCanEat

{
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();  

    //Variables that animals have 

    //If animal is female set as true
    private boolean isFemale; 
    //The animal food level, which is increased by eating Food
    private int foodLevel; 
    //If the animal is infected with a disease set to true
    private boolean isInfected;
    //Hash map containing a the class type and food value of animal's prey
    private HashMap<Class<? extends Actor>,Integer>  canEat;
    //List containing all diseases animal infected with
    private List<Disease> diseasesInfectedWith; 

    // Holds object to access breeding methods
    private BreedingMechanism breedingMechanism; 
    // Holds object to access feeding methods 
    private FeedingMechanism feedingMechanism;
    // Holds object to access disease methods
    private DiseaseMechanism diseaseMechanism; 

    /**
     * Create a new Animal at location in field.
     * make foodLevel random
     * @param randomAge If true, the Animal will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location, boolean randomAge)
    { 
        super(field, location, randomAge); 

        canEat = new HashMap<>(); 
        diseasesInfectedWith = new ArrayList<>(); 
        canEat = new HashMap<>(); 

        breedingMechanism = new BreedingMechanism(this);
        feedingMechanism = new FeedingMechanism(this);
        diseaseMechanism = new DiseaseMechanism(this);
        canEat = new HashMap<>(); 

        isFemale = rand.nextBoolean(); 
        feedingMechanism.setInitialFoodValue();
        diseaseMechanism.randomlyInfect();
        setCanEat(); 

    }

    /**
     * @return BreedingMechanism object
     */
    public BreedingMechanism getBreedingMechanism()
    {
        return breedingMechanism; 
    }

    /**
     * Make this Animal act - that is: make it do
     * whatever it wants/needs to do. 
     * Actions differ from night and day
     * @param newActors A list to receive newly born Animals
     * @param weather Current weather
     * @param step How long animal infected by disease 
     */
    protected void act(List<Actor> newAnimals,Weather weather , int step)
    { 
        switch(getDayOrNight()){ 
            case NIGHT:  
            break; 
            case DAY: 
            incrementAge();
            incrementHunger();
            if(isAlive()) {
                diseaseMechanism.infectOtherAnimals(meet() ,step);
                breedingMechanism.giveBirth(newAnimals);            
                // Move towards a source of food if found.
                Location newLocation = feedingMechanism.findFood(canEat, weather);
                if(newLocation == null) { 
                    // No food found - try to move to a free location.
                    newLocation = pickAllowedLocation(allowedLocations(getField().getFreeAdjacentLocations(getLocation()))); 
                }
                // See if it was possible to move.
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                }
            }
            diseaseMechanism.killInfectedAnimals(); 
            break;
        }
    }     

    /** 
     * Returns a list of all animal actors adjacent to animal
     * @return list of adjacent animals of same type
     */
    public List<ActorCanBreed> meet() 
    { 
        List<ActorCanBreed> adjacentActors = new ArrayList<ActorCanBreed>();
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Actor actor = (Actor) field.getObjectAt(where);  
            if (actor != null && actor.getClass().equals(getClass())){ 
                adjacentActors.add((Animal) actor); 
            }
        }
        return adjacentActors;
    } 

    /**
     * Descreases the animal's food level 
     * If 0 the animal is dead 
     */
    public void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    } 

    /**
     * @return food level of animal
     */
    public int getFoodLevel()
    {
        return foodLevel;
    }

    /**
     * Changes value of food level
     * @param value New value of food level
     */
    public void setFoodLevel(int value)
    {
        foodLevel = value;
    }

    /**
     * Does not allow food level to increase above max food level
     */
    public void checkFoodLevel()
    {
        if (foodLevel > getMaxFoodLevel()){
            foodLevel = getMaxFoodLevel();
        }
    }

    /**
     * An animal can breed if it has reached the breeding age. 
     * @return true if age above breeding age
     */
    public boolean checkBreedingAge()
    {
        return age >= getBreedingAge();
    }

    /**
     * @return list pf diseases animal infected with 
     */
    public List<Disease> getDiseasesInfectedWith()
    {
        return diseasesInfectedWith;
    }

    /**
     * Produces a list of locations that are not on water that animal can move into
     * One of that locations on list randomly selected
     * @return randomly selected location from list fo allowed locations
     */
    public List<Location> allowedLocations(List<Location> locations) 
    {  
        List<Location> allowedLocation = new ArrayList<>();
        for(Location location : locations){ 
            if(!(location.checkIsWaterArea())){ 
                allowedLocation.add(location);
            }
        }   
        if(allowedLocation.size() == 0){ 
            return null;
        }
        return allowedLocation; 
    }

    /**
     * Randomly selects location from allowed locations
     * @return Location from list allowed locations
     * @param allowedLocation list of allowed locations actor can move to
     */
    public Location pickAllowedLocation(List<Location> allowedLocation)
    {
        if (allowedLocation != null){
        int randomLocation = rand.nextInt(allowedLocation.size()); 
        Location allowed = allowedLocation.get(randomLocation);
        return allowedLocation.get(randomLocation);}
        else{
            return null;
        }
    }

    /** 
     * @return hashmap of animal type and food level of prey 
     */
    protected HashMap<Class<? extends Actor>,Integer> getCanEat(){
        return canEat;
    } 

    /** 
     * @return true if animal is female
     */
    public boolean getIsFemale() 
    { 
        return isFemale;
    } 

    /**
     * Sets the value of isInfected to parameter 
     * @param newBool New value isInfected changed to
     */
    public void setIsInfected(boolean newBool)
    {
        isInfected = newBool; 
    }

    /** 
     * @return true if animal is infected with a disease 
     */
    public boolean getIsInfected()
    {
        return isInfected;
    }  

    /**
     * Add disease to list of disease animal has 
     */
    public void addDisease(Disease disease) 
    { 
        diseasesInfectedWith.add(disease);
    } 

    /**
     * @return age at which animal can breed
     */
    abstract public int getBreedingAge();

    /**
     * @return probability that if breeding conditions are met, births will occur
     */
    abstract public double getBreedingProbability();

    /**
     * @return number of offspring produced when animal breeds
     */
    abstract public int getMaxLitterSize();

    /**
     * @return new animal
     */
    abstract public Actor createActor(boolean randomAge, Field field, Location location); 

    /**
     * @return subclass of animal
     */
    abstract public Class getActorClass(); 

    /**
     * Adds the class name and food value of animal's prey
     */
    abstract public void setCanEat();

    /**
     * @return max food level fo animal
     */
    abstract public int getMaxFoodLevel();

}
