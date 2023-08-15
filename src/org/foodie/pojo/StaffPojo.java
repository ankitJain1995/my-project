package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class StaffPojo extends UserPojo {

    private String staffId;
    private String companyId;
    private String emailId;
    private String staffName;

    public StaffPojo() {
    }

    public StaffPojo(String staffId, String companyId, String emailId, String staffName) {
        this.staffId = staffId;
        this.companyId = companyId;
        this.emailId = emailId;
        this.staffName = staffName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public String toString() {
        return "StaffPojo{" + "staffId=" + staffId + ", companyId=" + companyId + ", emailId=" + emailId + ", staffName=" + staffName + super.toString() + '}';
    }

}
