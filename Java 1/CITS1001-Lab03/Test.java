
/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Test
     */
    public Test()
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
    public static boolean ison(int x, int[] a)
    {
        boolean issubset = false;
        int index = 0;
        while(index < a.length && !issubset)
        {
            int setnumber = a[index];
            if (setnumber == x){return true;}
            index++;
        }
        return false;
    }
}
