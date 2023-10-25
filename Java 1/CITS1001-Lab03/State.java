
/**
 * Write a description of class State here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class State
{
 private String name;
 private int timeZone;
 private boolean DST;
 public State(String n, int tz, boolean d)
 {
 name = n;
 timeZone = tz;
 DST = d;
 }
 public String getName()
 {return name;}

 public int getTimeZone()
 {return timeZone;}

 public boolean getDST()
 {return DST;}

 public void setDST(boolean b)
 {DST = b;}
}