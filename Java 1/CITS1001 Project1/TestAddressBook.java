import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test cases for the project AddressBook class
 * 
 * @author Arran Stewart
 * @version March 2017
 */
public class TestAddressBook {

  Person peopleToStore[] = {
      new Person("Harry", "Osborn"),
      new Person("Gwen", "Stacy"),
      new Person("Mary Jane", "Watson"),
      new Person("May", "Parker")
  };
  
  Person peopleNotToStore[] = {
      new Person("Felicia", "Hardy"),
      new Person("Jonah", "Jameson"),
      new Person("Otto", "Octavius"),
      new Person("Ben", "Reilly")
  };

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }
  
  /** Use reflection to extract the contacts list, even though it's private.
   */
  @SuppressWarnings("unchecked")
  public static ArrayList<Person> extractContacts(AddressBook addr) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    Field contactListFld = addr.getClass().getDeclaredField("contacts"); 
    
    contactListFld.setAccessible(true);
    return (ArrayList<Person>) contactListFld.get(addr);
    
  }

  /**
   * Test method for {@link AddressBook#AddressBook()}.
   */
  @Test
  public final void testConstructor() {
    AddressBook addr = new AddressBook();
    ArrayList<Person> contacts = null;
    
    try {
      contacts = extractContacts(addr);
      assertEquals("list should be empty on construction, w/ size 0", 0, contacts.size() );
      
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test method for {@link AddressBook#addPerson(Person)}.
   */
  @Test
  public final void testAddPerson() {
    Person p = new Person("", "somesurname");
    AddressBook addr = new AddressBook();
    addr.addPerson(p);
    ArrayList<Person> contacts = null;
    
    try {
      contacts = extractContacts(addr);
      assertEquals("list should have size 1", 1, contacts.size() );
      assertTrue("list should contain the added person", contacts.get(0) == p);
      
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test method for {@link AddressBook#findPerson(java.lang.String, java.lang.String)}.
   */
  @Test
  public final void testFindAddedPerson() {
    AddressBook addr = new AddressBook();
    for (int i = 0; i < peopleToStore.length; i++) {
      addr.addPerson( peopleToStore[i] );
    }
    
    for (int i = 0; i < peopleToStore.length; i++) {
      Person toFind = peopleToStore[i];
      Person result = addr.findPerson(toFind.getFirstName(), toFind.getSurname());
      assertTrue("found person should be identical to added person", toFind == result);
    }
    
  }
  


}
