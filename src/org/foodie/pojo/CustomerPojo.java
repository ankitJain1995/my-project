package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class CustomerPojo extends UserPojo {

    private String customerId;
    private String emailId;
    private String address;
    private String CustomerName;

    public CustomerPojo() {
    }

    public CustomerPojo(String customerId, String emailId, String address, String CustomerName) {
        this.customerId = customerId;
        this.emailId = emailId;
        this.address = address;
        this.CustomerName = CustomerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    @Override
    public String toString() {
        return "CustomerPojo{" + "customerId=" + customerId + ", emailId=" + emailId + ", address=" + address + ", CustomerName=" + CustomerName + super.toString() + "}";
    }

}
