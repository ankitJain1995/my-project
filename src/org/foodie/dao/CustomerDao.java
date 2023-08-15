
package org.foodie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.foodie.dbuitl.DBConnection;
import org.foodie.pojo.CustomerPojo;

/**
 *
 * @author AFROZ
 */
public class CustomerDao {

    public static boolean addCustomer(CustomerPojo customer) throws SQLException {

        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        PreparedStatement ps = conn.prepareStatement("insert into customers values (?,?,?,?)");
        ps.setString(1, getNewCustomerId());
        ps.setString(2, customer.getEmailId());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getCustomerName());

        boolean isUserAdded = UserDao.addUser(customer);
        int result = ps.executeUpdate();
        if (result == 0 || isUserAdded == false) {
            conn.rollback();
            return false;
        }

        conn.commit();

        return true;

    }

    public static boolean updateCustomer(CustomerPojo customer) throws SQLException {

        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        PreparedStatement ps = conn.prepareStatement("UPDATE customers SET customer_name = ?, address = ? where customer_id = ?");

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getCustomerId());

        boolean isUserAdded = UserDao.updateUser(customer);
        int result = ps.executeUpdate();
        if (result == 0 || isUserAdded == false) {
            conn.rollback();
            return false;
        }

        conn.commit();

        return true;

    }

    public static CustomerPojo getCustomerDetailsByCustomerId(String customerId) throws SQLException {

        Connection conn = DBConnection.getConnection();
        String qry = "SELECT c.customer_name, u.mobile_no, u.password, c.address, u.email_id "
                + "FROM customers c "
                + "JOIN users u ON u.email_id = c.email_id "
                + "WHERE c.customer_id = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, customerId);
        ResultSet result = ps.executeQuery();

        CustomerPojo customer = null;

        if (result.next()) {
            customer = new CustomerPojo();
            customer.setCustomerId(customerId);
            customer.setCustomerName(result.getString("customer_name"));
            customer.setMobileNo(result.getString("mobile_no"));
            customer.setPassword(result.getString("password"));
            customer.setAddress(result.getString("address"));
            customer.setEmailId(result.getString("email_id"));

        }

        return customer;

    }

    public static String getNewCustomerId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select count(*) from customers");
        rs.next();
        return "CTMR" + (100 + rs.getInt(1));
    }
}
