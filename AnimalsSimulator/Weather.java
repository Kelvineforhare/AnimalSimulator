import java.util.Random;

/**
 * A class used to represent the weather
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public enum Weather
{
    FOG,RAIN,SNOW,NORMAL;  

    /**  
     * Whether is generated randomly 
     * @return the current weather as an enum variable  
     */
    public Weather getWeather() 
    { 
        Random rand = new Random(); 
        double num = rand.nextFloat(); 
        if(num <= 0.1){ 
            return FOG;
        }  
        else if(num <= 0.2){ 
            return SNOW;
        }
        else if (num <= 0.3){ 
            return RAIN;
        } 
        else { 
            return NORMAL;
        }
    } 

    /**  
     * Used as a default weather
     * @return Normal weather enum variable
     */
    public Weather getNormal() 
    { 
        return NORMAL;
    }
}
