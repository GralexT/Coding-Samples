import java.lang.Math;
/**
 * Write a description of class CheckPositive here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CheckPositive
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class CheckPositive
     */
    public CheckPositive()
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
    public int returnPositive(int number)
        {
            if (number < 0){
                return number*-1;
            }
            else{
                return number;
            } 
        }
    public int returnP(int number)
    {
        return Math.abs(number);
    }
    
}
