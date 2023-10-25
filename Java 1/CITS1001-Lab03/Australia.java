
/**
 * Write a description of class Australia here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Australia
{
 private State[] states;
 public State getState(int k)
 {return states[k];}
 public int timeDifference(State x, State y)
 {
 int tx = x.getTimeZone();
 if (x.getDST()) {tx++;}
 int ty = y.getTimeZone();
 if (y.getDST()) {
     ty++;
    }
 return Math.abs(tx - ty);
 }
}
