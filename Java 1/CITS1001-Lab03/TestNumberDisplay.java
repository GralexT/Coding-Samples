import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** Test a NumberDisplay object
 * 
 */
public class TestNumberDisplay {

  @Test
  /**
   * Test that object is initialized so that initial value is 0,
   * and initial display value is "00".
   */
  public void testConstructor() {
    NumberDisplay nd = new NumberDisplay(10);
    assertEquals(0, nd.getValue());
    assertEquals("00", nd.getDisplayValue());
     
  }
  
  @Test
  /**
   * Test that setValue alters the value and the displayed number.
   */
  public void testSetValue() {
    NumberDisplay nd = new NumberDisplay(10);
    nd.setValue(5);
    assertEquals(5, nd.getValue());
    assertEquals("05", nd.getDisplayValue());
     
  }
  
  @Test
  /**
   * Test that the value "rolls over" correctly.
   */
  public void testRollover() {
    NumberDisplay nd = new NumberDisplay(10);
    nd.setValue(9);
    
    nd.increment();
    assertEquals(0, nd.getValue());
    assertEquals("00", nd.getDisplayValue());
     
  }

}
