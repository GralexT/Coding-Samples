import java.util.ArrayList;
/**
 * Person details including contacts and social media accounts
 * 
 * @author Name: Grant Alexander Taylor.    Student Number: 21947126.
 * 
 * @version 0.
 */
public class Person {
  private String firstName;
  private String surname;
  private String mobile;
  private String email;
  private ArrayList<SocialMediaAccount> socialMediaAccounts;
  
  /**
   * Create a Person with the first name and surname given by
   * the parameters.
   */
  public Person(String firstName, String surname) {
    this.firstName = firstName;
    this.surname = surname;
    mobile = null;
    email = null;
    this.socialMediaAccounts = new ArrayList<SocialMediaAccount>();
  }

  /**
   * @return the person's first name
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Set the person's first name 
   * unless the parameter is an empty string.
   */
  public void setFirstName(String firstName) {
    if (firstName != ("") && !firstName.contains (" ")) {
        this.firstName = firstName;
    }
  }
  
 
  /**
   * Return the person's surname
   */
  public String getSurname() {
    return surname;
  }
  
  /**
   * Set the person's surname
   * unless the parameter is an empty string.
   */
  public void setSurname(String surname) {
     if (surname != ("") && !surname.contains (" ")){
          this.surname = surname;
     }
  }
  
 
  /**
   * Return the person's mobile phone number
   */
  public String getMobile() {
    return mobile;
  }
  
  /**
   * Set the person's mobile phone number
   */
  public void setMobile(String mobile) {
      for (int mobilenumbercheck : mobile.toCharArray()) {
        if (!Character.isDigit(mobilenumbercheck)) {
            return;
        }
    }
    this.mobile = mobile;
  }
  
  /**
   * Return the person's email address
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Set the person's email address
   */
  public void setEmail(String email) {
    if (email != ("") && !email.contains (" ") && email.contains ("@")){
          this.email = email;
    }
  }
  
  /**
   * Create a new SocialMediaAccount object, and add it to the socialMediaAccounts list.
   * 
   */
  public void addSocialMediaAccount(String userID, String websiteName, String websiteURL, int activityLevel) {
    // Empty strings and invalid activityLevel will not pass.
    if (userID != "" && websiteName != "" && websiteURL != "" && activityLevel >= 0) {
        SocialMediaAccount account = new SocialMediaAccount(userID, websiteName, websiteURL, activityLevel);
        socialMediaAccounts.add(account);
    }
  }
  
  /**
   * Search the socialMediaAccounts list for an account on the website specified by the websiteName 
   * parameter, and return the userID for that account. If no such account can be found, return
   * null.
   */
  public String getSocialMediaID(String websiteName) {
    String result = "";
      for (SocialMediaAccount account : socialMediaAccounts){
        if (websiteName == account.getWebsiteName())
        {
              result = account.getUserID();
        }
        else{
            result = null;
        }
    }
    return result;}
    
  /** Print the person's contact details in the format given in the
   * project specifications.
   */
  public void printContactDetails() {
    System.out.println("name: "+ firstName + " " + surname);
    System.out.println("mobile: " + mobile);
    System.out.println("email: " + email);
    
    String username = "";
    String website = "";
    String url = "";
    for (SocialMediaAccount account : socialMediaAccounts){
        username = account.getUserID();
        website = account.getWebsiteName();
        url = account.getWebsiteURL();
        System.out.println(website + ", " + username + ", " + url);
    }
  }
}