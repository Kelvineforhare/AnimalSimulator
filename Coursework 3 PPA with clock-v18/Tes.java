import java.io.IOException;
import java.util.ArrayList;

/**
 * Write a description of class Tes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tes
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Tes
     */
    public Tes()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y) throws java.io.IOException
    {
        if (y ==0){ 
            throw new IOException("");
        } 
        
        ArrayList<Integer> a = new ArrayList<>(); 
        
        a.add(1);
        a.add(2); 
        a.add(3); 
        
        for(Integer x : a){ 
            if(x%2 ==1){ 
                a.remove(x);
            }
        }
        
        return x + y;
    }
}
