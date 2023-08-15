package org.foodie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.foodie.dbuitl.DBConnection;
import org.foodie.pojo.UserPojo;

/**
 *
 * @author AFROZ
 */

public class UserDao {

    public static Map<String, String> validateStaff(String emailId, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT staffs.staff_id, staffs.staff_name FROM users, staffs WHERE staffs.email_id = users.email_id AND users.email_id = ? AND users.password = ?";
//        String qry = "SELECT s.staff_id, s.staff_name "
//                + "FROM staffs s, users u, companies c "
//                + "WHERE s.email_id = u.email_id AND s.company_id = c.company_id AND "
//                + "u.email_id = 'B@GMAIL.COM' AND u.password = 'MTIzNA==' AND c.status = 'ACTIVE'";

        String qry = "SELECT s.staff_id, s.staff_name "
                + "FROM staffs s "
                + "JOIN users u ON s.email_id = u.email_id "
                + "JOIN companies c ON s.company_id = c.company_id "
                + "WHERE u.email_id = ? AND u.password = ? AND c.status = 'ACTIVE'";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, emailId);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        Map<String, String> staff = null;
        if (rs.next()) {
            staff = new HashMap<>();
            staff.put("emailId", emailId);
            staff.put("userName", rs.getString("staff_name"));
            staff.put("staffId", rs.getString("staff_id"));
        }
        return staff;
    }

    public static Map<String, String> validateCustomer(String emailId, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT c.customer_id, c.customer_name FROM customers c, users u "
//                + "WHERE u.email_id = c.email_id AND u.email_id = ? AND u.password = ?";

        String qry = "SELECT c.customer_id, c.customer_name "
                + "FROM customers c "
                + "JOIN users u ON u.email_id = c.email_id "
                + "WHERE u.email_id = ? AND u.password = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, emailId);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        Map<String, String> customer = null;
        if (rs.next()) {
            customer = new HashMap<>();
            customer.put("emailId", emailId);
            customer.put("userName", rs.getString("customer_name"));
            customer.put("customerId", rs.getString("customer_id"));
        }
        return customer;
    }

    public static boolean addUser(UserPojo user) throws SQLException {
        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement("insert into users values (?,?,?)");
        ps.setString(1, user.getEmailId());
        ps.setString(2, user.getMobileNo());
        ps.setString(3, user.getPassword());
        int result = ps.executeUpdate();

        return result > 0;
    }

    public static boolean updateUser(UserPojo user) throws SQLException {
        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement("UPDATE users SET mobile_no = ?, password = ? where email_id = ?");

        ps.setString(1, user.getMobileNo());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmailId());

        int result = ps.executeUpdate();

        return result > 0;
    }

}
