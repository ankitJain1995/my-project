package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class CompanyPojo {

    private String companyId;
    private String companyName;
    private String emailId;
    private String ownerName;
    private String password;
    private String securityKey;

    public CompanyPojo() {
    }

    public CompanyPojo(String companyId, String companyName, String emailId, String ownerName, String password, String securityKey) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.emailId = emailId;
        this.ownerName = ownerName;
        this.password = password;
        this.securityKey = securityKey;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    @Override
    public String toString() {
        return "CompanyPojo{" + "companyId=" + companyId + ", companyName=" + companyName + ", emailId=" + emailId + ", ownerName=" + ownerName + ", password=" + password + ", securityKey=" + securityKey + '}';
    }

}
