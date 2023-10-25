import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the project Person class
 * 
 * @author Arran Stewart
 * @version March 2017
 */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPerson {
  
  Person bobbie;
  
  ByteArrayOutputStream outContent; 
  ByteArrayOutputStream errContent; 
  PrintStream origOut ;
  PrintStream origErr ;
  
  Person bruce;

    
  @Before
  public void setUp() {
    bobbie = new Person("Bobbie", "McGee");
  }

  /**
   * Test method for {@link Person#getFirstName()}.
   */
  @Test
  public void testGetFirstName() {
    assertEquals("first name should match", "Bobbie", bobbie.getFirstName());
  }
  
  /**
   * Test method for {@link Person#getSurname()}.
   */
  @Test
  public void testGetSurname() {
    assertEquals("surname should match", "McGee", bobbie.getSurname());
  }
  
  /**
   * Test method for {@link Person#setMobile(java.lang.String)}.
   */
  @Test
  public void setValidMobileNumber() {
    String validNumbers[] = { "0401022925", "000000" };
    
    for (int i = 0; i < validNumbers.length; i++) {
      String mobile = validNumbers[i];
      Person p = new Person("Angela", "Lansbury");
      p.setMobile(mobile);
      assertEquals("valid mobile num should be set", mobile, p.getMobile() );
    }
  }
  
  /**
   * Test method for {@link Person#setMobile(java.lang.String)}.
   */
  @Test
  public void setInvalidMobileNumber() {
    String validNumbers[] = { "a0402044988", "address@website.com" };
    
    for (int i = 0; i < validNumbers.length; i++) {
      String mobile = validNumbers[i];
      Person p = new Person("Angela", "Lansbury");
      p.setMobile(mobile);
      assertTrue("invalid mobile num should have no effect", p.getMobile() == null);
    }
  }

  /**
   * Test method for {@link Person#setEmail(java.lang.String)}.
   */
  @Test
  public void testSetValidEmails() {
    String validEmails[] = { "foo@bar.com", "logan.howlett@xmen.org" };
    
    for (int i = 0; i < validEmails.length; i++) {
      String email = validEmails[i];
      Person p = new Person("Logan", "Howlett");
      p.setEmail(email);
      assertEquals("valid email should be set", email, p.getEmail() );
    }
  }
  
  /**
   * Test method for {@link Person#setEmail(java.lang.String)}.
   */
  @Test
  public void testSetInvalidEmails() {
    String invalidEmails[] = { "" };
    
    for (int i = 0; i < invalidEmails.length; i++) {
      String email = invalidEmails[i];
      Person p = new Person("Logan", "Howlett");
      p.setEmail(email);
      assertTrue("invalid email should have no effect", p.getEmail() == null);
    }
  }
  
  
  @Before
  public void setUpPrintTest() {
    
    origOut = System.out;
    origErr = System.err;

    outContent = new ByteArrayOutputStream();
    errContent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
    
    bruce = new Person("Bruce", "Banner");
    bruce.setEmail("b.banner@synbio.berkeley.edu");
    bruce.setMobile("0410782344");
    bruce.addSocialMediaAccount("@bruce", "twitter", "http://twitter.com/",50);
    bruce.addSocialMediaAccount("bruce.banner", "facebook", "http://facebook.com/",60);
  }
  
  @After
  public void cleanUpStreams() {
      System.setOut(origOut);
      System.setErr(origErr);
  }
  

  
  /**
   * Test method for {@link Person#addSocialMediaAccount(java.lang.String, java.lang.String, java.lang.String)}.
   */
  @Test
  public void testSocialMediaAccountValid() {
    Person p = new Person("Logan", "Howlett");
    String userID = "logan.howlett"; 
    String websiteName = "Facebook"; // TODO: try empty name
    String websiteURL = "http://facebook.com/";
    
    p.addSocialMediaAccount(userID, websiteName, websiteURL,0);
    String returnedUserID = p.getSocialMediaID(websiteName);
    
    assertEquals("should return validly set social media ID", userID, returnedUserID);
  }
  
  
  /**
   * Test method for {@link Person#printContact()}.
   */
  @Test
  public void testPrintContactDetailsLength() {

    
    bruce.printContactDetails();
    String outputLines[] = outContent.toString().split("\\r?\\n");
    
    assertEquals("expect stdout 5 lines longs", 5, outputLines.length);
  }
  
  /**
   * Test method for {@link Person#printContact()}.
   */
  @Test
  public void testPrintContactDetailsFirstLine() {

    bruce.printContactDetails();
    String outputLines[] = outContent.toString().split("\\r?\\n");
    String expectedFirstLine = "name: " + bruce.getFirstName() + " " + bruce.getSurname();
    
    assertEquals("stderr should be empty", "", errContent.toString());
    assertEquals("first line of stdout should be name: line", expectedFirstLine, outputLines[0]);
  }
  


    @Test
    public void asd()
    {
    }
}
