import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * Class that contains methods required to simulate disease
 * Disease only programmed to infect animals 
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class DiseaseMechanism
{
   
    // holds animal disease is impacting 
    private Animal animal;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom(); 

    /**
     * Create an object that contains methods that simulate disease 
     * @param animal Animal the disease is impacting 
     */
    public DiseaseMechanism(Animal animal)
    {
        this.animal = animal;
    }

    /** 
     * randomly infects proportion of susceptible animals
     */
    public void randomlyInfect() 
    {      
        DiseaseGenerator getDisease = new DiseaseGenerator();
        ArrayList<Disease> allDisease = getDisease.getAllDisease();
        for(Disease disease : allDisease ){   
            for(Class animalType : disease.getSusceptibleAnimals()){ 
                if (animalType.equals(animal.getActorClass())){
                    if (disease.getProbabilityRandomInfection() >= rand.nextFloat()){
                        animal.setIsInfected(true);
                        animal.getDiseasesInfectedWith().add(disease);
                    }
                }
            }
        }
    }

    /** 
     * Based on death probability of disease set animal as dead 
     */
    public void killInfectedAnimals()
    {
        if (animal.getIsInfected()){ 
            for(Disease disease: animal.getDiseasesInfectedWith()){
                if (disease.getProbabilityDeath() >= rand.nextFloat()){
                    animal.setDead();
                }
            } 
        }
    }

    /**  
     * Infected animals may infect other animals of the same type adjacent to them
     * @param adjacentAnimals list of adjecent animals
     * @param step How many steps has animal been infected for 
     */
    public void infectOtherAnimals(List<ActorCanBreed> adjacentAnimals , int step)
    {
        if (animal.getIsInfected()){  
            for(Iterator<Disease> it = animal.getDiseasesInfectedWith().iterator(); it.hasNext();){
                    Disease disease = it.next();
                    if (disease.isCured()){  
                        it.remove();
                        animal.setIsInfected(false);
                    } 
                    else{  
                        for(Disease animalDisease : animal.getDiseasesInfectedWith()){ 
                            if (animalDisease.equals(disease)){ 
                                animalDisease.incrementStep(step);
                            }
                        }
                        for (ActorCanBreed adjacentActor : adjacentAnimals){  
                            Animal adjacentAnimal = (Animal) adjacentActor;
                            if (disease.getInfectiousRate() >= rand.nextFloat() && adjacentAnimal.getActorClass().equals(animal.getActorClass()) 
                            && !(adjacentAnimal.getDiseasesInfectedWith().contains(disease))){    
                                adjacentAnimal.setIsInfected(true);  
                                adjacentAnimal.addDisease(disease);
                            }  
                        } 
                    }                            
            }
        }
    }  
}
    
