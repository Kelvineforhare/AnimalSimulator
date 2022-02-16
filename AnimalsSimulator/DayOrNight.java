
/**
 * Enumeration class DayOrNight - A class representing whether it is day or night
 *
 * @author Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public enum DayOrNight{ 
    DAY, NIGHT ; 
    
    public static ClockDisplay clock = new ClockDisplay(10,0); //default time

    /** 
     * Based of the time returns whether it is day or night 
     * @return day or night enumerated variable
     */
    public static DayOrNight getDayOrNight() 
    {   
        int hour = clock.getHour(); 
        DayOrNight day;
        if ((hour >= 18) || (hour < 10)){ 
            day = DayOrNight.NIGHT;
        } 
        else{ 
            day = DayOrNight.DAY;
        }
        return day;
    } 

    /** 
     * @return the current time
     */
    public static String getTime() 
    { 
        return clock.getTime();
    } 

    /** 
     * increments the time based of the the current step 
     * @param the step the simulator is on
     */
    public static void incrementTime(int step) 
    { 
        if (step != 0 && step % 1 == 0) {
            clock.timeTick();
        }
    }
}  

