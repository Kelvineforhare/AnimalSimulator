import java.util.ArrayList;

/**
 * Class that stores all diseases 
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class DiseaseGenerator
{
    // List stores all diseases
    private ArrayList<Disease> allDiseases;

    /**
     * Constructor for objects of class DiseaseGenerator
     */
    public DiseaseGenerator()
    { 
        allDiseases = new ArrayList<>();
        addDiseases();        
    } 

    /**
     * @return list of all diseases
     */
    public  ArrayList<Disease> getAllDisease() 
    {  
        return allDiseases;
    } 

    /**
     * Add a disease to the list of all diseases
     */
    public void addDiseases() 
    { 
        Disease1 disease1 = new Disease1();
        allDiseases.add(disease1);
    }

}
