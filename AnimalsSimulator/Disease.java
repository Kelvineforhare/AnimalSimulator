import java.util.ArrayList;

/** 
 * A class modelling diseases 
 * Disease can only be given to animals
 * 
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public abstract class Disease
{ 
    // List containing all susceptible animals for disease 
    private ArrayList<Class<? extends Animal>> susceptibleAnimals;
    //the steps program has taken
    private int steps;
    /**
     * Constructor for objects of class Disease
     * Creates arryList to store susceptible animals for disease
     */
    public Disease()
    { 
        susceptibleAnimals = new ArrayList<Class<? extends Animal>>(); 
    }    

    /**
     * @return list of suscpetible animals
     */
    public ArrayList<Class<? extends Animal>> getSusceptibleAnimals()
    {
        return susceptibleAnimals;
    }  
    
    /** 
     * checks if a the amount of steps is greater than the amount of steps to be cured of disease 
     * @return true if steps greater total disease steps
     */
    public boolean isCured() 
    { 
        return steps > getTotalDiseaseSteps();
    }  
    
    /** 
     * increment step to the current 
     * @param current step
     */
    public void incrementStep(int steps) 
    { 
        this.steps = steps;
    }

    /**
     * @return infectious rate of disease
     */
    abstract protected double getInfectiousRate();

    /**
     * @return liklihood of disease randomly infecting animal
     */
    abstract protected double getProbabilityRandomInfection(); 

    /**
     * @return probability animal will die
     */
    abstract protected double getProbabilityDeath();     
    
    /** 
     * @return total amount of steps actor can take to cure disease
     */
    abstract protected int getTotalDiseaseSteps();
}
