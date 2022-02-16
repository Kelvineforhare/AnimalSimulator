import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class that contains methods required to simulate breeding
 * Actors may only breed if they fulfill all bredding conditions 
 * Probability that elegible animals may not breed even though conditions are met 
 * 
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class BreedingMechanism
{

    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom(); 
    // holds animal could potentially breed
    private ActorCanBreed thisActor;

    /**
     * Create an object that contains methods that simulate breeding
     * @param actor Actor that could potentially breed 
     */
    public BreedingMechanism(ActorCanBreed actor)
    {
        this.thisActor = actor;
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(rand.nextDouble() <= thisActor.getBreedingProbability() && canBreed(thisActor.meet()) ) {
            births = rand.nextInt(thisActor.getMaxLitterSize() + 1) ;
        }
        return births;
    }

    /**
     * An actor can breed if it has reached the breeding age. 
     * @return true if age above breeding age
     */
    private boolean checkBreedingAge()
    {
        return thisActor.getAge() >= thisActor.getBreedingAge();
    }

    /**
     * An actor can breed if it of the same type if they are opposite genders and if they are above breeding age. 
     * @param adjacentActors provides list of adjacent actors 
     * @return true if the breeding condtions are all met
     */
    private boolean canBreed(List<ActorCanBreed> adjacentactors)
    {
        for (ActorCanBreed actor: adjacentactors)
        { 
            actor = (ActorCanBreed) actor;
            if((actor.getIsFemale() != thisActor.getIsFemale()) && actor.checkBreedingAge() && checkBreedingAge() && isSameactor(actor)) {
                return true;
            }
        } 
        return false;
    }

    /**
     * Check whether or not this actor is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newactors A list to return newly born actors.
     */
    public void giveBirth(List<Actor> newActors)
    {
        // Get a list of adjacent free locations.
        Field field = thisActor.getField();
        List<Location> free = thisActor.allowedLocations(field.getFreeAdjacentLocations(thisActor.getLocation()));
        int births = breed();
        if (free != null){
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Actor young = thisActor.createActor(false, field, loc);
            newActors.add(young);
        }
    }
    }  

    /** 
     * @return true if two actors are of the same dynamic type
     */
    private boolean isSameactor(ActorCanBreed otherActor) 
    {
        if (thisActor.getActorClass().equals(otherActor.getActorClass())){
            return true;
        }
        return false;
    }

}
