import java.util.ArrayList;

/** 
 * Example disease that only affects rats and bears
 * 
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Disease1 extends Disease
{
    // Charectaristics all form of this disease share 

    // Probability that infected actor will pass disease on
    private static final double INFECTIOUS_RATE = 0.3;
    // Probability that newly created susceptible animal will have disease
    private static final double PROBABILITY_RANDOM_INFECTION = 0.05;
    // Probability that infected animal will die of disease
    private static final double PROBABILITY_DEATH = 0.4;
    // Length of infected period in steps
    private static final int DISEASE_PERIOD_STEPS = 5;

    /**
     * Adds all susceptible animals to list
     */
    public Disease1() 
    { 
        setSusceptibleAnimal();        
    }

    /**
     * @return rate at which animals of same type catch disease
     */
    public double getInfectiousRate()
    {
        return INFECTIOUS_RATE;
    } 

    /**
     * @return probability that newly created susceptible animal will have disease
     */
    protected double getProbabilityRandomInfection()
    {
        return PROBABILITY_RANDOM_INFECTION;
    }

    /**
     * @return probability animal will die if infected
     */
    protected double getProbabilityDeath()
    {
        return PROBABILITY_DEATH;
    }  

    /**
     * Adds all suscpetible animals of disease to list
     */
    protected void setSusceptibleAnimal()
    {
        getSusceptibleAnimals().add(Rat.class);
        getSusceptibleAnimals().add(Bear.class);
    } 
    
    /** 
     * @return total amount of steps actor can take to cure disease1
     */
    protected int getTotalDiseaseSteps() 
    { 
        return DISEASE_PERIOD_STEPS;
    }
}
