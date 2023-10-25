
/**
 * A user ID on a social media site (like Twitter or Facebook)
 * 
 * @author Arran Stewart
 * @version March 2017
 */
public class SocialMediaAccount {

    private String userID;
    private String websiteName;
    private String websiteURL;
    /**
     * activityLevel indicates the user's activity on this account.  
     * e.g. number of friends or tweets or posts or combination of these.
     */
    private int activityLevel; 

     /** 
     * Construct a SocialMediaAccount object, with the specified userID,
     * website name, and website URL and activity Level
     */
    public SocialMediaAccount(String userID, String websiteName, String websiteURL, int activityLevel) {
        this.userID = userID;
        this.websiteName = websiteName;
        this.websiteURL = websiteURL;
        this.activityLevel = activityLevel;
    }

    /**
     * Return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Set the userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the websiteName
     */
    public String getWebsiteName() {
        return websiteName;
    }

    /**
     * @return the websiteURL
     */
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
     * @return the activityLevel
     */
    public int getActivityLevel() {
        return activityLevel;
    }

    /**
     * Set or reset the activityLevel
     * which must be 0 or greater.
     */
    public void setActivityLevel(int newActivityLevel) {
        if (newActivityLevel >= 0) {
            activityLevel = newActivityLevel;
        } else {
            System.out.println("Error: ActivityLevel must be an integer >=0. Please try again.");
        }
    }

}
