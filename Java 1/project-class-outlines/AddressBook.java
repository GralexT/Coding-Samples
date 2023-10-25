import java.util.ArrayList;
/**
 * Manage an AddressBook of Person contacts
 * 
 * @author Name: Grant Alexander Taylor.    Student Number: 21947126.
 * 
 * @version 0.1
 */
public class AddressBook {

  private ArrayList<Person> contacts;

  public AddressBook(){
      this.contacts = new ArrayList<Person>();
  }

  /** Add the person p to the "contacts" list, unless they have the same 
   * surname and first name as a person already in the list, in which case
   * do not add them, but instead print the error message "could not add person".
   *  
   */
  public void addPerson(Person p) {
    String firstnamelist = "";
    String surnamelist = "";
    String firstnamep = "";
    String surnamep = "";
      for (Person list : contacts){
        firstnamelist = list.getFirstName();
        surnamelist = list.getSurname();
        firstnamep = p.getFirstName();
        surnamep = p.getSurname();
        if (firstnamelist == firstnamep && surnamelist == surnamep){
            return;
        }
    }
    contacts.add(p);
  }
  
  /** Search for a person in the "contacts" list by first name and surname,
   * and return the relevant Person object if one matches, or otherwise return null.
   */
  public Person findPerson(String firstName, String surname) {
    String firstnamelist = "";
    String surnamelist = "";
    for (Person list : contacts){
        firstnamelist = list.getFirstName();
        surnamelist = list.getSurname();
        if (firstName == firstnamelist && surname == surnamelist){
            return list;
        }
    }
    return null;
  }
  
  /**
   * Find the most social person in the address book.
   */
  public Person findMostSocial() {
      return null;
  }
}
