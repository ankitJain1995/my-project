package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class UserPojo {
    
    private String mobileNo;
    private String password;
    private String emailId;
    
    public UserPojo() {
    }
    
    public UserPojo(String mobileNo, String password, String emailId) {
        this.mobileNo = mobileNo;
        this.password = password;
        this.emailId = emailId;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    @Override
    public String toString() {
        return "UserPojo{" + "mobileNo=" + mobileNo + ", password=" + password
                + ", emailId=" + emailId + '}';
    }
    
}
