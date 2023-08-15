package org.foodie.pojo;

/**
 *
 * @author AFROZ
 */
public class PlaceOrderPojo {
//    (order_id, product_id, customer_id, staff_id, review, status, company_id)

    private String orderId;
    private String productId;
    private String customerId;
    private String deliveryStaffId;
    private String companyId;

    public PlaceOrderPojo() {
    }

    public PlaceOrderPojo(String orderId, String productId, String customerId, String deliveryStaffId, String companyId) {
        this.orderId = orderId;
        this.productId = productId;
        this.customerId = customerId;
        this.deliveryStaffId = deliveryStaffId;
        this.companyId = companyId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryStaffId() {
        return deliveryStaffId;
    }

    public void setDeliveryStaffId(String deliveryStaffId) {
        this.deliveryStaffId = deliveryStaffId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "PlaceOrderPojo{" + "orderId=" + orderId + ", productId=" + productId + ", customerId=" + customerId + ", deliveryStaffId=" + deliveryStaffId + ", companyId=" + companyId + '}';
    }

}
