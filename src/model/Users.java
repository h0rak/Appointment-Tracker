package model;

/** The Users class.
 * This class houses the constructor for the Users data type as well as the getters and setters.
 */
public class Users {

    private int userId;
    private String userName;
    private String userPassword;

    /** The Users constructor method.
     * This is a standard constructor.
     * @param userId the userId is a unique ID for the user
     * @param userName the userName is the name of the user
     * @param userPassword the userPassword is the password of the user
     * */
    public Users(int userId, String userName, String userPassword){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /** The toString method.
     * This method returns a userName instead of memory location of the user
     * @return returns a userName
     */
    @Override
    public String toString(){
        return userName;
    }

    /** The getUserId method.
     * @return returns a userId
     */
    public int getUserId() {
        return userId;
    }

    /** The setUserId method.
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** The getUserName method.
     * @return returns a userName
     */
    public String getUserName() {
        return userName;
    }

    /** The setUserName method.
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** The getUserPassword method.
     * @return returns a userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /** The setUserPassword method.
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
