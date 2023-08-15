package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class UserCredentials {

    private static String userName;
    private static String userId;
    private static String emailId;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserCredentials.userName = userName;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserCredentials.userId = userId;
    }

    public static String getEmailId() {
        return emailId;
    }

    public static void setEmailId(String emailId) {
        UserCredentials.emailId = emailId;
    }
}
